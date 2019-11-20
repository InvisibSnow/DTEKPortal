package com.dtek.portal.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.dtek.portal.BuildConfig;
import com.dtek.portal.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dtek.portal.utils.GetIntentData.checkIntentData;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initAppVersion();
        startTimerApp();
    }

    private void initAppVersion(){
        tvAppVersion.setText(this.getString(R.string.app_version ,BuildConfig.VERSION_NAME));
    }

    private void startTimerApp(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 3500);
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        checkIntentData(getIntent(),intent);
        startActivity(intent);
        finish();
    }
}
