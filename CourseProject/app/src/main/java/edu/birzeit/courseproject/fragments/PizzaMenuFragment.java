package edu.birzeit.courseproject.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.birzeit.courseproject.MainActivity;
import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.RegestrationUnitActivity;
import edu.birzeit.courseproject.models.PizzaTypeResponse;
import edu.birzeit.courseproject.network.ApiService;
import edu.birzeit.courseproject.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzaMenuFragment extends Fragment {
    private static final String TAG = "PizzaMenuFragment";
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_menu, container, false);
        listView = view.findViewById(R.id.listViewPizza);

        if (MainActivity.pizzaTypesCache != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, MainActivity.pizzaTypesCache);
            listView.setAdapter(adapter);
        }

        return view;
    }
}
