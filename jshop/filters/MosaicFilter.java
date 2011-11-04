package jshop.filters;

/**
 *  Class, which handles mosaic-effects. <br>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.0CFCEFD4-0F35-F156-253D-7B13F2C97A08]
// </editor-fold> 
public final class MosaicFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1CC00ACF-6B28-6E56-503C-D78493F9AE2D]
    // </editor-fold> 
    private static final long serialVersionUID = -5923957901714632716L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E0ECDF08-BEAE-EEB2-588F-1C687225983D]
    // </editor-fold> 
    private int w;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.71084DF1-2772-31FA-4E6E-A2C2A5B486B6]
    // </editor-fold> 
    private int h;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C3B5BFF2-E5D9-6C2E-850B-C1E81BDD8661]
    // </editor-fold> 
    private int am;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E280F38B-C2D5-9176-D8E4-038EDDBCAE12]
    // </editor-fold> 
    private int[] filter;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.592DF9C4-98DB-7C48-F39A-4CBA63BCF043]
    // </editor-fold> 
    private int style = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FB4DA726-7667-78AF-D20C-0011BA66BC05]
    // </editor-fold> 
    private boolean corner = true;
    /**
     *  Custom mosaic
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.918009CE-B8B1-BDB8-280F-1D08551149AF]
    // </editor-fold> 
    public static final int CUSTOM = 0;
    /**
     *  Customized rectangle
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C00F4A45-65C7-D1D7-6445-6A2819889B97]
    // </editor-fold> 
    public static final int CUSTOM_RECTANGLE = 1;
    /**
     *  Rectangular
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BFCDEE27-804E-33BB-6CEE-58E7F5E44CF2]
    // </editor-fold> 
    public static final int RECTANGLE = 2;

    /**
     *  Creates a new rectangular MosaicFilter
     * @param w
     * @param h 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.260DFB1F-6B91-7E03-2B31-4FF9734214DB]
    // </editor-fold> 
    public MosaicFilter(int w, int h) {

        this.w = w;
        this.h = h;
        style = RECTANGLE;

    }

    /**
     *  Creates a rectangular but not uniformly colored mosaic filter
     *  	 
     *  	 @param w
     *  	            Width of one square
     *  	 @param h
     *  	            Height of one square
     *  	 @param filter
     *  	            Array of size wh consisting integers. Instances of same int in
     *  	            the array are used to calculate the shape of the mosaic.
     *  	            Example:
     *  	 
     *  	            <pre>
     *  	             w = 8;
     *  	             h = 6;
     *  	             filter = { 1, 2, 2, 2, 2, 2, 2, 1,
     *  	                        2, 1, 2, 2, 2, 2, 1, 2,
     *  	                        2, 2, 1, 3, 3, 1, 2, 2,
     *  	                        2, 2, 1, 3, 3, 1, 2, 2,
     *  	                        2, 1, 2, 2, 2, 2, 1, 2,
     *  	                        1, 2, 2, 2, 2, 2, 2, 1 };
     *  	             am = 3;
     *  	 </pre>
     *  	 
     *  	            This would make a mosaic with 22 square in the middle and
     *  	            lines coming from the corners toward the center. (See image at
     *  	            top)<br>
     *  	            The image is handled in squares starting from topleft corner.
     *  	            All instances of 1 get the average color of pixels they cover,<br>
     *  	            all instances of 2 get the average of their coverage and so
     *  	            on.<br>
     *  	            Instances of 0 do not alter the image.
     *  	 @param am
     *  	            Number of different integers in the filter-array.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8567F94A-6FC1-97DE-73AB-265137AFD453]
    // </editor-fold> 
    public MosaicFilter(int w, int h, int[] filter, int am) {

        this.w = w;
        this.h = h;
        this.filter = filter;
        this.am = am;
        style = CUSTOM_RECTANGLE;

    }

    /**
     *  Creates a custom shaped mosaic filter. This filter takes a similar array
     *  	 as the customized rectangle mosaic. This array is spread over the whole
     *  	 image and the mosaic areas are recursively calculated. Example:<br>
     *  	 
     *  	 <pre>
     *  	             w = 8;
     *  	             h = 6;
     *  	             filter = { 1, 2, 2, 2, 2, 2, 2, 1,
     *  	                        2, 1, 2, 2, 2, 2, 1, 2,
     *  	                        2, 2, 1, 3, 3, 1, 2, 2,
     *  	                        2, 2, 1, 3, 3, 1, 2, 2,
     *  	                        2, 1, 2, 2, 2, 2, 1, 2,
     *  	                        1, 2, 2, 2, 2, 2, 2, 1 };
     *  	             
     *  	             This filter would spread out to:
     *  	             122222211222222112222221...
     *  	             212222122122221221222212...
     *  	             221331222213312222133122...
     *  	             221331222213312222133122...
     *  	             212222122122221221222212...
     *  	             122222211222222112222221...
     *  	             122222211222222112222221...
     *  	             212222122122221221222212...
     *  	             221331222213312222133122...
     *  	             221331222213312222133122...
     *  	             212222122122221221222212...
     *  	             122222211222222112222221...
     *  	             ...
     *  	 </pre>
     *  	 
     *  	 See image at top.
     *  	 
     *  	 @param w
     *  	            Width of one square
     *  	 @param h
     *  	            Height of one square
     * @param filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A9322710-16A4-BCFB-B5AF-5C89596C6852]
    // </editor-fold> 
    public MosaicFilter(int w, int h, int[] filter) {

        this.w = w;
        this.h = h;
        this.filter = filter;
        style = CUSTOM;

    }

    /**
     * Gets the properties
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.22473713-21FD-032F-A4EE-B8278BCE30D7]
    // </editor-fold> 
    protected String getProperties() {

        switch (style) {
            case CUSTOM:
                return "Style: Customized, Size = " + w + "*" + h + ", Cornerflow = " + corner;
            case CUSTOM_RECTANGLE:
                return "Style: Customized rectangle, Size = " + w + "*" + h;
            default:
                return "Style: Rectangular, Size = " + w + "*" + h;
        }

    }

    /**
     * Filters the data
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.80092A7A-B2C4-54E4-2411-102BA98C5DD7]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        if (style == CUSTOM) {
            customMosaic(w, h, filter, pix, width, height);
        } else if (style == CUSTOM_RECTANGLE) {
            customRectangle(w, h, filter, am, pix, width, height);
        } else {
            rectangularMosaic(w, h, pix, width, height);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A38D02B2-CFE3-2EB0-105E-9A844180ED34]
    // </editor-fold> 
    private void rectangularMosaic(int w, int h, int[] pix, int width, int height) {

        int red, gr, blu, ind;
        for (int y = 0; y < height; y += h) {
            for (int x = 0; x < width; x += w) {

                red = gr = blu = 0;
                for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                    for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                        if (dx >= width) {
                            dx = width - 1;
                        }
                        if (dy >= height) {
                            dy = height - 1;
                        }
                        ind = dy * width + dx;
                        if (!getLock(CH1)) {
                            red += ((pix[ind] >> 16) & 0xff);
                        }
                        if (!getLock(CH2)) {
                            gr += ((pix[ind] >> 8) & 0xff);
                        }
                        if (!getLock(CH3)) {
                            blu += (pix[ind] & 0xff);
                        }
                    }
                }

                red = (red / (h * w)) & 0xff;
                gr = (gr / (h * w)) & 0xff;
                blu = (blu / (h * w)) & 0xff;

                for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                    for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                        if (dx >= width) // If we are horizontally over the
                        // edge, we start a new row
                        {
                            break;
                        }
                        if (dy >= height) { // If we are vertically over the
                            // edge, we kill the loops
                            sy = h;
                            break;
                        }
                        ind = dy * width + dx;
                        if (getLock(CH1)) {
                            red = (pix[ind] >> 16) & 0xff;
                        }
                        if (getLock(CH2)) {
                            gr = (pix[ind] >> 8) & 0xff;
                        }
                        if (getLock(CH3)) {
                            blu = pix[ind] & 0xff;
                        }

                        pix[ind] = (255 << 24) | (red << 16) | (gr << 8) | blu;
                    }
                }
            }
        }
        passToConsumer(0, 0, width, height, pix, 0, width);

    }

    /**
     *  Recursively adds pixel data together according to the given filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.833AD273-478F-11A4-F345-865DF1C56692]
    // </editor-fold> 
    private void calculateData(short[] data, int[] filter, int y, int x, int value, int[] values, int width, int height) {

        if (++values[3] > 16000 || data[y * width + x] < 0) {
            return;
        }
        values[0] += (filter[y * width + x] >> 16) & 0xff;
        values[1] += (filter[y * width + x] >> 8) & 0xff;
        values[2] += filter[y * width + x] & 0xff;
        data[y * width + x] = (short) (value - 256);
        // Checks if the pixel to the left has same filter value
        if (x > 0 && data[y * width + x - 1] == value) {
            calculateData(data, filter, y, x - 1, value, values, width, height);
        }
        // Topleft
        if (corner && x > 0 && y > 0 && data[(y - 1) * width + x - 1] == value) {
            calculateData(data, filter, y - 1, x - 1, value, values, width,
                    height);
        }
        // Bottomleft
        if (corner && x > 0 && y < height - 1 && data[(y + 1) * width + x - 1] == value) {
            calculateData(data, filter, y + 1, x - 1, value, values, width,
                    height);
        }
        // Pixel over the current
        if (y > 0 && data[(y - 1) * width + x] == value) {
            calculateData(data, filter, y - 1, x, value, values, width, height);
        }
        // Pixel under the current
        if (y < height - 1 && data[(y + 1) * width + x] == value) {
            calculateData(data, filter, y + 1, x, value, values, width, height);
        }
        // Topright
        if (corner && y > 0 && x < width - 1 && data[(y - 1) * width + x + 1] == value) {
            calculateData(data, filter, y - 1, x + 1, value, values, width,
                    height);
        }
        // Pixel to the right
        if (x < width - 1 && data[y * width + x + 1] == value) {
            calculateData(data, filter, y, x + 1, value, values, width, height);
        }
        // Bottomright
        if (corner && y < height - 1 && x < width - 1 && data[(y + 1) * width + x + 1] == value) {
            calculateData(data, filter, y + 1, x + 1, value, values, width,
                    height);
        }

    }

    /**
     *  Sets the calculated values to the pixel array
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.87076C8D-1E2B-C645-328B-F7A7F381C891]
    // </editor-fold> 
    private void setData(short[] data, int[] filter, int y, int x, int value, int[] values, int width, int height) {

        filter[y * width + x] = (filter[y * width + x] & 0xff000000) | (values[0] << 16) | (values[1] << 8) | values[2];
        data[y * width + x] = 0;
        if (x > 0 && data[y * width + x - 1] == value) {
            setData(data, filter, y, x - 1, value, values, width, height);
        }
        if (y > 0 && data[(y - 1) * width + x] == value) {
            setData(data, filter, y - 1, x, value, values, width, height);
        }
        if (x < width - 1 && data[y * width + x + 1] == value) {
            setData(data, filter, y, x + 1, value, values, width, height);
        }
        if (y < height - 1 && data[(y + 1) * width + x] == value) {
            setData(data, filter, y + 1, x, value, values, width, height);
        }
        if (!corner) {
            return;
        }
        if (x > 0 && y > 0 && data[(y - 1) * width + x - 1] == value) {
            setData(data, filter, y - 1, x - 1, value, values, width, height);
        }
        if (y > 0 && x < width - 1 && data[(y - 1) * width + x + 1] == value) {
            setData(data, filter, y - 1, x + 1, value, values, width, height);
        }
        if (x < width - 1 && y < height - 1 && data[(y + 1) * width + x + 1] == value) {
            setData(data, filter, y + 1, x + 1, value, values, width, height);
        }
        if (y < height - 1 && x > 0 && data[(y + 1) * width + x - 1] == value) {
            setData(data, filter, y + 1, x - 1, value, values, width, height);
        }

    }

    /**
     *  Custom
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2B3A7368-7149-3C9D-2675-DCAD9025AAEE]
    // </editor-fold> 
    private void customMosaic(int w, int h, int[] filter, int[] pix, int width, int height) {

        short data[] = new short[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int cy = y % h;
                int cx = x % w;
                data[y * width + x] = (short) filter[cy * w + cx];
            }
        }

        int values[] = new int[4];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (data[y * width + x] != 0) {
                    values[0] = values[1] = values[2] = values[3] = 0;
                    calculateData(data, pix, y, x, data[y * width + x], values,
                            width, height);
                    values[0] /= values[3];
                    values[1] /= values[3];
                    values[2] /= values[3];
                    setData(data, pix, y, x, data[y * width + x], values,
                            width, height);
                }
            }
        }
        passToConsumer(0, 0, width, height, pix, 0, width);

    }

    /**
     *  Custom rectangle
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3AAD26B6-F59B-51F6-4009-8A348D64F160]
    // </editor-fold> 
    private void customRectangle(int w, int h, int[] filter, int am, int[] pix, int width, int height) {

        int[] counter = new int[am];

        // Counts the instances of each int in the filter
        for (int i = 0; i < w * h; i++) {
            for (int j = 0; j < am; j++) {
                if (filter[i] == j) {
                    counter[j]++;
                }
            }
        }

        // If there is no instances of some int, counter of that int
        // must be replaced by 1 or we'll end up with dividion by zero
        for (int i = 0; i < am; i++) {
            if (counter[i] == 0) {
                counter[i] = 1;
            }
        }

        int val, filterind, ind;
        int[] red = new int[am];
        int[] gr = new int[am];
        int[] blu = new int[am];
        int[] square = new int[w * h];

        // Handles the image in squares of size w*h
        for (int y = 0; y < height; y += h) {
            for (int x = 0; x < width; x += w) {

                // Counts the color values for the current square
                for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                    for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                        filterind = sy * w + sx;
                        val = filter[filterind];
                        if (dx >= width) {
                            dx = width - 1;
                        }
                        if (dy >= height) {
                            dy = height - 1;
                        }
                        ind = dy * width + dx;
                        for (int i = 0; i < am; i++) {
                            if (!getLock(CH1) && val == i) {
                                red[i] += ((pix[ind] >> 16) & 0xff);
                            }
                            if (!getLock(CH2) && val == i) {
                                gr[i] += ((pix[ind] >> 8) & 0xff);
                            }
                            if (!getLock(CH3) && val == i) {
                                blu[i] += (pix[ind] & 0xff);
                            }
                        }

                    }
                }

                for (int i = 0; i < am; i++) {
                    red[i] /= counter[i];
                    gr[i] /= counter[i];
                    blu[i] /= counter[i];

                }

                for (int dy = y, sy = 0; sy < h; dy++, sy++) {
                    for (int dx = x, sx = 0; sx < w; dx++, sx++) {
                        filterind = sy * w + sx;
                        val = filter[filterind];
                        if (dx >= width) {
                            dx = width - 1;
                        }
                        if (dy >= height) {
                            dy = height - 1;
                        }
                        ind = dy * width + dx;
                        if (getLock(CH1)) {
                            for (int i = 0; i < am; i++) {
                                red[i] = (pix[ind] >> 16) & 0xff;
                            }
                        }
                        if (getLock(CH2)) {
                            for (int i = 0; i < am; i++) {
                                gr[i] = (pix[ind] >> 8) & 0xff;
                            }
                        }
                        if (getLock(CH3)) {
                            for (int i = 0; i < am; i++) {
                                blu[i] = pix[ind] & 0xff;
                            }
                        }

                        for (int i = 0; i < am; i++) {
                            if (val == i) {
                                square[sy * w + sx] = (255 << 24) | (red[i] << 16) | (gr[i] << 8) | blu[i];
                            }
                        }
                    }
                }

                for (int i = 0; i < am; i++) {
                    red[i] = gr[i] = blu[i] = 0;
                }

                passToConsumer(x, y, w, h, square, 0, w);
            }
        }

    }

    /**
     *  Sets the corner flow true or false. By default it is true. If cornerflow
     *  	 is true the corners of the pixel are checked also when locating
     *  	 neighbouring pixels in custom mosaic.
     * @param b
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.28F095F6-4340-64AB-12B1-EAF24C2F2442]
    // </editor-fold> 
    public void setCornerFlow(boolean b) {

        corner = b;

    }
} // End MosaicFilter