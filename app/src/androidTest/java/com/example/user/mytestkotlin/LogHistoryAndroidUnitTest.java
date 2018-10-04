package com.example.user.mytestkotlin;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LogHistoryAndroidUnitTest {

    public static final String TEST_STRING = "This is a string";
    public static final long TEST_LONG = 12345678L;
    private LogHistory mLogHistory;
    @Before
    public void createLogHistory()
    {
        mLogHistory=new LogHistory ();
    }
    @Test
    public void logHistory_ParcelableWriteRead()
    {
        mLogHistory.addEntry(TEST_STRING,TEST_LONG);

        //Write the data
        Parcel parcel =Parcel.obtain();
        mLogHistory.writeToParcel(parcel,mLogHistory.describeContents());
        //After you're done with writing, you need to reset the parcel for reading/
        parcel.setDataPosition(0);
        //Read the data.
        LogHistory createdFromParcel=LogHistory.CREATOR.createFromParcel(parcel);
        List<Pair<String,Long>> createdFromParcelData=createdFromParcel.getmData();
        //Verify that the received data is correct
        assertThat(createdFromParcelData.size(), is(1));
        assertThat(createdFromParcelData.get(0).first,is(TEST_STRING));
        assertThat(createdFromParcelData.get(0).second,is(TEST_LONG));

    }




}

