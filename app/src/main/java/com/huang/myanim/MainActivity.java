package com.huang.myanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {


    private ArrowsView anim;
    private float lastX;
    private float lastY;
    private float dx;
    private float dy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anim = (ArrowsView) findViewById(R.id.arrows_view);
        anim.setOnTouchListener(this);
    }

    public void start(View view) {
        anim.startAnim();
    }


    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                dx = event.getRawX() - lastX;
                dy = event.getRawY() - lastY;
                int left = (int) (anim.getLeft() + dx);
                int right = (int) (anim.getRight() + dx);
                int bottom = (int) (anim.getBottom() + dy);
                int top = (int) (anim.getTop() + dy);
                anim.layout(left, top, right, bottom);
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return true;
    }


    public void setLayout(int x, int y) {

    }
}
