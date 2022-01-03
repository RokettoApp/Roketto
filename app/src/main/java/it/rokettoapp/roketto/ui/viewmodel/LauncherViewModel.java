package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.repository.LauncherRepository;

public class LauncherViewModel extends AndroidViewModel {

    private final LauncherRepository mLauncherRepository;
    private MutableLiveData<List<Launcher>> mLauncherListLivedata;

    public LauncherViewModel(@NonNull Application application) {

        super(application);
        mLauncherRepository = new LauncherRepository(application);
        mLauncherListLivedata = mLauncherRepository.getLiveData();
    }

    public MutableLiveData<List<Launcher>> getLiveData() {

        return mLauncherListLivedata;
    }

    public void getLaunchers(Boolean isConnected) {

        mLauncherRepository.getLauncherList(isConnected);
    }

    public void getLauncherById(int id) {

        mLauncherRepository.getLauncherById(id);
    }
}
