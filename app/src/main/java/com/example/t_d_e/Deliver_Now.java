package com.example.t_d_e;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Deliver_Now extends AppCompatActivity {
    public static final String TAG = Deliver_Now.class.getName();
    private static final String TAG1 = Deliver_Now.class.getName();
    EditText receiver_name,receiver_mobile,pickup_inst,delivery_inst;
    RadioButton size1,size2,size3,weight1,weight2,weight3,weight4;
    Button deliver_Now;
    ProgressBar progressBar;
    String Package_Size="";
    String Package_Weight="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver__now);
        receiver_name=findViewById(R.id.Receiver_Name);
        receiver_mobile=findViewById(R.id.Receiver_mobile);
        pickup_inst=findViewById(R.id.Pickup_inst);
        delivery_inst=findViewById(R.id.Delivery_inst);
        size1=findViewById(R.id.Btn_size_1);
        size2=findViewById(R.id.btn_size_2);
        size3=findViewById(R.id.btn_size_3);
        weight1=findViewById(R.id.Btn_weight_1);
        weight2=findViewById(R.id.btn_weight_2);
        weight3=findViewById(R.id.btn_weight_3);
        weight4=findViewById(R.id.btn_weight_4);
        deliver_Now=findViewById(R.id.btn_delivery);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        deliver_Now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Receiver_name = receiver_name.getText().toString().trim();
                String Receiver_mobile = receiver_mobile.getText().toString().trim();
                String Pickup_instruction = pickup_inst.getText().toString().trim();
                String Delivery_instruction = delivery_inst.getText().toString().trim();

                if (TextUtils.isEmpty(Receiver_name)) {
                    receiver_name.setError("Receivername is required");
                    return;
                }
                if (TextUtils.isEmpty(Receiver_mobile)) {
                    receiver_mobile.setError("Receiver mobile is required");
                    return;
                }


                if (size1.isChecked()) {
                    Package_Size = "Under 1sq Feet";
                }
                if (size2.isChecked()) {
                    Package_Size = "1sq-2sq Feet";
                }
                if (size3.isChecked()) {
                    Package_Size = "More than 2sq Feet";
                }
                if (weight1.isChecked()) {
                    Package_Weight = "Under 500gm";
                }
                if (weight2.isChecked()) {
                    Package_Weight = "500gm-2kg";
                }
                if (weight3.isChecked()) {
                    Package_Weight = "2kg-5kg";
                }
                if (weight4.isChecked()) {
                    Package_Weight = "More than 5kg";
                }

                progressBar.setVisibility(View.VISIBLE);
               deliver_Now .setVisibility(View.INVISIBLE);



                // Create a new user with a first and last name
                Map<String, Object> user = new HashMap<>();
                user.put("Receiver Name", Receiver_name );
                user.put("Receiver Mobile Number",Receiver_mobile );
                user.put("Pick up Instruction",Pickup_instruction);
                user.put("Delivery Instruction",Delivery_instruction);
                user.put("Package Size",Package_Size);
                user.put("Package Weight",Package_Weight);

// Add a new document with a generated ID
                db.collection("Receivers")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with : " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG1, "Error adding document", e);
                            }
                        });

                startActivity(new Intent(getApplicationContext(),driverMov .class));


            }

        });

    }
}









