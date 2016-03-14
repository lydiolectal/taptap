--Description--

In Tap Tap Revolution, the user's goal is to tap out the beats to a song with maximal precision, on which they are scored. Four stationary dots at the bottom of the screen correspond to the keys 'd', 'f', 'j', and 'k', which are the pads of the user's virtual drumset. While the song is playing, circles that represent the beats of the song float down vertically towards one or more of these dots at a regular speed. Tbe objective is to tap at the moment the circle reaches the edge of the screen, in time with the music.

Currently, the player may select one of three songs, ranging from 'Easy' to 'Legendary' in difficulty. The most frequent the beats and simultaneous key presses required, the more difficult a song is.

--How it works--

TO START: Read the tutorial if you're just starting out, or hit "Skip." Select a song you like from the dropdown menu and click "Start Game!"

TO PAUSE SONG: Move cursor out of window to pause the song. Move cursor back into window to resume.

TO PAUSE SONG AND MENU: Click the pause button in the top left corner. This will take you to a screen that allows you to either resume the game or begin a new one.

TO PLAY: The keys 'd,' 'f,' 'j,' and 'k' correspond to columns 1, 2, 3, 4 from left to right. When a circle floats down a column, try to press the button in the right column at the instant the circle reaches the dot at the bottom of the screen. Ex: if a yellow circle floats down column 4, press 'k' as soon as the yellow circle reaches the yellow dot. As the game gets harder, you may have to press more than one key at a time!

--Rules--

After starting the game, a song will start playing, and dots corresponding to beats will float downwards. Each time the player presses the correct key at the correct time (within 400 milliseconds before or after the actual dot), 10 points will be add to the total score.

--To run the program--

Click "Main.java" to run the program.

--Issues/Improvement--

Javafx does not allow us to use a relative path to access text files. Therefore when we open a text file we use: "/folder_name/.txt". If you still encounter any compiler errors when running the program, change the file path to an absolute path. We have test the program on different computers, only one or two computers had this error.

Because of the real-time scoring calculations that the program must perform while playing music and controlling animation, there are sometimes syncronization issues between animation, music, and keypress tracking. A possible improvement would be to optimize the time efficiency of our scorer and animation. 

Given more time, we would implement some other minor features to the game: a volume slider to adjust the volume during the game, and a progress bar to indicate how far through the song the user is. The way that the program reads from mp3 and simple beat (txt) files means that Tap Tap is easily extendable and can be made to play and score any new songs as well. To increase the options for users, Tap Tap might have multiple difficulty levels for each song. 


