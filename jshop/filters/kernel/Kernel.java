package jshop.filters.kernel;

import java.io.Serializable;

/**
 *  Represents a convolution kernel used in conjunction with
 *   <code>ConvolutionFilter</code>
 *   
 *   @author Timo Ohtonen
 *   @see jshop.filters.ConvolutionFilter
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.32322D98-5DB7-039F-C11E-EB3F318352E9]
// </editor-fold> 
public class Kernel implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.647C6BD5-8B88-59AA-8197-9E2E71CC1AAC]
    // </editor-fold> 
    private static final long serialVersionUID = -8125914978058449098L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.44F7CE98-2154-6238-EB50-8D6A37B39665]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EBC0145A-CD9D-0FD4-4005-3FF35B4133CC]
    // </editor-fold> 
    private int height;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.63366B7D-C9A4-681A-B63B-EB788EC98070]
    // </editor-fold> 
    private int xorigin;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CD0A878D-B3DE-F332-73CC-B86AD7C09578]
    // </editor-fold> 
    private int yorigin;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.825322C2-5234-2378-D64A-D4FF939A56E9]
    // </editor-fold> 
    private int[] data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9F7A4EDF-82B9-2636-90DD-E3D0E7E3F02D]
    // </editor-fold> 
    private int divider;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1890AC5B-CEAB-6AB8-CE7C-FC5AFDDB7D9B]
    // </editor-fold> 
    private int roffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.93AFE167-E3AD-D2B4-E747-E35D2ED050A4]
    // </editor-fold> 
    private int goffset;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5D19CD40-EB47-15D3-08B4-8CC6167FCC99]
    // </editor-fold> 
    private int boffset;

    /**
     *  Creates an empty <code>Kernel</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9E1FC277-2748-C194-B586-EF23B427D434]
    // </editor-fold> 
    public Kernel() {

        this(1, 1, 0, 0, new int[]{1}, 1);

    }

    /**
     *  Creates a new <code>Kernel</code>.
     *  	 
     *  	 @exception IllegalArgumentException
     *  	                if data-array size is not width height, if width or height
     *  	                is zero or negative, xorigin or yorigin is out of bounds
     *  	                or divider is 0
     *  	 @param width
     *  	            kernel width
     *  	 @param height
     *  	            kernel height
     *  	 @param xorigin
     *  	            x-coordinate iof the origin, starting from 0
     *  	 @param yorigin
     *  	            y-coordinate
     *  	 @param data
     *  	            kernel data
     *  	 @param divider
     *  	            modifier which divides each integer in the kernel.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7AB8727-561E-B3DB-ECCD-53976C340046]
    // </editor-fold> 
    public Kernel(int width, int height, int xorigin, int yorigin, int[] data, int divider) {

        if (data == null || data.length != width * height) {
            throw new IllegalArgumentException("Illegal array size");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Negative dimension");
        }
        if (xorigin < 0 || xorigin >= width || yorigin < 0 || yorigin >= height) {
            throw new IllegalArgumentException("Illegal origin");
        }
        if (divider == 0) {
            throw new IllegalArgumentException("Zero divider");
        }
        this.width = width;
        this.height = height;
        this.xorigin = xorigin;
        this.yorigin = yorigin;
        this.data = data;
        this.divider = divider;

    }

    /**
     *  Creates a <code>Kernel</code> with origin at center.
     * @param width 
     * @param height 
     * @param data
     * @param divider
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.49C8D53F-01EC-EE1D-7042-E723557DCF18]
    // </editor-fold> 
    public Kernel(int width, int height, int[] data, int divider) {

        this(width, height, width % 2 == 0 ? width / 2 - 1 : width / 2,
                height % 2 == 0 ? height / 2 - 1 : height / 2, data, divider);

    }

    /**
     *  Sets the dimensions.
     *  	 
     *  	 @param w
     *  	            kernel width
     *  	 @param h
     *  	            kernel height
     *  	 @exception IllegalArgumentException
     *  	                on negative or zero dimension
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3A04A239-C878-DA32-9D55-9C46DC186024]
    // </editor-fold> 
    public void setDimensions(int w, int h) {

        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Illegal dimension");
        }
        width = w;
        height = h;

    }

    /**
     *  Sets the divider
     *  	 
     *  	 @param div
     * @exception IllegalArgumentException
     *  	                when divider is zero
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.22B96763-9881-7193-F1A2-7CDAE4B75DAD]
    // </editor-fold> 
    public void setDivider(int div) {

        if (divider == 0) {
            throw new IllegalArgumentException("Zero divider");
        }
        divider = div;

    }

    /**
     *  Sets the origin of the kernel.
     *  	 
     *  	 @param x 
     * @param y
     * @exception IllegalArgumentException
     *  	                when coordinate is negative
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.43B5E716-EB11-FEA9-25EC-2E93C70F2E52]
    // </editor-fold> 
    public void setOrigin(int x, int y) {

        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Illegal origin");
        }
        xorigin = x;
        yorigin = y;

    }

    /**
     *  Sets the kernel data
     * @param data
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.215E0531-4D80-4CC7-112D-C05D98A040CF]
    // </editor-fold> 
    public void setData(int[] data) {

        this.data = data;

    }

    /**
     *  Sets the channel offsets. These are added to channel values after
     *  	 filtering
     * @param ch1
     * @param ch2
     * @param ch3 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.046F8AD3-1F76-97FC-D352-C1623AC7F18B]
    // </editor-fold> 
    public void setOffsets(int ch1, int ch2, int ch3) {

        roffset = ch1;
        goffset = ch2;
        boffset = ch3;

    }

    /**
     *  Gets the kernel data
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6904716E-5CC0-349A-278D-A24A955A9760]
    // </editor-fold> 
    public int[] getData() {

        return data;

    }

    /**
     *  Gets the width
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2221CD9-D237-DFE6-EC5D-50ED8D10144C]
    // </editor-fold> 
    public int getWidth() {

        return width;

    }

    /**
     *  Gets the height
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.705D8242-CDF8-4ECD-F8BF-B7714CBE49D8]
    // </editor-fold> 
    public int getHeight() {

        return height;

    }

    /**
     *  Gets the divider
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A2B32F01-4568-B3B2-118B-35C987481D58]
    // </editor-fold> 
    public int getDivider() {

        return divider;

    }

    /**
     *  Gets the x-coordinate of the origin
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E4050E0B-1074-B37A-4A56-B603E2247EF2]
    // </editor-fold> 
    public int getOriginX() {

        return xorigin;

    }

    /**
     *  Gets the y-coordinate of the origin
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.94E5AC3A-E6A1-525C-8D52-B97CD44F421C]
    // </editor-fold> 
    public int getOriginY() {

        return yorigin;

    }

    /**
     *  Channel 1 offset. Offsets are added to channel values after the operation
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.767B89BE-60C9-10E6-9A88-A7B78AF0162A]
    // </editor-fold> 
    public int getOffset1() {

        return roffset;

    }

    /**
     *  Channel 2 offset.
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C8A677EF-4179-8B4C-FEA9-ADEB9BEAD344]
    // </editor-fold> 
    public int getOffset2() {

        return goffset;

    }

    /**
     *  Channel 3 offset
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.120ECBA7-9B7E-25F4-0D85-517BB1105884]
    // </editor-fold> 
    public int getOffset3() {

        return boffset;

    }
} // End Kernel