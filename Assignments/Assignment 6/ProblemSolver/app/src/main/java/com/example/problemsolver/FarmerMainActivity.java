package com.example.problemsolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import domains.farmer.FarmerProblem;
import framework.problem.State;
import framework.solution.SolvingAssistant;

public class FarmerMainActivity extends AppCompatActivity {
    private FarmerProblem farmerProblem;
    private SolvingAssistant solver;
    private List<String> moves;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        farmerProblem = new FarmerProblem();
        solver = new SolvingAssistant(farmerProblem);
        String initialState = farmerProblem.getInitialState().toString();
        String finalState = farmerProblem.getFinalState().toString();
        moves = farmerProblem.getMover().getMoveNames();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);

        TextView initState = findViewById(R.id.farmerCurrentState);
        initState.setText(initialState);
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView finalStateView = findViewById(R.id.farmerGoalState);
        finalStateView.setText(finalState);
        finalStateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView moves = findViewById(R.id.movesView);
        moves.setText(String.valueOf(solver.getMoveCount()));
        moves.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView stats = findViewById(R.id.stats);
        stats.setText("");
    }

    public void tryGoesAlone(View view) {
        moveHelper(moves.get(0));
    }

    public void tryTakesWolf(View view) {
        moveHelper(moves.get(1));
    }

    public void tryTakesGoat(View view) {
        moveHelper(moves.get(2));
    }

    public void tryTakesCabbage(View view) {
        moveHelper(moves.get(3));
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
        TextView initState = findViewById(R.id.farmerCurrentState);
        initState.setText(farmerProblem.getCurrentState().toString());
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView moves = findViewById(R.id.movesView);
        moves.setText(String.valueOf(solver.getMoveCount()));
        moves.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }

    public void reset(View view) {
        farmerProblem = new FarmerProblem();
        solver = new SolvingAssistant(farmerProblem);
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
        findViewById(R.id.goesAlon).setEnabled(bool);
        findViewById(R.id.takesWolf).setEnabled(bool);
        findViewById(R.id.takesGoat).setEnabled(bool);
        findViewById(R.id.takesCabbage).setEnabled(bool);
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