package com.example.lesson1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lesson1.R;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    EditText userName, password;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        userName = findViewById(R.id.et_1);
        password = findViewById(R.id.et_2);
        imageView = findViewById(R.id.image);

        String text = getIntent().getStringExtra("key");
        userName.setText(text);
        String text1 = getIntent().getStringExtra("key1");
        password.setText(text1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                alert.setPositiveButton("Камера", ((dialogInterface, i) -> {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
                )).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap imageBitmap = (Bitmap) bundle.get("data");
            Glide.with(this).load(imageBitmap).circleCrop().into(imageView);

            // clon

        }
    }
}