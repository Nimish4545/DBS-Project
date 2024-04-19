import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePage {
    private JButton flightsButton;
    private JFrame frame;

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
        flightsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openFlightBookingPage();
            }
        });

        JButton hotelsButton = createStyledButton("Hotels", Color.GREEN.darker(), Color.BLACK);
        hotelsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openHotelBookingPage();
            }

            private void openHotelBookingPage() {
                frame.dispose();
                new HotelBookingPage();
            }
        });

        JButton paymentsButton = createStyledButton("Payments", Color.magenta, Color.BLACK);
        paymentsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPaymentPage();
            }

            private void openPaymentPage() {
                frame.dispose();
                new PaymentGatewayPage(); 
            }
        });

        JButton bookingsButton = createStyledButton("Bookings", Color.ORANGE, Color.BLACK);
        bookingsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openBookingPage();
            }

            private void openBookingPage() {
                frame.dispose();
                new BookingsPage(); 
            }
        });

        JButton cancellationsButton = createStyledButton("Cancellations", Color.YELLOW, Color.BLACK);
        cancellationsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openCancellationPage();
            }

            private void openCancellationPage() {
                frame.dispose();
                new BookingCancellationsPage();
            }
        });

        buttonPanel.add(flightsButton);
        buttonPanel.add(hotelsButton);
        buttonPanel.add(paymentsButton);
        buttonPanel.add(bookingsButton);
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

    private void openFlightBookingPage() {
        frame.dispose(); // Close current frame
        new FlightBookingPage(); // Open FlightBookingPage
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomePage::new);
    }
}
