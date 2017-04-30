package org.mburm.yahtzee;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by mbmis006 on 4/27/2017.
 */

public class DiceView extends View {
    Bitmap[] d = new Bitmap[6];
    int[] dice = new int[5];
    boolean[] hold = new boolean[5];

    public DiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public DiceView(Context context) {
        super(context);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.makeMeasureSpec(360, MeasureSpec.EXACTLY);
        int h = MeasureSpec.makeMeasureSpec(100,MeasureSpec.EXACTLY);
        setMeasuredDimension(w,h);
        super.onMeasure(w, h);
    }

    private void init(Context con)
    {
        d[0] = BitmapFactory.decodeResource(getResources(),R.drawable.diz61);
        d[1] = BitmapFactory.decodeResource(getResources(),R.drawable.diz62);
        d[2] = BitmapFactory.decodeResource(getResources(),R.drawable.diz63);
        d[3] = BitmapFactory.decodeResource(getResources(),R.drawable.diz64);
        d[4] = BitmapFactory.decodeResource(getResources(),R.drawable.diz65);
        d[5] = BitmapFactory.decodeResource(getResources(),R.drawable.diz66);
        dice[0] = 0;
        dice[1] = 1;
        dice[2] = 2;
        dice[3] = 3;
        dice[4] = 4;
        hold[0] = true;
        hold[1] = false;
        hold[2] = true;
        hold[3] = false;
        hold[4] = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int act = event.getAction();
        if (act == MotionEvent.ACTION_DOWN)
        {
            float x = event.getX();
            x = x - 5;
            int x1 = (int)(x / 70);
            if (x1 < 5)
                hold[x1] = !hold[x1];
        }
        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.RED);
        Rect R = new Rect();

        int x = 5;
        for (int i=0;i<5;i++)
        {
            canvas.drawBitmap(d[dice[i]], x, 5,p);
            if (hold[i]) {
                R.set(x, 70, x + 60, 75);
                canvas.drawRect(R, p);
            }
            x = x + 70;
        }
        super.onDraw(canvas);
    }

    public void setDice(int di, int val)
    {
        dice[di] = val;
    }

    public boolean getHold(int di)
    {
        return hold[di];
    }

    public int getDice(int di)
    {
        return dice[di];
    }

    public void releaseHold()
    {
        for (int i=0;i<5;i++)
            hold[i] = false;
        invalidate();
    }
}
