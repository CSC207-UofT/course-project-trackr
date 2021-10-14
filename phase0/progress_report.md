# Progress Report 

---

Prepare a short written document updating your TA on your group's progress so far. At your next meeting with your TA, your group should be ready to present a summary of your progress report to your TA. Each group member must present part of the progress report.

The report should include:
- a brief summary of your specification, CRC model, scenario walk-through, and skeleton program,
- open questions your group is struggling with,
- what has worked well so far with your design,
- and a brief summary of what each group member has been working on and plans to work on next.

## Brief Summary

Trackr is a birthday tracking application in which users can create events (specialized as Birthdays or Anniversaries,
etc.), set reminders for events, and receive notifications for those events when reminders are due. Events are also
associated with people specifically, and users can attack multiple events to the same person.

## Our CRC Model

Our CRC model provides an outline for the structure of the back-end (and a primitive CLI front-end) of our application.

We've split our CRC model into Clean Architecture Layers:

### Entities

- Event
- Anniversary
- Birthday
- Person

These are all raw data classes. They store and manipulate data related only to them; their methods do not directly
affect the rest of the application.

### Use Cases

- PersonManager
- EventManager
- NotificationManager

### Interface Adaptors/Controllers

- Birthday Presenter