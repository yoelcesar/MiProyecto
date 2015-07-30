package com.example.juegoyoel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
// podemos extender todos los metodos de la clase figura
public class Zombi extends figura {
	private static Bitmap bmp;// declaramos la imagen
//	resourser = ubicacion de la imagen 
	public Zombi(int x, int y, Resources res) {
		super(x, y, 40, 40);
		if (bmp==null) {
			//instancio la imagem de resource
			bmp = BitmapFactory.decodeResource(res, R.drawable.zombi);
			//redimensiona imagem
			bmp = Bitmap.createScaledBitmap(bmp, 40, 40, true);
		}
	}
	// metodo para que baje el enemigo
	public void mexe(int height, int width) {
		if (getY()<height) {
			setY(getY()+5);
		} else {
			// pomenos la posicion aleatoria de 0 - el ancho de la pantalla menos 25
			int x = (int) (Math.random() * (width-25));
			setX(x);
			setY(-25);
		}
	}
	public void draw(Canvas canvas, Paint paint) {
		//paint.setColor(Color.RED);
		//canvas.drawRect(getX(),getY(),
		//		getX()+getWidth(), getY()+getHeight(),
		//		paint);
		canvas.drawBitmap(bmp, getX(), getY(), paint);
	}
}
