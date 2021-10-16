# Progress Report

### Part 1: Project Summary

#### Specification

Our reminder application allows users to create, edit, or delete events that correspond to the date of a special occasion such as a birthday or anniversary.

#### CRC Model

Our CRC model has four **Entities** (Anniversary, Event, Birthday, Person), two **Use Cases** (Event Manager, Person Manager), one **Controller** (BirthdayPresenter), and two **Drivers** (Command Line Interface, Data Storage).

#### Scenario Walk-Through

Our typical walk-through has the user decide between creating a new event or managing previously set events. If they want to create an event, they need to give it a name, date, associated person, and set time they want to be reminded before the event. If the user wants to view their events the program will display a list of upcoming events, the user can then choose to view more information about the event or delete it entirely.

#### Skeleton Program

Our Skeleton Program will be able to do the following

- Create new Birthday Events
- Create new Anniversary Events
- Delete Existing Birthdays and Anniversaries
- View upcoming Events
- More

### Part 2: Open Questions

- How should we differentiate between different Objects. That is, given two Events/Person objects which have identical attributes, how can we distinguish between them?

- Do we need subclasses for the Event class? The Anniversay subclass and Birthday subclass are identical except for the class name. Should a Birthday exhibit different behaviour than an Anniversay?

- Should we use tags on Events instead of subclasses, or maybe a Tagable Interface?

- How should the Data from the database be transported to the UI? Is the current architecture valid and sustainable?

- Does the EventManager have too much responsibility? Should we divide EventManager into smaller classes?

- Should we be storing `Person` classes as instance attributes for `Events`? Should we be using generated ID's to link people and events together instead? Should we have person classes at all (and just store names in events)?

### Part 3: Things That Have Worked Well With Our Design

While we haven't yet directly dealt with the consequences of our project architecture (we're still _in_ the architecting
phase), we've certainly planned for numerous areas of expansion.

#### DataAccess

The `DataAccess` class, and its related classes `Database`, `DatabaseAccessFactory`, and `DatabaseAccessInterface` are
designed to make swapping out database solutions easy and with minimal refactoring.

Why do we need this extensibility? For now, we have decided that only using volatile memory to store database objects (
like `Event`s and `Person`s) is sufficient to test and showcase our application: by not having to support some external
database schema, and by not having to work through the challenges that come with object serialization (what attributes
are important? what can we re-assemble from the data we've decided to store?) greatly decreases the complexity of our
application and allows us to focus on the core user experience.

Eventually, however, we'll want persistence: our users shouldn't have to re-create events every time they launch our
app! The `DataAccess` class will simplify this extension by providing a common interface for the rest of our program to
access this kind of data: it provides adders and removers that will be common to all types of databases (this means that
the rest of the app doesn't _need_ to care about what database is being used).

When we decide to add a database, then, we will only need to modify `DataAccess` to use the API of our
database (and remove our temporary volatile `Database`); since the rest of our program works _through_ `DataAccess`, it
doesn't even have to be refactored.

#### Subclassing `Event`

Why our event structure (having an abstract `Event` class as a parent class to different types of event classes) is a
point of contention right now (as in, is it really necessary?), as of phase0 it seems to be the best way to represent
events. Having an abstract `Event` class forces us to implement new events as subclasses, allowing us the extensibility
of subclasses: like adding event-specific features and messages. The class hierarchy also makes for easy polymorphism: 
instead of keeping track of specific events in the rest of our program, we can just refer to the general `Event` class.

The class hierarchy also makes it easy to add new classes without having to refactor the entire program: just add a new
subclass (and the appropriate UI flags to create it), and, since the rest of the app only ever refers to the
non-specific `Event` class, the new event type is automatically compatible.

### Part 4a: Work Distribution

Person 1: Menghao Yu

- Implemented a `Person` class with various methods that allow the object to be edited.
- Implemented a tentative `Database` which stores `Event` objects
- Created a `DatabaseAccessInterface` to allow other classes to perform operations on the `Database`.
- Created a `DataAccess` class which provides an implementation for the `DatabaseAccessInterface`.
- Created a `DatabaseAccessFactory` to create `DatabaseAccessInterfaces` without directly instantiating the `DataAccess` class.

Person 2: Roman Zupancic

- Created the `Event` class and its subclasses `Birthday` and `Anniversary`
- Implemented tests for `Birthday` and `Anniversary` classes
- Wrote part of this report

Person 3: Jonathan Ginevro

- Implemented the `PersonManager` class and architected its interactions with other classes
- Wrote part of this report

Person 4: Nathan Hansen

- Created the `CommandInterface` class that acts as a (temporary) front end for interacting with our application
- Wrote handling logic for directing commands to internal classes (early command parsing) 

Person 5: Jeremiah Djianto

- Wrote the `BirthdayPresenter` class, including the logic for handling `add`, `remove` and `view` commands and parsing their parameters for compatibility with the higher-level layers
- Wrote the `InputBoundary` and `OutputBoundary` classes, which separates the core of our program from I/O

Person 6: Daniel Hocevar

- Wrote the `EventManager` class, including its interaction with our database architecture.
- Architected how `Event` classes and the `Person` class interact with each other.
- Wrote part of this report

### Part 4b: What we plan to work on next
Big Goals:
- Our primary goal for the next phase of the project will be to build an android app. We plan to have Daniel, Jonathan and Jeremiah work on this.
- Building an android app will provide users with a simpler and more friendly way of interacting with our application. 
- Another goal we have is to replace the Database class with a real Database. We have discussed the possibility of using SQL or Firebase. We plan to have Menghao, Roman and Nathan work on this.

Code Level Goals (we will all work together to realize these goals):
- Clean up the interaction between the CLI class and the BirthdayPresenter class
- Add more features for the user (such as the ability to edit events)
- Add Notifications class to support notification feature

