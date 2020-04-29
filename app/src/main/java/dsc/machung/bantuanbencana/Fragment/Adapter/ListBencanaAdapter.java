package dsc.machung.bantuanbencana.Fragment.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import dsc.machung.bantuanbencana.Fragment.DetailBencanaFragment;
import dsc.machung.bantuanbencana.Model.DisasterModel;
import dsc.machung.bantuanbencana.R;

public class ListBencanaAdapter extends RecyclerView.Adapter<ListBencanaAdapter.ViewHolder> {

    private ArrayList<DisasterModel> dataList;
    private Activity activity;

    public ListBencanaAdapter(Activity activity, ArrayList<DisasterModel> dataList){
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bencana, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        byte[] decodedString = Base64.decode(dataList.get(position).getDisasterPhoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        holder.disasterImage.setImageBitmap(decodedByte);
        holder.disasterType.setText(dataList.get(position).getDisasterType());
        holder.location.setText(dataList.get(position).getLocation());
        holder.title.setText(dataList.get(position).getDisasterTitle());
        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new DetailBencanaFragment(dataList.get(position));
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_container, fragment)
                        .addToBackStack(fragment.getClass().getName())
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null!= dataList) ? dataList.size():0;
    }

    public ArrayList<DisasterModel> getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList<DisasterModel> dataList) {
        this.dataList = dataList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView disasterImage;
        TextView title;
        TextView location;
        TextView disasterType;
        public MaterialCardView cvMain;
        public ViewHolder( View itemView) {
            super(itemView);
            disasterImage = itemView.findViewById(R.id.disasterPhoto);
            title = itemView.findViewById(R.id.disasterTitle);
            location = itemView.findViewById(R.id.location);
            disasterType = itemView.findViewById(R.id.disasterType);
            cvMain = itemView.findViewById(R.id.cardView);
        }
    }
}
