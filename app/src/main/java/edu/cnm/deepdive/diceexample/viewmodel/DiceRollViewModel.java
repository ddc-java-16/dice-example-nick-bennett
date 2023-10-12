package edu.cnm.deepdive.diceexample.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.diceexample.model.Roll;
import edu.cnm.deepdive.diceexample.service.DiceRollRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import org.jetbrains.annotations.NotNull;

public class DiceRollViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  private final MutableLiveData<Roll[]> diceRoll;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final DiceRollRepository repository;

  public DiceRollViewModel(@NotNull Application application) {
    super(application);
    diceRoll = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    repository = new DiceRollRepository();
  }

  public LiveData<Roll[]> getDiceRoll() {
    return diceRoll;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public void rollDice(int numberOfDice, int numberOfSides) {
    throwable.postValue(null);
    Disposable disposable = repository.rollDice(numberOfDice, numberOfSides)
        .subscribe(
            diceRoll::postValue,
            throwable::postValue
        );
    pending.add(disposable);
  }

  @Override
  public void onStop(@NotNull LifecycleOwner owner) {
    pending.clear();
    DefaultLifecycleObserver.super.onStop(owner);
  }

}
