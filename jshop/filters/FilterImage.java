package jshop.filters;

import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;

/**
 *  Implementation of the <code>EasyFilter</code>. This class takes an
 *   <code>Image</code> object as an argument. This image can be filtered through
 *   any number of filters before returning it by the
 *   <code>getFilteredImage</code>-method.<br>
 *   Example of implementation:
 *   
 *   <pre>
 *         FilterImage fi = new FilterImage(myImage);
 *         fi.median()
 *         fi.blur(fi.LIGHT);
 *         fi.mosaic(3, 3);
 *         fi.emboss(128, fi.NW, 1);
 *         myImage = fi.getFilteredImage();
 *   </pre>
 *   
 *   @version 1.0 October 1. 1997
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.C76989C4-4A2E-31C7-9392-F4086BF1CB79]
// </editor-fold> 
public final class FilterImage implements EasyFilter {

    /**
     *  North light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B262188C-A67C-6FBF-E018-F5383F199C08]
    // </editor-fold> 
    public static final int N = 0;
    /**
     *  Northeast light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6D2AE65F-2DF6-7503-622E-EE7946DEDCC0]
    // </editor-fold> 
    public static final int NE = 1;
    /**
     *  East light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A4F1E181-D963-50B2-8D99-FBE2F2A1FC91]
    // </editor-fold> 
    public static final int E = 2;
    /**
     *  Southeast light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CC3953D7-6978-987B-A964-14BA0036A90E]
    // </editor-fold> 
    public static final int SE = 3;
    /**
     *  South light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C832F221-E18D-EDEC-4A9C-B984FA8224E0]
    // </editor-fold> 
    public static final int S = 4;
    /**
     *  Southwest light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BEB0BD8B-4F3B-21A6-1CAB-119AB1CC4864]
    // </editor-fold> 
    public static final int SW = 5;
    /**
     *  West light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7AE2DCE5-E13D-23C3-6D5F-3AF25836DD1B]
    // </editor-fold> 
    public static final int W = 6;
    /**
     *  Northwest light source (emboss)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7F77D6A4-4484-B7B3-3FDE-8FB5ADC14605]
    // </editor-fold> 
    public static final int NW = 7;
    /**
     *  Light filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1CF8A99E-4E95-E512-6570-2B37EFB5C1A1]
    // </editor-fold> 
    public static final int LIGHT = 0;
    /**
     *  Medium filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3574D9A6-65AA-CD04-2A5D-591A0A69CDD4]
    // </editor-fold> 
    public static final int MEDIUM = 1;
    /**
     *  Strong filter (blur and sharpen)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0AD1AAF2-8177-94D4-6E53-46D64FCF2688]
    // </editor-fold> 
    public static final int STRONG = 2;
    /**
     *  Gaussian averaging (blur)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0AB10F0E-7991-6536-3639-066D171D772C]
    // </editor-fold> 
    public static final int GAUSS = 4;
    /**
     *  All edges (edge detection)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2B0AA453-56C2-45FF-441A-CA4D30571FA7]
    // </editor-fold> 
    public static final int ALLEDGES = 0;
    /**
     *  Horizontal edges (edge detection)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3FEB6F6D-3F70-9F87-CA1E-8C5CBD5191EA]
    // </editor-fold> 
    public static final int HORIZONTAL = 1;
    /**
     *  Vertical edges (edge detection)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.99E66906-6D69-D4A3-2006-641C00AAA056]
    // </editor-fold> 
    public static final int VERTICAL = 2;
    /**
     *  Diagonal edges from top left to bottom right (edge detection)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BC41513C-AA28-CC79-DA24-C15434B799E1]
    // </editor-fold> 
    public static final int TOPLEFTDIAGONAL = 3;
    /**
     *  Diagonal edges from top right to bottom left (edge detection)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.73A3FD2C-019B-AB69-30D7-CC6CD109416D]
    // </editor-fold> 
    public static final int TOPRIGHTDIAGONAL = 4;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.49144BC3-4DAF-A579-9790-BDB47E2D7323]
    // </editor-fold> 
    private ImageProducer image;

    /**
     *  Creates a new FilterImage instance
     * @param image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.023FB33E-ABB1-80A4-CAEE-9F2313F326F6]
    // </editor-fold> 
    public FilterImage(Image image) {
        this.image = image.getSource();
    }

    /**
     *  Blurs the image
     *  	 
     *  	 @param strength
     *  	            Strength of the blurring filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.79129447-D173-ACCE-35CD-099A853C6662]
    // </editor-fold> 
    public void blur(int strength) {
        ImageDataCollector filter;

        if (strength == GAUSS) {
            int[] matrix = {1, 2, 1, 2, 4, 2, 1, 2, 1, 16};

            filter = new KernelFilter3x3(matrix);

        } else if (strength == LIGHT) {
            int[] matrix = {1, 1, 1, 1, 1, 1, 1, 1, 1, 9};

            filter = new KernelFilter3x3(matrix);

        } else if (strength == MEDIUM) {
            int[] matrix = {1, 2, 4, 2, 1, 2, 4, 8, 4, 2, 4, 8, 16, 8, 4, 2,
                4, 8, 4, 2, 1, 2, 4, 2, 1, 100};

            filter = new KernelFilter5x5(matrix);

        } else {
            int[] matrix = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 49};

            filter = new KernelFilter7x7(matrix);

        }

        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Sharpens the image
     *  	 
     *  	 @param strength
     *  	            Strength of the sharpening filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9763BB3D-48B4-8D45-EB43-CDE9331006D0]
    // </editor-fold> 
    public void sharpen(int strength) {
        ImageDataCollector filter;

        if (strength == LIGHT) {
            int[] matrix = {-1, -2, -1, -2, 24, -2, -1, -2, -1, 12};

            filter = new KernelFilter3x3(matrix);

        } else if (strength == MEDIUM) {
            int[] matrix = {-1, -2, -1, -2, 16, -2, -1, -2, -1, 4};

            filter = new KernelFilter3x3(matrix);

        } else {
            int[] matrix = {0, -1, -2, -1, 0, -1, -2, -4, -2, -1, -2, -4, 46,
                -4, -2, -1, -2, -4, -2, -1, 0, -1, -2, -1, 0, 6};

            filter = new KernelFilter5x5(matrix);

        }

        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Detects edges which are aligned with given direction
     *  	 
     *  	 @param direction
     * @see #detectEdges(int, int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2AF1998C-FD2C-D338-1D77-50C316505FB6]
    // </editor-fold> 
    public void detectEdges(int direction) {
        detectEdges(direction, 1);
    }

    /**
     *  Edge detection filter.
     *  	 
     *  	 @param direction
     *  	            Direction of the edges.
     *  	 @param strength
     *  	            Strength of the filter. Bigger strength means that more edges
     *  	            will be shown. Maximum strength is 10 which creates a very
     *  	            noisy image. Negative values may also be used to detect fewer
     *  	            edges.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C978BFC-430C-4B0D-0FCF-096B156CC679]
    // </editor-fold> 
    public void detectEdges(int direction, int strength) {
        ImageDataCollector filter;
        if (strength > 10) {
            strength = 10;
        }
        if (strength < -1000) {
            strength = -1000;
        }

        if (direction == HORIZONTAL) {
            int[] matrix = {-10, -10, -10, 20, 20, 20, -10, -10, -10,
                11 - strength};

            filter = new KernelFilter3x3(matrix);
        } else if (direction == VERTICAL) {
            int[] matrix = {-10, 20, -10, -10, 20, -10, -10, 20, -10,
                11 - strength};

            filter = new KernelFilter3x3(matrix);
        } else if (direction == TOPLEFTDIAGONAL) {
            int[] matrix = {20, -10, -10, -10, 20, -10, -10, -10, 20,
                11 - strength};

            filter = new KernelFilter3x3(matrix);
        } else if (direction == TOPRIGHTDIAGONAL) {
            int[] matrix = {-10, -10, 20, -10, 20, -10, 20, -10, -10,
                11 - strength};

            filter = new KernelFilter3x3(matrix);
        } else {
            int[] matrix = {-10, -10, -10, -10, 80, -10, -10, -10, -10,
                11 - strength};

            filter = new KernelFilter3x3(matrix);
        }
        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Embosses the image with grayscale background
     *  	 
     *  	 @param bg
     *  	            Background color. Applied to all channels (0-255)
     *  	 @param dir
     *  	            Direction of the light source
     *  	 @param str
     *  	            Emboss strength
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.13766526-8AC1-93A4-C7BF-A29553416933]
    // </editor-fold> 
    public void emboss(int bg, int dir, int str) {
        ImageDataCollector filter;

        if (dir == SE) {
            int[] dl = {str, 0, 0, 0, 0, 0, 0, 0, -str, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == NW) {
            int[] dl = {-str, 0, 0, 0, 0, 0, 0, 0, str, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == SW) {
            int[] dl = {0, 0, str, 0, 0, 0, -str, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == NE) {
            int[] dl = {0, 0, -str, 0, 0, 0, str, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == N) {
            int[] dl = {0, -str, 0, 0, 0, 0, 0, str, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == W) {
            int[] dl = {0, 0, 0, -str, 0, str, 0, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else if (dir == S) {
            int[] dl = {0, str, 0, 0, 0, 0, 0, -str, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        } else {
            int[] dl = {0, 0, 0, str, 0, -str, 0, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bg);
        }

        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Embosses the image
     *  	 
     *  	 @param bgr
     *  	            Amount of red in background
     *  	 @param bgg
     *  	            Amount of green in background
     *  	 @param bgb
     *  	            Amount of blue in background
     *  	 @param dir
     *  	            Direction of light source
     *  	 @param str
     *  	            Strength
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.42BA95C7-EA50-8153-9F25-EF662676A008]
    // </editor-fold> 
    public void emboss(int bgr, int bgg, int bgb, int dir, int str) {
        ImageDataCollector filter;

        if (dir == NW) {
            int[] dl = {-str, 0, 0, 0, 0, 0, 0, 0, str, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == SE) {
            int[] dl = {str, 0, 0, 0, 0, 0, 0, 0, -str, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == NE) {
            int[] dl = {0, 0, -str, 0, 0, 0, str, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == SW) {
            int[] dl = {0, 0, str, 0, 0, 0, -str, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == N) {
            int[] dl = {0, -str, 0, 0, 0, 0, 0, str, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == W) {
            int[] dl = {0, 0, 0, -str, 0, str, 0, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else if (dir == S) {
            int[] dl = {0, str, 0, 0, 0, 0, 0, -str, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        } else {
            int[] dl = {0, 0, 0, str, 0, -str, 0, 0, 0, 1};
            filter = new KernelFilter3x3(dl, bgr, bgg, bgb);
        }

        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Gammacorrects the image
     *  	 
     *  	 @param value
     * @see GammaCorrectionFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0E04927E-E0FA-BAD2-BB32-F67A43AD729F]
    // </editor-fold> 
    public void gammaCorrect(double value) {
        image = new FilteredImageSource(image, new GammaCorrectionFilter(value));
    }

    /**
     *  Splits channel 1 from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7356DD2C-DA84-3CB7-EEF4-8EBDB1FDBD54]
    // </editor-fold> 
    public void split1() {
        image = new FilteredImageSource(image, new ChannelSplitter(Locks.CH1));
    }

    /**
     *  Splits channel 2 from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1DA9CD32-53AB-55D5-75F9-E215900A56A4]
    // </editor-fold> 
    public void split2() {
        image = new FilteredImageSource(image, new ChannelSplitter(Locks.CH2));
    }

    /**
     *  Splits channel 3 from the image
     *  	 
     *  	 @see ChannelSplitter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C6FE0DC0-E0BC-8055-394D-B8AB994A1AAB]
    // </editor-fold> 
    public void split3() {
        image = new FilteredImageSource(image, new ChannelSplitter(Locks.CH3));
    }

    /**
     *  Creates a negative of the image
     *  	 
     *  	 @see NegativeFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.64728B16-B8E6-1625-D945-397A04C11D81]
    // </editor-fold> 
    public void negative() {
        image = new FilteredImageSource(image, new NegativeFilter());
    }

    /**
     *  Creates a gray scale image
     *  	 
     *  	 @see GrayFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DCDDF131-83D9-8A47-45E9-4494A9C35FC4]
    // </editor-fold> 
    public void grayScale() {
        image = new FilteredImageSource(image, new GrayFilter());
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
    // #[regen=yes,id=DCE.887105F4-C8C3-ED48-7D53-AA6E2D2AC30C]
    // </editor-fold> 
    public void rotate(int dir, int angle, int bgcol) {
        image = new FilteredImageSource(image, new ImageRotator(dir, angle,
                bgcol));
    }

    /**
     *  Flips the image
     *  	 
     *  	 @see ImageFlipper
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.11848F5D-6D22-D71A-3E36-3D7C96AB7ACF]
    // </editor-fold> 
    public void flip() {
        image = new FilteredImageSource(image, new ImageFlipper());
    }

    /**
     *  Mirrors the image
     *  	 
     *  	 @see ImageMirrorer
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.379170FC-DA8F-F2E8-43F1-6B23246CFFCE]
    // </editor-fold> 
    public void mirror() {
        image = new FilteredImageSource(image, new ImageMirrorer());
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
    // #[regen=yes,id=DCE.319764F2-B8A1-DAC1-C8D8-0D5FAB4C9C27]
    // </editor-fold> 
    public void translate(int dir, int amount, int col) {
        image = new FilteredImageSource(image, new ImageTranslator(dir, amount,
                col));
    }

    /**
     *  Translates the image to given direction and overflows to opposite side.
     *  	 
     *  	 @param dir
     * @param amount
     * @see ImageTranslator
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5E48B73A-81B4-BE1A-2E16-1F0248057FFD]
    // </editor-fold> 
    public void translate(int dir, int amount) {
        image = new FilteredImageSource(image, new ImageTranslator(dir, amount));
    }

    /**
     *  Square mosaic
     *  	 
     *  	 @param width 
     * @param height
     * @see MosaicFilter#MosaicFilter(int, int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2D92F540-FE6D-75C6-F80A-FF6B04BC5813]
    // </editor-fold> 
    public void mosaic(int width, int height) {
        image = new FilteredImageSource(image, new MosaicFilter(width, height));
    }

    /**
     *  Custom mosaic
     *  	 
     *  	 @param width 
     * @param height
     * @param filter
     * @see MosaicFilter#MosaicFilter(int, int, int[])
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2C275C99-5D07-C0EF-21CF-A4CF2039FF06]
    // </editor-fold> 
    public void mosaic(int width, int height, int[] filter) {
        image = new FilteredImageSource(image, new MosaicFilter(width, height,
                filter));
    }

    /**
     *  Rectangular custom mosaic
     *  	 
     *  	 @param width
     * @param height
     * @param filter
     * @param am
     * @see MosaicFilter#MosaicFilter(int, int, int[], int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EDBB8ADB-C03B-A61C-ECF8-E518E9738A1E]
    // </editor-fold> 
    public void mosaic(int width, int height, int[] filter, int am) {
        image = new FilteredImageSource(image, new MosaicFilter(width, height,
                filter, am));
    }

    /**
     *  Creates an image which looks like watercolored painting
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6B40EA31-F3BF-2E69-3C54-A86824AA6AB7]
    // </editor-fold> 
    public void waterColor() {
        ImageDataCollector filter = new MedianFilter(MedianFilter.SIZE5X5, 13);
        image = new FilteredImageSource(image, filter);
        int[] matrix = {-1, -2, -1, -2, 15, -2, -1, -2, -1, 3};
        filter = new KernelFilter3x3(matrix);
        image = new FilteredImageSource(image, filter);
    }

    /**
     *  Runs the image through a median filter
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8ADD11B4-9949-9D51-D5FF-44B1E08856D9]
    // </editor-fold> 
    public void median() {
        image = new FilteredImageSource(image, new MedianFilter(
                MedianFilter.SIZE3X3, 5));
    }

    /**
     *  Erodes the image
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E8E6B801-5E50-7FCF-DF23-59AC1BD6FF6D]
    // </editor-fold> 
    public void erode() {
        image = new FilteredImageSource(image, new MedianFilter(
                MedianFilter.SIZE3X3, 7));
    }

    /**
     *  Dilates the image
     *  	 
     *  	 @see MedianFilter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D6281C42-9AA6-28FC-C4E9-192B16D2F652]
    // </editor-fold> 
    public void dilate() {
        image = new FilteredImageSource(image, new MedianFilter(
                MedianFilter.SIZE3X3, 2));
    }

    /**
     * Gets the filtered image
     *
     * @return the image
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5452A6E3-F73B-23B3-DEAA-9BF248E8F68E]
    // </editor-fold> 
    public Image getFilteredImage() {
        return Toolkit.getDefaultToolkit().createImage(image);
    }
}
