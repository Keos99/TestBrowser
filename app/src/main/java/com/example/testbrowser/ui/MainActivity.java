package com.example.testbrowser.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testbrowser.R;
import com.example.testbrowser.mvp.presenter.MainActivityPresenter;
import com.example.testbrowser.mvp.view.MainActivityView;

public class MainActivity extends MvpAppCompatActivity implements MainActivityView {

    private ImageButton backButton;
    private ImageButton forwardButton;
    private EditText editText;
    private WebView webView;

    @InjectPresenter
    MainActivityPresenter presenter;

    private class MyWebViewClient extends WebViewClient{
        @TargetApi(Build.VERSION_CODES.P)

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {
        backButton = findViewById(R.id.button_back);
        forwardButton = findViewById(R.id.button_forward);
        editText = findViewById(R.id.mainactivity_edittext);
        editText.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        webView = findViewById(R.id.mainactivity_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()){
                    webView.goForward();
                }
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    presenter.takeURL(editText.getText().toString());
                    return true;
                }
                return true;
            }
        });
    }

    @Override
    public void loadURLtoWebView(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void changeTextinEditText(String text) {
        editText.setText(text);
    }
}
