# Trackr

--------------------------

![our epic trackr logo](https://i.imgur.com/CqHlNde.png)

Event Trackr Android app. Allows users to track birthdays and anniversaries of friends and family. 

## Configuring the Project

To keep things organized, this project uses the modules feature of IntelliJ. IntelliJ modules separate out the
phase-specific folders from the source code, and keep the project root from getting cluttered by gradle build files (
which are only necessary for the source code).

On cloning the project (and opening it in IntelliJ idea), you will have to mark the `trackr` folder as its own module. 
To do this, go to:

- `File` -> `Project Structure`
- `Project Settings` -> `Modules` -> `+` (in the top left corner) -> `Import Module`
- Select the `trackr` folder in this project -> `Okay`
- `Import Module from externel model` -> `Gradel` -> `Finish`

IntelliJ should now properly see the project source, and download the relevant JDK and libraries.

## Building the Project

Once the project has been configured, it can be built in two ways:

1. Via `Add Configuration`: Press press `Add new` or `+`, select `Gradle`, and ensure `trackr` is selected as the 'Grade
   project'
2. Via interacting with the code: Navigate to `trackr/src/main/java/Main.java` and press the play icon next to the class
   declaration or the main function declaration (this will create a build and run configuration).

## Running Tests

Once the project has been configured, the simplest way to run all tests is to navigate (in IntelliJ)
to `trackr/src/test/java`, right click on this folder, and select `run 'All Tests'`.