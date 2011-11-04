package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Negates unlocked channels
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.7F56104C-1043-B2F0-9E13-54B27690D3E2]
// </editor-fold> 
public final class NegativeFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.270CB3E2-CE20-9C9D-17AC-6752DD0BACAB]
    // </editor-fold> 
    private static final long serialVersionUID = 8869203147521486451L;

    /**
     *  Creates a new <code>NegativeFilter</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F5BF3AB3-0CDE-B33C-5A76-9E66B62AFCB0]
    // </editor-fold> 
    public NegativeFilter() {
    }

    /**
     * Only the color model is negated
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA9EE47F-5502-2E57-44D1-F1E18E6CA575]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return true;

    }

    /**
     * Integer filter
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8E0FE1DC-C0B5-3189-DB36-9053D20116EA]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        int row[] = new int[w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                row[x] = (data[i] & 0xff000000) | ((~data[i]) & 0xffffff);
            }
            passToConsumer(0, y, w, 1, row, 0, w);
        }

    }

    /**
     * Color model filter
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9505CBBE-DD8B-BEBA-8F25-776E00395C7D]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        for (int i = 0; i < n; i++) {
            red[i] = (byte) ~red[i];
            gre[i] = (byte) ~gre[i];
            blu[i] = (byte) ~blu[i];
        }
        return new IndexColorModel(8, n, red, gre, blu);

    }

    /**
     * Gray scale filtering
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4802B57F-5F55-0AFB-B436-D5F750D7F3C8]
    // </editor-fold> 
    protected void filterBytes(byte[] data, int w, int h) {

        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (~data[i]);
        }
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }
} // End NegativeFilter