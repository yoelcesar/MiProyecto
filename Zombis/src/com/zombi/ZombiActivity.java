package com.zombi;



import unifran.ely.R;
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

public class ZombiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);
     //boton de PLAY
     		TextView play = (TextView)findViewById(R.id.textJugar);
     		play.setTextColor(Color.RED);
     		play.setTextSize(50);
     		play.setOnClickListener(new OnClickListener() {
     		 public void onClick(View v) {
     		  
     		  empiezaJuego();
     		 }
     		});
     		//boton de SALIR
		    TextView exit = (TextView)findViewById(R.id.textSalir);
		    exit.setTextColor(Color.YELLOW);
		    play.setTextSize(50);
		    exit.setOnClickListener(new OnClickListener() {
	
		     public void onClick(View v) {
		      finish();
		     }
		    });	
             
         }
     	//metodos que pasa de una actividad a otra actividad
     	private void empiezaJuego() {
     		 Intent juego = new Intent(this, ActivityJuego.class);
     		 this.startActivity(juego);
     		}
         

     }
