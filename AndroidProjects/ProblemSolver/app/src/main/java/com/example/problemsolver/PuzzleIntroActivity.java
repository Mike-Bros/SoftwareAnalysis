package com.example.problemsolver;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import domains.puzzle.PuzzleProblem;


public class PuzzleIntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PuzzleProblem puzzleProblem = new PuzzleProblem();
        String initialState = puzzleProblem.getInitialState().toString();
        String finalState = puzzleProblem.getFinalState().toString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_intro);

        TextView initState = findViewById(R.id.puzzleInitState);
        initState.setText(initialState);
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView finalStateView = findViewById(R.id.puzzleFinalState);
        finalStateView.setText(finalState);
        finalStateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}