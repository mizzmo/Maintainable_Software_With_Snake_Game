[Back to README](../README.md)\
\
\
[<-- Development Notes](devNotes.md)



## Changelog
### 9/11/23
- Removed Unused Files / Classes.
- Added JavaDoc to Project.
- Removed Commented Out Code.
- Reworked some code comments.
### 15/11/23 
- Renamed some folders.
- Rearranged Files. Introduced resources and java folder.
- Moved Classes to java folder.
- Moved Images to resources/images folder. Music to resources/music folder.
- Reworked getImage method in GameUtil.
- Added module.info
- Added gitignore.
### 28/11/23
- Reworked Project into a Maven Project.
- Added Dependencies to the pom file.
- Reworked module-info file.
- Reorganised class files.
- Changed some file locations in code to fit with the new Maven Build Method.
- Added some new issues to Git.
### 29/11/23
- Wrote up plan for MVC in DevNotes.
- Added Interface Classes for MVC.
- Implemented Methods for MVC interfaces.
- Implemented Constants in IController Interface.
- Added Class files for MVC.
- Removed Redundant Main Class.
### 30/11/23
- Implemented Getters and Setters in the Model.
- Added some private variables in Model.
- Impmemented part of Initialise method in Model.
- Started to implement InitialiseDisplay method in view.
- Changed variable names so that they follow the coding convetntions. 

### 6/12/23
- Changed comments on SnakeView from JavaDoc to Regular Comments, as JavaDoc is inherited from the Interface Class.
- Changed comments on SnakeController from JavaDoc to Regular Comments, see previous.
- Added two methods to the model interface; getAlive and setAlive, to get and set the liveliness status of the snake, to be used for things like ending the game when the snake dies.
- Added a new variable to SnakeModel, m_Alive.
- Implemented methods in SnakeModel, incrementLength, getAlive, setAlive.
- Reworked SnakeView initialiseDisplay to respect MVC, view now has no interaction with model.
- Added constructors to View, Controller and Model.
- Added a new Class, SnakeMain, that intialised all of the MVC.
- Started to convert the program to JavaFX. 
- Set up JavaFX.
- Fixed an issue where the controller was not initialised in the View beacuse of a issue with how JavaFX handles its instance of View.
- Added an Icon to the JavaFx.
- Implemented FXML.
- Updated Maven POM with dependencies.
- Updated module-info.
- Linked FXML to the Controller and View.
