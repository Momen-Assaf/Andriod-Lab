package edu.birzeit.expirement7_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAddF = findViewById(R.id.button);
        final FirstFragment firstFragment = new FirstFragment();
        final SecondFragment secondFragment = new SecondFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();
        buttonAddF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.main, firstFragment, "FIRSTFrag");
                fragmentTransaction.commit();
            }
        });

        Button buttonAddF2 = findViewById(R.id.button2);
        buttonAddF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.main, secondFragment, "SECONDFrag");
                fragmentTransaction.commit();
            }
        });

        Button buttonRemoveF = findViewById(R.id.button3);
        buttonAddF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(firstFragment);
                fragmentTransaction.commit();
            }
        });

        Button buttonRemoveF2 = findViewById(R.id.button4);
        buttonAddF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(secondFragment);
                fragmentTransaction.commit();
            }
        });
    }
}