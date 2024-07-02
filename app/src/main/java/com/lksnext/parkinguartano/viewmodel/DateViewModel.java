package com.lksnext.parkinguartano.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DateViewModel extends ViewModel {
    // Aquí puedes declarar los LiveData y métodos necesarios para la vista main
    private final MutableLiveData<String> selectedDate = new MutableLiveData<>();

    // Método para obtener la fecha seleccionada
    public LiveData<String> getSelectedDate() {
        return selectedDate;
    }

    // Método para actualizar la fecha seleccionada
    public void setSelectedDate(String date) {
        selectedDate.setValue(date);
    }
}
