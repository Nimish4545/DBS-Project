import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        searchPanel.add(new JLabel("Departure Airport:"));
        JTextField departureAirportField = new JTextField(10);
        searchPanel.add(departureAirportField);

        searchPanel.add(new JLabel("Arrival Airport:"));
        JTextField arrivalAirportField = new JTextField(10);
        searchPanel.add(arrivalAirportField);

        JButton searchButton = new JButton("Search Flights");
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departureAirport = departureAirportField.getText();
                String arrivalAirport = arrivalAirportField.getText();
                searchFlights(departureAirport, arrivalAirport, resultsPanel);
            }
        });

        frame.setVisible(true);
    }

    private static void searchFlights(String departureAirport, String arrivalAirport, JPanel resultsPanel) {
        // Clear previous results
        resultsPanel.removeAll();

        // Example query
        String query = "SELECT FlightNumber, DepartureTime, ArrivalTime FROM Flights WHERE DepartureAirport = ? AND ArrivalAirport = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Create table model and populate it with data from the result set
            String[] columnNames = {"Flight Number", "Departure Time", "Arrival Time"};
            Object[][] data = new Object[rs.getFetchSize()][3];
            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getString("FlightNumber");
                data[row][1] = rs.getTimestamp("DepartureTime");
                data[row][2] = rs.getTimestamp("ArrivalTime");
                row++;
            }

            // Create table and add it to the results panel
            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            resultsPanel.add(scrollPane, BorderLayout.CENTER);
            resultsPanel.revalidate();
            resultsPanel.repaint();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}