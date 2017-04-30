package org.mburm.yahtzee;

import android.widget.TextView;

/**
 * Created by mbmis006 on 4/29/2017.
 */

public class Yahtzee {
    static TextView Tx;
    static DiceView Dices;
    static YahtzeeActivity Ac;
    static int Turn;

    public static void Init()
    {
        Turn = 1;
    }

    public static void Process(YahtzeeActivity A, DiceView d, TextView v)
    {
        Dices = d;
        Ac = A;
        Tx = v;
        int I = v.getId();
        switch (I)
        {
            case R.id.Ones: One();
                break;
            case R.id.Twos: Two();
                break;
            case R.id.Threes: Three();
                break;
            case R.id.Fours: Four();
                break;
            case R.id.Fives: Five();
                break;
            case R.id.Sixes: Six();
                break;
            case R.id.n3ofkind: Threeofkind();
                break;
            case R.id.n4ofkind: Fourofkind();
                break;
            case R.id.nfullhouse: Fullhouse();
                break;
            case R.id.nsmalls: Smallstraight();
                break;
            case R.id.nlarges: Largestraight();
                break;
            case R.id.nyahtzee: Yahtzee();
                break;
            case R.id.nchance: Chance();
                break;
        }
        Tx.setBackground(null);
        UpperSubTotal();
        LowerTotal();
        GrandTotal();
        doTurn();
    }

    private static void One()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 0)
                t = t + 1;
        Tx.setText(String.valueOf(t));
    }

    private static void Two()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 1)
                t = t + 2;
        Tx.setText(String.valueOf(t));
    }

    private static void Three()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 2)
                t = t + 3;
        Tx.setText(String.valueOf(t));
    }

    private static void Four()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 3)
                t = t + 4;
        Tx.setText(String.valueOf(t));
    }

    private static void Five()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 4)
                t = t + 5;
        Tx.setText(String.valueOf(t));
    }

    private static void Six()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            if (Dices.getDice(i) == 5)
                t = t + 6;
        Tx.setText(String.valueOf(t));
    }

    private static int UpperSubTotal()
    {
        int t = 0;
        Tx = (TextView) Ac.findViewById(R.id.Ones);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Twos);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Threes);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Fours);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Fives);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Sixes);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.Subtotal);
        Tx.setText(String.valueOf(t));
        Bonus(t);
        return t;
    }

    private static int Bonus(int t)
    {

        Tx = (TextView) Ac.findViewById(R.id.bonus);
        if (t >= 63) {
            Tx.setText("35");
            t = t + 35;
        }
        UpperTotal(t);
        return t;
    }

    private static int UpperTotal(int t)
    {
        Tx = (TextView) Ac.findViewById(R.id.UpperTotal);
        Tx.setText(String.valueOf(t));
        return t;
    }

    private static void Threeofkind()
    {
        int t = 0;
        for (int i=0;i<4;i++) {
            t = 0;
            for (int j = i+1; j < 5; j++)
                if (Dices.getDice(i) == Dices.getDice(j))
                    t++;
            if (t > 1) {
                t = Dices.getDice(0) + Dices.getDice(1) + Dices.getDice(2) +
                        Dices.getDice(3) + Dices.getDice(4) + 5;
                Tx.setText(String.valueOf(t));
                i = 6;
            }
        }
    }

    private static void Fourofkind()
    {
        int t = 0;
        for (int i=0;i<4;i++) {
            t = 0;
            for (int j = i+1; j < 5; j++)
                if (Dices.getDice(i) == Dices.getDice(j))
                    t++;
            if (t > 2) {
                t = Dices.getDice(0) + Dices.getDice(1) + Dices.getDice(2) +
                        Dices.getDice(3) + Dices.getDice(4) + 5;
                Tx.setText(String.valueOf(t));
                i = 6;
            }
        }
    }

    private static void Fullhouse()
    {
        int t = 0;
        int d = 0;
        for (int i=1;i<5;i++)
        {
            if (Dices.getDice(d) != Dices.getDice(i))
            {
                d = i;
                i = 6;
            }
        }

        if (d > 0)
        {
            t = 0;
            for (int i=0;i<5;i++)
            {
                if (Dices.getDice(d) == Dices.getDice(i))
                    t++;
            }
            if (t == 3)  //found the 3 same
            {
                t = 0;
                for (int i=0;i<5;i++)
                    if (Dices.getDice(0)==Dices.getDice(i))
                        t++;
                if (t == 2)
                    Tx.setText("25");
            }
            if (t == 2)  //found the 2 same
            {
                t = 0;
                for (int i=0;i<5;i++)
                    if (Dices.getDice(0)==Dices.getDice(i))
                        t++;
                if (t == 3)
                    Tx.setText("25");
            }
        }
    }

    private static void Smallstraight()
    {
        int j = 0;
        int t = 0;
        for (int i=0;i<5;i++)
            j = j | 1 << Dices.getDice(i);
        for (int i=0;i<5;i++)
            if ((j & (1<<i)) != 0) {
                t++;
                if (t == 3)
                    Tx.setText("30");
            }
            else
                t = 0;
    }

    private static void Largestraight()
    {
        int j = 0;
        int t = 0;
        for (int i=0;i<5;i++)
            j = j | (1 << Dices.getDice(i));
        for (int i=0;i<5;i++)
            if ((j & (1<<i)) != 0) {
                t++;
                if (t == 4)
                    Tx.setText("40");
            }
            else
                t = 0;
    }

    private static void Yahtzee()
    {
        int t = 0;
        for (int i=1;i<5;i++)
            if (Dices.getDice(i) != Dices.getDice(0))
                t = 1;
        if (t == 0)
            Tx.setText("50");
    }

    private static void Chance()
    {
        int t = 0;
        for (int i=0;i<5;i++)
            t = t + Dices.getDice(0)+1;
        Tx.setText(String.valueOf(t));
    }

    private static int LowerTotal()
    {
        int t = 0;
        Tx = (TextView) Ac.findViewById(R.id.n3ofkind);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.n4ofkind);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.nfullhouse);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.nsmalls);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.nlarges);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.nyahtzee);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.nchance);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.LowerTotal);
        Tx.setText(String.valueOf(t));
        return t;
    }

    private static int GrandTotal()
    {
        int t = 0;
        Tx = (TextView) Ac.findViewById(R.id.LowerTotal);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.UpperTotal);
        t = t + Integer.parseInt((String)Tx.getText());
        Tx = (TextView) Ac.findViewById(R.id.GrandTotal);
        Tx.setText(String.valueOf(t));
        return t;
    }

    private static void doTurn()
    {
        Turn++;
        Tx = (TextView) Ac.findViewById(R.id.nturn);
        Tx.setText(String.valueOf(Turn));
    }


}
