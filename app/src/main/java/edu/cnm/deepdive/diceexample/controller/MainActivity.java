package edu.cnm.deepdive.diceexample.controller;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.diceexample.R;
import edu.cnm.deepdive.diceexample.adapter.RollsAdapter;
import edu.cnm.deepdive.diceexample.databinding.ActivityMainBinding;
import edu.cnm.deepdive.diceexample.viewmodel.DiceRollViewModel;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private DiceRollViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.roll.setOnClickListener((v) -> viewModel.rollDice(50, 6));
    setupViewModel();
  }

  private void setupViewModel() {
    viewModel = new ViewModelProvider(this).get(DiceRollViewModel.class);
    viewModel
        .getDiceRoll()
        .observe(this, (rolls) -> {
          RollsAdapter adapter = new RollsAdapter(this, rolls);
          binding.rollValues.setAdapter(adapter);
        });
    viewModel
        .getThrowable()
        .observe(this, (throwable) -> {
          if (throwable != null) {
            Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
          }
        });
  }

}