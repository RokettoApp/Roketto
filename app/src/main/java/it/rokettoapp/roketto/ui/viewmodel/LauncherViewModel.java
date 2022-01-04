package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.Launcher;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.LauncherRepository;

public class LauncherViewModel extends AndroidViewModel {

    private final LauncherRepository mLauncherRepository;
    private MutableLiveData<ResponseList<Launcher>> mLauncherListLivedata;

    public LauncherViewModel(@NonNull Application application) {

        super(application);
        mLauncherRepository = new LauncherRepository(application);
        mLauncherListLivedata = mLauncherRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Launcher>> getLiveData() {

        return mLauncherListLivedata;
    }

    public void getLaunchers(Boolean isConnected) {

        mLauncherRepository.getLauncherList(isConnected);
    }

    public void getLauncherById(int id) {

        mLauncherRepository.getLauncherById(id);
    }
}
