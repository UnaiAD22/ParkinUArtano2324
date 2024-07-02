package com.lksnext.parkinguartano.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lksnext.parkinguartano.R;
import com.lksnext.parkinguartano.utils.HistoryItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel {
    private MutableLiveData<List<HistoryItem>> historyItemList = new MutableLiveData<>();

    public LiveData<List<HistoryItem>> getHistoryItemList() {
        return historyItemList;
    }

    public void loadHistoryItems() {
        List<HistoryItem> items = new ArrayList<>();
        items.add(new HistoryItem(R.drawable.ic_user, "Usuario 1", R.drawable.ic_calendar, "01/07/2024", R.drawable.ic_clock, "10:00 - 18:00", R.drawable.ic_car_normal, "Minusválido", R.drawable.ic_location, "1"));
        items.add(new HistoryItem(R.drawable.ic_user, "Usuario 1", R.drawable.ic_calendar, "29/06/2022", R.drawable.ic_clock, "23:00 - 1:45", R.drawable.ic_car_normal, "Eléctrico", R.drawable.ic_location, "105"));
        items.add(new HistoryItem(R.drawable.ic_user, "Usuario 1", R.drawable.ic_calendar, "01/07/2020", R.drawable.ic_clock, "11:00 - 23:30", R.drawable.ic_car_normal, "Eléctrico", R.drawable.ic_location, "1"));
        items.add(new HistoryItem(R.drawable.ic_user, "Usuario 1", R.drawable.ic_calendar, "29/06/2017", R.drawable.ic_clock, "11:00 - 23:30", R.drawable.ic_car_normal, "Normal", R.drawable.ic_location, "105"));

        System.out.println(items.size());

        historyItemList.setValue(items);
    }
}
