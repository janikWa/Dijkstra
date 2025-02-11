import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements ActionListener, MouseListener {

    private GUI gui;
    private EdgeInputField edgeInputField;
    private RemoveField removeField;
    private SelectStartNode selectStartNode;

    public void setGui(GUI gui){
        this.gui = gui;
    }

    public void setEdgeInputField(EdgeInputField edgeInputField) {
        this.edgeInputField = edgeInputField;
    }

    public Point getMouseLoc(){
        return  MouseInfo.getPointerInfo().getLocation();
    }

    public SelectStartNode getSelectStartNode() {
        return selectStartNode;
    }

    // Action Performed Methods
    @Override
    public void actionPerformed(ActionEvent e) {

        //Click Button
        if(e.getSource() == gui.getButton()){
            Model.displayNodesEdges();

        //Add Nodes
        }else if(e.getSource() == gui.get_add_node()){
            gui.getFrame().revalidate();
            Model.setAddNodesMode(true);

        //Add Edges
        } else if (e.getSource() == gui.get_add_edge()){
            Model.setAddNodesMode(false);
            this.edgeInputField = new EdgeInputField(this);
            edgeInputField.run();

        //Remove Node
        }else if(e.getSource()==gui.getRemove_node()){
            this.removeField = new RemoveField(this);
            removeField.run();

        //remove edge
        }else if(e.getSource() == gui.getRemove_edge()){
            System.out.println("remove edge");
        //Clear
        }else if(e.getSource() == gui.getClearAll()){
            Model.clearAll();

        //calculate
        }else if(e.getSource() == gui.getCalculate()){
            this.selectStartNode = new SelectStartNode(this);
            Model.dijkstra(selectStartNode.getSelected());
            Model.displayPath();

        }else if(removeField!= null && e.getSource()==removeField.getRemoveBtn()){
            try{
                Model.removeNode(Model.getNodes().get(removeField.getRemoveNodeCombobox().getSelectedIndex()));
                removeField.updateComboBox(); // Update the JComboBox
                removeField.revalidate();
            }catch (IndexOutOfBoundsException empty){
                JOptionPane.showMessageDialog(gui.getMainpanel(), "Nothing to remove", "Invalid input", JOptionPane.ERROR_MESSAGE);
            }


        //Info - Dijkstra Explaination
        }else if(e.getSource() == gui.getInfo()){

            JOptionPane.showMessageDialog(
                    gui.getMainpanel(),
                    "Dijkstra's Algorithm finds the shortest path in a graph from a source node to all other nodes.",
                    "Dijkstra Algorithm Explanation",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }else if(e.getSource() == gui.getClearAll()){
            System.out.println("CLEAR");
        }
        //Add Edge
        else if(e.getSource() == edgeInputField.getAddBtn()){
            try{
                Model.addEdge(new Edge(Model.getNodes().get(edgeInputField.getStartNodeComboBox().getSelectedIndex()), Model.getNodes().get(edgeInputField.getEndNodeComboBox().getSelectedIndex()), Integer.parseInt(edgeInputField.getWeightField().getText())));
                Model.drawEdge();
            }catch (NumberFormatException noInt){
                JOptionPane.showMessageDialog(gui.getMainpanel(), "Please enter a number", "Invalid input", JOptionPane.ERROR_MESSAGE);
                gui.getFrame().revalidate();
            }catch (IndexOutOfBoundsException noNodes){
                JOptionPane.showMessageDialog(gui.getMainpanel(), "Please add nodes", "Invalid input", JOptionPane.ERROR_MESSAGE);
                gui.getFrame().revalidate();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent klicked) {
        if(klicked.getSource() == gui.getMainpanel() && Model.getAddNodesMode()){
            //Model.newNode(new Point((int) klicked.getX(), (int) klicked.getY()), JOptionPane.showInputDialog(gui.getMainpanel(), "Enter the name of the Node"));
            if(Model.checkDouble(Model.newNode(new Point((int) klicked.getX(), (int) klicked.getY()), JOptionPane.showInputDialog(gui.getMainpanel(), "Enter the name of the Node"))) == false){
                for (int i = 0; i < Model.getNodes().size(); i++) {
                    System.out.println(Model.getNodes().get(i).getName());
                }
            }else{
                Model.drawNode();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
