package com.duomobsoft.theme.nature;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;

public class ThemeUtils {
	public static final String NAME_THEME = "theme";
	public static final String KEY_APPLY_CURRENT_THEME = "apply_current_theme";
	public static final String KEY_EVER_USED = "ever_used";

	public static void activeApplyThemeFlag(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_THEME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(KEY_APPLY_CURRENT_THEME, true);
		editor.commit();
	}

	public static void inactiveApplyThemeFlag(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_THEME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(KEY_APPLY_CURRENT_THEME, false);
		editor.commit();
	}

	public static boolean getApplyThemeFlag(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_THEME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(KEY_APPLY_CURRENT_THEME, false);
	}

	public static void sendApplyThemeBroadcast(Context context) {
		Intent intent = new Intent(Constants.ACTION_MYTHEME);
		intent.putExtra(Constants.ACTION_TYPE_STRING, Constants.CHANGE_THEME);
		intent.putExtra(Constants.PKGNAME_STRING, context.getPackageName());
		context.sendBroadcast(intent);
	}

	public static void sendStartThemesPanelBroadcast(Context context) {
		Intent intent = new Intent(Constants.ACTION_START_MY_THEMES);
		context.sendBroadcast(intent);
	}

	public static void sendInactiveApplyThemeFlagBroadcast(Context context) {
		Intent intent = new Intent(Constants.ACTION_INACTIVE_APPLY_THEME_FLAG);
		intent.putExtra(Constants.PKGNAME_STRING, context.getPackageName());
		context.sendBroadcast(intent);
	}

	public static void uninstall(Context context) {
		Uri packageURI = Uri.parse("package:" + context.getPackageName());
		Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
		context.startActivity(uninstallIntent);
	}

	public static boolean isEverUsed(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_THEME,
				Context.MODE_PRIVATE);
		return sp.getBoolean(KEY_EVER_USED, false);
	}

	public static void setEverUsed(Context context) {
		SharedPreferences sp = context.getSharedPreferences(NAME_THEME,
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean(KEY_EVER_USED, true);
		editor.commit();
	}
}
