# Trackr

--------------------------

![our epic trackr logo](https://i.imgur.com/CqHlNde.png)

[![Android CI](https://github.com/CSC207-UofT/course-project-trackr/actions/workflows/android.yml/badge.svg)](https://github.com/CSC207-UofT/course-project-trackr/actions/workflows/android.yml)

Event Trackr Android app. Allows users to track birthdays and anniversaries of friends and family. 

## Configuring the Project

To keep things organized, this project uses the modules feature of IntelliJ. IntelliJ modules separate out the
phase-specific folders from the source code, and keep the project root from getting cluttered by gradle build files 
(which are only necessary for the source code).

To set this up, you need to import the gradle project:

- Right click on `/trackr/build.gradle`
- Click `Link Gradle Project`

## Building the Project

Once the project has been configured, it can be built in two ways:

1. Via `Add Configuration`: Press press `Add new` or `+`, select `Gradle`, and ensure `trackr` is selected as the 'Grade
   project'
2. Via interacting with the code: Navigate to `trackr/src/main/java/Main.java` and press the play icon next to the class
   declaration or the main function declaration (this will create a build and run configuration).

## Running Tests

Once the project has been configured, the simplest way to run all tests is to navigate (in IntelliJ)
to `trackr-app/app/src/androidTest/java/com/trackr/trackr_app`, right click on this folder, and select `run 'All Tests'`.

## Running the project

- Navigate to `trackr/src/main/java/Main.java` and press the play icon next to the class
  declaration or the main function declaration.
- Here are some sample commands:
  - `add Birthday 2020/04/05 Jeffry_Bezos 30`; add a birthday associated with Jeffry Bezos
  - `remove Birthday Jeffry_Bezos`; remove that same birthday event associated with Jeffry Bezos
