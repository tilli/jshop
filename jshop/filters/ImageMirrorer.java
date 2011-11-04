package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Mirrors the unlocked channels
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.31D67BF9-02F4-A183-1EE0-20A0134483A6]
// </editor-fold> 
public final class ImageMirrorer extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5BC1D86F-8F7B-331D-9270-642E2334A836]
    // </editor-fold> 
    private static final long serialVersionUID = 3720543482789266819L;

    /**
     *  Creates a new <code>ImageMirrorer</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EC3A40C6-273F-2D0C-2563-D476476A2C44]
    // </editor-fold> 
    public ImageMirrorer() {
    }

    /**
     * Integer filter
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FB6AF5B3-764F-4F68-9EDD-A0164333C136]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int[] row = new int[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                row[x] = pix[width - x - 1 + width * y];
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    /**
     * Byte filter
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2F6211F0-207C-29EB-55EF-69D98B15D836]
    // </editor-fold> 
    protected void filterIndexed(byte[] pix, int width, int height) {

        byte[] row = new byte[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                row[x] = pix[width - x - 1 + width * y];
            }
            passBytesToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    /**
     * No special filtering needed
     *
     * @param model
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.023E09EC-F589-AAB8-E53A-96D056763494]
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
    // #[regen=yes,id=DCE.CCFACE83-376E-56FA-5117-86C0EFF9E4B5]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return false;

    }

    /**
     * No color space convertions
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.48B39256-18FC-2387-43E0-49866C310331]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }
} // End ImageMirrorer