package dsc.machung.bantuanbencana.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dsc.machung.bantuanbencana.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBencanaFragment extends Fragment {


    public DetailBencanaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_bencana, container, false);
    }

}
