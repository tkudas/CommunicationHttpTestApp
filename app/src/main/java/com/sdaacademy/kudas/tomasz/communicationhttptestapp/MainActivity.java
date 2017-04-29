package com.sdaacademy.kudas.tomasz.communicationhttptestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.messageList)
    ListView messageList;

    @BindView(R.id.messageText)
    TextView messageText;

    @BindView(R.id.sendMessageButton)
    Button sendMessageButton;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
