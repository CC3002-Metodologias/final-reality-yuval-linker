Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

How to run the game
---
To run this project please compile it using a Java Virtual Machine first and then run the FinalReality class. 
*The game does nothing for now as it is in development*

Assumptions
---
- The code assumes that the playable characters have a base weight of 10.
- It also assumes that there is only one type of enemy (*Although is open to add different classes*)
- The thief class can equip swords, knifes, and bows.

The Code's Logic
---
This game is programmed using Object Oriented Programming. It has characters and weapons that
only playable characters may equip.

Every character is has the same base behavior so they all inherit from the same
abstract class and interface. This is also applicable for the weapons.

Playable characters differ from enemies because the player can equip and use weapons.
This is why the playable characters have their own interface and abstract class.

The player's characters have different classes and so here comes another fork between the objects.
There are **Common** characters and **Mage** characters. The mages have the same behaviour as the common
except for the fact that mages can use spells and have mana.

This is why mages have their own interface and abstract class. With this said the **Staff**
weapon is different from the other weapons because it has *magic damage*. But since it is a
particular case it doesnt have another abstract class or interface of its own.

To implement the attack and equip a weapon of a character the **double dispatch**
technique is used. This is done by implementing an *attack* and *defend* method
on each character. Also every playable character has an *equip* method that
messages a weapon what class it's trying to equip that weapon. And finally every 
weapon has a method that can answer accordingly (all the equipTo*Class* methods) 