# Life Manager Application 
A simple custom made desktop application for a school project. This allows you to write journal entires about your day and server as a virtual journal. You can also use this as a weekly schedule manager to better organise events happening in your life.

## GIF Demo
<img src="https://github.com/justvinny/school-project-demo/blob/main/19089783-gui-sample-demo(GIF).gif" width="600" height="375">

## App Features
**Journal Entry:**
* Add new journal entries.
* Remove existing journal entries dynamically. 
* View currently saved journal entries.

**Weekly Schedule Entries:**
* Add new weekly schedule entires.
* Remove existing weekly schedule entries dynamically.
* View currently saved weekly schedule entries.
* Various sorting options available when viewing the entries.

**Storage:**
* Saved in two separate CSV files using a custom delimiter to properly save paragraphs of text without breaking my program.

**Unit Tests:**
* Added 6 basic unit tests using JUnit5 for 6 classes that handled the backend logic of my application.

**Design Pattern:**
* Imperfect variation of MVC. I separated the classes and files into different packages called domain, logic, userinterface, userexceptions, tests, database, and the main driver of the application is located in the default package.

**Some Intermediate Language Features Used:**
* Lambdas and Streams
* Anonymous classes
* Inner classes
* Collections
* Unit Testing

**Version Control:**
* Used Git command line. Currently stored locally and in a private repository. Will make it public after semester ends.

**Class/File Stats (26 Total):**
* Main Driver - 1
* Abstract class - 1
* Customer Exception - 1
* Customer Interfaces - 2
* Enumeration - 2
* Unit Tests - 6
* Concrete Classes for backend logic - 7
* Concrete Classes for GUI (Swing) - 6

**Screenshots:**

<img src="https://github.com/justvinny/school-project-demo/blob/main/MainMenu.PNG" width="600" height="375">

<img src="https://github.com/justvinny/school-project-demo/blob/main/AddJournalEntry.PNG" width="600" height="375">

<img src="https://github.com/justvinny/school-project-demo/blob/main/ViewRemoveJournalEntry.PNG" width="600" height="375">

<img src="https://github.com/justvinny/school-project-demo/blob/main/AddWeeklySchedule.PNG" width="600" height="375">

<img src="https://github.com/justvinny/school-project-demo/blob/main/ViewRemoveWeeklySchedule.PNG" width="600" height="375">
