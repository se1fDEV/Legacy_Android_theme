package com.duomobsoft.theme.nature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;


public class MergeUtil {

	public static final int MERGE_OK = 0;
	public static final int MERGE_ERR = 1;
	private static final String SPLIT_INFO_FILE = "split_info.data";

	private static final String INDEX_FILE = "split_index";
	private static final String SPLIT_FILE_EXT = ".go";

	private static final String MERGE_FILEDIR = Environment
			.getExternalStorageDirectory() + "/GOTheme/";
	private static final String MERGE_FILENAME = "merge.apk";

	public static final String getMergeFileName() {
		return MERGE_FILEDIR + MERGE_FILENAME;
	}

	public static AttachInfo getAttachInfo(Context aContext) {
		AttachInfo ai = null;
		AssetManager am = aContext.getAssets();
		try {
			ai = new AttachInfo();
			InputStream is = am.open(SPLIT_INFO_FILE);
			ai.mAttachState = is.read();
			if (ai.mAttachState == AttachInfo.SPLIT_TYPE_URL) {
				int urllen = is.read();
				byte[] data = new byte[urllen];
				is.read(data);
				ai.mApkUrl = new String(data);
			}
		} catch (Exception e) {
			ai = null;
		}

		return ai;
	}

	public static void DoMergeFileTask(Handler aHandler, Context aContext) {
		new MergeFileTask(aHandler).execute(aContext);
	}

	private static class MergeFileTask extends
			AsyncTask<Context, Void, Boolean> {

		private boolean mMergingFile = false;
		private final byte[] lock = new byte[0];
		private Handler mHandler = null;

		public MergeFileTask(Handler aHandler) {
			mHandler = aHandler;
		}

		@Override
		protected void onPreExecute() {
			// Auto-generated method stub
			super.onPreExecute();
			File file = new File(MERGE_FILEDIR + MERGE_FILENAME);
			if (file != null && file.exists()) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 * @Override protected Boolean doInBackground(Void... params) { //String
		 * files[] = getResources().getStringArray(R.array.package_files);
		 * return mergeAssetFilesEx(getAssets(), MERGE_FILEDIR,
		 * MERGE_FILENAME);//mergeAssetFiles(getAssets(), MERGE_FILEDIR,
		 * MERGE_FILENAME, files); }
		 */

		@Override
		protected Boolean doInBackground(Context... params) {

			return mergeAssetFilesEx(params[0].getAssets(), MERGE_FILEDIR,
					MERGE_FILENAME);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			// Auto-generated method stub
			super.onPostExecute(result);

			Message message = new Message();
			if (result) {
				message.what = MERGE_OK;
			} else {
				message.what = MERGE_ERR;
			}

			mHandler.sendMessage(message);
		}

		/**
		 * @param am
		 * @param mergeFileDir
		 * @param mergeFileName
		 * @param files
		 * @return
		 */
		private boolean mergeAssetFiles(AssetManager am, String mergeFileDir,
				String mergeFileName, String[] files) {

			File file = new File(mergeFileDir);
			if (!file.exists()) {
				try {
					file.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			synchronized (lock) {
				mMergingFile = true;
			}
			file = new File(mergeFileDir + mergeFileName);
			if (file.exists()) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
					synchronized (lock) {
						mMergingFile = false;
					}
					return false;
				}
			}
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				synchronized (lock) {
					mMergingFile = false;
				}
				return false;
			}
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
				synchronized (lock) {
					mMergingFile = false;
				}
				return false;
			}

			byte[] datacache = new byte[8 * 1024];

			boolean mergeOK = true;
			for (int i = 0; i < files.length; i++) {
				InputStream is = null;
				try {
					is = am.open(files[i]);
					while (true) {
						int len = is.read(datacache);
						if (len <= 0) {
							break;
						}
						fos.write(datacache, 0, len);
					}
				} catch (Exception e) {
					mergeOK = false;
				} finally {
					try {
						is.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

				if (!mergeOK) {
					break;
				}
			}

			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!mergeOK) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			synchronized (lock) {
				mMergingFile = false;
			}
			return mergeOK;
		}

		private boolean mergeAssetFilesEx(AssetManager am, String mergeFileDir,
				String mergeFileName) {

			File file = new File(mergeFileDir);
			if (!file.exists()) {
				try {
					file.mkdirs();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			synchronized (lock) {
				mMergingFile = true;
			}
			file = new File(mergeFileDir + mergeFileName);
			if (file.exists()) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
					synchronized (lock) {
						mMergingFile = false;
					}
					return false;
				}
			}
			try {
				file.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
				synchronized (lock) {
					mMergingFile = false;
				}
				return false;
			}

			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
				synchronized (lock) {
					mMergingFile = false;
				}
				return false;
			}

			InputStream split_index = null;
			String resPrefixName = null;
			int iSplit_cnt = 0;
			try {
				split_index = am.open(INDEX_FILE);
				int iNamelen = split_index.read();
				byte[] data = new byte[iNamelen];
				split_index.read(data);
				resPrefixName = new String(data);
				iSplit_cnt = split_index.read();

			} catch (Exception e) {
				synchronized (lock) {
					mMergingFile = false;
				}

				return false;
			}

			byte[] datacache = new byte[50 * 1024];

			boolean mergeOK = true;
			for (int i = 0; i < iSplit_cnt; i++) {
				InputStream is = null;
				try {
					String name = resPrefixName + i + SPLIT_FILE_EXT;
					is = am.open(name);
					while (true) {
						int len = is.read(datacache);
						if (len <= 0) {
							break;
						}
						fos.write(datacache, 0, len);
					}
				} catch (Exception e) {
					mergeOK = false;
				} finally {
					try {
						is.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

				if (!mergeOK) {
					break;
				}
			}

			try {
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!mergeOK) {
				try {
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			synchronized (lock) {
				mMergingFile = false;
			}
			return mergeOK;
		}

	}
}
