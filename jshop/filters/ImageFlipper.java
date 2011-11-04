package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Flips the unlocked channels upside down
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.FE8B5027-95F2-C9D8-DD75-CA2C44A0EEEF]
// </editor-fold> 
public final class ImageFlipper extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.056A9426-C505-F0BC-0921-78C4A8FDADB7]
    // </editor-fold> 
    private static final long serialVersionUID = -6395033809850034702L;

    /**
     *  Creates a new <code>ImageFlipper</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.81F72848-9AC9-508D-EE1E-5274EF026671]
    // </editor-fold> 
    public ImageFlipper() {
    }

    /**
     *  Flips the data.
     * @param width
     * @param height 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FE350E0F-8A47-BBFA-D112-7A31B03CA709]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        int[] row = new int[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                row[x] = pix[width * (height - 1 - y) + x];
            }
            passToConsumer(0, y, width, 1, row, 0, width);
        }

    }

    /**
     * Byte flipper
     *
     * @param height
     * @param width
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0CE9D511-52AF-27B4-DB0D-2848F3F95AA4]
    // </editor-fold> 
    protected void filterIndexed(byte[] pix, int width, int height) {

        System.out.println("Shit");
        byte[] row = new byte[width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                row[x] = pix[width * (height - y - 1) + x];
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
    // #[regen=yes,id=DCE.98AC25FB-CC43-4D40-83F1-1A49A9F3D423]
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
    // #[regen=yes,id=DCE.506AF2FF-0B3E-0E76-D11D-F425DE5FF3D4]
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
    // #[regen=yes,id=DCE.31E7B225-3E0A-47D2-FFC3-0282F5D089F2]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }
} // End ImageFlipper