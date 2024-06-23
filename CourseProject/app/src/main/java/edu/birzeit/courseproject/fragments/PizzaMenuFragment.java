package edu.birzeit.courseproject.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.models.PizzaTypeResponse;

public class PizzaMenuFragment extends Fragment {

    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_menu, container, false);
        listView = view.findViewById(R.id.listViewPizza);

        return view;
    }
}