package it.rokettoapp.roketto.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.ui.viewmodel.FavouritesViewModel;
import it.rokettoapp.roketto.util.CSVCountries;
import it.rokettoapp.roketto.util.SharedPreferencesProvider;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mFirebaseAuth = FirebaseAuth.getInstance();

        CSVCountries mTest = CSVCountries.getInstanceCountry();
        mTest.readFromCSV(this);

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            FavouritesViewModel favouritesViewModel = new ViewModelProvider(this).get(FavouritesViewModel.class);
            favouritesViewModel.readFavouriteEvents(firebaseUser.getUid())
                    .observe(this, user -> {
                        if (user != null) {
                            favouritesViewModel.refreshFavouriteEvents(user.getFavouriteEvents());
                        }
                    });
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            Fragment fragment = null;
            switch(item.getItemId()){
                case R.id.itemHome:
                    fragment = new FragmentHome();
                    break;
                case R.id.itemNews:
                    fragment = new FragmentNews();
                    break;
                case R.id.itemFavorites:
                    fragment = new FragmentFavorites();
                    break;
                case R.id.itemSettings:
                    fragment = new FragmentSettings();
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment).commit();
            return true;
        });
    }

    @Override
    public void onStart() {

        super.onStart();
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            SharedPreferencesProvider sharedPreferencesProvider =
                    new SharedPreferencesProvider(getApplication());
            if (!sharedPreferencesProvider.isLoginSkipped()) {
                Intent intent = new Intent(this, AuthenticationActivity.class);
                startActivity(intent);
            }
        }
    }
}