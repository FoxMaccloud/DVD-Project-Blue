# Obligatorisk oppgave 3

### Part 1

**Summary last meeting**

Date: 23.04 10.00-12.00

Participants: Henrik, Håkon, Gytis, Tård

Agenda:
* greetings and updates
* what have been done since last time
* issues
* continue or get new assigments
* agree on date for next meeting

**Group dynamics and team processes**

The different roles in the team functions well. Tård will still continue as team leader, as will Gytis as responsible for the master branch, 
Henrik as responsible for the rules and MVC design patterns and Håkon as responsible for communication and reports. 
For a more descriptive explanation of the different roles, see the previous report from oblig 2.

As we mentioned in the second obligatory assignment, we use the project methodology 
called Kanban. This works still as a good methodology for our group as we use the project 
board frequently. The project board serves as an overview of what is going on at each 
particular moment, and will be continuously updated. In order to perform couples programming 
during the current situation, considering the Covid-19 virus, we benefit from applications as 
Microsoft Teams or Zoom where it is easy to share screen. Otherwise we still use Slack as our 
main communication channel where we discuss issues and progress pertaining delegated task and 
deadlines. 

![Project board](https://github.com/inf112-v20/DVD-Project-Blue/blob/master/deliverables/UML/ProjectBoard_no3.png?raw=true)

**Retrospective**

Last time we mentioned that the workload in the group changes due to one members absence. 
This workload is now more stabilized and distributed among the team members. We all agree 
that the communication has been good. The meetings before the closing of the school were 
all held in an efficient way, where problems were discussed and solved in fellowship, if
possible.

Since last time we have created more branches in order to separate the work from the master 
branch. As mentioned, there were some unnecessary communication taking place before the 
second obligatory assignment when each member had to wait for the other to commit to the 
master branch. This is now not longer a problem due to the different created branches. 

The communication between the team members has been lower since the closing of the school. 
Forward we will focus on maintaining the good flow we had before, and continue to work 
systematically. 


### Part 2

**This delivery**

This time we implemented most of the user stories from the previous report. 
We have created a menu where the player is able to choose between _play_, _lan_ 
and _quit_. The menu has the same design as last time. After selecting play,
the player can choose between five different maps and the number of players (from 2 to 8). 

For this delivery we made it possible for the robot to move by using the different
type of cards. Now the player can choose the cards by dragging them as we have 
implemented a functional “drag and drop” function. The robot will now move according 
to the cards’ value. Further the robot will now interact with the different effects on 
the board according to the rules of the game. The robot can not for example move through
 a wall. 

The next delivery will be the last and final delivery, and therefore the ending result
should be a fully, functional game. A game where you can play with others online and 
against other AI players.

**_Implement when a player wins_**

_User story:_ As a player I need to collect the flags in the right order to manage my next moves. 

_Task:_ After implementing the flags correctly we want to create a method that checks whether a player has all the flags so that it can be called out as the winner of the game. 

_Acceptance requirements:_ If the player has collected all the flags in the right order, it should win the game


**_A player could die_**
 
_User story:_ In order to a functional game I need that my player, or the others play, is able to die

_Task:_ Implement a own class, e.g called LoseGame. Implement a method in Player that moves the player to LoseGame when the player has died.

_Acceptance requirements:_ If the player loose its three lives it should be redirected to LoseGame


**_Create or join an already existing game_**

_User story:_ I want to be able to join an already existing game with my friends, or create a new one

_Task:_ Implement a functional LAN so that a player could play with others

_Acceptance requirements:_ be able to join og create a new game.


As for the next delivery, we consider all the user stories as MVP since its the final submission.


### Part 3
The code builds and runs by running _Main_ which is located in the inf112.RoboRally.app folder 
- the outermost folder in our package hierarchy. A LwgjApplication is initialized, with the 
_GameScreen_ class, which sets the screen to the _MainMenu_ class. From the _MainMenu_ class 
_ShowBoard_ is initialized, where current models for cards and robots are initialized manually
 within the class, and views are hardcoded. Refer to the UML diagram below ,which contains only 
 the most important dependencies with regards to the focus of this delivery.
 
CLASS DIAGRAM 
![UML diagram](https://github.com/inf112-v20/DVD-Project-Blue/blob/master/deliverables/UML/ObligatoriskOppgave3UML.png?raw=true)

**Navigating the menu**

The code builds and runs by running Main.java which is located in the inf112.RoboRally.app
 folder. The user is then taken to a menu, where he can choose between the buttons _play_, 
 _lan_ and _quit_. As described earlier, the player is now able to choose between differents 
 maps and the amount of player (after clicking on play). There are currently no LAN 
 functionality, after clicking this button you can choose between create new lobby, 
 join existing game or go back. Go back is the only functional button as of now. Mouse 
 clicking quit will terminate the program, as intended. 
 
**Manual Movement Test: player cannot move through a wall**
 The user can then manually test the robots’ movement by dragging the cards placed at the right side of the game.
 
 1. Run main() and click play
 2. Select board and amount of players (choose Vault Assault for this test)
 3. Select the cards “Move 2” twice, for the supplementing with other cards
 4. You should see that the player stays at the same coordinates after two moves
 
 
**Automated tests**

The JUnit tests can be found in the Test folder. The tests can be found in the following 
classes: _SinglePlayerSettingsController_, _CardTest_, _RoundTest_ and _CardFactoryTest_.

A big part of testing this delivery is manual testing the connection of cardviews and moving the robot via models. The user can drag and drop to card slots which will then, invoke a series of communication between classes that in the end move the robot model. A series of standard output messages are given to help the user trace the communication.

The seperation of model and view on the robot took a step back this delivery, and is not intended to be a part of the final implementation. This made automatic testing of the game, board and robot model unecessary. 

Tests for models that are the next step in development are to be found
in the ‘NextDeliveryTests’ branch. This branch also includes skeletons 
classes for round, board and player models.

