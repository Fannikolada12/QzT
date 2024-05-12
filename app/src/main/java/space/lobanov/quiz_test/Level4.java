package space.lobanov.quiz_test;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class Level4 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft; //переменная для левой картинки + текст
    public int numRight; //переменная для правой картинки + текст
    Array array = new Array(); //создали массив из класса Table
    Random random = new Random(); //для генерации случайных чисел
    public int count=0;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //Создаем переменную text_levels
        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level4); //Установили текст
        text_levels.setTextColor(R.color.black95);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        //код который скругляет углы левой кнопки
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        //код который скругляет углы правой кнопки
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = (TextView)findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);
        //Путь к правой TextView
        final TextView text_right = (TextView)findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);

        //Развернуть игру на весь экран - начало
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Развернуть игру на весь экран - конец

        //Устанавливаем фон - начало
        ImageView background = (ImageView)findViewById(R.id.background);
        background.setImageResource(R.drawable.level4);
        //Устанавливаем фон - конец

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this); //создаем новое диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к макету диалогового окна
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        //Устанавливаем картинку в диалоговое окно - начало
        ImageView previewimg = (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previewimg4);
        //Устанавливаем картинку в диалоговое окно - конец

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfon = (LinearLayout) dialog.findViewById(R.id.dialogfon);
        dialogfon.setBackgroundResource(R.drawable.previewbackground4);
        //Устанавливаем фон диалогового окна - конец

        //Устанавливаем описание задания - начало
        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.levelfour);
        //Устанавливаем описание задания - конец

        //кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level4.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //здесь кода не будет
                }
                dialog.dismiss(); //закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка, которая закрывает диалоговое окно - конец

        //кнопка "Продолжить" - начало
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });
        //кнопка "Продолжить" - конец

        dialog.show(); //показать диалоговое окно

        //___________________________________________________
        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this); //создаем новое диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE); // скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend); //путь к макету диалогового окна
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false); //окно нельзя закрыть кнопкой "Назад"

        //Устанавливаем фон диалогового окна - начало
        LinearLayout dialogfonEnd = (LinearLayout) dialogEnd.findViewById(R.id.dialogfon);
        dialogfonEnd.setBackgroundResource(R.drawable.previewbackground4);
        //Устанавливаем фон диалогового окна - конец

        //интересный факт - начало
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionEnd.setText(R.string.levelfourEnd);
        //интересный факт - конец


        //кнопка, которая закрывает диалоговое окно - начало
        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level4.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); //старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //здесь кода не будет
                }
                dialogEnd.dismiss(); //закрываем диалоговое окно
                //обрабатываем нажатие кнопки - конец
            }
        });
        //кнопка, которая закрывает диалоговое окно - конец

        //кнопка "Продолжить" - начало
        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    //Здесь кода не будет
                }
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });
        //кнопка "Продолжить" - конец

        //___________________________________________________

        //кнопка "Назад" - начало
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btn_back.setTextColor(R.color.black95);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //обрабатываем нажатие кнопки "Назад" - начало
                try{
                    //вернуться назад к выбору уровня - начало
                    Intent intent = new Intent(Level4.this, GameLevels.class); //создали намерение для перехода
                    startActivity(intent); ////старт намерения
                    finish(); //закрыть этот класс
                    //вернуться назад к выбору уровня - конец
                }catch (Exception e){
                    //Здесь кода не будет
                }
                //обрабатываем нажатие кнопки "Назад" - конец
            }
        });
        //кнопка "Назад" - конец

        //Массив для прогресса игры - начало
        final int[] progress = {R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
                R.id.point6,R.id.point7,R.id.point8,R.id.point9,R.id.point10,
                R.id.point11,R.id.point12,R.id.point13,R.id.point14,R.id.point15,
                R.id.point16,R.id.point17,R.id.point18,R.id.point19,R.id.point20,};
        //Массив для прогресса игры конец

        //Подключаем анимацию - начало
        final Animation a = AnimationUtils.loadAnimation(Level4.this, R.anim.alpha);
        //Подключаем анимацию - конец

        numLeft = random.nextInt(20); //генерируем случайное число
        img_left.setImageResource(array.images4[numLeft]); //достаем из массива картинку
        text_left.setText(array.texts4[numLeft]); //Достаем из массива текст

        numRight = random.nextInt(20); //Генерируем случайное чилсо

        //цикл с предусловием, проверяющий равенство чисел - начало
        while(array.strong[numLeft]==array.strong[numRight]){
            numRight = random.nextInt(20);
        }
        //цикл с предусловием, проверяющий равенство чисел - конец

        img_right.setImageResource(array.images4[numRight]); //Достаем из массива картинку
        text_right.setText(array.texts4[numRight]); //достаем из массива текст

        //Обрабатываем нажатие на левую картинку - начало
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);
                    if(array.strong[numLeft]>array.strong[numRight]){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(array.strong[numLeft]>array.strong[numRight]){
                        //Если левая картинка больше
                            if(count<20){
                                count=count+1;
                            }
                        //Закрашиваем прогресс серым цветом - начало
                            for (int i=0; i<20; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points);
                            }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                            for (int i=0; i<count; i++){
                                TextView tv = findViewById(progress[i]);
                                tv.setBackgroundResource(R.drawable.style_points_green);
                            }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }else{
                        //Если левая картинка меньше
                            if(count>0){
                                if(count==1){
                                    count = 0;
                                }else{
                                    count=count-2;
                                }
                            }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }
                    //Если отпустил палец - конец
                        if(count==20){
                            //ВЫХОД ИЗ УРОВНЯ
                            dialogEnd.show();
                        }else{
                            numLeft = random.nextInt(20); //генерируем случайное число
                            img_left.setImageResource(array.images4[numLeft]); //достаем из массива картинку
                            text_left.setText(array.texts4[numLeft]); //Достаем из массива текст

                            numRight = random.nextInt(20); //Генерируем случайное чилсо

                            //цикл с предусловием, проверяющий равенство чисел - начало
                            while(array.strong[numLeft]==array.strong[numRight]){
                                numRight = random.nextInt(20);
                            }
                            //цикл с предусловием, проверяющий равенство чисел - конец

                            img_right.setImageResource(array.images4[numRight]); //Достаем из массива картинку
                            text_right.setText(array.texts4[numRight]); //достаем из массива текст
                            img_right.setEnabled(true); //Включаем обратно правую картинку
                        }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец

        //Обрабатываем нажатие на левую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //Условие касания картинки - начало
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    //Если коснулся картинки - начало
                    img_left.setEnabled(false);
                    if(array.strong[numLeft]<array.strong[numRight]){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    //Если отпустил палец - начало
                    if(array.strong[numLeft]<array.strong[numRight]){
                        //Если левая картинка больше
                        if(count<20){
                            count=count+1;
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<20; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }else{
                        //Если правая картинка меньше
                        if(count>0){
                            if(count==1){
                                count = 0;
                            }else{
                                count=count-2;
                            }
                        }
                        //Закрашиваем прогресс серым цветом - начало
                        for (int i=0; i<19; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашиваем прогресс серым цветом - конец

                        //Определяем правильные ответы и закрашиваем зеленым - начало
                        for (int i=0; i<count; i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Определяем правильные ответы и закрашиваем зеленым - конец
                    }
                    //Если отпустил палец - конец
                    if(count==20){
                        //ВЫХОД ИЗ УРОВНЯ
                        dialogEnd.show();
                    }else{
                        numLeft = random.nextInt(20); //генерируем случайное число
                        img_left.setImageResource(array.images4[numLeft]); //достаем из массива картинку
                        text_left.setText(array.texts4[numLeft]); //Достаем из массива текст

                        numRight = random.nextInt(20); //Генерируем случайное чилсо

                        //цикл с предусловием, проверяющий равенство чисел - начало
                        while(array.strong[numLeft]==array.strong[numRight]){
                            numRight = random.nextInt(20);
                        }
                        //цикл с предусловием, проверяющий равенство чисел - конец

                        img_right.setImageResource(array.images4[numRight]); //Достаем из массива картинку
                        text_right.setText(array.texts4[numRight]); //достаем из массива текст
                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                //Условие касания картинки - конец

                return true;
            }
        });
        //Обрабатываем нажатие на левую картинку - конец


    }
    //Системная кнопка "Назад" - начало
    @Override
    public void onBackPressed(){
        //обрабатываем нажатие кнопки "Назад" - начало
        try{
            //вернуться назад к выбору уровня - начало
            Intent intent = new Intent(Level4.this, GameLevels.class); //создали намерение для перехода
            startActivity(intent); ////старт намерения
            finish(); //закрыть этот класс
            //вернуться назад к выбору уровня - конец
        }catch (Exception e){
            //Здесь кода не будет
        }
        //обрабатываем нажатие кнопки "Назад" - конец
    }
    //Системная кнопка "Назад" - конец
}
