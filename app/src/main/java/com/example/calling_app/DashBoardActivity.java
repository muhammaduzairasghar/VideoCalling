package com.example.calling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashBoardActivity extends AppCompatActivity {

    EditText secretCodeBox;
    Button join,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        secretCodeBox = findViewById(R.id.codebox);
        join = findViewById(R.id.join);
        share = findViewById(R.id.share);


        URL serverURL;
        try{
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        }
         catch (MalformedURLException e) {
            e.printStackTrace();
        }



        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(DashBoardActivity.this,options);
            }
        });



    }
}
