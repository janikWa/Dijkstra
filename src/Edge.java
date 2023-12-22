import javax.swing.*;
import java.awt.*;

public class Edge extends JComponent {

    private double start_X;
    private double start_Y;
    private double end_X;
    private double end_Y;
    private int weight;
    private double node_angle;
    private Node startnode;
    private Node endnode;

    public Edge(Node startnode, Node endnode, int weight){
        this.startnode = startnode;
        this.endnode = endnode;
        this.weight = weight;
        this.start_X = startnode.getPosX();
        this.start_Y = startnode.getPosY();
        this.end_X = endnode.getPosX();
        this.end_Y = endnode.getPosY();
        this.node_angle = Math.toDegrees(Math.atan(Model.delta(start_Y,end_Y)/Model.delta(start_X,end_X)));
        startnode.addOutGoingEdge(this);
        endnode.addIngoinEdges(this);
    }

    //Getter and Setter
    public int getWeight() {
        return weight;
    }

    public Node getStartnode() {
        return startnode;
    }

    public Node getEndnode() {
        return endnode;
    }

    public double getStart_X() {
        return start_X;
    }

    public double getStart_Y() {
        return start_Y;
    }

    public double getEnd_X() {
        return end_X;
    }

    public double getEnd_Y() {
        return end_Y;
    }


    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawLine((int) start_X, (int) start_Y, (int) end_X, (int) end_Y);;
        Model.drawArrow(g2, startnode, endnode, 30);
    }




}
