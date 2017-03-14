package cn.efarm360.com.dabaomvp.utils;


import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class UpdateManager {

	private Context mContext;
	
	//提示语
	private String updateMsg = "软件需要升级，请点击确定升级";
	
	//返回的安装包url
	private String apkUrl ;
	
	
	private Dialog noticeDialog;

	private Dialog downloadDialog;


    private static final String saveFileName = Environment.getExternalStorageDirectory().getPath()+"/ljt.apk";

    /* 进度条与通知ui刷新的handler和msg常量 */
    private ProgressBar mProgress;


    private static final int DOWN_UPDATE = 1;

    private static final int DOWN_OVER = 2;

    private int progress;

    private Thread downLoadThread;

    private boolean interceptFlag = false;

    private Handler mHandler = new Handler(){
    	public void handleMessage(Message msg) {
    		switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				break;
			case DOWN_OVER:

				installApk();
				break;
			default:
				break;
			}
    	};
    };

	public UpdateManager(Context context) {
		this.mContext = context;
	}

	public static int getVersionCode(Context context)
	{
		int versionCode = Integer.valueOf(DeviceUtils.getVersionCode(context));
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return versionCode;
	}

	public static int getVersionName(Context context)
	{
		int versionCode = 1;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionCode = pi.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return versionCode;
	}

	//外部接口让主Activity调用
	public void checkUpdateInfo(boolean showToast,String url){

		apkUrl = url;
//		int versionCode = getVersionName(mContext);
		//Todo 这里需要改的
//		int _newVersionCode =2;
//		if(versionCode!=_newVersionCode)
//		{
			showNoticeDialog();
//		}
//		else
//		{
//			if(showToast)
//			{
		//TODO  自定义弹出框
//				final AlertDialogBase alertDialogBase = new AlertDialogBase(mContext);
//				alertDialogBase.setResourceId(R.layout.dlg_tipinfo);
//				alertDialogBase.setTitle(R.string.check_updata);
//				alertDialogBase.setMessage(R.string.islast);
//				alertDialogBase.setCanceledOnTouchOutside(false);
//				alertDialogBase.show();
//				alertDialogBase.addPositiveButton(mContext.getString(R.string.ok), new View.OnClickListener()
//				{
//					@Override
//					public void onClick(View v)
//					{
//						alertDialogBase.dismiss();
//					}
//				});
//			  ToastUtil.showToast(mContext,R.string.islast);
//			}
//		}

	}


	private void showNoticeDialog(){

		final AlertDialogBase alertDialog = new AlertDialogBase(mContext);
		//TODO  自定义弹出框
//		alertDialog.setResourceId(R.layout.dialog_update);
//		alertDialog.setTitle(R.string.update);
//		alertDialog.setMessage(R.string.update_mesg);

		alertDialog.addNegativeButton("以后再说",
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						alertDialog.dismiss();
					}
				});
		alertDialog.addPositiveButton("下载",
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						alertDialog.dismiss();
						showDownloadDialog();
					}
				});
		alertDialog.show();
	}

	public void showDownloadDialog(){
		if (apkUrl.trim().equals(""))
		{
			return;
		}
		Builder builder = new Builder(mContext);
		builder.setTitle("软件版本更新");
		
		final LayoutInflater inflater = LayoutInflater.from(mContext);
		//TODO  自定义弹出框
//		View v = inflater.inflate(R.layout.update_dig, null);
//		mProgress = (ProgressBar)v.findViewById(R.id.progress);
		
//		builder.setView(v);
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;
			}
		});
		downloadDialog = builder.create();
		downloadDialog.show();
		
		downloadApk();
	}
	
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);
			
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				
//				File file = new File(savePath);
//				if(!file.exists()){
//					file.mkdir();
//				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				
				int count = 0;
				byte buf[] = new byte[1024];
				
				do{   		   		
		    		int numread = is.read(buf);
		    		count += numread;
		    	    progress =(int)(((float)count / length) * 100);
		    	    //更新进度
		    	    mHandler.sendEmptyMessage(DOWN_UPDATE);
		    		if(numread <= 0){	
		    			//下载完成通知安装
		    			mHandler.sendEmptyMessage(DOWN_OVER);
		    			break;
		    		}
		    		fos.write(buf,0,numread);
		    	}while(!interceptFlag);//点击取消就停止下载.
				
				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
			
		}
	};
	
	 /**
     * 下载apk
     * @param
     */
	
	private void downloadApk(){
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}
	 /**
     * 安装apk
     * @param
     */
	private void installApk(){
		File apkfile = new File(saveFileName);
        if (!apkfile.exists()) {
            return;
        }    
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//启动新的activity
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        mContext.startActivity(i);
	
	}
}

