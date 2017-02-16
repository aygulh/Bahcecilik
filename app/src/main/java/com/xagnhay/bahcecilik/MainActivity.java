package com.xagnhay.bahcecilik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        BahcecilikApplication application = (BahcecilikApplication) getApplication();
        mTracker = application.getDefaultTracker();

        mTracker.setScreenName(MainActivity.class.getSimpleName());
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());


        mWebView = (WebView) findViewById(R.id.activity_main_webview);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Use remote resource
        // mWebView.loadUrl("http://getbootstrap.com/examples/navbar/");

        // Stop local links and redirects from opening in browser instead of WebView
        // mWebView.setWebViewClient(new MyAppWebViewClient());

        // Use local resource
        mWebView.loadUrl("file:///android_asset/www/index.htm");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_about) {
            Intent intent3 = new Intent(this, AboutActivity.class);
            startActivity(intent3);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
