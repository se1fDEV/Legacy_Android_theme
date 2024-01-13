package com.duomobsoft.theme.nature;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ThemeControlReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (Constants.ACTION_LAUNCHER_START.equals(action)) {
			boolean flag = ThemeUtils.getApplyThemeFlag(context);
			if (flag) {
				ThemeUtils.sendApplyThemeBroadcast(context);
				ThemeUtils.inactiveApplyThemeFlag(context);
			}
		} else if (Constants.ACTION_INACTIVE_APPLY_THEME_FLAG.equals(action)) {
			// String packageName = intent
			// .getStringExtra(Constants.PKGNAME_STRING);
			// if (!context.getPackageName().equals(packageName)) {
			ThemeUtils.inactiveApplyThemeFlag(context);
			// }
		}
	}

}
