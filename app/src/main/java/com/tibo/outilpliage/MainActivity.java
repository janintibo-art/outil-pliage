package com.tibo.outilpliage;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
public class MainActivity extends Activity {
  private WebView web;
  @Override public void onCreate(Bundle saved) {
    super.onCreate(saved);
    web = new WebView(this); setContentView(web);
    web.setBackgroundColor(0xff0b1423);
    WebSettings settings=web.getSettings(); settings.setJavaScriptEnabled(true); settings.setDomStorageEnabled(true);
    web.setWebViewClient(new WebViewClient(){
      @Override public boolean shouldOverrideUrlLoading(WebView v, WebResourceRequest req){
        Uri uri=req.getUrl();
        if ("https".equals(uri.getScheme()) || "http".equals(uri.getScheme())) {
          try {startActivity(new Intent(Intent.ACTION_VIEW,uri));} catch(Exception ignored){} return true;
        }return !"file".equals(uri.getScheme());
      }
    });
    web.loadUrl("file:///android_asset/index.html");
  }
  @Override public void onBackPressed() { web.evaluateJavascript("window.closeDetail ? window.closeDetail() : false",null); }
}
