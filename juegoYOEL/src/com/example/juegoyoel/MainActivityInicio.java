package com.example.juegoyoel;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


import com.example.juegoyoel.juegoVista;



public class MainActivityInicio extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Con esto estamos pidiendo al sistema de ventanas de Android que nos quite el nombre de la aplicación y 
		//que además oculte la barra de notificaciones
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				//se inicia el menu principal
		setContentView(R.layout.pantalla);
		
		//boton de PLAY
		TextView play = (TextView)findViewById(R.id.btnJugar);
		play.setTextColor(Color.YELLOW);
		play.setOnClickListener(new OnClickListener() {
		 @Override
		 public void onClick(View v) {
		  //Toast.makeText(getApplicationContext(), R.string.,Toast.LENGTH_SHORT).show();
		  
		  empiezaJuego();
		 }
		});
		
        
    }
	//metodos que pasa de una actividad a otra actividad
	private void empiezaJuego() {
		 Intent juego = new Intent(this, MainActivity.class);
		 this.startActivity(juego);
		}
}
