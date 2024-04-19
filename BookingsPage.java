import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookingsPage {
    JFrame frame;
    JPanel topPanel, bookingsPanel, rightPanel, buttonPanel;
    JLabel textLabel, amountLabel, userIdLabel, paymentMethodLabel, successLabel, UserNameLabel, bookingTextLabel; // Added paymentGatewayTextLabel
    JLabel bookingIdValueLabel, amountValueLabel;
    JComboBox<String> paymentMethodComboBox;
    JButton bookButton, returnButton;
    JTextArea paymentTextArea;
    ImageIcon image1, userDisplayIcon;
    JRadioButton flightButton, hotelButton;

    // Database connection parameters
    String url = "jdbc:mysql://localhost:3306/your_database_name";
    String username = "your_username";
    String password = "your_password";
    Connection connection;

    public BookingsPage() {
        frame = new JFrame("Bookings | TraveLIT");

        topPanel = new JPanel();
        UserNameLabel = new JLabel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.ORANGE);
        image1 = new ImageIcon("Images/logo.png");
        userDisplayIcon = new ImageIcon("Images/username.png");

        textLabel = new JLabel("TraveLIT");
        bookingsPanel = new JPanel();
        buttonPanel = new JPanel();
        bookingsPanel.setLayout(new GridBagLayout()); // Change to GridBagLayout for more precise component placement


        flightButton = new JRadioButton("Flight");
        hotelButton = new JRadioButton("Hotel");
        ButtonGroup group = new ButtonGroup();
        group.add(flightButton);
        group.add(hotelButton);
        JPanel radioPanel = new JPanel();
        radioPanel.add(flightButton);
        radioPanel.add(hotelButton);
        paymentMethodLabel = new JLabel("Payment Method:");
        amountLabel = new JLabel("Total Price:");
        userIdLabel = new JLabel("User ID:");
        paymentMethodComboBox = new JComboBox<>(new String[]{"Credit Card", "Debit Card", "PayPal"});
        amountValueLabel = new JLabel("100"); // Example amount
        bookingIdValueLabel = new JLabel("123456"); // Example booking ID
        bookButton = new JButton("Book Now");
        returnButton = new JButton("Return to Homepage");

        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setIcon(image1);
        textLabel.setHorizontalTextPosition(JLabel.RIGHT);
        textLabel.setIconTextGap(-20);

        UserNameLabel.setIcon(userDisplayIcon);
        UserNameLabel.setText("Username");
        UserNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UserNameLabel.setIconTextGap(10);

        bookingTextLabel = new JLabel("Bookings"); // Create and initialize the paymentGatewayTextLabel
        bookingTextLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the text
        bookingTextLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Set font and size

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing

        bookingsPanel.add(radioPanel, gbc);
        gbc.gridy++;
        bookingsPanel.add(userIdLabel, gbc);
        gbc.gridy++;
        bookingsPanel.add(amountLabel, gbc);
        gbc.gridy++;
        bookingsPanel.add(paymentMethodLabel, gbc);
        gbc.gridy++;
        bookingsPanel.add(paymentMethodComboBox, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        bookingsPanel.add(bookButton, gbc);

        bookButton.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the button
        bookButton.setFocusable(false); // Set button focusable
        paymentMethodComboBox.setPreferredSize(new Dimension(150, 30)); // Set preferred size for the combo box
        
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open payment gateway in JOptionPane
                //openPaymentGateway();
            }
        });

        returnButton.setPreferredSize(new Dimension(200, 30));
        returnButton.setFocusable(false); // Set focusable for return button
        returnButton.setForeground(Color.BLACK);
        returnButton.setBackground(Color.WHITE);
        buttonPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomePage();
            }
        });

        returnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                returnButton.setBackground(Color.LIGHT_GRAY);
            }
        });

        topPanel.add(textLabel, BorderLayout.WEST);
        topPanel.add(bookingTextLabel, BorderLayout.CENTER); // Add paymentGatewayTextLabel to the center of topPanel
        topPanel.add(UserNameLabel, BorderLayout.EAST);
        topPanel.setBackground(Color.orange);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Right panel to display payments
        rightPanel = new JPanel(new BorderLayout());
        successLabel = new JLabel("Successful Bookings:");
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
        frame.add(bookingsPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER); // Added rightPanel to the CENTER region
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate payment text area initially
        refreshBookingTextArea();
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

    private void addBookingToDatabase(String bookingId, String amount, String paymentMethod) {
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

    private void refreshBookingTextArea() {
        try {
            if (connection != null) {
                StringBuilder bookingDetails = new StringBuilder();
                String query = "SELECT * FROM payments";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String bookingId = resultSet.getString("booking_id");
                    String amount = resultSet.getString("amount");
                    String paymentMethod = resultSet.getString("payment_method");
                    bookingDetails.append("Booking ID: ").append(bookingId).append(", Amount: ").append(amount).append(", Payment Method: ").append(paymentMethod).append("\n");
                }
                paymentTextArea.setText(bookingDetails.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve booking data from the database.");
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
        bookButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change color when mouse enters
                bookButton.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore default color when mouse exits
                bookButton.setBackground(UIManager.getColor("Button.background"));
            }
        });
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(frame, "Payment successful!");
    }

    public static void main(String[] args) {
        new BookingsPage();
    }
}
