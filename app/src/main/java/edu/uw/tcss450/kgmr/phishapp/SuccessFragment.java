package edu.uw.tcss450.kgmr.phishapp;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.uw.tcss450.kgmr.phishapp.model.Credentials;


/**
 * A simple {@link Fragment} subclass.
 */


/**
 * the success fragment of the application that the user see after login
 * @author Ken Gil Romero
 * @version Fall 19
 */
public class SuccessFragment extends Fragment {

//    public SuccessFragment() {
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
        return inflater.inflate(R.layout.fragment_success, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * start of the fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        updateContent();
    }

    /**
     * updates the email text to the argument's credential's email
     */
    public void updateContent() {
        if (getArguments() != null) {
            Log.d("cred", "yes");
            Credentials credentials = (Credentials) getArguments().get("key");
            TextView tv = getActivity().findViewById(R.id.tv_email);
            tv.setText(credentials.getEmail());
        }
    }
}
