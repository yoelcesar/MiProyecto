package com.example.juegoyoel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
public class juegoVista extends view implements Runnable{
	private boolean running = true;
	private Paint paint;
	
	private Bitmap bmpFundo; //declaramos una imagen
	private boolean iniciarjuego = false;
	
    public void iniciarjuego(){
	
	     bmpFundo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);//cargar la imagen
	     bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(), true);// cambiamos las medidas de laimagen de fondo 
         iniciarjuego = true;
	}
 // metodo que dibuja todas las imagenes y figuras
 	public void draw(Canvas canvas) {
 		super.draw(canvas);
 		if (iniciarjuego==false) {
 			iniciarjuego();
 		}
 		
 		Paint paint;
		//desenha cor de fundo
 		//canvas.drawColor(Color.BLUE);// color de fondo
 		canvas.drawBitmap(bmpFundo, 0, 0, paint);// cargamos la imagen de fondo
 		//define a cor do desenho
 		//paint.setColor(Color.RED);
 		}
 		
    
}