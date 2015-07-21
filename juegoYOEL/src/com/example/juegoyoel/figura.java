package com.example.juegoyoel;

public class figura {
	private int x;
	private int y;
	private int height;
	private int width;
	
	public figura(int x, int y, int height, int width) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
    // metodo para colicion de las imagenes	detectamos el area del rectangulo
	public boolean colide(figura f) {
		if (f.getX()>x+width) return false;
		if (f.getY() > y +height) return false;
		if (f.getX()+f.getWidth()<x) return false;
		if (f.getY()+f.getHeight()<y) return false;
		return true;
	}
	// metodo para enviar la posicion del rectangulo
	public boolean colide(int x2, int y2) {
		if (x2>x+width) return false;
		if (y2>y+height) return false;
		if (x2<x) return false;
		if (y2<y) return false;
		return true;
	}
}
