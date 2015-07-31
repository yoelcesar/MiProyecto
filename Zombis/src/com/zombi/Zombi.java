package com.zombi;

import unifran.ely.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Zombi extends Retangulo {
	
	private Rect src,dst;
	private int currentFrame=0;
    private int tLimite=5;
    private int t=0;
    public int ancho;
    public int alto;
	public Zombi(int x, int y,int _ancho,int _alto, Bitmap image) {
		super(x, y, _ancho, _alto,image);
		this.ancho=_ancho;
		this.alto=_alto;
	}
	public void mexe(int height, int width) {
		if (getY()<height) {
			setY(getY()+5);
		} else {
			int x = (int) (Math.random() * (width-25));
			setX(x);
			setY(-25);
		}
	}
	private void secuencia(){
		if(t==tLimite){
		currentFrame = ++currentFrame % 2;
		  t=0;
	      }else t++;
	}
	public void draw(Canvas canvas, Paint paint) {
		 secuencia();
		
	     int srcX=currentFrame * ancho;
		 int srcY = currentFrame*alto;
		 src = new Rect(srcX,srcY,srcX + ancho,srcY + alto);
		 dst = new Rect (x,y,x+ancho,y+alto);
		 canvas.drawBitmap(image, src, dst,null);
	}
}
