package dsc.machung.bantuanbencana.Fragment;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dsc.machung.bantuanbencana.Model.DetailDisasterModel;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Task.DetailBencanaTask;
import dsc.machung.bantuanbencana.Task.DetailLoadDone;
import dsc.machung.bantuanbencana.Util.Global;
import dsc.machung.bantuanbencana.apimodel.DetailBencana.DetailBencanaRequestModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBencanaFragment extends Fragment implements DetailLoadDone, View.OnClickListener {

    DisasterModel model;
    public static ArrayList<DetailDisasterModel> dataList;

    TextView tvJmlBaju, tvButuhJmlBaju;
    TextView tvJmlMakan, tvButuhJmlMakan;
    TextView tvButuhJmlUang;
    TextView tvJmlUang;
    ProgressBar pbJmlUang;

    CardView cardClothes, cardFood, cardMoney;

    TextView title;

    TextView tvTglBencana;
    TextView tvDescription;

    Button btnDonateBaju, btnDonateMakanan, btnDonateUang;
    public DetailBencanaFragment() {
        // Required empty public constructor
    }

    public DetailBencanaFragment(DisasterModel model) {
        this.model = model;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_detail_bencana, container, false);

        cardClothes = view.findViewById(R.id.cardClothes);
        cardFood = view.findViewById(R.id.cardFood);
        cardMoney = view.findViewById(R.id.cardMoney);

        title = view.findViewById(R.id.name);
        tvTglBencana = view.findViewById(R.id.tgl_bencana);
        tvDescription = view.findViewById(R.id.description);

        title.setText(model.getDisasterTitle());
        tvTglBencana.setText(model.getDisasterDate());
        tvDescription.setText(model.getDescription());

        tvJmlBaju = view.findViewById(R.id.jml_baju);
        tvButuhJmlBaju = view.findViewById(R.id.jml_butuhbaju);

        tvJmlMakan = view.findViewById(R.id.jml_makanan);
        tvButuhJmlMakan = view.findViewById(R.id.jml_butuhmakanan);

        pbJmlUang = view.findViewById(R.id.pb_jml_uang);
        tvJmlUang = view.findViewById(R.id.jml_uang);
        tvButuhJmlUang = view.findViewById(R.id.jml_butuhuang);

        btnDonateBaju = view.findViewById(R.id.donateBaju);
        btnDonateMakanan = view.findViewById(R.id.donateMakanan);
        btnDonateUang = view.findViewById(R.id.donateMoney);

        btnDonateUang.setOnClickListener(this);
        btnDonateMakanan.setOnClickListener(this);
        btnDonateBaju.setOnClickListener(this);

        DetailBencanaRequestModel request = new DetailBencanaRequestModel(model.getId());
        DetailBencanaTask task = new DetailBencanaTask(getContext(), request, this);
        task.execute();
        return view;
    }

    @Override
    public void load() {
        for(DetailDisasterModel model: dataList){
            if(Global.CLOTHES.equalsIgnoreCase(model.getType())){
                cardClothes.setVisibility(View.VISIBLE);
                tvJmlBaju.setText(model.getColected());
                tvButuhJmlBaju.setText(model.getNeed());
            } else if(Global.FOOD.equalsIgnoreCase(model.getType())){
                cardFood.setVisibility(View.VISIBLE);
                tvJmlMakan.setText(model.getColected());
                tvButuhJmlMakan.setText(model.getNeed());
            } else if(Global.MONEY.equalsIgnoreCase(model.getType())){
                int progress = Integer.parseInt(model.getNeed())/Integer.parseInt(model.getColected());
                cardMoney.setVisibility(View.VISIBLE);
                tvButuhJmlUang.setText(model.getNeed());
                tvJmlUang.setText(progress+"");
                pbJmlUang.setProgress(progress);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.donateBaju:
                Toast.makeText(getContext(),"Clothes donation has made!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.donateMakanan:
                Toast.makeText(getContext(),"Food donation has made!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.donateMoney:
                Toast.makeText(getContext(),"Money donation has made!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
