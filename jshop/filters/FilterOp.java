/*
 * Tehty 1.10.1997
 */
package jshop.filters;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import jshop.filters.producers.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.ReplicateScaleFilter;
import jshop.filters.kernel.*;

/**
 *  Wraps for all filter operations. Methods apply cumulatively to the image that
 *   was passed to the constructor. Filtered image is returned with
 *   <code>getfilteredImage</code>.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.13F4C6E0-68A9-B318-39D2-329C79B39EEB]
// </editor-fold> 
public final class FilterOp {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3B460340-5EB7-C4B6-0ACA-D9A317DD79C8]
    // </editor-fold> 
    private ImageSource image;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.53F2898F-A176-A00C-D848-1A4AE9D0C915]
    // </editor-fold> 
    private ImageFilter filter;
    /**
     *  North light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4B57C88E-32E2-BDFA-5A5E-F1C7D8916AE6]
    // </editor-fold> 
    public static final int N = 0;
    /**
     *  Northeast light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ECD2BE89-7649-4C55-47ED-3E31F91D9F15]
    // </editor-fold> 
    public static final int NE = 1;
    /**
     *  East light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.66AC9218-D880-A802-F463-534FAD2D28C1]
    // </editor-fold> 
    public static final int E = 2;
    /**
     *  Southeast light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38703DAE-6A36-E7C3-DF11-1C675175C17C]
    // </editor-fold> 
    public static final int SE = 3;
    /**
     *  South light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0960BB5C-2B1D-F990-7EAB-4D43D5DFCF2E]
    // </editor-fold> 
    public static final int S = 4;
    /**
     *  Southwest light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5E9B7463-D0D1-910B-EB7C-0FD35EE2AF3B]
    // </editor-fold> 
    public static final int SW = 5;
    /**
     *  West light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.06AEC771-0AEE-5948-8147-D3412D2F7061]
    // </editor-fold> 
    public static final int W = 6;
    /**
     *  Northwest light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D0FBD985-D0E8-4206-FEB0-886688B59015]
    // </editor-fold> 
    public static final int NW = 7;
    /**
     *  Light filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AA5F9A89-45B9-3317-2961-B384CFAAE404]
    // </editor-fold> 
    public static final int LIGHT = 0;
    /**
     *  Medium filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EE87B583-6E20-3AD9-F0F9-84C8E7B6E923]
    // </editor-fold> 
    public static final int MEDIUM = 1;
    /**
     *  Strong filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DC236520-D43D-6A70-9695-32200AC0EADE]
    // </editor-fold> 
    public static final int STRONG = 2;
    /**
     *  Gaussian averaging (blur)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.886C359C-8187-0DF5-DFF1-33EC2A3817C5]
    // </editor-fold> 
    public static final int GAUSS = 4;
    /**
     *  All edges
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4A245089-6264-E4D9-679D-29E9DA8DB691]
    // </editor-fold> 
    public static final int ALL = LineDetector.ALL;
    /**
     *  Horizontal lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F982DB3D-898C-091F-348C-331A14A9617F]
    // </editor-fold> 
    public static final int HORIZONTAL = LineDetector.HORIZONTAL;
    /**
     *  Vertical lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7776DB7-3811-9CC7-322C-E605D515B98D]
    // </editor-fold> 
    public static final int VERTICAL = LineDetector.VERTICAL;
    /**
     *  Diagonal from top left to bottom right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C061AE67-992C-1229-299D-8ECEFDF89B40]
    // </editor-fold> 
    public static final int TOPLEFT = LineDetector.TOPLEFT;
    /**
     *  Diagonal from top right to bottom left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9D51CCF2-ED4C-3A40-49B8-704D61BF22EB]
    // </editor-fold> 
    public static final int TOPRIGHT = LineDetector.TOPRIGHT;

    /**
     *  Creates a new FilterOp instance. If the producer of the image is not
     *  	 instance of an <code>ImageSource</code> it will be converted with
     *  	 <code>SourceConvertter</code>
     *  	 
     *  	 @param img
     * @see jshop.filters.producers.SourceConvertter
     *  	 @see jshop.filters.producers.ImageSource
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7D575716-8B60-4072-04A4-625C9A65FAAC]
    // </editor-fold> 
    public FilterOp(Image img) {

        if (img.getSource() instanceof ImageSource) {
            image = (ImageSource) img.getSource();
        } else {
            image = new ImageSource(img.getSource());
        }

    }

    /**
     *  Blurs the image
     *  	 
     *  	 @param strength
     *  	            filter strength (see fields)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E2A98893-56F8-06B1-FDDA-E4FA766EEC2A]
    // </editor-fold> 
    public void blur(int strength) {

        Kernel k;
        if (strength == GAUSS) {
            k = new Gauss3();
        } else if (strength == LIGHT) {
            k = new Average3();
        } else if (strength == MEDIUM) {
            k = new Gauss5();
        } else {
            k = new Average7();
        }
        filter = new ConvolutionFilter(k);
        image = image.filterImage(filter);

    }

    /**
     *  Sharpens the image
     *  	 
     *  	 @param strength
     *  	            filter strength (see fields)
     *  	 @see jshop.filters.kernel.EdgeEnhancer
     *  	 @see jshop.filters.kernel.MediumSharpener
     *  	 @see jshop.filters.kernel.LightSharpener
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.18AE1602-DD7D-878B-E577-013D9640BE42]
    // </editor-fold> 
    public void sharpen(int strength) {

        Kernel k;
        if (strength == LIGHT) {
            k = new LightSharpener();
        } else if (strength == MEDIUM) {
            k = new MediumSharpener();
        } else {
            k = new EdgeEnhancer();
        }
        filter = new ConvolutionFilter(k);
        image = image.filterImage(filter);

    }

    /**
     *  Detects lines which are aligned with given direction
     *  	 
     *  	 @param direction
     * @see jshop.filters.kernel.LineDetector
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C275283E-34A7-0B31-9B63-4E8E03871476]
    // </editor-fold> 
    public void detectLines(int direction) {

        detectLines(direction, 1);

    }

    /**
     *  Line detection filter.
     *  	 
     *  	 @param direction
     * @param strength
     * @see jshop.filters.kernel.LineDetector
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BAF835DF-57B0-52E0-C912-46C4AF4D2BE7]
    // </editor-fold> 
    public void detectLines(int direction, int strength) {

        LineDetector ln = new LineDetector(direction, strength);
        filter = new ConvolutionFilter(ln);
        image = image.filterImage(filter);

    }

    /**
     *  Edge detection filter
     *  	 
     *  	 @param direction
     * @see jshop.filters.kernel.EdgeDetector
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2448A32D-7CE8-21DE-61BF-C8CA88FF9AA5]
    // </editor-fold> 
    public void detectEdges(int direction) {

        detectEdges(direction, 1, true);

    }

    /**
     *  Edge detection filter. This filter uses <code>GradientOperator</code> if
     *  	 all edges need to be detected.
     *  	 
     *  	 @param direction 
     * @param strength
     * @param inverted
     * @see jshop.filters.kernel.EdgeDetector
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E1A29EAA-409F-7C01-1122-1B6CDD9AD2E0]
    // </editor-fold> 
    public synchronized void detectEdges(int direction, int strength, boolean inverted) {

        if (direction == ALL) { // Uses GradientOperator to calculate the
            // gradient
            EdgeDetector ed = new EdgeDetector(EdgeDetector.HORIZONTAL,
                    strength, inverted);
            filter = new ConvolutionFilter(ed) {

                private static final long serialVersionUID = 1L;

                @Override
                protected boolean usesLocks() {
                    return false;
                }
            };
            ImageSource img = image;
            image = image.filterImage(filter);
            ed = new EdgeDetector(EdgeDetector.VERTICAL, strength, inverted);
            filter = new ConvolutionFilter(ed) {

                private static final long serialVersionUID = 1L;

                @Override
                protected boolean usesLocks() {
                    return false;
                }
            };
            img = img.filterImage(filter);
            GradientOperator grad = new GradientOperator(image, img, Locks.ALL,
                    Locks.ALL);
            grad.start();
            image = new ImageSource(grad.getData(), grad.getWidth(), grad.getHeight(), grad.getColorModel());
            return;
        }
        EdgeDetector ed = new EdgeDetector(direction, strength, inverted);
        filter = new ConvolutionFilter(ed);
        image = image.filterImage(filter);

    }

    /**
     *  Embosses the image with grayscale background
     *  	 
     *  	 @param bg
     * @param dir
     * @param str
     * @see jshop.filters.kernel.Embosser
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2DFD47F4-CD1B-C329-D124-A79CDFDDF17D]
    // </editor-fold> 
    public void emboss(int bg, int dir, int str) {

        emboss(bg, bg, bg, dir, str);

    }

    /**
     *  Embosses the image
     *  	 
     *  	 @param bgr 
     * @param bgg
     * @param bgb
     * @param dir
     * @param str
     * @see jshop.filters.kernel.Embosser
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DF68DE78-586C-CC75-2D41-FA978CF88E64]
    // </editor-fold> 
    public void emboss(int bgr, int bgg, int bgb, int dir, int str) {

        Embosser e = new Embosser(bgr, bgg, bgb, dir, str);
        filter = new ConvolutionFilter(e);
        image = image.filterImage(filter);

    }

    /**
     *  Gammacorrects the image
     *  	 
     *  	 @param value
     * @see GammaCorrectionFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7EFBCFDB-7521-2800-BC24-300939236201]
    // </editor-fold> 
    public void gammaCorrect(double value) {

        filter = new GammaCorrectionFilter(value);
        image = image.filterImage(filter);

    }

    /**
     *  Splits the red channel from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.11F44278-1476-A764-7E36-0224CFF4785E]
    // </editor-fold> 
    public void splitRed() {

        filter = new ChannelSplitter(Locks.CH1);
        image = image.filterImage(filter);

    }

    /**
     *  Splits the green channel from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B9E3545C-1559-7FD5-679F-D7E8E3EA8C9D]
    // </editor-fold> 
    public void splitGreen() {

        filter = new ChannelSplitter(Locks.CH2);
        image = image.filterImage(filter);

    }

    /**
     *  Splits the blue channel from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8271EF77-4140-2380-6370-7526EB26B72F]
    // </editor-fold> 
    public void splitBlue() {

        filter = new ChannelSplitter(Locks.CH3);
        image = image.filterImage(filter);

    }

    /**
     *  Creates a negative of the image
     *  	 
     *  	 @see NegativeFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BBEF097A-205B-E581-1BCD-93D5DBF79F8B]
    // </editor-fold> 
    public void negative() {

        filter = new NegativeFilter();
        image = image.filterImage(filter);

    }

    /**
     *  Creates a gray scale image
     *  	 
     *  	 @see GrayFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5A5F4188-3634-FC5D-3EAC-DA3E301FF3F5]
    // </editor-fold> 
    public void grayScale() {

        filter = new GrayFilter();
        image = image.filterImage(filter);

    }

    /**
     *  Rotates the image
     *  	 
     *  	 @param dir
     * @param angle
     * @param bgcol
     * @see ImageRotator
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C11DA7B5-FA1B-D7FD-2EC1-4448C9A199D2]
    // </editor-fold> 
    public void rotate(int dir, int angle, int bgcol) {

        filter = new ImageRotator(dir, angle, bgcol);
        image = image.filterImage(filter);

    }

    /**
     *  Flips the image
     *  	 
     *  	 @see ImageFlipper
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3BEEAFDC-163C-7DF4-9BCD-5A25B3EA449F]
    // </editor-fold> 
    public void flip() {

        filter = new ImageFlipper();
        image = image.filterImage(filter);

    }

    /**
     *  Mirrors the image
     *  	 
     *  	 @see ImageMirrorer
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E8F883F3-3C6F-F715-580B-136B67A31022]
    // </editor-fold> 
    public void mirror() {

        filter = new ImageMirrorer();
        image = image.filterImage(filter);

    }

    /**
     *  Translates the image to given direction
     *  	 
     *  	 @param dir 
     * @param amount 
     * @param col
     * @see ImageTranslator
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F4DF2A1E-9EA0-E907-63A5-A004F3F91165]
    // </editor-fold> 
    public void translate(int dir, int amount, int col) {

        filter = new ImageTranslator(dir, amount, col);
        image = image.filterImage(filter);

    }

    /**
     *  Translates the image to given direction and overflows to opposite side.
     *  	 
     *  	 @param dir
     * @param amount
     * @see ImageTranslator
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.423DF41E-99E1-B00D-78FF-8AC45CCC5F72]
    // </editor-fold> 
    public void translate(int dir, int amount) {

        filter = new ImageTranslator(dir, amount);
        image = image.filterImage(filter);

    }

    /**
     *  Square mosaic
     *  	 
     *  	 @param width 
     * @param height
     * @see MosaicFilter#MosaicFilter(int, int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CEC264AB-9BD4-9E55-843E-507D9FE05DD8]
    // </editor-fold> 
    public void mosaic(int width, int height) {

        filter = new MosaicFilter(width, height);
        image = image.filterImage(filter);

    }

    /**
     *  Custom mosaic
     *  	 
     *  	 @param width
     * @param height
     * @param filt
     * @see MosaicFilter#MosaicFilter(int, int, int[])
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A9D511B6-8978-C693-7488-A38E4754A692]
    // </editor-fold> 
    public void mosaic(int width, int height, int[] filt) {

        filter = new MosaicFilter(width, height, filt);
        image = image.filterImage(filter);

    }

    /**
     *  Rectangular custom mosaic
     *  	 
     *  	 @param width 
     * @param filt
     * @param height
     * @param am
     * @see MosaicFilter#MosaicFilter(int, int, int[], int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ADD4EFC5-0A6C-1137-A233-B45F516ECC00]
    // </editor-fold> 
    public void mosaic(int width, int height, int[] filt, int am) {

        filter = new MosaicFilter(width, height, filt, am);
        image = image.filterImage(filter);

    }

    /**
     *  Performs 33 median filtering
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.726AA421-8274-27E8-046F-B4F083898810]
    // </editor-fold> 
    public void median() {

        filter = new MedianFilter(MedianFilter.SIZE3X3, 5);
        image = image.filterImage(filter);

    }

    /**
     *  Erodes the image
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6D5C5924-63BA-99CD-C128-DF57284BCA61]
    // </editor-fold> 
    public void erode() {

        filter = new MedianFilter(MedianFilter.SIZE3X3, 2);
        image = image.filterImage(filter);

    }

    /**
     *  Dilates the image
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4D79212E-39A4-D3D8-9DEB-57622AE5F94C]
    // </editor-fold> 
    public void dilate() {

        filter = new MedianFilter(MedianFilter.SIZE3X3, 7);
        image = image.filterImage(filter);

    }

    /**
     *  Resamples the image
     *  	 
     *  	 @param w
     *  	            New width
     *  	 @param h
     *  	            new Height
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3FDB9E8F-64F0-A173-E7DD-B826338FAF89]
    // </editor-fold> 
    public void resample(int w, int h) {

        filter = new AreaAveragingScaleFilter(w, h);
        image = image.filterImage(filter);

    }

    /**
     *  Resizes the image
     *  	 
     *  	 @param w
     *  	            New width
     *  	 @param h
     *  	            New height
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C2F104B-B772-5E6D-52B9-59FBB4905A43]
    // </editor-fold> 
    public void resize(int w, int h) {

        filter = new ReplicateScaleFilter(w, h);
        image = image.filterImage(filter);

    }

    /**
     *  Performs arithmetic operations on color channels
     *  	 
     *  	 @param rop 
     * @param gop 
     * @param bop 
     * @param rarg
     * @param b
     * @param garg
     * @param barg
     * @see ColorChanger
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.69AFBB0D-78A8-9AB1-2D30-4DAB0EE262D5]
    // </editor-fold> 
    public void channelArithmetic(int rop, int gop, int bop, double rarg, double garg, double barg, boolean b) {

        filter = new ColorChanger(rop, gop, bop, rarg, garg, barg, b);
        image = image.filterImage(filter);

    }

    /**
     *  Adjusts the color channels by specified percentages
     *  	 
     *  	 @param a1
     * @param a2
     * @param a3
     * @see PercentageChanger
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6192FDA2-87D6-0A48-9DD1-2E9E1F0CF4C1]
    // </editor-fold> 
    public void adjust(int a1, int a2, int a3) {

        filter = new PercentageChanger(a1, a2, a3);
        image = image.filterImage(filter);

    }

    /**
     *  Sets a new color map
     *  	 
     *  	 @param data
     * @see ColorValueSetter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E1AD3F05-6A64-B274-D622-D95B1C4E8591]
    // </editor-fold> 
    public void setPixels(byte[] data) {

        filter = new ColorValueSetter(data);
        image = image.filterImage(filter);

    }

    /**
     *  Sets a new color map
     *  	 
     *  	 @param rdata 
     * @param gdata 
     * @param bdata
     * @see ColorValueSetter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5248C765-7CF4-7417-A4D0-E5AD6C78C4A5]
    // </editor-fold> 
    public void setPixels(byte[] rdata, byte[] gdata, byte[] bdata) {

        filter = new ColorValueSetter(rdata, gdata, bdata);
        image = image.filterImage(filter);

    }

    /**
     *  Performs histogram eqaualization
     *  	 
     *  	 @see HistogramEqualizer
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.468DC0E8-3112-80A2-2310-0CA80FF2F911]
    // </editor-fold> 
    public void equalize() {

        filter = new HistogramEqualizer();
        image = image.filterImage(filter);

    }

    /**
     *  Custom image filter.
     * @param f
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.694257F9-9A0E-4BE3-B13D-8B9BFD53F8C3]
    // </editor-fold> 
    public void customFilter(ImageFilter f) {

        image = image.filterImage(filter);

    }

    /**
     *  Returns the filtered image
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1725F4E7-033A-D7C1-15AD-B03F850FEAFC]
    // </editor-fold> 
    public Image getFilteredImage() {

        return Toolkit.getDefaultToolkit().createImage(image);

    }
} // End FilterOp