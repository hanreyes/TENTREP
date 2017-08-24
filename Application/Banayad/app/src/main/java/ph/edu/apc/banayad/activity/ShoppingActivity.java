package ph.edu.apc.banayad.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ph.edu.apc.banayad.R;
import ph.edu.apc.banayad.fragment.CartFragment;
import ph.edu.apc.banayad.fragment.CheckoutFragment;
import ph.edu.apc.banayad.fragment.ItemsFragment;
import ph.edu.apc.banayad.other.PageAdapter;
import ph.edu.apc.banayad.other.Scanning;

public class ShoppingActivity extends FragmentActivity implements CartFragment.OnFragmentInteractionListener,
CheckoutFragment.OnFragmentInteractionListener, ItemsFragment.OnFragmentInteractionListener, View.OnClickListener{

    PageAdapter pageAdapter ;
    ViewPager viewPager;
    TabLayout tabLayout;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public static String currentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        currentTransaction = database.getReference("user")
                .child(user.getUid())
                .child("transactions")
                .push().getKey();

        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        findViewById(R.id.btn_scan).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_navigation_drawer_drawer, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingActivity.this);
        builder.setMessage("This will delete current cart items.").setTitle("End this session?");
        // Alert Dialog buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ShoppingActivity.this, MainActivity.class);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("user")
                        .child(user.getUid())
                        .child("transactions")
                        .child(currentTransaction).removeValue();
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_scan:
                Intent in = new Intent(ShoppingActivity.this, Scanning.class);
                startActivity(in);
        }
    }
}