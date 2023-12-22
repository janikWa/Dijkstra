import javax.swing.*;

public class EdgeInputField extends JComponent{
        // Swing-Komponenten erstellen
        private  JComboBox<String> startNodeComboBox = new JComboBox<>();
        private  JComboBox<String> endNodeComboBox = new JComboBox<>();
        private  JTextField weightField = new JTextField(5);
        private  JButton addBtn = new JButton("Add");
        private  Controller controller;

    public JTextField getWeightField() {
        return weightField;
    }

    public JComboBox<String> getStartNodeComboBox() {
        return startNodeComboBox;
    }

    public JComboBox<String> getEndNodeComboBox() {
        return endNodeComboBox;
    }

    public EdgeInputField(Controller c){
            controller = c;
            addBtn.addActionListener(controller);
        }

        public void run(){
            for (int i = 0; i < Model.getNodes().size(); i++) {
                startNodeComboBox.addItem(Model.getNodes().get(i).getName());
                endNodeComboBox.addItem(Model.getNodes().get(i).getName());
            }

            JPanel panel = new JPanel();
            panel.add(new JLabel("Startnode: "));
            panel.add(startNodeComboBox);
            panel.add(new JLabel("Endnode: "));
            panel.add(endNodeComboBox);
            panel.add(new JLabel("Weight: "));
            panel.add(weightField);
            panel.add(addBtn);

            // Dialog anzeigen
            int result = JOptionPane.showConfirmDialog(null, panel, "Eingabe", JOptionPane.OK_CANCEL_OPTION);

            // Werte aus den Comboboxen und dem Textfeld abrufen
            if (result == JOptionPane.OK_OPTION) {
                try {
                    //Model.addEdge(new Edge(Model.getNodes().get(startNodeComboBox.getSelectedIndex()), Model.getNodes().get(endNodeComboBox.getSelectedIndex()), Integer.parseInt(weightField.getText())));

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid Input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        public JButton getAddBtn() {
            return addBtn;
        }
}

