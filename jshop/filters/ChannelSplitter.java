package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Splits a channel from the image.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.524568A9-B899-0CE8-B642-75B8EE132804]
// </editor-fold> 
public final class ChannelSplitter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6EBCFF9E-1A33-5D36-253D-132105EFD48D]
    // </editor-fold> 
    private static final long serialVersionUID = -2148355671659681796L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.89E373CB-D10D-9DF0-7732-989D215FE066]
    // </editor-fold> 
    private int channel;

    /**
     *  Creates a new ChannelSplitter which splits the given channel
     * @param ch
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C588BD7D-9DA1-FA5B-57ED-B6FAA320872B]
    // </editor-fold> 
    public ChannelSplitter(int ch) {

        channel = ch;

    }

    /**
     * Gets the properties
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3BBBAAC0-DC77-4615-CE3F-F661B9114807]
    // </editor-fold> 
    protected String getProperties() {

        return "Splitting channel " + (channel == 4 ? 3 : channel);

    }

    /**
     * Splits the channel
     *
     * @param dt
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6717C5D0-4954-4572-1E7F-9D83FEEE676A]
    // </editor-fold> 
    protected void filterData(int[] dt, int w, int h) {

        byte[] data = new byte[dt.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) ((channel == CH1) ? (dt[i] >> 16) & 0xff
                    : (channel == CH2) ? (dt[i] >> 8) & 0xff : dt[i] & 0xff);
        }
        byte[] val = new byte[256];
        for (int i = 0; i < val.length; i++) {
            val[i] = (byte) i;
        }
        IndexColorModel mod = new IndexColorModel(8, 256, val, val, val);
        consumer.setColorModel(mod);
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Indicates that only the <code>ColorModel</code> is affected
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B1EDF37C-02AD-C382-8F8E-4B5011A371F3]
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
    // #[regen=yes,id=DCE.8AB2AC16-364E-C01B-E857-F1BD48F84A92]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        for (int i = 0; i < n; i++) {
            red[i] = (channel == CH1) ? red[i] : (channel == CH2) ? gre[i]
                    : blu[i];
        }
        return new IndexColorModel(8, n, red, red, red);

    }

    /**
     * No special filtering
     *
     * @param model
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.685E448A-3D83-BE35-A051-787EA361CB72]
    // </editor-fold> 
    protected boolean isSpecialModel(IndexColorModel model) {

        return false;

    }

    /**
     * No channel locks
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.45704A19-741A-D853-1347-FD6A2AF7FF13]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }
} // End ChannelSplitter