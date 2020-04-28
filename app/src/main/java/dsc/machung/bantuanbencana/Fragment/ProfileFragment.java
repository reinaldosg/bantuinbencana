package dsc.machung.bantuanbencana.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Util.Global;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    TextView tvFullname;
    TextView tvPhoneNumber;
    TextView tvAddress;
    TextView tvAnon;
    TextView tvTotalDonation;
    TextView tvEmail;
    LinearLayout line4;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvFullname = view.findViewById(R.id.profileName);
        tvPhoneNumber = view.findViewById(R.id.profilePhoneNumber);
        tvAddress = view.findViewById(R.id.profileAddress);
        tvAnon = view.findViewById(R.id.profileAnon);
        tvTotalDonation = view.findViewById(R.id.profileDonation);
        tvEmail = view.findViewById(R.id.profileEmail);
        line4 = view.findViewById(R.id.line4);

        bindDataUser();

        return view;
    }

    private void bindDataUser(){
        tvFullname.setText(Global.USER.getName());
        tvPhoneNumber.setText(Global.USER.getTelp());
        tvAddress.setText(Global.USER.getAddress());
        tvTotalDonation.setText(Global.USER.getTotal()+"");
        tvEmail.setText(Global.USER.getEmail());
        if(Global.USER.getAnonymous() == 1){
            tvAnon.setText("Anonymous");
        }else{
            line4.setVisibility(View.GONE);
        }
    }

}
