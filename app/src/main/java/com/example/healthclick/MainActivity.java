package com.example.healthclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.JetPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView respiration,oxygen,fat,colestral,cardiogram,bloodpresure;
    List data,dataList;
    Button stress;
    HashMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.refresh);
        bloodpresure = findViewById(R.id.bloodpresure);
        respiration=findViewById(R.id.respiration);
        fat=findViewById(R.id.fat);
        stress=findViewById(R.id.stress);
        colestral=findViewById(R.id.colestral);
        oxygen=findViewById(R.id.oxygen);
        cardiogram=findViewById(R.id.cardiogram);
        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,strssmanagement.class);
                startActivity(i);
            }
        });
        getData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }


    void getData(){

        DocumentReference docRef=db.collection("hema").document("1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isComplete()) {
                    try{
                        DocumentSnapshot data=task.getResult();
                        HashMap<String,String> da= (HashMap)data.getData();
//                        listSize=dataList.size()-1;
                        Toast.makeText(MainActivity.this, da.get("bp").toString(), Toast.LENGTH_SHORT).show();
                        SystemClock.sleep(50);
                        bloodpresure.setText(da.get("bloodpresure").toString());
                        respiration.setText(da.get("respiration").toString());
                        oxygen.setText(da.get("oxygen").toString());
                        fat.setText(da.get("fat").toString());
                        colestral.setText(da.get("colestral").toString());
                        cardiogram.setText(da.get("cardiogram").toString());

                    }catch (Exception e){
//                        Toast.makeText(MainActivity.this, " user Found With This ID", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

}
