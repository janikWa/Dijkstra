import javax.swing.*;

public class RemoveField extends JComponent {

    private JComboBox<String> NodeComboBox = new JComboBox<>();
    private JButton removeBtn = new JButton("Remove");
    private Controller controller;

    public RemoveField(Controller c) {
        controller = c;
        removeBtn.addActionListener(controller);
    }

    public JComboBox<String> getRemoveNodeCombobox() {
        return NodeComboBox;
    }

    public JButton getRemoveBtn() {
        return removeBtn;
    }

    public void setCombobox() {
        NodeComboBox.removeAllItems(); // Clear existing items
        for (int i = 0; i < Model.getNodes().size(); i++) {
            NodeComboBox.addItem(Model.getNodes().get(i).getName());
        }
    }

    public void updateComboBox() {
        setCombobox();
    }

    public void run() {
        setCombobox();
        JPanel panel = new JPanel();
        panel.add(new JLabel("Startnode: "));
        panel.add(NodeComboBox);
        panel.add(removeBtn);

        // Dialog anzeigen
        int result = JOptionPane.showConfirmDialog(null, panel, "Eingabe", JOptionPane.OK_CANCEL_OPTION);

        // Werte aus den Comboboxen und dem Textfeld abrufen
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Model.addEdge(new Edge(Model.getNodes().get(startNodeComboBox.getSelectedIndex()),
                // Model.getNodes().get(endNodeComboBox.getSelectedIndex()),
                // Integer.parseInt(weightField.getText())));

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
