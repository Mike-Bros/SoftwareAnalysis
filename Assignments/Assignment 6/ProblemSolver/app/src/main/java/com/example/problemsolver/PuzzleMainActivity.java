package com.example.problemsolver;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import domains.puzzle.PuzzleProblem;
import framework.solution.SolvingAssistant;

public class PuzzleMainActivity extends AppCompatActivity {
    private PuzzleProblem puzzleProblem;
    private SolvingAssistant solver;
    private List<String> moves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        puzzleProblem = new PuzzleProblem();
        solver = new SolvingAssistant(puzzleProblem);
        String initialState = puzzleProblem.getInitialState().toString();
        String finalState = puzzleProblem.getFinalState().toString();
        moves = puzzleProblem.getMover().getMoveNames();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_main);

        TextView initState = findViewById(R.id.puzzleCurrentState);
        initState.setText(initialState);
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView finalStateView = findViewById(R.id.puzzleGoalState);
        finalStateView.setText(finalState);
        finalStateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView moves = findViewById(R.id.movesView);
        moves.setText(String.valueOf(solver.getMoveCount()));
        moves.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
    public void trySlide1(View view) {
        moveHelper(moves.get(0));
    }

    public void trySlide2(View view) {
        moveHelper(moves.get(1));
    }

    public void trySlide3(View view) {
        moveHelper(moves.get(2));
    }

    public void trySlide4(View view) {
        moveHelper(moves.get(3));
    }

    public void trySlide5(View view) {
        moveHelper(moves.get(4));
    }

    public void trySlide6(View view) {
        moveHelper(moves.get(5));
    }

    public void trySlide7(View view) {
        moveHelper(moves.get(6));
    }

    public void trySlide8(View view) {
        moveHelper(moves.get(7));
    }

    private void moveHelper(String move){
        solver.tryMove(move);
        if(solver.isMoveLegal()){
            showLegal();
            updateDisplay();
            if(solver.isProblemSolved()){
                showSuccess();
            }
        }else{
            showIllegal();
        }
    }

    private void showIllegal(){
        TextView feed = findViewById(R.id.feedback);
        feed.setText(getString(R.string.illegal));
        feed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void showLegal(){
        TextView feed = findViewById(R.id.feedback);
        feed.setText(getString(R.string.blank));
        feed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void showSuccess(){
        TextView feed = findViewById(R.id.feedback);
        feed.setText(getString(R.string.congrats));
        feed.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    private void updateDisplay(){
        TextView initState = findViewById(R.id.puzzleCurrentState);
        initState.setText(puzzleProblem.getCurrentState().toString());
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView moves = findViewById(R.id.movesView);
        moves.setText(String.valueOf(solver.getMoveCount()));
        moves.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void reset(View view) {
        puzzleProblem = new PuzzleProblem();
        solver = new SolvingAssistant(puzzleProblem);
        updateDisplay();
        showLegal();
        setButtons(true);
        TextView stats = findViewById(R.id.stats);
        stats.setText(" ");
        findViewById(R.id.next).setEnabled(false);

    }


    public void solve(View view) {
        //minimum length solution found

        //display solution statistics
        displayStats();
        setButtons(false);
        //benchmark spinner disabled
        findViewById(R.id.next).setEnabled(true);
    }

    private void displayStats() {
        //solution length
        //solution time in milliseconds
        //number of queue operations during solution
        //max queue size
        String statistics = "Statistics\n----------------------------\n";
        statistics += "Solution length "+ ""+"\n";
        statistics += "Solution time "+ ""+"\n";
        statistics += "Num of queue ops "+ ""+"\n";
        statistics += "Max queue size "+ ""+"\n";
        TextView stats = findViewById(R.id.stats);
        stats.setText(statistics);
    }
    private void setButtons(Boolean bool){
        findViewById(R.id.solve).setEnabled(bool);
        findViewById(R.id.slideTile1).setEnabled(bool);
        findViewById(R.id.slideTile2).setEnabled(bool);
        findViewById(R.id.slideTile3).setEnabled(bool);
        findViewById(R.id.slideTile4).setEnabled(bool);
        findViewById(R.id.slideTile5).setEnabled(bool);
        findViewById(R.id.slideTile6).setEnabled(bool);
        findViewById(R.id.slideTile7).setEnabled(bool);
        findViewById(R.id.slideTile8).setEnabled(bool);
    }

    public void next(View view) {
        //do next move
        updateDisplay();
        if(solver.isProblemSolved()){
            showSuccess();
            findViewById(R.id.next).setEnabled(false);
        }
    }

}