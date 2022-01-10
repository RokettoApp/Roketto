package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.model.ResponseList;
import it.rokettoapp.roketto.repository.ProgramRepository;

public class ProgramViewModel extends AndroidViewModel {

    private final ProgramRepository mProgramRepository;
    private MutableLiveData<ResponseList<Program>> mProgramListLivedata;

    public ProgramViewModel(@NonNull Application application) {

        super(application);
        mProgramRepository = new ProgramRepository(application);
        mProgramListLivedata = mProgramRepository.getLiveData();
    }

    public MutableLiveData<ResponseList<Program>> getLiveData() {

        return mProgramListLivedata;
    }

    public void getPrograms(Boolean isConnected) {
        
        mProgramRepository.getProgramList(isConnected);
    }

    public void getProgramById(int id) {

        mProgramRepository.getProgramById(id);
    }

    public void refreshPrograms() {

        mProgramRepository.refreshPrograms();
    }
}
