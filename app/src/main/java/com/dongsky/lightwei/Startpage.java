package com.dongsky.lightwei;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


public class Startpage extends Activity{

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			this.requestWindowFeature(Window.FEATURE_NO_TITLE);
			this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			setContentView(R.layout.start);
			
			
			new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					Intent intent = new Intent(Startpage.this,Login.class);
					startActivity(intent);
					Startpage.this.finish();
				}
			}, 3000);
			
		}

}
