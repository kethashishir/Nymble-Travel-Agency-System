# Nymble Travel Agency System
Welcome to the Nymble Travel Agency System! This project is a Java-based travel agency management system that allows users to perform various tasks related to travel packages, activity enrollments, and passenger details.

**admin login credentials**
**username : Nymble**
**password: Admin123**


**Project Overview**

The software is designed to provide functionality for a travel agency, including features such as:

- Admin login to access the system.
- Selection of travel packages with details on destinations and activities.
- Run the SQLSheet in mysql workbench
- Viewing travel itineraries.
- Enrollment of customers in various activities.
- Viewing personal details of passengers.
- Getting Started
- To run the application, start with the Splash.java class. This serves as the entry point to the system. The project is structured with multiple classes to handle different aspects of the travel agency functionality.

**Prerequisites**

- Java Development Kit (JDK)
- MySQL Database
- NetBeans or any preferred Java IDE
- Running the Application
- Open the project in your Java IDE.
- Locate the Splash.java file.
- Run the Splash.java file to start the application.

**Project Structure**

The project is organized into several classes, each handling specific functionalities. Below are key classes:

Splash.java: The starting point of the application. Handles the initialization and transition to the main system.

AdminLogin.java: Represents the login screen for admin users. Provides access to the system.

TravelPackageSelection.java: Allows users to select a travel package and view package details.

ItenaryView.java: Displays travel itineraries based on the selected package.

ViewPersonalDetails.java: GUI for viewing personal details of passengers, including their activities.

AvailableActivities.java: Allows users to enroll in available activities by selecting a package, destination, and activity.

CustomerActivityEnrollment.java: Enables customers to enroll in activities, providing options for package, destination, activity, and passenger name.

Conn.java: Handles the connection to the MySQL database.

**Testing**

JUnit testing has been implemented for each class to ensure the reliability and correctness of the implemented functionalities.

**Folder Structure**

src: Contains the source code files.
icons: Holds icons used in the GUI.
test: Includes JUnit test files for testing each class.

**Languages Used**

High-Level Language: Java
Low-Level Language: SQL (used for database interactions)
Feel free to explore the classes and functionalities provided by the Nymble Travel Agency System. If you encounter any issues or have suggestions for improvements, please let us know!
