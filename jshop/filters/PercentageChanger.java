package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Filter which adjusts color values by given percentages.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.918B5428-9303-591C-A528-C5D889954283]
// </editor-fold> 
public final class PercentageChanger extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F577F1ED-5864-ADD8-4F37-B2A2D64F6FA0]
    // </editor-fold> 
    private static final long serialVersionUID = 2493062783904587409L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2E5432F-8C6F-26B6-783F-4E800DF9EDC6]
    // </editor-fold> 
    private double arg1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D334A780-B0C1-38E7-BCE1-7482C58ED53B]
    // </editor-fold> 
    private double arg2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.25986393-5093-CA73-2CCB-E95D13F06556]
    // </editor-fold> 
    private double arg3;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.319D2D0D-F9DB-ABE8-9B1B-23AFF935CE09]
    // </editor-fold> 
    private int a1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F2834E1A-38CD-06D7-4936-73A451CB2B39]
    // </editor-fold> 
    private int a2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3659C48E-A006-092A-9776-347A1A904229]
    // </editor-fold> 
    private int a3;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.062DD24E-F55C-D513-CFBE-98C2D6F81186]
    // </editor-fold> 
    private boolean preventer = true;

    /**
     *  Creates a <code>PercentageChanger</code> which does nothing
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CD3893F1-53E8-838B-F7F1-1F4966BC2DF2]
    // </editor-fold> 
    public PercentageChanger() {

        this(0, 0, 0);

    }

    /**
     *  Creates a new <code>PercentageChanger</code>. Arguments should range from
     *  	 -100 to 100.
     *  	 
     *  	 @param a1
     *  	            channel 1 modifier
     *  	 @param a2
     *  	            channel 2 modifier
     *  	 @param a3
     *  	            channel 3 modifier
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.17678050-211B-7C06-09FD-A96EECC4463C]
    // </editor-fold> 
    public PercentageChanger(int a1, int a2, int a3) {

        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        arg1 = a1 > 100 ? 100 : a1 < -100 ? -100 : a1;
        arg2 = a2 > 100 ? 100 : a2 < -100 ? -100 : a2;
        arg3 = a3 > 100 ? 100 : a3 < -100 ? -100 : a3;
        arg1 /= 100;
        arg2 /= 100;
        arg3 /= 100;

    }

    /**
     *  Sets the channel modifiers
     * @param a1 
     * @param a2 
     * @param a3
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D4812751-3AD6-BE0E-BF52-130DD200D50F]
    // </editor-fold> 
    public void setValues(int a1, int a2, int a3) {

        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        arg1 = a1 > 100 ? 100 : a1 < -100 ? -100 : a1;
        arg2 = a2 > 100 ? 100 : a2 < -100 ? -100 : a2;
        arg3 = a3 > 100 ? 100 : a3 < -100 ? -100 : a3;
        arg1 /= 100;
        arg2 /= 100;
        arg3 /= 100;

    }

    /**
     *  Sets all channel modifiers to <code>val</code>
     * @param val
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3CA61CC7-84F9-D4C2-6841-E8F273A316F4]
    // </editor-fold> 
    public void setValues(int val) {

        setValues(val, val, val);

    }

    /**
     *  Gets the channel modifiers
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7063EF07-2451-5C4E-4863-A310CA8DA82F]
    // </editor-fold> 
    public int[] getValues() {

        return new int[]{(int) (a1 * 100 + 0.1), (int) (a2 * 100 + 0.1),
                    (int) (a3 * 100 + 0.1)};

    }

    /**
     *  Sets the overflow preventer. Overflow preventer prevents the channel
     *  	 values from going over 255. It is turned on by default
     * @param b
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BC39C4AC-2ECD-C808-6E5A-E74444102A2E]
    // </editor-fold> 
    public void setOverflowPreventer(boolean b) {

        preventer = b;

    }

    /**
     *  Gets the overflow preventer
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.66AD5E17-7993-FCFB-2188-B93236FA979E]
    // </editor-fold> 
    public boolean getOverflowPreventer() {

        return preventer;

    }

    /**
     * Locks have no effect
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9D2B0EDA-6F24-6F85-03D5-F571BF0B75A1]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }

    /**
     * Only the <code>ColorModel</code> is affected by filtering
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0939326A-7891-84FB-EC4F-C3A9E765CE1D]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return true;

    }

    /**
     * Gets the string representation of the properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0537740E-2D77-F4F1-1BAC-6EF95EE9BE8F]
    // </editor-fold> 
    protected String getProperties() {

        return "Channel 1: " + a1 + "%, Channel 2: " + a2 + "%, Channel 3: " + a3 + "%";

    }

    /**
     * Filters the pixels
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3AD8DB04-ADED-89B6-43E3-9B992515B142]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        int rd, gr, bl;
        for (int i = 0; i < data.length; i++) {
            rd = (int) (((data[i] >> 16) & 0xff) * (1 + arg1));
            gr = (int) (((data[i] >> 8) & 0xff) * (1 + arg2));
            bl = (int) ((data[i] & 0xff) * (1 + arg3));
            if (preventer) {
                rd = rd > 255 ? 255 : rd;
                gr = gr > 255 ? 255 : gr;
                bl = bl > 255 ? 255 : bl;
            }
            data[i] = (data[i] & 0xff000000) | (rd << 16) | (gr << 8) | bl;
        }
        passToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Color model filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5435FB11-A91C-092F-DFFD-A9C22EE34A17]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        int val;
        for (int i = 0; i < n; i++) {
            val = (int) (red[i] * (1 + arg1));
            red[i] = preventer ? (byte) (val > 255 ? 255 : val) : (byte) val;
            val = (int) (gre[i] * (1 + arg1));
            gre[i] = preventer ? (byte) (val > 255 ? 255 : val) : (byte) val;
            val = (int) (blu[i] * (1 + arg1));
            blu[i] = preventer ? (byte) (val > 255 ? 255 : val) : (byte) val;
        }
        return new IndexColorModel(8, n, red, gre, blu);

    }

    /**
     * Gray scale filtering. This uses the argument of channel 1 to adjust the
     * values
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AF2D35E2-F422-AF22-63D3-46AF900D0BD1]
    // </editor-fold> 
    protected void filterBytes(byte[] data, int w, int h) {

        int val;
        for (int i = 0; i < data.length; i++) {
            val = (int) ((data[i] & 0xff) * (1 + arg1));
            if (preventer) {
                val = val > 255 ? 255 : val;
            }
            data[i] = (byte) val;
        }
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }
} // End PercentageChanger