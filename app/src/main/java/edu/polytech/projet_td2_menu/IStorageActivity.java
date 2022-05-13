package edu.polytech.projet_td2_menu;

import android.graphics.Bitmap;

public interface IStorageActivity {
    int REQUEST_MEDIA_READ = 1000;
    int REQUEST_MEDIA_WRITE = 1001;

    void onPictureLoad(Bitmap bitmap);

    Bitmap getPictureToSave();
}
