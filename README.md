# Trackr

--------------------------

![our epic trackr logo](https://i.imgur.com/CqHlNde.png)

[![Android CI](https://github.com/CSC207-UofT/course-project-trackr/actions/workflows/android.yml/badge.svg)](https://github.com/CSC207-UofT/course-project-trackr/actions/workflows/android.yml)

Event Trackr Android app. Allows users to track birthdays and anniversaries of friends and family. 

You need to open this project in Android Studio to get the best development experience.

## Configuring the Project

To keep things organized, this project uses the modules feature of IntelliJ. IntelliJ modules separate out the
phase-specific folders from the source code, and keep the project root from getting cluttered by gradle build files 
(which are only necessary for the source code).

To set this up, you need to import the gradle project:

- Right click on `/trackr-app/build.gradle`
- Click `Link Gradle Project`

## Building the Project

1. After adding the gradle project, the top-right build menu should be populated with build options. 
2. Ensure that `trackr-app.app` is selected, and a device of your choosing is available (either an emulator or a physically tethered device).
3. Click the hammer to build the project, and then the play button to launch the app on your device 

## Running Tests

Once the project has been configured, the simplest way to run all tests is to navigate (in IntelliJ)
to `trackr-app/app/src/androidTest/java/com/trackr/trackr_app`, right click on this folder, and select `run 'All Tests'`.

## Running the project

You can download the apk from the [latest release](https://github.com/CSC207-UofT/course-project-trackr/releases) and download the app on any android device with APK level 26+