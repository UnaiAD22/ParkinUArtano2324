package com.lksnext.parkinguartano.utils;

// HistoryItem.java
public class HistoryItem {

    private int iconUser;
    private String userName;
    private int iconCalendar;
    private String calendarDate;
    private int iconClock;
    private String clockTime;
    private int iconCar;
    private String carType;
    private int iconLocation;
    private String locationId;

    public HistoryItem(int iconUser, String userName, int iconCalendar, String calendarDate, int iconClock, String clockTime, int iconCar, String carType, int iconLocation, String locationId) {
        this.iconUser = iconUser;
        this.userName = userName;
        this.iconCalendar = iconCalendar;
        this.calendarDate = calendarDate;
        this.iconClock = iconClock;
        this.clockTime = clockTime;
        this.iconCar = iconCar;
        this.carType = carType;
        this.iconLocation = iconLocation;
        this.locationId = locationId;
    }

    // Getters para todos los atributos
    public int getIconUser() {
        return iconUser;
    }

    public String getUserName() {
        return userName;
    }

    public int getIconCalendar() {
        return iconCalendar;
    }

    public String getCalendarDate() {
        return calendarDate;
    }

    public int getIconClock() {
        return iconClock;
    }

    public String getClockTime() {
        return clockTime;
    }

    public int getIconCar() {
        return iconCar;
    }

    public String getCarType() {
        return carType;
    }

    public int getIconLocation() {
        return iconLocation;
    }

    public String getLocationId() {
        return locationId;
    }
}

