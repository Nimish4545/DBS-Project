-- Table to store information about customers
CREATE TABLE UsersInfo (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(100),
    LastName VARCHAR(100),
    Email VARCHAR(150),
    Password VARCHAR(100),
    Phone VARCHAR(30),
    DateOfBirth DATE,
    Address VARCHAR(255),
    City VARCHAR(200),
    State VARCHAR(200),
    Country VARCHAR(200),
    ZipCode VARCHAR(20)
);

-- Table to store information about airlines
CREATE TABLE AirlinesInfo (
    AirlineID INT PRIMARY KEY AUTO_INCREMENT,
    AirlineName VARCHAR(250),
    AirlineCode VARCHAR(3)
);

-- Table to store information about aircraft
CREATE TABLE AircraftsInfo (
    AircraftID INT PRIMARY KEY AUTO_INCREMENT,
    AircraftType VARCHAR(200),
    Capacity INT
);

-- Table to store information about airports
CREATE TABLE AirportsInfo (
    AirportCode VARCHAR(3) PRIMARY KEY,
    AirportName VARCHAR(200),
    City VARCHAR(150),
    Country VARCHAR(150)
);

-- Table to store information about flight routes
CREATE TABLE RoutesInfo (
    RouteID INT PRIMARY KEY AUTO_INCREMENT,
    DepartureAirportCode VARCHAR(3),
    ArrivalAirportCode VARCHAR(3),
    Duration INT,
    FOREIGN KEY (DepartureAirportCode) REFERENCES AirportsInfo(AirportCode),
    FOREIGN KEY (ArrivalAirportCode) REFERENCES AirportsInfo(AirportCode)
);

-- Table to store information about flights
CREATE TABLE FlightsInfo (
    FlightID INT PRIMARY KEY AUTO_INCREMENT,
    AirlineID INT,
    AircraftID INT,
    FlightNumber VARCHAR(10),
    RouteID INT,
    DepartureTime DATETIME,
    ArrivalTime DATETIME,
    EconomySeats INT,
    BusinessSeats INT,
    PNRnumber VARCHAR(13),
    Price DECIMAL(10, 2),
    Status ENUM('On-Time' , 'Delayed', 'Cancelled', 'Departed', 'Arrived'),
    FOREIGN KEY (AirlineID) REFERENCES AirlinesInfo(AirlineID),
    FOREIGN KEY (AircraftID) REFERENCES AircraftsInfo(AircraftID),
    FOREIGN KEY (RouteID) REFERENCES RoutesInfo(RouteID)
);

-- Table to store information about flight connections
CREATE TABLE FlightLayovers (
    LayoverID INT PRIMARY KEY AUTO_INCREMENT,
    DepartureFlightID INT,
    ArrivalFlightID INT,
    LayoverDuration INT,
    LayoverAirport VARCHAR(3),
    FOREIGN KEY (DepartureFlightID) REFERENCES FlightsInfo(FlightID),
    FOREIGN KEY (ArrivalFlightID) REFERENCES FlightsInfo(FlightID),
    FOREIGN KEY (LayoverAirport) REFERENCES AirportsInfo(AirportCode)
);

-- Table to store information about ticket classes for flights
CREATE TABLE FlightClassesInfo (
    ClassID INT PRIMARY KEY AUTO_INCREMENT,
    ClassName VARCHAR(150)
);

-- Table to store information about baggage details for flights
CREATE TABLE LuggageInfo (
    LuggageID INT PRIMARY KEY AUTO_INCREMENT,
    FlightID INT,
    LuggageAllowance DECIMAL(10, 2), 
    FOREIGN KEY (FlightID) REFERENCES FlightsInfo(FlightID)
);
 
CREATE TABLE FlightPlansInfo (
	PlanId INT PRIMARY KEY AUTO_INCREMENT,
    PlanName VARCHAR(150),
    LuggageID INT,
    FOREIGN KEY (LuggageID) REFERENCES LuggageInfo(LuggageID) 
);

CREATE TABLE PlanClass (
    PlanID INT,
    ClassID INT,
    PRIMARY KEY (PlanID,ClassID),
    Price DECIMAL(10,2),
    FOREIGN KEY (PlanID) REFERENCES FlightPlansInfo(PlanID),
    FOREIGN KEY (ClassID) REFERENCES FlightClassesInfo(ClassID)
);

-- Table to store information about ticket availability for each flight class
CREATE TABLE TicketAvailability (
    AvailabilityID INT PRIMARY KEY AUTO_INCREMENT,
    FlightID INT,
    PlanID INT,
    ClassID INT,
    AvailableSeats INT,
    FOREIGN KEY (FlightID) REFERENCES FlightsInfo(FlightID),
    FOREIGN KEY (PlanID,ClassID) REFERENCES PlanClass(PlanID,ClassID)
);

-- Table to store information about flight cancellations
CREATE TABLE FlightCancellationsInfo (
    CancellationID INT PRIMARY KEY AUTO_INCREMENT,
    FlightID INT,
    CancellationDate DATETIME,
    FOREIGN KEY (FlightID) REFERENCES FlightsInfo(FlightID)
);

-- Table to store information about customer preferences
CREATE TABLE CustomerPreferences (
    PreferenceID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    FlightID INT,
    SeatPreference ENUM('Window', 'Aisle', 'Middle'),
    MealPreference VARCHAR(100),
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (FlightID) REFERENCES FlightsInfo(FlightID)
);

-- Table to store information about hotels
CREATE TABLE HotelsInfo (
    HotelID INT PRIMARY KEY AUTO_INCREMENT,
    HotelName VARCHAR(200),
    City VARCHAR(150),
    Country VARCHAR(150),
    Address VARCHAR(255),
    Ratings DECIMAL(1, 1),
    Price DECIMAL(10, 2),
    AvailableRooms INT
);

-- Table to store information about hotel room images
CREATE TABLE HotelImages (
    ImageID INT PRIMARY KEY AUTO_INCREMENT,
    HotelID INT,
    ImageURL VARCHAR(255),
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID)
);

-- Table to store information about hotel room types
CREATE TABLE RoomTypesInfo (
    RoomTypeID INT PRIMARY KEY AUTO_INCREMENT,
    TypeName VARCHAR(255)
);

-- Table to store information about hotel rooms
CREATE TABLE HotelRoomsInfo (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    HotelID INT,
    RoomNumber VARCHAR(10),
    RoomTypeID INT,
    Occupancy INT,
    RoomPrice DECIMAL(10, 2),
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID),
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypesInfo(RoomTypeID)
);

-- Table to store information about hotel room availability by date range
CREATE TABLE RoomAvailabilityInfo (
    AvailabilityID INT PRIMARY KEY AUTO_INCREMENT,
    HotelID INT,
    RoomID INT,
    StartDate DATE,
    EndDate DATE,
    AvailableRooms INT,
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID),
    FOREIGN KEY (RoomID) REFERENCES HotelRoomsInfo(RoomID)
);

-- Table to store information about customer reviews for hotels
CREATE TABLE HotelReviews (
    ReviewID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    HotelID INT,
    Rating DECIMAL(1, 1),
    Feedback TEXT,
    ReviewDate DATETIME,
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID)
);

-- Table to store information about hotel amenities
CREATE TABLE HotelAmenitiesInfo (
    AmenityID INT PRIMARY KEY AUTO_INCREMENT,
    AmenityName VARCHAR(200)
);

-- Table to store information about hotel amenities associations
CREATE TABLE AmenityHotel (
    HotelID INT,
    AmenityID INT,
    PRIMARY KEY (HotelID, AmenityID),
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID),
    FOREIGN KEY (AmenityID) REFERENCES HotelAmenitiesInfo(AmenityID)
);

-- Table to store information about hotel bookings including room type
CREATE TABLE HotelBookings (
    HotelBookingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    HotelID INT,
    RoomID INT,
    RoomTypeID INT,
    NumOfRooms INT,
    NumOfGuests INT,
    CheckInDate DATE,
    CheckOutDate DATE,
    TotalPrice DECIMAL(10, 2),
    PaymentMethod VARCHAR(50),
    Status ENUM('In-Process', 'Success', 'Failed'),
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (HotelID) REFERENCES HotelsInfo(HotelID),
    FOREIGN KEY (RoomID) REFERENCES HotelRoomsInfo(RoomID),
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypesInfo(RoomTypeID)
);

CREATE TABLE FlightBookings (
	FlightBookingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    FlightID INT,
    AvailabilityID INT,
    TotalPrice Decimal(10,2),
    NumOfSeats INT,
    PaymentMethod VARCHAR(50),
    Status ENUM('In-Process', 'Success', 'Failed'),
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (FlightID) REFERENCES FlightsInfo(FlightID),
    FOREIGN KEY (AvailabilityID) REFERENCES TicketAvailability(AvailabilityID)
);

-- Table to store information about bookings
CREATE TABLE Bookings (
    BookingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    HotelBookingID INT,
    FlightBookingID INT,
    TotalPrice DECIMAL(10, 2),
    PaymentMethod VARCHAR(50),
    PaymentStatus ENUM('In-Process', 'Success', 'Failed'),
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (HotelBookingID) REFERENCES HotelBookings(HotelBookingID),
    FOREIGN KEY (FlightBookingID) REFERENCES FlightBookings(FlightBookingID)
);

-- Table to store information about user cancellations for flight bookings
CREATE TABLE BookingCancellations (
    CancellationID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    BookingID INT,
    CancellationDate DATETIME,
    RefundAmount DECIMAL(10,2),
    FOREIGN KEY (UserID) REFERENCES UsersInfo(UserID),
    FOREIGN KEY (BookingID) REFERENCES Bookings(BookingID)
);

-- Table to store information about customer payments
CREATE TABLE UserPayments (
    PaymentID INT PRIMARY KEY AUTO_INCREMENT,
    BookingID INT,
    PaymentDate DATETIME,
    Amount DECIMAL(10, 2),
    PaymentMethod VARCHAR(100),
    PaymentStatus ENUM('In-Process', 'Success', 'Failed'),
    FOREIGN KEY (BookingID) REFERENCES Bookings(BookingID)
);




