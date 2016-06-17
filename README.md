# Crewber - Vehicle / Taxi Locating System (Android/iOS)
Open source vehicle tracking system. 
The objective of this project is develop a mobile base program with web base back-end with purpose of tracking and locate vehicle for an assigned passenger. The passengers can see their vehicle location, with real-time update on Google Map in mobile devices and web based administration console. In addition, passenger (mobile user) can see their vehicle registration number, driver details including a photo, estimated arrival time, driver's approximate rating, distance to pickup location, driver mobile number with quick access to calling facility and how many passengers have to pickup and their location pinpoints with the order of pickups. 

Mobile application is main part of the application and this runs on passenger's mobile devices. This includes above mentioned facilities and developed for iOS and Android devices. Google OAuth and Twitter Fabric use for authentication purposes and there are another two custom authentication levels with user specific identity number and pin number. Other facilities providing, using few Google APIs (Map, Distance Matrix, Cloud Messaging) that uses in Android and iOS. 

Web based console uses for mission handling purposes. This is the place where we provide information about drivers, schedules, vehicles and other miscellaneous information. 

Either mobile and web based solutions are powered by one REST API develop using JAX-RS technologies and Jersey framework. This runs on clustered well secured environment. Have use web service security mechanisms and firewalls, proxies for security and logging. 

Using the REST API, we track and logging every vehicle movement that broadcast from GPS device installed on vehicle. In this way, we would track vehicle location, speed, fuel levels and many more information and use them to provide our service for mobile users and manipulate them using web based dashboard console. 
