package com.duomobsoft.theme.nature;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.duomobsoft.theme.nature.R;

public class InfoActivity extends Activity implements OnClickListener {
	private TextView mInstructions;
	private TextView mGetLite;
	private TextView mThemes;
	private TextView mEmail;
	private TextView mGetADW;
	private TextView mGetGOL;
	private TextView mDonate;

	private static final int DIALOG_INSTRUCTIONS = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		mInstructions = (TextView) findViewById(R.id.instructions);
		mGetLite = (TextView) findViewById(R.id.crazyhomelite);
		mGetADW = (TextView) findViewById(R.id.adw);
		mThemes = (TextView) findViewById(R.id.search_crazythemes);
		mEmail = (TextView) findViewById(R.id.email);
		mGetGOL = (TextView) findViewById(R.id.gol);
		mDonate = (TextView) findViewById(R.id.Donate);

		mInstructions.setOnClickListener(this);
		mGetLite.setOnClickListener(this);
		mGetADW.setOnClickListener(this);
		mThemes.setOnClickListener(this);
		mEmail.setOnClickListener(this);
		mGetGOL.setOnClickListener(this);
		mDonate.setOnClickListener(this);

		// //////////////////////////////
		// super.onCreate(savedInstanceState);

	}

	public void onClick(View v) {
		if (v == mInstructions)
			showDialog(DIALOG_INSTRUCTIONS);
		else if (v == mGetLite) {
			final Intent searchIntent = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("market://search?q=pname:com.cdproductions.apps.crazyhomelite"));
			startActivity(searchIntent);
		} else if (v == mGetADW) {
			final Intent searchIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://search?q=pname:org.adw.launcher"));
			startActivity(searchIntent);
		} else if (v == mThemes) {
			final Intent searchIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://search?q=duomob theme"));
			startActivity(searchIntent);

		} else if (v == mEmail) {
			Intent intent = new Intent();
			intent.setClass(this, GalleryExample.class);
			startActivity(intent);

		} else if (v == mGetGOL) {
			final Intent searchIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://details?id=com.gau.go.launcherex"));
			startActivity(searchIntent);

		} else if (v == mDonate) {
			final Intent searchIntent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://duomob.com/?page_id=291"));
			startActivity(searchIntent);

		}
	}

	@Override
	public void onBackPressed() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								System.gc();
								finish();
								System.exit(0);
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
		return;
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_INSTRUCTIONS:
			return new AlertDialog.Builder(InfoActivity.this)
					.setTitle("Instructions").setMessage(R.string.instructions)
					.create();

		}
		return null;
	}

}