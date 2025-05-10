import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryGUI extends JFrame {

    private Inventory inventory;
    private JTextField idField, nameField, quantityField, priceField;
    private JTextField removeIdField;
    private JTextField updateIdField, newQuantityField;
    private JTextField findIdField;
    private JTextArea outputArea;

    public InventoryGUI() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        inventory = new Inventory();

        setTitle("Inventory Management System");
        setIconImage(new ImageIcon("src/product-management.png").getImage());
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Product Panel
        JPanel addPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(5,2,5,5));

        idField = new JTextField();
        nameField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();

        inputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Add Product"),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                )
        );

        inputPanel.add(new JLabel("Product ID:"));
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);

        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);


        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> addProduct(e));
        inputPanel.add(addButton);
        inputPanel.add(new JLabel());

        addPanel.add(inputPanel, BorderLayout.NORTH);

        //Remove Product Panel
        JPanel removePanel = new JPanel(new BorderLayout());
        JPanel removeInputPanel = new JPanel(new GridLayout(2,2,5,5));

        removeInputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Remove Product"),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                )
        );

        removeInputPanel.add(new JLabel("Product ID:"));
        removeIdField = new JTextField();
        removeInputPanel.add(removeIdField);

        JButton removeButton = new JButton("Remove Product");
        removeButton.addActionListener(e -> removeProduct(e));
        removeInputPanel.add(removeButton);
        removeInputPanel.add(new JLabel());

        removePanel.add(removeInputPanel, BorderLayout.NORTH);

        // Update Stock Panel
        JPanel updatePanel = new JPanel(new BorderLayout());
        JPanel updateInputPanel = new JPanel(new GridLayout(2,2,5,5));

        updateInputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Update Stock"),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                )
        );

        updateInputPanel.add(new JLabel("Product ID:"));
        updateIdField = new JTextField();
        updateInputPanel.add(updateIdField);

        updateInputPanel.add(new JLabel("New Quantity:"));
        newQuantityField = new JTextField();
        updateInputPanel.add(newQuantityField);

        JButton updateButton = new JButton("Update Stock");
        updateButton.addActionListener(e -> updateStock(e));

        JPanel updateButtonPanel = new JPanel();
        updateButtonPanel.add(updateButton);

        updatePanel.add(updateInputPanel, BorderLayout.NORTH);
        updatePanel.add(updateButtonPanel, BorderLayout.SOUTH);

        // Find Product Panel
        JPanel findPanel = new JPanel(new BorderLayout());
        JPanel findInputPanel = new JPanel(new GridLayout(2,2,5,5));

        findInputPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Find Product"),
                        BorderFactory.createEmptyBorder(10,10,10,10)
                )
        );

        findInputPanel.add(new JLabel("Product ID:"));
        findIdField = new JTextField();
        findInputPanel.add(findIdField);

        JButton findButton = new JButton("Find Product");
        findButton.addActionListener(e -> findProduct(e));
        findInputPanel.add(findButton);
        findInputPanel.add(new JLabel());

        findPanel.add(findInputPanel, BorderLayout.NORTH);

        // List Products Panel
        JPanel listPanel = new JPanel(new BorderLayout());
        JTable productTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        JButton refreshButton = new JButton("Refresh Product List");
        refreshButton.addActionListener(e -> {

            productTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            productTable.setRowHeight(25);
            productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
            productTable.setGridColor(Color.LIGHT_GRAY);
            productTable.setShowGrid(true);


            String[] columnNames = {"ID", "Name", "Quantity", "Price"};
            java.util.List<Product> products = inventory.getAllProducts();
            Object[][] data = new Object[products.size()][4];

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                data[i][0] = p.getId();
                data[i][1] = p.getName();
                data[i][2] = p.getQuantity();
                data[i][3] = String.format("%.2f", p.getPrice());
            }

            productTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        });

        listPanel.add(tableScrollPane, BorderLayout.CENTER);
        listPanel.add(refreshButton, BorderLayout.SOUTH);


        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);

        removeButton.setBackground(new Color(244, 67, 54));
        removeButton.setForeground(Color.WHITE);

        updateButton.setBackground(new Color(33, 150, 243));
        updateButton.setForeground(Color.WHITE);

        findButton.setBackground(new Color(255, 193, 7));
        findButton.setForeground(Color.BLACK);

        // Add the panel to tabs
        tabbedPane.addTab("Add Product", addPanel);
        tabbedPane.addTab("Remove Product", removePanel);
        tabbedPane.addTab("Update Stock", updatePanel);
        tabbedPane.addTab("Find Product", findPanel);
        tabbedPane.addTab("List Products", listPanel);
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Output Area (bottom of the frame)
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setPreferredSize(new Dimension(600, 150));

        add(tabbedPane, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setVisible(true);


    }

    private void addProduct(ActionEvent e) {
        String id = idField.getText().trim();
        String name = nameField.getText();
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            price = Double.parseDouble(priceField.getText());
            Product product = new Product(id, name, quantity, price);
            boolean added = inventory.addProduct(product);

            if (added) {
                outputArea.append("[INFO] Product added: " + product + "\n");
            }
            else {
                outputArea.append("[WARN] Product with same ID already exists: " + id + "\n");
            }

            // Clear input fields
            idField.setText("");
            nameField.setText("");
            quantityField.setText("");
            priceField.setText("");
        }
        catch (NumberFormatException ex) {
            outputArea.append("[ERROR] Quantity and price must be numeric.\n");
        }
        catch (IllegalArgumentException ex) {
            outputArea.append("[ERROR] " + ex.getMessage() + "\n");
        }
    }

    private void removeProduct(ActionEvent e) {
        String id = removeIdField.getText().trim();
        boolean removed = inventory.removeProduct(id);
        if (removed) {
            outputArea.append("[INFO] Product removed: " + id + "\n");
        }
        else {
            outputArea.append("[INFO] No product found with ID: " + id + "\n");
        }
        removeIdField.setText("");
    }

    private void updateStock(ActionEvent e) {
        String id = updateIdField.getText().trim();
        int newQuantity;

        try {
            newQuantity = Integer.parseInt(newQuantityField.getText());
            boolean updated = inventory.updateStock(id, newQuantity);

            if (updated) {
                outputArea.append("[INFO] Stock updated for product: " + id + "\n");
            }
            else {
                outputArea.append("[INFO] No product found to update with ID: " + id + "\n");
            }

            // Clear input fields
            updateIdField.setText("");
            newQuantityField.setText("");

        }
        catch (NumberFormatException ex) {
            outputArea.append("[ERROR] Quantity must be a valid number.\n");
        }
        catch (IllegalArgumentException ex) {
            outputArea.append("[ERROR]" + ex.getMessage() + "\n");
        }
    }

    private void findProduct(ActionEvent e) {
        String id = findIdField.getText().trim();
        Product found = inventory.findProduct(id);

        if (found != null) {
            outputArea.append("[FOUND] " + found + "\n");
        }
        else {
            outputArea.append("[INFO] No product found with ID: " + id + "\n");
        }

        findIdField.setText("");
    }
}