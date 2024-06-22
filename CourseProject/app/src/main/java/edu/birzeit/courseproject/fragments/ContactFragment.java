package edu.birzeit.courseproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.birzeit.courseproject.R;

public class ContactFragment extends Fragment {
    private Button btnCallUs, btnFindUs, btnEmailUs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        btnCallUs = view.findViewById(R.id.btnCallUs);
        btnFindUs = view.findViewById(R.id.btnFindUs);
        btnEmailUs = view.findViewById(R.id.btnEmailUs);

        btnCallUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0599000000"));
                startActivity(callIntent);
            }
        });

        btnFindUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:31.961013,35.190483?q=Pizza Restaurant");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnEmailUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","AdvancePizza@Pizza.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Inquiry");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        return view;
    }
}
