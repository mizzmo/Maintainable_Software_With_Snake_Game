[Back to README](../README.md)\
\
\
[<-- Instructions](instructions.md)
[ChangeLog -->](changelog.md)

### Converting to MVC Design Pattern.
Model Manages data and logic.
- Defines what the app should contain.
- Notifys view.
View handles layout and display.
- Defines how app data should be displayed.
Controller routes commands to the model and view parts.
- Contains logic that updates the model and or view in response to input from users.
<br>
Need to seperate out the different elements of the code.
Implement some interfaces that layout the functions that i will need for each of the MVC elements.

What should go in:
#### Model
- Initialise function to start the game?
- hasFinished function to see if the game is over or not.
- setFinished function to change if the game is finished.
- restart function to restart the game.
- getScore to get the score of the game.
- setScore to set the score of the game.
- getLength to get the length of the snake.
- setLength to set the length of the snake.
- incrementLength to add one to the length of the snake.
- getHeight to get the dimension of the screen.
- getWidth to get the dimension of the screen.
- setHeight to set the dimension of the screen.
- setWidth to set the dimension of the screen.
#### View
- getMusic to get the currently playing song. 
- setMusic to set the currently playing song.
- playMusic plays current song.
- loadFrame function from MyFrame.
- drawBody to draw the snake to the screen.
- paint function to draw the screen.
#### Controller
- move to move the snake.
- isMoving to check if the snake is moving and in which direction. Could assign some constants EG LEFT = 0, RIGHT = 1... to make it easier to understand what is happening here.
- eatBody (needs rename) to check if the snake eats itself.
- outOfBounds (needs rename) to detect if the user has gone off of the screen.
- KeyPressed functions...
- 