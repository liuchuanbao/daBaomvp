package cn.efarm360.com.dabaomvp.activity.picture;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.efarm360.com.dabaomvp.R;
import cn.efarm360.com.dabaomvp.utils.L;
import cn.efarm360.com.dabaomvp.utils.ScreenUtil;
import cn.efarm360.com.dabaomvp.utils.ToastUtil;
import cn.efarm360.com.dabaomvp.utils.Tool;

import static android.R.attr.width;

public class PicSelectActivity extends AppCompatActivity {

    Button btnPic;

    private RelativeLayout  addImgLayout;

     ImageView imgListChooseImgView;
    /** 添加图片相关 **/
    private String _sCurrPicPath = "";
    public final static int RESULT_LOCAL_IMAGE = 1001;
    public final static int RESULT_CAMERA_IMAGE = 1002;
//    权限申请
    private static final int CAMERA_REQUEST_CODE = 0x11;
    private int width = 88, height = 88;
    private MyPopupWindowS m_picDownPop;

    protected LayoutInflater mInflater;

    private LinearLayout imgListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_select);
        addImgLayout = (RelativeLayout) findViewById(R.id.addquestion_img_layout);
        mInflater = LayoutInflater.from(this);

         initView();

        //请求权限
        requestPermission();
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //显示popupWindow
                m_picDownPop.showAsLocation(Gravity.BOTTOM, 0, 0);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
            // 向用户解释为什么需要这个权限  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setMessage("申请存储权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //申请相机权限
                                ActivityCompat.requestPermissions(PicSelectActivity.this,
                                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
                            }
                        })
                        .show();
            } else {
                //申请相机权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_CODE);
            }
        } else {
//            tvPermissionStatus.setTextColor(Color.GREEN);
//            tvPermissionStatus.setText("存储权限已经申请");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                tvPermissionStatus.setTextColor(Color.GREEN);

//                tvPermissionStatus.setText("相机权限已申请");
            } else {
                //用户勾选了不再询问
                //提示用户手动打开权限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "相机权限已被禁止", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initView() {
        btnPic = (Button) findViewById(R.id.btn_pic);
        imgListView = (LinearLayout) findViewById(R.id.addquestion_imglist_view);
        imgListChooseImgView = (ImageView) findViewById(R.id.addquestion_imglist_addimg);

        m_picDownPop = new MyPopupWindowS(this);
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.picture_source_layout, null);

        Button captureImageButton = (Button) contentView
                .findViewById(R.id.buttonCaptureImage);
        Button chooseFromLocalButton = (Button) contentView
                .findViewById(R.id.buttonChooseFromLocal);
        Button cancelChooseButton = (Button) contentView
                .findViewById(R.id.cancelBtn);
        captureImageButton.setOnClickListener(pViewOnClickListener);
        chooseFromLocalButton.setOnClickListener(pViewOnClickListener);
        cancelChooseButton.setOnClickListener(pViewOnClickListener);


        m_picDownPop.setDimBackGroud(true);
        m_picDownPop.setContentView(contentView);
        ScreenUtil.GetInfo(this);
        m_picDownPop.setWindowSize(ScreenUtil.screenMin,
                ScreenUtil.dip2px(200f,this));

        m_picDownPop.setParentView(addImgLayout);
        m_picDownPop.setAnimationStyle(R.style.AnimationBottomUp);

//        try {
//            width = getResources().getDrawable(R.drawable.btn_add_img)
//                    .getIntrinsicWidth();
//            height = getResources().getDrawable(R.drawable.btn_add_img)
//                    .getIntrinsicHeight();
//        } catch (Exception e) {
//
//        }


    }
    private View.OnClickListener pViewOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            int vId = view.getId();

            switch (vId) {
                case R.id.buttonChooseFromLocal: {
                    m_picDownPop.dismiss();
                    //打开本地相册
                    choosePictureFromLocal();
                }
                break;
                case R.id.buttonCaptureImage: {
                    m_picDownPop.dismiss();
                    // 开始拍照
                    onStartPhoto();
                }
                break;
                case R.id.cancelBtn: {
                    m_picDownPop.dismiss();
                }
                break;
//                case R.id.addquestion_imglist_addimg:
//                case R.id.addquestion_img_layout: {
//                    if (IsShowMaxPic()) {
//                        return;
//                    }
//                    if (m_picDownPop != null) {
//                        m_picDownPop.dismiss();
//                    }
//
//                    //显示popupWindow
//                    m_picDownPop.showAsLocation(Gravity.BOTTOM, 0, 0);
//                }
//                break;
//                case R.id.del_work_log_audio: {
//                    delAudio();
//                }
//                break;
//                case R.id.addquestion_playaudio_btn: {
//                    playAudio();
//                }
//                break;
            }
        }

    };
    /**
     * 本地照片
     */
    private void choosePictureFromLocal() {
        new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected void onPreExecute() {
                ToastUtil.showToast(PicSelectActivity.this,
                        "正在打开相册，请稍候");
            }

            @Override
            protected Boolean doInBackground(String... params) {
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                Intent mIntent = new Intent(Intent.ACTION_GET_CONTENT);
                mIntent.setType("image/*");
                // isRefuseReload = true;
                try {
                    startActivityForResult(mIntent, RESULT_LOCAL_IMAGE);
                } catch (ActivityNotFoundException e) {
                    ToastUtil.showToast(PicSelectActivity.this,
                            "您的手机没有图库");
                }
            }
        }.execute();
    }
    @SuppressLint("SimpleDateFormat")
    private void onStartPhoto() {
//        boolean bol = StorageUtil.isSDcardExist();
//        if (!bol) {
//            ToastUtil.showToast(this, "请插入sd卡!");
//            return;
//        }

        String strRet = Environment.getExternalStorageDirectory() + "/tmppic";
        File file = new File(strRet);
        if (!file.exists()) {
            file.mkdirs();
        }

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        _sCurrPicPath = strRet + "/" + formatter.format(date) + ".jpeg";
        /**
         * Android 7.0 存在问题
         */
        File files =  new File(_sCurrPicPath);
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(PicSelectActivity.this, "cn.efarm360.com.dabaomvp.fileprovider", files);
        } else {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.fromFile(new File(_sCurrPicPath)));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, RESULT_CAMERA_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_LOCAL_IMAGE: {
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
//						Cursor cursor = getContentResolver().query(uri, null,
//								null, null, null);
//						if (cursor == null) {
//							// miui 2.3 有可能为null
//							_sCurrPicPath = uri.getPath();
//						} else {
//							cursor.moveToFirst();
//							int column_index = cursor
//									.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//							_sCurrPicPath = cursor.getString(column_index);
//						}
                            try{
                                //Android 7.0 需要读取内存的权限权限
                                _sCurrPicPath= getImagePath(uri);
                                Log.e("xyc", "onActivityResult: _sCurrPicPath =  "+_sCurrPicPath);
                            }catch(Exception e){
                                e.printStackTrace();
                                ToastUtil.showToast(PicSelectActivity.this, "选择图片失败,请重试...");
                                return;
                            }

                            onShowPhoto();
                        }
                    }
                }
                break;
                case RESULT_CAMERA_IMAGE: {
                    L.i("addfaqquerstion", "camera start deal ");
                    onShowPhoto();
                }
            }
        }
    }
    /*
	 * 从 Intent 的返回中获取图片的真实地址, 兼容 4.4(KitKat) 和 4.4之前版本
	 * Before and after KitKat(4.4), the Intent.ACTION_GET_CONTENT return different data:
	 *   before:  content://media/external/images/media/62
	 *   after:   content://com.android.providers.media.documents/document/image:62
	 *
	 * 函数取自: http://stackoverflow.com/questions/20067508/get-real-path-from-uri-android-kitkat-new-storage-access-framework/20559175#20559175
	 *     作者: http://stackoverflow.com/users/3082682/cvizv
	 *     修订: zhw  (2015.7.29)
	 */
    private String getImagePath(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }
    private void onShowPhoto() {
        L.i("addfaqquerstion", "camera start deal with custom urlpath :"
                + _sCurrPicPath);
        if (_sCurrPicPath != null && _sCurrPicPath.length() > 0) {

            BitmapFactory.Options opt = new BitmapFactory.Options();

            opt.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(_sCurrPicPath, opt);

            if (opt.mCancel || opt.outWidth == -1 || opt.outHeight == -1) {
                L.i("addfaqquerstion",
                        "camera start deal with custom urlpath returened onShowPhoto()");
                return;
            }

            int initialSize;
            int minSideLength = 640;
            int maxNumOfPixels = (int) (0.7 * 1024 * 1024);

            double w = opt.outWidth;
            double h = opt.outHeight;

            // 上下限范围
            int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                    .sqrt(w * h / maxNumOfPixels));
            int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                    Math.floor(w / minSideLength),
                    Math.floor(h / minSideLength));

            if (upperBound < lowerBound) {
                // return the larger one when there is no overlapping zone.
                initialSize = lowerBound;
            }

            if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
                initialSize = 1;
            } else if (minSideLength == -1) {
                initialSize = lowerBound;
            } else {
                initialSize = upperBound;
            }

            int roundedSize;
            if (initialSize <= 8) {
                roundedSize = 1;
                while (roundedSize < initialSize) {
                    roundedSize <<= 1;
                }
            } else {
                roundedSize = (initialSize + 7) / 8 * 8;
            }

            opt.inSampleSize = roundedSize;
            opt.inJustDecodeBounds = false;
            opt.inDither = false;
            opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
            try {
                L.i("addfaqquerstion",
                        "camera start deal with custom urlpath start decodefile");
                Bitmap bitmap = BitmapFactory.decodeFile(_sCurrPicPath, opt);
                opt = null;
                L.i("addfaqquerstion",
                        "camera start deal with custom urlpath start decodefile over :"
                                + bitmap);
                if (bitmap != null) {
                    addImgToImgListView(bitmap);
                }
            } catch (OutOfMemoryError err) {
                err.printStackTrace();
                ToastUtil.showToast(this, "图片加载异常");
                L.i("addfaqquerstion",
                        "camera start deal with custom urlpath out of memory exception");
            }
        } else {
            ToastUtil.showToast(this, "图片保存失败");
        }
    }
    private void addImgToImgListView(Bitmap bitmap) {


        Archive archive = new Archive();
        archive.setUrl(_sCurrPicPath);  //手机相册图片路径
        // archive.setData(FileUtil.byteToByte(TBitmapUtil.Bitmap2Bytes(TBitmapUtil.getimage(_sCurrPicPath))));
        // archive.setFilezize(archive.getData().length / 1024);
        archive.setFilename(_sCurrPicPath.substring(_sCurrPicPath
                .lastIndexOf("/") + 1));
        archive.setFileid(Tool.getUUID() + ".jpg");

        L.i("addfaqquerstion", "camera start show img");

        View view = mInflater.inflate(R.layout.img_has_del, null);
        ImageView imageView = (ImageView) view
                .findViewById(R.id.add_work_log_add_img);
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) imageView
                .getLayoutParams();
        lp.width = width;
        lp.height = height;
        imageView.setLayoutParams(lp);

        view.setTag(archive);


        imageView.setBackgroundDrawable(new BitmapDrawable(bitmap));
        ImageView delView = (ImageView) view
                .findViewById(R.id.del_work_log_img);
        delView.setTag(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgListView.removeView(view);
                changeAddImgLayout();
            }
        });

        imgListView.addView(view);

        changeAddImgLayout();
    }

    private void changeAddImgLayout() {
        int size = imgListView.getChildCount();
        if (size > 0) {
            addImgLayout.setVisibility(View.GONE);
//            imgShowLayout.setVisibility(View.VISIBLE);
        } else {
            addImgLayout.setVisibility(View.VISIBLE);
//            imgShowLayout.setVisibility(View.GONE);
        }
    }

}
