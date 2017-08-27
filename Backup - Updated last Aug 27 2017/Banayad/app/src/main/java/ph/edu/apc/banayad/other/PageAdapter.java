package ph.edu.apc.banayad.other;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Locale;

import ph.edu.apc.banayad.fragment.CartFragment;
import ph.edu.apc.banayad.fragment.CheckoutFragment;
import ph.edu.apc.banayad.fragment.ItemsFragment;

/**
 * Created by gc on 8/20/2017.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private String[] tabTitle = new String[]{"Items", "Cart", "Checkout"};
    private int pageCount = tabTitle.length;
    Context context;

    public PageAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }

    public Fragment getItem(int i){
        switch (i){
            case 0:
                ItemsFragment items = new ItemsFragment();
                return items;
            case 1:
                CartFragment cart = new CartFragment();
                return cart;
            case 2:
                CheckoutFragment checkout = new CheckoutFragment();
                return checkout;
        }
        return null;
    }

    @Override
    public int getCount() {
        return pageCount;
    }


    public CharSequence getPageTitle(int position){
        Locale l = Locale.getDefault();
        return tabTitle[position];
    }
}
