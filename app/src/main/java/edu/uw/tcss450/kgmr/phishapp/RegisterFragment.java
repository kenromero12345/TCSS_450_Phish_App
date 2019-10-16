package edu.uw.tcss450.kgmr.phishapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
 * the register fragment of the application that lets the user register
 * @author Ken Gil Romero
 * @version Fall 19
 */
public class RegisterFragment extends Fragment {

//    public RegisterFragment() {
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    /**
     * when view was created
     * @param view the current view
     * @param savedInstanceState the instance state that was saved
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button b = (Button) view.findViewById(R.id.button_register);
        b.setOnClickListener(vi -> {
            if (isEmailHasAt(view)
                    && !isEditTextEmpty(view, R.id.edit_text_email)
                    && !isEditTextEmpty(view, R.id.edit_text_password)
                    && !isEditTextEmpty(view, R.id.edit_text_retype_password)
                    && ((EditText)view.findViewById(R.id.edit_text_password)).getText().toString()
                    .equals(((EditText)view.findViewById(R.id.edit_text_retype_password)).getText().toString())
                    && !(isPasswordLessThan6(view))) {
                gotoLoginFragment(view);
            } else if (isEditTextEmpty(view, R.id.edit_text_email)){
                ((EditText)view.findViewById(R.id.edit_text_email)).setError("Email EditText is empty");
            } else if (!isEmailHasAt(view)) {
                ((EditText)view.findViewById(R.id.edit_text_email)).setError("Email EditText does not contain a single @");
            } else if (isEditTextEmpty(view, R.id.edit_text_password)){
                ((EditText)view.findViewById(R.id.edit_text_password)).setError("Password EditText is empty");
            } else if (isEditTextEmpty(view, R.id.edit_text_retype_password)){
                ((EditText)view.findViewById(R.id.edit_text_retype_password)).setError("Retype Password EditText is empty");
            } else if (isPasswordLessThan6(view)){
                ((EditText)view.findViewById(R.id.edit_text_password)).setError("Password is less than 6 characters");
            } else {
                ((EditText)view.findViewById(R.id.edit_text_retype_password)).setError("Passwords do not match");
            }

        });
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
     * @return if password is less tham 6 in size
     */
    private boolean isPasswordLessThan6(@NonNull View theView) {
        return ((EditText) theView.findViewById(R.id.edit_text_password)).getText().toString().length() < 6;
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
     * go to and send email and password to the login frament
     * @param view the current view
     */
    private void gotoLoginFragment(@NonNull View view) {
        Credentials credentials = new Credentials.Builder(((EditText)view.findViewById(R.id.edit_text_email)).getText().toString()
                , ((EditText)view.findViewById(R.id.edit_text_password)).getText().toString()).build();
        Bundle args = new Bundle();
        args.putSerializable("key", credentials);

        NavController nc = Navigation.findNavController(getView());

        if (nc.getCurrentDestination().getId() != R.id.registerFragment) {
            nc.navigateUp();
        }
        nc.navigate(R.id.action_registerFragment_to_loginFragment, args);
    }

}
