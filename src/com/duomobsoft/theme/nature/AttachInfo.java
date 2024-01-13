package com.duomobsoft.theme.nature;

/**
 * 
 */
public class AttachInfo {
	public static final int SPLIT_TYPE_URL = 1;
	private static final int SPLIT_TYPE_APK = 2;

	public boolean IsAttachApk() {
		if (mAttachState == SPLIT_TYPE_APK) {
			return true;
		}

		return false;
	}

	public String mApkUrl = null;
	public int mAttachState = 0;
}
