package com.example.ocr;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.image);
        textView =(TextView) findViewById(R.id.text);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.card2);

                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if(!textRecognizer.isOperational()){
                    Toast.makeText(MainActivity.this, "Could not get the Text", Toast.LENGTH_SHORT).show();
                }

                else{
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();

                    SparseArray<TextBlock> items = textRecognizer.detect(frame);

                    StringBuilder sb = new StringBuilder();

                    for(int i=0;i<items.size();++i){
                        TextBlock myitems = items.valueAt(i);
                        sb.append(myitems.getValue());
                        sb.append("\n");
                    }
                    textView.setText(sb.toString());
                }
            }
        });
    }
}