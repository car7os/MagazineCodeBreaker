//+-------------------------------------------------------------------------------------------------------+
//|                                                                                 MagazineCodeBreaker   |
//|                                                                          Copyright 2019, CodeBreaker  |
//|                                                                        http://www.codebreaker.com.br  |
//|                                                                                                       |
//| Referencias bibliograficas no final do arquivo.                                                       |
//+-------------------------------------------------------------------------------------------------------+

//+-------------------------------------------------------------------------------------------------------+
//| Pacote / Diretório da Classe                                                                          |
//+-------------------------------------------------------------------------------------------------------+

package br.com.codebreaker.magazinecodebreaker.vs1;

//+-------------------------------------------------------------------------------------------------------+
//| Bibliotecas Necessárias                                                                               |
//+-------------------------------------------------------------------------------------------------------+

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


//+-------------------------------------------------------------------------------------------------------+
//| Classe LimparCache                                                                                    |
//+-------------------------------------------------------------------------------------------------------+

public class LimparCache extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String comando = "limpar";

        if (comando.equals(intent.getStringExtra("action"))) {

            // Apagar Cache e dados do aplicativo
            ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();

        }
    }
}
