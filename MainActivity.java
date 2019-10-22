package com.example.pc.colorgame;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    View[][] tiles = new View[4][4];
    int col1, col2;
    boolean over;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        col1 = r.getColor(R.color.dark);
        col2 = r.getColor(R.color.light);
        tiles[0][0] = findViewById(R.id.a1);
        tiles[0][1] = findViewById(R.id.a2);
        tiles[0][2] = findViewById(R.id.a3);
        tiles[0][3] = findViewById(R.id.a4);
        tiles[1][0] = findViewById(R.id.b1);
        tiles[1][1] = findViewById(R.id.b2);
        tiles[1][2] = findViewById(R.id.b3);
        tiles[1][3] = findViewById(R.id.b4);
        tiles[2][0] = findViewById(R.id.c1);
        tiles[2][1] = findViewById(R.id.c2);
        tiles[2][2] = findViewById(R.id.c3);
        tiles[2][3] = findViewById(R.id.c4);
        tiles[3][0] = findViewById(R.id.d1);
        tiles[3][1] = findViewById(R.id.d2);
        tiles[3][2] = findViewById(R.id.d3);
        tiles[3][3] = findViewById(R.id.d4);

        randField();
    }

    public void changeColor(View v)
    {
        ColorDrawable color = (ColorDrawable) v.getBackground();
        if (color.getColor() == col1)
            v.setBackgroundColor(col2);
        else
            v.setBackgroundColor(col1);
    }

    public void randField()
    {
        for (int i = 0;i < 4;i++)
        {
            for (int j = 0;j < 4;j++)
            {
                double rand = Math.random();
                if (rand > 0.5)
                {
                    changeColor(tiles[i][j]);
                }
            }
        }
    }

    public void onClick(View v)
    {
        String[] tag = v.getTag().toString().split(" ");

        int x = Integer.parseInt(tag[0]);
        int y = Integer.parseInt(tag[1]);

        over = true;
        ColorDrawable color, color2;
        changeColor(tiles[x][y]);
        for (int i = 0;i < 4;i++)
        {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }

        color = (ColorDrawable) tiles[0][0].getBackground();
        for (int i = 0;i < 4;i++)
        {
            for (int j = 0;j < 4;j++)
            {
                color2 = (ColorDrawable) tiles[i][j].getBackground();
                if (color.getColor() != color2.getColor())
                {
                    over = false;
                    break;
                }
            }
            if (!over)
                break;
        }

        if (over)
        {
            Toast ending = Toast.makeText(this, "Вы победили!", Toast.LENGTH_LONG);
            ending.show();
        }
    }
}
