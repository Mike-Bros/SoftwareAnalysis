function ProblemPanel(problem) {   
    var congratsPanel;   // A panel that replaces the problem view
    var finalState;
    var grats;
    var solved;
    var numMoves;
    var moves;
    var cont;
    var quit;
    var state;
    var stateCanvas;
    var stateDisplay;
    var movesUL;
    var assistant;
    var messageDisplay;

    this.panel = $("<div></div>");
    this.panel.addClass("outer");

    this.panel.append(welcome());
    this.panel.append(intro());
    this.panel.append(stateArea());
    this.panel.append(movesArea());
    this.panel.append(bottomArea());

    /*
     * Make the message and reset button area.
     */
    function bottomArea() {
	messageDisplay = $("<p></p>");
	messageDisplay.addClass("emphasized");

	var resetButton = $("<input></input>");
	resetButton.attr("type", "button");
	resetButton.val("RESET");
	resetButton.click(function() {
	    clearMessage();
	    assistant.reset();
	    updateState();
	    movesUL.fadeIn("slow");
	});

	var bottomDiv = $("<div></div>");
	bottomDiv.addClass("bottom centerText largeBold");
	bottomDiv.append(messageDisplay);
	bottomDiv.append(resetButton);

	return bottomDiv;
    }

    /*
     * Make the moves display area
     */
    function movesArea() {
	var movesDiv = $("<div></div>");
	movesDiv.addClass("right centerText");
	movesDiv.append(boldTextElement("Possible Moves"));

	movesUL = $("<ul></ul>");
	assistant = new SolvingAssistant(problem);
	var moveNames = problem.mover.moveNames;
	const scaler = 0.65; // to get button size right
	var bSize = (scaler * buttonSize(moveNames) * 16) + "px";

	moveNames.forEach((m) => makeButton(m));
	movesDiv.append(movesUL);
	return movesDiv;

	function makeButton(m) {
	    var button = $("<input></input>");

	    button.attr("type", "button");
	    button.val(m);
	    button.css("width", bSize);
	    button.addClass("moveButton");

	    addActionForButton(button, m);

	    var item = $("<li></li>");
	    item.append(button);
	    movesUL.append(item);
	};

	function addActionForButton(button, m) {
	    button.click(function () {
		assistant.tryMove(m);
		if (assistant.moveLegal) {
		    clearMessage();
		    updateState();
		    if (assistant.problemSolved) {
			displayMessage(
                            "Congratulations! " +
                            "You solved the problem using " +
                            assistant.moveCount +
                            " moves.");
			animateCongrats();
                        congratulate();
		    }
		} else {
		    displayMessage("That move is not legal.");
		}
	    });
	};
    }

    function animateCongrats() {
	messageDisplay.css("font-size", 'xx-small');
	messageDisplay.css("color", 'Green');
	messageDisplay.css("background-color", 'Red');
	messageDisplay.animate({fontSize: '1.5em'}, "slow");
        
    }
    
    /*
     * Change the canvas of the current state display area
     */
    function updateState() {
	$(stateCanvas).remove();
	var prevState = state;
	state = problem.currentState;
	stateCanvas = state.makeCanvas();
	stateDisplay.append($(stateCanvas));
	state.animateMove(prevState);
    }

    /*
     * Display a string to the message display area.
     */
    function displayMessage(str) {
	messageDisplay.text(str);
    }

    /*
     * Clear the message display area.
     */
    function clearMessage() {
	messageDisplay.css("font-size", 'medium'); // restore after
	messageDisplay.css("color", 'FireBrick');  // animation
	messageDisplay.css("background-color", 'transparent');
	displayMessage("");
    }

    /*
     * Compute and return maximum move button label size
     */
    function buttonSize(moveNames) {
	var size = 0;
	moveNames.forEach(function (m) {
	    if (m.length > size) {
		size = m.length;
	    }
	});
	return size;
    }

    /*
     * Make the current state display.
     */
    function stateArea() {
	stateDisplay = $("<div></div>");
	stateDisplay.addClass("left centerText");
	stateDisplay.append(boldTextElement("Current State"));
	state = problem.currentState;
	stateCanvas = state.makeCanvas();
	stateDisplay.append($(stateCanvas));
	return stateDisplay;
    }

    /*
     * Make the introductory text for the problem.
     */
    function intro() {
	var introP = $("<p></p>");
	introP.addClass("justifyText");
	introP.text(problem.introduction);
	return introP;
    }

    /*
     * Make the welcoming text for the problem.
     */
    function welcome() {
	var welcomeDiv = $("<div></div>");
	welcomeDiv.addClass("centerText");
	welcomeDiv.append(boldTextElement("Welcome to the "));

	var problemName = boldTextElement(problem.name);
	problemName.addClass("largeBold emphasized");
	welcomeDiv.append(problemName);

	welcomeDiv.append(boldTextElement(" Problem"));
	return welcomeDiv;
    }

    /*
     * Make and return a text element with a large bold font 
     * for a given string.
     */
    function boldTextElement(str) {
	var e = $("<span></span>");
	e.text(str);
	e.addClass("largeBold");
	return e;
    }
    
    
    function congratulate(){
        //hide the chooser element and display
        $("#chooser").hide();
        
        //make congrats panel
        congratsPanel = $("<div></div>");
        congratsPanel.addClass("congratsPanel");
        //make FinalState view
        finalState = $("<div></div>");
        state = problem.currentState;
	stateCanvas = state.makeCanvas();
        finalState.append($(stateCanvas));
        finalState.addClass("middle centerText");
        
        grats = $("<div></div>").text("Congratulations!");
        grats.addClass("medium pad");
        
        solved = $("<div></div>").text("You solved the problem using");
        solved.addClass("small");
        
        numMoves = $("<div></div>").text(assistant.moveCount.toString());
        numMoves.addClass("large");
        
        moves = $("<div></div>").text("moves");
        moves.addClass("small");
        
        cont = $("<button></button>").text("Continue");
        cont.addClass("navButton");
        cont.click(contin());
        
        quit = $("<button></button>").text("Quit");
        quit.addClass("navButton");
        quit.click(quitB());
        
        //append objects to congratsPanel
        congratsPanel.append(finalState);
        congratsPanel.append(grats);
        congratsPanel.append(solved);
        congratsPanel.append(numMoves);
        congratsPanel.append(moves);
        congratsPanel.append(cont);
        congratsPanel.append(quit);
        //show congratsPanel
        $("body").append(congratsPanel);
    }    
    
    function contin(){
        
    }
    
    function quitB(){
        
    }
}
