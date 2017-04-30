package org.mburm.yahtzee;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class YahtzeeActivity extends AppCompatActivity implements ValueAnimator.AnimatorUpdateListener {

    DiceView Dices;
    Random Num;
    ValueAnimator An;
    int total;
    int Roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yahtzee);
        Dices = (DiceView) findViewById(R.id.dice);
        Dices.releaseHold();
        Num = new Random();
        An = ValueAnimator.ofInt(0,100);
        An.setDuration(3000);
        An.addUpdateListener(this);
        Yahtzee.Init();
        Roll = 0;
    }

    public void diceThrow(View v)
    {
        if (Roll == 2)
            v.setClickable(false);
        Roll++;
        TextView T = (TextView) findViewById(R.id.nrolls);
        T.setText(String.valueOf(Roll));
        total = 0;
        An.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int i = total++ % 5;
        int j = Num.nextInt(6);
        if (!Dices.getHold(i)) {
            Dices.setDice(i, j);
            Dices.invalidate();
        }
    }

    public void itemSelect(View v)
    {
        if (Roll == 0)
            return;
        TextView T = (TextView)v;
        Yahtzee.Process(this, Dices, T);
        Dices.releaseHold();
        Roll = 0;
        v = findViewById(R.id.BThrow);
        v.setClickable(true);
        T = (TextView)findViewById(R.id.nrolls);
        T.setText(String.valueOf(Roll));
    }
}
