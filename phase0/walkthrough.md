# Scenario Walkthrough:

The user will get to choose what they want to do:

The user can create a birthday event:
This will be done through the BirthdayPresenter. The event will have a date and a friend it corresponds to. The user can also set a time before the event to be notified. The program will use DatabaseAccessInterfaces store its information (date, friend, set time) in the Database. The user can then look through their events using the CommnadLineInterface. The user will be able to search for the event they want to see the details of. Then the program will request that the BirthdayPresenter get the specified data and show the user the details of the selected event. Then, the user can choose to delete the event, and the program will use the previously mentioned DatabaseAccessInterface to remove the event stored as needed.
