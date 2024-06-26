# MCDA5550 - Hotel Reservation Android App

Welcome to the Hotel Reservation Android App. This project serves as the final submission for the MCDA5550 course at Saint Mary's University.

## Overview

This Android application follows the MVVM (Model-View-ViewModel) architecture and comprises one activity and four fragments. Here's a brief overview of each component:

1. **Main Activity:** 
   - This activity contains a frame layout that dynamically loads each of the following fragments.

2. **Hotel Search Fragment:** 
   - Allows users to input check-in date, check-out date, number of guests, and the user's name.
   - ![Hotel Search Fragment](HotelSearchFragment.png)

3. **Hotel List Fragment:** 
   - Fetches hotel listings from the backend API and displays them using a recycler view.
   - Each item in the list is clickable, directing users to the next fragment upon selection.
   - ![Hotel List Fragment](HotelListFragment.png)

4. **Reservation Details Fragment:** 
   - Enables users to enter details for each guest, including name, email, gender, and age, and confirm the reservation.
   - Clicking the "Confirm Reservation" button leads to the next fragment.
   - ![Reservation Details Fragment](ReservationDetailsFragment.png)

5. **Reservation Success Fragment:** 
   - Displays the confirmation number received from the backend upon successful reservation.
   - Users can navigate back to the first fragment by clicking the back button.
   - ![Reservation Success Fragment](ReservationSuccessFragment.png)

## Features

The Android app offers the following features:

1. **MVVM Architecture:** 
   - Implements Models, Views (activities, fragments, etc.), and ViewModel for a structured and maintainable codebase.

2. **API Connectivity:**
   - Connects to the backend API through ApiClient, which is accessed via HotelReservationApiService, an interface.

3. **Field Validations:**
   - Includes validations for all fields such as email, date (not in the past), check-in, and check-out dates.

4. **Error Handling:**
   - Displays error messages for validations using Toast Messages, enhancing user experience and guiding them through the input process.

5. **Data Passing:**
   - Utilizes Bundle to pass data between fragments, ensuring seamless navigation and interaction within the app.

## Installation and Running the App

To install and run the app, follow these steps:

1. Clone the Git repository by running the following command in your terminal or command prompt:
   ```
   git clone https://github.com/A00431008/MCDA5550-HotelReservationApp
   ```
   
2. Open the project in Android Studio.

3. Configure your ip address in the base URL in `API/ApiClient.java` and `res/xml/network_security_config.xml`

4. Build and run the project on an Android emulator or physical device.
