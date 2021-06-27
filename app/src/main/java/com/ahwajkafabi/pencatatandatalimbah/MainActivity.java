package com.ahwajkafabi.pencatatandatalimbah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahwajkafabi.pencatatandatalimbah.Helpers.Utils;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {
    //our splash screen views
    private ImageView mLogo;
    private TextView mainTitle, subTitle;

    /**
     * Let's initialize our widgets.
     */
    private void initializeWidgets() {
        mLogo = findViewById(R.id.mLogo);
        mainTitle = findViewById(R.id.mainTitle);
        subTitle = findViewById(R.id.subTitle);
    }
    /**
     * Let's show our Splash animation using Animation class. We fade in our widgets.
     */
    private void showSplashAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.top_to_bottom);
        mLogo.startAnimation(animation);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        mainTitle.startAnimation(fadeIn);
        subTitle.startAnimation(fadeIn);
    }


    /**
     * Let's go to our DashBoard after 2 seconds
     */
    private void goToDashboard() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Utils.openActivity(MainActivity.this, LoginActivity.class);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    /**
     * Let's Override attachBaseContext method. This is needed if you want to
     * show the custom fonts in this activity as ell.
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    /**
     * Let's create our onCreate method
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeWidgets();
        this.showSplashAnimation();
        this.goToDashboard();
    }
}