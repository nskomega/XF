package ru.om.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ru.om.R;

public class First_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_actitivty);
    }

    public void onClick(View view) {
        Intent intent = new Intent(First_Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
