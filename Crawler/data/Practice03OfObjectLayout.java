2
https://raw.githubusercontent.com/Geekholt/Practice-CustomView/master/app/src/main/java/com/geekholt/practiceui/view/lesson6/practice03/Practice03OfObjectLayout.java
package com.geekholt.practiceui.view.lesson6.practice03;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.geekholt.practiceui.R;

public class Practice03OfObjectLayout extends RelativeLayout {
    Practice03OfObjectView view;
    Button animateBt;

    public Practice03OfObjectLayout(Context context) {
        super(context);
    }

    public Practice03OfObjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03OfObjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = (Practice03OfObjectView) findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofObject(view, "position",
                        new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(1000);
                animator.start();
            }
        });
    }

    private class PointFEvaluator implements TypeEvaluator<PointF> {
        PointF newPoint = new PointF();
        int sum = 0;

        // 重写 evaluate() 方法，让 PointF 可以作为属性来做动画
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            //fraction表示动画进度
            sum++;
            System.out.println("fraction = " + fraction + " startValue = " + startValue + " endValue = " + endValue + " sum = " + sum);
            float x = startValue.x + (fraction * (endValue.x - startValue.x));
            float y = startValue.y + (fraction * (endValue.y - startValue.y));
            System.out.println("x = " + x + " y = " + y);

            newPoint.set(x, y);

            return newPoint;
        }
    }
}