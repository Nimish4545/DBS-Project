 TraveLIT 

TraveLIT is a comprehensive travel management system designed to streamline the process of searching for flights, hotels, and managing user logins. This project is structured around a SQL database (`TraveLIT.sql`) and includes Java Swing GUIs for user interactions. 


 Project Structure:

- TraveLIT.sql: Contains the SQL schema for the TraveLIT database. This includes tables for storing information about users, flights, hotels, and more. 

- DatabaseConnection.java: Provides a utility class for establishing a connection to the TraveLIT database. 

- HotelSearchPage.java: Implements a Swing GUI for searching hotels. Users can select a city from a dropdown menu and view available hotels in that city. 

- FlightSearchPage.java: Implements a Swing GUI for searching flights. Users can select departure and arrival airports and view available flights. 

- LoginPage.java: Implements a Swing GUI for user login. Users can enter their credentials to log in to the system. 


 Prerequisites:

- Java Development Kit (JDK) installed on system. 

- A SQL database server (e.g., MySQL, PostgreSQL) where the TraveLIT database will be hosted. 

- An Integrated Development Environment (IDE) with Java support, such as IntelliJ IDEA or Eclipse. We used VSCode.


 Setting Up the Database: 

1. Create the Database: Use the SQL commands in `TraveLIT.sql` to create the TraveLIT database on SQL server. 

2. Populate the Database: Populate the database with initial data as needed. 


 Configuring the Database Connection: 

1. Open `DatabaseConnection.java`. 

2. Update the `getConnection()` method with database connection details (URL, username, password). 


 Running the Application:

1. Compile the Java Files: Compile all the Java files in the project. 

2. Run the Login Page: Execute `LoginPage.java` to start the application. The login page will be displayed. 

3. Navigate the Application: From the login page, users can navigate to the flight search and hotel search pages to perform searches. 
