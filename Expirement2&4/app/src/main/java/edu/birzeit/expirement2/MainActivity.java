package edu.birzeit.expirement2;

import android.content.Intent;
import android.database.Cursor;
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
        protected void onResume() {
            super.onResume();
            DBHelper dataBaseHelper =new
                    DBHelper(MainActivity.this,"EXP4", null,1);
            Cursor allCustomersCursor = dataBaseHelper.getAllCustomers();
            secondLinearLayout.removeAllViews();
            while (allCustomersCursor.moveToNext()){
                TextView textView =new TextView(MainActivity.this);
                textView.setText(
                        "Id= "+allCustomersCursor.getString(0)
                                +"\nName= "+allCustomersCursor.getString(1)
                                +"\nPhone= "+allCustomersCursor.getString(2)
                                +"\nGender= "+allCustomersCursor.getString(3)
                                +"\n\n"
                );
                secondLinearLayout.addView(textView);
            }
        }
        for(Customer objCustomer : Customer.customersArrayList){
            TextView txtCustomerInfo = new TextView(this);
            txtCustomerInfo.setTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat);
            txtCustomerInfo.setText(objCustomer.toString());
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