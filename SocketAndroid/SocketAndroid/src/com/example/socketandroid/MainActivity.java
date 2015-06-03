package com.example.socketandroid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button mInButton, mSendButton;
	private EditText mEditText01, mEditText02;
	//private static final String SERVERIP = "192.168.1.102";
	private static final String SERVERIP = "222.66.175.222";//"180.160.35.13";
	private static final int SERVERPORT = 12234;
	private Thread mThread = null;
	private Socket mSocket = null;
	private BufferedReader mBufferedReader = null;
	private PrintWriter mPrintWriter = null;
	private  String mStrMSG = "";
	//private static String TAG = Camera.class.getSimpleName();
	private static String TAG = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads().detectDiskWrites().detectNetwork()
        .penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects().penaltyLog().penaltyDeath()
        .build());
		
		mInButton = (Button) findViewById(R.id.myinternet_tcpclient_BtnIn);
		mSendButton = (Button) findViewById(R.id.myinternet_tcpclient_BtnSend);
		mEditText01 = (EditText) findViewById(R.id.myinternet_tcpclient_EditText01);
		mEditText02 = (EditText) findViewById(R.id.myinternet_tcpclient_EditText02);
		
		mInButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
            {
                try
	            {
		            // 鈶燬ocket瀹炰緥鍖栵紝杩炴帴鏈嶅姟鍣�
		            mSocket = new Socket(SERVERIP, SERVERPORT);
		            // 鈶¤幏鍙朣ocket杈撳叆杈撳嚭娴佽繘琛岃鍐欐搷浣�
		            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		            mPrintWriter = new PrintWriter(mSocket.getOutputStream(), true);
		        } catch (Exception e){
		                // TODO: handle exception
		                Log.e(TAG, e.toString());
		                }
		             }
		          });
		
		// 鍙戦�佹秷鎭�
		         mSendButton.setOnClickListener(new OnClickListener()
		         {
		              public void onClick(View v)
		              {
		                  try
		                  {
		                      // 鍙栧緱缂栬緫妗嗕腑鎴戜滑杈撳叆鐨勫唴瀹�
		                      String str = mEditText02.getText().toString() + "\n";
		                      // 鍙戦�佺粰鏈嶅姟鍣�
		                      mPrintWriter.print(str);
		                     mPrintWriter.flush();
		                  } catch (Exception e)
		                  {
		                      // TODO: handle exception
		                      Log.e(TAG, e.toString());
		                  }
		              }
		          });
		    mThread = new Thread(mRunnable);
		    mThread.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// 绾跨▼:鐩戝惉鏈嶅姟鍣ㄥ彂鏉ョ殑娑堟伅
    private Runnable mRunnable = new Runnable()
    {
        public void run()
        {
            while (true)
            {
                try
                {
                    if ((mStrMSG = mBufferedReader.readLine()) != null)
                    {                        
                        mStrMSG += "\n";// 娑堟伅鎹㈣
                        mHandler.sendMessage(mHandler.obtainMessage());// 鍙戦�佹秷鎭�
                    }                    
                } catch (Exception e)
                {
                    Log.e(TAG, e.toString());
                }
            }
        }
    };
    // ////////////////////////////////////////////////////////////////////////////////////
    Handler mHandler = new Handler()//鏇存柊鐣岄潰鐨勬樉绀猴紙涓嶈兘鐩存帴鍦ㄧ嚎绋嬩腑鏇存柊瑙嗗浘锛屽洜涓篈ndroid绾跨▼鏄畨鍏ㄧ殑锛�
    {
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            // 鍒锋柊
            try
            {                
                mEditText01.append(mStrMSG);// 灏嗚亰澶╄褰曟坊鍔犺繘鏉�
            } catch (Exception e)
            {
                Log.e(TAG, e.toString());
            }
        }
    };

}
