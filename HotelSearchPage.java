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

public class HotelSearchPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Hotel Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        frame.add(searchPanel, BorderLayout.NORTH);

        JPanel resultsPanel = new JPanel();
        resultsPanel.setLayout(new BorderLayout());
        frame.add(resultsPanel, BorderLayout.CENTER);

        searchPanel.add(new JLabel("Hotel Search"));
        searchPanel.add(new JLabel("City:"));

        // Fetch city names from the database and populate the JComboBox
        List<String> cityNames = fetchCityNames();
        JComboBox<String> cityComboBox = new JComboBox<>(cityNames.toArray(new String[0]));
        searchPanel.add(cityComboBox);

        JButton searchButton = new JButton("Search Hotels");
        searchPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = (String) cityComboBox.getSelectedItem();
                searchHotels(city, resultsPanel);
            }
        });

        frame.setVisible(true);
    }

    private static List<String> fetchCityNames() {
        List<String> cityNames = new ArrayList<>();
        String query = "SELECT DISTINCT City FROM HotelsInfo";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                cityNames.add(rs.getString("City"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cityNames;
    }

    private static void searchHotels(String city, JPanel resultsPanel) {
        // Clear previous results
        resultsPanel.removeAll();

        // Example query
        String query = "SELECT HotelName, Address, Ratings, Price FROM HotelsInfo WHERE City = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Create table model and populate it with data from the result set
            String[] columnNames = {"Hotel Name", "Address", "Ratings", "Price"};
            Object[][] data = new Object[rs.getFetchSize()][4];
            int row = 0;
            while (rs.next()) {
                data[row][0] = rs.getString("HotelName");
                data[row][1] = rs.getString("Address");
                data[row][2] = rs.getDouble("Ratings");
                data[row][3] = rs.getDouble("Price");
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