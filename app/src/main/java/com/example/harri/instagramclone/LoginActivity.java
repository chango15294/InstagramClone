package com.example.harri.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //ui componentes
    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignUpLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Inicio de Sesión");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event)
            {
                //tecla de enter y evento de presionar
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    //realiza directamente la tarea de registro
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignUpLoginActivity = findViewById(R.id.btnSignUpLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignUpLoginActivity.setOnClickListener(this);

        //existe usuario con sesion iniciada
        if(ParseUser.getCurrentUser() != null)
        {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view)
    {
       switch (view.getId())
       {
           case R.id.btnLoginActivity:

               if(edtLoginEmail.getText().toString().equals("")
                       || edtLoginPassword.getText().toString().equals(""))

               {
                   FancyToast.makeText(LoginActivity.this,
                           "Email y Contraseña son campos obligatorios...",
                           Toast.LENGTH_LONG,FancyToast.SUCCESS,
                           true).show();


               }
               else
               {
                   ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                           edtLoginPassword.getText().toString(),
                           new LogInCallback() {
                               @Override
                               public void done(ParseUser user, ParseException e)
                               {
                                   if(user != null && e == null)
                                   {
                                       FancyToast.makeText(LoginActivity.this,
                                               user.getUsername() + " ha iniciado sesión correctamente",
                                               Toast.LENGTH_LONG,FancyToast.SUCCESS,
                                               true).show();
                                       transitionToSocialMediaActivity();
                                   }
                                   else
                                   {
                                       FancyToast.makeText(LoginActivity.this,
                                               "Hubo un error " + e.getMessage(),
                                               Toast.LENGTH_LONG,FancyToast.ERROR,
                                               true).show();
                                   }
                               }
                           });
               }



               break;

           case R.id.btnSignUpLoginActivity:
               Intent intent = new Intent(LoginActivity.this, SignUp.class);
               startActivity(intent);
               break;
       }
    }

    public void rootLayoutLoginTapped(View view)
    {
        try
        {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity()
    {
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
