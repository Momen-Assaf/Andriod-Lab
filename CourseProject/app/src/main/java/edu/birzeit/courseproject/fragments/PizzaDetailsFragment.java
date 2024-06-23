package edu.birzeit.courseproject.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.database.FavoriteDatabaseHelper;
import edu.birzeit.courseproject.database.OrderDatabaseHelper;
import edu.birzeit.courseproject.database.PizzaDatabaseHelper;
import edu.birzeit.courseproject.database.UserDatabaseHelper;
import edu.birzeit.courseproject.models.Favorite;
import edu.birzeit.courseproject.models.Order;
import edu.birzeit.courseproject.models.PizzaType;
import edu.birzeit.courseproject.models.User;

public class PizzaDetailsFragment extends Fragment {
    private static final String ARG_PIZZA_NAME = "pizza_name";
    private PizzaType pizza;
    private PizzaDatabaseHelper pizzaDatabaseHelper;
    private OrderDatabaseHelper orderDatabaseHelper;
    private UserDatabaseHelper userDatabaseHelper;
    private FavoriteDatabaseHelper favoriteDatabaseHelper;
    private User currentUser;

    public static PizzaDetailsFragment newInstance(String pizzaName) {
        PizzaDetailsFragment fragment = new PizzaDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PIZZA_NAME, pizzaName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza_details, container, false);

        // Initialize database helpers
        pizzaDatabaseHelper = new PizzaDatabaseHelper(getContext());
        orderDatabaseHelper = new OrderDatabaseHelper(getContext());
        userDatabaseHelper = new UserDatabaseHelper(getContext());
        favoriteDatabaseHelper = new FavoriteDatabaseHelper(getContext());

        TextView pizzaNameTextView = view.findViewById(R.id.pizzaName);
        TextView pizzaPriceTextView = view.findViewById(R.id.pizzaPrice);
        TextView pizzaCategoryTextView = view.findViewById(R.id.pizzaCategory);
        Button btnAddToFavorites = view.findViewById(R.id.btnAddToFavorites);
        Button btnOrder = view.findViewById(R.id.btnOrder);

        SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);
        if (email != null) {
            currentUser = userDatabaseHelper.getUserByEmail(email);
        }

        if (currentUser == null) {
            Toast.makeText(getContext(), "Error: User not found", Toast.LENGTH_SHORT).show();
            return view;
        }

        if (getArguments() != null) {
            String pizzaName = getArguments().getString(ARG_PIZZA_NAME);
            pizza = pizzaDatabaseHelper.getPizzaByName(pizzaName);

            if (pizza != null) {
                pizzaNameTextView.setText(pizza.getName());
                pizzaPriceTextView.setText(String.valueOf(pizza.getPrice()));
                pizzaCategoryTextView.setText(pizza.getCategory());
            }
        }

        btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavorites(pizza);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderPizza(pizza);
            }
        });

        return view;
    }

    private void addToFavorites(PizzaType pizza) {
        if (pizza == null) {
            Toast.makeText(getContext(), "Error: Unable to add to favorites. Pizza not found.", Toast.LENGTH_SHORT).show();
            return;
        }
        Favorite favorite = new Favorite(pizza.getName(),currentUser.getEmail(),System.currentTimeMillis());
        favoriteDatabaseHelper.addFavorite(favorite);

        Toast.makeText(getContext(), pizza.getName() + " added to favorites", Toast.LENGTH_SHORT).show();
    }

    private void orderPizza(PizzaType pizza) {
        if (pizza == null || currentUser == null) {
            Toast.makeText(getContext(), "Error: Unable to place order. Please try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        Order order = new Order(pizza.getName(), currentUser.getEmail(), System.currentTimeMillis());
        orderDatabaseHelper.addOrder(order);
        Toast.makeText(getContext(), "Order placed for " + pizza.getName(), Toast.LENGTH_SHORT).show();
    }
}
