package dsc.machung.bantuanbencana;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dsc.machung.bantuanbencana.Fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new ProfileFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Fragment fragment;
//        fragment = new DetailBencanaFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.commit();
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.homeNav:
                fragment = new ProfileFragment();
                break;
            case R.id.historyNav:
//                fragment = new SearchFragment();
                break;
            case R.id.profileNav:
//                fragment = new FavoriteFragment();
                break;
        }
        return loadFragment(fragment);    }
}
