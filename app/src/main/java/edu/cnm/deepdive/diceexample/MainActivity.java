package edu.cnm.deepdive.diceexample;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    OnClickListener listener = (viewClicked) -> Log.d(getClass().getSimpleName(), ((Button) viewClicked).getText() + " clicked");
    findViewById(R.id.first).setOnClickListener(listener);
    findViewById(R.id.second).setOnClickListener(listener);
    findViewById(R.id.third).setOnClickListener(listener);
  }

}