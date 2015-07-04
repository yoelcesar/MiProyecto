package com.example.suma;

import android.support.v7.app.ActionBarActivity;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	EditText a,b;
	TextView re;
	Button sumar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sumar=(Button)findViewById(R.id.btn);
		a=(EditText) findViewById(R.id.n1);
		b=(EditText) findViewById(R.id.n2);
		re=(TextView) findViewById(R.id.r);
	}
	public void sumar(View veiw){
		int x,y,q;
		
		x=Integer.parseInt(a.getText().toString());
		y=Integer.parseInt(b.getText().toString());
		q=x+y;
		re.setText(String.valueOf((int) (q)));
		
		
		}
	

	
}
