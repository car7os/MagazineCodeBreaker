package br.com.codebreaker.magazinecodebreaker;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LimparCache extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String comando = "limpar";
        if (comando.equals(intent.getStringExtra("action"))){

            // Apagar Cache e dados do aplicativo
            ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();

        }



    }

}
