package com.example.heyrobin.mainactivity.domain;

import java.util.Random;

/**
 * Created by HeyRobin on 6-3-2018.
 */

public class Color {

    private int r;
    private int g;
    private int b;

    public int createRandomColor()  {
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(255);

            if (r <= 0) {
                r = randomNumber;
            } else if (g <= 0) {
                g = randomNumber;
            } else {
                b = randomNumber;
            }
        }

        return android.graphics.Color.rgb(r, g, b);
    }
}
