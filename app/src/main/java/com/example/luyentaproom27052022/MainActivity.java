package com.example.luyentaproom27052022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luyentaproom27052022.database.UserDatabase;
import com.example.luyentaproom27052022.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

//    EditText mEdtUsername,mEdtAddress;
//    Button mBtnButton;
//    RecyclerView mRecyclerView;
    private UserAdapter userAdapter;
    private List<User> mListUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControll();

        userAdapter=new UserAdapter();
        mListUser=new ArrayList<>();
        mListUser=UserDatabase.getInstance(this).userDAO().getUser();
        userAdapter.setData(mListUser);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setAdapter(userAdapter);

        mainBinding.rcvUser.setLayoutManager(linearLayoutManager);
        mainBinding.rcvUser.setAdapter(userAdapter);

//        mBtnButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onClickButton();
//            }
//        });

        mainBinding.btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton();
            }
        });
    }

    private void onClickButton() {
        //trim xoa khoang trang dau va cuoi cua string
//        String strUsername=mEdtUsername.getText().toString().trim();
//        String strAddress=mEdtAddress.getText().toString().trim();

        String strUsername=mainBinding.edtUsername.getText().toString().trim();
        String strAddress=mainBinding.edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strUsername))
        {
            return;
        }

        User user=new User(strUsername,strAddress);

        UserDatabase.getInstance(this).userDAO().insertUser(user);

//        mEdtAddress.setText("");
//        mEdtUsername.setText("");

        mainBinding.edtAddress.setText("");
        mainBinding.edtUsername.setText("");

        mListUser=UserDatabase.getInstance(this).userDAO().getUser();
        userAdapter.setData(mListUser);
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();

    }


    private void addControll() {
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        mBtnButton=findViewById(R.id.btnButton);
//        mEdtAddress=findViewById(R.id.edtAddress);
//        mEdtUsername=findViewById(R.id.edtUsername);
//        mRecyclerView=findViewById(R.id.rcv_user);
    }
}