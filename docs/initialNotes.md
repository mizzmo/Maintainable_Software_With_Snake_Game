
[Back to README](../README.md)\
\
\
[<-- Introduction](../README.md)\
[Initial Notes on Structure -->](notesOnStructure.md)

<a name="initialNotes"></a>

## Questions To Answer.
- Q: Where are the images/assets stored?
-   A: All Assets are stored in the same directory as the code.  
- Q: How does the program choose which fruit to display?
-   A: In the Food Class, a fruit image is randomly chosen in the constructor using the "Random" libaray.  
- Q: How is the score calculated?
-   A: In the Food Class, there is a method called "eaten", which checks if the snake intersects a fruit, if so the score is increased by a flat 521 each time.
- Q: How does the game know the user has crashed into something?
-   A: For fruit, the "eaten" class in Food detects if you have hit a fruit. For a self hit, in the MyFrame.MySnake Class, there is a method called "eatBody" which detects if the head is at the same point as one of the snakes body parts. For the walls, also in MyFrame.MySnake, there is a method called "outofBounds" which detects if you have hit the wall.
- Q: How does the game know a key has been pressed?
-   A: The game uses the "KeyEvent" and "KeyListener" libraries to tell if a key has been pressed, and act on that.
- Q: How does the game handle the frame creation?
-   A: Frame creation starts in "Play" in main, where a new frame is loaded onto a Play object using "loadFrame". In MyFrame, a Jframe is created and this is where the loadFrame method that was called in Play is stored. It adds a keyListner, sets title, size, location, windowListner and setVisible.
- Q: What is responsible for adding a new segment to the snake?
-   A: In Food, if a fruit is eaten, it adds +1 to the length of the snake and then passes this to "changeLength" in MyFrame.MySnake. Then "drawBody" is responsible for adding the new point to the snake, and this is called by "draw", which is called by "paint" which is stored in "Play".
- Q: How do the body parts of the snake follow the same path as the head?
-   A: The "move" method in MyFrame.MySnake seems to be responsible for deciding which way the snake moves based on user inputs. Then "drawBody" draws a body point in the last x and y that the head was at.
- Q: How does the game handle music?
-   A: The MusicPlayer Class handles music for the game. Music is played by calling "getMusicPlay" in main of Play.
- Q: How are the images created on the screen?
-   A: The graphics library is used to draw images to the screen.

## Initial Notes on the Code.
- All code and assets are contained within the same directory.
- There seems to be no naming conventions for files 
- Random comments are left in the code.
- Where there are comments, they dont mean anything.
- Most code is left completely unexplained.
- There are methods that dont do anything in the code.
- There are unused methods in the code.
- There are classes inside classes, leading to confusing code.
- There doesn't seem to be any obvious design pattern used here.
- There is an interface with only one method that is only used once, seems redundant.
- Duplicated methods resulting in excessive code.

## Proposed Immidiate Changes.
- Split out the code and assets into seperate folders.
- Refactor code to account for this change.
- Establish new naming and coding conventions to be implemented into this code.
- Remove unneeded files and code snippets. (*Unused Files Etc.*)
- Go through and comment all existing code.
- Establish a MVC design pattern in the code.
- Rename files to be simpler and more expressive.
- Seperate out the nested classes into seperate files.
- Either remove the interface, or improve it to house more methods.
- Remove repeated methods, adapt instead to include more functionality.
- Remove unused variables.

