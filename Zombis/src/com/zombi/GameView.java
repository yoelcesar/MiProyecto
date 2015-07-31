package com.zombi;

import java.util.ArrayList;
import java.util.List;

import unifran.ely.R;
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
// clases principal 
public class GameView extends View implements Runnable {
	// declarar variables
	private final static int INTERVAL = 45;
	private boolean running = true;
	private Paint paint;
	private Zombi[] zombi;
	private int puntos = 0;
	private long tiempo = System.currentTimeMillis();
	private List<TempSprite> temps = new ArrayList<TempSprite>(); 
	private boolean iniciarJuego = false;
	private Bitmap bmpFondo,sangre;
	private static Bitmap bmp;
	// declarar variable para zonido
	private MediaPlayer sonido = null;
	
	public GameView(Context context) {
		super(context);
		
		paint = new Paint();
		//capturar imagen zombi
		bmp = BitmapFactory.decodeResource(getResources(), R.drawable.zombi);
		bmp = Bitmap.createScaledBitmap(bmp, bmp.getWidth()*2-2, bmp.getHeight()*2-2, true);
		sangre = BitmapFactory.decodeResource(getResources(), R.drawable.sangre);
		
		Thread minhaThread = new Thread(this);
		minhaThread.setPriority(Thread.MIN_PRIORITY);
		minhaThread.start();
		// capturar el sonido 
		this.sonido = MediaPlayer.create(context,R.raw.pong);
	}
	// metodo para iniciar juego
	public void iniciaJuego() {
		zombi = new Zombi[getHeight()/50];//instaciamos a los imagenes en 50%
		for (int i = 0; i < zombi.length; i++) {
			int y = i*-50;
			// aplicamos el metodo random
			int x = (int) (Math.random()*(getWidth()-bmp.getHeight()));
			zombi[i] = new Zombi(x, y,bmp.getWidth()/2,bmp.getHeight()/3, bmp);
		}
		//metodo para capturar imagen de pista
		bmpFondo = BitmapFactory.decodeResource(getResources(), R.drawable.pista);
		bmpFondo = Bitmap.createScaledBitmap(bmpFondo, getWidth(), getHeight(), true);
		
		iniciarJuego = true;
	}
     //metodo parta correr el juego
	public void run() {
		while (running) {
			try {
				Thread.sleep(INTERVAL);
			} catch (Exception e) {
				Log.e("Jogo", "Sleep da Thread");
			}
			update();
		}
	}
	// metodo para actualizar		
	private void update() {
		if (iniciarJuego==false) {
			return;
		}
		for (int i = 0; i < zombi.length; i++) {
			zombi[i].mexe(getHeight(), getWidth());
		}

		//invoca o método draw
		postInvalidate();
		
	}
	// metodo para dibujar imagenes, color, texto
	public void draw(Canvas canvas) {
		super.draw(canvas);
		if (iniciarJuego==false) {
			iniciaJuego();
		}
		
		//definir fonde del juego
		//canvas.drawColor(Color.GREEN);
		canvas.drawBitmap(bmpFondo, 0, 0, paint);
		
		for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).onDraw(canvas);
		}
		paint.setColor(Color.RED);
		for (int i = 0; i < zombi.length; i++) {
			zombi[i].draw(canvas, paint);
		}
		
		
		//defino color de texo
		paint.setColor(Color.BLUE);
		paint.setTextSize(30);
		canvas.drawText("Pontos: " + puntos, 0, 30, paint);
		
		int segundos = (int) (System.currentTimeMillis() - tiempo)/1000;
		canvas.drawText("Tempo: " + segundos, 200, 30, paint);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		//evento pegar
		int action = event.getAction();
		//evento la posicion de la pantalla
		int x = (int) event.getX();
		int y = (int) event.getY();
		if (action == MotionEvent.ACTION_DOWN) {
			//el dedo en la pantalla
			for (int i = 0; i < zombi.length; i++) {
				if (zombi[i].colide(x, y)) {
					zombi[i].setX(-80);
					
					temps.add(new TempSprite(temps, this, x, y, sangre));
                  
					puntos ++;
					sonido.start();
					break;
				}
			}
		
		} else if (action==MotionEvent.ACTION_UP) {
			//soltar la pantalla

		} else if (action==MotionEvent.ACTION_MOVE) {
			//movimiento del dedo
			
		}
		
		return super.onTouchEvent(event);
	}
	
	//terminar juego
	public void release() {
	 running = false;
	}
	
}
