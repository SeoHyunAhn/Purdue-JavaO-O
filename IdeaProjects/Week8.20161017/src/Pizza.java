import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.*;

public class Pizza extends JFrame implements ActionListener {

    private static final String[] Topping = {"Garlic","Jalapenos","extra cheese","bacon"};
    private static final String[] Size = {"Small", "Medium", "Large"};
    private static final String[] Style = {"Margherita","Prosciutto","Diavola","Verdure","Calzone"};

    private JButton SubmitButton;
    private JCheckBox[] topping;
    private JRadioButton[] size;
    private boolean Selectedtopping[];
    private int Sizeselected;
    private JTextArea output;
    private JScrollPane scrollPane;
    private JComboBox dropDownList;

    public Pizza() {
        this.setTitle("Pizza Shop");
        addElements();
    }

    private void addElements() {
        JPanel buttonPanel, basePanel, subbasePanel, bottomPanel;
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        basePanel = new JPanel(new GridLayout(1, 0));
        buttonPanel = createButtonPanel();
        subbasePanel = new JPanel(new BorderLayout());
        subbasePanel.setBorder(BorderFactory.createTitledBorder("Options"));
        subbasePanel.add(buttonPanel, BorderLayout.NORTH);
        basePanel.add(subbasePanel);
        basePanel.add(TextOutput());
        bottomPanel = new JPanel(new FlowLayout());
        SubmitButton = addButtonToPane("Submit", bottomPanel);
        contentPane.add(basePanel, BorderLayout.NORTH);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        JPanel pizzaSize = Pizzasize();
        buttonPanel.add(pizzaSize);

        JPanel Pizzastyle = PizzaStyle();
        buttonPanel.add(Pizzastyle);

        JPanel Pizzatopping = PizzaTopping();
        buttonPanel.add(Pizzatopping);

        return buttonPanel;
    }
    private JPanel PizzaStyle() {
        JPanel tempPanel = new JPanel(new FlowLayout());
        tempPanel.setBorder(BorderFactory.createTitledBorder("Select your pizza Style"));
        dropDownList = new JComboBox(Style);
        dropDownList.addActionListener(this);
        tempPanel.add(dropDownList);
        return tempPanel;
    }
    private JPanel PizzaTopping() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Select your pizza topping"));
        JPanel topPanel = new JPanel(new FlowLayout());
        topping = new JCheckBox[Topping.length];
        Selectedtopping = new boolean[Topping.length];
        for (int i = 0; i < Topping.length; i++) {
            topping[i] = new JCheckBox();
            topping[i].setSelected(Selectedtopping[i]);
            topPanel.add(topping[i]);
            topPanel.add(new JLabel(Topping[i]));
        }
        panel.add(topPanel);
        return panel;
    }
    private JPanel Pizzasize() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Select your Pizza Size"));
        size = new JRadioButton[Size.length];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < Size.length; i++) {
            size[i] = new JRadioButton(Size[i]);
            group.add(size[i]);
            panel.add(size[i]);
        }
        return panel;
    }

    public JPanel TextOutput() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Output"));
        output = new JTextArea(10, 10);
        scrollPane = new JScrollPane(output);
        panel.add(scrollPane);
        return panel;
    }
    private JButton addButtonToPane(String text, Container pane) {
        JButton button = new JButton(text);
        pane.add(button);
        button.addActionListener(this);
        return button;
    }
    private void handleButtonAction(ActionEvent e) {
        String msg;
        showMessage("Your Custom Pizza\n");
        JButton button = (JButton) e.getSource();
        showMessage("Size: " + Size[Sizeselected] + "\n");
        showMessage("Style: " + Topping[dropDownList.getSelectedIndex()] + "\n");

        if (button == SubmitButton) {
            for (int i = 0; i < Topping.length; i++)
                Selectedtopping[i] = topping[i].isSelected();
            for (int i = 0; i < Size.length; i++)
                if (size[i].isSelected())
                    Sizeselected = i;
            msg = "";
            for (int i = 0; i < Topping.length; i++)
                if (Selectedtopping[i])
                    msg = msg + (Topping[i] + ", ");
            if (msg.length() > 0)
                showMessage("Topping " + msg);
            else
                showMessage("No Topping");
            }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            handleButtonAction(e);
        }
    }

    public void showMessage(String msg) {
        output.append(msg);
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza();
        pizza.pack();
        pizza.setVisible(true);
    }
}
