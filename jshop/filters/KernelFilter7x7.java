package jshop.filters;

/**
 *  7x7 image filter. The array must contain 50 integers
 *   
 *   @see KernelFilter3x3
 *   @see KernelFilter5x5
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.79B5E42B-EA07-1B9B-C9C1-66152EFA9F6F]
// </editor-fold> 
public final class KernelFilter7x7 extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6FD54FE7-9F0D-786E-5D86-FCF30145497F]
    // </editor-fold> 
    private static final long serialVersionUID = -1451356024267709301L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B6AC91C6-9C44-86BE-E7BD-EB746D2F0F89]
    // </editor-fold> 
    private int[] kernel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E2DC7A0D-8FA3-124C-B1D2-6E0ABEE4BA7F]
    // </editor-fold> 
    private int roffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ACB555F2-77E9-1B25-0304-D97E56BDE0AB]
    // </editor-fold> 
    private int goffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.53B7611B-FE86-58DC-7E79-AB0FA2B4B652]
    // </editor-fold> 
    private int boffset;

    /**
     *  Creates a 7x7 kernel filter
     * @param kernel
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1802CCB6-CC81-2CEB-52AD-6A0EF6FBCC0D]
    // </editor-fold> 
    public KernelFilter7x7(int[] kernel) {

        this(kernel, 0, 0, 0);

    }

    /**
     *  Creates a 7x7 kernel filter which adds given amount to each channel
     * @param kernel
     * @param bg 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0B033286-2157-ACF5-0D2E-42A3028B0529]
    // </editor-fold> 
    public KernelFilter7x7(int[] kernel, int bg) {

        this(kernel, bg, bg, bg);

    }

    /**
     *  Creates a 7x7 kernel filter which adds given amounts to channels.
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
    // #[regen=yes,id=DCE.768DBE2C-A8E9-E8A1-1F2A-0ED4D1E59021]
    // </editor-fold> 
    public KernelFilter7x7(int[] kernel, int r, int g, int b) {

        this.kernel = kernel;
        roffset = r > 255 ? 255 : r < 0 ? 0 : r;
        goffset = g > 255 ? 255 : g < 0 ? 0 : g;
        boffset = b > 255 ? 255 : b < 0 ? 0 : b;

    }

    /**
     * Arranges the pixel data and passes it to the consumer
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9F1D8481-972F-5DE4-49FE-774565DCE6F9]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int[] row = new int[width]; // One row of image data
        int red, gr, bl;
        int[] ind = new int[49]; // Array of indexes around the current pixel

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                bl = gr = red = 0; // Initialize the color counters

                if (y == 0 || y == 1 || x == 1 || x == 0 || x == width - 1 || x == width - 2 || y == height - 1 || y == height - 2 || x == 2 || y == 2 || x == width - 3 || y == height - 3) {

                    // Setting up the indexes. If an index goes one step over
                    // the edge, it will become one
                    // step towards the center, two steps when over by two etc.
                    ind[24] = x + y * width;
                    ind[23] = x == 0 ? ind[24] : x + y * width - 1;
                    ind[17] = y == 0 ? ind[24] : x + (y - 1) * width;
                    ind[25] = x == width - 1 ? ind[24] : x + y * width + 1;
                    ind[31] = y == height - 1 ? ind[24] : x + (y + 1) * width;

                    ind[16] = x == 0 ? ind[17] : y == 0 ? ind[23] : x + (y - 1) * width - 1;
                    ind[18] = x == width - 1 ? ind[17] : y == 0 ? ind[25] : x + (y - 1) * width + 1;
                    ind[30] = x == 0 ? ind[31] : y == height - 1 ? ind[23] : x + (y + 1) * width - 1;
                    ind[32] = x == width - 1 ? ind[31]
                            : y == height - 1 ? ind[25] : x + (y + 1) * width + 1;
                    ind[10] = y == 0 ? ind[24] : y == 1 ? ind[17] : x + (y - 2) * width;
                    ind[22] = x == 0 ? ind[24] : x == 1 ? ind[23] : x + y * width - 2;
                    ind[26] = x == width - 1 ? ind[24]
                            : x == width - 2 ? ind[25] : x + y * width + 2;
                    ind[38] = y == height - 1 ? ind[24]
                            : y == height - 2 ? ind[31] : x + (y + 2) * width;
                    ind[37] = y == height - 1 ? ind[23]
                            : y == height - 2 ? ind[30] : x == 0 ? ind[38] : x + (y + 2) * width - 1;
                    ind[39] = y == height - 1 ? ind[25]
                            : y == height - 2 ? ind[32]
                            : x == width - 1 ? ind[38] : x + (y + 2) * width + 1;
                    ind[9] = y == 0 ? ind[23] : y == 1 ? ind[16]
                            : x == 0 ? ind[10] : x + (y - 2) * width - 1;
                    ind[11] = y == 0 ? ind[25] : y == 1 ? ind[18]
                            : x == width - 1 ? ind[10] : x + (y - 2) * width + 1;
                    ind[15] = x == 0 ? ind[17] : x == 1 ? ind[16]
                            : y == 0 ? ind[22] : x + (y - 1) * width - 2;
                    ind[29] = x == 0 ? ind[31] : x == 1 ? ind[30]
                            : y == height - 1 ? ind[22] : x + (y + 1) * width - 2;
                    ind[19] = x == width - 1 ? ind[17]
                            : x == width - 2 ? ind[18] : y == 0 ? ind[26] : x + (y - 1) * width + 2;
                    ind[33] = x == width - 1 ? ind[31]
                            : x == width - 2 ? ind[31]
                            : y == height - 1 ? ind[26] : x + (y + 1) * width + 2;
                    ind[8] = x == 0 ? ind[10] : x == 1 ? ind[9]
                            : y == 0 ? ind[22] : y == 1 ? ind[15] : x + (y - 2) * width - 2;
                    ind[12] = x == width - 1 ? ind[10]
                            : x == width - 2 ? ind[11] : y == 0 ? ind[26]
                            : y == 1 ? ind[19] : x + (y - 2) * width + 2;
                    ind[36] = y == height - 1 ? ind[22]
                            : y == height - 2 ? ind[29] : x == 0 ? ind[31]
                            : x == 1 ? ind[30] : x + (y + 2) * width - 2;
                    ind[40] = y == height - 1 ? ind[26]
                            : y == height - 2 ? ind[33]
                            : x == width - 1 ? ind[38]
                            : x == width - 2 ? ind[39] : x + (y + 2) * width + 2;
                    ind[3] = y == 0 ? ind[24] : y == 1 ? ind[17]
                            : y == 2 ? ind[10] : x + (y - 3) * width;
                    ind[45] = y == height - 1 ? ind[24]
                            : y == height - 2 ? ind[31]
                            : y == height - 3 ? ind[38] : x + (y + 3) * width;
                    ind[21] = x == 0 ? ind[24] : x == 1 ? ind[23]
                            : x == 2 ? ind[22] : x + y * width - 3;
                    ind[27] = x == width - 1 ? ind[24]
                            : x == width - 2 ? ind[25]
                            : x == width - 3 ? ind[26] : x + y * width + 3;
                    ind[2] = y == 0 ? ind[23] : y == 1 ? ind[16]
                            : y == 2 ? ind[9] : x == 0 ? ind[3] : x + (y - 3) * width - 1;
                    ind[4] = y == 0 ? ind[25] : y == 1 ? ind[18]
                            : y == 2 ? ind[11] : x == width - 1 ? ind[3] : x + (y - 3) * width + 1;
                    ind[44] = y == height - 1 ? ind[23]
                            : y == height - 2 ? ind[30]
                            : y == height - 3 ? ind[37]
                            : x == 0 ? ind[45] : x + (y + 3) * width - 1;
                    ind[46] = y == height - 1 ? ind[25]
                            : y == height - 2 ? ind[32]
                            : y == height - 3 ? ind[39]
                            : x == width - 1 ? ind[45] : x + (y + 3) * width + 1;
                    ind[14] = x == 0 ? ind[17] : x == 1 ? ind[16]
                            : x == 2 ? ind[15] : y == 0 ? ind[21] : x + (y - 1) * width - 3;
                    ind[28] = x == 0 ? ind[31] : x == 1 ? ind[30]
                            : x == 2 ? ind[29] : y == height - 1 ? ind[21] : x + (y + 1) * width - 3;
                    ind[20] = x == width - 1 ? ind[17]
                            : x == width - 2 ? ind[18]
                            : x == width - 3 ? ind[19]
                            : y == 0 ? ind[27] : x + (y - 1) * width + 3;
                    ind[34] = x == width - 1 ? ind[31]
                            : x == width - 2 ? ind[32]
                            : x == width - 3 ? ind[33]
                            : y == height - 1 ? ind[27] : x + (y + 1) * width + 3;
                    ind[1] = y == 0 ? ind[22] : y == 1 ? ind[15]
                            : y == 2 ? ind[8] : x == 0 ? ind[3]
                            : x == 1 ? ind[2] : x + (y - 3) * width - 2;
                    ind[5] = y == 0 ? ind[26] : y == 1 ? ind[19]
                            : y == 2 ? ind[12] : x == width - 1 ? ind[3]
                            : x == width - 2 ? ind[4] : x + (y - 3) * width + 2;
                    ind[43] = y == height - 1 ? ind[22]
                            : y == height - 2 ? ind[29]
                            : y == height - 3 ? ind[36]
                            : x == 0 ? ind[45]
                            : x == 1 ? ind[44] : x + (y + 3) * width - 2;
                    ind[47] = y == height - 1 ? ind[26]
                            : y == height - 2 ? ind[33]
                            : y == height - 3 ? ind[40]
                            : x == width - 1 ? ind[45]
                            : x == width - 2 ? ind[46]
                            : x + (y + 3) * width + 2;
                    ind[7] = x == 0 ? ind[10] : x == 1 ? ind[9]
                            : x == 2 ? ind[8] : y == 0 ? ind[21]
                            : y == 1 ? ind[14] : x + (y - 2) * width - 3;
                    ind[35] = x == 0 ? ind[38] : x == 1 ? ind[37]
                            : x == 2 ? ind[36] : y == height - 1 ? ind[21]
                            : y == height - 2 ? ind[28] : x + (y + 2) * width - 3;
                    ind[13] = x == width - 1 ? ind[10]
                            : x == width - 2 ? ind[11]
                            : x == width - 3 ? ind[12]
                            : y == 0 ? ind[27]
                            : y == 1 ? ind[20] : x + (y - 2) * width + 3;
                    ind[41] = x == width - 1 ? ind[38]
                            : x == width - 2 ? ind[39]
                            : x == width - 3 ? ind[40]
                            : y == height - 1 ? ind[27]
                            : y == height - 2 ? ind[34]
                            : x + (y + 2) * width + 3;
                    ind[0] = x == 0 ? ind[3] : x == 1 ? ind[2]
                            : x == 2 ? ind[1] : y == 0 ? ind[21]
                            : y == 1 ? ind[14] : y == 2 ? ind[7] : x + (y - 3) * width - 3;
                    ind[6] = x == width - 1 ? ind[3] : x == width - 2 ? ind[4]
                            : x == width - 3 ? ind[5] : y == 0 ? ind[27]
                            : y == 1 ? ind[20] : y == 2 ? ind[13] : x + (y - 3) * width + 3;
                    ind[42] = x == 0 ? ind[45] : x == 1 ? ind[44]
                            : x == 2 ? ind[43] : y == height - 1 ? ind[21]
                            : y == height - 2 ? ind[28]
                            : y == height - 3 ? ind[35] : x + (y + 3) * width - 3;
                    ind[48] = x == width - 1 ? ind[45]
                            : x == width - 2 ? ind[46]
                            : x == width - 3 ? ind[47]
                            : y == height - 1 ? ind[27]
                            : y == height - 2 ? ind[34]
                            : y == height - 3 ? ind[41]
                            : x + (y + 3) * width + 3;

                } else { // if we are not in the border region

                    // Could have made this with a loop but it slowed the
                    // filtering.
                    // (By 20 seconds on 800*600 image)

                    ind[0] = x + (y - 3) * width - 3;
                    ind[1] = x + (y - 3) * width - 2;
                    ind[2] = x + (y - 3) * width - 1;
                    ind[3] = x + (y - 3) * width;
                    ind[4] = x + (y - 3) * width + 1;
                    ind[5] = x + (y - 3) * width + 2;
                    ind[6] = x + (y - 3) * width + 3;
                    ind[7] = x + (y - 2) * width - 3;
                    ind[8] = x + (y - 2) * width - 2;
                    ind[9] = x + (y - 2) * width - 1;
                    ind[10] = x + (y - 2) * width;
                    ind[11] = x + (y - 2) * width + 1;
                    ind[12] = x + (y - 2) * width + 2;
                    ind[13] = x + (y - 2) * width + 3;
                    ind[14] = x + (y - 1) * width - 3;
                    ind[15] = x + (y - 1) * width - 2;
                    ind[16] = x + (y - 1) * width - 1;
                    ind[17] = x + (y - 1) * width;
                    ind[18] = x + (y - 1) * width + 1;
                    ind[19] = x + (y - 1) * width + 2;
                    ind[20] = x + (y - 1) * width + 3;
                    ind[21] = x + y * width - 3;
                    ind[22] = x + y * width - 2;
                    ind[23] = x + y * width - 1;
                    ind[24] = x + y * width;
                    ind[25] = x + y * width + 1;
                    ind[26] = x + y * width + 2;
                    ind[27] = x + y * width + 3;
                    ind[28] = x + (y + 1) * width - 3;
                    ind[29] = x + (y + 1) * width - 2;
                    ind[30] = x + (y + 1) * width - 1;
                    ind[31] = x + (y + 1) * width;
                    ind[32] = x + (y + 1) * width + 1;
                    ind[33] = x + (y + 1) * width + 2;
                    ind[34] = x + (y + 1) * width + 3;
                    ind[35] = x + (y + 2) * width - 3;
                    ind[36] = x + (y + 2) * width - 2;
                    ind[37] = x + (y + 2) * width - 1;
                    ind[38] = x + (y + 2) * width;
                    ind[39] = x + (y + 2) * width + 1;
                    ind[40] = x + (y + 2) * width + 2;
                    ind[41] = x + (y + 2) * width + 3;
                    ind[42] = x + (y + 3) * width - 3;
                    ind[43] = x + (y + 3) * width - 2;
                    ind[44] = x + (y + 3) * width - 1;
                    ind[45] = x + (y + 3) * width;
                    ind[46] = x + (y + 3) * width + 1;
                    ind[47] = x + (y + 3) * width + 2;
                    ind[48] = x + (y + 3) * width + 3;

                }

                if (!getLock(CH1)) {
                    for (int i = 0; i < 49; i++) {
                        red += ((pix[ind[i]] >> 16) & 0xff) * kernel[i];
                    }
                    red /= kernel[49] + roffset;
                } else {
                    red = (pix[ind[24]] >> 16) & 0xff;
                }
                if (!getLock(CH2)) {
                    for (int i = 0; i < 49; i++) {
                        gr += ((pix[ind[i]] >> 8) & 0xff) * kernel[i];
                    }
                    gr /= kernel[49] + goffset;
                } else {
                    gr = (pix[ind[24]] >> 8) & 0xff;
                }
                if (!getLock(CH3)) {
                    for (int i = 0; i < 49; i++) {
                        bl += ((pix[ind[i]]) & 0xff) * kernel[i];
                    }
                    bl /= kernel[49] + boffset;
                } else {
                    bl = pix[ind[24]] & 0xff;
                }

                // Combine the channels to a single pixel
                row[x] = (255 << 24) | (((red > 255) ? 255 : (red < 0) ? 0 : red) << 16) | (((gr > 255) ? 255 : (gr < 0) ? 0 : gr) << 8) | ((bl > 255) ? 255 : (bl < 0) ? 0 : bl);

            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }
} // End KernelFilter7x7