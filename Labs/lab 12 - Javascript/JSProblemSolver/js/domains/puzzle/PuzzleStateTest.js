var displayDiv = document.getElementById("displayDiv");

var start =     new PuzzleState([[2, 8, 3], [1, 6, 4], [7, 0, 5]]);
var startCopy = new PuzzleState([[2, 8, 3], [1, 6, 4], [7, 0, 5]]);
var slide6 =    new PuzzleState([[2, 8, 3], [1, 0, 4], [7, 6, 5]]);
var goal =      new PuzzleState([[1, 2, 3], [8, 0, 4], [7, 6, 5]]);

//Test state display
displayText("Here is the start state:");
displayState(start);
displayText("Here is after sliding tile 6:");
displayState(slide6);
displayText("Here is the goal state:");
displayState(goal);

//Test state equality

assert("!start.equals(goal)");
assert("!goal.equals(start)");
assert("!start.equals(slide6)");
assert("goal.equals(goal)");
assert("start.equals(startCopy)");

displayText("Here is the location of 2 in start:");
displayText(start.getLocation(2).row.toString()+","+start.getLocation(2).column.toString());

displayText("Here is the location of 4 in start:");
displayText(start.getLocation(4).row.toString()+","+start.getLocation(4).column.toString());

displayText("Here is the location of 5 in start:");
displayText(start.getLocation(5).row.toString()+","+start.getLocation(5).column.toString());