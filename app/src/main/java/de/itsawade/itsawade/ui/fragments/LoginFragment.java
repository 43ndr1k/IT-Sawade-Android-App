package de.itsawade.itsawade.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.util.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String PREFS_NAME = "settings";
    private static final String SHPREF_KEY_ACCESS_TOKEN = "access_token";
    private static final String LOGIN_USER = "login_user";

    private EditText authAcessToken;
    private EditText username;
    private Button button;
    private View view;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        button = (Button) view.findViewById(R.id.buttonKeySave);
        authAcessToken = (EditText) view.findViewById(R.id.editkey);
        username = (EditText) view.findViewById(R.id.editLoginUsername);

        UserData userData = new UserData();
        userData.readLoginData(getContext());
        authAcessToken.setText(userData.getAccessToken());
        username.setText(userData.getUser());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void save() {
        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString(SHPREF_KEY_ACCESS_TOKEN, authAcessToken.getText().toString());
        editor.putString(LOGIN_USER, username.getText().toString());
        editor.commit();
        Toast.makeText(getContext(), "Settings Saved", Toast.LENGTH_SHORT).show();
        getFragmentManager().popBackStack();
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
}
