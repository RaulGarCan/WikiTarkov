package com.raulgarcan.wikitarkov.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.Ammo;

import java.util.ArrayList;

public class AmmoAdapter extends RecyclerView.Adapter<AmmoAdapter.ViewHolder> {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    private ArrayList<Ammo> ammoList;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAmmoName, tvAmmoPen, tvAmmoDmg, tvAmmoPenTier1, tvAmmoPenTier2, tvAmmoPenTier3, tvAmmoPenTier4, tvAmmoPenTier5, tvAmmoPenTier6;
        private CardView cvAmmoItem;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvAmmoName = view.findViewById(R.id.tv_ammo_name);
            tvAmmoPen = view.findViewById(R.id.tv_ammo_pen);
            tvAmmoDmg = view.findViewById(R.id.tv_ammo_dmg);
            tvAmmoPenTier1 = view.findViewById(R.id.tv_ammo_pen_tier1);
            tvAmmoPenTier2 = view.findViewById(R.id.tv_ammo_pen_tier2);
            tvAmmoPenTier3 = view.findViewById(R.id.tv_ammo_pen_tier3);
            tvAmmoPenTier4 = view.findViewById(R.id.tv_ammo_pen_tier4);
            tvAmmoPenTier5 = view.findViewById(R.id.tv_ammo_pen_tier5);
            tvAmmoPenTier6 = view.findViewById(R.id.tv_ammo_pen_tier6);
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     */
    public AmmoAdapter(ArrayList<Ammo> ammoList) {
        this.ammoList = ammoList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_ammo_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.tvAmmoName.setText(ammoList.get(position).getCaliber().replaceAll("Caliber","")+" "+ammoList.get(position).getShortName());
        viewHolder.tvAmmoDmg.setText("Dmg: "+ammoList.get(position).getDamage());
        viewHolder.tvAmmoPen.setText("Pen: "+ammoList.get(position).getPenetrationPower());
        viewHolder.tvAmmoPenTier1.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier1()));
        viewHolder.tvAmmoPenTier2.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier2()));
        viewHolder.tvAmmoPenTier3.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier3()));
        viewHolder.tvAmmoPenTier4.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier4()));
        viewHolder.tvAmmoPenTier5.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier5()));
        viewHolder.tvAmmoPenTier6.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier6()));
    }

    @Override
    public int getItemCount() {
        return ammoList.size();
    }
}
