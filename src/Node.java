import javax.swing.*;
import java.awt.*;

public class Node extends JComponent {

    private static int radius = 30;
    private double posX;
    private double posY;
    private String name;

    public Node(double posX, double posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.gray);
        g2.fillOval((int) (posX - radius), (int) (posY - radius), 2 * radius, 2 * radius);
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

    @Override
    public String getName() {
        return name;
    }
}
