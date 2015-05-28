package com.getvsm.ava;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by brabh on 5/22/2015.
 */
public class BindService extends Service {
    public static MyWebSocketClient mWebSocketClient;
    private final IBinder mBinder = new MyBinder();
    public static Context applicationContext;
    public String oauth;


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.d("connection", "you gt connected");
        String task = (String) intent.getExtras().get("task");
        if (task.contains("login")) {
            oauth = (String) intent.getExtras().get("oauth");
            applicationContext = this;
            Intent myService = new Intent(this, SocketService.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    myService, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentText("service is on")
                    .setContentIntent(pendingIntent).build();
            startForeground(1, notification);
            connectWebSocket();
            stopForeground(true);
        }
    }

    public class MyBinder extends Binder {
        public BindService getService() {
            return BindService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Service created", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        applicationContext = this;
        Intent myService = new Intent(this, SocketService.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                myService, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("service is on")
                .setContentIntent(pendingIntent).build();

        startForeground(1, notification);
        String task = (String) intent.getExtras().get("task");
        if (task.contains("login")) {

            oauth = (String) intent.getExtras().get("oauth");
            if (mWebSocketClient == null) {
                connectWebSocket();
            }

        } else if (task.contains("logout_fingerprint")) {

            String fingerprint = (String) intent.getExtras().get("fingerprint");
            if (mWebSocketClient == null) {
                connectWebSocket();
            }
            try {
                JSONObject message = new JSONObject();
                message.put("command", "BROADCAST_LOGOUT_FINGERPRINT");
                message.put("data", fingerprint);
                Log.d("sending", message.toString());
                mWebSocketClient.sendMessageTOServer(message.toString());

            } catch (Exception e) {
                e.printStackTrace();

            }

        } else if (task.contains("logout_all")) {


            if (mWebSocketClient == null) {
                connectWebSocket();
            }
            try {
                JSONObject message = new JSONObject();
                message.put("command", "BROADCAST_LOGOUT_ALL");
                message.put("data", "");
                Log.d("sending", message.toString());
                mWebSocketClient.sendMessageTOServer(message.toString());
                mWebSocketClient.close();
                mWebSocketClient = null;
            } catch (Exception e) {
                e.printStackTrace();

            }

        } else if (task.contains("logout")) {


            if (mWebSocketClient == null) {
                connectWebSocket();
            }
            try {
                JSONObject message = new JSONObject();
                message.put("command", "BROADCAST_LOGOUT");
                message.put("data", "");
                Log.d("sending", message.toString());
                mWebSocketClient.sendMessageTOServer(message.toString());
                mWebSocketClient.close();
                mWebSocketClient = null;
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
        stopForeground(true);
        return START_REDELIVER_INTENT;


    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Toast.makeText(this, "Service removed", Toast.LENGTH_LONG).show();
        mWebSocketClient.close();
        mWebSocketClient = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

    public void connectWebSocket() {

        try {

            mWebSocketClient = new MyWebSocketClient(new URI(getResources().getString(R.string.socket_url)), oauth);
            // mWebSocketClient.setOauth(oauth);
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }
}

