package jshop.filters;

import java.awt.image.*;

/**
 *  Filter which calculates two images using formula sqrt(pix1pix1+pix2pix2)<br>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.02D38ADE-572A-AED4-4C0D-EF6F1F6E14D1]
// </editor-fold> 
public class GradientOperator extends ArithmeticOperator {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3C5E9910-466C-A1DF-5DA7-D159362A517A]
    // </editor-fold> 
    private static final long serialVersionUID = 2155564680638968218L;

    /**
     *  Creates an empty <code>GradientOperator</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3CE60D13-9332-50AC-1632-08F2D2790C87]
    // </editor-fold> 
    public GradientOperator() {

        super();

    }

    /**
     *  Creates a new <code>GradientOperator</code>
     * @param ip1 
     * @param ip2
     * @param ch1
     * @param ch2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BD48BBAE-BA9A-0B9C-31DB-23D1423BDE75]
    // </editor-fold> 
    public GradientOperator(ImageProducer ip1, ImageProducer ip2, int ch1, int ch2) {

        super(ip1, ip2, ch1, ch2);

    }

    /**
     * Calculates the gradient
     *
     * @param area
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.10271CBA-B249-104C-7E75-F4F62E0998F5]
    // </editor-fold> 
    protected synchronized void calculateData(int x, int y, int w, int h, int[] area, int off, int sc, int[] data, int width, int height, int ch1, int ch2) {

        int red, gre, blu, ared, agre, ablu;
        for (int dy = y, sy = 0; dy < y + h; dy++, sy++) {
            for (int dx = x, sx = 0; dx < x + w; dx++, sx++) {
                int ind = dy * width + dx;
                int ind2 = sy * w + sx;
                red = (data[ind] >> 16) & 0xff;
                ared = (area[ind2] >> 16) & 0xff;
                red = (int) Math.sqrt(red * red + ared * ared) & 0xff;
                gre = (data[ind] >> 8) & 0xff;
                agre = (area[ind2] >> 8) & 0xff;
                gre = (int) Math.sqrt(gre * gre + agre * agre) & 0xff;
                blu = data[ind] & 0xff;
                ablu = area[ind2] & 0xff;
                blu = (int) Math.sqrt(blu * blu + ablu * ablu) & 0xff;
                data[ind] = (data[ind] & 0xff000000) | (red << 16) | (gre << 8) | blu;
            }
        }

    }
} // End ArithmeticOperator