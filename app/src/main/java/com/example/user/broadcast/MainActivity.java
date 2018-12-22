package com.example.user.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.etMsg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver!=null)
            unregisterReceiver(broadcastReceiver);
    }



        @Override
    protected void onResume() {
        super.onResume();

        if (broadcastReceiver==null){
            broadcastReceiver=new BroadcastReceiver(){
                @Override
                public void onReceive(Context context, Intent intent) {
                    String msg=intent.getStringExtra("msg");
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("my_custom_action"));
    }

    public void send(View view) {
        Intent intent=new Intent();
        String msg=editText.getText().toString();
        intent.putExtra("msg",msg);

        intent.setAction("my_custom_action");
        sendBroadcast(intent);
    }
}
