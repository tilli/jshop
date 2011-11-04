/*
 * 6.10. 1997
 */
package jshop.filters;

/**
 *  Filter which sets given values to pixels with specific values. Constructor
 *   takes 3 arrays of size 256 as arguments. If the channel value at position (x,
 *   y) == N, it will be replaced with array[N]. This filter will not handle
 *   indexed color models
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2F15851B-F8C4-FBC0-F030-A06E90043B1E]
// </editor-fold> 
public final class ColorValueSetter extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CD3E0592-7A70-FB99-676D-5F4839F71E83]
    // </editor-fold> 
    private static final long serialVersionUID = -2180085103333967182L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.972B4CF0-D632-64D6-F16C-5D0B0C5BECAD]
    // </editor-fold> 
    private byte[] chan1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B89B4B17-D961-C102-6B73-95D470666D61]
    // </editor-fold> 
    private byte[] chan2;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.77124430-C585-AD3E-545D-A4887BC04186]
    // </editor-fold> 
    private byte[] chan3;

    /**
     *  Creates a new <code>ColorValueSetter</code> with 0-array
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5C948BA7-6AEB-CB9D-4279-CB15FC796706]
    // </editor-fold> 
    public ColorValueSetter() {

        this(new byte[256], new byte[256], new byte[256]);

    }

    /**
     *  Creates a new <code>ColorValueSetter</code> with given data. If array
     *  	 size is not 256 a new array will be created
     *  	 
     *  	 @param ch1
     *  	            channel 1 pixel values
     *  	 @param ch2
     *  	            channel 2 pixel values
     *  	 @param ch3
     *  	            channel 3 pixel values
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9EEFA654-4BAC-22B8-6754-04A77552FB20]
    // </editor-fold> 
    public ColorValueSetter(byte[] ch1, byte[] ch2, byte[] ch3) {

        if (ch1.length != 256) {
            chan1 = new byte[256];
        } else {
            chan1 = ch1;
        }
        if (ch2.length != 256) {
            chan2 = new byte[256];
        } else {
            chan2 = ch2;
        }
        if (ch3.length != 256) {
            chan3 = new byte[256];
        } else {
            chan3 = ch3;
        }

    }

    /**
     *  Creates a new <code>ColorValueSetter</code> which has same data for all
     *  	 channels.
     * @param data
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5729ECFA-8B56-49DD-9451-6930AE5C1155]
    // </editor-fold> 
    public ColorValueSetter(byte[] data) {

        this(data, data, data);

    }

    /**
     *  Sets the given array to all channels
     * @param data
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1A12E60E-D93B-A31F-B773-E50E62BD7C48]
    // </editor-fold> 
    public void setData(byte[] data) {

        setData(data, data, data);

    }

    /**
     *  Sets the channel values.
     * @param ch1 
     * @param ch2
     * @param ch3
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.47A115B9-4F44-A790-D9EE-8C0F6A13601F]
    // </editor-fold> 
    public void setData(byte[] ch1, byte[] ch2, byte[] ch3) {

        if (ch1.length != 256) {
            chan1 = new byte[256];
        } else {
            chan1 = ch1;
        }
        if (ch2.length != 256) {
            chan2 = new byte[256];
        } else {
            chan2 = ch2;
        }
        if (ch3.length != 256) {
            chan3 = new byte[256];
        } else {
            chan3 = ch3;
        }

    }

    /**
     *  Gets the data of all channels
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3040EA91-EE79-6FAF-157A-AB2FC967E716]
    // </editor-fold> 
    public byte[][] getData() {

        return new byte[][]{chan1, chan2, chan3};

    }

    /**
     * True color filtering
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FAD4EAAD-718F-8694-FE26-AC76A14198EA]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        int row[] = new int[w];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int i = y * w + x;
                row[x] = (data[i] & 0xff000000) | ((chan1[(data[i] >> 16) & 0xff] & 0xff) << 16) | ((chan2[(data[i] >> 8) & 0xff] & 0xff) << 8) | (chan3[data[i] & 0xff] & 0xff);
            }
            passToConsumer(0, y, w, 1, row, 0, w);
        }

    }

    /**
     * Gray scale filtering. Values of the channel 1 constructor argument will
     * be used in the filtering
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D7B52445-AF35-A38C-4BB3-B327DAC45BCB]
    // </editor-fold> 
    protected void filterBytes(byte[] data, int w, int h) {

        for (int i = 0; i < data.length; i++) {
            data[i] = chan1[(data[i]) & 0xff];
        }
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }
} // End ColorValueSetter