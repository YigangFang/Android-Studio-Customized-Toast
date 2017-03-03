package com.aidenfang.customized_toast_demo1;
//tested by YF and worked great!
//03/02/2017

//Please refer to:
//http://blog.csdn.net/wirelessqa/article/details/8585194

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button toastWithAPicture = null;
    private Button toastWithATitle = null;
    private Button displayToastFromOtherThread = null;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Toast with a picture
        toastWithAPicture = (Button) findViewById(R.id.btn1);
        toastWithAPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Toast with a picture", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                LinearLayout toastView = (LinearLayout) toast.getView();
                ImageView myImageView = new ImageView(getApplicationContext());
                myImageView.setImageResource(R.drawable.ic_action_name);
                //setMargin(float horizontalMargin, float verticalMargin)
                toast.setMargin(20,40);
                toastView.addView(myImageView, 0);
                toast.show();
            }
        });


        // 2. Toast with a customized title
        toastWithATitle = (Button) findViewById(R.id.btn2);
        toastWithATitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.define, (ViewGroup) findViewById(R.id.define));

                TextView title = (TextView) layout.findViewById(R.id.define_title);
                title.setText("This is customized title!");
                //
                ImageView img = (ImageView) layout.findViewById(R.id.define_img);
                img.setImageResource(R.drawable.ic_action_name);
                //
                TextView text = (TextView) layout.findViewById(R.id.define_text);
                text.setText("this shows a Toast with a customized title");

                Toast toast = new Toast(getApplicationContext());
                //setGravity(int gravity, int xOffset, int yOffset)
                toast.setGravity(Gravity.LEFT | Gravity.CENTER, 50, 0);
                toast.setDuration(Toast.LENGTH_LONG);
                // replace the default view with the one we just created above
                toast.setView(layout);
                toast.show();
            }
        });


        // 3.
        displayToastFromOtherThread = (Button) findViewById(R.id.btn3);
        displayToastFromOtherThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        otherThreadToast();
                    }
                }).start();
            }
        });
    }

    private void otherThreadToast(){
        handler.post(new Runnable(){
            @Override
            public void run(){
                Toast.makeText(getApplicationContext(),"This Toast is indeed from a different thread!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
