package edu.cnm.deepdive.diceexample.service;

import edu.cnm.deepdive.diceexample.model.Roll;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.stream.IntStream;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;

public class DiceRollRepository {

  private final UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

  public Single<Roll[]> rollDice(int numberOfDice, int numberOfSides) {
    return Single.fromSupplier(() -> IntStream.generate(() -> 1 + rng.nextInt(numberOfSides))
            .limit(numberOfDice)
            .mapToObj(Roll::new)
            .toArray(Roll[]::new))
        .subscribeOn(Schedulers.computation());
  }

}
