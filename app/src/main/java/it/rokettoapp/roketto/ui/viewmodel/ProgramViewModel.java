package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Program;
import it.rokettoapp.roketto.repository.ProgramRepository;

public class ProgramViewModel extends AndroidViewModel {

    private final ProgramRepository mProgramRepository;
    private MutableLiveData<List<Program>> mProgramListLivedata;

    public ProgramViewModel(@NonNull Application application) {

        super(application);
        mProgramRepository = new ProgramRepository(application);
        mProgramListLivedata = mProgramRepository.getLiveData();
    }

    public MutableLiveData<List<Program>> getLiveData() {

        return mProgramListLivedata;
    }

    public void getPrograms() {
        
        mProgramRepository.getProgramList();
    }

    public void getProgramById(int id) {

        mProgramRepository.getProgramById(id);
    }

    public void refreshPrograms() {

        mProgramRepository.refreshPrograms();
    }
}
