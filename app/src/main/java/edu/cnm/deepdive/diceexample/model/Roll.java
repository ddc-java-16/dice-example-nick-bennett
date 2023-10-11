package edu.cnm.deepdive.diceexample.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

public class Roll {

  @Expose
  private int value;

  @NonNull
  @NotNull
  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
