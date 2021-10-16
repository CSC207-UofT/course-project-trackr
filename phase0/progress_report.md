# Progress Report

### Part 1: Project Summary

#### Specification

Our reminder application allows users to create, edit, or delete events that correspond to the date of a special occasion such as a birthday or anniversary.

#### CRC Model

Our CRC model has four **Entities** (Anniversary, Event, Birthday, Person), two **Use Cases** (Event Manager, Person Manager), one **Controller** (BirthdayPresenter), and two **Drivers** (Command Line Interface, Data Storage).

#### Scenario Walk-Through

Our typical walk-through has the user decide between creating a new event or managing previously set events. If they want to create an event, they need give it a name, date, associated person, and set time they want to be reminded before the event. If the user wants to view their events the program will display a list of upcoming events, the user can then choose to view more information about the event or delete it entirley.

#### Skeleton Program

Our Skeleton Program will be able to do the following

- Create new Birthday Events
- Create new Anniversary Events
- Delete Existing Birthdays and Anniversaries
- View upcoming Events
- More

### Part 2: Open Questions

- How should we differentiate between different Objects. That is, given two Events/Person objects which have identical attributes, how can we distinguish between them?

- Do we need subclasses for the Event class? The Anniversay subclass and Birthday subclass are identical except for the class name. Should a Birthday exhibit different behavior than a Anniversay?

- Should we use tags on Events instead of subclasses, or maybe a Tagable Interface?

- How should the Data from the database be transported to the UI? Is the current architecture valid and sustainable?

- Does the EventManager have too much responsibility? Should we divide EventManager into smaller classes?

### Part 3: Things That Have Worked Well With Our Design

### Part 4: Work Distribution

Person 1: Menghao Yu

- Implemented a `Person` class with various methods that allow the object to be edited.
- Implemented a tentative `Database` which stores `Event` objects
- Created a `DatabaseAccessInterface` to allow other classes to preform operations on the `Database`.
- Created a `DataAccess` class which provides an implementation for the `DatabaseAccessInterface`.
- Created a `DatabaseAccessFactory` to create `DatabaseAccessInterfaces` without directly instantiating the `DataAccess` class.

Person 2: Roman Zupancic

- Created the `Event` class and its subclasses `Birthday` and `Anniversary`
- Implemented tests for `Birthday` and `Anniversary` classes
- Wrote part of this report
- Wrote and troubleshooted the project build instructions

Person 3: Jonathan Ginervro

- Implemented the `PersonManager` class
- Wrote part of this report

Person 4: Nathan Hansen

- Created the `CommandInterface` class
- Task 2

Person 5: Jeremiah Djianto

- Wrote the `BirthdayPresenter` class
- Wrote the `InputBoundary` and `OutputBoundary` classes

Person 6: Daniel Hocevar

- Wrote the `EventManager` class, including its interaction with our database architecture.
- Task 2
