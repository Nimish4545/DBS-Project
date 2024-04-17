import javax.swing.*;
// import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sign Up Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(10, 20, 80, 25);
        panel.add(firstNameLabel);

        JTextField firstNameText = new JTextField(20);
        firstNameText.setBounds(100, 20, 165, 25);
        panel.add(firstNameText);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(10, 50, 80, 25);
        panel.add(lastNameLabel);

        JTextField lastNameText = new JTextField(20);
        lastNameText.setBounds(100, 50, 165, 25);
        panel.add(lastNameText);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 80, 80, 25);
        panel.add(emailLabel);

        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 80, 165, 25);
        panel.add(emailText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 110, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 110, 165, 25);
        panel.add(passwordText);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(10, 140, 80, 25);
        panel.add(phoneLabel);

        JTextField phoneText = new JTextField(20);
        phoneText.setBounds(100, 140, 165, 25);
        panel.add(phoneText);

        JLabel dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(10, 170, 80, 25);
        panel.add(dobLabel);

        JTextField dobText = new JTextField(20);
        dobText.setBounds(100, 170, 165, 25);
        panel.add(dobText);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(10, 200, 80, 25);
        panel.add(addressLabel);

        JTextField addressText = new JTextField(20);
        addressText.setBounds(100, 200, 165, 25);
        panel.add(addressText);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(10, 230, 80, 25);
        panel.add(cityLabel);

        JTextField cityText = new JTextField(20);
        cityText.setBounds(100, 230, 165, 25);
        panel.add(cityText);

        JLabel stateLabel = new JLabel("State");
        stateLabel.setBounds(10, 260, 80, 25);
        panel.add(stateLabel);

        JTextField stateText = new JTextField(20);
        stateText.setBounds(100, 260, 165, 25);
        panel.add(stateText);

        JLabel countryLabel = new JLabel("Country");
        countryLabel.setBounds(10, 290, 80, 25);
        panel.add(countryLabel);

        JTextField countryText = new JTextField(20);
        countryText.setBounds(100, 290, 165, 25);
        panel.add(countryText);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(10, 320, 80, 25);
        panel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement sign-up logic here
                System.out.println("Sign Up button clicked");
            }
        });
    }
}