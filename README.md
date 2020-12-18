Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)


Credit goes to:
- Redshrike for the enemy sprite pixel art
- Phillip Lensen for the character sprites
- Mathew Pablo for the soundtrack

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

How to run the game
---
To run this project please compile it using a Java Virtual Machine first and then run the FinalReality class. 
A window will open and you will be welcomed with a ***Text Area*** and some buttons.

Here you will choose your character roster by writing a name on the text area and pressing the 
button of your choice of class. This will create one character with random stats. You need to choose 5
characters in order to advance.

Then you will be prompted to choose your inventory for the battle. Here you are presented with 5 buttons
each one representing a kind of weapon. Pressing it will create a weapon with random stats. You need to
have an inventory of 5 weapons to advance.

Then the battle will start again 5 demons who are trying to defeat you and your team. The turns will start
instantly. On your left you can see the enemy characters and on your right the character roster you chose.

In the center the turn dialog will show. If it is an enemy's turn the damage done to one of your characters
is written. If it's your turn, the current turn character's name will be shown underlined, some text
with the equipped weapon and two buttons will appear. With one button you can choose to attack 
and with the other one you can equip a weapon.

If you choose to equip a weapon your available inventory will be in the form
of different buttons. Press a weapon to equip it. If you can't equip that
weapon then you will see no change on your character's equipped weapon.
You can choose to equip as many times as you want.

If you choose to attack, the enemies will be shown in the form of buttons.
Press one to attack it. After that your turn will end.

If you run out of characters you lose. If you defeat every enemy you win.

Have fun!


Assumptions
---
- The code assumes that the playable characters have a base weight of 10.
- It also assumes that there is only one type of enemy (*Although is open to add different classes*)
- The thief class can equip swords, knives, and bows.
- Each character has a base attack, base defense, base weight. The weapons stats add
to that base.
- At the start of the game there is no wait time to be added to the queue. Also the characters are added
randomly so in the beginning there is no order based on weight.

The Code's Logic
---
This game is programmed using Object Oriented Programming. It has characters and weapons that
only playable characters may equip.

Every character has the same base behavior, so they all inherit from the same
abstract class and interface. This is also applicable for the weapons.

Playable characters differ from enemies because the player can equip and use weapons.
This is why the playable characters have their own interface and abstract class.

The player's characters have different classes and so here comes another fork between the objects.
There are **Common** characters and **Mage** characters. The mages have the same behaviour as the common
except for the fact that mages can use spells and have mana.

This is why mages have their own interface and abstract class. With this said the **Staff**
weapon is different from the other weapons because it has *magic damage*. But since it is a
particular case it doesn't have another abstract class or interface of its own.

To implement the attack and equip a weapon of a character the **double dispatch**
technique is used. This is done by implementing an *attack* and *defend* method
on each character. Also, every playable character has an *equip* method that
messages a weapon what class it's trying to equip that weapon. And finally every 
weapon has a method that can answer accordingly (all the equipTo*Class* methods)

### Controler
For events and the game controller, design patterns are used. Mainly the Observer pattern is used for
the events of a character dying or a character beginning its turn.

IEventHandler is the interface used to group every handler. These are `EnemyDeadHandler`,
`EnemyTurnHandler`, `PlayerCharacterDeadHandler` and `PlayerCharacterTurnHandler`.

`EnemyDeadHandler` and `PlayerCharacterDeadHandler` are observer for the event of a character dying. Since
there are two different Array Lists (one for enemies and other one for the player characters) two different
handlers are needed to call two different methods: `onCharacterDeath` or `onEnemyDeath`. On every character
death the win or lose conditions (as appropriate) are tested.

The same happens with the turns handlers. This is because the computer's turn is different to the player's
turn.

The player characters and inventory are modelled using Array Lists. The same happens for the enemies.
The controller provides getters for every stat of a character or weapon. This way there is no direct
intervention of the player with the model.

The queue and inventory are part of the controller and neither character nor weapon have knowledge of them
because the controller is supposed to control the model and not the other way.

The turn system is based on waiting times decided by the formula
```
character.weight/10
```
This is the delay time that the `ScheduledExecutor` of every character has to wait to call `addtoQueue`.


The controller continuously tries to extract characters from the queue. This happens until its empty.
When the queue is empty, the `addToQueue` method knows to add a character to the queue and immediately start 
its turn.

On an enemy's turn a random player character is chosen to be attacked. On the player's turn, input is waited

### Turns
To implement the turns the state pattern is utilized. For this the controller has a turn phase that it is in
and 4 possible phases are implemented.

Each phase represents a moment in a character's turn, although a computer controlled character will only use 2.
The 4 phases are:
- Begin Turn Phase
- Select Action Phase
- Select Weapon Phase
- Select Attacking Target Phase

The turn begins in the ***Begin Turn Phase*** where the controller can take a character from the queue and
trigger the begin turn event. After this there are 2 options to go.

If the character is an enemy it will go directly to the ***Attack Phase*** since its the only thing it can do.
Here it will choose a target randomly, attack and end its turn.

If the character is controlled by the player then it goes to the ***Select Action Phase*** where the player
can choose between equipping a weapon and attacking an enemy.

From the ***Select Weapon Phase*** the player can equip a weapon and the go back to the Select Action Phase
to continue with the turn.

From the ***Select Attacking Target Phase*** the player decides who to attack and after attacking the turn
ends.

The following diagram shows the flow of the turn phases:


![Turns phase diagram](media/Tarea_3_Phase_Diagram.png)

For each action the controller implements a *try to do* type of method that tries to do a certain action and
depending on the current phase of the turn it can do it or do nothing.

### The View
Since the application uses a Model View Controller pattern, the view is implemented on the *gui* package.
Here JavaFX is used with the main application being the `FinalReailty` Class.

To modularize the view, each scene of the game is its own class. Also the nodes with enemy, player and weapon
info are made using the ***Factory Pattern***, with the *Node Builder* classes being the factories.

The controller also has access to the view, specifically to the Main Scene. To test this the ***Null Object***
pattern was used, implementing an interface `IScene` and a `NullScene` class.

The game consists of 4 scenes
- Choose character roster (`ChooseUIScene`)
- Choose inventory (`ChooseInventoryScene`)
- Main Scene (`MainScene`)
- Ending Screen (`Ending Screen`)

The first two scenes are initial set-ups that the player has to go through to start the battle. The Main
Scene is where the majority of the game is done, it changes according to the controller and its events.

The final scene is for the ending screen showing "Victory!" if the player won or "You Lose" otherwise.