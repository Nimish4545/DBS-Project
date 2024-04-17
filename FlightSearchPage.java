import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchPage {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Flight Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        frame.add(searchPanel, BorderLayout.NORTH);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        frame.add(resultsPanel, BorderLayout.CENTER);

        searchPanel.add(new JLabel("Flight Search"));

        // Dropdown for selecting departure airport
        List<String> departureAirports = fetchAirportNames(); // Fetch departure airport names from the database
        String[] departureAirportNames = departureAirports.toArray(new String[0]);
        JComboBox<String> departureAirportComboBox = new JComboBox<>(departureAirportNames);
        searchPanel.add(new JLabel("Departure Airport:"));
        searchPanel.add(departureAirportComboBox);

        // Dropdown for selecting arrival airport
        List<String> arrivalAirports = fetchAirportNames(); // Fetch arrival airport names from the database
        String[] arrivalAirportNames = arrivalAirports.toArray(new String[0]);
        JComboBox<String> arrivalAirportComboBox = new JComboBox<>(arrivalAirportNames);
        searchPanel.add(new JLabel("Arrival Airport:"));
        searchPanel.add(arrivalAirportComboBox);

        // Dropdown for selecting an airline
        List<String> airlines = fetchAirlineNames(); // Fetch airline names from the database
        String[] airlineNames = airlines.toArray(new String[0]);
        JComboBox<String> airlineComboBox = new JComboBox<>(airlineNames);
        searchPanel.add(new JLabel("Airline:"));
        searchPanel.add(airlineComboBox);

        JButton searchButton = new JButton("Search Flights");
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departureAirport = (String) departureAirportComboBox.getSelectedItem();
                String arrivalAirport = (String) arrivalAirportComboBox.getSelectedItem();
                String selectedAirline = (String) airlineComboBox.getSelectedItem();
                // Implement search logic here, using selectedAirline, departureAirport, and arrivalAirport
                searchFlights(departureAirport, arrivalAirport, selectedAirline, resultsPanel);
            }
        });

        frame.setVisible(true);
    }

    private static List<String> fetchAirportNames() {
        List<String> airports = new ArrayList<>();
        // Example query to fetch airport names
        String query = "SELECT AirportName FROM AirportsInfo";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                airports.add(rs.getString("AirportName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return airports;
    }

    private static List<String> fetchAirlineNames() {
        List<String> airlines = new ArrayList<>();
        // Example query to fetch airline names
        String query = "SELECT AirlineName FROM AirlinesInfo";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                airlines.add(rs.getString("AirlineName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return airlines;
    }

    private static void searchFlights(String departureAirport, String arrivalAirport, String selectedAirline, JPanel resultsPanel) {
        // Implement search logic here
    }
}