package ph.edu.apc.banayad.other;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ph.edu.apc.banayad.models.Item;

import static ph.edu.apc.banayad.activity.ShoppingActivity.currentTransaction;

public class Scanning extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private static int MY_PERMISSIONS_REQUEST_CAMERA;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

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

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        addItem("Notebook " + rawResult.getText(), "60", "1");
        //onBackPressed();
        Toast.makeText(this, "" + rawResult.getText(), Toast.LENGTH_LONG).show();

        // If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
    }

    private void addItem(String name, String price, String barcode) {
        Item item = new Item(name, price, barcode);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("user")
                .child(user.getUid())
                .child("transactions")
                //.child(currentTransaction)
                .push()
                .setValue(item);
    }

    private String findItemName(String barcode) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference itemsDb = db.child("items");

        return "fuck you";
    }

    private String findItemPrice(String barcode) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference itemsDb = db.child("price");

        return "too expensive";
    }
}