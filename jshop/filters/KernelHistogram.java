package jshop.filters;

/**
 *  Filter which takes a histogram from the area around a pixel and sets a new
 *   pixel value according to that histogram.<br>
 *   Example:<br>
 *   
 *   <pre>
 *       Size = 5 ( 55 kernel)
 *       Value = 2 ( The second common pixel )
 *       Image consists of following pixels:
 *       12111322...
 *       12111112...
 *       12221112...
 *       12212123...
 *       11111211...
 *       ...
 *       Consider pixel at row 3, column 3. The 55 area around it is:
 *       12111
 *       12111
 *       12222
 *       12212
 *       11111
 *       This area has 16 instances of 1 and 9 instances of 2, so the pixel at
 *       (3, 3) gets set to 2 (no modifications)
 *       The next pixel is (3, 4), where the area has 15 instances of 1, 9 of 2 and 1 of 3
 *       This pixel also gets value of 2 ( The second common value ).
 *   </pre>
 *   
 *   The value-argument is used to determine the pixel which is selected from the
 *   histogram. <br>
 *   Not working currently.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F9F4A9BF-6A07-E593-1C1B-E91E84A1087F]
// </editor-fold> 
public class KernelHistogram extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.338157C1-F9F6-8269-AB2A-49CF40D04417]
    // </editor-fold> 
    private static final long serialVersionUID = -3154368026296900436L;
    /**
     *  Uses the middle pixel of the histogram
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2FB19B33-977E-4A0F-FCAE-2F69A763232C]
    // </editor-fold> 
    public static final int MIDDLE = -1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0014616F-2A72-A60E-2FEF-F8C8482F9F96]
    // </editor-fold> 
    private int size;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8EB28DA7-1F29-6F66-1A0B-0C5A2589324F]
    // </editor-fold> 
    private int value;

    /**
     *  Creates a new KernelHistogram
     *  	 
     *  	 @param size
     *  	            Size of the kernel. This should be an odd number
     *  	 @param index
     *  	            Specifies the index which is selected from the histogram
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CAE497ED-5FDD-1842-606F-3C7AEDBAAC5A]
    // </editor-fold> 
    public KernelHistogram(int index, int size) {

        this.size = size / 2;
        value = index == MIDDLE ? size * size / 2 : index;

    }

    /**
     * Gets the string representation of the properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E1700BD9-298C-DA86-4BFF-194B41A6CF5E]
    // </editor-fold> 
    protected String getProperties() {

        return "Size = " + size + ", Index = " + value;

    }

    /**
     * Filters the image
     *
     * @param height
     * @param width 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BAC0690D-FE00-B5F0-181B-199B5ED3D108]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int red[] = new int[256];
        int gre[] = new int[256];
        int blu[] = new int[256];
        int r, g, b, ind;
        int[] row = new int[width];

        for (int y = size; y < height - size - 1; y++) {
            for (int x = size; x < width - size - 1; x++) {
                ind = y * width + x;
                for (int dy = -size; dy <= size; dy++) {
                    for (int dx = -size; dx <= size; dx++) {
                        red[((pix[ind + (dy * width + dx)]) >> 16) & 0xff]++;
                        gre[((pix[ind + (dy * width + dx)]) >> 8) & 0xff]++;
                        blu[(pix[ind + (dy * width + dx)]) & 0xff]++;
                    }
                }
                r = sort(red);
                g = sort(gre);
                b = sort(blu);
                row[x] = ((pix[ind] << 24) & 0xff) | ((r << 16) & 0xff) | ((g << 8) & 0xff) | (b & 0xff);
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B169F8C8-3772-01C7-D94E-26DD3B1F74A3]
    // </editor-fold> 
    /**
     *
     * @param data
     * @return
     */
    public int sort(int[] data) {

        return 0;

    }
} // End KernelHistogram