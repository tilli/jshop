package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Translates an image
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.45B65986-8710-5E5B-3A79-857F026B087C]
// </editor-fold> 
public class ImageTranslator extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0E60EC5B-A2AF-010F-6E87-7183ACE82EB7]
    // </editor-fold> 
    private static final long serialVersionUID = 4603421420151618348L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.15673938-A8DB-309F-C2EF-3103220D2256]
    // </editor-fold> 
    private int dir;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.917C66CA-C201-3376-5310-1DCDDC7372AD]
    // </editor-fold> 
    private int amount;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A1503C08-6F0B-BB06-3A1F-01731C82D5CA]
    // </editor-fold> 
    private boolean goaround;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7A7F8CA9-2FA3-5D34-A345-3A0140286988]
    // </editor-fold> 
    private int bgcolor;
    /**
     *  Translate up
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.271D89F1-3CB2-8AFC-02D8-E2B314A8541A]
    // </editor-fold> 
    public static final int UP = 0;
    /**
     *  Translate left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CAE86195-C781-3B58-E2B4-A8F3BEF4EB23]
    // </editor-fold> 
    public static final int LEFT = 1;
    /**
     *  Translate down
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DC1A7AC5-73E2-919B-2E59-39E14B259DA0]
    // </editor-fold> 
    public static final int DOWN = 2;
    /**
     *  Translate right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.12154BD7-4CD3-0E2A-2498-A8512A725AAC]
    // </editor-fold> 
    public static final int RIGHT = 3;

    /**
     *  Creates an <code>ImageTranslator</code> which does nothing
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FA9B43E7-5A46-25E0-B3F1-7BBE5F408CB8]
    // </editor-fold> 
    public ImageTranslator() {

        this(0, 0, true, 0);

    }

    /**
     *  Creates a new <code>ImageTranslator</code> with rollover. Pixels that go
     *  	 over the edge appear at the other side.
     *  	 
     *  	 @param dir
     *  	            Direction
     *  	 @param amount
     *  	            Amount of translation in pixels
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.73B86394-0755-69D9-D05F-21CB59AF3951]
    // </editor-fold> 
    public ImageTranslator(int dir, int amount) {

        this(dir, amount, true, 0);

    }

    /**
     *  Creates a new <code>ImageTranslator</code>. Pixels going over the edge
     *  	 will not appear at other side.
     *  	 
     *  	 @param dir
     *  	            direction
     *  	 @param amount
     *  	            amount of translation in pixels
     *  	 @param bgcolor
     *  	            background color used to fill empty pixels. If data of the
     *  	            image is bytes, the bgcolor will be casted to byte.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A8C68432-32A6-84C7-B969-2E9AE8DC35CF]
    // </editor-fold> 
    public ImageTranslator(int dir, int amount, int bgcolor) {

        this(dir, amount, false, bgcolor);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8AF173E9-B21C-E12C-7F84-0714E426E471]
    // </editor-fold> 
    private ImageTranslator(int dir, int amount, boolean flow, int bgcolor) {

        this.dir = dir;
        this.amount = amount;
        this.goaround = flow;
        this.bgcolor = bgcolor;

    }

    /**
     *  Sets the direction of translation
     * @param dir
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.30CA5F11-19B5-04F0-62EC-DF5C5050A379]
    // </editor-fold> 
    public void setDirection(int dir) {

        this.dir = dir;

    }

    /**
     *  Sets the background color
     * @param bg
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0A7072E5-B2C8-74A7-1185-A1F2DCA5AA6C]
    // </editor-fold> 
    public void setBackground(int bg) {

        bgcolor = bg;

    }

    /**
     *  Sets the amount of translation
     * @param am
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4FC3D59C-CA3C-CE5E-E2D4-71CCEDF5B960]
    // </editor-fold> 
    public void setAmount(int am) {

        amount = am;

    }

    /**
     *  If set, the pixels that go over the edge appear at the othe side
     * @param overflow
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FA3F0DFF-C030-A571-FCA1-A12AF7BA0D9B]
    // </editor-fold> 
    public void setFlow(boolean overflow) {

        goaround = overflow;

    }

    /**
     * Integer translator
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.510BE48F-8B51-9C76-BBBD-321B4E0402C6]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int ind;
        int[] row = new int[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ind = getDir(x, y, width, height);
                if (ind == -1) {
                    row[x] = bgcolor;
                } else {
                    row[x] = pix[ind];
                }
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    /**
     * Byte translator
     *
     * @param height
     * @param width 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E0E358D2-55C9-1F82-E6D3-208BED21EAB0]
    // </editor-fold> 
    protected void filterIndexed(byte[] pix, int width, int height) {

        int ind;
        byte[] row = new byte[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ind = getDir(x, y, width, height);
                if (ind == -1) {
                    row[x] = (byte) bgcolor;
                } else {
                    row[x] = pix[ind];
                }
            }
            passBytesToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AB1A4547-7145-BBD7-C224-AD2BBA0AA783]
    // </editor-fold> 
    private int getDir(int x, int y, int w, int h) {

        switch (dir) {
            case LEFT:
                x += amount;
                break;
            case RIGHT:
                x -= amount;
                break;
            case UP:
                y += amount;
                break;
            case DOWN:
                y -= amount;
                break;
        }
        if (goaround) {
            if (x >= w) {
                x -= w;
            } else if (x < 0) {
                x += w;
            } else if (y >= h) {
                y -= h;
            } else if (y < 0) {
                y += h;
            }
        } else if (x >= w || x < 0 || y >= h || y < 0) {
            return -1;
        }
        return x + y * w;

    }

    /**
     * No special filtering needed
     *
     * @param model
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7E179A72-0689-B1A0-3D17-194128712C98]
    // </editor-fold> 
    protected boolean isSpecialModel(IndexColorModel model) {

        return false;

    }

    /**
     * Not filtering color model
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.46482F92-DEC2-877F-89AD-21625628B5AC]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return false;

    }

    /**
     * Gets the string representation of the properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.946F4BDA-5884-5AB8-E813-03D70C05EE1C]
    // </editor-fold> 
    protected String getProperties() {

        String sdir = "";
        switch (dir) {
            case UP:
                sdir = "Up";
                break;
            case DOWN:
                sdir = "Down";
                break;
            case LEFT:
                sdir = "Left";
                break;
            case RIGHT:
                sdir = "Right";
                break;
        }
        String bg = goaround ? "" : ", Background = #" + Integer.toHexString(bgcolor);
        return "Direction = " + sdir + ", Amount = " + amount + ", Rollover = " + goaround + bg;

    }
} // End ImageTranslator