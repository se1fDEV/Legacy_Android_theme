package com.duomobsoft.theme.nature;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

public class ComponentUtils {
	public static void disableComponent(Context context, String pkg, String cls) {
		ComponentName component = new ComponentName(pkg, cls);
		context.getPackageManager().setComponentEnabledSetting(component,
				PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
				PackageManager.DONT_KILL_APP);
	}

	public static void enableComponent(Context context, String pkg, String cls) {
		ComponentName component = new ComponentName(pkg, cls);
		context.getPackageManager().setComponentEnabledSetting(component,
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);
	}
}
