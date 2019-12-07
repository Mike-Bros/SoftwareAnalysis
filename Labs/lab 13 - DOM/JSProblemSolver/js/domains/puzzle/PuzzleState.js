function PuzzleState(tiles) {
    
    this.tiles = tiles;
    var seperator = "+---+---+---+\n";
    var left = "| ";
    var right = " |\n";
    var inbetween = " | ";

    this.toString = function() {
        //top line
        var buf = seperator;
        buf += left;
        for(var j=0;j<3;j++){
            buf += this.tiles[0][j];
            if(j<2)buf += inbetween;
        }
        buf += right;
        //middle line
        buf += seperator;
        buf += left;
        for(var j=0;j<3;j++){
            buf += this.tiles[1][j];
            if(j<2)buf += inbetween;
        }
        buf += right;
        //bottom line
        buf += seperator;
        buf += left;
        for(var j=0;j<3;j++){
            buf += this.tiles[2][j];
            if(j<2)buf += inbetween;
        }
        buf += right;
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

    this.makeCanvas = function() {
	return this.makeDefaultCanvas(this);
    };
    
    this.getLocation = function(tile) {
        var r;
        var c;
        
        for(var i=0;i<3;i++){
            for(var j=0;j<3;j++){
                //if tile === this.tiles[i][j] save r and c to be returned
                if(tile === this.tiles[i][j]){
                    r = i;
                    c = j;
                }
            }
        }
        return {row: r, column: c};
    };
}

PuzzleState.prototype = STATE_PROTO;