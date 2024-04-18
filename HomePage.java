import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomePage implements MouseListener{
    JButton flightsButton;
    JFrame frame;
    HomePage() {
        // Create main frame
        frame = new JFrame("Home Page | TraveLIT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

        // Header panel with title and slogan
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.ORANGE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        ImageIcon logoImage = new ImageIcon("Images/logo.png");
        JLabel titleLabel = new JLabel("TraveLIT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setIcon(logoImage);
        titleLabel.setIconTextGap(-20);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel sloganLabel = new JLabel("Book Flights and Hotels only on TraveLIT");
        sloganLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        sloganLabel.setForeground(Color.WHITE);
        headerPanel.add(sloganLabel, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Content panel with welcome message and image
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        ImageIcon imageIcon = new ImageIcon("Images/Travel.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        contentPanel.add(imageLabel, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new GridLayout(2, 1, 0, 10)); // Panel for message labels
        messagePanel.setOpaque(false); // Make panel transparent

        JLabel welcomeLabel = new JLabel("Welcome to TraveLIT");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(welcomeLabel);

        JLabel tagLabel = new JLabel("Be LIT. TraveLIT!");
        tagLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tagLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagePanel.add(tagLabel);

        contentPanel.add(messagePanel, BorderLayout.SOUTH);

        frame.add(contentPanel, BorderLayout.CENTER);

        // Button panel for navigation
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        flightsButton = createStyledButton("Flights", Color.BLUE, Color.BLACK);
        flightsButton.addMouseListener(this);
        JButton hotelsButton = createStyledButton("Hotels", Color.GREEN.darker(), Color.BLACK);
        JButton paymentsButton = createStyledButton("Payments", Color.ORANGE, Color.BLACK);
        JButton cancellationsButton = createStyledButton("Cancellations", Color.yellow, Color.BLACK);

        buttonPanel.add(flightsButton);
        buttonPanel.add(hotelsButton);
        buttonPanel.add(paymentsButton);
        buttonPanel.add(cancellationsButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(150, 50)); // Set preferred button size
        return button;
    }

    public void mouseEntered(MouseEvent e){
        flightsButton.setBackground(Color.ORANGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage();
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}