package com.example.lavan32428068.tamrin6;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private TextView hours,minutes;
    private Button say;
    private int[] sounds = {R.raw.fouro, R.raw.fiftyo, R.raw.two, R.raw.daghigheh, 0};
    private int loc=0;
    private SharedPreferences sp ;
    private RadioButton twelve;
    private RadioButton twentytwo;
    private int n;
    private RadioGroup rg;

    int[] sounds1 = {
            0,
            R.raw.one,
            R.raw.two,
            R.raw.three,
            R.raw.four,
            R.raw.five,
            R.raw.six,
            R.raw.seven,
            R.raw.eight,
            R.raw.nine,
            R.raw.ten,
            R.raw.eleven,
            R.raw.towelve,
            R.raw.thirteen,
            R.raw.fourteen,
            R.raw.fifteen,
            R.raw.sixteen,
            R.raw.seventeen,
            R.raw.eighteen,
            R.raw.nineteen,
            R.raw.twenty,
    };

    int[] sounds1o = {
            0,
            R.raw.oneo,
            R.raw.twoo,
            R.raw.threeo,
            R.raw.fouro,
            R.raw.fiveo,
            R.raw.sixo,
            R.raw.seveno,
            R.raw.eighto,
            R.raw.nineo,
            R.raw.teno,
            R.raw.eleveno,
            R.raw.towelveo,
            R.raw.thirteeno,
            R.raw.fourteeno,
            R.raw.fifteeno,
            R.raw.sixteeno,
            R.raw.seventeeno,
            R.raw.eighteeno,
            R.raw.nineteeno,
            R.raw.twentyo,
    };

    int[] sounds10 = {
            0,
            R.raw.ten,
            R.raw.twenty,
            R.raw.thirty,
            R.raw.forty,
            R.raw.fifty,
    };

    int[] sounds10o = {
            0,
            R.raw.teno,
            R.raw.twentyo,
            R.raw.thirtyo,
            R.raw.fortyo,
            R.raw.fiftyo,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hours = (TextView) findViewById(R.id.hours);
        minutes = (TextView) findViewById(R.id.minutes);
        say = (Button) findViewById(R.id.say);
        twelve = (RadioButton) findViewById(R.id.twelve);
        twentytwo = (RadioButton) findViewById(R.id.twentytwo);
        SharedPreferences sp = getSharedPreferences("my", MODE_PRIVATE);
        sp.edit().putInt("choose", n);
        int n = sp.getInt("choose", 0);
        sp.edit().putInt("choose", n).apply();

        Typeface t = Typeface.createFromAsset(getAssets(), "digital7.otf");
        hours.setTypeface(t);
        minutes.setTypeface(t);

        rg=(RadioGroup)findViewById(R.id.rg2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.twentytwo) {
                    Date d = new Date();
                    int h = d.getHours();
                    int m = d.getMinutes();
                    String hs = String.format("%02d", h);
                    String ms = String.format("%02d", m);
                    hours.setText(hs);
                    minutes.setText(ms);
                }
                else{
                    Date d = new Date();
                    int h = d.getHours()-12;
                    int m = d.getMinutes();
                    String hs = String.format("%02d", h);
                    String ms = String.format("%02d", m);
                    hours.setText(hs);
                    minutes.setText(ms);
                }
            }
        });


        say.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Date d = new Date();
                int h = d.getHours();
                int m = d.getMinutes();
                String hs = String.format("%02d", h);
                String ms = String.format("%02d", m);
                hours.setText(hs);
                minutes.setText(ms);



                int i=0;
                sounds[i++] = m==0? sounds1[h] : sounds1o[h];

                if (m<20)
                    sounds[i++] = sounds1[m];
                else
                {
                    int m10 = m/10;
                    int m1 = m%10;
                    sounds[i++] = m1==0? sounds10[m10] : sounds10o[m10];
                    if (m1 !=0)
                        sounds[i++] = sounds1[m1];
                }

                if (m!=0)
                    sounds[i++] = R.raw.daghigheh;
                sounds[i++] = 0;

                MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.saat);
                mp.setOnCompletionListener(MainActivity.this);
                mp.start();
            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        if (sounds[loc]!=0)
        {
        MediaPlayer m = MediaPlayer.create(this,sounds[loc]);
        loc++;
        m.setOnCompletionListener(this);
        m.start();
        }
    }


}
