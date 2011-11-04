/*
 * 30.9. 1997
 */
package jshop.filters;

import java.awt.image.IndexColorModel;

/**
 *  Class which performs arithmetic operations on pixels Applying these will
 *   usually end with funny results, especially when overflow is not prevented.
 *   Channel locks have no effect on this filter.
 *   <ul>
 *   <li>Channel values range from 0 to 255
 *   <li>All operations apply to each channel separately.
 *   </ul>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.068BE494-EF0B-62D1-6DFD-DC6C96BD5CB4]
// </editor-fold> 
public final class ColorChanger extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.83D15D4B-5E67-1035-20CF-3866ABF82F47]
    // </editor-fold> 
    private static final long serialVersionUID = -5162988810158793567L;
    /**
     *  Adds argument to channel value (channel + arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AD663D1A-C199-FC30-8CE7-DA5A77DBDEAA]
    // </editor-fold> 
    public static final int PIXEL_ADD_ARG = 0;
    /**
     *  Subtracts argument from channel value (channel - arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E8D0707B-40F3-915F-6AFE-19E419276C34]
    // </editor-fold> 
    public static final int PIXEL_SUB_ARG = 1;
    /**
     *  Multiplies channel by argument (channel arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.40FDA9AD-5B56-F66B-291B-BD77FD50789F]
    // </editor-fold> 
    public static final int PIXEL_MUL_ARG = 2;
    /**
     *  Divides channel by argument (channel / arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0F6B5647-8A06-EDC9-2FAD-5C59734732CA]
    // </editor-fold> 
    public static final int PIXEL_DIV_ARG = 3;
    /**
     *  Modulus (channel % arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.40A9D5BE-C500-2AAE-2614-7113D86692AC]
    // </editor-fold> 
    public static final int PIXEL_MOD_ARG = 4;
    /**
     *  Bitwise and (channel & arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.041C1FBE-348F-52F3-C83E-A525055B6E9A]
    // </editor-fold> 
    public static final int PIXEL_AND_ARG = 5;
    /**
     *  Bitwise or (channel | arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.01B2E988-FCEB-9911-B300-6F26DC3BACA0]
    // </editor-fold> 
    public static final int PIXEL_OR_ARG = 6;
    /**
     *  Bitwise xor (channel ^ arg)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.05873E7B-8BC5-C570-6FE0-61C4C3169D76]
    // </editor-fold> 
    public static final int PIXEL_XOR_ARG = 7;
    /**
     *  Bitwise not (~channel)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EFE9B5AC-2AB1-F8A4-5186-C2DFA8FE7A35]
    // </editor-fold> 
    public static final int PIXEL_NOT = 8;
    /**
     *  Subtracts channel value from given argument (arg - channel)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.25AE8B0D-1BA2-EABA-D05C-163EE99472A1]
    // </editor-fold> 
    public static final int ARG_SUB_PIXEL = 9;
    /**
     *  Divides argument by channel (arg / channel)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AB6A05DD-A417-190A-DC34-286AC37585F9]
    // </editor-fold> 
    public static final int ARG_DIV_PIXEL = 10;
    /**
     *  Modulus (arg % channel)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.63D9F8D6-2C98-AA24-12EF-6AD6A08F56FC]
    // </editor-fold> 
    public static final int ARG_MOD_PIXEL = 11;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8B061B78-1451-AC11-D8F8-D02E3532257C]
    // </editor-fold> 
    private int redop;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0A1A49EB-1554-D5D0-C929-F503D541AED4]
    // </editor-fold> 
    private int greenop;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.91ECF766-0D26-56AE-040D-DA6DF41F16D3]
    // </editor-fold> 
    private int blueop;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6D7E834F-1D01-2250-1B4A-BFF2B7B150B2]
    // </editor-fold> 
    private double redarg;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3A6CF4C9-F091-E312-46B6-E31B1456E06E]
    // </editor-fold> 
    private double greenarg;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A0E09639-0CAA-DE98-D4B0-0BD1EB816E0F]
    // </editor-fold> 
    private double bluearg;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FE57FBCF-328E-E3DC-A621-6BF0C023AB8A]
    // </editor-fold> 
    private boolean preventOverflow = false;

    /**
     *  Creates a new ColorChanger which performs the given operation with given
     *  	 argument to all channels without overflow prevention
     *  	 
     *  	 @param op
     *  	            See fields
     *  	 @param arg
     *  	            Argument for operation
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.443ED7B8-879D-7322-DFA6-56C05C2DA415]
    // </editor-fold> 
    public ColorChanger(int op, double arg) {

        this(op, op, op, arg, arg, arg, false);

    }

    /**
     *  Creates a new ColorChanger which performs the given operation to all
     *  	 channels
     *  	 
     *  	 @param op
     *  	            see fields
     *  	 @param arg
     *  	            argument for operation
     *  	 @param b
     *  	            prevents overflow.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B638A388-AC30-EF4D-66F3-F56CBB75FB07]
    // </editor-fold> 
    public ColorChanger(int op, double arg, boolean b) {

        this(op, op, op, arg, arg, arg, b);

    }

    /**
     *  Creates a new ColorChanger which performs the given operations with given
     *  	 arguments. If the image is grayscale, the channel 1 operation and
     *  	 argument will be used to perform the operation.
     *  	 
     *  	 @param rop
     *  	            operation on channel 1
     *  	 @param gop
     *  	            operation on channel 2
     *  	 @param bop
     *  	            operation on channel 3
     *  	 @param rarg
     *  	            argument for op1
     *  	 @param garg
     *  	            argument for op2
     *  	 @param barg
     *  	            argument for op3
     *  	 @param b
     *  	            overflow preventer
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.62E100B5-D681-B244-CA43-1EFD7583A7D3]
    // </editor-fold> 
    public ColorChanger(int rop, int gop, int bop, double rarg, double garg, double barg, boolean b) {

        redop = rop;
        greenop = gop;
        blueop = bop;
        redarg = rarg;
        greenarg = garg;
        bluearg = barg;
        preventOverflow = b;

    }

    /**
     *  If set, the values will not over or underflow
     * @param b
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8FB6ED5D-E466-63F4-0A96-7C195078A8FE]
    // </editor-fold> 
    public void setOverflowLock(boolean b) {

        preventOverflow = b;

    }

    /**
     *  Gets the status of the overflow lock
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.531BEA21-D3C4-985A-7850-9E903273355A]
    // </editor-fold> 
    public boolean getOverflowLock() {

        return preventOverflow;

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4A91B645-F72D-CB2B-80F0-F1704E68DDDF]
    // </editor-fold> 
    private int doOperation(int channel, int op, double arg) {

        switch (op) {
            case PIXEL_ADD_ARG:
                channel += (int) arg;
                break;
            case PIXEL_SUB_ARG:
                channel -= (int) arg;
                break;
            case ARG_SUB_PIXEL:
                channel = (int) arg - channel;
                break;
            case PIXEL_MOD_ARG:
                if (arg > 1) {
                    channel %= (int) arg;
                }
                break;
            case ARG_MOD_PIXEL:
                if (channel > 0) {
                    channel = (int) arg % channel;
                }
                break;
            case PIXEL_AND_ARG:
                channel &= (int) arg;
                break;
            case PIXEL_OR_ARG:
                channel |= (int) arg;
                break;
            case PIXEL_XOR_ARG:
                channel ^= (int) arg;
                break;
            case PIXEL_NOT:
                channel = ~channel;
                break;
            case PIXEL_MUL_ARG:
                channel = (int) (arg * channel);
                break;
            case PIXEL_DIV_ARG:
                if (arg != 0) {
                    channel = (int) (channel / arg);
                }
                break;
            case ARG_DIV_PIXEL:
                if (channel != 0) {
                    channel = (int) (arg / channel);
                }
                break;
        }
        return channel;

    }

    /**
     * Integer operations
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.68E77C9B-56D1-48F5-48D8-B8E28A350629]
    // </editor-fold> 
    protected void filterData(int[] data, int w, int h) {

        for (int i = 0; i < data.length; i++) {
            int red = doOperation((data[i] >> 16) & 0xff, redop, redarg);
            int green = doOperation((data[i] >> 8) & 0xff, greenop, greenarg);
            int blue = doOperation(data[i] & 0xff, blueop, bluearg);
            if (preventOverflow) {
                red = red > 255 ? 255 : red < 0 ? 0 : red;
                green = green > 255 ? 255 : green < 0 ? 0 : green;
                blue = blue > 255 ? 255 : blue < 0 ? 0 : blue;
            }
            data[i] = (data[i] & 0xff000000) | ((red << 16) & 0xff0000) | ((green << 8) & 0xff00) | (blue & 0xff);
        }
        passToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Grayscale operations
     *
     * @param data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.740E21CF-A42C-0ED8-7CCF-A790F636A038]
    // </editor-fold> 
    protected void filterBytes(byte[] data, int w, int h) {

        for (int i = 0; i < data.length; i++) {
            int val = doOperation(data[i] & 0xff, redop, redarg);
            if (preventOverflow) {
                val = val > 255 ? 255 : val < 0 ? 0 : val;
            }
            data[i] = (byte) val;
        }
        passBytesToConsumer(0, 0, w, h, data, 0, w);

    }

    /**
     * Color model filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.07819BB3-27C8-FC3A-A682-6D76F6DFD44D]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        int n = model.getMapSize();
        byte[] red = new byte[n], gre = new byte[n], blu = new byte[n];
        model.getReds(red);
        model.getGreens(gre);
        model.getBlues(blu);
        int re, gr, bl;
        for (int i = 0; i < n; i++) {
            re = doOperation(red[i] & 0xff, redop, redarg);
            gr = doOperation(gre[i] & 0xff, greenop, greenarg);
            bl = doOperation(blu[i] & 0xff, blueop, bluearg);
            if (preventOverflow) {
                re = re > 255 ? 255 : re < 0 ? 0 : re;
                gr = gr > 255 ? 255 : gr < 0 ? 0 : gr;
                bl = bl > 255 ? 255 : bl < 0 ? 0 : bl;
            }
            red[i] = (byte) re;
            gre[i] = (byte) gr;
            blu[i] = (byte) bl;
        }
        return new IndexColorModel(8, n, red, gre, blu);

    }

    /**
     * Gets the string representation of the properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7394DD8-5D36-C157-D65B-594AEC19F682]
    // </editor-fold> 
    protected String getProperties() {

        return opToString(redop, redarg) + ", " + opToString(greenop, greenarg) + ", " + opToString(blueop, bluearg) + ", Overflow = " + !preventOverflow;

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.27E14EE4-33A3-E18D-20C5-C6DD49C34C9B]
    // </editor-fold> 
    private String opToString(int op, double arg) {

        switch (op) {
            case PIXEL_ADD_ARG:
                return "CH + " + (int) arg;
            case PIXEL_SUB_ARG:
                return "CH - " + (int) arg;
            case ARG_SUB_PIXEL:
                return (int) arg + " - CH";
            case PIXEL_MOD_ARG:
                if (arg > 1) {
                    return "CH % " + (int) arg;
                } else {
                    return "Illegal argument";
                }
            case ARG_MOD_PIXEL:
                return (int) arg + "% CH";
            case PIXEL_AND_ARG:
                return "CH & " + (int) arg;
            case PIXEL_OR_ARG:
                return "CH | " + (int) arg;
            case PIXEL_XOR_ARG:
                return "CH ^ " + (int) arg;
            case PIXEL_NOT:
                return "~CH";
            case PIXEL_MUL_ARG:
                return "CH * " + arg;
            case PIXEL_DIV_ARG:
                if (arg != 0) {
                    return "CH / " + arg;
                } else {
                    return "Illegal argument";
                }
            case ARG_DIV_PIXEL:
                return arg + " / CH";
        }
        return "";

    }

    /**
     * Only the color model is affected.
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.96109899-2746-2AE3-522D-AC1CB16D2D2E]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return true;

    }

    /**
     * Unaffected by the channel locks
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.180AC2FE-8AE5-4821-D6A2-CE001635B236]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }
} // End ColorChanger