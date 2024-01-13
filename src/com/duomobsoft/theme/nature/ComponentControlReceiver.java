package com.duomobsoft.theme.nature;

import com.duomobsoft.theme.nature.Constants;
import com.duomobsoft.theme.nature.GoLauncherUtils;
import com.duomobsoft.theme.nature.NotificationActivity;
import com.duomobsoft.theme.nature.ThemeUtils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ComponentControlReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Constants.ACTION_HIDE_THEME_ICON.equals(action)) {
			String pkgName = intent.getStringExtra(Constants.PKGNAME_STRING);
			if (context.getPackageName().equals(pkgName)) {
				ComponentUtils.disableComponent(context,
						context.getPackageName(),
						NotificationActivity.class.getName());
				ThemeUtils.setEverUsed(context);
			}
			ThemeUtils.inactiveApplyThemeFlag(context);
		} else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)) {
			if (isGoLauncher(intent)) {
				ComponentUtils.enableComponent(context,
						context.getPackageName(),
						NotificationActivity.class.getName());
				GoLauncherUtils.setGoLauncherUninstalled(context, true);
			}
		} else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
			if (isGoLauncher(intent)) {
				if (ThemeUtils.isEverUsed(context)) {
					ComponentUtils.disableComponent(context,
							context.getPackageName(),
							NotificationActivity.class.getName());
				}
				GoLauncherUtils.setGoLauncherUninstalled(context, false);
			}
		} else if (Intent.ACTION_PACKAGE_ADDED.equals(action)) {
			if (isGoLauncher(intent)) {
				// if (ThemeUtils.isEverUsed(context)) {
				// ComponentUtils.disableComponent(context,
				// context.getPackageName(),
				// NotificationActivity.class.getName());
				// }
				GoLauncherUtils.setGoLauncherUninstalled(context, false);
			}
		}
	}

	private boolean isGoLauncher(Intent intent) {
		String dataString = intent.getDataString();
		if (dataString != null && dataString.length() > 8) {
			String packageName = dataString.substring(8);
			return Constants.PACKAGE_LAUNCHER.equals(packageName);
		} else {
			return false;
		}
	}
}
