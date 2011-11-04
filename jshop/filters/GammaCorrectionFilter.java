package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Alters the gamma-correction of the channels which are not locked.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.029A0513-5FB4-BADC-FED7-038C1DD7717D]
// </editor-fold> 
public final class GammaCorrectionFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C70AEA64-2834-B2BA-8720-647C273B5B2C]
    // </editor-fold> 
    private static final long serialVersionUID = -9013583965686390741L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C86FD2F3-6111-F0EA-7AC2-63DE9BD8D94B]
    // </editor-fold> 
    private double gammavalue;

    /**
     *  Gamma value will be 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.48468042-7103-418B-CD71-5F6BF2397841]
    // </editor-fold> 
    public GammaCorrectionFilter() {

        gammavalue = 1;

    }

    /**
     *  Creates a new <code>GammaCorrectionFilter</code>
     *  	 
     *  	 @param val
     *  	            amount of correction
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A93B80CE-5BE5-7436-656F-2B09ABD0F5EF]
    // </editor-fold> 
    public GammaCorrectionFilter(double val) {

        if (val == 0) {
            gammavalue = 1;
        } else {
            gammavalue = 1 / val;
        }

    }

    /**
     *  Sets the gamma value
     * @param val
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0BE1AD20-E60A-48D9-909A-3D964FE32B51]
    // </editor-fold> 
    public void setGammaValue(double val) {

        if (val == 0) {
            gammavalue = 1;
        } else {
            gammavalue = 1 / val;
        }

    }

    /**
     *  Gets the gamma value
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B1A57AB1-5465-595F-76F7-F32C69E8197A]
    // </editor-fold> 
    public double getValue() {

        return gammavalue;

    }

    /**
     * True color operation
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5E2E6AF0-A7B8-17A5-A8FB-9D961D23264C]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        int row[] = new int[w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                row[x] = (data[i] & 0xff000000) | ((int) (Math.pow(
                        ((double) ((data[i] >> 16) & 0xff) / 255),
                        gammavalue) * 255) << 16) | ((int) (Math.pow(
                        ((double) ((data[i] >> 8) & 0xff) / 255),
                        gammavalue) * 255) << 8) | ((int) (Math.pow(((double) ((data[i]) & 0xff) / 255),
                        gammavalue) * 255));
            }
            passToConsumer(0, y, w, 1, row, 0, w);
        }

    }

    /**
     * Only the <code>ColorModel</code> is affected in index color operations
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A0596ED7-EEFE-6747-CEC9-ECAF92D38976]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return true;

    }

    /**
     * Color model filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1AA6340B-CFE4-7C84-9E75-7A584DF129A7]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        for (int i = 0; i < n; i++) {
            red[i] = (byte) (255 * Math.pow((double) (red[i] & 0xff) / 255,
                    gammavalue));
            gre[i] = (byte) (255 * Math.pow((double) (gre[i] & 0xff) / 255,
                    gammavalue));
            blu[i] = (byte) (255 * Math.pow((double) (blu[i] & 0xff) / 255,
                    gammavalue));
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
    // #[regen=yes,id=DCE.8176C669-1C7F-F4C3-86C3-B87F107AD9AA]
    // </editor-fold> 
    protected void filterBytes(byte[] data, int w, int h) {

        for (int i = 0; i < data.length; i++) {
            data[i] = ((byte) (Math.pow(((double) ((data[i]) & 0xff) / 255),
                    gammavalue) * 255));
        }
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Allways RGB
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.623BAC54-1221-6113-ED78-B372C2119C7C]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }
} // End GammaCorrectionFilter