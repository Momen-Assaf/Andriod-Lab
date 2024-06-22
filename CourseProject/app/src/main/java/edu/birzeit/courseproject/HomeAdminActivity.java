package edu.birzeit.courseproject;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import edu.birzeit.courseproject.fragments.AddAdminFragment;
import edu.birzeit.courseproject.fragments.AddSpecialOffersFragment;
import edu.birzeit.courseproject.fragments.AdminProfileFragment;
import edu.birzeit.courseproject.fragments.ViewOrdersFragment;

public class HomeAdminActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AdminProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_admin_profile);
        }
    }

    private void selectDrawerItem(MenuItem item) {
        Fragment fragment = null;

        int id = item.getItemId();
        if (id == R.id.nav_admin_profile) {
            fragment = new AdminProfileFragment();
        } else if (id == R.id.nav_add_admin) {
            fragment = new AddAdminFragment();
        } else if (id == R.id.nav_view_orders) {
            fragment = new ViewOrdersFragment();
        } else if (id == R.id.nav_add_special_offers) {
            fragment = new AddSpecialOffersFragment();
        } else if (id == R.id.nav_logout) {
            // TODO: Implement logout functionality
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
