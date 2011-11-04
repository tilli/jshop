package jshop.filters;

import java.awt.Point;

/**
 *  Area filler which works within the given thresholding area.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.35CE2AF4-0E63-745C-A34D-46E7810F5E6A]
// </editor-fold> 
public class AreaFiller extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.29B6CF58-3A42-C942-89CF-8DA6ACECB580]
    // </editor-fold> 
    private static final long serialVersionUID = -6233258136003968896L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E819DB91-0D3A-C215-91E0-D93AB5960694]
    // </editor-fold> 
    private int startx;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E99CB47D-2750-5540-8B3A-83374345EF12]
    // </editor-fold> 
    private int starty;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2BD6CA65-4A29-A132-B134-9BAE487CFC33]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.37069763-1FF4-C73B-1EB1-84595B298559]
    // </editor-fold> 
    private int height;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F54A871F-587E-682F-6906-8BA136257AD2]
    // </editor-fold> 
    private int col;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA5ABC25-D080-244D-BE1D-ED91D33AA1A9]
    // </editor-fold> 
    private int[] pix;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C20158F-210D-F058-355C-FB098575C4F1]
    // </editor-fold> 
    private int[] newdata;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6DAF171C-E690-6EA4-2894-CDC6A7AF3A64]
    // </editor-fold> 
    private int rth;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F02A00C5-66CF-8176-E8E9-47D325D89C4B]
    // </editor-fold> 
    private int gth;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3C111577-E258-D324-06D0-0DB1B76C832F]
    // </editor-fold> 
    private int bth;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.16F40332-351B-FCBC-9AE7-3B56805444CD]
    // </editor-fold> 
    private int rmax;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A5F0E72C-2552-CE2B-1F21-3C16009AF091]
    // </editor-fold> 
    private int gmax;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AAAD2309-B64C-FB35-77B9-6C7625DEE00B]
    // </editor-fold> 
    private int bmax;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A130ED39-E623-A59A-2D25-C7CE2F2FB946]
    // </editor-fold> 
    private int rmin;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.69DC54F4-61E7-2C42-7E96-0194736412EC]
    // </editor-fold> 
    private int gmin;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B845ACED-B0C8-EE05-CCD7-B64A98D88513]
    // </editor-fold> 
    private int bmin;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.066201C3-1678-7195-CF0F-01CE7F6FAF47]
    // </editor-fold> 
    private int[] stack;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BEDF708A-F9C8-3559-71F3-499FCEDE3D64]
    // </editor-fold> 
    private int stackcounter;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F61F6A15-0F39-61C2-5769-A9DA4B800084]
    // </editor-fold> 
    private int thstyle = 0;
    /**
     *  Or thresholding. If one of the color channels of the tested pixel is in
     *  	 the thresholding area, the pixel is colored.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B5D28A07-C307-6CFA-9CF2-FEF673A45565]
    // </editor-fold> 
    public static final int OR_THRESHOLD = 1;
    /**
     *  And thresholding. All channels must be in their thresholding area to
     *  	 color the pixel. This is default behaviour.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA9D4C59-EEC5-3885-20AB-F6D1C6D09361]
    // </editor-fold> 
    public static final int AND_THRESHOLD = 0;

    /**
     *  Wraps the coordinates to a <code>Point</code>.
     * @param p 
     * @param col
     * @param threshold
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77104632-1750-2FF9-E718-02540EA49275]
    // </editor-fold> 
    public AreaFiller(Point p, int col, int threshold) {

        this(p.x, p.y, col, threshold, threshold, threshold);

    }

    /**
     *  Creates a new <code>AreaFiller</code> which has same thresholding value
     *  	 on all channels.
     * @param x 
     * @param col
     * @param y
     * @param threshold
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2D176568-1391-6AD4-36EB-3D141B23DB40]
    // </editor-fold> 
    public AreaFiller(int x, int y, int col, int threshold) {

        this(x, y, col, threshold, threshold, threshold);

    }

    /**
     *  @param p 
     * @param col
     * @param rthres
     * @param gthres 
     * @param bthres
     * @see #AreaFiller(int, int, int, int, int, int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7C9BB412-B785-B27B-8275-30C625582E4A]
    // </editor-fold> 
    public AreaFiller(Point p, int col, int rthres, int gthres, int bthres) {

        this(p.x, p.y, col, rthres, gthres, bthres);

    }

    /**
     *  Creates a new <code>AreaFiller</code>
     *  	 
     *  	 @param x
     *  	            starting x coordinate
     *  	 @param y
     *  	            starting y coordinate
     *  	 @param col
     *  	            fill color (0xaarrggbb)
     *  	 @param rthres
     *  	            red threshold.
     *  	 @param gthres
     *  	            green threshold
     *  	 @param bthres
     *  	            blue threshold
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9EF4520A-BFC4-D6DC-EA5A-451DA87E0E0D]
    // </editor-fold> 
    public AreaFiller(int x, int y, int col, int rthres, int gthres, int bthres) {

        startx = x;
        starty = y;
        this.col = col;
        rth = rthres;
        gth = gthres;
        bth = bthres;
        stack = new int[200];

    }

    /**
     *  Sets the thresholding style to AND or OR
     * @param style
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.96501BA3-7BCF-04A5-ACE1-0822270F2D44]
    // </editor-fold> 
    public void setThresholdStyle(int style) {

        thstyle = style;

    }

    /**
     * Filters the image
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9FAB1B05-6820-9471-9A5F-7912ACA4A869]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        this.pix = pix;
        this.width = width;
        this.height = height;
        newdata = new int[pix.length];
        firstPoint(startx, starty);
        fill(startx, starty);
        for (int i = 0; i < pix.length; i++) {
            if (newdata[i] == 0) {
                newdata[i] = pix[i];
            }
        }
        this.pix = null;
        pix = null;
        passToConsumer(0, 0, width, height, newdata, 0, width);

    }

    /**
     *  Sets the threshold
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84244363-6B92-8463-E87E-203130B85455]
    // </editor-fold> 
    private void firstPoint(int x, int y) {

        int pixel = pix[x + y * width];
        rmax = ((pixel >> 16) & 0xff) + rth;
        rmin = ((pixel >> 16) & 0xff) - rth;
        gmax = ((pixel >> 8) & 0xff) + gth;
        gmin = ((pixel >> 8) & 0xff) - gth;
        bmax = (pixel & 0xff) + bth;
        bmin = (pixel & 0xff) - bth;
        rmax = rmax > 255 ? 255 : rmax;
        gmax = gmax > 255 ? 255 : gmax;
        bmax = bmax > 255 ? 255 : bmax;
        rmin = rmin < 0 ? 0 : rmin;
        gmin = gmin < 0 ? 0 : gmin;
        bmin = bmin < 0 ? 0 : bmin;

    }

    /**
     *  Checks if a point is in threshold area
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6F86A8DB-6F57-3213-E782-374D3C2A10C8]
    // </editor-fold> 
    private boolean checkPoint(int px, int py) {

        if (px == -1 || px == width || py == -1 || py == height) {
            return false;
        }
        int pixel = pix[px + py * width];
        boolean b;
        if (thstyle == 0) {
            b = ((((pixel >> 16) & 0xff) <= rmax) && (((pixel >> 16) & 0xff) >= rmin)) && ((((pixel >> 8) & 0xff) <= gmax) && (((pixel >> 8) & 0xff) >= gmin)) && (((pixel & 0xff) <= bmax) && ((pixel & 0xff) >= bmin));
        } else {
            b = ((((pixel >> 16) & 0xff) <= rmax) && (((pixel >> 16) & 0xff) >= rmin)) || ((((pixel >> 8) & 0xff) <= gmax) && (((pixel >> 8) & 0xff) >= gmin)) || (((pixel & 0xff) <= bmax) && ((pixel & 0xff) >= bmin));
        }
        return b;

    }

    /**
     *  Recursive fill algorithm
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5E5A6A6E-CB8A-73F9-47D3-A282707EB518]
    // </editor-fold> 
    private void fill(int sx, int sy) {

        int x = sx;
        int y = sy;
        do {
            newdata[x + y * width] = col;
            if (!checkPoint(x - 1, y - 1) && checkPoint(x, y - 1) && newdata[x + (y - 1) * width] != col) {
                push(x, y - 1);
            }
            if (!checkPoint(x - 1, y + 1) && checkPoint(x, y + 1) && newdata[x + (y + 1) * width] != col) {
                push(x, y + 1);
            }
            --x;
        } while (checkPoint(x, y));
        if (checkPoint(x, y - 1) && newdata[x + (y - 1) * width] != col) {
            push(x, y - 1);
        }
        if (checkPoint(x, y + 1) && newdata[x + (y + 1) * width] != col) {
            push(x, y + 1);
        }
        x = sx + 1;
        if (checkPoint(x, y)) {
            do {
                newdata[x + y * width] = col;
                if (!checkPoint(x + 1, y - 1) && checkPoint(x, y - 1) && newdata[x + (y - 1) * width] != col) {
                    push(x, y - 1);
                }
                if (!checkPoint(x + 1, y + 1) && checkPoint(x, y + 1) && newdata[x + (y + 1) * width] != col) {
                    push(x, y + 1);
                }
                ++x;
            } while (checkPoint(x, y));
        }
        if (checkPoint(x, y - 1) && newdata[x + (y - 1) * width] != col) {
            push(x, y - 1);
        }
        if (checkPoint(x, y + 1) && newdata[x + (y + 1) * width] != col) {
            push(x, y + 1);
        }

        int val;
        while ((val = pop()) != -1) {
            if (checkPoint((val >> 16) & 0xffff, val & 0xffff)) {
                fill((val >> 16) & 0xffff, val & 0xffff);
            }
        }

    }

    /**
     *  Stack pop
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8F6D7F32-12B0-6A9B-A0B2-ABC2025F5DF5]
    // </editor-fold> 
    private int pop() {

        if (stackcounter == 0) {
            return -1;
        }
        return stack[--stackcounter];

    }

    /**
     *  Stack push
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1B81A26D-25A7-9DE7-F29E-695A5633ABB4]
    // </editor-fold> 
    private void push(int sx, int sy) {

        int val = ((sx << 16) & 0xffff0000) | (sy & 0xffff);
        if (stackcounter == stack.length) {
            int[] temp = stack;
            stack = new int[temp.length + stack.length];
            System.arraycopy(temp, 0, stack, 0, temp.length);
        }
        stack[stackcounter++] = val;

    }
} // End AreaFiller