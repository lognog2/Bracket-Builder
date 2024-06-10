# Bracket Builder version 1.0
- Creates a series of ints [1, n] from a hard-coded int n, currently set to 16.
- From this series of integers, a bracket is constructed in traditional seeding format(1).


## How bracket generation works
- From the initial list, a new empty list is created whose size is the next largest 'perfect bracket' number(2).
- The number 1 is added to the beginning of the bracket, since it will always be first.
- Numbers from the initial list are added to the bracket list by the following formula:
  - A number n, beginning at 2, is doubled each round.
  - From 0 to half of n, The next number in the initial list is added to one after its pairing's position in the bracket.
  - A number's pairing is found by subtracting the number to be added from n+1 (for example, 3 would face 2 in round 2 because (4+1-3 = 2)
  - After being added to the bracket, the number is removed from the initial list.
  - Once this is repeated n/2 times, the process repeats with n*2 until the bracket is filled.
- If there are still bracket slots to be filled after the list is empty, there must be placeholder 'bye' numbers added.
- Byes are added to the bracket by the same formula, but negative.   

## How the bracket progresses
- Once the bracket has been made, Play can begin.
- The next pairing to face off is taken, let's call them A and B.
  - If either number is a bye, the other wins automatically.
- A random integer [1, A+B] is generated. If the number is between [1, A], then A is eliminated. Otherwise B is eliminated.
  - For example, say A is 5 and B is 2. If the random number is between [1, 5] then B wins. If it's [6, 7] then A wins. 
- The eliminated number is simply removed from the bracket; The bracket will keep its structural integrity.
- This repeats until one number is left, which is declared the winner.

(1) Highest seed plays the lowest, second highest plays the second lowest, etc. Position in bracket remains constant each round
(2) A perfect bracket has 2^n number of contestants (2, 4, 8, 16, etc) 
