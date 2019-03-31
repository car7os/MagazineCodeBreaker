//+---------------------------------------------------------------------+
//|                                               MagazineCodeBreaker   |
//|                                        Copyright 2019, CodeBreaker  |
//|                                      http://www.codebreaker.com.br  |
//|                                                                     |
//| Conteúdo retirado do site:                                          |
//| https://www.androidpro.com.br/blog/desenvolvimento-android/webview-converter-site-aplicativo/
//+---------------------------------------------------------------------+

package br.com.codebreaker.magazinecodebreaker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;

/**
 * Esta Classe faz com que toda a atividade do WebView permaneçam no próprio WebView.
 * Ao clicar nos links, não deixa acessar um navegador externo, fazendo com que todas
 * as operações permaneçam dentro do seu aplicativo
 */


public class WebViewClientImpl extends android.webkit.WebViewClient {

    private Activity activity = null;

    public WebViewClientImpl(Activity activity){
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url){
        if(url.contains("magazinevoce.com.br")) return false;

        Intent intent = new Intent (Intent.ACTION_VIEW, Uri.parse(url));

        activity.startActivity(intent);

        return true;
    }
}
