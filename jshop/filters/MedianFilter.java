package jshop.filters;

/**
 *  Class which performs median filtering to the image.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.7B47876E-9C01-D480-945D-326566D594CF]
// </editor-fold> 
public final class MedianFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.00F337F8-34F6-17F1-361C-A57968ED3FCE]
    // </editor-fold> 
    private static final long serialVersionUID = -6687603862242420781L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.09E9B02E-15F8-4646-FB31-3382CFBFDE8A]
    // </editor-fold> 
    private int size;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.288A864A-2888-F007-53E4-1D96A7114220]
    // </editor-fold> 
    private int index;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B48ADC0C-DAC3-E78C-C9A9-0B4540724603]
    // </editor-fold> 
    private Sort sort;
    /**
     *  33 median filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB3948EC-30F1-4580-09C7-C099EDDFC3D7]
    // </editor-fold> 
    public static final int SIZE3X3 = 0;
    /**
     *  55 median filter
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3701E53A-9253-344C-A44F-E5057ECBA7EF]
    // </editor-fold> 
    public static final int SIZE5X5 = 1;

    /**
     *  Creates a new MedianFilter with given size and amount of dilation.
     * @param size 
     * @param index
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D10A0C27-D14A-D8E1-ECA5-3019E7BD5E03]
    // </editor-fold> 
    public MedianFilter(int size, int index) {

        sort = new Sort();
        this.size = size;
        this.index = index;

    }

    /**
     * Gets the string representation of properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.028204FC-8423-F5F7-1650-EAB937660571]
    // </editor-fold> 
    protected String getProperties() {

        return "Size: " + (size == 0 ? "3*3" : "5*5") + ", Index = " + index;

    }

    /**
     * Performs the filtering
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F20BC7C0-3095-5AC9-1137-5275B2FAF28D]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        if (size == SIZE3X3) {
            filterImage3x3(pix, width, height);
        } else {
            filterImage5x5(pix, width, height);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7F5769DB-4EAC-A5B4-EDD1-BFC378D9DDC6]
    // </editor-fold> 
    private void filterImage5x5(int[] pix, int width, int height) {

        int rd, gr, bl;
        int[] red = new int[25];
        int[] gre = new int[25];
        int[] blu = new int[25];
        int[] ind = new int[25];
        int[] row = new int[width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

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
                        red[i] = ((pix[ind[i]] >> 16) & 0xff);
                    }
                    rd = getMiddle(red);
                } else {
                    rd = (pix[ind[12]] >> 16) & 0xff;
                }
                if (!getLock(CH2)) {
                    for (int i = 0; i < 25; i++) {
                        gre[i] = ((pix[ind[i]] >> 8) & 0xff);
                    }
                    gr = getMiddle(gre);
                } else {
                    gr = (pix[ind[12]] >> 8) & 0xff;
                }
                if (!getLock(CH3)) {
                    for (int i = 0; i < 25; i++) {
                        blu[i] = ((pix[ind[i]]) & 0xff);
                    }
                    bl = getMiddle(blu);
                } else {
                    bl = pix[ind[12]] & 0xff;
                }

                row[x] = (255 << 24) | (rd << 16) | (gr << 8) | bl;
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.928AC605-1A0B-EE6E-0377-7C4325B09FA3]
    // </editor-fold> 
    private void filterImage3x3(int[] pix, int width, int height) {

        int rd, gr, bl;
        int[] red = new int[9];
        int[] gre = new int[9];
        int[] blu = new int[9];
        int[] ind = new int[9];
        int[] row = new int[width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                ind[4] = x + y * width;
                ind[3] = x == 0 ? ind[4] : x + y * width - 1;
                ind[1] = y == 0 ? ind[4] : x + (y - 1) * width;
                ind[5] = x == width - 1 ? ind[4] : x + y * width + 1;
                ind[7] = y == height - 1 ? ind[4] : x + (y + 1) * width;
                ind[0] = x == 0 ? ind[1] : y == 0 ? ind[3] : x + (y - 1) * width - 1;
                ind[2] = x == width - 1 ? ind[1] : y == 0 ? ind[5] : x + (y - 1) * width + 1;
                ind[6] = x == 0 ? ind[7] : y == height - 1 ? ind[3] : x + (y + 1) * width - 1;
                ind[8] = x == width - 1 ? ind[7] : y == height - 1 ? ind[5] : x + (y + 1) * width + 1;

                // Gets the median value of each color channel.
                // Result is that the pixels get mixed up
                if (!getLock(CH1)) {
                    for (int i = 0; i < 9; i++) {
                        red[i] = ((pix[ind[i]] >> 16) & 0xff);
                    }
                    rd = getMiddle(red);
                } else {
                    rd = (pix[ind[4]] >> 16) & 0xff;
                }
                if (!getLock(CH2)) {
                    for (int i = 0; i < 9; i++) {
                        gre[i] = ((pix[ind[i]] >> 8) & 0xff);
                    }
                    gr = getMiddle(gre);
                } else {
                    gr = (pix[ind[4]] >> 8) & 0xff;
                }
                if (!getLock(CH3)) {
                    for (int i = 0; i < 9; i++) {
                        blu[i] = ((pix[ind[i]]) & 0xff);
                    }
                    bl = getMiddle(blu);
                } else {
                    bl = pix[ind[4]] & 0xff;
                }

                row[x] = (255 << 24) | (rd << 16) | (gr << 8) | bl;
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    /**
     *  Sets the index. Larger index means more dilated result. Size should be
     *  	 set before this.
     * @param dk 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.76C3400E-AF61-9841-B2C2-DE0EF48EDE33]
    // </editor-fold> 
    public void setIndex(int dk) {

        if (size == SIZE3X3 && dk > 8) {
            dk = 8;
        } else if (dk > 24) {
            dk = 24;
        } else if (dk < 0) {
            dk = 0;
        }
        index = dk;

    }

    /**
     *  Sets the filter size. (33 or 55)
     * @param sz
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.61BF48DE-6129-406A-92A7-2F93205B8830]
    // </editor-fold> 
    public void setFilterSize(int sz) {

        size = sz;

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0DA41472-81E7-E80F-B430-5D940A15FEE3]
    // </editor-fold> 
    private int getMiddle(int[] source) {

        sort.quickSort(source, 0, source.length - 1);
        return source[index];

    }
} // End Medianfilter