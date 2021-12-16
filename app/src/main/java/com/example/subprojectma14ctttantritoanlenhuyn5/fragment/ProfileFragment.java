package com.example.subprojectma14ctttantritoanlenhuyn5.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.subprojectma14ctttantritoanlenhuyn5.MainActivity;
import com.example.subprojectma14ctttantritoanlenhuyn5.MapsActivity;
import com.example.subprojectma14ctttantritoanlenhuyn5.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {

    private GoogleSignInClient mGoogleSignInClient;
    private TextView tvNameAccount;
    private SharedPreferences sharedPreferences;
    private String str;

    public static final String MY_PREFRENCE = "myPrefs";
    public static final String TOKEN = "myToken";
    public static final String NAME = "name";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        tvNameAccount = view.findViewById(R.id.tvNameAccount);

        sharedPreferences = this.getActivity().getSharedPreferences(MY_PREFRENCE, Context.MODE_PRIVATE);
        str = sharedPreferences.getString(NAME, "");
        if(str != null){
            tvNameAccount.setText(str);
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this.getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.getActivity());
        if (acct != null) {
            Picasso.get().load(acct.getPhotoUrl()).into((ImageView) view.findViewById(R.id.photo));
            tvNameAccount.setText(acct.getDisplayName());
        }

        registerForContextMenu(view.findViewById(R.id.photo));

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dangXuat:
                signOut();
                return true;
            case R.id.lienHe:
                ggMap();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ggMap() {
        startActivity(new Intent(getActivity(), MapsActivity.class));
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }
                });
    }
}
