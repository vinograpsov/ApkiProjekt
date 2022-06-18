package com.example.projertapki;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {


    MainActivity activity = (MainActivity) getActivity();
    private EditText email, password;
    private Button login;
    private TextView warning;

    private FirebaseAuth auth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

        View view = inflater.inflate(R.layout.fragment_login,container,false);




        email = view.findViewById(R.id.emailLog);
        password = view.findViewById(R.id.passwordLog);
        login = view.findViewById(R.id.singUpAcc);
        warning = view.findViewById(R.id.textView10);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();

                if (TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(getActivity(), "Empty email of password pole", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() < 6) {
                    Toast.makeText(getActivity(), "Password to short", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(txtEmail, txtPassword);
                }
            }
        });

        return view;
    }

//    @Override
//    public void onBackPressed()
//    {
//        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
//        startActivity(intent);
//        finish();
//    }




    private void loginUser(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    warning.setText("Succsessful");
                    warning.setTextColor(Color.GREEN);
                    MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new GameFragment(),null).addToBackStack(null).commit();
                }
                else{
                    warning.setText("FAILED");
                    warning.setTextColor(Color.RED);
                }
            }
        });
    }
}