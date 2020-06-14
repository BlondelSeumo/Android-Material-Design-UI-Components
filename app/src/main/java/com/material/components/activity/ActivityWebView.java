package com.material.components.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.material.components.R;
import com.material.components.utils.Tools;

public class ActivityWebView extends AppCompatActivity {

    private static final String EXTRA_OBJC = "key.EXTRA_OBJC";
    private static final String EXTRA_FROM_NOTIF = "key.EXTRA_FROM_NOTIF";

    public static void navigate(Activity activity, String url, boolean from_notif) {
        Intent i = navigateBase(activity, url, from_notif);
        activity.startActivity(i);
    }

    public static Intent navigateBase(Context context, String url, boolean from_notif) {
        Intent intent = new Intent(context, ActivityWebView.class);
        intent.putExtra(EXTRA_OBJC, url);
        intent.putExtra(EXTRA_FROM_NOTIF, from_notif);
        return intent;
    }

    private Toolbar toolbar;
    private ActionBar actionBar;

    private WebView webView;
    private String url;
    private View lyt_parent;
    private ProgressBar progressBar;
    private Boolean from_notif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        lyt_parent = findViewById(R.id.lyt_parent);

        // get extra object
        url = getIntent().getStringExtra(EXTRA_OBJC);
        from_notif = getIntent().getBooleanExtra(EXTRA_FROM_NOTIF, false);

        initComponent();
        initToolbar();
        loadWebFromUrl();

    }

    private void initComponent() {
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        progressBar.setBackgroundColor(getResources().getColor(R.color.overlay_dark_10));
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setTitle(null);
        Tools.changeOverflowMenuIconColor(toolbar, getResources().getColor(R.color.colorPrimary));
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void loadWebFromUrl() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                progressBar.setVisibility(View.VISIBLE);
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                actionBar.setTitle(null);
                actionBar.setSubtitle(Tools.getHostName(url));
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                actionBar.setTitle(view.getTitle());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress + 5);
                if (progress >= 100) actionBar.setTitle(view.getTitle());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackChecker();
        } else if (item.getItemId() == R.id.action_refresh) {
            loadWebFromUrl();
        } else if (item.getItemId() == R.id.action_browser) {
            Tools.directLinkToBrowser(this, url);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_webview, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.colorPrimary));
        return true;
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            onBackChecker();
        }
    }

    private void onBackChecker() {
        if (from_notif) {
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        webView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }
}
