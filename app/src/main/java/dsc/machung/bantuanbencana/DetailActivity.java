package dsc.machung.bantuanbencana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;

import dsc.machung.bantuanbencana.Fragment.DetailBencanaFragment;

public class DetailActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView jml_uang;
    private ProgressBar pb_jml_uang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        jml_uang = (TextView) findViewById(R.id.jml_uang);
        pb_jml_uang = (ProgressBar) findViewById(R.id.pb_jml_uang);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment fragment;
        fragment = new DetailBencanaFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.commit();
    }

}
