package ph.edu.apc.banayad.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ph.edu.apc.banayad.R;
import ph.edu.apc.banayad.models.Item;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView itemsCart = (RecyclerView) rootView.findViewById(R.id.recyclerViewItems);
        itemsCart.setLayoutManager(new LinearLayoutManager(getActivity()));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("user");

        FirebaseRecyclerAdapter<Item, CartHolder> adapter =
                new FirebaseRecyclerAdapter<Item, CartHolder>(
                        Item.class,
                        R.layout.cart_view_holder,
                        CartHolder.class,
                        ref.child(user.getUid())
                                .child("transactions")
                ) {
                    @Override
                    protected void populateViewHolder(CartHolder viewHolder, Item model, int position) {
                        viewHolder.setItemQty("1");
                        viewHolder.setItemName(model.getmName());
                        viewHolder.setItemBarcode(model.getmBarcode());
                        viewHolder.setItemPrice(model.getmPrice());
                    }
                };
        itemsCart.setAdapter(adapter);
        return rootView;
    }

    public static class CartHolder extends RecyclerView.ViewHolder {
        private final TextView itemQty;
        private final TextView itemName;
        private final TextView itemPrice;
        private final TextView itemBarcode;

        public CartHolder(View itemView) {
            super(itemView);
            itemQty = (TextView) itemView.findViewById(R.id.textViewQuantity);
            itemName = (TextView) itemView.findViewById(R.id.textViewItemName);
            itemPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            itemBarcode = (TextView) itemView.findViewById(R.id.textViewBarcode);
        }

        public void setItemQty(String itemQty) {
            this.itemQty.setText(itemQty);
        }

        public void setItemName(String itemName) {
            this.itemName.setText(itemName);
        }

        public void setItemPrice(String itemPrice) {
            this.itemPrice.setText(itemPrice);
        }

        public void setItemBarcode(String barcode) {
            itemBarcode.setText(barcode);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
