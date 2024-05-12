package space.lobanov.quiz_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.google.ads.consent.*;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //private long backPressedTime;
    //private Toast backToast;
    private ConsentForm form;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "Идентификатор приложения admob");

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);





        Button buttonStart = (Button)findViewById(R.id.buttonStart);
        buttonStart.setClipToOutline(true);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                    SharedPreferences.Editor editor = save.edit();
                    editor.putInt("Level", 1);
                    editor.commit();

                    Intent intent = new Intent(MainActivity.this, GameLevels.class);startActivity(intent);finish();
                } catch (Exception e) {
                }
            }
        });

    }

    //Системная кнопка "Назад" - начало

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setTitle("Выход из игры")
                .setMessage("Вы действительно хотите выйти?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface arg0, int arg1){
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();



        /*
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
        */
    }

    //Системная кнопка "Назад" - конец






}
