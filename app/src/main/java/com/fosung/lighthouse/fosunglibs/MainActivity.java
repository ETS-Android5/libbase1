package com.fosung.lighthouse.fosunglibs;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.geek.libnsfw.NsfwMainActivity;

/**
 * @author houjie
 * @date 2022/4/13
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent3 = new Intent(this, NsfwMainActivity.class);
        startActivity(intent3);
        /*ocr识别*/
//        Intent intent3 = new Intent(this, ScanAct1.class);
//        startActivity(intent3);
        /*新手引导页*/
//        Intent intent3 = new Intent(this, FirstActivity.class);
//        startActivity(intent3);
        /*适配压缩*/
//        Intent intent3 = new Intent(this, VideoComPressorActivity.class);
//        startActivity(intent3);
        /*shadow阴影的各项使用*/
//        Intent intent3 = new Intent(this, ShadowMainActivity.class);
//        startActivity(intent3);
//        new PgyerSDKManager.Init()
//                .setContext(getApplicationContext()) //设置上下问对象
//                .start();
//        startActivity(new Intent(MainActivity.this, ScanAct2.class));


    }
}