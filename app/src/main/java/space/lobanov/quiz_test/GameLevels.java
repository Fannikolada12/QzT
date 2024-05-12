package space.lobanov.quiz_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.consent.*;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class GameLevels extends AppCompatActivity {

//    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

//        MobileAds.initialize(this, "Идентификатор приложения admob");
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level",1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //начало конструкции
                try{
                    Intent intent = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                //конец конструкции
            }
        });

        //кнопка для перехода на 1 уровень - начало
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(level>=1){
                        Intent intent = new Intent(GameLevels.this, Level1.class);
                        startActivity(intent);finish();
                    }else{
                        //пусто
                    }
                }catch(Exception e){
                    //пусто
                }
            }
        });
        //кнопка для перехода на 1 уровень - конец

        //кнопка для перехода на 2 уровень - начало
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(level>=2){
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);finish();
                    }else{
                      //пусто
                    }
                }catch(Exception e){
                    //пусто
                }
            }
        });
        //кнопка для перехода на 2 уровень - конец

        //кнопка для перехода на 3 уровень - начало
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(level>=3) {
                        Intent intent = new Intent(GameLevels.this, Level3.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //пусто
                    }
                }catch(Exception e){
                    //пусто
                }
            }
        });
        //кнопка для перехода на 3 уровень - конец

        //кнопка для перехода на 3 уровень - начало
        TextView textView4 = (TextView)findViewById(R.id.textView4);
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(level>=4) {
                        Intent intent = new Intent(GameLevels.this, Level4.class);
                        startActivity(intent);
                        finish();
                    }else{
                        //пусто
                    }
                }catch(Exception e){
                    //пусто
                }
            }
        });
        //кнопка для перехода на 3 уровень - конец

        final int[] x={
                R.id.textView1,
                R.id.textView2,
                R.id.textView3,
                R.id.textView4,
                R.id.textView5,

        };


            for (int i=1; i<level; i++){
                TextView tv = findViewById(x[i]);
                tv.setText(""+(i+1));
            }

    }

    //Системная кнопка "Назад" - начало
        @Override
        public void onBackPressed(){
            try{
                Intent intent = new Intent(GameLevels.this, MainActivity.class);
                startActivity(intent);finish();
            }catch (Exception e){

            }
        }
    //Системная кнопка "Назад" - конец
}
