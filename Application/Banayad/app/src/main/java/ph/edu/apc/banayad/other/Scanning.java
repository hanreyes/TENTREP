package ph.edu.apc.banayad.other;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static ph.edu.apc.banayad.activity.ShoppingActivity.currentTransaction;

public class Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    // models for items
    public static class Items {
        public String barcode;
        public String itemName;

        public Items(String barcode, String itemName) {
            // ..
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference transactionRef = database.getReference("user")
                .child(user.getUid())
                .child("transactions")
                .child(currentTransaction)
                .child(rawResult.getText());
        transactionRef.setValue("Cereals");
        //onBackPressed();
        Toast.makeText(this, "" + rawResult.getText(), Toast.LENGTH_LONG).show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }
}