import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Node extends JComponent {

    private static int radius = 30;
    private double posX;
    private double posY;
    private String name;
    private ArrayList<Edge>  outgoingEdges;
    private ArrayList<Edge> ingoingEdges;

    public Node(double posX, double posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.outgoingEdges = new ArrayList<>();
        this.ingoingEdges = new ArrayList<>();
    }

    public void addOutGoingEdge(Edge edge){
        outgoingEdges.add(edge);
    }

    public void addIngoinEdges(Edge edge){
        ingoingEdges.add(edge);
    }

    public ArrayList<Edge> getOutgoingEdges(){
        return outgoingEdges;
    }

    public ArrayList<Edge> getIngoingEdges(){
        return ingoingEdges;
    }
    public int getRadius() {
        return radius;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fillOval((int) (posX - radius), (int) (posY - radius), 2 * radius, 2 * radius);
    }


    @Override
    public String getName() {
        return name;
    }
}
