package com.example.harri.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName,edtPunchSpeed,edtPunchPower, edtKickSpeed, edtkickPower;
    private TextView txtGetdata;
    private Button btnGetAllData;

    private Button btnTransition;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKeckSpeed);
        edtkickPower = findViewById(R.id.edtKickPower);

        txtGetdata = findViewById(R.id.txtGetData);
        btnGetAllData = findViewById(R.id.btnGetAllData);
        btnTransition = findViewById(R.id.btnNextActivity);


        txtGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("KLn231E58s", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e)
                    {
                       if(object != null && e == null)
                       {
                           txtGetdata.setText(object.get("name") +" - " + "Punch Power: " + object.get("punchPower"));
                       }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                allKickBoxers= "";
              ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");

              //se reciben unicamente los que tienen valor superior a 100
              //queryAll.whereGreaterThan("punchPower", 2000);
                //igual a 300
              queryAll.whereGreaterThanOrEqualTo("punchPower", 300);
              //solo salga 1
              queryAll.setLimit(1);

              queryAll.findInBackground(new FindCallback<ParseObject>() {
                  @Override
                  public void done(List<ParseObject> objects, ParseException e)
                  {
                     if(e == null)
                     {
                         if(objects.size() > 0)
                         {
                             for(ParseObject kickBoxer: objects)
                             {
                                 allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                             }

                             FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_LONG,
                                     FancyToast.SUCCESS, true).show();
                         }
                         else
                         {
                             FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                     FancyToast.ERROR, true).show();
                         }
                     }
                  }
              });
            }
        });

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignUp.this, SignUpLoginActivity.class);
                startActivity(intent);
            }
        });


    }



    @Override
    public void onClick(View v) {
        try {


            final ParseObject kickBoxer = new ParseObject("KickBoxer");
            kickBoxer.put("name", edtName.getText().toString());

            kickBoxer.put("punchSpeed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(edtkickPower.getText().toString()));
            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " guardado satisfactoriamente", FancyToast.LENGTH_LONG,
                                FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG,
                                FancyToast.ERROR, true).show();
                    }

                }
            });

        }catch (Exception e)
        {
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG,
                    FancyToast.ERROR, true).show();
        }
    }
}
