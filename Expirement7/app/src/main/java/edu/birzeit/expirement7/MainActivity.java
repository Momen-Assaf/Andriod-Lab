package edu.birzeit.expirement7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity implements SecondFragment.communicator{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bAdd = findViewById(R.id.fAdd);
        Button bRemove = findViewById(R.id.fRemove);
        Button bAttach = findViewById(R.id.fAttach);
        Button bDetach = findViewById(R.id.fDetach);

        View firstFragment = findViewById(R.id.fragment1);
        View secondFragment = findViewById(R.id.fragment2);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        bAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.attach(secondFragment );
                fragmentTransaction.commit();
            }
        });

        bDetach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.detach(secondFragment);
                fragmentTransaction.commit();
            }
        });

    }
    @Override
    public void respond() {
        SecondFragment secondFragment =
                (SecondFragment)getSupportFragmentManager().findFragmentById(R.id.fragment2);
        secondFragment.changeData();
    }
    @Override
    public void inc(){}
}