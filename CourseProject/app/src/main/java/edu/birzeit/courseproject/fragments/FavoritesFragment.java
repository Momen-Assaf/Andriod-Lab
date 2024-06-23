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
import edu.birzeit.courseproject.database.FavoriteDatabaseHelper;
import edu.birzeit.courseproject.database.OrderDatabaseHelper;
import edu.birzeit.courseproject.models.Favorite;
import edu.birzeit.courseproject.models.Order;
import edu.birzeit.courseproject.models.PizzaType;
import edu.birzeit.courseproject.models.User;

public class FavoritesFragment extends Fragment {
    private static final String TAG = "FavoriteFragment";
    private ListView listView;
    private FavoriteDatabaseHelper favoriteDatabaseHelper;
    private OrderDatabaseHelper orderDatabaseHelper;
    private ArrayList<Favorite> favorites;

    private User currentUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        listView = view.findViewById(R.id.listViewFavorites);
        favoriteDatabaseHelper = new FavoriteDatabaseHelper(getContext());
        orderDatabaseHelper = new OrderDatabaseHelper(getContext());

        SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email != null) {
            loadUserFavorites(email);
        } else {
            Toast.makeText(getContext(), "Error: User email not found", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void loadUserFavorites(String email) {
        favorites = favoriteDatabaseHelper.getUserFavorites(email);
        displayFavorites(favorites);
    }

    private void displayFavorites(ArrayList<Favorite> favoriteList) {
        ArrayList<String> favDetails = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        for (Favorite favorite : favoriteList) {
            String formattedDate = sdf.format(new Date(favorite.getDateAdded()));
            String details = String.format("Pizza: %s\nDate: %s", favorite.getPizzaName(), formattedDate);
            favDetails.add(details);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, favDetails);
        listView.setAdapter(adapter);

//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            Favorite favorite = favorites.get(position);
//
//            handleFavoriteActions(favorite);
//        });
    }

    private void handleFavoriteActions(Favorite favorite) {


    }
}
