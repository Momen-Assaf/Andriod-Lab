package edu.birzeit.courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import edu.birzeit.courseproject.fragments.ContactFragment;
import edu.birzeit.courseproject.fragments.FavoritesFragment;
import edu.birzeit.courseproject.fragments.HomeFragment;
import edu.birzeit.courseproject.fragments.OrderFragment;
import edu.birzeit.courseproject.fragments.PizzaMenuFragment;
import edu.birzeit.courseproject.fragments.ProfileFragment;
import edu.birzeit.courseproject.fragments.SpecialOffersFragment;

public class HomeCustomerActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        loadFragment(new HomeFragment());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_pizza_menu) {
            fragment = new PizzaMenuFragment();
        } else if (id == R.id.nav_orders) {
            fragment = new OrderFragment();
        } else if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
        } else if (id == R.id.nav_special_offers) {
            fragment = new SpecialOffersFragment();
        } else if (id == R.id.nav_profile) {
            fragment = new ProfileFragment();
        } else if (id == R.id.nav_contact) {
            fragment = new ContactFragment();
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(HomeCustomerActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        if( fragment != null) {
            loadFragment(fragment);
        }
        drawerLayout.closeDrawers();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
