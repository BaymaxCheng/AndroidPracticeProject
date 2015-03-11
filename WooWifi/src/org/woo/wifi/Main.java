package org.woo.wifi;

import org.woo.wifi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.ScrollView;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener {
	// 右侧滚动条按钮
	//private ScrollView sView;
	// Button
	private Button openWifi;
	private Button closeWifi;
	private Button checkWifiState;
	private Button createWifiLock;
	private Button acquireWifiLock;
	private Button releaseWifiLock;
	private Button scanWifi01;
	private Button scanWifi02;
	private Button connectWifi;
	private Button disconnectWifi;
	private Button checkNetWorkState01;
	private Button checkNetWorkState02;
	// TextView
	private TextView returnInfo;

	private WifiAdmin mWifiAdmin;
	
	private String mReturnInfo;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mWifiAdmin = new WifiAdmin(Main.this);
		init();
	}

	/**
	 * 按钮等控件的初始化
	 */
	public void init() {
		//sView = (ScrollView) findViewById(R.id.mScrollView);
		openWifi = (Button) findViewById(R.id.openWifi);
		closeWifi = (Button) findViewById(R.id.closeWifi);
		checkWifiState = (Button) findViewById(R.id.checkWifiState);
		createWifiLock = (Button) findViewById(R.id.createWifiLock);
		acquireWifiLock = (Button) findViewById(R.id.acquireWifiLock);
		releaseWifiLock = (Button) findViewById(R.id.releaseWifiLock);
		scanWifi01 = (Button) findViewById(R.id.scanWifi01);
		scanWifi02 = (Button) findViewById(R.id.scanWifi02);
		connectWifi = (Button) findViewById(R.id.connectWifi);
		disconnectWifi = (Button) findViewById(R.id.disconnectWifi);
		checkNetWorkState01 = (Button) findViewById(R.id.checkNetWorkState01);
		checkNetWorkState02 = (Button) findViewById(R.id.checkNetWorkState02);
		returnInfo = (TextView) findViewById(R.id.returnInfo);

		openWifi.setOnClickListener(Main.this);
		closeWifi.setOnClickListener(Main.this);
		checkWifiState.setOnClickListener(Main.this);
		createWifiLock.setOnClickListener(Main.this);
		acquireWifiLock.setOnClickListener(Main.this);
		releaseWifiLock.setOnClickListener(Main.this);
		scanWifi01.setOnClickListener(Main.this);
		scanWifi02.setOnClickListener(Main.this);
		connectWifi.setOnClickListener(Main.this);
		disconnectWifi.setOnClickListener(Main.this);
		checkNetWorkState01.setOnClickListener(Main.this);
		checkNetWorkState02.setOnClickListener(Main.this);
		returnInfo.setOnClickListener(Main.this);
	}

	/**
	 * WIFI_STATE_DISABLING 0 
	 * WIFI_STATE_DISABLED  1 
	 * WIFI_STATE_ENABLING  2
	 * WIFI_STATE_ENABLED   3
	 */
	public void openWifi() {
		mReturnInfo = mWifiAdmin.openWifi();
		returnInfo.setText(mReturnInfo);
	}

	public void closeWifi() {
		mReturnInfo = mWifiAdmin.closeWifi();
		returnInfo.setText(mReturnInfo);
	}

	public void checkWifiState() {
		mReturnInfo = mWifiAdmin.checkWifiState();
		returnInfo.setText(mReturnInfo);
	}

	public void createWifiLock() {
		mReturnInfo = mWifiAdmin.createWifiLock();
		returnInfo.setText(mReturnInfo);
	}
	
	public void acquireWifiLock() {
		mReturnInfo = mWifiAdmin.acquireWifiLock();
		returnInfo.setText(mReturnInfo);
	}
	
	public void releaseWifiLock() {
		mReturnInfo = mWifiAdmin.releaseWifiLock();
		returnInfo.setText(mReturnInfo);
	}
	
	public void scanWifi01() {
		mReturnInfo = mWifiAdmin.scanWifi01();
		returnInfo.setText(mReturnInfo);		
	}
	
	public void scanWifi02() {
		mReturnInfo = mWifiAdmin.scanWifi02();
		returnInfo.setText(mReturnInfo);		
	}

	public void connectWifi() {
		mReturnInfo = mWifiAdmin.connectWifi();
		returnInfo.setText(mReturnInfo);
//		startActivityForResult(new Intent(
//				android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
		startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
	}

	public void disconnectWifi() {
		mReturnInfo = mWifiAdmin.disconnectWifi();
		returnInfo.setText(mReturnInfo);
	}

	public void checkNetWorkState01() {
		mReturnInfo = mWifiAdmin.checkNetWorkState01();
		returnInfo.setText(mReturnInfo);
	}
	
	public void checkNetWorkState02() {
		mReturnInfo = mWifiAdmin.checkNetWorkState02();
		returnInfo.setText(mReturnInfo);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.openWifi:
			openWifi();
			break;
		case R.id.closeWifi:
			closeWifi();
			break;
		case R.id.checkWifiState:
			checkWifiState();
			break;
		case R.id.createWifiLock:
			createWifiLock();
			break;
		case R.id.acquireWifiLock:
			acquireWifiLock();
			break;
		case R.id.releaseWifiLock:
			releaseWifiLock();
			break;
		case R.id.scanWifi01:
			scanWifi01();
			break;
		case R.id.scanWifi02:
			scanWifi02();
			break;
		case R.id.connectWifi:
			connectWifi();
			break;
		case R.id.disconnectWifi:
			disconnectWifi();
			break;
		case R.id.checkNetWorkState01:
			checkNetWorkState01();
			break;
		case R.id.checkNetWorkState02:
			checkNetWorkState02();
			break;
		default:
			break;
		}
	}
}
