import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;

public class PaymentGateway {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Payment Gateway");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Select Booking:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        String[] bookings = {"Booking 1", "Booking 2", "Booking 3"}; // Populate dynamically
        JComboBox<String> bookingBox = new JComboBox<>(bookings);
        bookingBox.setBounds(100, 20, 165, 25);
        panel.add(bookingBox);

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setBounds(10, 50, 100, 25);
        panel.add(paymentLabel);

        String[] paymentMethods = {"Debit Card", "Credit Card", "Internet Banking", "UPI"};
        JComboBox<String> paymentMethodBox = new JComboBox<>(paymentMethods);
        paymentMethodBox.setBounds(100, 50, 165, 25);
        panel.add(paymentMethodBox);

        JLabel owedAmountLabel = new JLabel("Owed Amount: $0.00");
        owedAmountLabel.setBounds(10, 80, 165, 25);
        panel.add(owedAmountLabel);

        JButton submitButton = new JButton("Submit Payment");
        submitButton.setBounds(10, 110, 165, 25);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitPayment(bookingBox.getSelectedItem().toString(), paymentMethodBox.getSelectedItem().toString(), owedAmountLabel.getText().split(" ")[2]);
            }
        });
        panel.add(submitButton);
    }

    private static void submitPayment(String bookingID, String paymentMethod, String paymentAmount) {
        // Connect to the database and insert the payment
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
            String query = "INSERT INTO UserPayments (BookingID, Amount, PaymentDate, PaymentMethod, PaymentStatus) VALUES (?, ?, NOW(), ?, 'Success')";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, bookingID);
            pstmt.setDouble(2, Double.parseDouble(paymentAmount));
            pstmt.setString(3, paymentMethod);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Payment submitted successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error submitting payment: " + ex.getMessage());
        }
    }
}

private static void updateOwedAmount(String bookingID) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
        String query = "SELECT TotalPrice FROM Bookings WHERE BookingID = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, bookingID);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            double owedAmount = rs.getDouble("TotalPrice");
            JLabel owedAmountLabel = (JLabel) panel.getComponent(3); // Assuming the label is the 4th component added to the panel
            owedAmountLabel.setText("Owed Amount: $" + owedAmount);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error fetching owed amount: " + ex.getMessage());
    }
}