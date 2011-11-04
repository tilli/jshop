package jshop.filters;

import jshop.filters.kernel.Kernel;

/**
 *  Image filter which performs convolution operations. Not finished, borders are
 *   not working correctly
 *   
 *   @see Kernel
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.BCF4D770-AE1E-F88B-A0C6-863627F3912E]
// </editor-fold> 
public class ConvolutionFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B64594EA-964F-CDF8-E04D-0105EBFFB690]
    // </editor-fold> 
    private static final long serialVersionUID = 4251934204332522879L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A0139A0E-E758-B486-C182-5E6EA2A2F6AD]
    // </editor-fold> 
    private Kernel kernel;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB38D330-C2B2-14FB-856C-391BB3E79C97]
    // </editor-fold> 
    private int hints = 0;
    /**
     *  Extends the edges with the value in the border
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D07634F3-CE70-10A5-6A8D-1658B8358494]
    // </editor-fold> 
    public static final int EXTEND_EDGES = 0;
    /**
     *  Mirrors the extended edges from the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.15C5D3E8-B506-08DA-72B0-E4C7FD230372]
    // </editor-fold> 
    public static final int MIRROR_EDGES = 1;
    /**
     *  Performs no operation to the edges
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1FF4B27F-2B99-17DB-7ECC-012C74F69666]
    // </editor-fold> 
    public static final int FORGET_EDGES = 2;
    /**
     *  Sets the edges to 0
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.12567C8E-A74F-0AB7-1C0F-1C72A50F9D98]
    // </editor-fold> 
    public static final int ZERO_EDGES = 3;

    /**
     *  Creates an empty <code>ConvolutionFilter</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A824CBE3-FE4D-1CC7-0BC5-2E33F04CD8FB]
    // </editor-fold> 
    public ConvolutionFilter() {

        kernel = new Kernel();

    }

    /**
     *  Creates a <code>ConvolutionFilter</code> with specified
     *  	 <code>Kernel</code>
     * @param kernel
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.089E4EE5-AC1C-8FE7-DA8C-9BF834CE4F98]
    // </editor-fold> 
    public ConvolutionFilter(Kernel kernel) {

        this.kernel = kernel;

    }

    /**
     *  Sets a new <code>Kernel</code>
     * @param kernel
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.871A4830-C498-0C14-A6AC-806C91090901]
    // </editor-fold> 
    public void setKernel(Kernel kernel) {

        this.kernel = kernel;

    }

    /**
     *  Gets the <code>Kernel</code>
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9B11BA8F-A0DA-AA8C-32F3-8DE7CC6365BB]
    // </editor-fold> 
    public Kernel getKernel() {

        return kernel;

    }

    /**
     * Sets the hints. This affects operations near the border.
     *
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1A9D855A-8690-8922-FABE-34C1A7192E8B]
    // </editor-fold> 
    public void setHints(int h) {

        hints = h;

    }

    /**
     * Performs the filtering
     *
     * @param data
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1B5E3FFB-364D-5BFC-4B1E-28793F066ACB]
    // </editor-fold> 
    protected void filterData(int[] data, int width, int height) {

        hints = FORGET_EDGES;
        int kw = kernel.getWidth();
        int kh = kernel.getHeight();
        int kx = kernel.getOriginX();
        int ky = kernel.getOriginY();
        int roff = kernel.getOffset1();
        int goff = kernel.getOffset2();
        int boff = kernel.getOffset3();
        int diff = ky * width + kx;
        int[] kdata = kernel.getData();
        int div = kernel.getDivider();
        int[] row = new int[width];
        int borderx = width - kx - 1, bordery = height - ky - 1;

        for (int y = ky; y < bordery; y++) {
            for (int x = kx; x < borderx; x++) {
                int red = 0, gre = 0, blu = 0;
                int index = y * width + x;
                for (int dy = 0; dy < kw; dy++) {
                    for (int dx = 0; dx < kh; dx++) {
                        int dind = dy * kw + dx;
                        int tind = index - diff + dy * width + dx;
                        red += kdata[dind] * ((data[tind] >> 16) & 0xff);
                        gre += kdata[dind] * ((data[tind] >> 8) & 0xff);
                        blu += kdata[dind] * (data[tind] & 0xff);
                    }
                }
                red = (red / div + roff);
                gre = (gre / div + goff);
                blu = (blu / div + boff);
                red = red > 255 ? 255 : red < 0 ? 0 : red;
                gre = gre > 255 ? 255 : gre < 0 ? 0 : gre;
                blu = blu > 255 ? 255 : blu < 0 ? 0 : blu;
                row[x] = (data[index] & 0xff000000) | (red << 16) | (gre << 8) | blu;
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }
        if (hints == ZERO_EDGES) {
            for (int x = 0; x < width; x++) {
                row[x] = 0xff000000;
            }
            int[] col = new int[height - kh];
            for (int i = 0; i < col.length; i++) {
                col[i] = 0xff000000;
            }
            for (int y = 0; y < ky; y++) {
                passToConsumer(0, y, width, 1, row, 0, width);
            }
            for (int y = height - (kh - ky); y < height; y++) {
                passToConsumer(0, y, width, 1, row, 0, width);
            }
            for (int x = 0; x < kx; x++) {
                passToConsumer(x, ky, 1, height - kh, col, 0, 1);
            }
            for (int x = width - (kw - kx); x < width; x++) {
                passToConsumer(x, ky, 1, height - kh, col, 0, 1);
            }
        } else if (hints == FORGET_EDGES) {
            int[] col = new int[height - kh];
            for (int y = 0; y < ky; y++) {
                System.arraycopy(data, y * width, row, 0, width);
                passToConsumer(0, y, width, 1, row, 0, width);
            }
            for (int y = height - (kh - ky); y < height; y++) {
                System.arraycopy(data, y * width, row, 0, width);
                passToConsumer(0, y, width, 1, row, 0, width);
            }
            for (int x = 0; x < kx; x++) {
                for (int y = ky, dy = 0; y < height - (kh - ky); y++, dy++) {
                    col[dy] = data[y * width + x];
                }
                passToConsumer(x, ky, 1, height - kh, col, 0, 1);
            }
            for (int x = width - (kw - kx); x < width; x++) {
                for (int y = ky, dy = 0; y < height - (kh - ky); y++, dy++) {
                    col[dy] = data[y * width + x];
                }
                passToConsumer(x, ky, 1, height - kh, col, 0, 1);
            }
        }

    }
} // End ConvolutionFilter