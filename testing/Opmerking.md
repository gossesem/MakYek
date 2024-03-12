
# Nog te verbeteren van de code: makeMove method werkt maar niet 100% zoals het moet.

Player X's turn.
Enter the row and column of the piece you want to move (from):
0 0
Enter the row and column of the square you want to move to (to):
6 0
Player X captured three O counters in a single move by a combination of intervention and custodianship.
Updated Board:
. X X X X X X X
. . . . . . . .
X X X X X X X X
. . . . . . . .
. . . . . . . .
O O O O O O O O
X . . . . . . .
O O O O O O O O
Player X count: 16
Player O count: 13
Player O's turn.
Enter the row and column of the piece you want to move (from):


# te corrigeren:
nieuwe positie op board te printen: The captured piece moet het verwijderd worden op updated board .

player count : als 2 or 1 or 3 captured is , we krijgen the rest op counterVariable zoals hier:
Player X count: 16
Player O count: 13

makeMove() method : we zouden void gebruiken in plaats van boolean  denk ik.



werkt mee :
printBoard()

Probeer nog verder mee te werken: ik krijg het niet 100% te werken volgens de rules van MakYek game.






# wat werkt nog:

