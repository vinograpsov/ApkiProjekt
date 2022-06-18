package com.example.projertapki;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    MainActivity activity = (MainActivity) getActivity();

    private EditText email, password;
    private Button register;
    private TextView warning;
    private String username;
    private FirebaseAuth auth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration,container,false);

        email = view.findViewById(R.id.emailReg);
        password = view.findViewById(R.id.passwordReg);
        register = view.findViewById(R.id.registrationAcc);
        warning = view.findViewById(R.id.textView6);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(getActivity(), "Empty email of password pole", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6) {
                    Toast.makeText(getActivity(), "Password to short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtEmail, txtPassword);
                }
            }
        });

        return view;
    }

    /*
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(RegistrationActivity.this, StartActivity.class);
        startActivity(intent);
        finish();
    }
*/

    private void registerUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    warning.setText("Succsessful");
                    warning.setTextColor(Color.GREEN);

                    username = getNameFromEmail(email);

                    firebaseLeaderboard();

                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new GameFragment(),null).addToBackStack(null).commit();
                }
                else{
                    warning.setText("FAILED");
                    warning.setTextColor(Color.RED);
                }
            }
        });

    }

    private void firebaseLeaderboard(){
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseRef;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseRef= firebaseDatabase.getReference().child("Leaderboard");
        LeaderboardUser user = new LeaderboardUser(username,(long) 0 );
        Map<String, Object> userHashMap = new HashMap<>();
        userHashMap.put(username,user);
        databaseRef.updateChildren(userHashMap);
    }

    private String getNameFromEmail(String email){
        String username = "";
        for(int i = 0; i < email.length();i++){
            if (email.charAt(i) == '@') break;
            username += email.charAt(i);
        }
        return username;
    }
}