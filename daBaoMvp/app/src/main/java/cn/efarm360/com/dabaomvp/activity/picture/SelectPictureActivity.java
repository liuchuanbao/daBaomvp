package cn.efarm360.com.dabaomvp.activity.picture;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.efarm360.com.dabaomvp.R;
import me.iwf.photopicker.PhotoPicker;

public class SelectPictureActivity extends AppCompatActivity {

     Button btnClick ;
    PhotoView photoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_picture);
        btnClick = (Button) findViewById(R.id.btn_picture);
        photoView = (PhotoView) findViewById(R.id.photo_view);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoPicker.builder()
                        .setPhotoCount(9)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(SelectPictureActivity.this, PhotoPicker.REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                for (String str: photos) {
                    Log.e("xyc", "onActivityResult: str "+str);


                }
                Uri url = Uri.parse(photos.get(0));
                photoView.setImageURI(url);
            }
        }
    }

}
