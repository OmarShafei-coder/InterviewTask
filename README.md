**Android Location Tracking App**

**Overview:**

This Android app demonstrates a simple location tracking system using background services, broadcast receivers, and the OpenStreetMap (OSM) library. The app allows users to start a background service that periodically sends random location updates, and a map fragment that displays these updates in real-time.

**Key Features:**

1. **Background Service:**
   - Implement a background service triggered by a button press.
   - The service sends periodic location updates (latitude, longitude, description) every 5 seconds.

2. **Broadcast Receiver:**
   - Set up a broadcast receiver in a fragment to receive location updates from the background service.

3. **Map Integration:**
   - Utilize the OSMDroid library to display a map in the fragment.
   - Receive location updates from the broadcast receiver and add markers to the map in real-time.

**Technologies Used:**

- Android SDK
- Kotlin programming language
- OSMDroid library for maps
- View Binding
- MVVM

**How to Use:**

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the app on an Android device or emulator.
4. Press the "Start" button to initiate the background service.
5. The map fragment will receive location updates and display markers on the map.

**Additional Notes:**

- This project is meant for educational purposes and serves as a foundation for understanding background services, broadcast receivers, and map integration in Android applications.

Feel free to explore, modify, and enhance the project to suit your needs!

Here's a screen recording from the app, just click on the below image

[![Short Video](https://img.youtube.com/vi/fGF279_to5I/0.jpg)](https://www.youtube.com/shorts/fGF279_to5I?feature=share)
