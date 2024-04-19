import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class OptionPage {
    JFrame frame;
    OptionPage(){
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2,1));

        JLabel label = new JLabel("Are You A New User?");
        optionPanel.add(label);

        JPanel buttonPanel = new JPanel();

        JButton loginButton = new JButton("Yes");
        loginButton.setBounds(10, 80, 80, 35);
        buttonPanel.add(loginButton);

        JButton registerButton = new JButton("No");
        registerButton.setBounds(180, 80, 80, 35);
        buttonPanel.add(registerButton);
        optionPanel.add(buttonPanel);
        optionPanel.setAlignmentX(SwingConstants.CENTER);
        optionPanel.setAlignmentY(SwingConstants.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement login logic here
                new SignUpPage();
                frame.dispose();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement registration logic here
                new LoginPage();
                frame.dispose();
            }
        });

        frame = new JFrame();
        frame.add(optionPanel);
        frame.setVisible(true);
        frame.pack();
    }
    public static void main(String args[]) {
        new OptionPage();
    }
}
