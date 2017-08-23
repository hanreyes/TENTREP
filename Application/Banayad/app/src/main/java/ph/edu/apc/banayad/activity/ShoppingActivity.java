package ph.edu.apc.banayad.activity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ph.edu.apc.banayad.R;
import ph.edu.apc.banayad.fragment.CartFragment;
import ph.edu.apc.banayad.fragment.CheckoutFragment;
import ph.edu.apc.banayad.fragment.ItemsFragment;
import ph.edu.apc.banayad.other.PageAdapter;

public class ShoppingActivity extends FragmentActivity implements CartFragment.OnFragmentInteractionListener,
CheckoutFragment.OnFragmentInteractionListener, ItemsFragment.OnFragmentInteractionListener{

    PageAdapter pageAdapter ;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), this);

        // Initialize viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pageAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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

}