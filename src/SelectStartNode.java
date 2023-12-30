import javax.swing.*;

public class SelectStartNode extends JComponent {

    private JComboBox<String> comboBox = new JComboBox<String>();
    private Controller controller;
    private Node selected;
    public SelectStartNode(Controller c) {

        this.controller = c;
        setComboBox();
        int result = JOptionPane.showOptionDialog(
                null,
                comboBox,
                "Choose a start node",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                null);

        if (result == JOptionPane.OK_OPTION) {
            for(Node node: Model.getNodes()){
                if(node.getName() == (String) comboBox.getSelectedItem()){
                    selected = node;
                }
            }
        }
    }

    public Node getSelected(){
        return selected;
    }

    public void setComboBox(){
        for (int i = 0; i < Model.getNodes().size(); i++) {
            comboBox.addItem(Model.getNodes().get(i).getName());
        }
    }
}
