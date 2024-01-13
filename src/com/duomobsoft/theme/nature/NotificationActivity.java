package com.duomobsoft.theme.nature;

import com.duomobsoft.theme.nature.Constants;
import com.duomobsoft.theme.nature.GoLauncherUtils;
import com.duomobsoft.theme.nature.Result;
import com.duomobsoft.theme.nature.ThemeUtils;
import com.duomobsoft.theme.nature.R;

import java.util.Locale;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class NotificationActivity extends Activity {

	private boolean applyTheme = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected void onResume() {
		super.onResume();
		ThemeUtils.sendInactiveApplyThemeFlagBroadcast(this);
		checkGoLauncher();
		applyTheme = false;
	}

	private void checkGoLauncher() {
		final Result result = GoLauncherUtils.isGoLauncherExist(this);
		boolean goLauncherExist = result.isExist;
		if (!goLauncherExist) {
			if (GoLauncherUtils.isGoLauncherUninstalled(this)) {
				displayGoLauncherUninstalledDialog();
			} else {
				displayInstallGoLauncherDialog();
			}

		} else {
			displayStartGoLauncherDialog(result);
		}
	}

	private void displayGoLauncherUninstalledDialog() {

		String dialogtitle = null;
		String dialogmsg = null;
		String dialogreinstall = null;
		String dialoguninstall = null;

		String language = Locale.getDefault().getLanguage(); // zh
		if (language.equals("zh")) {
			dialogtitle = "Info";
			dialogmsg = "GO Launcher EX has been uninstalled. Would you like to install it again or uninstall this theme?";
			dialogreinstall = "Reinstall";
			dialoguninstall = "Uninstall";
		} else {
			dialogtitle = "Info";
			dialogmsg = "GO Launcher EX has been uninstalled. Would you like to install it again or uninstall this theme?";
			dialogreinstall = "Reinstall";
			dialoguninstall = "Uninstall";
		}

		new AlertDialog.Builder(this).setTitle(dialogtitle)
				.setMessage(dialogmsg)
				.setPositiveButton(dialogreinstall, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						downloadGoLauncher();
						ThemeUtils
								.activeApplyThemeFlag(NotificationActivity.this);
						NotificationActivity.this.finish();
					}
				}).setNegativeButton(dialoguninstall, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						ThemeUtils.uninstall(NotificationActivity.this);
						NotificationActivity.this.finish();
					}
				}).setOnKeyListener(new OnKeyListener() {

					public boolean onKey(DialogInterface dialog, int keyCode,
							KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK) {
							NotificationActivity.this.finish();
						}
						return false;
					}
				}).show();
	}

	private void displayInstallGoLauncherDialog() {

		String dialogtitle = null;
		String dialogmsg = null;
		String dialogok = null;
		String dialogcancel = null;

		String language = Locale.getDefault().getLanguage(); // zh
		if (language.equals("zh")) {
			dialogtitle = "Info";
			dialogmsg = "Press OK button to install GO Launcher EX";
			dialogok = "OK";
			dialogcancel = "Cancel";
		} else {
			dialogtitle = "Info";
			dialogmsg = "Press OK button to install GO Launcher EX";
			dialogok = "OK";
			dialogcancel = "Cancel";
		}

		new AlertDialog.Builder(this).setTitle(dialogtitle)
				.setMessage(dialogmsg)
				.setPositiveButton(dialogok, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						downloadGoLauncher();
						ThemeUtils
								.activeApplyThemeFlag(NotificationActivity.this);
						NotificationActivity.this.finish();
					}
				}).setNegativeButton(dialogcancel, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						NotificationActivity.this.finish();
					}
				}).setOnKeyListener(new OnKeyListener() {

					public boolean onKey(DialogInterface dialog, int keyCode,
							KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK) {
							NotificationActivity.this.finish();
						}
						return false;
					}
				}).show();
	}

	private void displayStartGoLauncherDialog(final Result result) {

		String dialogtitle = null;
		String dialogmsg = null;
		String dialogok = null;
		String dialogcancel = null;

		String language = Locale.getDefault().getLanguage(); // zh
		if (language.equals("zh")) {
			dialogtitle = "Info";
			dialogmsg = "Press OK button to launch GO Launcher EX";
			dialogok = "OK";
			dialogcancel = "Cancel";
		} else {
			dialogtitle = "Info";
			dialogmsg = "Press OK button to launch GO Launcher EX";
			dialogok = "OK";
			dialogcancel = "Cancel";
		}

		new AlertDialog.Builder(this).setTitle(dialogtitle)
				.setMessage(dialogmsg)
				.setPositiveButton(dialogok, new OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// NotificationActivity.this.finish();
						startGoLauncher(
								result.packageName == null ? Constants.PACKAGE_LAUNCHER
										: result.packageName,
								result.componentName);
					}
				}).setNegativeButton(dialogcancel, new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						NotificationActivity.this.finish();
					}
				}).setOnKeyListener(new OnKeyListener() {

					public boolean onKey(DialogInterface dialog, int keyCode,
							KeyEvent event) {
						if (keyCode == KeyEvent.KEYCODE_BACK) {
							NotificationActivity.this.finish();
						}
						return false;
					}
				}).show();
	}

	private void downloadGoLauncher() {
		GoLauncherUtils.downloadGoLauncher(this,
				Constants.DOWNLOAD_GOLAUNCHER_LINK);
	}

	private void startGoLauncher(final String packageName,
			final ComponentName componentName) {
		applyTheme = true;
		new AsyncTask<Void, Void, Boolean>() {
			private ProgressDialog pDialog = null;

			@Override
			protected void onPreExecute() {
				String msg = null;

				String language = Locale.getDefault().getLanguage(); // zh
				if (language.equals("zh")) {
					msg = "Starting GO Launcher...please wait";
				} else {
					msg = "Starting GO Launcher...please wait";
				}

				pDialog = ProgressDialog.show(NotificationActivity.this, null,
						msg, true);
			}

			@Override
			protected Boolean doInBackground(Void... v) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				NotificationActivity.this.finish();
				try {
					GoLauncherUtils.startGoLauncher(NotificationActivity.this,
							packageName, componentName);
				} catch (Throwable e) {
					return false;
				}
				return true;
			}

			protected void onPostExecute(Boolean success) {
				if (pDialog != null) {
					pDialog.dismiss();
				}
				if (!success) {

					String msg = null;

					String language = Locale.getDefault().getLanguage(); // zh
					if (language.equals("zh")) {
						msg = "Start GO Launcher EX failed, please reinstall GO Launcher EX";
					} else {
						msg = "Start GO Launcher EX failed, please reinstall GO Launcher EX";
					}

					Toast.makeText(NotificationActivity.this, msg,
							Toast.LENGTH_LONG).show();
				}
			};
		}.execute();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (applyTheme) {
			applyTheme = false;
			ThemeUtils.sendApplyThemeBroadcast(this);
		}
	}

}
