This is arguably the most important part of your project — even more important than the code itself! Phase 1 should have given your group a great start on this document, so you should just need to update it based on what you work on in phase 2. Take the time to polish it up and catch any typos, poor formatting, or missing content.

Reminder: Your TA will start by reading this document when they grade your project, so make a good first impression!

The document should include everything outlined in phase 1, except the progress report should contain:

- a brief summary of what each group member has been working on since phase 1
- Each group member should include a link to a significant pull request (or two if you can't pick just one) that they made throughout the term. Include a sentence or two explaining why you think this demonstrates a significant contribution to the team.
- *NEW* Please read A Suggested Development Workflow if you or your group has been having trouble with the idea of using branching and pull requests in your workflow.

Your group may choose to include other information in your design document, as you see fit. For example, you might include additional information about testing in your program and a summary of the test coverage. Including links to specific parts of the code or pull requests are strongly encouraged.

Remember to keep the document concise and focused

# Design Document

## Major Design Decisions

### Kotlin

While not necessarily a design change, the largest project change we've made between Phase 0 and Phase 1 is our decision to use the Kotlin programming language instead of Java. We had a few reasons for doing this, but most of them revolved around ecosystem and maintainability: Kotlin is simply nicer to work with in the Android development space. 

Jetpack compose, for example, is the framework we are using to programmatically build our application views: it allows us to only use Kotlin to design and structure our interface layouts, allowing us to avoid learning the android-specific of and then using XML. This means that we could keep our code more organized (a single view file instead of a few) and stick with patterns that we were familiar with (Jetpack follows a similar structure to Flutter, which some of us have experience with). Unfortunately, Jetpack compose is only available for Kotlin, and so forced our hand into the language transition.

### Model-View-ViewModel (MVVM)

One major design choice was to use the MVVM design pattern. We chose this
design pattern because it helps our program adhere to clean architecture:
the components are easily divisible into the clean architecture layers.
View being in frameworks and drivers, view models in interface adapters, and model in use
cases/entities. Moreover, common tools within the Android ecosystem work well with the
model-view-viewmodel design pattern: the ViewModel class, for example, is built in to the standard
Android libraries, and facilitates interactions with views as well as setting up it's own lifecycle
context that asynchronous functions can be called in.

Hilt, our dependency injection library, also works specifically with viewmodels, and injecting
dependencies within them.

### Multiple ViewModels instead of one

Another design choice was to use multiple view models, one for each screen,
instead of one view model for all screens. This choice was to prevent any
bloating code smells because we predicted one view model would get too big.
That would also be a violation of the single responsibility principle as the
single view model would be responsible for managing multiple screens.

## Adherence to Clean Architecture

As for serialization, our group opted for a local SQLite Database through the Room persistence library.
We chose this over other storage options because we felt SQLite was both fast and allows for easier
migration to a hosted postgres database, which could allow for highly sought-after features, such as
syncing data across user devices.

Our database structure is represented by the following Entity Relation Diagram (ERD):
![erd](https://cdn.discordapp.com/attachments/907383386207842314/909549647209582602/Screenshot_from_2021-11-14_16-04-37.png)
With our data in seperate tables for each entity, we can avoid having duplicate data while having entity-specific access objects,
which also decreases overall coupling.

Here is a UML diagram for our program.
![erd](https://media.discordapp.net/attachments/892174330329448455/909670610689462313/UML_1-3.png?width=1770&height=1269)

## Consistency with SOLID design principles

- Single responsibility principle:
  - Our team did well adhering to the single responsibility principle. Each class
    has one responsibility. As some examples, our screen classes in the drivers/frameworks 
    layer of clean architecture have the single responsibility of inputting/outputting
    information to the screen/user for their own respective screen. We have a view model
    for each screen so there is not one big one responsible for too much. 
    We have repositories that have the single responsibility of giving/getting
    data from the database. We have an EventNotificationManager who's sole responsibility is 
    setting/removing alarms for a notification.
    The only minor violation to the SRP is that our view models (AddScreenViewModel and EditScreenViewModel) 
    hold 2 responsibilities. One is transforming data into a usable form and the other is telling the other
    classes what to do with this information. For example, the EditScreenViewModel tells the eventRepository
    to perform multiple tasks in a series of steps to edit an event. If we had more time, we would split these 
    responsibilities into a class that transforms data to and from the screen, and another that takes this 
    information and tells the other classes what to do.
- Open/closed principle:  
  - Our program adheres to the open/closed principle as well. For example, instead
    we use Android's ViewModel class as a tool that is closed for modification
    but open to extension. We extend the ViewModel class to make specific view 
    models that are needed for our program. This way, whenever we want to create a new screen that
    requires a new view model, we do not have to modify any old code, we can just
    extend the ViewModel class to create as many implementations as we need.
- Liskov substitution principle:
  - Any time there is inheritance or implementation of an interface, we make sure
    that the derived object only extends the base classes features, not modify or remove anything.
    For example, our EventBroadcastReceiver class extends Android's BroadcastReceiver
    object. EventBroadcastReceiver has the same fields and responsibilities as BroadcastReceiver, and it extends its functionality by implementing its abstract method "onReceive". We extend the
    functionality by allowing it to send a notification upon receiving a specific intent.
- Interface segregation principle:
  - For the most part, our group keeps interfaces concise, including only essential
    methods. However, in a few cases, there are unnecessary methods. 
    For example, our EventDao interface requires an editPerson method which we 
    currently are not using. If we had more time, we would definitely implement a person
    editing functionality in which we would require that method. In that case, it
    would not have violated the interface segregation principle.
- Dependency inversion principle:
  - Our group does a good job implementing the dependency inversion principle
    into our program. For example, to prevent our repositories such as EventRepository
    from depending directly on lower level classes such as the Database, we 
    have the EventRepository depend on the EventDao interface instead. As a result, 
    the EventRepository does not need to know about how the Database is implemented.

## Packaging Strategies

### Where we were in Phase 0 
Our Phase 0 submission was based around a much more cut-and-dry clean architecture approach: because we were writing everything from scratch in an ecosystem with less-defined package structures (a console-focused app with basic interactions), we took the liberty to come up with a package structure that worked specifically for us and what we were trying to accomplish. It turns out, in Phase 0, the clean architecture package approach (where each sub-package is a layer in clean architecture) was the most logical; we could easily see when we were violating layer dependencies based off of package imports in our source files, and at the same time were able to easily import the classes we did actually need (without unnecessarily polluting our name space). This clean architecture packaging approach also made it easy to decided where to add new modules, and to visualize the dependency tree: we just had to take note of which packages are module files were in!

### Where we are now
Phase 1, and our transition to both the Kotlin programming language and the android programming ecosystem, complicated the clean architecture packaging approach. All of a sudden, certain android-specific classes and extensions needed to be places in packages they didn't entirely belong (Where should the Dependency Injection modules be stored, especially if they're being used by a third-party extension), and some packages started accumulating modules that were only related by their layer, and not by any shared properties between them (for example, the database and the UI, under clean architecture, should both be placed in the same "frameworks and drivers" package; they are otherwise completely unrelated). These complications made adding new modules very difficult (especially when trying to import modules for other packages), and made finding modules after we added them unnecessarily complicated.

Our solution to these issues, then, is to group modules based on their functionality; this has the happy side-effect that most functionality groupings will also result in grouping modules of the same layer (but also allowing us to have multiple packages representing different parts of that one layer). For example, all models (previously described as entities) are grouped together in the `model` package: they are grouped by their functionality, and they just happen to all exist on the same layer.

This hybrid approach allows us to reap the benefits of both modules: we get the intuitive module layout of organizing by function (if you want to edit a viewmodel, find the viewmodel folder) and the ease-of-adherence to clean architecture through the examination of module imports.

## Design Patterns

### Dependency Injection 

We used dependency injection extensively throughout our program. We injected our data access object interfaces into the repositories classes which was then injected again into all our viewmodel classes. This was all done with a library called Hilt which makes dependency injection much easier and removes all the boilerplate code required. This resulted in a very loosely coupled classes and allows us to substitute the repositories with their subclasses (if we wish to create them).

### Singleton Pattern

We used the Singleton pattern to create a Single instance of the database, repositories, and data access objects (DAO). This restricts our program to having only one instance of the database, repositories, and DAOs at any given time. There should only be one instance of the database at any given time, thus this singleton pattern helps ensure the correctness of our code. Furthermore, the repositories and DAOs are just classes that provide an interface for database operations, and therefore there is no reason to create multiple instances of it when all classes that depend on them can use the same instances since the behavior of the repository and DAOs will never change. The singleton pattern was utitilized through the Hilt library again. The hilt library creates singleton instances of the database, repositories, and DAOs to inject to the classes that depend on them.

### Observer Pattern

The observer pattern was used implicitly through the android library through State and LiveData classes. The LiveData and State classes provided by the android library allow the UI to observe any changes that happen to the data wrapped by a State or LiveData class. This allows all changes that happen to the LiveData and State data through the viewmodels to propogate up to the UI without the viewmodel explicitly depending on the UI (which is against clean architecture). Since the UI is observing this data, it will update itself accordingly instead of having another class explicitly tell it when to update.

## Use of Github Features

Pull Requests were used extensively throughout our project [which can be seen here](https://github.com/CSC207-UofT/course-project-trackr/pulls?q=). They allowed us to discuss various changes, as well as allowed others to review any changes and give their own feedback. The pull requests also allowed us to make sure all changes being merged into our main branch was error free.

We also leveraged the Code Review features that are baked into pull requests, including code-block level commenting and the approve-reject system. These features allowed us to specifically identify points in a pull request that we felt weren't up to standard, or points which might require further elaboration and design discussion.

Pull request drafts were also used as a method of showing potential contributors what was currently being worked on, but not yet ready for merging. This use allowed us to avoid the mistake of duplicate work and to share with each other how a future interface might function with respect to the application.

We used Github Issues as a TODO list. This allowed us to keep track of all the features we need to complete and assign tasks to each person. It also allows us to make sure that no two people are working on the same feature independely (unaware of each other), which may have led to code conflicts.

Github actions was recently configured to make sure the project builds, which allows us to make sure the main branch always builds and prevents any errors that prevent the project from building to be merged in.

## Code Style and Documentation

While both reliant on the JVM, Kotlin and Java require different design decisions when considering discrete implementation steps; these differences largely focus on cleaning away redundant and/or useless syntax and wrapper objects.

One of the main differences between Kotlin and Java in terms of syntax is the ability for multiple scopes to exist within a single class: we can use these scopes to attribute certain functions (perhaps static functions) to a class without worrying about polluting some of that classes namespace. For example, the tracker database utilizes the a `companion object` syntax: within this scope, the functions for getting, storing, and maintaining a singleton database are stored. In Java, these functions might just be a part of the regular database class, or they might exist in their own wrapper class (that would require it's own lifecycle and maintenance); here in Kotlin, these functions are visible to the programmers that need them and might modify them, are separated from the actual database (which, once created, no longer cares about how it was instantiated) without having to engineer entirely separate units of code.

Kotlin's constructor semantics also change the way we might program an application. The ability to have optional constructor arguments, for example, completely eliminates most of the usefulness of a builder class: why instantiate an object in multiple steps when you can just do it in one?

Kotlin also changes what it means for a unit of code to exist: functions no longer need to be tied to classes as methods. This feature we used fairly sparingly: it is often useful to have and manipulate state, and objects are very good at this kind of workflow. However, when state wasn't necessary (like our Views), we could just write the functions to build them without worring about having to maintain boiler-plate class definitions.

## Testing

Our application's data access objects, notification system, calendar view, and view models have a robust test suite to ensure proper access and storage of user information.
Since these serve as our application's primary use cases and interfaces, it is critical that we are sure they are working properly.
Every method used to read or write to the database has its own test.

## Refactoring 

Refactoring our codebase was something we did periodically throughout the development process. Here are a few examples of pull requests that involved refactoring.

### PR - 101
This refactoring involve making changes to AddScreen.kt and AddScreenViewModel.kt files. Initially, variables representing the state of the AddScreen were stored in the AddScreen function itself. This was a bad design not only because it violated the MVVM architecture, but also because it lead to an awful code smell. Managing the state of the AddScreen in the AddScreen function meant that the AddScreen function was very long and difficult to read. Extracting the state management code into the AddScreenViewModel fixed both of these problems.

### PR - 126 
This refactoring involved extracting a UI component from the HomeScreen file and placing it in the shared components folder. EventList is a UI component that was used by the HomeScreen, the CalendarScreen and the SelectScreen. Originally EventList was defined inside of the HomeScreen file, but storing this component in this file didn't make sense. The main question we asked ourselves when making this refactor was why should AddScreen depend on something in HomeScreen? If multiple UI views depend on a specific component, that component should be defined in a shared resource folder in order to make the code easier to navigate. This pull request successfully implemented this change.

### PR - 115
In this pull request, we intentionally avoided a major refactor by instead undertaking a smaller one. One of the main features we wanted to include in our app was the ability to track the current age of an event (i.e. 6th birthday or 10th anniversary). This was one of the last features we added in the app, so when we attempted to add it the majority of the app had already been built. Such a feature depended on storing the first year of the event in the database. One option would have been to store the first year of the event in the date property of the Event entity. However, this would have required a major refactor of the CalendarScreen, since the CalendarScreen depends on a dummy year being stored in the date property. Since the logic controlling the CalendarScreen was quite complex, we instead decided to refactor the TrackrEvent entity class by adding a new property to store the first year of the event. This also meant we had to add new methods to the EventRepository class and EventDao interface which entailed the less than ideal act of "shotgun surgery". Regardless we were able to complete the refactor and implement the desired feature.

## Code Organization

See packaging strategies, clean architecture, and SOLID.

## Functionality
Our code successfully executes everything that the specification says it should do. One highlight of our program is that it is capable of preserving state between runs of the program by storing user data in a database. Here is a link to a screen recording of the app:
https://utoronto-my.sharepoint.com/:v:/g/personal/daniel_hocevar_mail_utoronto_ca/EXqjbxZuf6BNlzVZHw4yWwoBBZcJpS1hX5gPQjzEKOtiUg?e=AF2agE
