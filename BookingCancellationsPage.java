import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BookingCancellationsPage {
    JFrame frame;
    JPanel topPanel, cancellationPanel, rightPanel, buttonPanel;
    JLabel textLabel, cancellationReasonLabel, successLabel, UserNameLabel, paymentCancellationsTextLabel, bookingIdLabel, cancellationDateLabel, refundAmountLabel;
    JTextArea cancellationReasonTextArea;
    JButton cancelButton;
    JButton returnButton;
    JTextArea paymentTextArea;
    ImageIcon image1, userDisplayIcon;
    // Database connection parameters
    String url = "jdbc:mysql://localhost:3306/your_database_name";
    String username = "your_username";
    String password = "your_password";
    Connection connection;

    public BookingCancellationsPage() {
        frame = new JFrame("Booking Cancellations | TraveLIT");

        topPanel = new JPanel();
        UserNameLabel = new JLabel();
        buttonPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.ORANGE);
        image1 = new ImageIcon("Images/logo.png");
        userDisplayIcon = new ImageIcon("Images/username.png");

        textLabel = new JLabel("TraveLIT");
        textLabel.setIcon(image1);
        cancellationPanel = new JPanel();
        cancellationPanel.setLayout(new GridBagLayout());

        paymentCancellationsTextLabel = new JLabel("Booking Cancellations");
        paymentCancellationsTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentCancellationsTextLabel.setFont(new Font("MV Boli", Font.BOLD, 30));

        bookingIdLabel = new JLabel();
        cancellationReasonLabel = new JLabel("Cancellation Reason:");
        cancellationReasonTextArea = new JTextArea(5, 20);
        cancelButton = new JButton("Cancel Booking");
        returnButton = new JButton("Return to Homepage");
        cancelButton.setBackground(Color.CYAN);
        returnButton.setPreferredSize(new Dimension(200, 30));

        // Additional labels for cancellation date and refund amount
        cancellationDateLabel = new JLabel("Cancellation Date: ");
        refundAmountLabel = new JLabel("Refund Amount: ");

        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setHorizontalTextPosition(JLabel.RIGHT);
        textLabel.setIconTextGap(-20);

        UserNameLabel.setIcon(userDisplayIcon);
        UserNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UserNameLabel.setIconTextGap(10);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add booking ID label to the cancellation panel
        cancellationPanel.add(new JLabel("Booking ID:"), gbc);
        gbc.gridx++;
        bookingIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cancellationPanel.add(bookingIdLabel, gbc);

        // Add labels for cancellation date and refund amount
        gbc.gridy++;
        gbc.gridx = 0;
        cancellationPanel.add(cancellationDateLabel, gbc);
        gbc.gridy++;
        cancellationPanel.add(refundAmountLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        cancellationPanel.add(cancellationReasonLabel, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        cancellationPanel.add(cancellationReasonTextArea, gbc);
        gbc.gridy++;
        cancellationPanel.add(cancelButton, gbc);

        cancelButton.setPreferredSize(new Dimension(150, 30));
        cancelButton.setFocusable(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform cancellation
                String bookingId = bookingIdLabel.getText(); // Retrieve booking ID from the label
                String cancellationReason = cancellationReasonTextArea.getText();

                if (connection != null) {
                    cancelPayment(bookingId, cancellationReason);
                    showCancellationMessage();
                    refreshPaymentTextArea();
                }
            }
        });

        // Add mouse listener to change button color
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(Color.GREEN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(Color.CYAN);
            }
        });

        returnButton.setFocusable(false); // Set focusable for return button
        returnButton.setForeground(Color.BLACK);
        returnButton.setBackground(Color.WHITE);

        buttonPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close current FlightBookingPage frame
                frame.dispose();
                // Open new HomePage
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
        topPanel.add(paymentCancellationsTextLabel, BorderLayout.CENTER);
        topPanel.add(UserNameLabel, BorderLayout.EAST);
        topPanel.setBackground(Color.orange);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        // Right panel to display payments
        rightPanel = new JPanel(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        successLabel = new JLabel("Successful Booking Cancellations:");
        successLabel.setHorizontalAlignment(SwingConstants.LEFT);
        successLabel.setFont(new Font(successLabel.getFont().getName(), Font.BOLD, 20));
        paymentTextArea = new JTextArea(10, 30);
        paymentTextArea.setEditable(false);
        paymentTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(paymentTextArea);
        rightPanel.add(successLabel, BorderLayout.NORTH);
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(cancellationPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Populate payment text area initially
        refreshPaymentTextArea();
        connectToDatabase();
        retrieveAndSetUsername(); // Call the method to fetch and set the username
    }

    private void connectToDatabase() {
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(url, username, password);
            // Retrieve booking ID from the database and display it
            if (connection != null) {
                String query = "SELECT booking_id FROM bookings WHERE condition"; // Modify condition as needed
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String bookingId = resultSet.getString("booking_id");
                    bookingIdLabel.setText(bookingId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // No need to display JOptionPane here
        }
    }

    private void cancelPayment(String bookingId, String cancellationReason) {
        try {
            String query = "UPDATE payments SET cancellation_reason = ? WHERE booking_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cancellationReason);
            statement.setString(2, bookingId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to cancel payment.");
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
                    String cancellationReason = resultSet.getString("cancellation_reason");
                    String cancellationDate = resultSet.getString("cancellation_date"); // Retrieve cancellation date from the database
                    String refundAmount = resultSet.getString("refund_amount"); // Retrieve refund amount from the database
                    paymentDetails.append("Booking ID: ").append(bookingId).append(", Amount: ").append(amount).append(", Payment Method: ").append(paymentMethod);
                    if (cancellationReason != null) {
                        paymentDetails.append(", Cancellation Reason: ").append(cancellationReason);
                    }
                    if (cancellationDate != null) {
                        paymentDetails.append("\nCancellation Date: ").append(cancellationDate);
                    }
                    if (refundAmount != null) {
                        paymentDetails.append(", Refund Amount: ").append(refundAmount);
                    }
                    paymentDetails.append("\n");
                }
                paymentTextArea.setText(paymentDetails.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve payment data from the database.");
        }
    }

    private void showCancellationMessage() {
        JOptionPane.showMessageDialog(frame, "Payment cancelled successfully!");
    }
    
    private void retrieveAndSetUsername() {
        try {
            if (connection != null) {
                String query = "SELECT username FROM users WHERE condition"; // Modify condition as needed
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    String username = resultSet.getString("username");
                    UserNameLabel.setText(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BookingCancellationsPage bookingCancellationsPage = new BookingCancellationsPage();
    }
}
