package com.project.sensor_aproximacao_luminosidade;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView ivMicrofoneON, ivMicrofoneOFF;

    private SensorManager mSensorManager;
    private Sensor mLuz;
    private Sensor mProx;
    private ConstraintLayout Tela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tela = findViewById(R.id.Tela);
        ivMicrofoneON = findViewById(R.id.ivMicrofoneON);
        ivMicrofoneOFF = findViewById(R.id.ivMicrofoneOFF);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            mLuz = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            mProx = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        mSensorManager.registerListener(new Luz(), mLuz, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(new Proximacao(), mProx, SensorManager.SENSOR_DELAY_UI);

    }

    class Proximacao implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float valores = event.values[0];

            if (valores >= 6 ) {
                ivMicrofoneOFF.setImageResource(R.drawable.microfoneoff);
            } else {
                ivMicrofoneON.setImageResource(R.drawable.microfoneon);
            }
        }
    }

    class Luz implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float valor = event.values[0];

            if (valor >= 10000) {
                Tela.setBackgroundColor(Color.WHITE);
            } else {
                Tela.setBackgroundColor(Color.BLACK);
            }
        }
    }
}