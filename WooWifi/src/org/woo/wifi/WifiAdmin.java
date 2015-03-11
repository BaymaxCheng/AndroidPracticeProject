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
	// ����Log TAG
	private final static String TAG = "WifiAdmin";
	private StringBuffer mStringBuffer = new StringBuffer();
	private ScanResult mScanResult;
	
	// ����WifiManager����
	private WifiManager mWifiManager;
	// ����WifiInfo����
	private WifiInfo mWifiInfo;
	// ɨ��������������б�
	private List<ScanResult> mWifiScanResultList;
	// ���������б�
	private List<WifiConfiguration> mWifiConfigurationList;
	// ����һ��WifiLock
	WifiLock mWifiLock;

	/**
	 * ���췽��
	 */
	public WifiAdmin(Context context) {
		// ȡ��WifiManager����
		mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		// ȡ��WifiInfo����
		mWifiInfo = mWifiManager.getConnectionInfo();
	}

	/**
	 * ���mStringBuffer
	 */
	private void clearStringBuffer() {
		// ���mStringBuffer
		if (mStringBuffer != null) {
			mStringBuffer = new StringBuffer();
		}	
	}
	
	/**
	 * ��Wifi
	 */
	public String openWifi() {
		// ���mStringBuffer
		clearStringBuffer();
		// ��Wifi
		if (!mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(true);
			mStringBuffer = mStringBuffer.append("��Wifi�ɹ�");
		}else{
			mStringBuffer = mStringBuffer.append("Wifi�Ѵ�");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * �ر�Wifi
	 */
	public String closeWifi() {
		// ���mStringBuffer
		clearStringBuffer();
		// �ر�Wifi
		if (mWifiManager.isWifiEnabled()) {
			mWifiManager.setWifiEnabled(false);
			mStringBuffer = mStringBuffer.append("�ر�Wifi�ɹ�");
		}else{
			mStringBuffer = mStringBuffer.append("Wifi�ѹر�");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ��鵱ǰWifi״̬
	 * WIFI������״̬����һϵ�е����γ�������ʾ
	 * WIFI_STATE_DISABLING 0 
	 * WIFI_STATE_DISABLED  1 
	 * WIFI_STATE_ENABLING  2
	 * WIFI_STATE_ENABLED   3
	 * WIFI_STATE_UNKNOWN   4
	 */
	public String checkWifiState() {
		// ���mStringBuffer
		clearStringBuffer();
		// ��鵱ǰWifi״̬
		if (mWifiManager.getWifiState() == 0) {
			mStringBuffer = mStringBuffer.append("Wifi���ڹر�");
		} else if (mWifiManager.getWifiState() == 1) {
			mStringBuffer = mStringBuffer.append("Wifi�Ѿ��ر�");
		} else if (mWifiManager.getWifiState() == 2) {
			mStringBuffer = mStringBuffer.append("Wifi���ڴ�");
		} else if (mWifiManager.getWifiState() == 3) {
			mStringBuffer = mStringBuffer.append("Wifi�Ѿ���");
		} else {
			mStringBuffer = mStringBuffer.append("---_---��......û�л�ȡ��״̬---_---");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ����һ��WifiLock
	 */	
	public String createWifiLock() {
		// ���mStringBuffer
		clearStringBuffer();
		// ����һ��WifiLock
		mWifiLock = mWifiManager.createWifiLock("Test");
		mStringBuffer = mStringBuffer.append("WifiLock(Test)�����ɹ�");
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * ����WifiLock
	 */	
	public String acquireWifiLock() {
		// ���mStringBuffer
		clearStringBuffer();
		// �ж�WifiLock�Ƿ�����
		if (mWifiLock.isHeld()) {
			mStringBuffer = mStringBuffer.append("WifiLoc�Ѿ�����");
		}else{
			// ����WifiLock
			mWifiLock.acquire();
			mStringBuffer = mStringBuffer.append("WifiLock�����ɹ�");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * ����WifiLock
	 */	
	public String releaseWifiLock() {
		// ���mStringBuffer
		clearStringBuffer();
		// �ж�WifiLock�Ƿ�����
		if (mWifiLock.isHeld()) {
			// ����WifiLock
			mWifiLock.release();
			mStringBuffer = mStringBuffer.append("WifiLock�����ɹ�");
		}else{
			mStringBuffer = mStringBuffer.append("WifiLock�Ѿ�����");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	/**
	 * ɨ���ܱ�Wifi����(ԭʼ���ݰ�)
	 */
	public String scanWifi01() {
		// ÿ�ε��ɨ��֮ǰ�����һ�ε�ɨ����
		clearStringBuffer();
		// ��ʼɨ��Wifi����
		mWifiManager.startScan();
		// ��ȡɨ�����б�
		mWifiScanResultList = mWifiManager.getScanResults();
		if (mWifiScanResultList != null) {
			mStringBuffer = mStringBuffer.append("��ǰ��������������磬��鿴ɨ����:\n");
			// ���ɨ���б�
			for (int i = 0; i < mWifiScanResultList.size(); i++) {
				mScanResult = mWifiScanResultList.get(i);
				mStringBuffer = mStringBuffer.append("NO.").append(i + 1).append(" :\n")
						.append(mScanResult.toString()).append("\n\n");
			}
		} else {
			mStringBuffer = mStringBuffer.append("��ǰ����û����������");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ɨ���ܱ�Wifi����(�������ݰ�)
	 */
	public String scanWifi02() {
		// ÿ�ε��ɨ��֮ǰ�����һ�ε�ɨ����
		clearStringBuffer();
		// ��ʼɨ��Wifi����
		mWifiManager.startScan();
		// ��ȡɨ�����б�
		mWifiScanResultList = mWifiManager.getScanResults();
		if (mWifiScanResultList != null) {
			mStringBuffer = mStringBuffer.append("��ǰ��������������磬��鿴ɨ����:\n");
			// ���ɨ���б�
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
			mStringBuffer = mStringBuffer.append("��ǰ����û����������");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ����ָ��Wifi����
	 */
	public String connectWifi() {
		// ���mStringBuffer
		clearStringBuffer();
		// ����ָ��Wifi����
		mWifiInfo = mWifiManager.getConnectionInfo();
		mStringBuffer = mStringBuffer.append("����ָ��Wifi����:\n")
				.append(mWifiInfo.toString());
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * �Ͽ���ǰ���ӵ�Wifi����
	 */
	public String disconnectWifi() {
		// ���mStringBuffer
		clearStringBuffer();
		// ��ȡ��ǰ���ӵ�ID
		int netId = getNetworkId();
		// �Ͽ���ǰ���ӵ�Wifi����
		mWifiManager.disableNetwork(netId);
		mWifiManager.disconnect();
		mWifiInfo = null;
		mStringBuffer = mStringBuffer.append("�Ͽ���ǰ���ӵ�����");
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ��鵱ǰWifi����״̬����Ϣ(ԭʼ���ݰ�)
	 * 
	 * @return String
	 */
	public String checkNetWorkState01() {
		// ���mStringBuffer
		clearStringBuffer();
		// ��鵱ǰWifi����״̬����Ϣ
		if (mWifiInfo != null) {
			mStringBuffer = mStringBuffer.append("������������,��ǰ������ϸ��Ϣ����:\n")
					.append(mWifiInfo.toString());
		} else {
			mStringBuffer = mStringBuffer.append("�����ѶϿ�");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}

	/**
	 * ��鵱ǰWifi����״̬����Ϣ(�������ݰ�)
	 * 
	 * @return String
	 */
	public String checkNetWorkState02() {
		// ���mStringBuffer
		clearStringBuffer();
		// ��鵱ǰWifi����״̬����Ϣ
		if (mWifiInfo != null) {
			mStringBuffer = mStringBuffer.append("������������,��ǰ������ϸ��Ϣ����:\n")
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
			mStringBuffer = mStringBuffer.append("�����ѶϿ�");
		}
		// ���Log
		Log.i(TAG, mStringBuffer.toString());
		return mStringBuffer.toString();
	}
	
	// ��ȡ��ǰ���ӵ�ID
	public int getNetworkId() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
	}

	// ��ȡ��ǰIP��ַ
	public int getIPAddress() {
		return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
	}

	// ��ȡMAC��ַ
	public String getMacAddress() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
	}

	// ��ȡ������BSSID
	public String getBSSID() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
	}

	// ��ȡWifiInfo��������Ϣ��
	public String getWifiInfo() {
		return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
	}
	
    //��ȡScanResult�����б�  
    public List<ScanResult> getWifiScanResultList(){  
        return mWifiScanResultList;  
    }  

	// ��ȡ���úõ�����
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfigurationList;
	}

	// ָ�����úõ������������
	public void connectConfiguration(int index) {
		// �����������úõ�������������
		if (index >= mWifiConfigurationList.size()) {
			return;
		}
		// �������úõ�ָ��ID������
		mWifiManager.enableNetwork(mWifiConfigurationList.get(index).networkId,true);
	}

	// ���һ�����粢����
	public int addNetwork(WifiConfiguration configuration) {
		int wcgID = mWifiManager.addNetwork(configuration);
		mWifiManager.enableNetwork(wcgID, true);
		return wcgID;
	}
}
