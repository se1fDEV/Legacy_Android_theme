package com.duomobsoft.theme.nature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.duomobsoft.theme.nature.R;
import com.github.droidfu.widgets.WebImageView;

public class GalleryExample extends Activity {

	protected static final String LOG_TAG = null;
	private Gallery gallery;
	private ImageView imgView;
	private WebImageView webImgView;
	public ProgressDialog pd;
	int position;
	// int action = -1;

	int message = -1;

	File root = android.os.Environment.getExternalStorageDirectory();

	File dir = new File(root.getAbsolutePath() + "/wallpaper_duomob");

	// public DialogInterface dialog;
	// private Handler handler;
	// HUDView mView;

	// LENGTH_LONG = 100;

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	private Integer[] Imgid = {

	R.drawable.wallpaper_thumb, R.drawable.wallpaper_2_thumb,
			R.drawable.wallpaper_3_thumb, R.drawable.wallpaper_4_thumb,
			R.drawable.wallpaper_5_thumb, R.drawable.wallpaper_6_s,
			R.drawable.wallpaper_7_s, R.drawable.wallpaper_8_s,
			R.drawable.wallpaper_9_s, R.drawable.wallpaper_10_s,
			R.drawable.wallpaper_11_s, R.drawable.wallpaper_12_s,
			R.drawable.wallpaper_13_s, R.drawable.wallpaper_14_s,
			R.drawable.wallpaper_15_s, R.drawable.wallpaper_16_s,
			R.drawable.wallpaper_17_s, R.drawable.wallpaper_18_s,
			R.drawable.wallpaper_19_s, R.drawable.wallpaper_20_s,
			R.drawable.wallpaper_21_s, R.drawable.wallpaper_22_s,
			R.drawable.wallpaper_23_s, R.drawable.wallpaper_24_s,
			R.drawable.wallpaper_25_s, R.drawable.wallpaper_26_s,
			R.drawable.wallpaper_27_s, R.drawable.wallpaper_28_s,
			R.drawable.wallpaper_29_s, R.drawable.wallpaper_30_s,
			R.drawable.wallpaper_31_s, R.drawable.wallpaper_32_s,
			R.drawable.wallpaper_33_s, R.drawable.wallpaper_34_s,
			R.drawable.wallpaper_35_s, R.drawable.wallpaper_36_s,
			R.drawable.wallpaper_37_s, R.drawable.wallpaper_38_s,
			R.drawable.wallpaper_39_s, R.drawable.wallpaper_40_s,
			R.drawable.wallpaper_41_s, R.drawable.wallpaper_42_s,
			R.drawable.wallpaper_43_s, R.drawable.wallpaper_44_s,
			R.drawable.wallpaper_45_s, R.drawable.wallpaper_46_s,
			R.drawable.wallpaper_47_s, R.drawable.wallpaper_48_s,
			R.drawable.wallpaper_49_s, R.drawable.wallpaper_50_s,
			R.drawable.wallpaper_51_s, R.drawable.wallpaper_52_s,
			R.drawable.wallpaper_53_s, R.drawable.wallpaper_54_s,
			R.drawable.wallpaper_55_s, R.drawable.wallpaper_56_s,
			R.drawable.wallpaper_57_s, R.drawable.wallpaper_58_s,
			R.drawable.wallpaper_59_s, R.drawable.wallpaper_60_s,
			R.drawable.wallpaper_61_s, R.drawable.wallpaper_62_s,
			R.drawable.wallpaper_63_s, R.drawable.wallpaper_64_s,
			R.drawable.wallpaper_65_s, R.drawable.wallpaper_66_s,
			R.drawable.wallpaper_67_s, R.drawable.wallpaper_68_s,
			R.drawable.wallpaper_69_s, R.drawable.wallpaper_70_s,
			R.drawable.wallpaper_71_s, R.drawable.wallpaper_72_s,
			R.drawable.wallpaper_73_s, R.drawable.wallpaper_74_s,
			R.drawable.wallpaper_75_s, R.drawable.wallpaper_76_s,
			R.drawable.wallpaper_77_s, R.drawable.wallpaper_78_s,
			R.drawable.wallpaper_79_s, R.drawable.wallpaper_80_s,
			R.drawable.wallpaper_81_s, R.drawable.wallpaper_82_s,
			R.drawable.wallpaper_83_s, R.drawable.wallpaper_84_s,
			R.drawable.wallpaper_85_s, R.drawable.wallpaper_86_s,
			R.drawable.wallpaper_87_s, R.drawable.wallpaper_88_s,
			R.drawable.wallpaper_89_s, R.drawable.wallpaper_90_s,
			R.drawable.wallpaper_91_s, R.drawable.wallpaper_92_s,
			R.drawable.wallpaper_93_s, R.drawable.wallpaper_94_s,
			R.drawable.wallpaper_95_s, R.drawable.wallpaper_96_s,
			R.drawable.wallpaper_97_s, R.drawable.wallpaper_98_s,
			R.drawable.wallpaper_99_s, R.drawable.wallpaper_100_s,

			R.drawable.wallpaper_101_s, R.drawable.wallpaper_102_s,
			R.drawable.wallpaper_103_s, R.drawable.wallpaper_104_s,
			R.drawable.wallpaper_105_s, R.drawable.wallpaper_106_s,
			R.drawable.wallpaper_107_s, R.drawable.wallpaper_108_s,
			R.drawable.wallpaper_109_s, R.drawable.wallpaper_110_s,
			R.drawable.wallpaper_111_s, R.drawable.wallpaper_112_s,
			R.drawable.wallpaper_113_s, R.drawable.wallpaper_114_s,
			R.drawable.wallpaper_115_s, R.drawable.wallpaper_116_s,
			R.drawable.wallpaper_117_s, R.drawable.wallpaper_118_s,
			R.drawable.wallpaper_119_s, R.drawable.wallpaper_120_s,
			R.drawable.wallpaper_121_s, R.drawable.wallpaper_122_s,
			R.drawable.wallpaper_123_s, R.drawable.wallpaper_124_s,
			R.drawable.wallpaper_125_s, R.drawable.wallpaper_126_s,
			R.drawable.wallpaper_127_s, R.drawable.wallpaper_128_s,
			R.drawable.wallpaper_129_s, R.drawable.wallpaper_130_s,
			R.drawable.wallpaper_131_s, R.drawable.wallpaper_132_s,
			R.drawable.wallpaper_133_s, R.drawable.wallpaper_134_s,
			R.drawable.wallpaper_135_s, R.drawable.wallpaper_136_s,
			R.drawable.wallpaper_137_s, R.drawable.wallpaper_138_s,
			R.drawable.wallpaper_139_s, R.drawable.wallpaper_140_s,
			R.drawable.wallpaper_141_s, R.drawable.wallpaper_142_s,
			R.drawable.wallpaper_143_s, R.drawable.wallpaper_144_s,
			R.drawable.wallpaper_145_s, R.drawable.wallpaper_146_s,
			R.drawable.wallpaper_147_s, R.drawable.wallpaper_148_s,
			R.drawable.wallpaper_149_s, R.drawable.wallpaper_150_s,
			R.drawable.wallpaper_151_s, R.drawable.wallpaper_152_s,
			R.drawable.wallpaper_153_s, R.drawable.wallpaper_154_s,
			R.drawable.wallpaper_155_s, R.drawable.wallpaper_156_s,
			R.drawable.wallpaper_157_s, R.drawable.wallpaper_158_s,
			R.drawable.wallpaper_159_s, R.drawable.wallpaper_160_s,
			R.drawable.wallpaper_161_s, R.drawable.wallpaper_162_s,
			R.drawable.wallpaper_163_s, R.drawable.wallpaper_164_s,
			R.drawable.wallpaper_165_s, R.drawable.wallpaper_166_s,
			R.drawable.wallpaper_167_s, R.drawable.wallpaper_168_s,
			R.drawable.wallpaper_169_s, R.drawable.wallpaper_170_s,
			R.drawable.wallpaper_171_s, R.drawable.wallpaper_172_s,
			R.drawable.wallpaper_173_s, R.drawable.wallpaper_174_s,
			R.drawable.wallpaper_175_s, R.drawable.wallpaper_176_s,
			R.drawable.wallpaper_177_s, R.drawable.wallpaper_178_s,
			R.drawable.wallpaper_179_s, R.drawable.wallpaper_180_s,
			R.drawable.wallpaper_181_s, R.drawable.wallpaper_182_s,
			R.drawable.wallpaper_183_s, R.drawable.wallpaper_184_s,
			R.drawable.wallpaper_185_s, R.drawable.wallpaper_186_s,
			R.drawable.wallpaper_187_s, R.drawable.wallpaper_188_s,
			R.drawable.wallpaper_189_s, R.drawable.wallpaper_190_s,
			R.drawable.wallpaper_191_s, R.drawable.wallpaper_192_s,
			R.drawable.wallpaper_193_s, R.drawable.wallpaper_194_s,
			R.drawable.wallpaper_195_s, R.drawable.wallpaper_196_s,
			R.drawable.wallpaper_197_s, R.drawable.wallpaper_198_s,
			R.drawable.wallpaper_199_s, R.drawable.wallpaper_200_s };

	private Integer[] ImgidBig = {

	R.drawable.wallpaper, R.drawable.wallpaper_2, R.drawable.wallpaper_3,
			R.drawable.wallpaper_4, R.drawable.wallpaper_5 };

	private String[] SimageURLs = {

			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_6.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_7.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_8.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_9.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_10.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_11.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_12.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_13.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_14.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_15.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_16.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_17.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_18.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_19.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_20.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_21.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_22.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_23.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_24.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_25.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_26.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_27.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_28.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_29.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_30.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_31.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_32.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_33.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_34.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_35.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_36.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_37.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_38.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_39.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_40.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_41.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_42.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_43.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_44.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_45.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_46.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_47.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_48.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_49.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_50.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_51.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_52.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_53.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_54.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_55.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_56.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_57.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_58.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_59.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_60.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_61.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_62.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_63.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_64.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_65.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_66.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_67.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_68.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_69.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_70.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_71.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_72.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_73.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_74.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_75.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_76.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_77.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_78.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_79.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_80.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_81.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_82.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_83.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_84.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_85.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_86.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_87.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_88.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_89.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_90.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_91.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_92.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_93.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_94.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_95.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_96.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_97.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_98.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_99.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_100.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_101.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_102.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_103.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_104.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_105.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_106.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_107.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_108.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_109.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_110.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_111.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_112.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_113.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_114.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_115.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_116.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_117.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_118.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_119.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_120.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_121.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_122.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_123.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_124.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_125.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_126.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_127.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_128.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_129.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_130.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_131.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_132.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_133.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_134.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_135.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_136.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_137.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_138.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_139.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_140.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_141.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_142.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_143.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_144.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_145.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_146.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_147.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_148.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_149.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_150.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_151.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_152.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_153.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_154.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_155.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_156.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_157.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_158.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_159.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_160.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_161.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_162.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_163.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_164.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_165.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_166.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_167.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_168.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_169.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_170.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_171.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_172.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_173.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_174.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_175.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_176.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_177.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_178.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_179.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_180.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_181.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_182.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_183.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_184.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_185.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_186.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_187.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_188.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_189.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_190.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_191.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_192.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_193.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_194.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_195.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_196.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_197.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_198.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_199.jpg",
			"http://dl.dropbox.com/u/61051056/Nature_wallpaper/wallpaper_200.jpg" };

	Bitmap bmImg = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gallery);

		if (dir.exists() == false) {
			dir.mkdirs();
		}

		// final ProgressDialog pd = ProgressDialog.show(GalleryExample.this,
		// "", "Please wait...", true, true);

		position = 0;
		imgView = (ImageView) findViewById(R.id.ImageView01);
		imgView.setImageResource(ImgidBig[position]);

		webImgView = (WebImageView) findViewById(R.id.webimage);

		gallery = (Gallery) findViewById(R.id.examplegallery);
		gallery.setAdapter(new AddImgAdp(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {

				if (position < 5) {

					if (imgView.getVisibility() != View.VISIBLE)
						imgView.setVisibility(View.VISIBLE);

					imgView.setImageResource(ImgidBig[position]);

				} else {

					if (imgView.getVisibility() == View.VISIBLE) {
						imgView.setVisibility(View.GONE);

						if (isOnline() == false)
							Toast.makeText(getBaseContext(),
									"Sorry, no Internet connection...", 3)
									.show();
						else
							Toast.makeText(getBaseContext(),
									"Internet connection is fine", 3).show();
					}

					if (webImgView.getImageUrl() != SimageURLs[position - 5]) {
						webImgView.reset();
						webImgView.setImageUrl(SimageURLs[position - 5]);
						webImgView.loadImage();

					}

				}

				GalleryExample.this.position = position;

			}
		});

		imgView.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				// action = -1;

				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						GalleryExample.this);
				alertDialog.setTitle("Confirmation");
				alertDialog
						.setMessage("Do you want to set this image as wallaper?");

				alertDialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

								SetImageClass setImageClass = new SetImageClass();
								setImageClass.start();

								Toast.makeText(getBaseContext(),
										"Please wait...", 1).show();

								// action = 1;
								// ProgressDialog pd =
								// ProgressDialog.show(GalleryExample.this, "",
								// "Loading. Please wait...", true);

								// action = -1;
								// pd.dismiss();
								// dialog.dismiss();

							}
						});

				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
				alertDialog.show();

				// pd = ProgressDialog.show(GalleryExample.this, "Working..",
				// "Calculating Pi", true, true);

				return true;
			}
		});

		imgView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// action = -1;

				final AlertDialog.Builder alertDialog = new AlertDialog.Builder(
						GalleryExample.this);
				alertDialog.setTitle("Confirmation");
				alertDialog
						.setMessage("Do you want to set this image as wallaper?");

				alertDialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

								SetImageClass setImageClass = new SetImageClass();
								setImageClass.start();

								Toast.makeText(getBaseContext(),
										"Please wait...", 1).show();

								// action = 1;
								// ProgressDialog pd =
								// ProgressDialog.show(GalleryExample.this, "",
								// "Loading. Please wait...", true);

								// action = -1;
								// pd.dismiss();
								// dialog.dismiss();

							}
						});

				alertDialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
				alertDialog.show();

				// pd = ProgressDialog.show(GalleryExample.this, "Working..",
				// "Calculating Pi", true, true);

				// return true;
			}
		});

		webImgView.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {

				AlertDialog alertDialog = new AlertDialog.Builder(
						GalleryExample.this)
						.setMessage(
								"Do you want to set this image as wallaper?")
						.setPositiveButton(android.R.string.yes,
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

										DownloadImageClass downloadImageClass = new DownloadImageClass();
										downloadImageClass.start();

										Toast.makeText(getBaseContext(),
												"Please wait...", 1).show();

										File file = new File(dir, "Nature_"
												+ "wallpaper_" + position
												+ ".jpg");

										if (!file.exists())
											Toast.makeText(
													getBaseContext(),
													"Downloading in progress ...",
													1).show();

										// superFun(v);

									}

								})
						.setNegativeButton(android.R.string.cancel,
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

									}
								}).create();

				alertDialog.show();

				return true;
			}
		});

		webImgView.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				AlertDialog alertDialog = new AlertDialog.Builder(
						GalleryExample.this)
						.setMessage(
								"Do you want to set this image as wallaper?")
						.setPositiveButton(android.R.string.yes,
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

										DownloadImageClass downloadImageClass = new DownloadImageClass();
										downloadImageClass.start();

										Toast.makeText(getBaseContext(),
												"Please wait...", 1).show();

										File file = new File(dir, "Nature_"
												+ "wallpaper_" + position
												+ ".jpg");

										if (!file.exists())
											Toast.makeText(
													getBaseContext(),
													"Downloading in progress ...",
													1).show();

										// superFun(v);

									}

								})
						.setNegativeButton(android.R.string.cancel,
								new OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

									}
								}).create();

				alertDialog.show();

				// return true;
			}
		});

	}

	public class AddImgAdp extends BaseAdapter {
		int GalItemBg;
		private Context cont;

		public AddImgAdp(Context c) {
			cont = c;
			TypedArray typArray = obtainStyledAttributes(R.styleable.GalleryTheme);
			GalItemBg = typArray.getResourceId(
					R.styleable.GalleryTheme_android_galleryItemBackground, 0);
			typArray.recycle();
		}

		public int getCount() {
			return Imgid.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView imgView = new ImageView(cont);

			imgView.setImageResource(Imgid[position]);
			// imgView.setLayoutParams(new Gallery.LayoutParams(80, 70));
			imgView.setScaleType(ImageView.ScaleType.FIT_XY);
			imgView.setBackgroundResource(GalItemBg);

			return imgView;
		}

	}

	private class DownloadImageClass extends Thread {

		@Override
		public void run() {
			// / long
			message = -1;

			int positionf = position;

			File file = new File(dir, "Nature_" + "wallpaper_" + positionf
					+ ".jpg");

			// DownloadImage(SimageURLs[position - 5],
			// position, fileName);

			try {

				URL url = new URL(SimageURLs[positionf - 5]);

				// String DownloadUrl = SimageURLs[position - 5];

				if (!file.exists()) {

					// long startTime = System.currentTimeMillis();
					// Log.d("DownloadManager", "download begining");
					// Log.d("DownloadManager", "download url:" + url);
					// Log.d("DownloadManager", "downloaded file name:" +
					// fileName);

					/* Open a connection to that URL. */
					URLConnection ucon = url.openConnection();

					/*
					 * Define InputStreams to read from the URLConnection.
					 */
					InputStream is = ucon.getInputStream();
					BufferedInputStream bis = new BufferedInputStream(is);

					/*
					 * Read bytes to the Buffer until there is nothing more to
					 * read(-1).
					 */
					ByteArrayBuffer baf = new ByteArrayBuffer(5000);
					int current = 0;
					while ((current = bis.read()) != -1) {
						baf.append((byte) current);
					}

					/* Convert the Bytes read to a String. */
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(baf.toByteArray());
					fos.flush();
					fos.close();

					// Log.d("DownloadManager",
					// "download ready in"
					// + ((System.currentTimeMillis() - startTime) / 1000)
					// + " sec");
					// Toast.makeText(getBaseContext(),
					// "Wallpaper is setted(was downloaded and saved)", 1)
					// .show();
					message = 0;
				} else
					message = 1;
				// Toast.makeText(getBaseContext(),
				// "Wallpaper is setted(from memory)", 1).show();

			} catch (IOException e) {
				Log.d("DownloadManager", "Error: " + e);
				message = 3;
				// Toast.makeText(getBaseContext(), "Downloaded error...",
				// 1).show();
			}

			Log.d("!!!!!!!!", "Image setted.");

			// File dir = new File
			// (root.getAbsolutePath() +
			// "/wallpaper_duomob");
			// File root = android.os.Environment
			// .getExternalStorageDirectory();
			String fileName = ("Nature_" + "wallpaper_" + positionf + ".jpg");
			fileName = root.getAbsolutePath() + "/wallpaper_duomob/" + fileName;

			FileInputStream fstream = null;
			try {
				fstream = new FileInputStream(fileName);

				GalleryExample.this.setWallpaper(fstream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Long running process

			handler.sendEmptyMessage(0);
		}

		private Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				switch (message) {

				case -1:
					Toast.makeText(getBaseContext(),
							"Error... Something gone wrong", 1).show();
					break;

				case 0:
					Toast.makeText(
							getBaseContext(),
							"Wallpaper setted(was downloaded and saved in folder)",
							1).show();

					break;

				case 1:
					Toast.makeText(getBaseContext(),
							"Wallpaper setted(from memory)", 1).show();
					break;

				case 2:
					Toast.makeText(getBaseContext(),
							"Error... Something gone wrong", 1).show();
					break;

				default:
					Toast.makeText(getBaseContext(),
							"Error... Something gone wrong", 1).show();
					break;

				}

			}
		};

	}

	private class SetImageClass extends Thread {

		@Override
		public void run() {
			// / long
			int positionf = position;

			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
					ImgidBig[positionf]);
			try {
				GalleryExample.this.setWallpaper(bitmap);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Long running process

			handler.sendEmptyMessage(0);
		}

		private Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {

				Toast.makeText(getBaseContext(), "Wallpaper setted", 1).show();

			}
		};

	}

}