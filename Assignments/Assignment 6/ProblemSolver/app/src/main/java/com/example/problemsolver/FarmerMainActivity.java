package com.example.problemsolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import domains.farmer.FarmerProblem;

public class FarmerMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FarmerProblem farmerProblem = new FarmerProblem();
        String initialState = farmerProblem.getInitialState().toString();
        String finalState = farmerProblem.getFinalState().toString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_main);

        TextView initState = findViewById(R.id.farmerCurrentState);
        initState.setText(initialState);
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView finalStateView = findViewById(R.id.farmerGoalState);
        finalStateView.setText(finalState);
        finalStateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


    }
}