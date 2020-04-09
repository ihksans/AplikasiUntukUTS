package com.example.aplikasiuntukuts.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CheeseViewModel extends AndroidViewModel{
    private WordRepository mRepository;

    private LiveData<List<Cheese>> mAllWords;


    public CheeseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }
    public LiveData<List<Cheese>> getAllWords() { return mAllWords; }

    public void insert(Cheese word) { mRepository.insert(word); }
}
