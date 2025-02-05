package com.example.calendar;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calendar.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WebView 인스턴스를 가져옵니다.
        WebView webView = findViewById(R.id.webView);

        // WebView에서 JavaScript를 활성화합니다.
        WebSettings webSettings = webView.getSettings();
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true); // localStorage 사용을 허용

        // PC의 IP 주소로 로컬 서버를 로드합니다.
        // 'http://172.17.22.82:3000'로 연결합니다.
        webView.loadUrl("http://3.38.20.237");

        // WebViewClient를 설정하여 새 페이지가 브라우저에서 열리지 않도록 설정합니다.
        webView.setWebViewClient(new WebViewClient());
    }
}
