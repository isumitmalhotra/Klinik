package com.excelsior.klinik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientScreen extends AppCompatActivity {
    CardView card1,card2,cardconsult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        cardconsult=findViewById(R.id.card_consult);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PatientScreen.this,childSpecialistDoctor.class);
                startActivity(intent);

            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PatientScreen.this,childSpecialistDoctor.class);
                startActivity(intent);
            }
        });

        cardconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PatientScreen.this,Available_doctors.class);
                startActivity(intent);
            }
        });
    }
}