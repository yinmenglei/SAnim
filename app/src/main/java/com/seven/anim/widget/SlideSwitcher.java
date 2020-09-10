package com.seven.anim.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.core.view.MotionEventCompat;


public class SlideSwitcher extends View {

    public static final int SHAPE_RECT = 1;
    public static final int SHAPE_CIRCLE = 2;
    private static final int RIM_SIZE = 4;
    private static final int DEFAULT_OPEN_COLOR = 0xFFFF6B00;
    private static final int DEFAULT_CLOSE_COLOR = 0xFF282828;

    // 4 attributes
    private int openColor;
    private int closeColor;
    private boolean isOpen;
    private int shape;

    // varials of drawing
    private Paint paint;
    private Rect backRect;
    private Rect frontRect;
    private RectF frontCircleRect;
    private RectF backCircleRect;
    private int alpha;
    private int maxLeft;
    private int minLeft;
    private int frontRectLeft;
    private int frontRectLeftBegin = RIM_SIZE;
    private int eventStartX;
    private int eventLastX;
    private int diffX = 0;
    private boolean slideable = true;
    private SlideListener mSlideListener;
    public float mDeviceScal = 1.0f;

    public interface SlideListener {
        void onStatusChanged(SlideSwitcher view, boolean isOpen);
    }

    public SlideSwitcher(Context context) {
        this(context, null);
        DisplayMetrics ds = context.getResources().getDisplayMetrics();
        mDeviceScal = ds.density;
    }

    public SlideSwitcher(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        DisplayMetrics ds = context.getResources().getDisplayMetrics();
        mDeviceScal = ds.density;
    }

    public SlideSwitcher(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);

        openColor = DEFAULT_OPEN_COLOR;
        closeColor = DEFAULT_CLOSE_COLOR;
        isOpen = false;
        shape = SHAPE_CIRCLE;

        DisplayMetrics ds = context.getResources().getDisplayMetrics();
        mDeviceScal = ds.density;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = measureDimension((int) (100 * mDeviceScal), widthMeasureSpec);
        int height = measureDimension((int) (40 * mDeviceScal), heightMeasureSpec);
        if (shape == SHAPE_CIRCLE) {
            if (width < height) {
                width = height * 2;
            }
        }
        setMeasuredDimension(width, height);
        initDrawingVal();
    }

    private void initDrawingVal() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        backCircleRect = new RectF();
        frontCircleRect = new RectF();
        frontRect = new Rect();
        backRect = new Rect(0, 0, width, height);
        minLeft = RIM_SIZE;
        if (shape == SHAPE_RECT) {
            maxLeft = width / 2;
        } else {
            maxLeft = width - (height - 2 * RIM_SIZE) - RIM_SIZE;
        }
        if (isOpen) {
            frontRectLeft = maxLeft;
            alpha = 255;
        } else {
            frontRectLeft = RIM_SIZE;
            alpha = 0;
        }
        frontRectLeftBegin = frontRectLeft;
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize; // UNSPECIFIED
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (shape == SHAPE_RECT) {
            paint.setColor(closeColor);
            canvas.drawRect(backRect, paint);
            paint.setColor(openColor);
            paint.setAlpha(alpha);
            canvas.drawRect(backRect, paint);
            frontRect.set(frontRectLeft, RIM_SIZE, frontRectLeft + getMeasuredWidth() / 2 - RIM_SIZE, getMeasuredHeight() - RIM_SIZE);
            paint.setColor(Color.WHITE);
            canvas.drawRect(frontRect, paint);
        } else {
            // draw circle
            int radius = backRect.height() / 2;
            paint.setColor(closeColor);
            backCircleRect.set(backRect);
            canvas.drawRoundRect(backCircleRect, radius, radius, paint);
            paint.setColor(openColor);
            paint.setAlpha(alpha);
            canvas.drawRoundRect(backCircleRect, radius, radius, paint);
            frontRect.set(frontRectLeft, RIM_SIZE, frontRectLeft + backRect.height() - 2 * RIM_SIZE, backRect.height() - RIM_SIZE);
            frontCircleRect.set(frontRect);
            paint.setColor(Color.WHITE);
            canvas.drawRoundRect(frontCircleRect, radius, radius, paint);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (slideable == false) {
            return super.onTouchEvent(event);
        }
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                eventStartX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                eventLastX = (int) event.getRawX();
                diffX = eventLastX - eventStartX;
                int tempX = diffX + frontRectLeftBegin;
                tempX = (tempX > maxLeft ? maxLeft : tempX);
                tempX = (tempX < minLeft ? minLeft : tempX);
                if (tempX >= minLeft && tempX <= maxLeft) {
                    frontRectLeft = tempX;
                    alpha = (int) (255 * (float) tempX / (float) maxLeft);
                    invalidateView();
                }
                break;
            case MotionEvent.ACTION_UP:
                int wholeX = (int) (event.getRawX() - eventStartX);
                frontRectLeftBegin = frontRectLeft;
                boolean toRight = (frontRectLeftBegin > maxLeft / 2);
                if (Math.abs(wholeX) < 3) {
                    toRight = !toRight;
                }
                moveToDest(toRight);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * draw again
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public void setSlideListener(SlideListener listener) {
        this.mSlideListener = listener;
    }

    public void moveToDest(final boolean toRight) {
        ValueAnimator toDestAnim = ValueAnimator.ofInt(frontRectLeft, toRight ? maxLeft : minLeft);
        toDestAnim.setDuration(300);
        toDestAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        toDestAnim.start();
        toDestAnim.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                frontRectLeft = (Integer) animation.getAnimatedValue();
                alpha = (int) (255 * (float) frontRectLeft / (float) maxLeft);
                invalidateView();
            }
        });
        toDestAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (toRight) {
                    isOpen = true;
                    frontRectLeftBegin = maxLeft;
                } else {
                    isOpen = false;
                    frontRectLeftBegin = minLeft;
                }

                if (mSlideListener != null) {
                    mSlideListener.onStatusChanged(SlideSwitcher.this, isOpen);
                }
            }
        });
    }

    public void setOpen(boolean isOpen) {
        setOpen(isOpen, false);
    }

    public void setOpen(boolean isOpen, boolean invokListener) {
        this.isOpen = isOpen;
        initDrawingVal();
        invalidateView();
        if (invokListener && mSlideListener != null) {
            mSlideListener.onStatusChanged(this, isOpen);
        }
    }

    public void setShapeType(int shapeType) {
        this.shape = shapeType;
    }

    public void setSlideable(boolean slideable) {
        this.slideable = slideable;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.isOpen = bundle.getBoolean("isOpen");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putBoolean("isOpen", this.isOpen);
        return bundle;
    }
}
