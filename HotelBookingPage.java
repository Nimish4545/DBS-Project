import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class HotelBookingPage {

    JFrame frame;
    JPanel topPanel, inputPanel, resultPanel;
    JLabel textLabel, HotelBookingsLabel, UserNameLabel, resultLabel, hotelImageLabel, leftPanelImageLabel;
    ImageIcon image1, userDisplayIcon, hotelImage, leftPanelImage;
    JComboBox<String> locationComboBox, checkInDateComboBox, checkOutDateComboBox, roomTypeComboBox;
    JSpinner guestsSpinner;
    JButton searchButton;
    JList<String> hotelList;
    DefaultListModel<String> hotelListModel;

    HotelBookingPage() {
        UserNameLabel = new JLabel();
        HotelBookingsLabel = new JLabel("Hotel Booking");
        textLabel = new JLabel("TraveLIT");
        frame = new JFrame("Hotel Bookings | TraveLIT");
        topPanel = new JPanel();
        inputPanel = new JPanel();
        resultPanel = new JPanel();
        userDisplayIcon = new ImageIcon("Images/username.png");
        image1 = new ImageIcon("Images/logo.png");

        // Load the image for the left panel
        leftPanelImage = new ImageIcon("Images/TraveLITlogo.png");

        topPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        resultPanel.setLayout(new BorderLayout());

        HotelBookingsLabel.setForeground(Color.black);
        HotelBookingsLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        HotelBookingsLabel.setHorizontalAlignment(JLabel.CENTER);

        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setIcon(image1);
        textLabel.setHorizontalTextPosition(JLabel.RIGHT);
        textLabel.setIconTextGap(-20);

        UserNameLabel.setIcon(userDisplayIcon);
        UserNameLabel.setText("Username");
        UserNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UserNameLabel.setIconTextGap(10);

        // Example hotel locations
        String[] locations = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};

        // Add dropdown combo boxes for location, check-in date, check-out date, room type, and number of guests
        locationComboBox = new JComboBox<>(locations);
        String[] dates = {"2024-04-15", "2024-04-16", "2024-04-17"}; // Example dates
        checkInDateComboBox = new JComboBox<>(dates);
        checkOutDateComboBox = new JComboBox<>(dates);
        String[] roomTypes = {"Standard", "Deluxe", "Suite"}; // Example room types
        roomTypeComboBox = new JComboBox<>(roomTypes);

        // Set background color for JComboBoxes
        locationComboBox.setBackground(Color.WHITE);
        checkInDateComboBox.setBackground(Color.WHITE);
        checkOutDateComboBox.setBackground(Color.WHITE);
        roomTypeComboBox.setBackground(Color.WHITE);

        // Set preferred size for JComboBoxes
        locationComboBox.setPreferredSize(new Dimension(200, 30));
        checkInDateComboBox.setPreferredSize(new Dimension(200, 30));
        checkOutDateComboBox.setPreferredSize(new Dimension(200, 30));
        roomTypeComboBox.setPreferredSize(new Dimension(200, 30));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1); // Min: 1, Max: Integer.MAX_VALUE, Step: 1
        guestsSpinner = new JSpinner(spinnerModel);

        // Set preferred size for JSpinner
        guestsSpinner.setPreferredSize(new Dimension(200, 30));

        searchButton = new JButton("Search Available Hotels");

        // Set preferred size for JButton
        searchButton.setPreferredSize(new Dimension(200, 30));

        resultLabel = new JLabel("Hotel Search Results:");
        // Increase the font size of the result label
        resultLabel.setFont(new Font(resultLabel.getFont().getName(), Font.BOLD, 20));

        hotelListModel = new DefaultListModel<>();
        hotelList = new JList<>(hotelListModel); // Using DefaultListModel to dynamically update hotelList

        // Set button focusable and change text color
        searchButton.setFocusable(false);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(Color.CYAN);

        // Add mouse listener to JComboBoxes
        locationComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                locationComboBox.setBackground(Color.LIGHT_GRAY); // Change background color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                locationComboBox.setBackground(Color.WHITE); // Restore default background color when mouse exits
            }
        });

        checkInDateComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkInDateComboBox.setBackground(Color.LIGHT_GRAY); // Change background color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkInDateComboBox.setBackground(Color.WHITE); // Restore default background color when mouse exits
            }
        });

        checkOutDateComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkOutDateComboBox.setBackground(Color.LIGHT_GRAY); // Change background color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                checkOutDateComboBox.setBackground(Color.WHITE); // Restore default background color when mouse exits
            }
        });

        roomTypeComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                roomTypeComboBox.setBackground(Color.LIGHT_GRAY); // Change background color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                roomTypeComboBox.setBackground(Color.WHITE); // Restore default background color when mouse exits
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Align components to the center
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create a panel to hold the left panel image and label
        JPanel leftPanelImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // leftPanelImagePanel.setBackground(Color.WHITE);
        
        // Create a border around the leftPanelImageLabel
        leftPanelImageLabel = new JLabel(leftPanelImage);
        leftPanelImageLabel.setIcon(leftPanelImage);
        // leftPanelImageLabel.setBorder(BorderFactory.createLineBorder(Color.RED)); // Add a border
        leftPanelImagePanel.add(leftPanelImageLabel);
        inputPanel.add(leftPanelImagePanel, gbc);

        gbc.gridy++; // Move to the next row
        inputPanel.add(new JLabel("Hotel Location:"), gbc);
        gbc.gridy++;
        inputPanel.add(locationComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Check-In Date:"), gbc);
        gbc.gridy++;
        inputPanel.add(checkInDateComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Check-Out Date:"), gbc);
        gbc.gridy++;
        inputPanel.add(checkOutDateComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Room Type:"), gbc);
        gbc.gridy++;
        inputPanel.add(roomTypeComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Guests:"), gbc);
        gbc.gridy++;
        inputPanel.add(guestsSpinner, gbc);
        gbc.gridy++;
        inputPanel.add(searchButton, gbc);

        // Increase the size of the input panel
        inputPanel.setPreferredSize(new Dimension(300, inputPanel.getPreferredSize().height));

        // Set preferred size for result panel and disable auto-resizing
        resultPanel.setPreferredSize(new Dimension(300, 400));
        resultPanel.setMaximumSize(new Dimension(300, 400));
        resultPanel.setMinimumSize(new Dimension(300, 400));
        resultPanel.setLayout(new BorderLayout());

        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(hotelList), BorderLayout.CENTER);

        topPanel.add(HotelBookingsLabel, BorderLayout.CENTER);
        topPanel.add(UserNameLabel, BorderLayout.EAST);
        topPanel.add(textLabel, BorderLayout.WEST);
        topPanel.setBackground(Color.orange);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.WEST); // Align inputPanel to the left
        frame.add(resultPanel, BorderLayout.CENTER); // Display resultPanel in the center

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform hotel search based on input fields
                searchHotels();
            }
        });

        // Mouse listener for search button
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                searchButton.setBackground(Color.GREEN); // Change color when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchButton.setBackground(Color.CYAN); // Restore default color when mouse exits
            }
        });
    }

    // Method to search hotels and update the hotel list
    private void searchHotels() {
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password")) {
            // Construct the query
            String query = "SELECT * FROM hotels WHERE location = ? AND check_in_date = ? AND check_out_date = ? AND room_type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, (String) locationComboBox.getSelectedItem());
            preparedStatement.setString(2, (String) checkInDateComboBox.getSelectedItem());
            preparedStatement.setString(3, (String) checkOutDateComboBox.getSelectedItem());
            preparedStatement.setString(4, (String) roomTypeComboBox.getSelectedItem());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear the previous search results
            hotelListModel.clear();

            // Iterate over the result set and add hotels to the list model
            while (resultSet.next()) {
                String hotelInfo = resultSet.getString("hotel_name") + " - " + resultSet.getString("price_per_night");
                hotelListModel.addElement(hotelInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new HotelBookingPage();
    }
}
