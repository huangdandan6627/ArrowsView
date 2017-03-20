package com.huang.myanim;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Huang on 2017/3/18.
 */

public class ArrowsView extends LinearLayout implements View.OnClickListener {

    /**
     * 第一次移动的距离
     */
    public static final int firstdistance = 30;

    /**
     * 第二次移动的距离
     */
    public static final int secondistance = 180;

    private ImageView iv_up;
    private ImageView iv_down;
    private ImageView iv_left;
    private ImageView iv_right;
    private boolean isUp = false;
    private boolean isDown = false;
    private boolean isLeft = false;
    private boolean isRight = false;
    private int height;
    private int width;
    private Paint paint;

    private int iv_left_firstinstance;
    private int iv_left_firstAnim;

    private int iv_right_firstinstance;
    private int iv_right_firstAnim;

    private int iv_up_firstinstance;
    private int iv_up_firstAnim;

    private int iv_down_firstinstance;
    private int iv_down_firstAnim;

    private int iv_left_right;
    private int iv_right_left;
    private int iv_up_bottom;
    private int iv_down_top;
    private int iv_up_secondinstance;
    private int iv_down_secondinstance;
    private int iv_right_secondinstance;
    private int iv_left_secondinstance;
    private boolean isStarteEnd = false;

    public ArrowsView(Context context) {
        super(context, null);
        init(context);
    }


    public ArrowsView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public ArrowsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_arrows_view, null);
        addView(inflate);
        iv_up = (ImageView) findViewById(R.id.iv_up);
        iv_down = (ImageView) findViewById(R.id.iv_down);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);

        iv_up.setOnClickListener(this);
        iv_down.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);

        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.parseColor("#E3A300"));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();

        iv_left_right = iv_left.getRight();
        iv_left_firstinstance = iv_left_right - firstdistance + 2;
        iv_left_firstAnim = iv_left_right;

        iv_right_left = iv_right.getLeft();
        iv_right_firstinstance = iv_right_left + firstdistance - 2;
        iv_right_firstAnim = iv_right_left;

        iv_up_bottom = iv_up.getBottom();
        iv_up_firstinstance = iv_up_bottom - firstdistance + 2;
        iv_up_firstAnim = iv_up_bottom;


        iv_down_top = iv_down.getTop();
        iv_down_firstinstance = iv_down_top + firstdistance - 2;
        iv_down_firstAnim = iv_down_top;

        iv_up_secondinstance = iv_up_firstinstance - secondistance + 30;
        iv_down_secondinstance = iv_down_firstinstance + secondistance - 30;
        iv_right_secondinstance = iv_right_firstinstance + secondistance - 30;
        iv_left_secondinstance = iv_left_firstinstance - secondistance + 30;
    }


    /**
     * 隐藏上
     */
    public void hindUp() {
        iv_up.setVisibility(View.GONE);
    }

    /**
     * 隐藏下
     */
    public void hindDown() {
        iv_down.setVisibility(View.GONE);
    }

    /**
     * 隐藏Left
     */
    public void hindLeft() {
        iv_left.setVisibility(View.GONE);
    }

    /**
     * 隐藏right
     */
    public void hindRight() {
        iv_right.setVisibility(View.GONE);
    }


    /**
     * 隐藏上下
     */
    public void hindUpAndDown() {
        hindDown();
        hindUp();
    }

    /**
     * 隐藏上右边(right)
     */
    public void hindUpAndRight() {
        hindRight();
        hindUp();
    }

    /**
     * 隐藏下右边(right)
     */
    public void hindDownAndRight() {
        hindRight();
        hindDown();
    }


    /**
     * 隐藏下 左边(left)
     */
    public void hindDownAndLeft() {
        hindDown();
        hindLeft();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("huang---", "jdddddddddddddddddddddddddddddd");
        canvas.drawLine(width / 2, height / 2, iv_left_firstAnim, height / 2, paint);
        canvas.drawLine(width / 2, height / 2, iv_right_firstAnim, height / 2, paint);
        canvas.drawLine(width / 2, height / 2, width / 2, iv_down_firstAnim, paint);
        canvas.drawLine(width / 2, height / 2, width / 2, iv_up_firstAnim, paint);
    }


    /**
     * 显示出来开始动画
     */
    public void startAnim() {
        iv_up.setVisibility(View.VISIBLE);
        iv_down.setVisibility(View.VISIBLE);
        iv_left.setVisibility(View.VISIBLE);
        iv_right.setVisibility(View.VISIBLE);

        AnimatorSet setAnim = new AnimatorSet();
        ObjectAnimator upAnim = ObjectAnimator.ofFloat(iv_up, "translationY", 0, -firstdistance);
        ObjectAnimator upAlpha = ObjectAnimator.ofFloat(iv_up, "alpha", 0.5f, 1f);


        ObjectAnimator downAnim = ObjectAnimator.ofFloat(iv_down, "translationY", 0, firstdistance);
        ObjectAnimator downAlpha = ObjectAnimator.ofFloat(iv_down, "alpha", 0.5f, 1f);


        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(iv_left, "translationX", 0, -firstdistance);
        ObjectAnimator leftAlpha = ObjectAnimator.ofFloat(iv_left, "alpha", 0.5f, 1f);

        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(iv_right, "translationX", 0, firstdistance);
        ObjectAnimator righAlpha = ObjectAnimator.ofFloat(iv_right, "alpha", 0.5f, 1f);


        ValueAnimator leftAnimator = ValueAnimator.ofInt(iv_left_firstAnim, iv_left_firstinstance);
        leftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_left_firstAnim = value;
                invalidate();
            }
        });
        ValueAnimator rightAnimator = ValueAnimator.ofInt(iv_right_firstAnim, iv_right_firstinstance);
        rightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_right_firstAnim = value;
                invalidate();
            }
        });

        ValueAnimator upAnimator = ValueAnimator.ofInt(iv_up_firstAnim, iv_up_firstinstance);
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_up_firstAnim = value;
                invalidate();
            }
        });

        ValueAnimator downAnimator = ValueAnimator.ofInt(iv_down_firstAnim, iv_down_firstinstance);
        downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_down_firstAnim = value;
                invalidate();
            }
        });

        setAnim.setDuration(500);
        setAnim.play(upAlpha).with(downAlpha).with(leftAlpha).with(righAlpha)
                .after(200).with(upAnim).with(downAnim).with(leftAnim).with(rightAnim)
                .with(leftAnimator).with(rightAnimator).with(downAnimator).with(upAnimator);
        setAnim.start();

        setAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStarteEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public void up() {
        AnimatorSet upSet = new AnimatorSet();
        ValueAnimator upAnimator = ValueAnimator.ofInt(iv_up_firstinstance, iv_up_secondinstance);
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_up_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator upAnim = ObjectAnimator.ofFloat(iv_up, "translationY", -firstdistance, -secondistance);
        upSet.setDuration(200);
        upSet.play(upAnim).with(upAnimator);
        upSet.start();
        isUp = true;
    }

    public void down() {

        AnimatorSet downSet = new AnimatorSet();
        ValueAnimator downAnimator = ValueAnimator.ofInt(iv_down_firstinstance, iv_down_secondinstance);
        downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_down_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator downAnim = ObjectAnimator.ofFloat(iv_down, "translationY", firstdistance, secondistance);
        downSet.setDuration(200);
        downSet.play(downAnimator).with(downAnim);
        downSet.start();
        isDown = true;
    }

    public void left() {
        AnimatorSet leftSet = new AnimatorSet();
        ValueAnimator leftAnimator = ValueAnimator.ofInt(iv_left_firstinstance, iv_left_secondinstance);
        leftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_left_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(iv_left, "translationX", -firstdistance, -secondistance);
        leftSet.setDuration(200);
        leftSet.play(leftAnimator).with(leftAnim);
        leftSet.start();
        isLeft = true;
    }

    public void right() {
        AnimatorSet rightSet = new AnimatorSet();
        ValueAnimator rightAnimator = ValueAnimator.ofInt(iv_right_firstinstance, iv_right_secondinstance);
        rightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_right_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(iv_right, "translationX", firstdistance, secondistance);
        rightSet.setDuration(200);
        rightSet.play(rightAnimator).with(rightAnim);
        rightSet.start();
        isRight = true;
    }


    @Override
    public void onClick(View v) {

        if (!isStarteEnd) {
            return;
        }
        switch (v.getId()) {
            case R.id.iv_up:
                if (!isUp) {
                    recover();
                    up();
                } else {
                    upColose();
                }
                break;
            case R.id.iv_down:
                if (!isDown) {
                    recover();
                    down();
                } else {
                    downColose();
                }
                break;
            case R.id.iv_left:
                if (!isLeft) {
                    recover();
                    left();
                } else {
                    leftColose();
                }

                break;
            case R.id.iv_right:
                if (!isRight) {
                    recover();
                    right();
                } else {
                    rightColose();
                }

                break;
        }
    }

    public void recover() {
        if (isUp) {
            upColose();
        }

        if (isDown) {
            downColose();
        }

        if (isLeft) {
            leftColose();
        }

        if (isRight) {
            rightColose();
        }
    }

    private void rightColose() {
        isRight = false;
        AnimatorSet rightSet = new AnimatorSet();
        ValueAnimator rightAnimator = ValueAnimator.ofInt(iv_right_secondinstance, iv_right_firstinstance);
        rightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_right_firstAnim = value;
                invalidate();
            }
        });

        ObjectAnimator rightAnim = ObjectAnimator.ofFloat(iv_right, "translationX", secondistance, firstdistance);
        rightSet.setDuration(200);
        rightSet.play(rightAnimator).with(rightAnim);
        rightSet.start();
    }

    private void leftColose() {
        isLeft = false;
        AnimatorSet leftSet = new AnimatorSet();
        ValueAnimator leftAnimator = ValueAnimator.ofInt(iv_left_secondinstance, iv_left_firstinstance);
        leftAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_left_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator leftAnim = ObjectAnimator.ofFloat(iv_left, "translationX", -secondistance, -firstdistance);
        leftSet.setDuration(200);
        leftSet.play(leftAnim).with(leftAnimator);
        leftSet.start();
    }

    private void downColose() {
        isDown = false;
        AnimatorSet downSet = new AnimatorSet();
        ValueAnimator downAnimator = ValueAnimator.ofInt(iv_down_secondinstance, iv_down_firstinstance);
        downAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_down_firstAnim = value;
                invalidate();
            }
        });
        ObjectAnimator downAnim = ObjectAnimator.ofFloat(iv_down, "translationY", secondistance, firstdistance);
        downSet.setDuration(200);
        downSet.play(downAnimator).with(downAnim);
        downSet.start();
    }

    private void upColose() {
        isUp = false;
        AnimatorSet upSet = new AnimatorSet();
        ValueAnimator upAnimator = ValueAnimator.ofInt(iv_up_secondinstance, iv_up_firstinstance);
        upAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                iv_up_firstAnim = value;
                Log.e("huang==", "iv_up_secondinstance ||==" + iv_up_secondinstance);
                Log.e("huang==", "iv_up_firstinstance ||==" + iv_up_firstinstance);
                Log.e("huang==", "iv_up_firstAnim ||==" + iv_up_firstAnim);
                invalidate();
            }
        });
        ObjectAnimator upAnim = ObjectAnimator.ofFloat(iv_up, "translationY", -secondistance, -firstdistance);
        upSet.setDuration(200);
        upSet.play(upAnim).with(upAnimator);
        upSet.start();
    }
}
