package com.lqt.duynguyenhairsalon.Model;

public class BookingTime {
    private String mTime;
    private boolean isSelected;
    private boolean isSelecting;

    public BookingTime(String mTime, boolean isSelected) {
        this.mTime = mTime;
        this.isSelected = isSelected;
        this.isSelecting = false;
    }

    public boolean isSelecting() {
        return isSelecting;
    }

    public void setSelecting(boolean selecting) {
        isSelecting = selecting;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
