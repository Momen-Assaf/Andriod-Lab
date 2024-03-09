package edu.birzeit.expirement2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout firstLinearLayout = new LinearLayout(this);
        Button addButton = new Button(this);
        LinearLayout secondLinearLayout = new LinearLayout(this);

        ScrollView scrollView = new ScrollView(this);

        firstLinearLayout.setOrientation(LinearLayout.VERTICAL);
        secondLinearLayout.setOrientation(LinearLayout.VERTICAL);

        addButton.setText("ADD CUSTOMER");
        addButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        firstLinearLayout.addView(addButton);
        scrollView.addView(secondLinearLayout);
        firstLinearLayout.addView(scrollView);

        setContentView(firstLinearLayout);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCustomerActivity.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });

        for(Customer objCustomer : Customer.customersArrayList){
            TextView txtCustomerInfo = new TextView(this);
            txtCustomerInfo.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Display2);
            txtCustomerInfo.setText(Customer.customersArrayList.toString());
            Button dltButton = new Button(this);
            dltButton.setText("X");

            dltButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    secondLinearLayout.removeView(txtCustomerInfo);
                    secondLinearLayout.removeView(dltButton);
                }
            });
            secondLinearLayout.addView(txtCustomerInfo);
            secondLinearLayout.addView(dltButton);
        }
    }
}