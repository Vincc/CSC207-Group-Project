# CSC207-Group-Project

Problem domain: social and sports 

The primary objective of this project is to develop an application designed to facilitate connections
and sports participation among individuals. When a group of people wants to engage in a specific sport but
lacks sufficient participants at the moment, they can create a group within the application. 
For instance, if they want to play soccer at 13:00 at the Harthouse field, they can create a group
for that purpose. Nearby individuals in the area will then have the opportunity to join this group
and participate in the sporting activity.

API use:
Areas of the project where APIs could be used include fetching Opencage_example data, group-chats for sports events, 
user authentication and exporting event details to external calendars.

Location Data - Open Cage:
https://opencagedata.com/api

Screenshot of generating API-key for testing:  
![Alt text](images/openCage.png?raw=true)

Longitude and latitude inputs:  
![Alt text](images/longlat.png?raw=true)

Output dictionary containing Opencage_example data:  
![Alt text](images/goethes.png?raw=true)

Calendar exports - Google Calendar api:
https://developers.google.com/calendar/api/guides/overview

Chat system - Stream Chat (tentative):
https://github.com/GetStream/stream-chat-java

Authentication:
https://docs.warrant.dev/

Documentation:

Getting Json Working - Installing json simple:
https://code.google.com/archive/p/json-simple/
1) Json simple is located in \lib
2) Go to File < Project Structure < Modules
3) Click on Dependencies tab
4) Click on "+", select the simple json jar file from lib, and then "ok".




In order to use Google Calendar Api
right click project -> open module setting -> dependencies -> click + -> library-> from maven.  
Add following respectively

    'com.google.api-client:google-api-client:2.0.0'
    'com.google.oauth-client:google-oauth-client-jetty:1.34.1'
    'com.google.apis:google-api-services-calendar:v3-rev20220715-2.0.0'

Then click Apply -> OK.




