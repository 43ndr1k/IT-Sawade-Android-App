package de.itsawade.itsawade.ui.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.itsawade.itsawade.R;
import de.itsawade.itsawade.async.GenericLoader;
import de.itsawade.itsawade.logic.PlaceholderLogic;
import de.itsawade.itsawade.model.UserList;
import de.itsawade.itsawade.util.Function;
import de.itsawade.itsawade.util.UserData;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoaderManager.LoaderCallbacks<UserList>{

    private static final String PREFS_NAME = "settings";
    private static final String SHPREF_KEY_ACCESS_TOKEN = "access_token";
    private static final String LOGIN_USER = "login_user";
    private static final int USER_LIST_DOWNLOADER = 0;


    private EditText authAcessToken;
    private EditText username;
    private Button button;
    private View view;
    private boolean UserVorhanden;
    FragmentActivity c;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);

        c = getActivity();

        button = (Button) view.findViewById(R.id.buttonKeySave);
        authAcessToken = (EditText) view.findViewById(R.id.editkey);
        username = (EditText) view.findViewById(R.id.editLoginUsername);

        UserData userData = new UserData(c);

        authAcessToken.setText(userData.getAccessToken());
        username.setText(userData.getUser());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoader();
                backToBackStack();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void backToBackStack() {
        getFragmentManager().popBackStack();
    }

    private void startLoader() {

        c.getSupportLoaderManager().restartLoader(USER_LIST_DOWNLOADER, null, this);
    }

    public void save(UserList list) {


        String user = username.getText().toString();

        for(int i = 0; i < list.getResults().size(); i++) {
            String u = list.getResults().get(i).getUsername();
            if ( user.equals(u)) {

                UserVorhanden = true;
                SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, c.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(SHPREF_KEY_ACCESS_TOKEN, authAcessToken.getText().toString());
                editor.putString(LOGIN_USER, username.getText().toString());
                editor.commit();
                Toast.makeText(c, "Settings Saved", Toast.LENGTH_SHORT).show();
            }
        }
        if (UserVorhanden == false) {
            Toast.makeText(getContext(), "Username falsch", Toast.LENGTH_SHORT).show();
        }

    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public Loader<UserList> onCreateLoader(int id, Bundle args) {
        final FragmentActivity ctx = getActivity();
        return new GenericLoader<>(ctx, new Function<UserList>() {
            @Override
            public UserList apply() {
                return PlaceholderLogic.getInstance().getAllUsers();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<UserList> loader, UserList data) {
        if (data != null) {
            save(data);
        } else {
            Toast.makeText(getContext(), "Login Daten falsch!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<UserList> loader) {

    }
}
