package com.example.user.mytestkotlin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class LogHistory implements Parcelable {


    public LogHistory() {
    }

    private final List<Pair<String, Long>> mData = new ArrayList<>();


    public static final Creator<LogHistory> CREATOR = new Creator<LogHistory>() {
        @Override
        public LogHistory createFromParcel(Parcel in) {
            return new LogHistory(in);
        }

        @Override
        public LogHistory[] newArray(int size) {
            return new LogHistory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        String[] texts = new String[mData.size()];
        long[] timestamps = new long[mData.size()];

        //store the data in the arrays.
        for (int i = 0; i < mData.size(); i++) {
            texts[i] = mData.get(i).first;
            timestamps[i] = mData.get(i).second;
        }
        // Write the size of the arrays first.
        out.writeInt(texts.length);
        //Write the two arrays in a specific order.
        out.writeStringArray(texts);
        out.writeLongArray(timestamps);
    }

    public List<Pair<String, Long>> getmData() {
        return mData;
    }

    public void addEntry(String text, long timestamp) {

        mData.add(new Pair<String, Long>(text, timestamp));
    }

    //Constructor used from CREATOR field that unpacks a Parcel.
    private LogHistory(Parcel in) {
        int length = in.readInt();
        String[] texts = new String[length];
        long[] textStamps = new long[length];
        in.readStringArray(texts);
        in.readLongArray(textStamps);
        if(texts.length!= textStamps.length)
        {
            throw new IllegalStateException("Error eading from saved state.");
        }
        //Reset the data container and update the data
        mData.clear();
        for ( int i=0; i<texts.length;i++)
        {
            Pair<String ,Long> pair=new Pair<>(texts [i],textStamps[i]);
            mData.add(pair);
        }
    }
}
