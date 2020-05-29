package com.example.harri.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //Ui componentes
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnsignUp, btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Registro");

        edtEmail = findViewById(R.id.edtEmterEmail);
        edtUsername = findViewById(R.id.edtEnterUserName);
        edtPassword = findViewById(R.id.edtEnterPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event)
            {
                //tecla de enter y evento de presionar
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    //realiza directamente la tarea de registro
                   onClick(btnsignUp);
                }
                return false;
            }
        });
        btnsignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogin);

        btnLogIn.setOnClickListener(SignUp.this);
        btnsignUp.setOnClickListener(SignUp.this);

        //hay un usuario con sesion iniciada
        if(ParseUser.getCurrentUser() != null)
        {

            //ParseUser.getCurrentUser().logOut();
            transitionToSocialMediaActivity();
        }



    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnSignUp:

                if(edtEmail.getText().toString().equals("")
                        || edtUsername.getText().toString().equals("")
                        || edtPassword.getText().toString().equals(""))
                {
                    FancyToast.makeText(SignUp.this,
                            "Email, NombreUsuario y Contraseña son campos obligatorios...",
                            Toast.LENGTH_LONG,FancyToast.SUCCESS,
                            true).show();
                }
                else
                {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());


                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Registrandose " + edtUsername.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e)
                        {
                            if (e == null)
                            {
                                FancyToast.makeText(SignUp.this,
                                        appUser.getUsername() + " se ha registrado",
                                        Toast.LENGTH_LONG,FancyToast.SUCCESS,
                                        true).show();

                                transitionToSocialMediaActivity();
                            }
                            else
                            {
                                FancyToast.makeText(SignUp.this,
                                        "Hubo un error " + e.getMessage(),
                                        Toast.LENGTH_LONG,FancyToast.ERROR,
                                        true).show();
                            }
                            progressDialog.dismiss();
                        }

                    });

                }

                break;

            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }
    }

    //para que desaparezca el teclado pulsando en un area vacia de la pantalla
    public void rootLayoutTapped(View view)
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
        Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
        startActivity(intent);
        finish();
    }
}
