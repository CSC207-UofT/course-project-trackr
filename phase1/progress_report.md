#  Progress Report

### Open Questions
- Open questions your group is struggling with  
  - How should we design the user experience in the future so that we can 
    allow multiple events to be tied with a certain person? How should the 
    user be able to choose which person is tied to which event?
  - Should we allow users to specify their own types of events or keep it
    only as birthdays and anniversaries? If the former, how should we 
    represent this (through inheritance or another TrackrEvent field)?
  - Should we split the certain view models (`AddScreenViewModel` and 
    `EditScreenViewModel`) into separate classes?
  - What options can we give to the user to satisfy universal design principles?
    Dark mode? High contrast text? Font adjusting?

### Things That Have Worked Well With Our Design
- What has worked well so far with your design  
  - So far using the MVVM design pattern/keeping clean architecture made it 
    very easy to work on one aspect of the project at a time without necessarily
    having to rely on someone else to work on the other class first. It also
    makes testing easier.
  - Using multiple view models instead of one has made testing and simply working
    on those view models much simpler. Since each has a specific responsibility,
    one person can work on one view model while someone else works on a 
    different one.
  - Having a data access object/repository for each major entity (eg. one for events, another
    for people) allows us to do specific operations on each entity. As opposed to
    our phase 0 which had one data access object, this keeps things modular and convenient.

### Work Distribution

Menghao Yu:
- Designed HomeScreen and Calendar Screen
- Worked on Viewmodels for all screens to display data from the database
- wrote tests for the calendar screen
- integrated Hilt for dependency injection into the project and created the dependency injection modules

Roman Zupancic:
- Refactored event repeat systems
- Architected and implemented initial Viewmodel architecture
- Abstracted away various view components for reuse

Jonathan Ginevro:
- Designed and built EditScreen and SelectScreen
- Implemented basic functionalities for these screens (such as the ability to edit an Event or select an Event from the HomeScreen and edit that event specifically)
- Wrote and worked on tests for AddScreenViewModel, CalendarScreenViewModel,
  and EditScreenViewModel

Nathan Hansen:
- Wrote classes and test for database and data access objects
- Wrote classes for repositories that fetch and store data from the database
- Made sure database was serializable/could persist through runs
 
Jeremiah Djianto:
- Wrote classes and tests to implement a notification functionality
- Worked on view models (specifically `EditScreenViewModel`) to allow 
  editing of events
- Worked on tests for `EditScreenViewModel`

Daniel Hocevar:
- Designed and implemented AddScreen and HomeScreen
- Worked on view models including the AddScreenViewModel
- Built EventManager and PersonManager
- Created codebase architecture diagram/UML diagram


### What We Plan to Work on Next

Menghao Yu:
- Improve user interface 
- perhaps add a login screen and user authentication
- work on improving the architecture of the project.

Roman Zupancic:
- Clean up various code around viewmodels and views (specifically identifying duplicate code to be abstracted and then depended upon)
- Attempt (once again) complex event recurrences
- Help with setting up a remote database that syncs with the local one

Jonathan Ginevro:
- Improve adherence to the 7 Principles of Universal Design (add dark mode, add a "Are you sure you want to delete that event?" pop-up, etc.)
- Work on being able to add/view comments on events
- Help refactor old code to reduce/prevent code smells

Nathan Hansen:
- Work on expanding to remote database (perhaps Firebase)
- work on multi-user experience and implementing that functionality
- Perhaps adding features relevant to universal design principles

Jeremiah Djianto:
- Improving adherence to SOLID principles (specifically OPC and SRP)
  perhaps by adding interfaces/extracting classes
- Work on allowing editing of people and events in relation to the person
  (may require another person editing screen)
- Adding functionality to tapping a notification (perhaps brings the
  user to the event in the app)

Daniel Hocevar:
- AddScreenViewModel shouldn't depend on so many other classes (Fix this code smell)
- Work on remote database
- Change homescreen to show upcoming events instead of events this year.
