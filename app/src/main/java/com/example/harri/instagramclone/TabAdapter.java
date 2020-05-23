package com.example.harri.instagramclone;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

public class TabAdapter extends FragmentPagerAdapter
{


    public TabAdapter(FragmentManager fm)
    {
        super(fm);

    }

    @Override
    public Fragment getItem(int tabPosition)
    {
       switch (tabPosition)
       {
           case 0:
               ProfileTab profileTab = new ProfileTab();
               return profileTab;
           case 1:

               return new UsersTab();
           case 2:
               return new SharePictureTab();

           default:
               return null;

       }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Perfil";
            case 1:
                return "Usuarios";
            case 2:
                return "Compartir Fotos";
            default:
                return null;
        }


    }
}
