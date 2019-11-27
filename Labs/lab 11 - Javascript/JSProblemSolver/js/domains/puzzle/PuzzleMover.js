const SLIDE_1 = "Slide 1";
const SLIDE_2 = "Slide 2";
const SLIDE_3 = "Slide 3";
const SLIDE_4 = "Slide 4";
const SLIDE_5 = "Slide 5";
const SLIDE_6 = "Slide 6";
const SLIDE_7 = "Slide 7";
const SLIDE_8 = "Slide 8";

var puzzleMover = new Mover();

puzzleMover.addMove(SLIDE_1,(s) => slideOne(s)); // You provide move functions
puzzleMover.addMove(SLIDE_2,(s) => slideTwo(s));
puzzleMover.addMove(SLIDE_3,(s) => slideThree(s));
puzzleMover.addMove(SLIDE_4,(s) => slideFour(s));
puzzleMover.addMove(SLIDE_5,(s) => slideFive(s));
puzzleMover.addMove(SLIDE_6,(s) => slideSix(s));
puzzleMover.addMove(SLIDE_7,(s) => slideSeven(s));
puzzleMover.addMove(SLIDE_8,(s) => slideEight(s));

function PuzzleMover() { }

PuzzleMover.prototype = puzzleMover;

function slideOne(state) {  // creates a new state with block moved
    
    return state;
}

function slideTwo(state) {  // creates a new state with block moved
    
    return state;
}

function slideThree(state) {  // creates a new state with block moved
    
    return state;
}

function slideFour(state) {  // creates a new state with block moved
    
    return state;
}

function slideFive(state) {  // creates a new state with block moved
    
    return state;
}

function slideSix(state) {  // creates a new state with block moved
    
    return state;
}

function slideSeven(state) {  // creates a new state with block moved
    
    return state;
}

function slideEight(state) {  // creates a new state with block moved
    
    return state;
}