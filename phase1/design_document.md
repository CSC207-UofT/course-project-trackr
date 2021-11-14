# Design Document

## Major Design Decisions

- A description of any major design decisions your group has made (along with brief explanations of why you made them).

## Adherence to Clean Architecture

- A brief description of how your project adheres to Clean Architecture (if you notice a violation and aren't sure how to fix it, talk about that too!)

~section before we talk about the database goes here~

As for serialization, our group opted for a local SQLite Database through the Room persistence library.
We chose this over other storage options because we felt SQLite was both fast and allows for easier
migration to a hosted postgres database, which could allow for highly sought-after features, such as
syncing data across user devices.

Our database structure is represented by the following Entity Relation Diagram (ERD):
![erd](https://cdn.discordapp.com/attachments/907383386207842314/909549647209582602/Screenshot_from_2021-11-14_16-04-37.png)
With our data in seperate tables for each entity, we can avoid having duplicate data while having entity-specific access objects,
which also decreases overall coupling.

## Consistency with SOLID design principles

- A brief description of how your project is consistent with the SOLID design principles (if you notice a violation and aren't sure how to fix it, talk about that too!)

## Packaging Strategies

- A brief description of which packaging strategies you considered, which you decided to use, and why

## Design Patterns

- A summary of any design patterns your group has implemented (or plans to implement).

## Use of Github Features

## Code Style and Documentation

## Testing

Our application's data access objects, notification system and calendar view have a robust test suite to ensure proper access and storage of user information.
Since these serve as our application's primary use cases and interfaces, it is critical that we are sure they are working properly.
Every method used to read or write to the database has its own test.

## Refactoring 

## Code Organization

## Functionality
