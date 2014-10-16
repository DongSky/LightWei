package com.dongsky.lightwei;

import java.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

import com.dongsky.lightwei.LogoutAPI;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.net.RequestListener;
import com.dongsky.lightwei.LogoutAPI;
import com.dongsky.lightwei.AbsOpenAPI;
import com.sina.weibo.sdk.exception.*;
public class Main extends Activity{

	private TabHost tabHost;
	private LogOutRequestListener mLogoutListener = new LogOutRequestListener();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		
		tabHost=(TabHost)findViewById(android.R.id.tabhost);	//获取TabHost对象
        tabHost.setup();	//初始化TabHost组件
        LayoutInflater inflater = LayoutInflater.from(this); 	// 声明并实例化一个LayoutInflater对象  
        inflater.inflate(R.layout.fragment_main, tabHost.getTabContentView());  
        inflater.inflate(R.layout.fragment_mentioned, tabHost.getTabContentView());
        inflater.inflate(R.layout.fragment_profile, tabHost.getTabContentView());
        tabHost.addTab(tabHost.newTabSpec("tab01")
        		.setIndicator("首页")
        		.setContent(R.id.fragment_main));   //添加第一个标签页
        tabHost.addTab(tabHost.newTabSpec("tab02")
        		.setIndicator("提及")
        		.setContent(R.id.fragment_mentioned));  	//添加第二个标签页
        tabHost.addTab(tabHost.newTabSpec("tab03")
        		.setIndicator("帐户")
        		.setContent(R.id.fragment_profile));  
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id== R.id.action_quit){
			this.finish();
		return true;
		}
		if(id==R.id.action_send_new_weibo){
			Intent intent=new Intent(Main.this,SendNewWeibo.class);
			startActivity(intent);
			
			
		}
		if(id==R.id.action_logout){
			new LogoutAPI(AccessTokenKeeper.readAccessToken(Main.this)).logout(mLogoutListener);
			AccessTokenKeeper.clear(Main.this);
			
		}
		return super.onOptionsItemSelected(item);
	}

	private class LogOutRequestListener implements RequestListener
	{

		@Override
		public void onWeiboException(WeiboException p1)
		{
			// TODO: Implement this method
		}

        @Override
        public void onComplete(String response) {
			
			Intent intent1=new Intent(Main.this,Login.class);
			startActivity(intent1);
			Main.this.finish();
          /*  if (!TextUtils.isEmpty(response)) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String value = obj.getString("result");

                    if ("true".equalsIgnoreCase(value)) {
                        
                 //       mTokenView.setText(R.string.weibosdk_demo_logout_success);
                   }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }*/
        }     

	

}}
