package com.dongsky.lightwei;

import android.app.*;
import android.content.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.dongsky.lightwei.*;
import com.sina.weibo.sdk.auth.*;
import com.sina.weibo.sdk.exception.*;
import com.sina.weibo.sdk.net.*;
import android.graphics.*;

public class SendNewWeibo extends Activity{

	private Oauth2AccessToken mAccessToken;
    private  int RESULT_LOAD_IMAGE = 0;
    private StatusesAPI mStatusesAPI;
	private EditText et1;
	private String content=null;
	private Button bt1=null;
	private ImageButton bt2=null;
	private TextView number=null;
	private String picturePath=null;
	private Bitmap bitmap=null;
	private ImageView preview1=null;
	

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			final ActionBar actionBar = getActionBar();
			setContentView(R.layout.sendnewweibo);
			Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(this);
			// 对statusAPI实例化
			mStatusesAPI = new StatusesAPI(mAccessToken);
			actionBar.setDisplayHomeAsUpEnabled(true);
			final EditText et1=(EditText)findViewById(R.id.et1);
			final TextView number=(TextView)findViewById(R.id.number);
			preview1=(ImageView)findViewById(R.id.preview1);
			//preview1.setEnabled(false);
			bt1=(Button)findViewById(R.id.bt1);
			bt2=(ImageButton)findViewById(R.id.bt2);
			bt2.setImageResource(android.R.drawable.ic_menu_gallery);
			number.setText("");
			bt1.setOnClickListener(new OnClickListener(){

					

					@Override
					public void onClick(View p1)
					{
						
							
						if(bitmap==null){
							content=et1.getText().toString();
							mStatusesAPI.update(content, null, null, mListener);
							
						}else{
							content=et1.getText().toString();
						mStatusesAPI.upload(content, bitmap, null, null, mListener);
						}

					}

				
				});
			bt2.setOnClickListener(new OnClickListener(){

					

					@Override
					public void onClick(View p2)
					{
						Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI); 
						startActivityForResult(i, RESULT_LOAD_IMAGE); 
						
						
						// TODO: Implement this method
					}

					
				});
			preview1.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View p1)
					{
						bitmap=null;
						//preview1.setEnabled(false);
						preview1.setImageBitmap(bitmap);
						bt2.setImageResource(android.R.drawable.ic_menu_gallery);
						// TODO: Implement this method
						return false;
					}


				/*	@Override
					public void onClick(View p1)
					{
						
						// TODO: Implement this method
					}*/

					
				});
			et1.addTextChangedListener(new TextWatcher(){
				
				 

					@Override
					public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
					{
						
						// TODO: Implement this method
					}

					@Override
					public void onTextChanged(CharSequence p1, int p2, int p5, int p3)
					{
						
						// TODO: Implement this method
					}

					@Override
					public void afterTextChanged(Editable p1)
					{
						int num=p1.length();
						number.setText(""+num);
						// TODO: Implement this method
					}

					
				});
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
		{
			// TODO: Implement this method
			super.onActivityResult(requestCode, resultCode, data);
			if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {  
				Uri selectedImage = data.getData();  
				String[] filePathColumn = { MediaStore.Images.Media.DATA };  

				Cursor cursor = getContentResolver().query(selectedImage,  														   filePathColumn, null, null, null);  
				cursor.moveToFirst();  
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);  
				String picturePath = cursor.getString(columnIndex);  
				cursor.close(); 
				bitmap = BitmapFactory.decodeFile(picturePath);
				bt2.setImageResource(android.R.drawable.checkbox_on_background);
				preview1.setImageBitmap(bitmap);
				preview1.setEnabled(true);
			}
		}
		
		
	
	
		
	public boolean onKeyDown(int keyCode, KeyEvent event)  

    {  

        if (keyCode == KeyEvent.KEYCODE_BACK )  

        {  

           

            AlertDialog isExit = new AlertDialog.Builder(this).create();  

          

            isExit.setTitle("系统提示");  

           

            isExit.setMessage("放弃编辑？");  

         

            isExit.setButton("确定", listener);  

            isExit.setButton2("取消", listener);  
 

            isExit.show();  



        }  



        return false;  



    }  

   

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  

    {  

        public void onClick(DialogInterface dialog, int which)  

        {  

            switch (which)  

            {  

				case AlertDialog.BUTTON_POSITIVE:

					finish();  

					break;  

				case AlertDialog.BUTTON_NEGATIVE:

					break;  

				default:  

					break;  

            }  

        }  

    };
	

	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
				// Respond to the action bar's Up/Home button
			case android.R.id.home:
				AlertDialog isExit = new AlertDialog.Builder(this).create();  



				isExit.setTitle("系统提示");  



				isExit.setMessage("放弃编辑？");  



				isExit.setButton("确定", listener);  

				isExit.setButton2("取消", listener);  


				isExit.show();  
				return true;
	    }
	    return super.onOptionsItemSelected(item);
	}

	
	private RequestListener mListener = new RequestListener( ){
		
		

		@Override
		public void onWeiboException(WeiboException p1)
		{
			if(content.length()>140){
				Toast.makeText(SendNewWeibo.this,"微博过长",Toast.LENGTH_SHORT).show();
			}
			if(content.length()==0){
				Toast.makeText(SendNewWeibo.this,"无法发送空微博",Toast.LENGTH_SHORT).show();
			}
			// TODO: Implement this method
		}

        @Override
        public void onComplete(String response) {
			
			
			SendNewWeibo.this.finish();
		/*	   if (!TextUtils.isEmpty(response)) {
			 LogUtil.i(TAG, response);
			 if (response.startsWith("{\"statuses\"")) {
			 // 调用 StatusList#parse 解析字符串成微博列表对象
			 StatusList statuses = StatusList.parse(response);
			 if (statuses != null && statuses.total_number > 0) {
			 Toast.makeText(WBStatusAPIActivity.this, 
			 "获取微博信息流成功, 条数: " + statuses.statusList.size(), 
			 Toast.LENGTH_LONG).show();
			 }
			 } else if (response.startsWith("{\"created_at\"")) {
			 // 调用 Status#parse 解析字符串成微博对象
			 Status status = Status.parse(response);
			 Toast.makeText(WBStatusAPIActivity.this, 
			 "发送一送微博成功, id = " + status.id, 
			 Toast.LENGTH_LONG).show();
			 } else {
			 //    Toast.makeText(WBStatusAPIActivity.this, response, Toast.LENGTH_LONG).show();
			 }*/
		} 
    };

}

