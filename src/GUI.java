import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{

    private JFrame frame;
    private JTextArea posX_Y = new JTextArea();
    private JPanel mainpanel = new JPanel(new BorderLayout());
    private JButton btn = new JButton("Show Nodes & Edges");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Menu");
    private JMenu menuBarNodes = new JMenu("Nodes");
    private JMenu menuBarEdges = new JMenu("Edges");
    private JMenu menuBarCalculate = new JMenu("Calculate");
    private JMenuItem clearAll = new JMenuItem("Clear");
    private JMenuItem info = new JMenuItem("Info");
    private JMenuItem add_node = new JMenuItem("Add Node");
    private JMenuItem remove_node = new JMenuItem("Remove Node");
    private JMenuItem add_edge = new JMenuItem("Add edge");
    private JMenuItem remove_edge = new JMenuItem(("Remove Edge"));
    private JMenuItem calculate = new JMenuItem("Calculate");

    public GUI(){
        frame = new JFrame("Dijkstras Algorithm");
        frame.setLayout(new BorderLayout());
        frame.setSize(1400,1000);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        mainpanel.setBackground(Color.white);
        menu.add(clearAll);
        menu.add(info);
        menuBarNodes.add(add_node);
        menuBarNodes.add(remove_node);
        menuBarEdges.add(add_edge);
        menuBarEdges.add(remove_edge);
        menuBarCalculate.add(calculate);
        menuBar.add(menu);
        menuBar.add(menuBarNodes);
        menuBar.add(menuBarEdges);
        menuBar.add(menuBarCalculate);
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

    public JMenuItem getClearAll() {
        return clearAll;
    }

    public JMenuItem getInfo() {
        return info;
    }

    public JMenuItem getCalculate() {
        return calculate;
    }

    public JMenuItem getRemove_node() {
        return remove_node;
    }

    public JMenuItem getRemove_edge() {
        return remove_edge;
    }

    public void setController(Controller controller){
        btn.addActionListener(controller);
        add_node.addActionListener(controller);
        add_edge.addActionListener(controller);
        remove_node.addActionListener(controller);
        remove_edge.addActionListener(controller);
        calculate.addActionListener(controller);
        info.addActionListener(controller);
        clearAll.addActionListener(controller);
        mainpanel.addMouseListener(controller);
    }
}
