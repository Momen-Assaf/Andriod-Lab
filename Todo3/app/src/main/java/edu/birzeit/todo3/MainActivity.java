package edu.birzeit.todo3;

import android.os.Bundle;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView sun = findViewById(R.id.sunImageView);
        ImageView earth = findViewById(R.id.earthImageView);
        ImageView cloud1 = findViewById(R.id.cloud1ImageView);
        ImageView cloud2 = findViewById(R.id.cloud2ImageView);
        ImageView car = findViewById(R.id.car);
        ImageView trafic = findViewById(R.id.light);
        ImageView rock = findViewById(R.id.rock);

        final Animation cloud1anim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
        final Animation cloud2anim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate2);
        final Animation sunAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.suncycle);
        final Animation carAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.carcycle);
        //final Animation lightAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.lightchange);
        final Animation rockAnim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rocktrans);

        cloud1.startAnimation(cloud1anim);
        cloud2.startAnimation(cloud2anim);
        sun.startAnimation(sunAnim);
        car.startAnimation(carAnim);
       // trafic.startAnimation(lightAnim);
        rock.startAnimation(rockAnim);

    }
}
