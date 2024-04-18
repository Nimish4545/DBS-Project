import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PaymentGatewayPage {

    JFrame frame;
    JPanel topPanel, paymentPanel, rightPanel;
    JLabel textLabel, amountLabel, bookingIdLabel, paymentMethodLabel, successLabel, UserNameLabel, paymentGatewayTextLabel; // Added paymentGatewayTextLabel
    JLabel bookingIdValueLabel, amountValueLabel;
    JComboBox<String> paymentMethodComboBox;
    JButton payButton;
    JTextArea paymentTextArea;
    ImageIcon image1, userDisplayIcon;

    // Database connection parameters
    String url = "jdbc:mysql://localhost:3306/your_database_name";
    String username = "your_username";
    String password = "your_password";
    Connection connection;

    public PaymentGatewayPage() {
        frame = new JFrame("Payment Gateway | TraveLIT");

        topPanel = new JPanel();
        UserNameLabel = new JLabel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.ORANGE);
        image1 = new ImageIcon("Images/logo.png");
        userDisplayIcon = new ImageIcon("Images/username.png");

        textLabel = new JLabel("TraveLIT");
        paymentPanel = new JPanel();
        paymentPanel.setLayout(new GridBagLayout()); // Change to GridBagLayout for more precise component placement

        paymentMethodLabel = new JLabel("Payment Method:");
        amountLabel = new JLabel("Amount:");
        bookingIdLabel = new JLabel("Booking ID:");
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal"});
        amountValueLabel = new JLabel("100"); // Example amount
        bookingIdValueLabel = new JLabel("123456"); // Example booking ID
        payButton = new JButton("Proceed to Pay");

        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setIcon(image1);
        textLabel.setHorizontalTextPosition(JLabel.RIGHT);
        textLabel.setIconTextGap(-20);

        UserNameLabel.setIcon(userDisplayIcon);
        UserNameLabel.setText("Username");
        UserNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UserNameLabel.setIconTextGap(10);

        paymentGatewayTextLabel = new JLabel("Payment Gateway"); // Create and initialize the paymentGatewayTextLabel
        paymentGatewayTextLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        paymentGatewayTextLabel.setFont(new Font("MV Boli", Font.BOLD, 30)); // Set font and size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing

        paymentPanel.add(bookingIdLabel, gbc);
        gbc.gridy++;
        paymentPanel.add(amountLabel, gbc);
        gbc.gridy++;
        paymentPanel.add(paymentMethodLabel, gbc);
        gbc.gridy++;
        paymentPanel.add(paymentMethodComboBox, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        paymentPanel.add(payButton, gbc);

        payButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the button
        payButton.setFocusable(false); // Set button focusable
        paymentMethodComboBox.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the combo box

        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open payment gateway in JOptionPane
                openPaymentGateway();
            }
        });

        topPanel.add(textLabel, BorderLayout.WEST);
        topPanel.add(paymentGatewayTextLabel, BorderLayout.CENTER); // Add paymentGatewayTextLabel to the center of topPanel
        topPanel.add(UserNameLabel, BorderLayout.EAST);
        topPanel.setBackground(Color.orange);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Right panel to display payments
        rightPanel = new JPanel(new BorderLayout());
        successLabel = new JLabel("Successful Payments:");
        successLabel.setHorizontalAlignment(SwingConstants.LEFT);
        successLabel.setFont(new Font(successLabel.getFont().getName(), Font.BOLD, 20)); // Increase font size
        paymentTextArea = new JTextArea(10, 30);
        paymentTextArea.setEditable(false);
        paymentTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Change font
        JScrollPane scrollPane = new JScrollPane(paymentTextArea);
        rightPanel.add(successLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(paymentPanel, BorderLayout.WEST); // Adjusted to add paymentPanel to the WEST region
        frame.add(rightPanel, BorderLayout.CENTER); // Added rightPanel to the CENTER region
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate payment text area initially
        refreshPaymentTextArea();
        addComboBoxMouseListener();
        addButtonMouseListener();
        connectToDatabase(); // Connect to database after UI initialization
    }

    private void connectToDatabase() {
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // No need to display JOptionPane here
        }
    }

    private void openPaymentGateway() {
        // Create and show a JOptionPane to simulate a payment gateway
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Card Number:"));
        JTextField cardNumberField = new JTextField();
        panel.add(cardNumberField);
        panel.add(new JLabel("Expiry Date:"));
        JTextField expiryDateField = new JTextField();
        panel.add(expiryDateField);
        panel.add(new JLabel("CVV:"));
        JTextField cvvField = new JTextField();
        panel.add(cvvField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Payment Gateway", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Proceed with payment if OK is clicked
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();
            String cvv = cvvField.getText();

            // Add payment to the database
            String amount = amountValueLabel.getText();
            String bookingId = bookingIdValueLabel.getText();
            String paymentMethod = (String) paymentMethodComboBox.getSelectedItem();

            if (connection != null) {
                addPaymentToDatabase(bookingId, amount, paymentMethod);
                showSuccessMessage();
                // Refresh payment text area
                refreshPaymentTextArea();
            }
        }
    }

    private void addPaymentToDatabase(String bookingId, String amount, String paymentMethod) {
        try {
            String query = "INSERT INTO payments (booking_id, amount, payment_method) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookingId);
            statement.setString(2, amount);
            statement.setString(3, paymentMethod);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to add payment to the database.");
        }
    }

    private void refreshPaymentTextArea() {
        try {
            if (connection != null) {
                StringBuilder paymentDetails = new StringBuilder();
                String query = "SELECT * FROM payments";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String bookingId = resultSet.getString("booking_id");
                    String amount = resultSet.getString("amount");
                    String paymentMethod = resultSet.getString("payment_method");
                    paymentDetails.append("Booking ID: ").append(bookingId).append(", Amount: ").append(amount).append(", Payment Method: ").append(paymentMethod).append("\n");
                }
                paymentTextArea.setText(paymentDetails.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve payment data from the database.");
        }
    }

    private void addComboBoxMouseListener() {
        paymentMethodComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change background color when mouse enters
                paymentMethodComboBox.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore default background color when mouse exits
                paymentMethodComboBox.setBackground(Color.WHITE);
            }
        });
    }

    private void addButtonMouseListener() {
        payButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change color when mouse enters
                payButton.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore default color when mouse exits
                payButton.setBackground(UIManager.getColor("Button.background"));
            }
        });
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(frame, "Payment successful!");
    }

    public static void main(String[] args) {
        PaymentGatewayPage paymentGatewayPage = new PaymentGatewayPage();
    }
}
