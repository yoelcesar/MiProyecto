package com.example.juegoyoel;

import java.util.ArrayList;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
public class juegoVista extends View implements Runnable{

	private final static int INTETRVAL = 25;
	private boolean running = true;
	private Paint paint;
	public Zombi[] malos;
	private int puntos = 0;
	private long tiempo = System.currentTimeMillis();
	private MediaPlayer sonido = null;
	private Bitmap bmpFundo; //declaramos una imagen
	
	private boolean iniciarjuego = false;
	
	public juegoVista(Context context){
		super (context);
	
		paint = new Paint();
		Thread minhaThread = new Thread(this);// clase exixtente para motor de tiempo(bucle/hilo)
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
		this.sonido = MediaPlayer.create(context,R.raw.pong);
	}
	
    public void iniciarjuego(){
    	malos =  new Zombi[getHeight()/50];
    	for (int i = 0; i < malos.length ; i++ ) {
			int y = i*-50;
			int x = (int) (Math.random()*(getHeight()-25));
			malos[i]=new Zombi(x, y, getResources());
		}
    	bmpFundo = BitmapFactory.decodeResource(getResources(), R.drawable.fondo);//cargar la imagen
 	    bmpFundo = Bitmap.createScaledBitmap(bmpFundo, getWidth(), getHeight(), true);// cambiamos las medidas de laimagen de fondo   
	     iniciarjuego = true;
	}
    // medodo existente iniciando juego
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
 		
		//desenha color de fundo
 		//canvas.drawColor(Color.BLUE);// color de fondo
 		canvas.drawBitmap(bmpFundo, 0, 0, paint);// cargamos la imagen de fondo
 		//define a cor do desenho
 		//paint.setColor(Color.RED);
 		for (int i = 0; i < malos.length; i++) {
			malos[i].draw(canvas, paint);
		}
 		
 		//definir el color text
 		paint.setColor(Color.BLUE);
 		paint.setTextSize(30);//tamaño del texto
		canvas.drawText("puntos:" + puntos , 0, 30, paint);
		
		int segundos = (int) (System.currentTimeMillis()-tiempo)/1000;
		canvas.drawText("tiempo:"+ segundos, 200, 30, paint);
 	} 
 	
 	public boolean onTouchEvent(MotionEvent event){
 		//pega la pantalla
 		int action = event.getAction();
 		//pega la posision del dedo
 		int x = (int) event.getX();
 		int y = (int) event.getY();
 		if (action==MotionEvent.ACTION_DOWN ) {
 			//toca el dedo
 			for (int i = 0; i < malos.length; i++) {
				if (malos[i].colide(x,y)) { /////////........................
					malos[i].setX(-50);//.......................
					sonido.start();
					puntos ++;
				}
			}
		}else if (action==MotionEvent.ACTION_UP){
			//soltar pantalla
			
		}else if (action==MotionEvent.ACTION_MOVE){
			//moviniemto del dedo
		}
		return super.onTouchEvent(event);
 		
 	}
 	//terminar el juego
 	public void terminar(){
 		running = false;
 	}
    
}
