package com.example.ago.ontouchtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private int _xStart;
    private int _yStart;
    private RelativeLayout _rootLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        _rootLayout = (RelativeLayout)findViewById(R.id._RootLayout);

        mImageView = new ImageView(this);
        mImageView.setImageResource(R.drawable.warr);
        mImageView.setLayoutParams(layoutParams);

        mImageView.setOnTouchListener(new MyTouchListener(1));

        _rootLayout.addView(mImageView);

    }

    public class MyTouchListener implements View.OnTouchListener{

        int method;

        public MyTouchListener(int incMethod){
            this.method = incMethod;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {

            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();

            final int rawX = (int) motionEvent.getRawX();
            final int rawY = (int) motionEvent.getRawY();

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    _xStart = rawX - layoutParams.leftMargin;
                    _yStart = rawY - layoutParams.topMargin;
                    break;

                case MotionEvent.ACTION_UP:
                    break;

                case MotionEvent.ACTION_MOVE:
                    layoutParams.leftMargin = rawX - _xStart;
                    layoutParams.topMargin = rawY - _yStart;
                    view.setLayoutParams(layoutParams);
                    break;

            }

            return true;
        }

    }
}
