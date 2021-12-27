package it.rokettoapp.roketto.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.rokettoapp.roketto.R;
import it.rokettoapp.roketto.util.CSVCountries;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        CSVCountries mTest = CSVCountries.getInstanceCountry();
        mTest.readFromCSV(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                return true;
            }
        });
    }
}