package com.example.maximeweekhout.madlabs;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by MaximeWeekhout on 18-09-16.
 */
public class RetainedFragment extends Fragment {

    // data object we want to retain
    private Story data;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    public void setData(Story data) {
        this.data = data;
    }

    public Story getData() {
        return data;
    }
}
