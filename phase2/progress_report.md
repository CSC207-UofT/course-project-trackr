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

### Work Distribution

Menghao Yu:

Phase 1:
- Designed HomeScreen and Calendar Screen
- Worked on Viewmodels for all screens to display data from the database
- wrote tests for the calendar screen
- integrated Hilt for dependency injection into the project and created the dependency injection modules

Phase 2:
-

Roman Zupancic:

Phase 1:
- Refactored event repeat systems
- Architected and implemented initial Viewmodel architecture
- Abstracted away various view components for reuse

Phase 2:
-

Jonathan Ginevro:

Phase 1:
- Designed and built EditScreen and SelectScreen
- Implemented basic functionalities for these screens (such as the ability to edit an Event or select an Event from the HomeScreen and edit that event specifically)
- Wrote and worked on tests for AddScreenViewModel, CalendarScreenViewModel,
  and EditScreenViewModel

Phase 2:
-

Nathan Hansen:

Phase 1:
- Wrote classes and test for database and data access objects
- Wrote classes for repositories that fetch and store data from the database
- Made sure database was serializable/could persist through runs
 
Phase 2:
-

Jeremiah Djianto:

Phase 1:
- Wrote classes and tests to implement a notification functionality
- Worked on view models (specifically `EditScreenViewModel`) to allow 
  editing of events
- Worked on tests for `EditScreenViewModel`

Phase 2:
-

Daniel Hocevar:

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
