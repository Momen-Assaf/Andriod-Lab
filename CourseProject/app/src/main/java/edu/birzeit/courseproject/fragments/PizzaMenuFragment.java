package edu.birzeit.courseproject.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.database.PizzaDatabaseHelper;
import edu.birzeit.courseproject.database.UserDatabaseHelper;
import edu.birzeit.courseproject.models.PizzaType;

public class PizzaMenuFragment extends Fragment {
    private static final String TAG = "PizzaMenuFragment";
    private ListView listView;
    private PizzaDatabaseHelper pizzaDatabaseHelper;
    private ArrayList<PizzaType> pizzaTypes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_menu, container, false);
        listView = view.findViewById(R.id.listViewPizza);
        pizzaDatabaseHelper = new PizzaDatabaseHelper(getContext());

        Button btnFilterPrice = view.findViewById(R.id.btnFilterPrice);
        Button btnFilterSize = view.findViewById(R.id.btnFilterSize);
        Button btnFilterCategory = view.findViewById(R.id.btnFilterCategory);

        btnFilterPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPizzaTypes("price");
            }
        });

        btnFilterSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPizzaTypes("size");
            }
        });

        btnFilterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterPizzaTypes("pizza");
            }
        });

        loadPizzaTypes();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PizzaType selectedPizza = pizzaTypes.get(position);
                displayPizzaDetails(selectedPizza.getName());
            }
        });

        return view;
    }

    private void loadPizzaTypes() {
        pizzaTypes = pizzaDatabaseHelper.getAllPizzaTypes();
        displayPizzaTypes(pizzaTypes);
    }

    private void filterPizzaTypes(String filterType) {
        ArrayList<PizzaType> filteredList = new ArrayList<>();

        for (PizzaType pizza : pizzaTypes) {
            if (filterType.equals("price") && pizza.getPrice() < 10) {
                filteredList.add(pizza);
            } else if (filterType.equals("size")) {
                filteredList.add(pizza);
            } else if (filterType.equals("category") && pizza.getCategory().equals("pizza")) {
                filteredList.add(pizza);
            }
        }

        displayPizzaTypes(filteredList);
    }

    private void displayPizzaTypes(ArrayList<PizzaType> pizzaList) {
        ArrayList<String> pizzaNames = new ArrayList<>();
        for (PizzaType pizza : pizzaList) {
            pizzaNames.add(pizza.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, pizzaNames);
        listView.setAdapter(adapter);
    }

    private void displayPizzaDetails(String pizzaName) {
        PizzaType pizza = pizzaDatabaseHelper.getPizzaByName(pizzaName);
        if (pizza != null) {
            // Show details in a fragment or dialog
            // Example: Displaying in a Toast (replace with your preferred method)
            Toast.makeText(getContext(), "Name: " + pizza.getName() + "\nPrice: $" + pizza.getPrice() + "\nCategory: " + pizza.getCategory(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Pizza details not found", Toast.LENGTH_SHORT).show();
        }
    }

}
