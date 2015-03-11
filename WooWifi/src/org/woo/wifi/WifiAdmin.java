package org.woo.wifi;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

import android.util.Log;

public class WifiAdmin {
	// 定义Log TAG
	private final static String TAG = "WifiAdmin";
	private StringBuffer mStringBuffer = new StringBuffer();
	private ScanResult mScanResult;
	
	// 定义WifiManager对象
	private WifiManager mWifiManager;
	// 定义WifiInfo对象
	private WifiInfo mWifiInfo;
	// 扫描出的网络连接列表
	private List<ScanResult> mWifiScanResultList;
	// 网络连接列表
	private List<WifiConfiguration> mWifiConfigurationList;
	// 定义一个WifiLock
	WifiLock mWifiLock;

	/**
	 * 构造方法
	 */
	public WifiAdmin(Context context) {
		// 取得WifiManager对象
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// 取得WifiInfo对象
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	/**
	 * 清空mStringBuffer
	 */
	private void clearStringBuffer() {
		// 清空mStringBuffer
		if (mStringBuffer != null) {
			mStringBuffer = new StringBuffer();
		}	
	}
	
	/**
	 * 打开Wifi
	 */
	public String openWifi() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 打开Wifi
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
			mStringBuffer = mStringBuffer.append("打开Wifi成功");
		}else{
			mStringBuffer = mStringBuffer.append("Wifi已打开");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 关闭Wifi
	 */
	public String closeWifi() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 关闭Wifi
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
			mStringBuffer = mStringBuffer.append("关闭Wifi成功");
		}else{
			mStringBuffer = mStringBuffer.append("Wifi已关闭");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 检查当前Wifi状态
	 * WIFI网卡的状态是由一系列的整形常量来表示
	 * WIFI_STATE_DISABLING 0 
	 * WIFI_STATE_DISABLED  1 
	 * WIFI_STATE_ENABLING  2
	 * WIFI_STATE_ENABLED   3
	 * WIFI_STATE_UNKNOWN   4
	 */
	public String checkWifiState() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 检查当前Wifi状态
		if (mWifiManager.getWifiState() == 0) {
			mStringBuffer = mStringBuffer.append("Wifi正在关闭");
		} else if (mWifiManager.getWifiState() == 1) {
			mStringBuffer = mStringBuffer.append("Wifi已经关闭");
		} else if (mWifiManager.getWifiState() == 2) {
			mStringBuffer = mStringBuffer.append("Wifi正在打开");
		} else if (mWifiManager.getWifiState() == 3) {
			mStringBuffer = mStringBuffer.append("Wifi已经打开");
		} else {
			mStringBuffer = mStringBuffer.append("---_---晕......没有获取到状态---_---");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 创建一个WifiLock
	 */	
	public String createWifiLock() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 创建一个WifiLock
		mWifiLock = mWifiManager.createWifiLock("Test");
		mStringBuffer = mStringBuffer.append("WifiLock(Test)创建成功");
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * 锁定WifiLock
	 */	
	public String acquireWifiLock() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 判断WifiLock是否锁定
		if (mWifiLock.isHeld()) {
			mStringBuffer = mStringBuffer.append("WifiLoc已经锁定");
		}else{
			// 锁定WifiLock
			mWifiLock.acquire();
			mStringBuffer = mStringBuffer.append("WifiLock锁定成功");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * 解锁WifiLock
	 */	
	public String releaseWifiLock() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 判断WifiLock是否锁定
		if (mWifiLock.isHeld()) {
			// 解锁WifiLock
			mWifiLock.release();
			mStringBuffer = mStringBuffer.append("WifiLock解锁成功");
		}else{
			mStringBuffer = mStringBuffer.append("WifiLock已经解锁");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * 扫描周边Wifi网络(原始数据版)
	 */
	public String scanWifi01() {
		// 每次点击扫描之前清空上一次的扫描结果
		clearStringBuffer();
		// 开始扫描Wifi网络
		mWifiManager.startScan();
		// 获取扫描结果列表
		mWifiScanResultList = mWifiManager.getScanResults();
		if (mWifiScanResultList != null) {
			mStringBuffer = mStringBuffer.append("当前区域存在无线网络，请查看扫描结果:\n");
			// 输出扫描列表
			for (int i = 0; i < mWifiScanResultList.size(); i++) {
				mScanResult = mWifiScanResultList.get(i);
				mStringBuffer = mStringBuffer.append("NO.").append(i + 1).append(" :\n")
						.append(mScanResult.toString()).append("\n\n");
			}
		} else {
			mStringBuffer = mStringBuffer.append("当前区域没有无线网络");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 扫描周边Wifi网络(整理数据版)
	 */
	public String scanWifi02() {
		// 每次点击扫描之前清空上一次的扫描结果
		clearStringBuffer();
		// 开始扫描Wifi网络
		mWifiManager.startScan();
		// 获取扫描结果列表
		mWifiScanResultList = mWifiManager.getScanResults();
		if (mWifiScanResultList != null) {
			mStringBuffer = mStringBuffer.append("当前区域存在无线网络，请查看扫描结果:\n");
			// 输出扫描列表
			for (int i = 0; i < mWifiScanResultList.size(); i++) {
				mScanResult = mWifiScanResultList.get(i);
				mStringBuffer = mStringBuffer.append("NO.").append(i + 1).append(" :\n")
						.append("SSID->").append(mScanResult.SSID).append("\n")
						.append("BSSID->").append(mScanResult.BSSID).append("\n")
						.append("capabilities->").append(mScanResult.capabilities).append("\n")
						.append("frequency->").append(mScanResult.frequency).append("\n")
						.append("level->").append(mScanResult.level).append("\n")
						.append("describeContents->").append(mScanResult.describeContents()).append("\n")
						.append("\n\n");
			}
		} else {
			mStringBuffer = mStringBuffer.append("当前区域没有无线网络");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 连接指定Wifi网络
	 */
	public String connectWifi() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 连接指定Wifi网络
		mWifiInfo = mWifiManager.getConnectionInfo();
		mStringBuffer = mStringBuffer.append("连接指定Wifi网络:\n")
				.append(mWifiInfo.toString());
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 断开当前连接的Wifi网络
	 */
	public String disconnectWifi() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 获取当前连接的ID
		int netId = getNetworkId();
		// 断开当前连接的Wifi网络
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
		mWifiInfo = null;
		mStringBuffer = mStringBuffer.append("断开当前连接的网络");
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 检查当前Wifi网络状态和信息(原始数据版)
	 * 
	 * @return String
	 */
	public String checkNetWorkState01() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 检查当前Wifi网络状态和信息
		if (mWifiInfo != null) {
			mStringBuffer = mStringBuffer.append("网络正常工作,当前网络详细信息如下:\n")
					.append(mWifiInfo.toString());
		} else {
			mStringBuffer = mStringBuffer.append("网络已断开");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * 检查当前Wifi网络状态和信息(整理数据版)
	 * 
	 * @return String
	 */
	public String checkNetWorkState02() {
		// 清空mStringBuffer
		clearStringBuffer();
		// 检查当前Wifi网络状态和信息
		if (mWifiInfo != null) {
			mStringBuffer = mStringBuffer.append("网络正常工作,当前网络详细信息如下:\n")
					.append("NetworkId->").append(mWifiInfo.getNetworkId()).append("\n")
					.append("IpAddress->").append(mWifiInfo.getIpAddress()).append("\n")
					.append("MacAddress->").append(mWifiInfo.getMacAddress()).append("\n")
					.append("BSSID->").append(mWifiInfo.getBSSID()).append("\n")
					.append("SSID->").append(mWifiInfo.getSSID()).append("\n")
					.append("HiddenSSID->").append(mWifiInfo.getHiddenSSID()).append("\n")
					.append("Rssi->").append(mWifiInfo.getRssi()).append("\n")
					.append("LinkSpeed->").append(mWifiInfo.getLinkSpeed()).append("\n")
					.append("describeContents->").append(mWifiInfo.describeContents()).append("\n");
		} else {
			mStringBuffer = mStringBuffer.append("网络已断开");
		}
		// 输出Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	// 获取当前连接的ID
	public int getNetworkId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// 获取当前IP地址
	public int getIPAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// 获取MAC地址
	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	// 获取接入点的BSSID
	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	// 获取WifiInfo的所有信息包
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}
	
    //获取ScanResult网络列表  
    public List<ScanResult> getWifiScanResultList(){  
        return mWifiScanResultList;  
    }  

	// 获取配置好的网络
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurationList;
	}

	// 指定配置好的网络进行连接
	public void connectConfiguration(int index) {
		// 索引大于配置好的网络索引返回
		if (index >= mWifiConfigurationList.size()) {
			return;
		}
		// 连接配置好的指定ID的网络
		mWifiManager.enableNetwork(mWifiConfigurationList.get(index).networkId,true);
	}

	// 添加一个网络并连接
	public int addNetwork(WifiConfiguration configuration) {
		int wcgID = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgID, true);
		return wcgID;
	}
}
