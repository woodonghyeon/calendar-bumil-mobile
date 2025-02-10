package com.example.calendar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private boolean doubleBackToExitPressedOnce = false; // 뒤로가기 버튼 두 번 감지를 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // WebView 인스턴스 가져오기
        webView = findViewById(R.id.webView);

        // WebView 설정
        WebSettings webSettings = webView.getSettings();
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptEnabled(true); // JavaScript 활성화
        webSettings.setDomStorageEnabled(true); // localStorage 허용

        // 웹사이트 로드
        webView.loadUrl("http://3.38.20.237");

        // WebView 내에서 새 창이 열리도록 설정
        webView.setWebViewClient(new WebViewClient());

        // WebChromeClient 설정 - alert() 처리
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                // Android에서 알림을 띄움
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                result.confirm(); // alert 처리를 완료
                return true;
            }
        });
    }

    // 뒤로 가기 버튼 이벤트 처리
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                finish(); // 앱 종료
            } else {
                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();

                // 2초 안에 다시 누르지 않으면 초기화
                new Handler(Looper.getMainLooper()).postDelayed(
                    () -> doubleBackToExitPressedOnce = false, 
                    2000
                );
            }
            return true; // 기본 동작 방지
        }
        return super.onKeyDown(keyCode, event);
    }
}
