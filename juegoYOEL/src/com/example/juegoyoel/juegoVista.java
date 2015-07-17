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
	
	private Bitmap bmpFundo; //declaramos una imagen
	
    public void iniciarjuego(){
	
	     bmpFundo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);//cargar la imagen
	     bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(), true);// cambiamos las medidas de laimagen de fondo 
         iniciarjuego = true;
	}
    
private int getHeight() {
	
	return 0;
}
private int getWidth() {
	
	return 0;
}
private Resources getResources() {
	
		return null;
}
}