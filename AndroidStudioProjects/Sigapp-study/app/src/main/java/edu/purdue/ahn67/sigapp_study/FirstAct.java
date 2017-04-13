package edu.purdue.ahn67.sigapp_study;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class FirstAct extends AppCompatActivity {

    ImageButton imbutton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imbutton1=(ImageButton)findViewById(R.id.imButton_1);
        imbutton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        SecondAct.class); // 다음 넘어갈 클래스 지정
                startActivity(intent); // 다음 화면으로 넘어간
            }
        });

    }

}

