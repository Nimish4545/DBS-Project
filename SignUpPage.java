import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPage {
    JFrame frame;
    SignUpPage(){
        ImageIcon image = new ImageIcon("Images/BigLogo.png");
        
        JLabel title_label = new JLabel();
        title_label.setIcon(image);
        title_label.setIconTextGap(25);
        title_label.setText("TraveLIT");
        title_label.setVerticalTextPosition(JLabel.BOTTOM);
        title_label.setHorizontalTextPosition(JLabel.CENTER);
        Font title_font = new Font("Horizon", Font.BOLD, 55);
        title_label.setFont(title_font);

        JLabel tag_label = new JLabel("Be LIT. TraveLIT!");
        tag_label.setFont(new Font("League Spartan",Font.ITALIC,18));

        JPanel panel1 = new JPanel();
        //panel1.setLayout(new GridLayout(3,1));
        panel1.setBackground(Color.orange);
        panel1.setBounds(0,0,430,700);
        panel1.add(title_label);
        panel1.add(tag_label);

        JLabel head_label = new JLabel("Welcome to TraveLIT");
        head_label.setHorizontalAlignment(JLabel.CENTER);
        head_label.setFont(new Font("Georgia",Font.BOLD,40));

        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(500,50,500,100);
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(Color.white);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Adjust spacing

        JLabel userLabel = new JLabel("First Name");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("Last Name");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("E-mail");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("Date of Birth");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("Address");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("City");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("State");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        userLabel = new JLabel("Country");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy--;

        userLabel = new JLabel("Zip Code");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        userLabel.setBounds(10, 20, 80, 25);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial",Font.PLAIN,20));
        passwordLabel.setBounds(10, 50, 80, 25);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        passwordLabel = new JLabel("Confirm Password");
        passwordLabel.setFont(new Font("Arial",Font.PLAIN,20));
        passwordLabel.setBounds(10, 50, 80, 25);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.add(userLabel);
        inputPanel.add(userText);
        loginPanel.add(inputPanel, gbc);
        gbc.gridy++;

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 35);
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(loginButton, gbc);
        gbc.gridy++;

        JButton registerButton = new JButton("Sign Up");
        registerButton.setBounds(180, 80, 80, 35);
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        loginPanel.add(registerButton, gbc);
        gbc.gridy++;
        loginPanel.setAlignmentX(SwingConstants.CENTER);
        loginPanel.setAlignmentY(SwingConstants.CENTER);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement login logic here
                frame.dispose();
                new LoginPage();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement registration logic here
                JOptionPane.showMessageDialog(registerButton, "You are successfully Signed Up to TraveLIT!", "Sign Up Confirmation", JOptionPane.PLAIN_MESSAGE);
                frame.dispose();
                new LoginPage();
            }
        });

        JPanel helpPanel = new JPanel();
        helpPanel.add(head_label);
        helpPanel.add(loginPanel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setBounds(430,0,570,700);
        panel2.setBackground(Color.white);
        panel2.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        panel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //panel2.add(Box.createVerticalGlue());
        //panel2.add(helpPanel, BorderLayout.CENTER);
        panel2.add(head_label);
        panel2.add(Box.createVerticalGlue());
        panel2.add(loginPanel);
        panel2.add(Box.createVerticalGlue());
        
        frame = new JFrame();
        frame.setLayout(new GridLayout(1,2));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("User Sign Up | TraveLIT");
        frame.setSize(1000,700);
        frame.add(panel1);
        frame.add(panel2);
        //frame.pack();
    }
    public static void main(String args[]){
        new SignUpPage();
    }
}