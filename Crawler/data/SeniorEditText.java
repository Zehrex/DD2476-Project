1
https://raw.githubusercontent.com/niufuwei/block_chian/master/Stock/app/src/main/java/com/hjq/demo/widget/SeniorEditText.java
package com.hjq.demo.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Property;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.hjq.demo.R;

/**
 * 自定义可带清除、密码显示不显示的输入框
 */
public class SeniorEditText extends AppCompatEditText {


    /**
     * 边框样式
     */
    private static final String STYLE_RECT = "rectangle";//矩形
    private static final String STYLE_ROUND_RECT = "roundRect";//圆角矩形
    private static final String STYLE_HALF_RECT = "halfRect";//半矩形
    private static final String STYLE_ANIMATOR = "animator";//动画特效

    private static final int DEFAULT_ROUND_RADIUS = 20;//圆角矩形圆角度
    private static final int ANIMATOR_TIME = 200;//动画时间
    private static final int DEFAULT_FOCUSED_STROKE_WIDTH = 8;//获取到焦点的边框宽
    private static final int DEFAULT_UNFOCUSED_STROKE_WIDTH = 4;//未获取焦点的边框宽
    private static final int DEFAULT_STYLE_COLOR = Color.RED;//边框默认颜色

    //按钮间隔
    private int visible_res_padding = 0;
    //按钮宽度
    private int visible_res_width = 0;
    //右内边距
    private int mTextPaddingRight;

    //需要显示的drawable 资源id
    private int clear_res_id = 0;
    private int visible_res_id = 0;
    private int invisible_res_id = 0;

    //需要显示的bitmap---通过drawable 资源id来
    private Bitmap clear_bitmap;
    private Bitmap visible_bitmap;
    private Bitmap invisible_bitmap;

    private String edit_border_style = "";
    private int edit_border_color = -1;

    //出现和消失动画
    private ValueAnimator show_animator;
    private ValueAnimator dismiss_animator;
    //状态值
    private boolean clear_image_isShow = false;
    private boolean show_password_image_isShow = false;
    private boolean dismiss_password_image_isShow = false;

    private boolean isAnimatorRunning = false;
    private int mAnimatorProgress = 0;
    private ObjectAnimator mAnimator;

    //自定义属性动画
    private static final Property<SeniorEditText, Integer> BORDER_PROGRESS
            = new Property<SeniorEditText, Integer>(Integer.class, "borderProgress") {
        @Override
        public Integer get(SeniorEditText seniorEditText) {
            return seniorEditText.getBorderProgress();
        }

        @Override
        public void set(SeniorEditText seniorEditText, Integer value) {
            seniorEditText.setBorderProgress(value);
        }
    };

    private Paint mPaint;


    public SeniorEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SeniorEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //抗锯齿和位图滤波
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        //读取xml文件中的配置
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SeniorEditText);
            //获取清除图标资源
            clear_res_id = array.getResourceId(R.styleable.SeniorEditText_clearDrawable, R.mipmap.clear);
            //获取可见图标资源
            visible_res_id = array.getResourceId(R.styleable.SeniorEditText_visibleDrawable, R.mipmap.eye_open);
            //获取不可见图标资源
            invisible_res_id = array.getResourceId(R.styleable.SeniorEditText_invisibleDrawable, R.mipmap.eye_close);
            //可见图标按钮默认宽
            visible_res_width = array.getDimensionPixelSize(R.styleable.SeniorEditText_visibleDrawableWidth, dp2px(context,24));
            //可见图标按钮默认内边距
            visible_res_padding = array.getDimensionPixelSize(R.styleable.SeniorEditText_visibleDrawableSpacing, dp2px(context,5));
            //输入框边框样式
            edit_border_style = array.getString(R.styleable.SeniorEditText_editFrameStyle);
            //输入框边框颜色
            edit_border_color = array.getColor(R.styleable.SeniorEditText_editFrameColor, DEFAULT_STYLE_COLOR);
            array.recycle();
        }
        //初始化按钮显示的Bitmap
        clear_bitmap = createBitmap(context, clear_res_id, R.mipmap.clear);
        visible_bitmap = createBitmap(context, visible_res_id, R.mipmap.eye_open);
        invisible_bitmap = createBitmap(context, invisible_res_id, R.mipmap.eye_close);
        //如果自定义，则使用自定义的值，否则使用默认值
        if (visible_res_padding == 0) {
            visible_res_padding = dp2px(context,5);
        }
        if (visible_res_width == 0) {
            visible_res_width = dp2px(context,24);
        }
        //给文字设置一个padding，避免文字和按钮重叠了
        mTextPaddingRight = visible_res_padding * 4 + visible_res_width * 2;
        //按钮出现和消失的动画
        show_animator = ValueAnimator.ofFloat(1f, 0f).setDuration(ANIMATOR_TIME);
        dismiss_animator = ValueAnimator.ofFloat(0f, 1f).setDuration(ANIMATOR_TIME);
        //是否是密码样式
        show_password_image_isShow = getInputType() == (InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //设置右内边距, 防止清除按钮和文字重叠
        setPadding(getPaddingLeft(), getPaddingTop(), mTextPaddingRight, getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);

        //使用自定义颜色。如未定义，则使用默认颜色
        if (edit_border_color != -1) {
            mPaint.setColor(edit_border_color);
        } else {
            mPaint.setColor(DEFAULT_STYLE_COLOR);
        }

        //控件获取焦点时，加粗边框
        if (isFocused()) {
            mPaint.setStrokeWidth(DEFAULT_FOCUSED_STROKE_WIDTH);
        } else {
            mPaint.setStrokeWidth(DEFAULT_UNFOCUSED_STROKE_WIDTH);
        }


        //绘制边框
        drawBorder(canvas);

        //绘制清空和明文显示按钮
        drawButtons(canvas);
    }

    private void drawBorder(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        switch (edit_border_style) {
            //矩形样式
            case STYLE_RECT:
                setBackground(null);
                canvas.drawRect(0, 0, width, height, mPaint);
                break;

            //圆角矩形样式
            case STYLE_ROUND_RECT:
                setBackground(null);
                float roundRectLineWidth = 0;
                if (isFocused()) {
                    roundRectLineWidth = DEFAULT_FOCUSED_STROKE_WIDTH / 2;
                } else {
                    roundRectLineWidth = DEFAULT_UNFOCUSED_STROKE_WIDTH / 2;
                }
                mPaint.setStrokeWidth(roundRectLineWidth);
                if (Build.VERSION.SDK_INT >= 21) {
                    canvas.drawRoundRect(
                            roundRectLineWidth / 2, roundRectLineWidth / 2, width - roundRectLineWidth / 2, height - roundRectLineWidth / 2,
                            DEFAULT_ROUND_RADIUS, DEFAULT_ROUND_RADIUS,
                            mPaint);
                } else {
                    canvas.drawRoundRect(
                            new RectF(roundRectLineWidth / 2, roundRectLineWidth / 2, width - roundRectLineWidth / 2, height - roundRectLineWidth / 2),
                            DEFAULT_ROUND_RADIUS, DEFAULT_ROUND_RADIUS,
                            mPaint);
                }
                break;

            //半矩形样式
            case STYLE_HALF_RECT:
                setBackground(null);
                canvas.drawLine(0, height, width, height, mPaint);
                canvas.drawLine(0, height / 2, 0, height, mPaint);
                canvas.drawLine(width, height / 2, width, height, mPaint);
                break;

            //动画特效样式
            case STYLE_ANIMATOR:
                setBackground(null);
                if (isAnimatorRunning) {
                    canvas.drawLine(width / 2 - mAnimatorProgress, height, width / 2 + mAnimatorProgress, height, mPaint);
                    if (mAnimatorProgress == width / 2) {
                        isAnimatorRunning = false;
                    }
                } else {
                    canvas.drawLine(0, height, width, height, mPaint);
                }
                break;
            default:
                break;
        }
    }

    private void drawButtons(Canvas canvas) {
        if (clear_image_isShow) {
            //播放按钮出现的动画
            if (dismiss_animator.isRunning()) {
                float scale = (float) dismiss_animator.getAnimatedValue();
                drawClearButton(scale, canvas);
                if (show_password_image_isShow) {
                    drawVisibleButton(scale, canvas, dismiss_password_image_isShow);
                }
                invalidate();
                //绘制静态的按钮
            } else {
                drawClearButton(1, canvas);
                if (show_password_image_isShow) {
                    drawVisibleButton(1, canvas, dismiss_password_image_isShow);
                }
            }
        } else {
            //播放按钮消失的动画
            if (show_animator.isRunning()) {
                float scale = (float) show_animator.getAnimatedValue();
                drawClearButton(scale, canvas);
                if (show_password_image_isShow) {
                    drawVisibleButton(scale, canvas, dismiss_password_image_isShow);
                }
                invalidate();
            }
        }
    }

    private void drawClearButton(float scale, Canvas canvas) {

        int right = (int) (getWidth() + getScrollX() - visible_res_padding - visible_res_width * (1f - scale) / 2f);
        int left = (int) (getWidth() + getScrollX() - visible_res_padding - visible_res_width * (scale + (1f - scale) / 2f));
        int top = (int) ((getHeight() - visible_res_width * scale) / 2);
        int bottom = (int) (top + visible_res_width * scale);
        Rect rect = new Rect(left, top, right, bottom);
        canvas.drawBitmap(clear_bitmap, null, rect, mPaint);
    }

    private void drawVisibleButton(float scale, Canvas canvas, boolean isVisible) {

        int right = (int) (getWidth() + getScrollX() - visible_res_padding * 3 - visible_res_width - visible_res_width * (1f - scale) / 2f);
        int left = (int) (getWidth() + getScrollX() - visible_res_padding * 3 - visible_res_width - visible_res_width * (scale + (1f - scale) / 2f));
        int top = (int) ((getHeight() - visible_res_width * scale) / 2);
        int bottom = (int) (top + visible_res_width * scale);
        Rect rect = new Rect(left, top, right, bottom);
        if (isVisible) {
            canvas.drawBitmap(visible_bitmap, null, rect, mPaint);
        } else {
            canvas.drawBitmap(invisible_bitmap, null, rect, mPaint);
        }

    }

    // 清除按钮出现时的动画效果
    private void startVisibleAnimator() {
        endAllAnimator();
        dismiss_animator.start();
        invalidate();
    }

    // 清除按钮消失时的动画效果
    private void startGoneAnimator() {
        endAllAnimator();
        show_animator.start();
        invalidate();
    }

    // 结束所有动画
    private void endAllAnimator() {
        show_animator.end();
        dismiss_animator.end();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        //播放按钮出现和消失动画
        if (focused && getText().length() > 0) {
            if (!clear_image_isShow) {
                clear_image_isShow = true;
                startVisibleAnimator();
            }
        } else {
            if (clear_image_isShow) {
                clear_image_isShow = false;
                startGoneAnimator();
            }
        }

        //实现动画特效样式
        if (focused && edit_border_style.equals(STYLE_ANIMATOR)) {
            isAnimatorRunning = true;
            mAnimator = ObjectAnimator.ofInt(this, BORDER_PROGRESS, 0, getWidth() / 2);
            mAnimator.setDuration(ANIMATOR_TIME);
            mAnimator.start();
        }
    }

    protected void setBorderProgress(int borderProgress) {
        mAnimatorProgress = borderProgress;
        postInvalidate();
    }

    protected int getBorderProgress() {
        return mAnimatorProgress;
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if (text.length() > 0 && isFocused()) {
            if (!clear_image_isShow) {
                clear_image_isShow = true;
                startVisibleAnimator();
            }
        } else {
            if (clear_image_isShow) {
                clear_image_isShow = false;
                startGoneAnimator();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {

            boolean clearTouched =
                    (getWidth() - visible_res_padding - visible_res_width < event.getX())
                            && (event.getX() < getWidth() - visible_res_padding)
                            && isFocused();
            boolean visibleTouched =
                    (getWidth() - visible_res_padding * 3 - visible_res_width * 2 < event.getX())
                            && (event.getX() < getWidth() - visible_res_padding * 3 - visible_res_width)
                            && show_password_image_isShow && isFocused();

            if (clearTouched) {
                setError(null);
                setText("");
                return true;
            } else if (visibleTouched) {
                if (dismiss_password_image_isShow) {
                    dismiss_password_image_isShow = false;
                    setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    setSelection(getText().length());
                    invalidate();
                } else {
                    dismiss_password_image_isShow = true;
                    setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    setSelection(getText().length());
                    invalidate();
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 开始晃动的入口
     */
    public void startShakeAnimation() {
        if (getAnimation() == null) {
            setAnimation(shakeAnimation(4));
        }
        startAnimation(getAnimation());
    }

    /**
     * 晃动动画
     *
     * @param counts 0.5秒钟晃动多少下
     * @return
     */
    private Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(500);
        return translateAnimation;
    }


    private Bitmap createBitmap(Context context, int resId, int defResId) {
        if (resId != 0) {
            return BitmapFactory.decodeResource(context.getResources(), resId);
        } else {
            return BitmapFactory.decodeResource(context.getResources(), defResId);
        }
    }

    public int dp2px(Context context, float dpValue){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }
}