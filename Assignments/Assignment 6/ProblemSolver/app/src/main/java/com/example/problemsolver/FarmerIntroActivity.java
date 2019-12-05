package com.example.problemsolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import domains.farmer.FarmerProblem;

public class FarmerIntroActivity extends AppCompatActivity {
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


    public void onFarmerBeginClick(View view) {
        startActivity(new Intent(FarmerIntroActivity.this, FarmerMainActivity.class));
    }
}
