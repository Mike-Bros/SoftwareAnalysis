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
    return trySlide(state,1);
}

function slideTwo(state) {  // creates a new state with block moved
    return trySlide(state,2);
}

function slideThree(state) {  // creates a new state with block moved
    return trySlide(state,3);
}

function slideFour(state) {  // creates a new state with block moved
    return trySlide(state,4);
}

function slideFive(state) {  // creates a new state with block moved
    return trySlide(state,5);
}

function slideSix(state) {  // creates a new state with block moved
    return trySlide(state,6);
}

function slideSeven(state) {  // creates a new state with block moved
    return trySlide(state,7);
}

function slideEight(state) {  // creates a new state with block moved
    return trySlide(state,8);
}

function trySlide(state,tileNum){
    var locTile = state.getLocation(tileNum);
    var loc0 = state.getLocation(0);
    if(isAdjecent(locTile,loc0)){
        var newState = doSwap(locTile,loc0,state);
        return newState;
    }
    return null;
}

function isAdjecent(locTile,loc0){
    if(locTile.row === loc0.row){
        if(locTile.column+1 === loc0.column || locTile.column-1 === loc0.column){
            return true;
        }
    }else if(locTile.column === loc0.column){
        if(locTile.row+1 === loc0.row || locTile.row-1 === loc0.row){
            return true;
        }
    }else{
        return false;
    }
}

function doSwap(locTile,loc0,state){
    var tiles = state.tiles;
    tiles[loc0.row][loc0.column] = tiles[locTile.row][locTile.column];
    tiles[locTile.row][locTile.column] = 0;
    var newState = new PuzzleState(tiles);
    return newState;
}