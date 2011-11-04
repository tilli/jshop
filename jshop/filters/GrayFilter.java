package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Creates a gray scale image.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.E068107F-5961-15CE-625D-7F8DFE3AB488]
// </editor-fold> 
public final class GrayFilter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1B7905C5-8931-76FB-3EC2-0D8CEEEEC30F]
    // </editor-fold> 
    private static final long serialVersionUID = 8813752756790169882L;

    /**
     *  Creates a new <code>GrayFilter</code>.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7C90F894-BA03-DA5F-3FA0-412078636AE7]
    // </editor-fold> 
    public GrayFilter() {
    }

    /**
     *  Unaffected by channel locks
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.26965FF7-0DDB-CFB3-907F-9A3EE3F12411]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }

    /**
     *  Always RGB
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ED34FD16-CC45-71B2-E965-3B566288DAC4]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }

    /**
     * Integer data. The data passed to the consumer will be byte-data
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6086CC1D-7BAF-E2DB-E881-6995B2D4DFDB]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        int val;
        byte[] dat = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            val = (int) (((data[i] >> 16) & 0xff) * 0.212671 + ((data[i] >> 8) & 0xff) * .715160 + (data[i] & 0xff) * .072169);
            dat[i] = (byte) val;
        }
        byte[] ind = new byte[256];
        for (int i = 0; i < 256; i++) {
            ind[i] = (byte) i;
        }
        IndexColorModel mod = new IndexColorModel(8, 256, ind, ind, ind);
        consumer.setColorModel(mod);
        passBytesToConsumer(0, 0, w, h, dat, 0, w);

    }

    /**
     * Byte filter. This will convert the color model to a direct gray scale (0
     * black, 255 white)
     *
     * @param data
     */
    // NOT WORKING AS IT SHOULD, THE COLORS ARE NOT GOOD
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85DDDEF0-CC82-0B7B-CE95-324D140A9390]
    // </editor-fold> 
    protected void filterIndexed(byte[] data, int w, int h) {

        IndexColorModel model = (IndexColorModel) super.getColorModel();
        byte[] val = new byte[256];
        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        for (int i = 0; i < 256; i++) {
            val[i] = (byte) i;
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (red[data[i] & 0xff] * 0.212671 + gre[data[i] & 0xff] * 0.715160 + blu[data[i] & 0xff] * 0.072169);
        }
        consumer.setColorModel(new IndexColorModel(8, 256, val, val, val));
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Returns false. This filter affects both the color model and the byte data
     * when filtering indexed color models.
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DEC304B2-CF16-EFC1-5FD9-B798044A4AB3]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return false;

    }
} // End GrayFilter