package jshop.filters;

/**
 *  Similar to KernelFilter3x3 except that the filter size is 5x5. The array must
 *   contain 26 integers
 *   
 *   @see KernelFilter3x3
 *   @see KernelFilter7x7
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.DF3A50C7-C5CC-CB48-5E98-85CD69A89B81]
// </editor-fold> 
public final class KernelFilter5x5 extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.23A74DE0-5F87-76B8-2822-62D2FA4E187F]
    // </editor-fold> 
    private static final long serialVersionUID = 6045840511418098506L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C0F4ED67-E724-383A-FB75-C155B31CC868]
    // </editor-fold> 
    private int[] kernel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.66A7EA4D-B126-E657-8949-4CB13F49AF2E]
    // </editor-fold> 
    private int roffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D3E15254-0B83-AD6E-05AE-728970E88E63]
    // </editor-fold> 
    private int goffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A5A78CEF-10D1-55F8-762F-B6EAFB467C4F]
    // </editor-fold> 
    private int boffset;

    /**
     *  Creates a 5x5 kernel filter
     * @param kernel 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C868B677-893B-1DC7-6CAC-98D84D149656]
    // </editor-fold> 
    public KernelFilter5x5(int[] kernel) {

        this(kernel, 0, 0, 0);

    }

    /**
     *  Creates a 5x5 kernel filter which adds given amount to each channel
     * @param kernel 
     * @param bg
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.12FFF75A-CF82-2A7F-CA84-F4948FDE6692]
    // </editor-fold> 
    public KernelFilter5x5(int[] kernel, int bg) {

        this(kernel, bg, bg, bg);

    }

    /**
     *  Creates a 5x5 kernel filter which adds given amounts to channels.
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
    // #[regen=yes,id=DCE.881FE5BD-2B1C-BF3C-8AAE-D08B71ABD573]
    // </editor-fold> 
    public KernelFilter5x5(int[] kernel, int r, int g, int b) {

        this.kernel = kernel;
        roffset = r > 255 ? 255 : r < 0 ? 0 : r;
        goffset = g > 255 ? 255 : g < 0 ? 0 : g;
        boffset = b > 255 ? 255 : b < 0 ? 0 : b;

    }

    /**
     * Filters the pixel data and passes it to the consumer
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F842C874-1493-614D-4694-332E20A898C6]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int[] row = new int[width];
        int red, gr, bl;
        int[] ind = new int[25];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                red = gr = bl = 0;

                if (y == 0 || y == 1 || x == 1 || x == 0 || x == width - 1 || x == width - 2 || y == height - 1 || y == height - 2) {
                    ind[12] = x + y * width;
                    ind[11] = x == 0 ? ind[12] : x + y * width - 1;
                    ind[7] = y == 0 ? ind[12] : x + (y - 1) * width;
                    ind[13] = x == width - 1 ? ind[12] : x + y * width + 1;
                    ind[17] = y == height - 1 ? ind[12] : x + (y + 1) * width;
                    ind[6] = x == 0 ? ind[7] : y == 0 ? ind[11] : x + (y - 1) * width - 1;
                    ind[8] = x == width - 1 ? ind[7] : y == 0 ? ind[13] : x + (y - 1) * width + 1;
                    ind[16] = x == 0 ? ind[17] : y == height - 1 ? ind[11] : x + (y + 1) * width - 1;
                    ind[18] = x == width - 1 ? ind[17]
                            : y == height - 1 ? ind[13] : x + (y + 1) * width + 1;
                    ind[2] = y == 0 ? ind[12] : y == 1 ? ind[7] : x + (y - 2) * width;
                    ind[10] = x == 0 ? ind[12] : x == 1 ? ind[11] : x + y * width - 2;
                    ind[14] = x == width - 1 ? ind[12]
                            : x == width - 2 ? ind[13] : x + y * width + 2;
                    ind[22] = y == height - 1 ? ind[12]
                            : y == height - 2 ? ind[17] : x + (y + 2) * width;
                    ind[21] = y == height - 1 ? ind[11]
                            : y == height - 2 ? ind[16] : x == 0 ? ind[22] : x + (y + 2) * width - 1;
                    ind[23] = y == height - 1 ? ind[13]
                            : y == height - 2 ? ind[18]
                            : x == width - 1 ? ind[22] : x + (y + 2) * width + 1;
                    ind[1] = y == 0 ? ind[11] : y == 1 ? ind[16]
                            : x == 0 ? ind[2] : x + (y - 2) * width - 1;
                    ind[3] = y == 0 ? ind[13] : y == 1 ? ind[8]
                            : x == width - 1 ? ind[2] : x + (y - 2) * width + 1;
                    ind[5] = x == 0 ? ind[7] : x == 1 ? ind[6]
                            : y == 0 ? ind[10] : x + (y - 1) * width - 2;
                    ind[15] = x == 0 ? ind[17] : x == 1 ? ind[16]
                            : y == height - 1 ? ind[10] : x + (y + 1) * width - 2;
                    ind[9] = x == width - 1 ? ind[7] : x == width - 2 ? ind[8]
                            : y == 0 ? ind[14] : x + (y - 1) * width + 2;
                    ind[19] = x == width - 1 ? ind[17]
                            : x == width - 2 ? ind[18]
                            : y == height - 1 ? ind[14] : x + (y + 1) * width + 2;
                    ind[0] = x == 0 ? ind[2] : x == 1 ? ind[1]
                            : y == 0 ? ind[10] : y == 1 ? ind[5] : x + (y - 2) * width - 2;
                    ind[4] = x == width - 1 ? ind[2] : x == width - 2 ? ind[3]
                            : y == 0 ? ind[14] : y == 1 ? ind[9] : x + (y - 2) * width + 2;
                    ind[20] = y == height - 1 ? ind[10]
                            : y == height - 2 ? ind[15] : x == 0 ? ind[22]
                            : x == 1 ? ind[21] : x + (y + 2) * width - 2;
                    ind[24] = y == height - 1 ? ind[14]
                            : y == height - 2 ? ind[19]
                            : x == width - 1 ? ind[22]
                            : x == width - 2 ? ind[23] : x + (y + 2) * width + 2;
                } else {

                    ind[0] = x + (y - 2) * width - 2;
                    ind[1] = x + (y - 2) * width - 1;
                    ind[2] = x + (y - 2) * width;
                    ind[3] = x + (y - 2) * width + 1;
                    ind[4] = x + (y - 1) * width + 2;
                    ind[5] = x + (y - 1) * width - 2;
                    ind[6] = x + (y - 1) * width - 1;
                    ind[7] = x + (y - 1) * width;
                    ind[8] = x + (y - 1) * width + 1;
                    ind[9] = x + (y - 1) * width + 2;
                    ind[10] = x + y * width - 2;
                    ind[11] = x + y * width - 1;
                    ind[12] = x + y * width;
                    ind[13] = x + y * width + 1;
                    ind[14] = x + y * width + 2;
                    ind[15] = x + (y + 1) * width - 2;
                    ind[16] = x + (y + 1) * width - 1;
                    ind[17] = x + (y + 1) * width;
                    ind[18] = x + (y + 1) * width + 1;
                    ind[19] = x + (y + 1) * width + 2;
                    ind[20] = x + (y + 2) * width - 2;
                    ind[21] = x + (y + 2) * width - 1;
                    ind[22] = x + (y + 2) * width;
                    ind[23] = x + (y + 2) * width + 1;
                    ind[24] = x + (y + 2) * width + 2;
                }

                if (!getLock(CH1)) {
                    for (int i = 0; i < 25; i++) {
                        red += ((pix[ind[i]] >> 16) & 0xff) * kernel[i];
                    }
                    red /= kernel[25] + roffset;
                } else {
                    red = (pix[ind[12]] >> 16) & 0xff;
                }
                if (!getLock(CH2)) {
                    for (int i = 0; i < 25; i++) {
                        gr += ((pix[ind[i]] >> 8) & 0xff) * kernel[i];
                    }
                    gr /= kernel[25] + goffset;
                } else {
                    gr = (pix[ind[12]] >> 8) & 0xff;
                }
                if (!getLock(CH3)) {
                    for (int i = 0; i < 25; i++) {
                        bl += ((pix[ind[i]]) & 0xff) * kernel[i];
                    }
                    bl /= kernel[25] + boffset;
                } else {
                    bl = pix[ind[12]] & 0xff;
                }

                row[x] = (255 << 24) | (((red > 255) ? 255 : (red < 0) ? 0 : red) << 16) | (((gr > 255) ? 255 : (gr < 0) ? 0 : gr) << 8) | ((bl > 255) ? 255 : (bl < 0) ? 0 : bl);

            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }
} // End KernelFilter5x5