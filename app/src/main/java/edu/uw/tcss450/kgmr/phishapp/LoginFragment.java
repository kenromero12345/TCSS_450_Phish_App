package edu.uw.tcss450.kgmr.phishapp;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.uw.tcss450.kgmr.phishapp.model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 */

/**
 * the login fragment of the application that lets the user login
 * @author Ken Gil Romero
 * @version Fall 19
 */
public class LoginFragment extends Fragment {

//    public LoginFragment() {
//        // Required empty public constructor
//    }

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /**
     * when view was created
     * @param view current view
     * @param savedInstanceState instance state that was saved
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        updateContent(view);

        Button b = (Button) view.findViewById(R.id.button_register);
        b.setOnClickListener(v -> {
            gotoRegisterFragment();
        });

        b = (Button) view.findViewById(R.id.button_sign_in);
        b.setOnClickListener(v -> {
            logForClickingLogIn(view);

            if (isEmailHasAt(view)
                    && !isEditTextEmpty(view, R.id.edit_text_email)
                    && !isEditTextEmpty(view, R.id.edit_text_password)) {
                gotoSuccessFragment(view);
            } else if (isEditTextEmpty(view, R.id.edit_text_email)){
                ((EditText)view.findViewById(R.id.edit_text_email)).setError("Email EditText is empty");
            } else if (!isEmailHasAt(view)) {
                ((EditText)view.findViewById(R.id.edit_text_email)).setError("Email EditText does not contain a single @");
            } else {
                ((EditText)view.findViewById(R.id.edit_text_password)).setError("Password EditText is empty");
            }
        });
    }

    private void gotoRegisterFragment() {
        NavController nc = Navigation.findNavController(getView());

        if (nc.getCurrentDestination().getId() != R.id.loginFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_loginFragment_to_registerFragment);
    }

    private void logForClickingLogIn(@NonNull View view) {
        Log.d("LOGIN FRAG", ((EditText)view.findViewById(R.id.edit_text_email)).getText().toString() + " "
                + ((EditText)view.findViewById(R.id.edit_text_password)).getText().toString() + " "
                + isEmailHasAt(view) + " & "
                + !isEditTextEmpty(view, R.id.edit_text_email) + " & "
                + !isEditTextEmpty(view, R.id.edit_text_password) + " = "
                + (isEmailHasAt(view)
                && !isEditTextEmpty(view, R.id.edit_text_email)
                && !isEditTextEmpty(view, R.id.edit_text_password)));
    }

    /**
     * Go to the next fragment and send the credentials the users inputted
     * @param theView the current view
     */
    private void gotoSuccessFragment(@NonNull View theView) {
        Credentials credentials = new Credentials.Builder(((EditText)theView.findViewById(R.id.edit_text_email)).getText().toString()
                , ((EditText)theView.findViewById(R.id.edit_text_password)).getText().toString()).build();
        Bundle args = new Bundle();
        args.putSerializable("key", credentials);
        NavController nc = Navigation.findNavController(getView());

        if (nc.getCurrentDestination().getId() != R.id.loginFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_loginFragment_to_homeActivity, args);
    }

    /**
     * @param theView the current view
     * @return if email edittext has @
     */
    private boolean isEmailHasAt(@NonNull View theView) {
        return ((String) ((EditText) theView.findViewById(R.id.edit_text_email)).getText().toString()).contains("@");
    }

    /**
     * @param theView the current view
     * @param theId id of the edit text
     * @return if the edittext is empty
     */
    private boolean isEditTextEmpty(@NonNull View theView, int theId) {
        return ((EditText) theView.findViewById(theId)).getText().toString().equals("");
    }

    /**
     * updates the email and password edit text
     * @param theView the current view of the application
     */
    public void updateContent(View theView) {
        if (getArguments() != null) {
            Credentials credentials = (Credentials) getArguments().get("key");
            EditText etEmail = theView.findViewById(R.id.edit_text_email);
            etEmail.setText(credentials.getEmail());

            EditText etPass = theView.findViewById(R.id.edit_text_password);
            etPass.setText(credentials.getPassword());
        }
    }

    /**
     * when fragment is created
     * @param savedInstanceState the instance state that was saved
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                getActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
}
