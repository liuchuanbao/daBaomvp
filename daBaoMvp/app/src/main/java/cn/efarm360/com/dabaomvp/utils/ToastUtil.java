package cn.efarm360.com.dabaomvp.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;


public class ToastUtil {
	/**
	 * handler to show toasts safely
	 */
	private static Handler mHandler = null;

	private static Toast toast = null;

	private static Toast customToast = null;

	public static Toast makeToast(Context mContext, int resId) {
		return Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
	}

	public static Toast makeToast(Context mContext, String text) {
		return Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
	}

	public static void showToast(Context mContext, int resId) {
		final Context context = mContext;
		final int resid = resId;
		makeMainThreadHandler(mContext).post(new Runnable() {

			@Override
			public void run() {
				if (toast != null) {
					toast.setText(resid);
					toast.setDuration(Toast.LENGTH_SHORT);
				} else {
					toast = Toast.makeText(context, resid, Toast.LENGTH_SHORT);
				}

				toast.show();
			}
		});

	}

	public static void showToast(Context mContext, String text) {
		final Context context = mContext;
		final String msg = text;
		if (msg == null || msg.length() <= 0)
		{
			return;
		}
		makeMainThreadHandler(mContext).post(new Runnable() {

			@Override
			public void run() {
				if (toast != null) {
					toast.setText(msg);
					toast.setDuration(Toast.LENGTH_SHORT);
				} else {
					toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
				}
				toast.show();
			}
		});
	}

	public static void showLongToast(Context mContext, int resId) {
		final Context context = mContext;
		final int resid = resId;
		makeMainThreadHandler(mContext).post(new Runnable() {

			@Override
			public void run() {
				if (toast != null) {
					toast.setText(resid);
					toast.setDuration(Toast.LENGTH_LONG);
				} else {
					toast = Toast.makeText(context, resid, Toast.LENGTH_LONG);
				}
				toast.show();
			}
		});

	}

	public static void showLongToast(Context mContext, String text) {
		final Context context = mContext;
		final String msg = text;
		makeMainThreadHandler(mContext).post(new Runnable() {

			@Override
			public void run() {
				if (toast != null) {
					toast.setText(msg);
					toast.setDuration(Toast.LENGTH_LONG);
				} else {
					toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
				}
				toast.show();
			}
		});
	}

	private static Handler makeMainThreadHandler(Context context) {
		if (context == null) {
//			context = MyApplication.getInstance().getApplicationContext();
		}
		if (mHandler == null) {
			mHandler = new Handler(context.getMainLooper());
		}

		return mHandler;
	}

//	public static void showCustomToast(Context mContext, int resId) {
//		final Context context = mContext;
//		final int resid = resId;
//		makeMainThreadHandler(mContext).post(new Runnable() {
//
//			@Override
//			public void run() {
//				if (customToast == null) {
//					customToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
//					LayoutInflater inflater = (LayoutInflater) context
//							.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//					View view = inflater.inflate(R.layout.custom_toast_layout, null);
//					TextView textView = (TextView) view.findViewById(R.id.custom_toast_text);
//					textView.setText(context.getString(resid));
//					customToast.setView(view);
//				} else {
//					View view = customToast.getView();
//					TextView textView = (TextView) view.findViewById(R.id.custom_toast_text);
//					textView.setText(context.getString(resid));
//				}
//				customToast.show();
//			}
//		});
//	}
}
