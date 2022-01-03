package it.rokettoapp.roketto.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import it.rokettoapp.roketto.model.Expedition;
import it.rokettoapp.roketto.repository.ExpeditionRepository;

public class ExpeditionViewModel extends AndroidViewModel {

    private final ExpeditionRepository mExpeditionRepository;
    private MutableLiveData<List<Expedition>> mExpeditionListLiveData;

    public ExpeditionViewModel(@NonNull Application application) {

        super(application);
        mExpeditionRepository = new ExpeditionRepository(application);
        mExpeditionListLiveData = mExpeditionRepository.getLiveData();
    }

    public MutableLiveData<List<Expedition>> getLiveData() {

        return mExpeditionListLiveData;
    }

    public void getExpeditions(Boolean isConnected) {

        mExpeditionRepository.getExpeditionList(isConnected);
    }

    public void getExpeditionById(int id) {

        mExpeditionRepository.getExpeditionById(id);
    }
}
