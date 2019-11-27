function PuzzleState(tiles) {
    
    this.tiles = tiles;
    var seperator = "+---+---+---+\n";
    var left = "| ";
    var right = " |\n";
    var inbetween = " | ";

    this.toString = function() {
        var buf = seperator;
        buf += makeLine(0);
        buf += seperator;
        buf += makeLine(1);
        buf += seperator;
        buf += makeLine(2);
        buf += seperator;
        return buf;
    };

    this.equals = function(other) {
	if (other === null) return false;
        if(this.tiles === other.tiles){
            return true;
        }else{
            return this.tiles.toString() === other.tiles.toString();
        }
    };

    // Other properties and methods here

    this.makeCanvas = function() {
	return this.makeDefaultCanvas(this);
    };
    
    makeLine = function(i){
        var buf = left;
        for(var j=0;j<3;j++){
            buf += tiles[i][j];
            if(j<2)buf += inbetween;
        }
        buf += right;
        return buf;
    };
    
}

PuzzleState.prototype = STATE_PROTO;


