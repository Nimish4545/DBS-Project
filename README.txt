 TraveLIT 

TraveLIT is a comprehensive travel management system designed to streamline the process of searching for flights, hotels, It manages user logins, bookings, payments and cancellations. This project is structured around a SQL database (`TraveLIT.sql`) and includes Java Swing GUIs for user interactions. 

 Project Structure:

- TraveLIT.sql: Contains the SQL schema for the TraveLIT database. This includes tables for storing information about users, flights, hotels, and more. 

- LoadPage.java: 

- OptionPage.java

- LoginPage.java:

- SignUpPage.java:

- HomePage.java:

- FlightBookingPage.java:

- HotelBookingPage.java:

- BookingsPage.java

- PaymentGatewayPage.java:

- BookingCancellationsPage.java:

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

2. Run the Load Page: Execute `LoadPage.java` to start the application. The loading page will be displayed. 

3. Choose from Option Page : Select whether you are a New User or not. It will navigate you to the right place based on your selection.

4. Login using your credentials : Once you have logged in on LoginPage.java, the home page is opened.

5. Navigate through the Application: From the home page, users can now book flights and hotels and also confirm bookings, payments and cancellations. 
