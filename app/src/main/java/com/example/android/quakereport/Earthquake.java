package com.example.android.quakereport;

/**
 * Created by Javeria on 8/2/2017.
 */

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(double magnitude, String location, long date){
        setmMagnitude(magnitude);
        setmLocation(location);
        setmDate(date);
    }


    public double getmMagnitude() {
        return mMagnitude;
    }

    public void setmMagnitude(double mMagnitude) {
        this.mMagnitude = mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public long getmDate() {
        return mDate;
    }

    public void setmDate(long mDate) {
        this.mDate = mDate;
    }
}
