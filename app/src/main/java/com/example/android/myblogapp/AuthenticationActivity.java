package com.example.android.myblogapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthenticationActivity extends AppCompatActivity {
    EditText userName ,password;
    Button Register;
    Button signIn;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        userName=(EditText)findViewById(R.id.userName);
        password =(EditText)findViewById(R.id.password);

        signIn=(Button)findViewById(R.id.SignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormValid()) {
                    // Perform SignIn
                    new SignInTask().execute(userName.getText().toString(),password.getText().toString());
                    // execute is method of AsyncTask called on main UI thread


                }

            }


        });

        Register=(Button)findViewById(R.id.register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFormValid())
                {
                    // Perform  Registeration
                }

            }
        });

        p=new ProgressDialog(this);
        p.setMessage("Please Wait");

        p.setIndeterminate(true);
        // loading amount to be mentioned


    }
    public void showProgressDialog(boolean  s)
    {
        if(s)
        {
            p.show();
        }
        else
        {
            p.dismiss();
        }

    }
    public boolean isFormValid()
    {
        if(userName.getText().toString().trim().isEmpty())
        {
            // Also Provide ToAST
            Toast.makeText(this,"Username cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().trim().isEmpty())
        {
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void performSignIn()
    {

    }
    public void showAlert(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();


    }
    private void performRegister()
    {

    }
    class SignInTask extends AsyncTask<String, Void, Boolean>
    {
        // Require a backgroundProcess as signIn check may take time
    // Tryingwith mock values;
        String mockUserName = "test";
        String mockPassword = "password";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Execution begin
            showProgressDialog(true);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            // After execution is done
            showProgressDialog(false);
            // Based on SSignIn tresult show Alert to user Regarding the success or failure
            if(aBoolean)
            {
                showAlert("Welcome","You have Successfully Signed In");
            }
            else
            {
                showAlert("Error","UserName/Password is incorrect");
            }
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            // Will hace two string;
            String username = strings[0];
            String password =strings[1];
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return(username.contentEquals(mockUserName) && password.contentEquals(mockPassword));


        }
    }



}
