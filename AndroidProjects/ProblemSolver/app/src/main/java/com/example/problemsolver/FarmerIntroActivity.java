package com.example.problemsolver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import domains.farmer.FarmerProblem;

public class FarmerIntroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FarmerProblem farmerProblem = new FarmerProblem();
        String initialState = farmerProblem.getInitialState().toString();
        String finalState = farmerProblem.getFinalState().toString();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_intro);

        TextView initState = findViewById(R.id.farmerInitState);
        initState.setText(initialState);
        initState.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView finalStateView = findViewById(R.id.farmerFinalState);
        finalStateView.setText(finalState);
        finalStateView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
