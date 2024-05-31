package com.raulgarcan.wikitarkov.recyclers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.activities.AmmoDetailsActivity;
import com.raulgarcan.wikitarkov.pojo.Ammo;

import java.util.ArrayList;

public class AmmoAdapter extends RecyclerView.Adapter<AmmoAdapter.ViewHolder> {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    private ArrayList<Ammo> ammoList;
    private Activity activity;
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
            cvAmmoItem = view.findViewById(R.id.cv_ammo_item);
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     */
    public AmmoAdapter(ArrayList<Ammo> ammoList, Activity activity) {
        this.ammoList = ammoList;
        this.activity = activity;
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

        viewHolder.tvAmmoName.setText(ammoList.get(position).getDisplayCaliber().replaceAll("Caliber","")+" "+ammoList.get(position).getShortName());
        viewHolder.tvAmmoDmg.setText("Dmg: "+ammoList.get(position).getDamage());
        viewHolder.tvAmmoPen.setText("Pen: "+ammoList.get(position).getPenetrationPower());
        viewHolder.tvAmmoPenTier1.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier1()));
        viewHolder.tvAmmoPenTier2.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier2()));
        viewHolder.tvAmmoPenTier3.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier3()));
        viewHolder.tvAmmoPenTier4.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier4()));
        viewHolder.tvAmmoPenTier5.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier5()));
        viewHolder.tvAmmoPenTier6.setText(String.valueOf(ammoList.get(position).getPenPerTier().getTier6()));

        setColorPenTiers(viewHolder.tvAmmoPenTier1,ammoList.get(position).getPenPerTier().getTier1());
        setColorPenTiers(viewHolder.tvAmmoPenTier2,ammoList.get(position).getPenPerTier().getTier2());
        setColorPenTiers(viewHolder.tvAmmoPenTier3,ammoList.get(position).getPenPerTier().getTier3());
        setColorPenTiers(viewHolder.tvAmmoPenTier4,ammoList.get(position).getPenPerTier().getTier4());
        setColorPenTiers(viewHolder.tvAmmoPenTier5,ammoList.get(position).getPenPerTier().getTier5());
        setColorPenTiers(viewHolder.tvAmmoPenTier6,ammoList.get(position).getPenPerTier().getTier6());

        viewHolder.cvAmmoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, AmmoDetailsActivity.class);
                i.putExtra("ammo",ammoList.get(viewHolder.getAdapterPosition()));
                activity.startActivity(i);
            }
        });
    }
    private void setColorPenTiers(TextView tv, int pen){
        switch (pen){
            case 0:
                tv.setBackgroundResource(R.color.pen0);
                break;
            case 1:
                tv.setBackgroundResource(R.color.pen1);
                break;
            case 2:
                tv.setBackgroundResource(R.color.pen2);
                break;
            case 3:
                tv.setBackgroundResource(R.color.pen3);
                break;
            case 4:
                tv.setBackgroundResource(R.color.pen4);
                break;
            case 5:
                tv.setBackgroundResource(R.color.pen5);
                break;
            case 6:
                tv.setBackgroundResource(R.color.pen6);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ammoList.size();
    }
}
