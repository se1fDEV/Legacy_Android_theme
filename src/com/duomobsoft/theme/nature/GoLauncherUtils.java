package com.duomobsoft.theme.nature;

import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class GoLauncherUtils {

	public static final String NAME_GOLAUNCHER = "go_launcher";
	public static final String KEY_UNINSTALLED = "uninstalled";

	public static void startGoLauncher(Context context, String packageName,
			ComponentName componentName) throws Throwable {
		PackageManager packageMgr = context.getPackageManager();
		Intent launchIntent = packageMgr.getLaunchIntentForPackage(packageName);
		if (launchIntent != null) {
			try {
				context.startActivity(launchIntent);
			} catch (Throwable t1) {
				t1.printStackTrace();
				if (componentName != null) {
					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_LAUNCHER);
					intent.setComponent(componentName);
					try {
						context.startActivity(intent);
					} catch (Throwable t2) {
						t2.printStackTrace();
						throw t2;
					}
				} else {
					throw t1;
				}
			}
		} else {
			if (componentName != null) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.setComponent(componentName);
				try {
					context.startActivity(intent);
				} catch (Throwable t) {
					t.printStackTrace();
					throw t;
				}
			}
		}
	}

	public static void downloadGoLauncher(Context context, final String aUrl) {

		Intent intent = new Intent();
		intent.setClass(context, GoDownloadService.class);
		String fileName = "GO Launcher EX";
		intent.putExtra("downloadFileName", fileName);
		intent.putExtra(Constants.DOWNLOAD_URL_KEY, aUrl);
		context.startService(intent);
	}

	public static Result isGoLauncherExist(Context context) {
		Result result = new Result();
		PackageManager pm = context.getPackageManager();

		// Launcher
		Intent intent = new Intent("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.HOME");
		intent.addCategory("android.intent.category.DEFAULT");
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);

		int launcherSz = infos.size();
		for (int i = 0; i < launcherSz; i++) {
			ResolveInfo info = infos.get(i);
			if (null == info || null == info.activityInfo
					|| null == info.activityInfo.packageName) {
				continue;
			}
			String packageStr = info.activityInfo.packageName;
			if (packageStr.contains(Constants.PACKAGE_LAUNCHER)) {
				result.packageName = packageStr;
				result.componentName = new ComponentName(packageStr,
						info.activityInfo.name);
				result.isExist = true;
				return result;
			}
		}
		return result;
	}

	public static boolean isGoLauncherRunning(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// List<RunningAppProcessInfo> infos = am.getRunningAppProcesses();
		// for (RunningAppProcessInfo info : infos) {
		// System.out.println("---------------running process: "
		// + info.processName);
		//
		// if
		// ("com.gau.go.launcherex".equals(info.importanceReasonComponent.getPackageName()))
		// {
		// goLauncherRunning = true;
		// break;
		// }
		// }

		List<RunningServiceInfo> infos = am.getRunningServices(500);
		for (RunningServiceInfo info : infos) {
			if (Constants.PACKAGE_LAUNCHER
					.equals(info.service.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isGoLauncherUninstalled(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_GOLAUNCHER,
				Context.MODE_PRIVATE);
		return sp.getBoolean(KEY_UNINSTALLED, false);
	}

	public static void setGoLauncherUninstalled(Context context,
			boolean uninstalled) {
		SharedPreferences sp = context.getSharedPreferences(NAME_GOLAUNCHER,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(KEY_UNINSTALLED, uninstalled);
		editor.commit();
	}

}
