package com.example.harri.instagramclone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession,
                    edtProfileHobbies, edtProfilefavSport;
    private Button btnUpdateInfo;


    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfilefavSport = view.findViewById(R.id.edtProfileFavoriteSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser.get("profileName") == null)
        {
            edtProfileName.setText("");
        }else
        {
            edtProfileName.setText(parseUser.get("profileName").toString());
        }

        if(parseUser.get("profileBio") == null)
        {
            edtProfileBio.setText("");
        }else
        {
            edtProfileBio.setText(parseUser.get("profileBio").toString());
        }

        if(parseUser.get("ProfileProfession") == null)
        {
            edtProfileBio.setText("");
        }else
        {
            edtProfileBio.setText(parseUser.get("ProfileProfession").toString());
        }

        if(parseUser.get("profileHobbies") == null)
        {
            edtProfileBio.setText("");
        }else
        {
            edtProfileBio.setText(parseUser.get("profileHobbies").toString());
        }

        if(parseUser.get("profileFavSpor") == null)
        {
            edtProfileBio.setText("");
        }else
        {
            edtProfileBio.setText(parseUser.get("profileFavSpor").toString());
        }





        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                parseUser.put("profileName", edtProfileName.getText()+ "");
                parseUser.put("profileBio", edtProfileBio.getText()+ "");
                parseUser.put("ProfileProfession", edtProfileProfession.getText() + "");
                parseUser.put("profileHobbies",edtProfileHobbies.getText() + "");
                parseUser.put("profileFavSport",edtProfilefavSport.getText() + "");

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e)
                    {
                      if( e == null)
                      {
                          FancyToast.makeText(getContext(),
                                   " Información actualizada",
                                  Toast.LENGTH_LONG,FancyToast.SUCCESS,
                                  true).show();
                      }
                      else
                      {
                          FancyToast.makeText(getContext(),
                                  e.getMessage(),
                                  Toast.LENGTH_LONG,FancyToast.ERROR,
                                  true).show();
                      }
                    }
                });
            }
        });

        return view;
    }

}
