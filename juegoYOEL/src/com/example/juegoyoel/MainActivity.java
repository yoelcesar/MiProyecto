package com.example.juegoyoel;

import android.app.Activity;
import android.os.Bundle;
import com.example.juegoyoel.juegoVista;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		juegoVista game = new juegoVista();
        setContentView(game);
    }

	private void setContentView(juegoVista game) {
		
		
	}
}