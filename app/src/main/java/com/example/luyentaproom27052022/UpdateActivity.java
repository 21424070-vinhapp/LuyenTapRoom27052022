package com.example.luyentaproom27052022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.luyentaproom27052022.database.UserDAO;
import com.example.luyentaproom27052022.database.UserDatabase;
import com.example.luyentaproom27052022.databinding.ActivityMainBinding;
import com.example.luyentaproom27052022.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding mainBinding2;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mainBinding2 = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(mainBinding2.getRoot());

        mUser = (User) getIntent().getExtras().get("obj");

        if (mUser != null) {
            mainBinding2.edtEditAddress.setText(mUser.getAddress());
            mainBinding2.edtEditUsername.setText(mUser.getUsername());
        }
        mainBinding2.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upDateUser();
            }

        });
    }

    private void upDateUser() {
        String strUsername = mainBinding2.edtEditUsername.getText().toString().trim();
        String strAddress = mainBinding2.edtEditAddress.getText().toString().trim();

        if (TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strUsername)) {
            return;
        }

        //update user
        mUser.setUsername(strUsername);
        mUser.setAddress(strAddress);

        UserDatabase.getInstance(this).userDAO().updateUser(mUser);

        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(this,MainActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("updated_user",mUser);
        intent.putExtras(bundle);
        setResult(RESULT_OK);
        finish();
    }
}