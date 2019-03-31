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
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
        configNavegador.setAppCacheEnabled(true); // Ativa Chache
        configNavegador.setDomStorageEnabled(true); // Ativa Acesso a Sites seguros, como, por exemplo SSL / https://


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
                    Log.i("CAMERA PERMISSION", "GRANTED");

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

        int hasReadSMSPermission = ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET);

        if (hasReadSMSPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(perms, REQUEST_CODE_PERMISSION);
            return;
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
