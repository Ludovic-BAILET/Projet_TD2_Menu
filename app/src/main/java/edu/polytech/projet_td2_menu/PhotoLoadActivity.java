package edu.polytech.projet_td2_menu;

import static java.security.AccessController.getContext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import edu.polytech.projet_td2_menu.fragments.NavigationBar;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterface;
import edu.polytech.projet_td2_menu.fragments.NavigationBarInterfaceImplementation;

public class PhotoLoadActivity extends AppCompatActivity implements NavigationBarInterface {

    private static final String TAG = "photoLoad";
    private Button valider;
    private ImageView camera;
    private ImageView file;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_PICK_CODE = 1001;

    private static final int CAMERA_CODE = 100;
    private static final int PERMISSION_CAMERA_CODE = 101;

    private NavigationBarInterfaceImplementation implementation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_load);

        implementation = new NavigationBarInterfaceImplementation(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bar, new NavigationBar()).commit();

        valider = (Button) findViewById(R.id.button_valider);
        camera = findViewById(R.id.camera);
        file = findViewById(R.id.file);


        camera.setOnClickListener(view -> {


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
                    //permission not granted
                    String[] permissions = {Manifest.permission.CAMERA};
                    //show popup for runtime permission
                    requestPermissions(permissions, PERMISSION_CAMERA_CODE);

                }else{
                    //permission already granted
                    takeImageFromCamera();
                }
            }
            else{
                //system os is less than marsmallow
                takeImageFromCamera();
            }
        });




        file.setOnClickListener(view -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    //permission not granted
                    String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    //show popup for runtime permission
                    requestPermissions(permissions, PERMISSION_PICK_CODE);

                }else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system os is less than marsmallow
                pickImageFromGallery();
            }
        });

    }

    private void takeImageFromCamera(){
        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(open_camera,CAMERA_CODE);
    }


    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }


    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_PICK_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    pickImageFromGallery();
                }else{
                    //permission was denied
                    Toast.makeText(this,"Permission denied ... !",Toast.LENGTH_SHORT).show();
                }
            }
            case PERMISSION_CAMERA_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted
                    takeImageFromCamera();
                }else{
                    //permission was denied
                    Toast.makeText(this,"Permission denied ... !",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            file.setImageURI(data.getData());
        }

        if(resultCode == RESULT_OK && requestCode == CAMERA_CODE){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            camera.setImageBitmap(photo);
            SaveImage(photo);
        }

    }
    private void SaveImage(Bitmap finalBitmap) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions( this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    IStorageActivity.REQUEST_MEDIA_READ);
        } else {
            Toast.makeText(this,"wow we accessed this place!",Toast.LENGTH_SHORT).show();
            String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";

        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();

        try {
            FileOutputStream out = new FileOutputStream(file);

            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this,"wow we accessed before close file!",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }}
    }

    @Override
    public void onButtonPlanningClicked(View v) {
        implementation.onButtonPlanningClicked(v);
    }

    @Override
    public void onButtonProfilClicked(View v) {
        implementation.onButtonProfilClicked(v);
    }

    @Override
    public void onButtonMesCoursesClicked(View v) {
        implementation.onButtonMesCoursesClicked(v);
    }

    @Override
    public void onButtonRecettesClicked(View v) {
        implementation.onButtonRecettesClicked(v);
    }
}