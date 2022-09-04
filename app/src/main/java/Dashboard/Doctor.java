package Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.excelsior.klinik.R;
import com.excelsior.klinik.previou_pateint2;
import com.excelsior.klinik.previou_patient1;

public class Doctor extends AppCompatActivity {

    ImageView image1,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        image1=findViewById(R.id.patient1);
        image2=findViewById(R.id.patient2);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Doctor.this, previou_patient1.class);
                startActivity(intent);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Doctor.this, previou_pateint2.class);
                startActivity(intent);
            }
        });
    }
}