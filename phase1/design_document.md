# Design Document

## Major Design Decisions

- A description of any major design decisions your group has made (along with brief explanations of why you made them).

## Adherence to Clean Architecture

- A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)

## Consistency with SOLID design principles

- A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)

## Packaging Strategies

- A brief description of which packaging strategies you considered, which you decided to use, and why

## Design Patterns

- A summary of any design patterns your group has implemented (or plans to implement).

### Dependency Injection 

We used dependency injection extensively throughout our program. We injected our data access object interfaces into the repositories classes which was then injected again into all our viewmodel classes. This was all done with a library called Hilt which makes dependency injection much easier and removes all the boilerplate code required. This resulted in a very loosely coupled classes and allows us to substitute the repositories with their subclasses (if we wish to create them).

### Singleton Pattern

We used the Singleton pattern to create a Single instance of the database, repositories, and data access objects (DAO). This restricts our program to having only one instance of the database, repositories, and DAOs at any given time. There should only be one instance of the database at any given time, thus this singleton pattern helps ensure the correctness of our code. Furthermore, the repositories and DAOs are just classes that provide an interface for database operations, and therefore there is no reason to create multiple instances of it when all classes that depend on them can use the same instances since the behavior of the repository and DAOs will never change. The singleton pattern was utitilized through the Hilt library again. The hilt library creates singleton instances of the database, repositories, and DAOs to inject to the classes that depend on them.

### Observer Pattern

The observer pattern was used implicitly through the android library through State and LiveData classes. The LiveData and State classes provided by the android library allow the UI to observe any changes that happen to the data wrapped by a State or LiveData class. This allows all changes that happen to the LiveData and State data through the viewmodels to propogate up to the UI without the viewmodel explicitly depending on the UI (which is against clean architecture). Since the UI is observing this data, it will update itself accordingly instead of having another class explicitly tell it when to update.

## Use of Github Features

Pull Requests were used extensively throughout our project [which can be seen here](https://github.com/CSC207-UofT/course-project-trackr/pulls?q=). They allowed us to discuss various changes, as well as allowed others to review any changes and give their own feedback. The pull requests also allowed us to make sure all changes being merged into our main branch was error free.

We used Github Issues as a TODO list. This allowed us to keep track of all the features we need to complete and assign tasks to each person. It also allows us to make sure that no two people are working on the same feature independely (unaware of each other), which may have led to code conflicts.

## Code Style and Documentation

## Testing

## Refactoring 

## Code Organization

## Functionality
