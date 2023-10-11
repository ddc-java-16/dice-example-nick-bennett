package edu.cnm.deepdive.diceexample;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.diceexample.databinding.ActivityMainBinding;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

  private ActivityMainBinding binding;
  private DiceRollViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    OnClickListener listener = (viewClicked) -> Log.d(getClass().getSimpleName(), ((Button) viewClicked).getText() + " clicked");
    binding.first.setOnClickListener(listener);
    binding.second.setOnClickListener(listener);
    binding.third.setOnClickListener((v) -> viewModel.rollDice(2, 6));
    setupViewModel();
  }

  private void setupViewModel() {
    viewModel = new ViewModelProvider(this).get(DiceRollViewModel.class);
    viewModel
        .getDiceRoll()
        .observe(this, (rolls) -> Log.d(getClass().getSimpleName(), Arrays.toString(rolls)));
    viewModel
        .getThrowable()
        .observe(this, (throwable) -> {
          if (throwable != null) {
            Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
          }
        });
  }

}