import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    private static JFrame frame;
    private JTextArea posX_Y = new JTextArea();
    private static JPanel mainpanel = new JPanel(new BorderLayout());
    private JButton btn = new JButton("Show Nodes & Edges");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu menu_edge = new JMenu("Edges");

    private JMenuItem add_edge = new JMenuItem("Add edge");
    //rename to Nodes!!
    private JMenu menu_nodes = new JMenu("Nodes");
    private JMenuItem add_node = new JMenuItem("Add Node");
    private JMenuItem remove_node = new JMenuItem("Remove Node");
    private JMenuItem old = new JMenuItem("Add Edge");
    private JMenuItem calculate = new JMenuItem("Calculate");



    public GUI(){
        frame = new JFrame("Dijkstras Algorithm");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);
        frame.setSize(1400,1000);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        mainpanel.setBackground(Color.white);
        menu_nodes.add(add_node);
        menu_nodes.add(remove_node);
        menu_edge.add(add_edge);
        menu.add(menu_nodes);
        menu.add(menu_edge);
        menu.add(calculate);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.add(mainpanel, BorderLayout.CENTER);
        frame.add(btn, BorderLayout.SOUTH);
        frame.revalidate();
    }

    public JButton getButton(){
        return btn;
    }

    public JFrame getFrame(){
        return frame;
    }

    public JPanel getMainpanel(){
        return mainpanel;
    }

    public JMenuItem get_add_node() {
        return add_node;
    }

    public JMenuItem get_add_edge() {
        return add_edge;
    }

    public JMenuItem getCalculate() {
        return calculate;
    }

    public JMenuItem getRemove_node() {
        return remove_node;
    }

    public void setController(Controller controller){
        btn.addActionListener(controller);
        add_node.addActionListener(controller);
        add_edge.addActionListener(controller);
        remove_node.addActionListener(controller);
        calculate.addActionListener(controller);
        mainpanel.addMouseListener(controller);
    }

/*
    public void drawNode(){
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


    public void drawEdge(){
        Edge edge = Model.getEdges().getLast();
        JTextField edge_name = new JTextField();
        edge_name.setOpaque(false);
        edge_name.setFocusable(false);
        edge_name.setText(String.valueOf(edge.getWeight()));
        edge_name.setBorder(null);
        edge_name.setHorizontalAlignment(JTextField.CENTER);
        mainpanel.add(edge_name).setBounds((int) ((edge.getStart_X()+edge.getEnd_X())/2),(int) ((edge.getStart_Y()+edge.getEnd_Y())/2),30,30);
        mainpanel.add(edge);
        frame.revalidate();
    }
*/

}
