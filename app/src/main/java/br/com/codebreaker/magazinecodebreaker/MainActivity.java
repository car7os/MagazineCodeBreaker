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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

//+---------------------------------------------------------------------+
//| Classe Principal
//+---------------------------------------------------------------------+

public class MainActivity extends AppCompatActivity {

    private WebView navegadorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);


        // Variavel com a url do seu site
        String enderecoSite = "https://www.magazinevoce.com.br/magazinecodebreaker/";

        // configurando o WebView
        navegadorWeb = (WebView) findViewById(R.id.webview);
        navegadorWeb.setWebViewClient(webViewClient);
        navegadorWeb.loadUrl(enderecoSite);
        WebSettings configNavegador = navegadorWeb.getSettings();
        configNavegador.setJavaScriptEnabled(true);
        configNavegador.setAllowUniversalAccessFromFileURLs(true);
        configNavegador.setAppCacheEnabled(true);




    }

    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event){

        if ((keyCode == KeyEvent.KEYCODE_BACK) && navegadorWeb.canGoBack()){
            navegadorWeb.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}

//+------------------------------------------------------------------------------------------------+
//|                                                                    Referencias Bibliograficas  |
//| WebView - Convertendo Site em Aplicativo, AndroidPro (Visualizado em 27/03/2019)               |
//| https://www.androidpro.com.br/blog/desenvolvimento-android/webview-converter-site-aplicativo/
//+------------------------------------------------------------------------------------------------+
