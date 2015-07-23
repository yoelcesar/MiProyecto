package com.example.juegoyoel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
public class juegoVista extends View implements Runnable{
	private final static int INTETRVAL = 25;
	private boolean running = true;
	private Paint paint;
	private malos[] malos;
	private int puntos = 0;
	private long tiempo = System.currentTimeMillis();
	
	private Bitmap bmpFundo; //declaramos una imagen
	
	private boolean iniciarjuego = false;
	
	public juegoVista(Context context){
		super (context);
		
		paint = new Paint();
		Thread minhaThread = new Thread(this);// clase exixtente para motor de tiempo(bucle/hilo)
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
	}
	
    public void iniciarjuego(){
    	malos = new malos[getHeight()/50];//instaciamos los malos en un 50%
    	for (int i = 0; i < malos.length; i++) {
			int y = i*-50;
			int x = (int) (Math.random()*(getHeight()-25));
			malos[i] = new malos(x, y, getResources());// id del malo
		}
	
	     bmpFundo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);//cargar la imagen
	     bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(), true);// cambiamos las medidas de la imagen de fondo 
         iniciarjuego = true;
	}
    // medodo existente iniciando el juego
    public void run(){
    	while (running){
    		try{
    			Thread.sleep(INTETRVAL);//retardo de tiempo    			
    		}catch (Exception e){
    			Log.e("juego","bbb");
    		}
    		update();
    	}
    }
    //metodo actualizar existente propio de java
    public void update(){
    	if (iniciarjuego==false){
    		return;
    	}
    	for (int i = 0; i < malos.length; i++){
    		malos[i].mexe(getHeight(), getWidth());
    	}
    	
    	//invocar el metodo draw
    	postInvalidate();
    }
 // metodo que dibuja todas las imagenes y figuras
 	public void draw(Canvas canvas) {
 		super.draw(canvas);
 		if (iniciarjuego==false) {
 			iniciarjuego();
 		}
 		
 		// color de fondo
 		canvas.drawBitmap(bmpFundo, 0, 0, paint);// cargamos la imagen de fondo
 		//definir color  de fondo
 		//paint.setColor(Color.RED);
 		for (int i = 0; i < malos.length; i++) {
			malos[i].draw(canvas, paint);
		}
 		
 		//definir el color de  texto que aperecera la pantalla
 		paint.setColor(Color.BLUE);
 		paint.setTextSize(30);//tamaño del texto
		canvas.drawText("puntos:" + puntos , 0, 30, paint);
		
		int segundos = (int) (System.currentTimeMillis()-tiempo)/1000;
		canvas.drawText("tiempo:"+ segundos, 200, 30, paint);
 	} 
 	
 	public boolean onTouchEvent(MotionEvent event){
 		//pega la pantalla
 		int action = event.getAction();
 		//pega la posición  del dedo
 		int x = (int) event.getX();
 		int y = (int) event.getY();
 		if (action==MotionEvent.ACTION_DOWN ) {
 			//cuando toca el dedo ejecuta la siguiente funcuin
 			for (int i = 0; i < malos.length; i++) {
				if (malos[i].colide(x,y)) { /////////........................
					malos[i].setX(-50);//.......................
					puntos ++;
				}
			}
		}else if (action==MotionEvent.ACTION_UP){
			//soltar pantalla con el dedo
			
		}else if (action==MotionEvent.ACTION_MOVE){
			//moviniemto del dedo
		}
		return super.onTouchEvent(event);
 		
 	}
 	//termina el juego
 	public void terminar(){
 		running = false;
 	}
    
}
