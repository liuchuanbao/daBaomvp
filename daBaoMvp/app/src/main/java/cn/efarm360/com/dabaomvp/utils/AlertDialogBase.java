package cn.efarm360.com.dabaomvp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import cn.efarm360.com.dabaomvp.R;


public class AlertDialogBase extends Dialog {

	private TextView titleTV;
	private TextView messageTV;
	private Button positiveBtn, negativeBtn;
	private boolean isPositiveBtnOn = false, isNegativeBtnOn = false;
	private int resourceId;
	private String title = "", message = "", positiveBtnTitle = "",
			negativeBtnTitle = "";
	private View.OnClickListener positiveBtnListener, negativeBtnListener;
	private View dlgView;
	private Context context;
	private boolean showTitleBtn = false;
	
	public void showTitleBtn(boolean b)
	{
		showTitleBtn = b;
	}

	public AlertDialogBase(Context context, int resourceId, int style) {
		super(context, style);
		this.context = context;
		if (-1 != resourceId) {
			setContentView(resourceId);
			this.resourceId = resourceId;
		}

		LayoutParams Params = getWindow().getAttributes();
		Params.width = LayoutParams.FILL_PARENT;
		Params.height = LayoutParams.FILL_PARENT;
		getWindow().setAttributes(
				(LayoutParams) Params);
		
	}
//
	public AlertDialogBase(Context context, int style) {
		this(context, -1, style);
		//这里自定义布局
		resourceId = R.layout.activity_main;
	}

	public AlertDialogBase(Context context) {
		//这里自定义style
		this(context, R.style.easy_dialog_style1);
	}
	
	public void setMessage(String message) {
		if (null != message) {
			this.message = message;
			if (null != messageTV)
				messageTV.setText(message);
		}
	}
	
	public void setMessage(int id)
	{
		if (null != message) {
			message = ((Activity)context).getString(id);
			if (null != messageTV)
				messageTV.setText(message);
		}
	}

	public void setTitle(String title) {
		if (null != title) {
			this.title = title;
			if (null != titleTV)
				titleTV.setText(title);
		}
	}
	
	public void setTitle(int id)
	{
		if (null != title) {
			title = ((Activity)context).getString(id);
			if (null != titleTV)
				titleTV.setText(title);
		}
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	public View getDlgView()
	{
		return dlgView;
	}

	public Button getPositiveBtn() {
		return positiveBtn;
	}

	public Button getNegativeBtn() {
		return negativeBtn;
	}
	
	public void setDlgView(View view)
	{
		dlgView = view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(dlgView != null)
			setContentView(dlgView);
		else
			setContentView(resourceId);
		try {
			
//			Button btn = (Button) findViewById(R.id.leftBtn);
//			if(btn != null && !showTitleBtn)
//				btn.setVisibility(View.INVISIBLE);
//			btn = (Button) findViewById(R.id.rightBtn);
//			if(btn != null && !showTitleBtn)
//				btn.setVisibility(View.INVISIBLE);
//
//			titleTV = (TextView) findViewById(R.id.title_text);
//			if (titleTV != null) {
//				titleTV.setText(title);
//			}
//
//			messageTV = (TextView) findViewById(R.id.dlg_message);
//			if (messageTV != null) {
//				messageTV.setText(message);
//			}
//
//			positiveBtn = (Button) findViewById(R.id.btn_ok);
//			if (isPositiveBtnOn) {
//				positiveBtn.setVisibility(View.VISIBLE);
//				positiveBtn.setText(positiveBtnTitle);
//				positiveBtn.setOnClickListener(positiveBtnListener);
//			}
//			negativeBtn = (Button) findViewById(R.id.btn_off);
//			if (isNegativeBtnOn) {
//				negativeBtn.setVisibility(View.VISIBLE);
//				negativeBtn.setText(negativeBtnTitle);
//				negativeBtn.setOnClickListener(negativeBtnListener);
//			}
		} catch (Exception e) {

		}
	}

	public void addPositiveButton(String title,
			View.OnClickListener positiveBtnListener) {
		isPositiveBtnOn = true;
		positiveBtnTitle = title;
		this.positiveBtnListener = positiveBtnListener;

		if (positiveBtn != null) {
			positiveBtn.setText(positiveBtnTitle);
			positiveBtn.setOnClickListener(positiveBtnListener);
		}
	}

	public void addNegativeButton(String title,
			View.OnClickListener negativeBtnListener) {
		isNegativeBtnOn = true;
		negativeBtnTitle = title;
		this.negativeBtnListener = negativeBtnListener;

		if (negativeBtn != null) {
			negativeBtn.setText(negativeBtnTitle);
			negativeBtn.setOnClickListener(negativeBtnListener);
		}
	}

	public void show(String title, String message, String posBtn,
					 String negBtn, View.OnClickListener clickListener) {
		addPositiveButton(posBtn, clickListener);
		addNegativeButton(negBtn, clickListener);
		setMessage(message);
		setTitle(title);
		show();
	}

	public static AlertDialogBase show(Context context, String title,
									   String message, String posBtn, String negBtn,
									   View.OnClickListener clickListener) {

		AlertDialogBase alert = new AlertDialogBase(context);
		alert.addPositiveButton(posBtn, clickListener);
		alert.addNegativeButton(negBtn, clickListener);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.show();
		return alert;
	}

	public static AlertDialogBase show(Context context, int resourceId,
									   String title, String message, String posBtn, String negBtn,
									   View.OnClickListener clickListener) {
		AlertDialogBase alert = new AlertDialogBase(context);
		if (!TextUtils.isEmpty(title)) {
			alert.setResourceId(resourceId);
		}
		alert.addPositiveButton(posBtn, clickListener);
		alert.addNegativeButton(negBtn, clickListener);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.show();
		return alert;
	}

	public static AlertDialogBase show(Context context, int resourceId,
									   String title, String message, String posBtn,
									   View.OnClickListener clickListener) {
		AlertDialogBase alert = new AlertDialogBase(context);
		if (!TextUtils.isEmpty(title)) {
			alert.setResourceId(resourceId);
		}
		alert.addPositiveButton(posBtn, clickListener);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.show();
		return alert;
	}

	public static AlertDialogBase show(Context context, String title,
									   String message, String posBtn, View.OnClickListener clickListener) {
		AlertDialogBase alert = new AlertDialogBase(context);
		alert.addPositiveButton(posBtn, clickListener);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.show();
		return alert;
	}

	public static AlertDialogBase showAlert(Context context, String title,
											String message) {
		String ok ="确定";
		return showAlert(context, title, message, ok);
	}

	public static AlertDialogBase showAlert(Context context, String title,
											String message, String okMsg) {
		final AlertDialogBase alert = new AlertDialogBase(context);
		View.OnClickListener clickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				alert.dismiss();
			}
		};

		alert.addPositiveButton(okMsg, clickListener);
		alert.setMessage(message);
		alert.setTitle(title);
		alert.show();

		return alert;
	}

}
