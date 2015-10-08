package com.example.mikie.app3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class SubmitExpenseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_expense);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Intent intent = getIntent();
        final String imageUri = intent.getStringExtra("IMAGE_URI");
        ImageView recieptImage = (ImageView) findViewById(R.id.imageView);
        Button submitButton = (Button) findViewById(R.id.button);
        Button cancelButton = (Button) findViewById(R.id.button2);
        final EditText vendor = (EditText) findViewById(R.id.editText);
        final EditText total = (EditText) findViewById(R.id.editText2);
        final EditText description = (EditText) findViewById(R.id.editText3);
        recieptImage.setImageURI(Uri.parse(imageUri));
        submitButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                submitExpense(imageUri, vendor.getText(), total.getText(), description.getText());
                                            }
                                        }
        );
        cancelButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                finish();
                                            }
                                        }
        );


    }

    private void submitExpense(String imageUri, Editable vendor, Editable total, Editable description) {

        Context context = getApplicationContext();
        CharSequence text = "Your expense for "+vendor.toString()+" has been saved";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        finish();

    }

}


