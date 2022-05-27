package com.example.luyentaproom27052022;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

        userAdapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickDeleteUser(user);
            }
        });
        mListUser = new ArrayList<>();
        loadData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
        mainBinding.edtDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllUser();
            }
        });
        mainBinding.btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton();
            }
        });
        mainBinding.edtFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //i la action id
                if(i== EditorInfo.IME_ACTION_SEARCH)
                {
                    //Logic search
                    handleSearchUser();
                }
                return false;
            }
        });
    }

    private void handleSearchUser() {
        String strName=mainBinding.edtFind.getText().toString().trim();
        mListUser.clear();
        mListUser=UserDatabase.getInstance(MainActivity.this).userDAO().searchUser(strName);

        userAdapter.setData(mListUser);
    }

    private void deleteAllUser() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete all User")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteAllUser();
                        Toast.makeText(MainActivity.this, "Delete all user done", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void clickDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete User")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //deleteUser
                        UserDatabase.getInstance(MainActivity.this).userDAO().deleteUser(user);
                        Toast.makeText(MainActivity.this, "Delete user " + user.getUsername(), Toast.LENGTH_SHORT).show();

                        loadData();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void clickUpdateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("obj", user);
        intent.putExtras(bundle);
        activityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        loadData();
                    }
                }
            }
    );

    private void onClickButton() {

        //trim xoa khoang trang dau va cuoi cua string
//        String strUsername=mEdtUsername.getText().toString().trim();
//        String strAddress=mEdtAddress.getText().toString().trim();

        String strUsername = mainBinding.edtUsername.getText().toString().trim();
        String strAddress = mainBinding.edtAddress.getText().toString().trim();

        if (TextUtils.isEmpty(strAddress) || TextUtils.isEmpty(strUsername)) {
            return;
        }

        User user = new User(strUsername, strAddress);

        if (isUser(user)) {
            Toast.makeText(this, "user exist", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).userDAO().insertUser(user);

//        mEdtAddress.setText("");
//        mEdtUsername.setText("");

        Toast.makeText(this, "Add user sucessfully", Toast.LENGTH_SHORT).show();

        mainBinding.edtAddress.setText("");
        mainBinding.edtUsername.setText("");

        loadData();

    }


    private void addControll() {
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
//        mBtnButton=findViewById(R.id.btnButton);
//        mEdtAddress=findViewById(R.id.edtAddress);
//        mEdtUsername=findViewById(R.id.edtUsername);
//        mRecyclerView=findViewById(R.id.rcv_user);
    }

    private void loadData() {
        mListUser = UserDatabase.getInstance(this).userDAO().getUser();
        userAdapter.setData(mListUser);
    }

    private boolean isUser(User user) {
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

}