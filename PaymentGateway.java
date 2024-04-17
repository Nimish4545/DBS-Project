import javax.swing.*;
// import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JLabel paymentLabel = new JLabel("Select Payment Option:");
        paymentLabel.setBounds(10, 20, 200, 25);
        panel.add(paymentLabel);

        String[] paymentOptions = {"UPI", "Debit Card", "Credit Card", "Internet Banking"};
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentOptions);
        paymentComboBox.setBounds(200, 20, 150, 25);
        panel.add(paymentComboBox);

        JButton submitButton = new JButton("Pay");
        submitButton.setBounds(150, 100, 100, 25);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Directly use the combo box reference instead of casting the source of the event
                String selectedPaymentOption = (String) paymentComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(null, "You selected: " + selectedPaymentOption);
            }
        });
    }
}