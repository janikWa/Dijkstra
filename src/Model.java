import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {

    public static GUI gui;
    public static Controller controller;
    private static boolean addNodesMode = false;
    private static ArrayList<Node> nodes = new ArrayList<>();
    private static ArrayList<Edge> edges = new ArrayList<>();



    //getter and setter
    public static ArrayList<Edge> getEdges() {
        return edges;
    }

    public static boolean getAddNodesMode() {
        return addNodesMode;
    }

    public static ArrayList<Node> getNodes(){
        return nodes;
    }

    public static void setAddNodesMode(boolean mode) {
        addNodesMode = mode;
    }

    public static void setGui(GUI gui) {
        Model.gui = gui;
    }

    public static void setController(Controller controller) {
        Model.controller = controller;
    }

    //Methods
    public static Node newNode(Point loc, String name){
        Node node = new Node(loc.getX(), loc.getY(), name);
        nodes.add(node);
        return node;
    }

    public static void addEdge(Edge edge){
        edges.add(edge);
    }

    public static void removeNode(Node node){
        gui.getMainpanel().remove(node);
        nodes.remove(node);
        while(node.getOutgoingEdges().size()> 0 ){
            removeEdge(node.getOutgoingEdges().getLast());
        }
        while (node.getIngoingEdges().size() > 0){
            removeEdge(node.getIngoingEdges().getLast());
        }
        gui.getFrame().repaint();
    }

    public static void removeEdge(Edge edge){
        gui.getMainpanel().remove(edge);
        gui.getMainpanel().remove(edge.getWeightfield());
        edge.getStartnode().getOutgoingEdges().remove(edge);
        edge.getEndnode().getIngoingEdges().remove(edge);
        edges.remove(edge);
    }

    public static boolean checkDouble(Node node){
        Node newNode = node;
        for (int i = 0; i < nodes.size()-1; i++) {
            if(nodes.get(i).getName().equals(node.getName())){
                nodes.removeLast();
                JOptionPane.showMessageDialog(null, "This name is already in use; please choose another.", "Invalid input", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public static void drawNode(){
        JPanel mainpanel = gui.getMainpanel();
        JFrame frame = gui.getFrame();
        Node node = Model.getNodes().getLast();
        JTextField node_name = new JTextField();
        node_name.setOpaque(false);
        node_name.setForeground(Color.white);
        node_name.setFocusable(false);
        node_name.setText(node.getName());
        node_name.setBorder(null);
        node_name.setHorizontalAlignment(JTextField.CENTER);
        mainpanel.add(node_name).setBounds((int) node.getPosX()-15, (int)node.getPosY()-15, 25,25);
        mainpanel.add(node);
        frame.revalidate();
    }

    public static void drawEdge(){
        JFrame frame = gui.getFrame();
        JPanel mainpanel = gui.getMainpanel();
        Edge edge = Model.getEdges().getLast();
        //JTextField edge_name = new JTextField();
        //edge_name.setOpaque(false);
//        edge_name.setFocusable(false);
//        edge_name.setText(String.valueOf(edge.getWeight()));
//        edge_name.setBorder(null);
//        edge_name.setHorizontalAlignment(JTextField.CENTER);
        mainpanel.add(edge.getWeightfield()).setBounds((int) ((edge.getStart_X()+edge.getEnd_X())/2),(int) ((edge.getStart_Y()+edge.getEnd_Y())/2),30,30);
        mainpanel.add(edge);
        frame.revalidate();
    }

    public static void drawArrow(Graphics2D g2, Node start, Node end,  double arrowLength) {
        //x2,y2 == end of arrow
        double x1 = start.getPosX();
        double y1 = start.getPosY();
        double x2 = end.getPosX();
        double y2 = end.getPosY();
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double arrowX = x2 - Math.cos(angle) * arrowLength;
        double arrowY = y2 - Math.sin(angle) * arrowLength;

        g2.drawLine((int) x2, (int) y2, (int) arrowX, (int) arrowY);

        double angle1 = angle - Math.toRadians(30);
        double angle2 = angle + Math.toRadians(30);

        int x3 = (int) (arrowX - Math.cos(angle1) * arrowLength);
        int y3 = (int) (arrowY - Math.sin(angle1) * arrowLength);
        int x4 = (int) (arrowX - Math.cos(angle2) * arrowLength);
        int y4 = (int) (arrowY - Math.sin(angle2) * arrowLength);

        g2.drawLine((int) arrowX, (int) arrowY, x3, y3);
        g2.drawLine((int) arrowX, (int) arrowY, x4, y4);
    }

    public static void displayNodesEdges(){
        StringBuilder information = new StringBuilder();
        // Information about Nodes
        information.append("Node Information:\n");
        for (int i = 0; i < nodes.size(); i++) {
            information.append("Node: ").append(i).append("\n");
            information.append("Name: ").append(nodes.get(i).getName()).append("\n");
            information.append("PosX: ").append(nodes.get(i).getPosX()).append("\n");
            information.append("PosY: ").append(nodes.get(i).getPosY()).append("\n\n");
        }

        // Information about Edges
        information.append("Edge Information:\n");
        for (int i = 0; i < edges.size(); i++) {
            information.append("Edge: ").append(i).append("\n");
            information.append("Start Node: ").append(edges.get(i).getStartnode().getName()).append("\n");
            information.append("End Node: ").append(edges.get(i).getEndnode().getName()).append("\n");
            information.append("Weight: ").append(edges.get(i).getWeight()).append("\n\n");
        }

        // Erstellen und Konfigurieren des JTextArea
        JTextArea textArea = new JTextArea(information.toString());
        textArea.setEditable(false);

        // Erstellen und Konfigurieren des JScrollPane
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Anpassen der Größe des JOptionPane an den Inhalt
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500)); // Setze die gewünschte Größe

        // Anzeige der Informationen in einem JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Graph Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static double delta(double a, double b){
        return Math.max(a,b)-Math.min(a,b);
    }


    //dijkstras algorithm
    public static void dijkstra(Node node){
        Node startnode = node;
        double inf = Double.POSITIVE_INFINITY;
        HashMap<Node,Double> currentValues = new HashMap<>();

        //initialize with infinity
        for (int i = 0; i < nodes.size(); i++) {
            currentValues.put(nodes.get(i),inf);
        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        Controller controller = new Controller();
        controller.setGui(gui);
        gui.setController(controller);
        setController(controller);
        setGui(gui);
    }
}
