import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FlightBookingPage {
    JFrame frame;
    JPanel topPanel, inputPanel, resultPanel, buttonPanel;
    JLabel textLabel, FlightBookingsLabel, UserNameLabel, resultLabel, departureImageLabel;
    ImageIcon image1, userDisplayIcon, departureImage;
    JComboBox<String> departureComboBox, destinationComboBox, travelDateComboBox, flightPlanComboBox, flightFareComboBox;
    JSpinner passengersSpinner;
    JButton searchButton, returnButton;
    JList<String> flightList;
    DefaultListModel<String> flightListModel;

    FlightBookingPage() {
        UserNameLabel = new JLabel("");
        FlightBookingsLabel = new JLabel("Flight Booking");
        textLabel = new JLabel("TraveLIT");
        frame = new JFrame("Flight Bookings | TraveLIT");
        topPanel = new JPanel();
        inputPanel = new JPanel();
        resultPanel = new JPanel();
        buttonPanel = new JPanel(); // Panel for buttons
        userDisplayIcon = new ImageIcon("Images/username.png");
        image1 = new ImageIcon("Images/logo.png");

        // Set layouts for panels
        topPanel.setLayout(new BorderLayout());
        inputPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        resultPanel.setLayout(new BorderLayout());
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Button panel layout

        FlightBookingsLabel.setForeground(Color.black);
        FlightBookingsLabel.setFont(new Font("MV Boli", Font.BOLD, 30));
        FlightBookingsLabel.setHorizontalAlignment(JLabel.CENTER);

        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 40));
        textLabel.setIcon(image1);
        textLabel.setHorizontalTextPosition(JLabel.RIGHT);
        textLabel.setIconTextGap(-20);

        UserNameLabel.setIcon(userDisplayIcon);
        UserNameLabel.setText("Username");
        UserNameLabel.setHorizontalTextPosition(JLabel.LEFT);
        UserNameLabel.setIconTextGap(10);

        // Example departure and destination cities
        String[] cities = {"Bangalore", "New Delhi", "Mumbai", "Chennai", "Kolkata", "Chandigarh", "Hyderabad", "Pune", "Ahmedabad", "Jaipur"};

        // Add dropdown combo boxes for departure, destination, travel date, flight plan, flight fare, and number of passengers
        departureComboBox = new JComboBox<>(cities);
        destinationComboBox = new JComboBox<>(cities);
        String[] dates = {"2024-04-15", "2024-04-16", "2024-04-17"}; // Example dates
        travelDateComboBox = new JComboBox<>(dates);
        String[] plans = {"Economy", "Business"}; // Example flight plans
        flightPlanComboBox = new JComboBox<>(plans);
        String[] fares = {"Low Fare", "Standard Fare", "Full Fare"}; // Example flight fares
        flightFareComboBox = new JComboBox<>(fares);

        // Set background color for JComboBoxes
        departureComboBox.setBackground(Color.WHITE);
        destinationComboBox.setBackground(Color.WHITE);
        travelDateComboBox.setBackground(Color.WHITE);
        flightPlanComboBox.setBackground(Color.WHITE);
        flightFareComboBox.setBackground(Color.WHITE);

        // Set preferred size for JComboBoxes
        departureComboBox.setPreferredSize(new Dimension(200, 30));
        destinationComboBox.setPreferredSize(new Dimension(200, 30));
        travelDateComboBox.setPreferredSize(new Dimension(200, 30));
        flightPlanComboBox.setPreferredSize(new Dimension(200, 30));
        flightFareComboBox.setPreferredSize(new Dimension(200, 30));

        SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); // Min: 0, Max: Integer.MAX_VALUE, Step: 1
        passengersSpinner = new JSpinner(spinnerModel);

        // Set preferred size for JSpinner
        passengersSpinner.setPreferredSize(new Dimension(200, 30));

        searchButton = new JButton("Search Available Flights");
        returnButton = new JButton("Return to Homepage"); // Create return button

        // Set preferred size for JButton
        searchButton.setPreferredSize(new Dimension(200, 30));
        returnButton.setPreferredSize(new Dimension(200, 30)); // Set preferred size for return button

        resultLabel = new JLabel("Flight Search Results:");
        // Increase the font size of the result label
        resultLabel.setFont(new Font(resultLabel.getFont().getName(), Font.BOLD, 20));

        flightListModel = new DefaultListModel<>();
        flightList = new JList<>(flightListModel); // Using DefaultListModel to dynamically update flightList

        // Set button focusable and change text color
        searchButton.setFocusable(false);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(Color.CYAN);

        returnButton.setFocusable(false); // Set focusable for return button
        returnButton.setForeground(Color.BLACK);
        returnButton.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Align components to the center
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create a panel to hold the departure image and label
        JPanel returnPanel = new JPanel();
        returnPanel.add(returnButton);
        inputPanel.add(returnPanel);

        JPanel departureImagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        departureImage = new ImageIcon("Images/TraveLITlogo.png"); // Change this to the path of your image
        departureImageLabel = new JLabel(departureImage);
        // departureImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        departureImagePanel.add(departureImageLabel);
        inputPanel.add(departureImagePanel, gbc);

        gbc.gridy++; // Move to the next row
        inputPanel.add(new JLabel("Departure:"), gbc);
        gbc.gridy++;
        inputPanel.add(departureComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Destination:"), gbc);
        gbc.gridy++;
        inputPanel.add(destinationComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Travel Date:"), gbc);
        gbc.gridy++;
        inputPanel.add(travelDateComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Flight Plan:"), gbc);
        gbc.gridy++;
        inputPanel.add(flightPlanComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Flight Fare:"), gbc);
        gbc.gridy++;
        inputPanel.add(flightFareComboBox, gbc);
        gbc.gridy++;
        inputPanel.add(new JLabel("Passengers:"), gbc);
        gbc.gridy++;
        inputPanel.add(passengersSpinner, gbc);
        gbc.gridy++;
        inputPanel.add(searchButton, gbc);

        // Increase the size of the input panel
        inputPanel.setPreferredSize(new Dimension(300, inputPanel.getPreferredSize().height));

        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(flightList), BorderLayout.CENTER);

        // Add components to button panel
        buttonPanel.add(returnButton); // Add return button to button panel

        topPanel.add(FlightBookingsLabel, BorderLayout.CENTER);
        topPanel.add(UserNameLabel, BorderLayout.EAST);
        topPanel.add(textLabel, BorderLayout.WEST);
        topPanel.setBackground(Color.orange);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.WEST); // Align inputPanel to the left
        frame.add(resultPanel, BorderLayout.CENTER); // Display resultPanel in the center
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add buttonPanel to the bottom

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setVisible(true);

        // Action listener for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform flight search based on input fields
                searchFlights();
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

        // Action listener for return button
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
    }

    // Method to search flights and update the flight list
    private void searchFlights() {
        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "username", "password")) {
            // Construct the query
            String query = "SELECT * FROM flights WHERE departure = ? AND destination = ? AND travel_date = ? AND flight_plan = ? AND flight_fare = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, (String) departureComboBox.getSelectedItem());
            preparedStatement.setString(2, (String) destinationComboBox.getSelectedItem());
            preparedStatement.setString(3, (String) travelDateComboBox.getSelectedItem());
            preparedStatement.setString(4, (String) flightPlanComboBox.getSelectedItem());
            preparedStatement.setString(5, (String) flightFareComboBox.getSelectedItem());

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear the previous search results
            flightListModel.clear();

            // Iterate over the result set and add flights to the list model
            while (resultSet.next()) {
                String flightInfo = resultSet.getString("flight_number") + " - " + resultSet.getString("departure_time") + " - " + resultSet.getString("arrival_time");
                flightListModel.addElement(flightInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FlightBookingPage();
    }
}

