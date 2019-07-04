# Iteration 2

The Calendar bug from Iteration 1 is a result of the Android memory cleaner destroying inactive Activities on low-spec devices. We are aware of this and it will be fixed wit Persistance integration.
The CalendarActivity class and EventEditActivity class underwent significant refactoring to adhere to SOLID principles, and plenty of formatting code was extracted from the Logic layer into a new CalendarFormatter class
Event and DateTime class refactored to follow SOLID principles, and moved validation to DateTime class to provide less astonishment for Event methods.
Settings button was removed and refactored to have a navigation bar at the top of the app, need to implement fragments to create a 'real' tab navigation bar (will do in iteration 3).
Login has been refactored. Splitting the form monitoring from the view model and just making the view model a distributor of information.
#### New UI
    - Add new event button
    - save event button
    - Delete event button
    - Tabs at the top function
    - Improvements to event list layout, CalendarActivity layout, EventEditActivity layout
    - Login/Registration fields dynamically inform user of bad input

Unforeseen complications arose for the team member responsible for DB implementation too late into the Iteration to adapt to. As a result, we are still relying on a convincing Fake DB across the app

# Iteration 1
Valid user login information:
- username: "username"
- password: "password"
You can register but saving the information isn't implemented yet.
A lot of ground work is done. And some stuff will me move around and re organized from its current position

# Git Format
Master(Release) <-- Dev <-- Feature branches

# Vision Statement
The Ed-Easy Agenda System (E-EASy) will be used by educators, students and parents to promote communication of both class and individual progress. It will maintain information including but not limited to due dates, test dates and important events added by educators for each class they teach and in turn, be personalized and viewed by each student taking multiple classes.
 
The E-EASy will be an interactive calendar designed from the ground-up for grade school academic purposes. Educators will be able to create course plans that include a variety of events that can include a date, a time, a location. These course plans will be shared with students, who will be able to subscribe to the plan to add all associated events to the students’ calendars. 

One of the primary users will be educators, and they can use the system to view the classes they have on a specific day, record what was done in each class, and set upcoming events. They will have the option to make these events and class information public or private, with the default being set to private. Another feature of the system will allow educators to track their students progress by having a list of past due assignments and tests, and marking them as completed.
 
The other primary user will be students. They will have the schedules of all the classes they are attending which they can stack into daily agendas. Their calendars should reflect all of the events that their educators for each class has set. They will also be able to see which assignments they have completed, and potentially mark assignments as completed for personal use.
 
The final user will be students’ parents. They should be able to have access to the daily schedule of their children.  They also should be able to have multiple children’s accounts attached to theirs in the case where they have multiple children using this program regardless of their ages. Another feature will allow educators to poll parents to see whether they have seen the marks that their children have received for a specific assignment.
 
No permissions or account types will be needed to separate teacher and student accounts. Any user can create a course plan and share that course plan with any other user through the use of a unique code. Likewise, all users will have a private code that they can use to share their private schedule details with a second user. It will the user's responsibility to share codes with the appropriate audiences.

This scheduler will be a self-contained Android application. Future releases can expand into the iOS environment and integrate web-based course plan editing.By improving the communication between all the users, this system will allow everyone to be held more accountable. Educators in terms of material and reasonable times for returning grades, students for completing assignments and tracking when their tests are, and parents for understanding their children’s actual progress.
 
The first way that we can measure our success with this system is by polling all users for their opinions about this system, hopefully receiving positive feedback. We can also track usership of this program, if users are logging in and using this application once or even multiple times a day, we will know that our system is being integrated into our users’ lives. We can alpha test with some teachers only allowing some of their classes to use the system, and monitor success with assignment completion. Lastly, parents should find that they are informed about their children's studies throughout the semester.
