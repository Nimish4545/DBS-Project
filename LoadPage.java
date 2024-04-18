import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class LoadPage {
    private JFrame frame;
    private JProgressBar progressBar;

    LoadPage() {
        frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE);

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

        // Progress bar panel
        JPanel progressBarPanel = new JPanel(new BorderLayout());
        progressBarPanel.setBackground(Color.WHITE);
        progressBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Show progress percentage
        progressBar.setForeground(Color.GREEN);
        progressBarPanel.add(progressBar, BorderLayout.CENTER);

        frame.add(progressBarPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);

        // Simulate loading
        simulateLoading();
    }

    private void simulateLoading() {
        // Use a separate thread to update the progress bar
        Thread thread = new Thread(() -> {
            for (int i = 0; i <= 100; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(50); // Simulate loading delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int progress = i;
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(progress); // Update progress bar value
                });
            }
            // After completion, perform further actions (e.g., navigate to next screen)
            SwingUtilities.invokeLater(() -> {
                new OptionPage();
                frame.dispose(); // Close the loading page frame
                // Implement next steps after loading completion (e.g., open main application window)
                // For demonstration, you can replace the frame.dispose() with appropriate actions.
            });
        });
        thread.start(); // Start the thread for loading simulation
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoadPage::new);
    }
}
