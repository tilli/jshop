package jshop.filters;

import java.awt.image.*;
import jshop.filters.colorspaces.ColorSpace;

/**
 *  Collects histogram data of an image to arrays. Subclasses should provide
 *   methods to process these arrays. Following methods are available for
 *   processing:
 *   <ul>
 *   <li>If image is true color image (integer data), <code>getFilteredData</code>
 *   is invoked
 *   <li>If <code>isSpecialModel</code> returns true, <code>getByteData</code> is
 *   invoked. By default it returns true if the image is a gray scale image
 *   <li>If <code>isSpecialModel</code> returns false <code>getIndexedData</code>
 *   is invoked. This is meant for indexed color images.
 *   </ul>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.266AF846-53EB-97E0-574D-F943257DFDAD]
// </editor-fold> 
public abstract class HistogramCollector extends Locks {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FC6A4649-E85D-39FA-E89A-AF874B592F49]
    // </editor-fold> 
    private static final long serialVersionUID = 1210286495983839702L;
    /**
     *  Preserves the ratio between channel values.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.19342A09-ADBD-53A5-DA33-B4274CA7411E]
    // </editor-fold> 
    public static final int RATIO = 1;
    /**
     *  Preserves the difference between channel values. This is on by default.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BDE5300C-B2AB-4EBC-70C1-AEA15796DC1E]
    // </editor-fold> 
    public static final int DIFF = 2;
    /**
     *  Alters each channel separately. This is automatically turned on if any of
     *  	 the channels is locked
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85DCC09D-C4F3-2A95-3005-999F3E068C0A]
    // </editor-fold> 
    public static final int CHANNELS = 3;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38600172-3932-6D04-1DAB-E391137A3C0D]
    // </editor-fold> 
    private int histstyle = 2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CAD898B6-F3BC-91A0-66D7-01B9DC47DC25]
    // </editor-fold> 
    private int[] rdata;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DEA2FB96-1E88-8ED9-7B26-594EF17C562A]
    // </editor-fold> 
    private int[] gdata;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7AE86181-69AC-D7DC-B267-C9CBEC7FC173]
    // </editor-fold> 
    private int[] bdata;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0FFB7338-D66E-09BC-4F8E-EDD382DDC0B3]
    // </editor-fold> 
    private Object dataobject;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.90C04D5C-F1D1-C01C-B0E9-3C8B5BEDDD6B]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EC7DB846-F542-4353-33B3-49F6DE93C020]
    // </editor-fold> 
    private int height;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A0DCF17F-7C22-8DB6-1C34-094B832D0137]
    // </editor-fold> 
    private ColorModel model;

    /**
     * Sets the color model of the consumer
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B7571EAF-D458-EF1A-6574-2644B46927FF]
    // </editor-fold> 
    public void setColorModel(ColorModel model) {

        this.model = model;
        consumer.setColorModel(model);

    }

    /**
     * Turns the completescanlines and topdownleftright flags on
     *
     * @param hints 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.89299897-E5AB-45E1-147E-54A9DBC2A645]
    // </editor-fold> 
    public void setHints(int hints) {

        consumer.setHints(COMPLETESCANLINES | TOPDOWNLEFTRIGHT);

    }

    /**
     * Sets the dimensions of the image consumer and creates the int-array
     *
     * @param w
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.33AA0906-9D59-82C1-3D03-D99782ECEB06]
    // </editor-fold> 
    public void setDimensions(int w, int h) {

        width = w;
        height = h;
        consumer.setDimensions(width, height);

    }

    /**
     * Collects the histogram data.
     *
     * @param offset
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BBF0D1BB-4C3D-F30D-21C9-EEC174D7C043]
    // </editor-fold> 
    public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int offset, int scansize) {

        if (dataobject == null) {
            dataobject = new int[width * height];
            rdata = new int[256];
            gdata = new int[256];
            bdata = new int[256];
        }
        int[] pix = (int[]) dataobject;
        ColorSpace cs = getColorSpace();
        int dind, sind;
        if (cs != null) { // Converts the pixels to current color space
            for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                    dind = dx + dy * width;
                    sind = sx + sy * w;
                    rdata[((cs.convertRGBToInt(pixels[sind]) >> 16) & 0xff)]++;
                    gdata[((cs.convertRGBToInt(pixels[sind]) >> 8) & 0xff)]++;
                    bdata[((cs.convertRGBToInt(pixels[sind])) & 0xff)]++;
                    pix[dind] = cs.convertRGBToInt(pixels[sind]);
                }
            }
        } else {
            for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                    dind = dx + dy * width;
                    sind = sx + sy * w;
                    rdata[(pixels[sind] >> 16) & 0xff]++;
                    gdata[(pixels[sind] >> 8) & 0xff]++;
                    bdata[pixels[sind] & 0xff]++;
                    pix[dind] = pixels[sind];
                }
            }
        }

    }

    /**
     * Sets the pixels to a byte-array
     *
     * @param offset
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C27809CB-36AB-321D-962A-D9F15BD186F8]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int offset, int scansize) {

        if (dataobject == null) {
            dataobject = new byte[width * height];
            rdata = new int[((IndexColorModel) this.model).getMapSize()];
        }
        int sind, dind;
        for (int dy = y, sy = 0; sy < h; dy++, sy++) {
            for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                dind = dx + dy * width;
                sind = sx + sy * w;
                rdata[pixels[sind] & 0xff]++;
                rdata[dind] = pixels[sind];
            }
        }

    }

    /**
     * Filters the image.
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.78C48249-F57A-D864-AD57-A21CFCDEB4F2]
    // </editor-fold> 
    public void imageComplete(int status) {

        if (dataobject instanceof int[]) {
            complete((int[]) dataobject);
        } else if (isSpecialModel((IndexColorModel) model)) {
            complete((byte[]) dataobject, true);
        } else {
            complete((byte[]) dataobject, false);
        }
        consumer.imageComplete(status);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.40E0A219-BB37-A585-5836-A1B8005247E9]
    // </editor-fold> 
    private void complete(byte[] pix, boolean special) {

        int[] filtered;
        if (special) {
            filtered = getByteData(rdata, width, height);
        } else {
            filtered = getIndexedData(rdata, width, height);
        }
        for (int i = 0; i < pix.length; i++) {
            pix[i] = (byte) filtered[pix[i] & 0xff];
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.88987ADA-415F-0A6C-3C17-A39C39C76C80]
    // </editor-fold> 
    private void complete(int[] pix) {

        int[][] s = getFilteredData(new int[][]{rdata, gdata, bdata}, width,
                height);
        boolean ch1 = getLock(CH1);
        boolean ch2 = getLock(CH2);
        boolean ch3 = getLock(CH3);
        int red, gre, blu, rorig, gorig, borig;
        int flag = getHistogramFlag();
        for (int i = 0; i < pix.length; i++) {
            rorig = (pix[i] >> 16) & 0xff;
            gorig = (pix[i] >> 8) & 0xff;
            borig = pix[i] & 0xff;
            red = s[0][rorig];
            gre = s[1][gorig];
            blu = s[2][borig];
            if (flag == CHANNELS) {
                if (ch1) {
                    red = rorig;
                }
                if (ch2) {
                    gre = gorig;
                }
                if (ch3) {
                    blu = borig;
                }
            } else if (flag == DIFF) {
                float origav = (float) (rorig + gorig + borig) / 3;
                float rpr = rorig - origav;
                float gpr = gorig - origav;
                float bpr = borig - origav;
                float dav = (red + gre + blu) / 3;
                red = (int) (dav + rpr);
                gre = (int) (dav + gpr);
                blu = (int) (dav + bpr);
            } else if (flag == RATIO) {
                float origav = (float) (rorig + gorig + borig) / 3;
                if (origav == 0) {
                    origav++;
                }
                double rpr = (double) rorig / (double) origav;
                double gpr = (double) gorig / (double) origav;
                double bpr = (double) borig / (double) origav;
                float dav = (red + gre + blu) / 3;
                red = (int) (dav * rpr);
                gre = (int) (dav * gpr);
                blu = (int) (dav * bpr);
            }
            if (red > 255) {
                red = 255;
            } else if (red < 0) {
                red = 0;
            }
            if (gre > 255) {
                gre = 255;
            } else if (gre < 0) {
                gre = 0;
            }
            if (blu > 255) {
                blu = 255;
            } else if (blu < 0) {
                blu = 0;
            }
            pix[i] = (pix[i] & 0xff000000) | (red << 16) | (gre << 8) | blu;
        }
        consumer.setPixels(0, 0, width, height, model, pix, 0, width);

    }

    /**
     *  Returns the filtered data. A filter similar to ColorValueSetter is used
     *  	 to process this data.
     *  	 
     *  	 @see ColorValueSetter
     *  	 @return new histograms. array[0] is channel 1, array[1] is channel 2 and
     *  	         array[3] is channel 3. The arrays should contain values between 0
     *  	         and 255.
     *  	 @param data
     *  	            histogram data
     *  	 @param w
     *  	            width of the image
     *  	 @param h
     *  	            height of the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.167C9786-8ED8-6C2E-EA55-DCB2B58371BF]
    // </editor-fold> 
    protected int[][] getFilteredData(int[][] data, int w, int h) {

        return data;

    }

    /**
     *  Filters indexed color images.
     *  	 
     *  	 @return new histogram. This should contain as many instances as the
     *  	         original
     *  	 @param data
     *  	            histogram data
     *  	 @param w
     *  	            width of the image
     *  	 @param h
     *  	            height of the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.17F20145-0EA1-27D4-24BB-7130FFEBDB21]
    // </editor-fold> 
    protected int[] getIndexedData(int[] data, int w, int h) {

        return data;

    }

    /**
     *  Filters gray scale images.
     *  	 
     *  	 @return new histogram.
     *  	 @param data
     *  	            histogram data
     *  	 @param w
     *  	            width of the image
     *  	 @param h
     *  	            height of the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.74749ACD-87FE-40A6-903B-78F8E3C61121]
    // </editor-fold> 
    protected int[] getByteData(int[] data, int w, int h) {

        return data;

    }

    /**
     *  Gets the histogram control flag. If any channel lock is on the
     *  	 CHANNELS-flag will be returned
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C2518AA3-A8E3-51BB-CB60-268E4A09EBC5]
    // </editor-fold> 
    public int getHistogramFlag() {

        if (getLocks() == 0) {
            return histstyle;
        } else {
            return CHANNELS;
        }

    }

    /**
     *  Sets the histogram control flag.
     * @param style
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.63C2B72A-C482-436E-6DFA-D44AD6FD6712]
    // </editor-fold> 
    public void setHistogramFlag(int style) {

        histstyle = style;

    }

    /**
     *  Checks the given color model. By default this tests if the color model is
     *  	 a direct gray scale from 0 to 255 (0 black, 255 white). If this returns
     *  	 true <code>getByteData</code> is invoked instead of
     *  	 <code>getIndexedData</code>
     * @param mod 
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.73AE6C6D-A272-0786-A70C-2B2780DAB6E7]
    // </editor-fold> 
    protected boolean isSpecialModel(IndexColorModel mod) {

        for (int i = 0; i < 255; i++) {
            if ((mod.getRGB(i) & 0xffffff) != ((i << 16) | (i << 8) | i)) {
                return false;
            }
        }
        return true;

    }

    /**
     *  Gets the color model
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.71A27C29-E533-7695-340F-D236F6BDE514]
    // </editor-fold> 
    public ColorModel getColorModel() {

        return model;

    }
} // End HistogramCollector