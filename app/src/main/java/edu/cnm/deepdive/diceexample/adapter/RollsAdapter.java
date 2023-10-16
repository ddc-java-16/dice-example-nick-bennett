package edu.cnm.deepdive.diceexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.diceexample.R;
import edu.cnm.deepdive.diceexample.model.Roll;

public class RollsAdapter extends ArrayAdapter<Roll> {

  private final Roll[] rolls;
  private final LayoutInflater inflater;
  private final String dieFaceFormat;

  public RollsAdapter(@NonNull Context context, int resource, @NonNull Roll[] rolls) {
    super(context, resource, rolls);
    this.rolls = rolls;
    inflater = LayoutInflater.from(context);
    dieFaceFormat = context.getString(R.string.die_face_format);
  }

  @Override
  public int getCount() {
    return rolls.length;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ImageView face = (ImageView) ((convertView != null)
        ? convertView
        : inflater.inflate(R.layout.item_roll_layout, parent, false));
    int level = rolls[position].getValue();
    face.getDrawable().setLevel(level);
    face.setContentDescription(String.format(dieFaceFormat, level));
    return face;
  }

}
