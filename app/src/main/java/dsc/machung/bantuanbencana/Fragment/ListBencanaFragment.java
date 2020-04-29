package dsc.machung.bantuanbencana.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dsc.machung.bantuanbencana.Fragment.Adapter.ListBencanaAdapter;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.R;
import dsc.machung.bantuanbencana.Task.ListBencanaTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBencanaFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListBencanaAdapter adapter;
    private ArrayList<DisasterModel> dataList;

    public ListBencanaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_bencana, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new ListBencanaAdapter(getActivity(), dataList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext() ,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
        ListBencanaTask task = new ListBencanaTask(getContext(), dataList, adapter);
        task.execute();
        return  view;
    }


}
