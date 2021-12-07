#  Progress Report

### Open Questions
Here were some of the questions that we asked back in phase 1:
- How should we design the user experience in the future so that we can 
  allow multiple events to be tied with a certain person? How should the 
  user be able to choose which person is tied to which event?
  - We answered these question in phase 2, by redesigning the add event screen and creating a person managerment screen.
- Should we allow users to specify their own types of events or keep it
  only as birthdays and anniversaries? If the former, how should we 
  represent this (through inheritance or another TrackrEvent field)?
  - In phase 2, we opted to keep Birthdays and Anniversaries as the only supported event types.
- Should we split the certain view models (`AddScreenViewModel` and 
  `EditScreenViewModel`) into separate classes?
  - In order to clean up the view models in phase 2, we placed the business logic that cluttered the view models into separate manager classes.
- What options can we give to the user to satisfy universal design principles?
  Dark mode? High contrast text? Font adjusting?
  - Our app now supports dark mode as well as variable text size! These features can be adjusted within the android settings on the user's device.

### Things That Have Worked Well With Our Design
Here were some of the things that worked well in phase 1 of the project.
- So far using the MVVM design pattern/keeping clean architecture made it 
  very easy to work on one aspect of the project at a time without necessarily
  having to rely on someone else to work on the other class first. It also
  makes testing easier.
  - We continued to use the MVVM architecture in phase 2.
- Using multiple view models instead of one has made testing and simply working
  on those view models much simpler. Since each has a specific responsibility,
  one person can work on one view model while someone else works on a 
  different one.
- Having a data access object/repository for each major entity (eg. one for events, another
  for people) allows us to do specific operations on each entity. As opposed to
  our phase 0 which had one data access object, this keeps things modular and convenient.

In phase 2, some new things that have worked well are:
- Using interfaces to create boundaries between view models and managers. Although not necessary, 
  this reduced coupling. It made it easier to refactor our codebase (specifically our view models and managers) without
  needing to adjust one when we adjust another.
- Splitting the responsibilities of our view models between the view models and managers
  helped our code adhere to SRP and keep classes readable and helped fixed the slight
  bloating code smell.

### Work Distribution

**Menghao Yu**:

Phase 1:
- Designed HomeScreen and Calendar Screen
- Worked on Viewmodels for all screens to display data from the database
- wrote tests for the calendar screen
- integrated Hilt for dependency injection into the project and created the dependency injection modules

Phase 2:
- Created an edit toggle for the event details screen
- Fixed bug where the UI wasn't displaying the most up-to-date data after a Person edit
- Cleaned up all files and removed unused imports
- Integrated the new data access/manager interfaces with some of the viewmodels

[PR #92](https://github.com/CSC207-UofT/course-project-trackr/pull/92): I created a reusable UI component which displays a list of events from the database. This UI component provided to be very useful as a simple way to show events, and has been used throughout many different screens of our program. This component reduced lots of duplicate code, and provided a consistent way to display events throughout the app. It also allows the user to tap on an event and go to the details/edit screen.

[PR #108](https://github.com/CSC207-UofT/course-project-trackr/pull/108/files): I created a wrapper class and integrated the TrackEventOutput wrapper event into the UI. I refactored a of code and moved all data processing/anything related to data that was being done on the UI to the viewmodels instead. As a result, I standardized how the ViewModels fetch and give data to the UI. From that point on, all other viewmodel will deal with data from the database in a similar way, and the UI will no longer interact with the database/entities directly.

**Roman Zupancic**:

Phase 1:
- Refactored event repeat systems
- Architected and implemented initial Viewmodel architecture
- Abstracted away various view components for reuse

Phase 2:
- Cleaned up convention violations (snake_case naming violations, for example) and added
  comments/documentation to classes and methods
- Added UserManager class and orchestrated how single-user functionality works through our app.
- Refactored codebase for isolation/decoupling between the ViewModels and lower-level classes

A Significant Pull Request:
[Phase 1: PR 90](https://github.com/CSC207-UofT/course-project-trackr/pull/90)

This pull request introduced our first concrete implementation of the Model-View-Viewmodel pattern
in our application, and was the first commit to actually get data from the front end to the backend.
While I wrote the code, I did not implement it alone: I worked with team members to find examples of
MVVM in other applications, listened to opinions on implementation, and eventually made some
executive decisions to actually get everything to work.

**Jonathan Ginevro**:

 Phase 1:
- Designed and built EditScreen and SelectScreen
- Implemented basic functionalities for these screens (such as the ability to edit an Event or select an Event from the HomeScreen and edit that event specifically)
- Wrote and worked on tests for AddScreenViewModel, CalendarScreenViewModel,
  and EditScreenViewModel

Phase 2:
- Added functionality for users to edit and delete people
- Designed and built PersonDetailsScreen
- Implemented PersonDetailsViewModel
- Implemented ui improvements such as a toggle to switch between viewing/editing a person, and a popup to ensure that the user wants to delete a person

**Nathan Hansen**:

Phase 1:
- Wrote classes and test for database and data access objects
- Wrote classes for repositories that fetch and store data from the database
- Made sure database was serializable/could persist through runs
 
Phase 2:
- Add here

**Jeremiah Djianto**:

Phase 1:
- Wrote classes and tests to implement a notification functionality
- Worked on view models (specifically `EditScreenViewModel`) to allow 
  editing of events
- Worked on tests for `EditScreenViewModel`

Phase 2:
- Created classes and tests to add people such as AddPersonScreen, 
  AddPersonViewModel
- Created classes and tests to view/choose from all available people
such as AllPersonsScreen, AllPersonsScreenViewModel
- Worked on tests for PersonDetailsScreenViewModel
- Adjusted user navigation by integrating the ability to add/choose people as the user 
creates events

[PR #149](https://github.com/CSC207-UofT/course-project-trackr/pull/149): 
This was a pull request involving 3 people (including me) but I created the
classes (view and view model) for displaying all the people in the database
as well as allowed people to be added/selected first before an event is created.
As a result, I implemented a major change to how events and people are 
created which is a significant contribution to how the user navigates and uses
the program in the final product.

[PR #84](https://github.com/CSC207-UofT/course-project-trackr/pull/84):
In this pull request, I created the initial classes for allowing notifications
to be sent when an event is approaching. In later pull request, I build upon
this feature however this was the foundation for all notification related
features. This is a significant contribution since reminding the user when
an event is approaching is one of the key aspects of the specification. Therefore,
I made a significant contribution by fulfilling one of the specification requirements.


**Daniel Hocevar**:

Phase 1:
- Designed and implemented AddScreen and HomeScreen
- Worked on view models including the AddScreenViewModel
- Built EventManager and PersonManager
- Created codebase architecture diagram/UML diagram

Phase 2:
- Designed and implemented EventManager and PersonManager classes
- Wrote tests for EventManager and PersonManager
- Created multiple interfaces that the manager classes and repository classes implement
- Applied DIP to ensure that view models depended on interfaces rather than the manager and repository classes.
- Created HILT bindings for each of the new interfaces, to allow the interfaces to conform to the existing Dependency Injection protocols within the codebase.
