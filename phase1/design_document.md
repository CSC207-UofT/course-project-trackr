# Design Document

## Major Design Decisions

- A description of any major design decisions your group has made (along with brief explanations of why you made them).

## Adherence to Clean Architecture

- A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)

## Consistency with SOLID design principles

- A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)

## Packaging Strategies

### Where we were in Phase 0 
Our Phase 0 submission was based around a much more cut-and-dry clean architecture approach: because we were writing everything from scratch in an ecosystem with less-defined package structures (a console-focused app with basic interactions), we took the liberty to come up with a package structure that worked specifically for us and what we were trying to accomplish. It turns out, in Phase 0, the clean architecture package approach (where each sub-package is a layer in clean architecture) was the most logical; we could easily see when we were violating layer dependencies based off of package imports in our source files, and at the same time were able to easily import the classes we did actually need (without unnecessarily polluting our name space). This clean architecture packaging approach also made it easy to decided where to add new modules, and to visualize the dependency tree: we just had to take note of which packages are module files were in!

### Where we are now
Phase 1, and our transition to both the Kotlin programming language and the android programming ecosystem, complicated the clean architecture packaging approach. All of a sudden, certain android-specific classes and extensions needed to be places in packages they didn't entirely belong (Where should the Dependency Injection modules be stored, especially if they're being used by a third-party extension), and some packages started accumulating modules that were only related by their layer, and not by any shared properties between them (for example, the database and the UI, under clean architecture, should both be placed in the same "frameworks and drivers" package; they are otherwise completely unrelated). These complications made adding new modules very difficult (especially when trying to import modules for other packages), and made finding modules after we added them unnecessarily complicated.

Our solution to these issues, then, is to group modules based on their functionality; this has the happy side-effect that most functionality groupings will also result in grouping modules of the same layer (but also allowing us to have multiple packages representing different parts of that one layer). For example, all models (previously described as entities) are grouped together in the `model` package: they are grouped by their functionality, and they just happen to all exist on the same layer.

This hybrid approach allows us to reap the benefits of both modules: we get the intuitive module layout of organizing by function (if you want to edit a viewmodel, find the viewmodel folder) and the ease-of-adherence to clean architecture through the examination of module imports.

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

We also leveraged the Code Review features that are baked into pull requests, including code-block level commenting and the approve-reject system. These features allowed us to specifically identify points in a pull request that we felt weren't up to standard, or points which might require further elaboration and design discussion.

Pull request drafts were also used as a method of showing potential contributers what was currently being worked on, but not yet ready for merging (to avoid the mistake of duplicate work and to share how an interface might function).

We used Github Issues as a TODO list. This allowed us to keep track of all the features we need to complete and assign tasks to each person. It also allows us to make sure that no two people are working on the same feature independely (unaware of each other), which may have led to code conflicts.

## Code Style and Documentation

## Testing

## Refactoring 

## Code Organization

## Functionality
