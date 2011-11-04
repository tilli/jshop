package jshop.filters.producers;

import java.awt.image.ColorModel;
import java.awt.Image;

/**
 *  Combines data from three images to a single image. One color channel is taken
 *   from each image to produce the result.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F02304FB-011D-5F26-75E6-99B153BB63CA]
// </editor-fold> 
public class ChannelCombiner extends ImageSource {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2EB62BF-10A4-D6A7-74BA-549AA0D8576B]
    // </editor-fold> 
    private int channel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4DC0B400-EF79-7AD5-00AC-2112C89CD8BC]
    // </editor-fold> 
    private boolean combined;

    /**
     *  Creates a new <code>ChannelCombiner</code>
     *  	 
     *  	 @param ch1
     *  	            Red channel source
     *  	 @param ch2
     *  	            Green channel source
     *  	 @param ch3
     *  	            Blue channel source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3D733F88-79A0-C4E2-B62F-3C82443D5712]
    // </editor-fold> 
    public ChannelCombiner(Image ch1, Image ch2, Image ch3) {

        int w = ch1.getWidth(null);
        w = Math.max(w, ch2.getWidth(null));
        w = Math.max(w, ch3.getWidth(null));
        int h = ch1.getHeight(null);
        h = Math.max(h, ch2.getHeight(null));
        h = Math.max(h, ch3.getHeight(null));
        super.setDimensions(w, h);
        combined = false;
        channel = 2;
        ch1.getSource().startProduction(this);
        channel = 1;
        ch2.getSource().startProduction(this);
        channel = 0;
        ch3.getSource().startProduction(this);
        combined = true;

    }

    /**
     * Sets the dimensions
     *
     * @param w
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E6E02F58-7153-9B7F-5335-1A55355E559F]
    // </editor-fold> 
    public void setDimensions(int w, int h) {

        if (combined) {
            super.setDimensions(w, h);
        }

    }

    /**
     * Combines the channels
     *
     * @param m
     * @param dt
     * @param o
     * @param s 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BAA5FBDF-FF15-65A3-CB29-9EF6BC24238B]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel m, int[] dt, int o, int s) {

        if (combined) {
            super.setPixels(x, y, w, h, m, dt, o, s);
            return;
        }
        int soffset = 0;
        int width = getWidth();
        for (int dy = y; dy < y + h; dy++) {
            for (int dx = x; dx < x + w; dx++) {
                dt[dy * width + dx + o] |= ((dt[soffset] & 0xff000000) | (dt[soffset++] & (0xff << 8 * channel)));
            }
        }
        super.setPixels(x, y, w, h, m, dt, o, s);

    }
} // End ChannelCombiner