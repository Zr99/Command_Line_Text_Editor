Java Generics and Testing
-------------------------

**Purpose**: Get experience of the important software engineering process of 
testing (manual and unit), and to experiment with
the object-oriented features of Java, including generics.

Introduction
------------

A stack is a collection that supports adding elements to only one end
and removing them from the same end (i.e. a first-in-last-out data
structure). Stacks have a wide range of applications in situations where
we want to remember the order of requests and service the most recent
first.  

Implemented a bounded stack
and use it to remember the order of commands given to a text editor
so as to allow command 'undo' and 'redo'. A bounded stack is one that
can only hold a limited number of items.

Using a bounded stack limits memory usage. When in use for do/undo, if 
the stack gets full, commands will have to be dropped when new ones
need to be added.  In undo/redo stacks the usual
convention is to drop the oldest item in the stack - in other words to
keep information on only the most recent commands, thereby limiting the
number of commands that can be undone.

The Program
---------------------------

This repository holds an IntelliJ project with a sample Java program.
The idea of the program is that it is part of the source code of a 
text editor.  The structure of the text editor is that it has a class 'Document'
to hold the text being edited, and classes allowing users to enter editing
commands.  The interface of 'Document' allows manipulation of a 'cursor' (current
character position in the text), and insertion or deletion of characters at the cursor position.
Normally we would expect a text editor to have a graphic user interface.  In this
program, the Main class is just a command line program to allow a user to
enter simple editing commands at the keyboard. Command line editors in this style
were typical of the very first personal computer in the 1980's.

This example isn't intended to be a practically useful editor; but just to
have enough functionality to allow us to experiment 'doing' and 'undoing' commands.
For maximum simplicity our implementation of Document allows for just a single line of text.
The editor allows us to edit this line.
The 'cursor' is an integer.  If the line holds 'abcd', cursor position 0 is just before the 'a' at the
start of the line, 1 is between
the 'a' and 'b', 4 is between the 'c' and 'd' and 5 is at the end of the line.

The keyboard commands interpreted by the Main class are entered as lines of text holding a string of one
or two letter commands.

| Command | Action |
|:---|:---|
| `e` | is the exit command |
| `d` | deletes the character following the cursor (if possible) |
| `i c` | is a two character command that inserts the given character ```c``` into the document at the cursor position and advances the cursor |
| `f` | advances the cursor (if possible) without altering the text |
| `b` | moves the cursor left (if possible) without altering the text |
|:---|:---|
| `z` | undo last command.  This can be used repeatedly to undo multiple commands |
| `y` | redo the last command that was undone.  This can be used repeatedly |

Here is a sample interaction with the program.  Note that it displays
the current state of the line of text (the document) and current cursor value
at the start, and after each line of commands has been entered.  User input
follows 'Command >' on lines 3, 6, 9 and 12.

```
Document: 
Cursor position 0
Command > ia
Document: a
Cursor position 1
Command > ibicid
Document: abcd
Cursor position 4
Command > bbix
Document: abxcd
Cursor position 3
Command > e
Document: abxcd
Cursor position 3
```

If you examine the program, you will find that the Main program edits the Document
indirectly.  Instead of directly acting on inputs, it instead
creates an object holding details for each command, and asks those objects to 'perform' 
their command.  The objects created are subtypes of the class 'Command'.  The subtypes used
are 'Insert', 'Delete' and 'MoveCursor', all defined in the file 'Command.java'.
Command is an abstract class defined as follows:

````
public abstract class Command {
    protected Document doc;
    public Command(Document pdoc) { doc = pdoc; }
    public abstract boolean canBeDone();
    public abstract void perform();
    public abstract void undo();
}
````

Its constructor saves a reference to the document being edited.  It has a method
'canBeDone' which can be called to see if it is possible to perform the command, 
and methods 'perform' to carry out the editing operation and 'undo'  to reverse
its effect.  In the sample program you are given, commands are just carried out.
The reason for this indirect way of managing commands is to provide a structure
which will allow you to implement undo and redo.  Command objects can be pushed 
onto undo and redo stacks to be reversed and redone on user request.  
