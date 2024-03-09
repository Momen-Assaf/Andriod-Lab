package edu.birzeit.expirement2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        String[] options = { "Male", "Female"};
        final Spinner genderSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, options);
        genderSpinner.setAdapter(objGenderArr);

        final EditText idEditText = findViewById(R.id.editTextID);
        final EditText nameEditText = findViewById(R.id.editTextName);
        final EditText phoneEditText = findViewById(R.id.editTextPhone);

        Button addCustomerButton = findViewById(R.id.button);
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer newCustomer= new Customer();

                if(idEditText.getText().toString().isEmpty()) newCustomer.setmCustomerId(0);
                else newCustomer.setmCustomerId(Long.parseLong(idEditText.getText().toString()));
                if(nameEditText.getText().toString().isEmpty()) newCustomer.setmName("No Name");
                else newCustomer.setmName(nameEditText.getText().toString());
                if(phoneEditText.getText().toString().isEmpty()) newCustomer.setmPhone("no Phone");
                else newCustomer.setmPhone(phoneEditText.getText().toString());

                newCustomer.setmGender(genderSpinner.getSelectedItem().toString());
                Customer.customersArrayList.add(newCustomer);
                Intent intent = new Intent(AddCustomerActivity.this,MainActivity.class);
                AddCustomerActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}