package com.example.myapplication11;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab3 extends Fragment implements Animation.AnimationListener,View.OnClickListener {


    boolean blnButtonRotation = true;
    int intNumber = 6;;
    long lngDegrees = 0;
    SharedPreferences sharedPreferences;

    ImageView selected,imageRoulette;


    Button b_start,b_up,b_down;

    public tab3() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_tab3, container, false);
        b_start = (Button)v.findViewById(R.id.buttonStart);
        b_up = (Button)v.findViewById(R.id.buttonUp);
        b_down = (Button)v.findViewById(R.id.buttonDown);

        selected = (ImageView)v.findViewById(R.id.imageSelected);
        imageRoulette = (ImageView)v.findViewById(R.id.rouletteImage);


        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        this.intNumber = this.sharedPreferences.getInt("INT_NUMBER",6);
        setImageRoulette(this.intNumber);

        b_start.setOnClickListener(this);
        b_down.setOnClickListener(this);
        b_up.setOnClickListener(this);


        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        b_start.setVisibility(View.VISIBLE);

    }



    @Override
    public void onAnimationEnd(Animation animation) {
        Toast toast = Toast.makeText(getActivity(), " " + String.valueOf((int)(((double)this.intNumber)
                - Math.floor(((double)this.lngDegrees) / (360.0d / ((double)this.intNumber))))) + " ",0);
        toast.setGravity(49,0,0);
        toast.show();
        b_start.setVisibility(View.VISIBLE);


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    private void setImageRoulette(int myNumber)
    {
        switch(myNumber)
        {
            case 1:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette));
                return;
            case 2:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_2));
                return;

            case 3:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_3));
                return;
            case 4:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_4));
                return;

            case 5:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_5));
                return;
            case 6:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_6));
                return;

            case 7:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_7));
                return;
            case 8:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_8));
                return;

            case 9:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_9));
                return;
            case 10:
                imageRoulette.setImageDrawable(getResources().getDrawable(R.drawable.roulette_10));
                return;


        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonStart:
                if(this.blnButtonRotation)
                {
                    int ran = new Random().nextInt(360) + 3600;
                    RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                            (this.lngDegrees + ((long)ran)),1,0.5f,1,0.5f);

                    this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
                    rotateAnimation.setDuration((long)ran);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setInterpolator(new DecelerateInterpolator());
                    rotateAnimation.setAnimationListener(this);
                    imageRoulette.setAnimation(rotateAnimation);
                    imageRoulette.startAnimation(rotateAnimation);
                }
                break;
            case R.id.buttonDown:
                if(this.intNumber > 2)
                {
                    intNumber--;
                    setImageRoulette(this.intNumber);
                    b_up.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = this.sharedPreferences.edit();
                    editor.putInt("INT_NUMBER",this.intNumber);
                    editor.commit();
                }
                break;
            case R.id.buttonUp:
                if(this.intNumber < 10)
                {
                    this.intNumber++;
                    setImageRoulette(this.intNumber);
                    b_down.setVisibility(0);
                    SharedPreferences.Editor editor = this.sharedPreferences.edit();
                    editor.putInt("INT_NUMBER",this.intNumber);
                    editor.commit();
                }

                break;
            default:
                break;


        }



    }
}