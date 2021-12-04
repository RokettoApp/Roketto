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
    }

    public MutableLiveData<List<Program>> getPrograms() {

        if (mProgramListLivedata == null) fetchPrograms();
        return mProgramListLivedata;
    }

    private void fetchPrograms() {

        mProgramListLivedata = mProgramRepository.getProgramList();
    }

    public void refreshPrograms() {

        mProgramRepository.refreshPrograms();
    }
}
