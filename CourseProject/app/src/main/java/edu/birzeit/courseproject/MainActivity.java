package edu.birzeit.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.birzeit.courseproject.fragments.PizzaMenuFragment;
import edu.birzeit.courseproject.models.PizzaTypeResponse;
import edu.birzeit.courseproject.network.ApiService;
import edu.birzeit.courseproject.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static ArrayList<String> pizzaTypesCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFetchPizzaTypes = findViewById(R.id.btnGetStarted);

        btnFetchPizzaTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPizzaTypes();
            }
        });
    }

    private void fetchPizzaTypes() {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.getPizzaTypes().enqueue(new Callback<PizzaTypeResponse>() {
            @Override
            public void onResponse(Call<PizzaTypeResponse> call, Response<PizzaTypeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pizzaTypesCache = response.body().getTypes();
                    Toast.makeText(MainActivity.this, "Pizza types: " + pizzaTypesCache, Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, RegestrationUnitActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get pizza types", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Response error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PizzaTypeResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Network error: ", t);
            }
        });
    }
}
