package de.j4rvis.kalendarium.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.j4rvis.kalendarium.R;

/**
 * Created by j4rvis on 4/7/17.
 */

public class OverviewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_fragment, container, false);
    }
}
