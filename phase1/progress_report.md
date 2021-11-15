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

### Work Distribution
- A summary of what each group member has been working on  

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


Nathan Hansen:

 
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
- A summary of what each group member plans to work on next

Menghao Yu:
- Fix bugs related to the calendar screen, specifically the bugs regarding the events not displaying correctly in the list and the dates not being emphasized correctly after an edit.
- Improve user interface 
- perhaps add a login screen and user authentication
- work on improving the achitecture of the project.

Roman Zupancic:
- Clean up various code around viewmodels and views (specifically identifying duplicate code to be abstracted and then depended upon)
- Attempt (once again) complex event recurrences
- Help with setting up a remote database that syncs with the local one

Jonathan Ginevro:


Nathan Hansen:


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
