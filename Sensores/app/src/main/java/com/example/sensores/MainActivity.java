package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.SensorPrivacyManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {


    private float lastX, lastY, lastZ:

    private SensorPrivacyManager sensorManager;
    private Sensor accelerometer;

    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;

    private TextView currentX, currentY, currentZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
     // cria os links para tela
    currentX = (TextView) findViewById (R.id.currentX);
    currentY = (TextView) findViewById (R.id.currentY);
    currentZ = (TextView) findViewById (R.id.currentZ);

    //inicializa o sensor do acelerômetro
   sensorManager = (SensorManager)  getSystemService(Context.SENSOR_SERVICE);

   if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
       // nos temos um acelerometro no aparelho
        accelerometer - sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener (this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    } else {
       // Não existe sensor
    }

    }

     @Override
     protected void onPause(){
       super.OnPause();
       sensorManager.unregisterListener(this);
      }

      @Override
      protected void onResume(){
      super.OnResume();
          sensorManager.registerListener (this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
       }
    @Override
    public void onSensorChanged(SensorEvent event) {
    // limpa valores da tela
        currentX.setText("0.0");
        currentY.setText("0.0");
        currentZ.setText("0.0");

        currentX.setText(Float.toString(deltaX));
        currentY.setText(Float.toString(deltaY));
        currentZ.setText(Float.toString(deltaZ));

        deltaX = Math.abs(lastX-event.values[0]);
        deltaY = Math.abs(lastY-event.values[0]);
        deltaZ = Math.abs(lastZ-event.values[0]);

        lastX = event.values[0];
        lastY = event.values[1];
        lastZ = event.values[2];
    }

        @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}