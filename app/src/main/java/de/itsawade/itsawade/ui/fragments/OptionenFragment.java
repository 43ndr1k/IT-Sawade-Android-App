package de.itsawade.itsawade.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.itsawade.itsawade.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionenFragment extends Fragment {


    public OptionenFragment() {
        // Required empty public constructor
    }

    public static OptionenFragment newInstance() {
        return new OptionenFragment();
    }

    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_optionen, container, false);

        button = (Button) view.findViewById(R.id.buttonKonto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konto();
            }
        });


        return view;
    }

    private void konto() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = LoginFragment.newInstance();
        transaction.replace(R.id.activityContainer, loginFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
