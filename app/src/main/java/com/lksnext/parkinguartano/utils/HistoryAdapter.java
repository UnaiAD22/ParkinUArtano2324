package com.lksnext.parkinguartano.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lksnext.parkinguartano.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItem> historyItemList;

    public HistoryAdapter(List<HistoryItem> historyItemList) {
        this.historyItemList = historyItemList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem item = historyItemList.get(position);

        holder.iconUser.setImageResource(item.getIconUser());
        holder.textUser.setText(item.getUserName());

        holder.iconCalendar.setImageResource(item.getIconCalendar());
        holder.textCalendar.setText(item.getCalendarDate());

        holder.iconClock.setImageResource(item.getIconClock());
        holder.textClock.setText(item.getClockTime());

        holder.iconCar.setImageResource(item.getIconCar());
        holder.textCar.setText(item.getCarType());

        holder.iconLocation.setImageResource(item.getIconLocation());
        holder.textLocationId.setText(item.getLocationId());
    }

    @Override
    public int getItemCount() {
        return historyItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconUser, iconCalendar, iconClock, iconCar, iconLocation;
        TextView textUser, textCalendar, textClock, textCar, textLocationId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconUser = itemView.findViewById(R.id.user_icon);
            textUser = itemView.findViewById(R.id.user_name);
            iconCalendar = itemView.findViewById(R.id.calendar_icon);
            textCalendar = itemView.findViewById(R.id.calendar_text);
            iconClock = itemView.findViewById(R.id.clock_icon);
            textClock = itemView.findViewById(R.id.clock_text);
            iconCar = itemView.findViewById(R.id.car_icon);
            textCar = itemView.findViewById(R.id.car_type_text);
            iconLocation = itemView.findViewById(R.id.location_icon);
            textLocationId = itemView.findViewById(R.id.parking_id_text);
        }
    }
}
