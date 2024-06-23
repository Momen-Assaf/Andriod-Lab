package edu.birzeit.courseproject.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.birzeit.courseproject.R;
import edu.birzeit.courseproject.database.OrderDatabaseHelper;
import edu.birzeit.courseproject.models.Order;

public class OrderFragment extends Fragment {
    private static final String TAG = "OrderFragment";
    private ListView listView;
    private OrderDatabaseHelper orderDatabaseHelper;
    private ArrayList<Order> orders;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        listView = view.findViewById(R.id.listViewOrders);
        orderDatabaseHelper = new OrderDatabaseHelper(getContext());


        SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email != null) {
            loadUserOrders(email);
        } else {
            Toast.makeText(getContext(), "Error: User email not found", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void loadUserOrders(String email) {
        orders = orderDatabaseHelper.getUserOrders(email);
        displayOrders(orders);
    }

    private void displayOrders(ArrayList<Order> orderList) {
        ArrayList<String> orderDetails = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        for (Order order : orderList) {
            String formattedDate = sdf.format(new Date(order.getDate()));
            String details = String.format("Pizza: %s\nDate: %s", order.getPizzaName(), formattedDate);
            orderDetails.add(details);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, orderDetails);
        listView.setAdapter(adapter);
    }
}
