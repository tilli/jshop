package jshop.filters;

/**
 *  Subclass of ImageDataCollector which passes each pixel in the image through
 *   an filter of size 3x3 pixels.<br>
 *   The kernel is an array of 10 ints, First nine ints are pixel multipliers and
 *   the last one is their divider.<br>
 *   Example:
 *   
 *   <pre>
 *   int[] kernel = { 1, 2, 1, 2, 4, 2, 1, 2, 1, 16 };
 *   </pre>
 *   
 *   would run the following filter on each pixel in the image:
 *   
 *   <pre>
 *       (1/16, 1/8, 1/16)
 *       ( 1/8, 1/4, 1/8 )
 *       (1/16, 1/8, 1/16)
 *   </pre>
 *   
 *   <br>
 *   
 *   @see KernelFilter5x5
 *   @see KernelFilter7x7
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.88D6DFCB-D55B-2C9A-BCC3-081A4E29A6CA]
// </editor-fold> 
public final class KernelFilter3x3 extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7B039DE5-9308-008C-E250-9603011D80D0]
    // </editor-fold> 
    private static final long serialVersionUID = -1252789542087328019L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F4A8A988-D52A-D4B5-8384-9E4DEAE4A641]
    // </editor-fold> 
    private int[] kernel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.88447B98-1678-9173-C5A8-9DEB1F05EDAC]
    // </editor-fold> 
    private int roffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F1C94AB9-3464-1A6D-66D7-B095AEC5715A]
    // </editor-fold> 
    private int goffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CECB5C85-78ED-D768-D76A-0172A9AE9F27]
    // </editor-fold> 
    private int boffset;

    /**
     *  Creates a 3x3 kernel filter
     * @param kernel
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.67C3C065-E340-E2FA-A533-F00B4CDC3E62]
    // </editor-fold> 
    public KernelFilter3x3(int[] kernel) {

        this(kernel, 0, 0, 0);

    }

    /**
     *  Creates a 3x3 kernel filter which adds given amount to each channel
     * @param kernel 
     * @param bg
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77439628-BC43-7587-90F3-E770FD96C03E]
    // </editor-fold> 
    public KernelFilter3x3(int[] kernel, int bg) {

        this(kernel, bg, bg, bg);

    }

    /**
     *  Constructs a 3x3 kernel filter which adds given amounts to channels.
     *  	 
     *  	 @param kernel
     * @param r
     *  	            Amount added to channel 1
     *  	 @param g
     *  	            Amount added to channel 2
     *  	 @param b
     *  	            Amount added to channel 3
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3B130A1D-EC3E-BB21-AB44-E7C62D5D34C3]
    // </editor-fold> 
    public KernelFilter3x3(int[] kernel, int r, int g, int b) {

        this.kernel = kernel;
        roffset = r > 255 ? 255 : r < 0 ? 0 : r;
        goffset = g > 255 ? 255 : g < 0 ? 0 : g;
        boffset = b > 255 ? 255 : b < 0 ? 0 : b;

    }

    /**
     * Filters the pixel data and passes it to the consumer
     *
     * @param width
     * @param height 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.43D6CBA5-8494-604E-0ECD-64B67F8FDE57]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int[] row = new int[width];
        int[] ind = new int[9];
        int red, gr, bl;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                red = bl = gr = 0;

                ind[4] = x + y * width;
                ind[3] = x == 0 ? ind[4] : x + y * width - 1;
                ind[1] = y == 0 ? ind[4] : x + (y - 1) * width;
                ind[5] = x == width - 1 ? ind[4] : x + y * width + 1;
                ind[7] = y == height - 1 ? ind[4] : x + (y + 1) * width;
                ind[0] = x == 0 ? ind[1] : y == 0 ? ind[3] : x + (y - 1) * width - 1;
                ind[2] = x == width - 1 ? ind[1] : y == 0 ? ind[5] : x + (y - 1) * width + 1;
                ind[6] = x == 0 ? ind[7] : y == height - 1 ? ind[3] : x + (y + 1) * width - 1;
                ind[8] = x == width - 1 ? ind[7] : y == height - 1 ? ind[5] : x + (y + 1) * width + 1;

                if (!getLock(CH1)) {
                    for (int i = 0; i < 9; i++) {
                        red += ((pix[ind[i]] >> 16) & 0xff) * kernel[i];
                    }
                    red = red / kernel[9] + roffset;
                } else {
                    red = (pix[ind[4]] >> 16) & 0xff;
                }
                if (!getLock(CH2)) {
                    for (int i = 0; i < 9; i++) {
                        gr += ((pix[ind[i]] >> 8) & 0xff) * kernel[i];
                    }
                    gr = gr / kernel[9] + goffset;
                } else {
                    gr = (pix[ind[4]] >> 8) & 0xff;
                }
                if (!getLock(CH3)) {
                    for (int i = 0; i < 9; i++) {
                        bl += ((pix[ind[i]]) & 0xff) * kernel[i];
                    }
                    bl = bl / kernel[9] + boffset;
                } else {
                    bl = pix[ind[4]] & 0xff;
                }

                row[x] = (255 << 24) | (((red > 255) ? 255 : (red < 0) ? 0 : red) << 16) | (((gr > 255) ? 255 : (gr < 0) ? 0 : gr) << 8) | ((bl > 255) ? 255 : (bl < 0) ? 0 : bl);

            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }
} // End KernelFilter3x3