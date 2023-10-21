# World-of-Zuul
Line based game

Project: "Zuul Rescue"
Authors: Michael Kölling, David J. Barnes and Alexandra-Maria Anastase
        (k-number: 20022789)
Version: 02.12.2020

This project is part of the material for the book

   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016
   
   *THE GAME*
   Zuul Rescue is a game consisting of several locations in a castle. The game’s 
purpose is saving the prince of the World of Zuul, who is held captive and get 
him outside. First, the player must gather items for two different purposes: an 
eventual fight with a dragon and unlocking several rooms. If the player gets 
defeated by the dragon, he dies and loses the game. After that, the player needs 
to find the prince of Zuul and get him to the courtyard in order to win the game. 
   
   To start this application, create an instance of class "Game" and call its
"play" method.
    
    *WHAT IT DOES*
   The player can walk through the rooms, pick up and put down items, use them
for a purpose (e.g. use a key to open a room, eat food to restore health etc), 
answer to questions and attack another character (if the character can be attacked).

    *BASE TASKS COMPLETED*

• The game has several locations/rooms.
• The player can walk through the locations. (This is already implemented in the code you
are given.)
• There are items in some rooms. Every room can hold any number of items. Some items can
be picked up by the player, others can’t.
• The player can carry some items with him. Every item has a weight. The player can carry
items only up to a certain total weight.
• The player can win. There has to be some situation that is recognised as the end of the
game where the player is informed that they have won.
• Implement a command “back” that takes you back to the last room you’ve been in.
• Add at least four new commands (in addition to those that were present in the code you got
from us).

   
    *CHALLENGE TASKS COMPLETED*
    
• Add characters to your game. Characters are people or animals or monsters – anything that
moves, really. Characters are also in rooms (like the player and the items). Unlike items,
characters can move around by themselves.
• Extend the parser to recognise three-word commands. You could, for example, have a
command give bread dwarf to give some bread (which you are carrying) to the dwarf.
• Add a magic transporter room – every time you enter it you are transported to a random
room in your game.

    *MY CHALLENGE TASKS*
    
• Create different types of items (weapons and consumables).
• Player fights another character (dragon fight).
• Player answers to a riddle and it results in an item becoming available.
• Some rooms are locked and the player needs to unlock them (e.g.: player needs to have certain
items in their inventory or use a certain command with a certain item, answer a question or 
defeat an adversary in a battle).
• Player can lose the game. - Player loses the game if they die in the fight with the dragon.







   
