package com.duomobsoft.theme.nature;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;


public class HttpMachine {

	public static int NETTYPE_MOBILE = 0;
	public static int NETTYPE_UNICOM = 1;
	public static int NETTYPE_TELECOM = 2;

	public static boolean isCWWAPConnect(Context context) {
		boolean result = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivity.getActiveNetworkInfo();
		if (info != null && info.getType() == ConnectivityManager.TYPE_MOBILE) {
			if (Proxy.getDefaultHost() != null
					|| Proxy.getHost(context) != null) {
				result = true;
			}
		}

		return result;
	}

	public static int getNetWorkType(Context context) {
		int netType = -1;

		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String simOperator = manager.getSimOperator();
		if (simOperator != null) {
			if (simOperator.startsWith("46000")
					|| simOperator.startsWith("46002")) {
				netType = NETTYPE_MOBILE;
			} else if (simOperator.startsWith("46001")) {
				netType = NETTYPE_UNICOM;
			} else if (simOperator.startsWith("46003")) {
				netType = NETTYPE_TELECOM;
			}
		}
		return netType;
	}

	public static String getProxyHost(Context context) {
		return Proxy.getHost(context);
	}

	public static int getProxyPort(Context context) {
		return Proxy.getPort(context);
	}

}
