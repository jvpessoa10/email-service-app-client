package com.joao.emailserviceclient;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button mainBtn;

    EditText input;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = (TextView) findViewById(R.id.result);

        mainBtn = (Button) findViewById(R.id.start_service_btn);

        mainBtn.setOnClickListener(this);



        //com.joao.emailservice.EmailService.EMAIL_SERVICE_FINISHED

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.joao.emailservice.EmailService.EMAIL_SERVICE_FINISHED");

        registerReceiver(receiver,intentFilter);


    }

    BroadcastReceiver receiver= new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("data");

            System.out.println("Receiver:"+data);
            result.setText((String) data);

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service_btn:
                Intent i = new Intent();


                i.putExtra("data","{'data':{'body':'la','title':'LaLa'},'next':{'data':{'body':'LELE','title':'LELE'},'next':{'data':{'body':'la','title':'LaLa'}}}}");

                i.setComponent(new ComponentName("com.joao.emailservice", "com.joao.emailservice.EmailService"));
                ComponentName c = getApplicationContext().startService(i);

        }
    }


}
