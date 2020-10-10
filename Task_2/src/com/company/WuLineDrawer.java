package com.company;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    int integerPartOfNumber(float x) {
        return (int) Math.floor(x);
    }

    float floatingPartOfNumber(float x) {
        if (x > 0) return x - integerPartOfNumber(x);
        else return x - (integerPartOfNumber(x) + 1);

    }

    float reverseFloatingPartOfNumber(float x) {
        return 1 - floatingPartOfNumber(x);
    }


    @Override
    public void drawLine(int x0, int y0, int x1, int y1) {
        boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);

        if (steep) {
            x0 += y0;
            y0 = x0 - y0;
            x0 -= y0;

            x1 += y1;
            y1 = x1 - y1;
            x1 -= y1;
        }
        if (x0 > x1) {
            x0 += x1;
            x1 = x0 - x1;
            x0 -= x1;

            y0 += y1;
            y1 = y0 - y1;
            y0 -= y1;
        }

        float dx = (x1 - x0);
        float dy = (y1 - y0);

        float delta = dy / dx;

        float y = y0;

        if (steep)
            for (int i = x0; i < x1; i++) {
                float r = floatingPartOfNumber(y);
                float f = reverseFloatingPartOfNumber(y);

                pd.setPixel((int)y, i, new Color(r, r, r));
                pd.setPixel((int)y + 1, i, new Color(f, f, f));
                y += delta;
            }
        else
            for (int i = x0; i < x1; i++) {
                float r = floatingPartOfNumber(y);
                float f = reverseFloatingPartOfNumber(y);

                pd.setPixel(i,(int) y, new Color(r, r, r));
                pd.setPixel(i, (int)y + 1, new Color(f, f, f));
                y += delta;
            }

    }
}
