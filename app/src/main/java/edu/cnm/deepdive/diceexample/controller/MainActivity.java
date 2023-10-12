package edu.cnm.deepdive.diceexample.controller;

import android.util.Log;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.diceexample.databinding.ActivityMainBinding;
import edu.cnm.deepdive.diceexample.model.Roll;
import edu.cnm.deepdive.diceexample.viewmodel.DiceRollViewModel;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private DiceRollViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.roll.setOnClickListener((v) -> viewModel.rollDice(20, 60));
    setupViewModel();
  }

  private void setupViewModel() {
    viewModel = new ViewModelProvider(this).get(DiceRollViewModel.class);
    viewModel
        .getDiceRoll()
        .observe(this, (rolls) -> {
          ArrayAdapter<Roll> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rolls);
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