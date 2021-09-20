package com.example.gathering;

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

public class DaseboardActivity extends AppCompatActivity {
    EditText secrecodeBox;
    Button joinBtn,shareBtn;
    URL serverUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daseboard);

        secrecodeBox=findViewById(R.id.secretId);
        joinBtn=findViewById(R.id.joinBtn);
        shareBtn=findViewById(R.id.shareBtn);

        try {
            serverUrl = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions defaultOption = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverUrl)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOption);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

     joinBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             JitsiMeetConferenceOptions options=new JitsiMeetConferenceOptions.Builder()
                     .setRoom(secrecodeBox.getText().toString())
                     .setWelcomePageEnabled(false)
                     .build();

             JitsiMeetActivity.launch(DaseboardActivity.this,options);
         }
     });
    }
}