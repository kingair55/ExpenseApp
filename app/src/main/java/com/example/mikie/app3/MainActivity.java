package com.example.mikie.app3;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public final static String DIRECTORY_IMAGES= "ExpensesUploads";
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void viewExpenses(View view){
        Intent intent = new Intent(this, ExpensesSummary.class);


        ExpenseItem exp1 = new ExpenseItem("$46.20", "Lunch with client", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Approved, "9 / 25 / 2015", "Mary Jane");
        ExpenseItem exp2 = new ExpenseItem("$30.00", "Travel to airport", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Pending, "9 / 1 / 2015", "Joe Bloggs");
        ExpenseItem exp3 = new ExpenseItem("$22.50", "Breakfast off-site", "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa", ExpenseStatus.Flagged, "9 / 9 / 2015", "Dirk Nowitzki");
        exp1.Amount = "$46.20";
        exp1.Subject = "Lunch with client";
        exp1.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp1.Status = ExpenseStatus.Approved;
        exp1.Date = "9 / 25 / 2015";
        exp1.Owner = "Mary Jane";


        exp2.Amount = "$30.00";
        exp2.Subject = "Travel to airport";
        exp2.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp2.Status = ExpenseStatus.Pending;
        exp2.Date = "9 / 1 / 2015";
        exp2.Owner = "Joe Bloggs";


        exp3.Amount = "$22.50";
        exp3.Subject = "Breakfast off-site";
        exp3.Description = "Yadie yadie yada adaka nadsa ad akdba bda dad ab asbdba dsa aa ansabdbaa";
        exp3.Status = ExpenseStatus.Flagged;
        exp3.Date = "9 / 9 / 2015";
        exp3.Owner = "Dirk Nowitzki";

        intent.putExtra("expenseItem1", exp1);
        intent.putExtra("expenseItem2", exp2);
        intent.putExtra("expenseItem3", exp3);

        startActivity(intent);
    }

    public void createExpense(View view)
    {
        captureImage();
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri();

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, 100);
    }

    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile() {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                DIRECTORY_IMAGES);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("TAG", "Oops! Failed create "
                        + DIRECTORY_IMAGES + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;

        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                + "IMG_" + timeStamp + ".jpg");


        return mediaFile;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                openSubmitExpenses();
            }}

    }

    public void openSubmitExpenses() {
        Intent intent = new Intent(this, SubmitExpenseActivity.class);
        intent.putExtra("IMAGE_URI", fileUri.getEncodedPath());
        startActivity(intent);
    }
}
