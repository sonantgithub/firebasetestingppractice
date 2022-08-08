package com.example.firebasetesting;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.apache.commons.collections4.map.HashedMap;

import java.util.Calendar;

public class RealtimeFirebase extends AppCompatActivity {
    EditText editText1, editText2, editText3, editText4;
    String aidString = "", nameString = "", emailString = "", activeString = "", keyString = "";
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    CheckBox ch, ch2;
    Boolean isEmailValid;
    int count;


    HashedMap<String, Object> userMap = new HashedMap<>();
    HashedMap<String, Object> userMaptwo = new HashedMap<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_firebase);

        ch = findViewById(R.id.cheackboxone);
        ch2 = findViewById(R.id.cheackboxtwo);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        dateView = (TextView) findViewById(R.id.textView3);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        aidString = getIntent().getStringExtra("aid");
        nameString = getIntent().getStringExtra("bname");
        emailString = getIntent().getStringExtra("cemail");
        activeString = getIntent().getStringExtra("dactive");
        keyString = getIntent().getStringExtra("dkey");


        //  String v4 = editText4.getText().toString()

        editText1 = findViewById(R.id.rfotxtone);
        editText2 = findViewById(R.id.rfotxttwo);
        editText3 = findViewById(R.id.rfotxtthree);
        //  editText4 = findViewById(R.id.rfotxtfour);

        editText1.setText(aidString);
        editText2.setText(nameString);
        editText3.setText(emailString);
        //   editText4.setText(activeString);
    }

    public void editdata(View view) {

        userMaptwo.put("aid", editText1.getText().toString());
        userMaptwo.put("bname", editText2.getText().toString());
        userMaptwo.put("cemail", editText3.getText().toString());
        //   userMaptwo.put("dactive", editText4.getText().toString());

        // Log.d("YUPS", "onClickheyyyyy" + keyString);
        myRef.child("users").child(keyString).setValue(userMaptwo)
                // FirebaseDatabase.getInstance().getReference().child("users")
                //        .child(keyString).setValue(userMaptwo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(RealtimeFirebase.this, "Sucessfuly edit", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RealtimeFirebase.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });
        setvalidation();
    }

    private void setvalidation() {
            }


    public void uplodedata(View view) {

        //  setvalidation();


        String v1 = editText1.getText().toString();
        String v2 = editText2.getText().toString();
        String v3 = editText3.getText().toString();

        String emailToText = editText3.getText().toString();

        // Check for a valid email address.
        if (v1.length() >= 5) {
            editText1.setError("Not more than 5 digits");
        } else if (v2.length() >= 15) {
            editText2.setError("Not more than 15 digits");
        } else if (v3.length() >= 30) {
            editText3.setError("Not more than 20 digits");
        } else {

        }

        if (editText1.getText().toString().isEmpty()) {
            editText1.setError(getResources().getString(R.string.edittext1));
        } else if (editText2.getText().toString().isEmpty()) {

            editText2.setError(getResources().getString(R.string.edittext1));
        } else {
        }


        // TODO // this is check box method


        if (ch.isChecked()) {
            count = 1 + 1;
        } else if (ch2.isChecked()) {
            count = 1 + 1;
        } else {

        }
        if (count < 2) {
            AlertDialog alertDialog = new AlertDialog.Builder(RealtimeFirebase.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You have to select any check boxes");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

            count = 0;


        } else if (ch.isChecked() && ch2.isChecked()) {
            AlertDialog alertDialog = new AlertDialog.Builder(RealtimeFirebase.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("You cannot select two check boxes");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            // todo

            if (emailToText.isEmpty()) {

                editText3.setError(getResources().getString(R.string.edittext3));

            } else {
            }

            if (Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference();
                userMap.put("aid", v1);
                userMap.put("bname", v2);
                userMap.put("cemail", v3);
                // userMap.put("dactive", v4);

                myRef.child("users").push().setValue(userMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RealtimeFirebase.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                editText3.setError(getResources().getString(R.string.edittext2));
            }

        }


    }


    public void nextactivity(View view) {
        Intent intent = new Intent(this, RealtimeFirebaseThree.class);
        startActivity(intent);
    }


    // TODO // this is cheack box method

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        Log.d("TAGREAL", "showDate: " + year);
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
