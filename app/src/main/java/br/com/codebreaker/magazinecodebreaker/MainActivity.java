//+---------------------------------------------------------------------+
//|                                               MagazineCodeBreaker   |
//|                                        Copyright 2019, CodeBreaker  |
//|                                      http://www.codebreaker.com.br  |
//|                                                                     |
//| Referencias bibliograficas no final do arquivo.                     |
//+---------------------------------------------------------------------+

//+---------------------------------------------------------------------+
//| Pacote / Diretório da Classe                                        |
//+---------------------------------------------------------------------+

package br.com.codebreaker.magazinecodebreaker;

//+---------------------------------------------------------------------+
//| Bibliotecas Necessárias                                             |
//+---------------------------------------------------------------------+

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;


//+---------------------------------------------------------------------+
//| Classe Principal
//+---------------------------------------------------------------------+

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 100;
    private WebView navegadorWeb;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();

        // Referencia a Classe que não deixa abrir o site em um navegador externo
        WebViewClientImpl webViewClient = new WebViewClientImpl(this);


        // Endereço do seu site
        String enderecoSite = "https://www.magazinevoce.com.br/magazinecodebreaker/";

        // configurando o WebView
        navegadorWeb = (WebView) findViewById(R.id.webview);
        navegadorWeb.setWebViewClient(webViewClient); // Ao clicar nos links, não deixa acessar um navegador externo, fazendo com que todas as operações permaneçam dentro do seu aplicativo

        navegadorWeb.loadUrl(enderecoSite); // Carregar Site
        // Ajustar Configurações
        WebSettings configNavegador = navegadorWeb.getSettings();
        configNavegador.setJavaScriptEnabled(true); // Acessar JavaScript
        configNavegador.setAllowUniversalAccessFromFileURLs(true); // Permitir que o JavaScript acesse conteúdo de outras URLs, no caso da loje virtual, como o site já vem pronto, é melhor deixar ativo para não comprometer alguma funcionalidade
        configNavegador.setAppCacheEnabled(true); // Ativa Cache
        configNavegador.setDomStorageEnabled(true); // Ativa Acesso a Sites seguros, como, por exemplo SSL / https://


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                navegadorWeb.reload();
            }
        });


    }

    // Ativa o Botão Voltar do Android
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && navegadorWeb.canGoBack()) {
            navegadorWeb.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    // Solicitar Permissão


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //fazer o necessário
                } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    //TODO tratar em caso de Denied
                }

                if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("PERMISSION", "GRANTED");

                } else if (grantResults[1] == PackageManager.PERMISSION_DENIED) {
                    //TODO tratar em caso de Denied
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermissions() {
        String[] perms = {Manifest.permission.INTERNET};

        int hasReadSMSPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);

        if (hasReadSMSPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(perms, REQUEST_CODE_PERMISSION);
            return;
        }
    }


    @Override
    protected void onPause() {

        super.onPause();

        notificationLogout();

        // por motivo de segurança, todos os dados são apagados, assim ninguem acessa suas compras
        //((ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE)).clearApplicationUserData();

    }

//+-------------------------------------------------------------------------------------------+
//|                                                                     MagazineCodeBreaker   |
//|                                                              Copyright 2019, CodeBreaker  |
//|                                                            http://www.codebreaker.com.br  |
//|                                                                                           |
//| Conteúdo retirado do site:                                                                |
//| Implementando uma Notificação simples no Android (Visualizado em 06-04-2019)              |
//| https://www.treinaweb.com.br/blog/implementando-uma-notificacao-simples-no-android/       |
//+-------------------------------------------------------------------------------------------+

    public void notificationLogout() {

        int id = 1;
        int icone = R.drawable.ic_shopping_cart_black_24dp;
        String titulo = "Magazine CodeBreaker";
        String texto = "Deseja limpar os dados de acesso a sua conta?";

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), id, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder criarNotification = new NotificationCompat.Builder(getApplicationContext());
        criarNotification.setSmallIcon(icone);
        criarNotification.setContentTitle(titulo);
        criarNotification.setContentText(texto);

        criarNotification.setPriority(NotificationCompat.PRIORITY_MAX);
        criarNotification.setContentIntent(contentIntent);

        NotificationManager gerenciarNotification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        gerenciarNotification.notify(id, criarNotification.build());

    }


//+---------------------------------------------------------------------+
//|                                               MagazineCodeBreaker   |
//|                                        Copyright 2019, CodeBreaker  |
//|                                      http://www.codebreaker.com.br  |
//|                                                                     |
//| Conteúdo retirado do site:                                          |
//| https://www.androidpro.com.br/blog/desenvolvimento-android/webview-converter-site-aplicativo/
//+---------------------------------------------------------------------+

    /**
     * Esta Classe faz com que toda a atividade do WebView permaneçam no próprio WebView.
     * Ao clicar nos links, não deixa acessar um navegador externo, fazendo com que todas
     * as operações permaneçam dentro do seu aplicativo
     */


    public class WebViewClientImpl extends android.webkit.WebViewClient {

        private Activity activity = null;

        public WebViewClientImpl(Activity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.contains("magazinevoce.com.br")) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            activity.startActivity(intent);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            swipeLayout.setRefreshing(false);
        }

    }

}
//+-------------------------------------------------------------------------------------------------------+
//|                                                                    Referencias Bibliograficas         |
//| WebView - Convertendo Site em Aplicativo, AndroidPro (Visualizado em 27/03/2019)                      |
//| https://www.androidpro.com.br/blog/desenvolvimento-android/webview-converter-site-aplicativo/         |
//! Exemplo de código para solicitar permissões no Android M (6.0 Marchmellow) (Visualizado em 30/03/2019 |
//| https://gist.github.com/elcioabrahao/7a1f18b08eb252ccf2dd                                             |
//+-------------------------------------------------------------------------------------------------------+



