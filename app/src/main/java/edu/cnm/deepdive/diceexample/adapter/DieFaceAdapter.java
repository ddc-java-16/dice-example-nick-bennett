package edu.cnm.deepdive.diceexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.diceexample.model.Roll;

public class DieFaceAdapter extends ArrayAdapter<Roll> {

  private final Roll[] rolls;

  public DieFaceAdapter(@NonNull Context context, int resource, @NonNull Roll[] rolls) {
    super(context, resource, rolls);
    this.rolls = rolls;
  }

  @Override
  public int getCount() {
    return rolls.length;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    // TODO: 10/12/23 Inflate a layout containing an ImageView, and set the source of the image view
    //  to the drawable corresponding to rolls[position].getValue().
    return super.getView(position, convertView, parent);
  }

}
