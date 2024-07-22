package com.mp_sc.ivmeolcer_sc;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager yonet_sc;
    Sensor ivmeolcer_sc;
    float deger_sc,ilk_sc,son_sc;
    TextView ekran_sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yonet_sc = (SensorManager) getSystemService(SENSOR_SERVICE);
        ivmeolcer_sc = yonet_sc.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        yonet_sc.registerListener(this,ivmeolcer_sc,SensorManager.SENSOR_DELAY_NORMAL);
        ekran_sc = (TextView) findViewById(R.id.ekran);
        deger_sc = 0.000f;
        ilk_sc = SensorManager.GRAVITY_EARTH;
        son_sc = ilk_sc;

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float[] koordinat = sensorEvent.values;
        float x = koordinat[0];
        float y = koordinat[1];
        float z = koordinat[2];

        ilk_sc = son_sc;
        son_sc = (float) Math.sqrt(x*x+y*y+z*z);
        deger_sc = deger_sc*0.9f + (son_sc-ilk_sc);

        if (deger_sc >= 2.5){
            ekran_sc.setBackgroundColor(Color.argb(100,231,0,0));
            ekran_sc.setTextColor(Color.WHITE);
            ekran_sc.setText("UYARI");

        }

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}