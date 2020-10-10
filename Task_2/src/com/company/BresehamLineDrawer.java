package com.company;

import java.awt.*;

public class BresehamLineDrawer implements LineDrawer {
    PixelDrawer pd;

    public BresehamLineDrawer(PixelDrawer pd) {
        this.pd = pd;
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

        int dx = x1 - x0;
        int dy = Math.abs(y1 - y0);
        int e = 2 * dy - dx;
        int incrementY = (y0 < y1) ? 1 : -1;


        if (steep)
            for (int x = x0; x <= x1; x++) {
                pd.setPixel(y0, x, Color.BLACK);

                if (e >= 0) {
                    y0 += incrementY;
                    e += -2 * dx + 2 * dy;
                }
                else
                    e += 2 * dy;
            }
        else
            for (int x = x0; x <= x1; x++) {
                pd.setPixel(x, y0, Color.BLACK);

                if (e >= 0) {
                    y0 += incrementY;
                    e += -2 * dx + 2 * dy;
                }
                else
                    e += 2 * dy;
            }
    }
}
