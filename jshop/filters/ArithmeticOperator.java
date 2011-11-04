package jshop.filters;

import java.awt.image.*;
import java.awt.Image;
import jshop.filters.colorspaces.ColorSpace;
import jshop.filters.producers.ImageException;

/**
 *  Abstract class for two-image arithmetic operations. Subclasses should
 *   override <code>calculateData</code> to perform the actual operation.
 *   Filtering process does not start automatically, it must be started with
 *   <code>start</code> after the image producers have been set.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.531FC9C2-EE2F-49AA-4F0B-9B29E8FCBF2D]
// </editor-fold> 
public abstract class ArithmeticOperator extends Locks {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C45F8A96-F84D-B579-A63E-974D7C881AB0]
    // </editor-fold> 
    private static final long serialVersionUID = 4589096538304058630L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D5C85C2D-57D9-A19B-5C10-756754075F2A]
    // </editor-fold> 
    private int ch1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.97B0418A-178C-B032-4D25-58EAC651C2F2]
    // </editor-fold> 
    private int ch2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DEDC275F-EBA1-9F4F-850A-3E784B84CC5E]
    // </editor-fold> 
    private int width = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38EF8DC1-8BBC-3804-0246-F19303F05EA4]
    // </editor-fold> 
    private int height = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FD995E80-062D-9833-ECB3-0FD7A2FB14D2]
    // </editor-fold> 
    private Object data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D6934F70-A296-7572-1F71-37E194CFE970]
    // </editor-fold> 
    private ColorModel model;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E88F21D4-659D-FA6F-7F44-B381F68910F3]
    // </editor-fold> 
    private boolean first;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1D4CF4E0-0219-E562-55EF-286A02ED2FE6]
    // </editor-fold> 
    private boolean intpix;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A5E4AC33-5F71-FDEF-17F2-8639E6871B2D]
    // </editor-fold> 
    private ImageProducer img1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.010065A4-3AEF-04B6-F36F-8BECD7CF2BC8]
    // </editor-fold> 
    private ImageProducer img2;

    /**
     *  Creates a new <code>ArithmeticOperator</code> from image producers
     *  	 
     *  	 @param img1
     *  	            first image
     *  	 @param img2
     *  	            second image
     *  	 @param ch1
     *  	            channels from first image
     *  	 @param ch2
     *  	            channels from second image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CE004BAE-9CF3-A1EF-C644-D311DE96936B]
    // </editor-fold> 
    public ArithmeticOperator(ImageProducer img1, ImageProducer img2, int ch1, int ch2) {

        this.ch1 = ch1;
        this.ch2 = ch2;
        this.img1 = img1;
        this.img2 = img2;
        first = true;

    }

    /**
     *  Convenience for <code>Image.getSource()</code>
     *  	 
     *  	 @param img1
     *  	            first image
     *  	 @param img2
     *  	            second image
     *  	 @param ch1
     *  	            channels from first image
     *  	 @param ch2
     *  	            channels from second image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CD7F5605-43E2-1974-BB4C-52216C83B5D1]
    // </editor-fold> 
    public ArithmeticOperator(Image img1, Image img2, int ch1, int ch2) {

        this(img1.getSource(), img2.getSource(), ch1, ch2);

    }

    /**
     *  Creates a new <code>ArithmeticOperator</code> which affects all channels
     * @param img1
     * @param img2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6200CD7A-8015-6D6C-05C6-530221EDC62A]
    // </editor-fold> 
    public ArithmeticOperator(ImageProducer img1, ImageProducer img2) {

        this(img1, img2, ALL, ALL);

    }

    /**
     *  Convenience for <code>Image.getSource()</code>
     * @param img1
     * @param img2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5360635A-005F-1BF0-6CC1-344AFBFB855B]
    // </editor-fold> 
    public ArithmeticOperator(Image img1, Image img2) {

        this(img1.getSource(), img2.getSource(), ALL, ALL);

    }

    /**
     *  Creates an empty <code>ArithmeticOperator</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EFF39608-E0FD-CF1D-BFC1-53008D70E2EC]
    // </editor-fold> 
    public ArithmeticOperator() {

        ch1 = ALL;
        ch2 = ALL;
        first = true;

    }

    /**
     *  Sets the images as producers.
     * @param img1
     * @param img2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DFE863C8-3BAE-9157-81D8-C76FD177CAB2]
    // </editor-fold> 
    public void setImages(ImageProducer img1, ImageProducer img2) {

        this.img1 = img1;
        this.img2 = img2;

    }

    /**
     *  Sets the images
     * @param img1 
     * @param img2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.026DAA0C-851B-B82D-34DF-4E6FC3D95AF3]
    // </editor-fold> 
    public void setImages(Image img1, Image img2) {

        this.img1 = img1.getSource();
        this.img2 = img2.getSource();

    }

    /**
     *  Sets the channels of interest
     * @param ch1 
     * @param ch2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.89D2CEF7-DD2C-9617-AE7A-A54BA5251A2D]
    // </editor-fold> 
    public void setChannels(int ch1, int ch2) {

        this.ch1 = ch1;
        this.ch2 = ch2;

    }

    /**
     * Does nothing
     *
     * @param hints
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B40375D7-3374-0169-B570-EE307927EB1B]
    // </editor-fold> 
    public synchronized void setHints(int hints) {
    }

    /**
     *  Starts the filtering process
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B203C731-C536-DDEE-F146-97085CC5AF4B]
    // </editor-fold> 
    public synchronized void start() {

        img1.startProduction(this);
        first = false;
        img2.startProduction(this);

    }

    /**
     * Sets the color model
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.56C88F51-B7FD-581F-6C14-4C398A69D4E4]
    // </editor-fold> 
    public synchronized void setColorModel(ColorModel model) {

        if (first) {
            this.model = model;
        }

    }

    /**
     * Sets dimensions
     *
     * @param w
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B0C6F46F-9797-84F3-585D-529BEE81E87D]
    // </editor-fold> 
    public synchronized void setDimensions(int w, int h) {

        if (first) {
            width = w;
            height = h;
        }

    }

    /**
     * Filters the data. This calls <code>calculateData</code> of the subclass.
     *
     * @param pix
     * @param sc
     * @param m
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9AE38104-8BFF-C639-B619-50E98EE2CE28]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel m, int[] pix, int off, int sc) {

        if (data == null) {
            intpix = true;
            data = new int[width * height];
        }
        ColorSpace cs = getColorSpace();
        if (first) {
            int[] dat = (int[]) data;
            if (cs != null) {
                int soffset = 0;
                for (int cy = y; cy < h + y; cy++) {
                    for (int cx = x; cx < w + x; cx++) {
                        dat[off + width * cy + cx] = cs.convertRGBToInt(pix[soffset++]);
                    }
                }
            } else {
                for (int cy = y, sy = 0; sy < h; cy++, sy++) {
                    System.arraycopy(pix, sy * sc, dat, off + cy * width + x,
                            sc);
                }
            }
        } else {
            if (cs != null) {
                for (int i = 0; i < pix.length; i++) {
                    pix[i] = cs.convertRGBToInt(pix[i]);
                }
            }
            calculateData(x, y, w, h, pix, off, sc, (int[]) data, width,
                    height, ch1, ch2);
        }

    }

    /**
     *  Performs the operation. The operation is performed between the area data
     *  	 and the actual image data. Actual image data is obtained from the first
     *  	 argument to the constructor and area data from the second. The calculated
     *  	 data must be stored in the <i>data</i>-array, which is returned when
     *  	 <code>getData</code> is invoked.
     *  	 
     *  	 @param x
     *  	            x-coordinate of the area of interest
     *  	 @param y
     *  	            y-coordinate of the area of interest
     *  	 @param w
     *  	            width of the area
     *  	 @param h
     *  	            height of the area
     *  	 @param pix
     *  	            area data
     *  	 @param off
     *  	            data offset
     *  	 @param sc
     *  	            data scansize
     *  	 @param data
     *  	            data of the actual image. This is returned when
     *  	            <code>getData</code> is invoked
     *  	 @param wi
     *  	            image width
     *  	 @param he
     *  	            image height
     *  	 @param ch1
     *  	            channels from image 1
     *  	 @param ch2
     *  	            channels from image 2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.674B4A8B-25E0-F979-5EA9-AD5FD74D0B24]
    // </editor-fold> 
    protected abstract void calculateData(int x, int y, int w, int h, int[] pix, int off, int sc, int[] data, int wi, int he, int ch1, int ch2);

    /**
     * Notified when image is complete. This converts the image back to original
     * color space if needed.
     *
     * @exception jshop.filters.producers.ImageException
     *                When errors occur
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1ACED1AC-3A5C-A014-C40E-C387A5E7792D]
    // </editor-fold> 
    public synchronized void imageComplete(int status) {

        if (status == IMAGEERROR) {
            throw new ImageException();
        }
        ColorSpace cs = getColorSpace();
        if (!first && intpix && cs != null) {
            int[] dat = (int[]) data;
            for (int i = 0; i < dat.length; i++) {
                dat[i] = cs.convertToRGB(dat[i]);
            }
        }

    }

    /**
     *  Gets the filtered data
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB60248D-AF6C-91EA-74FE-0B4C61AD7BB7]
    // </editor-fold> 
    public synchronized Object getData() {

        return data;

    }

    /**
     *  Gets the width
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0E5D686F-F963-3D99-9EEB-CCC08F91B3B7]
    // </editor-fold> 
    public int getWidth() {

        return width;

    }

    /**
     *  Gets the height
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A45CD0E8-26D8-3796-6B58-B29055175861]
    // </editor-fold> 
    public int getHeight() {

        return height;

    }

    /**
     *  Gets the color model
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DD5C1A20-E2B0-27EC-70B5-7B0AF8BF0DE6]
    // </editor-fold> 
    public ColorModel getColorModel() {

        return model;

    }
} // End ArithmeticOperator