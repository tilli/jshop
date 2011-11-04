/*
 * Tehty 3.10 1997
 */
package jshop.filters;

/**
 *  Class which encapsulates data that is needed to count fade ratios. Following
 *   results are produced with fade amount 50:
 *   <ul>
 *   <li>Horizontal fade is 1 at left side, 0 at center and 1 at right side.
 *   <li>Vertical fade is 1 at top and bottom and 0 at center
 *   <li>Square fade is the sum of horizontal and vertical divided by two
 *   <li>Circular fade is 0 at center and rises at equals speed in all directions.
 *   <li>Left-to-right is 0 at left and 1 at right.
 *   <li>Up-to-down is 0 at top and 1 at bottom.
 *   <li>Nodirection returns 0.5
 *   <li>Linear style means that the values change linearly between 0 and 1
 *   <li>Sqpow raises the value to second power before returning it.
 *   <li>Sqroot takes a square root of the value before returning it.
 *   </ul>
 *   The reversed-flag will reverse the values (1-value). Each unit of fade amount
 *   adds or subtracts 0.02 from returned fade values. If fade amount is 100, the
 *   returned value will always be 1. If amount is 0 value will always be 0
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F092F9CC-8E93-84F5-AE81-321B87EE346F]
// </editor-fold> 
public class Fade {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84A75084-E9E4-7DE7-6640-3CD6C3818103]
    // </editor-fold> 
    private int amount = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.525351AF-3EE4-21CB-9318-26369E8603A4]
    // </editor-fold> 
    private int direction = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D0BA2993-01AF-4344-8333-BFB6A90D37A0]
    // </editor-fold> 
    private int style = 0;
    /**
     *  Horizontal fade.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DAC58421-F45E-81E9-5E09-43D34DCEC26B]
    // </editor-fold> 
    public static final int HORIZONTAL = 0;
    /**
     *  Vertical fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.566E460F-CEFB-A8CE-91AE-1F3A03594CF3]
    // </editor-fold> 
    public static final int VERTICAL = 1;
    /**
     *  Circular fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AF35D2D4-8E2B-D58D-BC1D-87A517C3BBA7]
    // </editor-fold> 
    public static final int CIRCULAR = 2;
    /**
     *  Square fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.874AF3A8-F676-4239-3895-F4F9FC3FB65B]
    // </editor-fold> 
    public static final int SQUARE = 3;
    /**
     *  Fade from left to right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.078FE275-C6F6-B50C-AED4-AA15D5DE9CD5]
    // </editor-fold> 
    public static final int LEFTRIGHT = 4;
    /**
     *  Fade from top to bottom
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.959D6081-0BB6-84DB-2DA1-7A6B9DB4A25D]
    // </editor-fold> 
    public static final int UPDOWN = 5;
    /**
     *  Not really a fade, just a ratio
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0CD5E320-B380-A2E6-7D8B-8779492C7A4E]
    // </editor-fold> 
    public static final int NODIRECTION = 6;

    /**
     *  Counts the fade at given position. The value returned will be between 0
     *  	 and 1.
     *  	 
     *  	 @param width
     *  	            Width of the area
     *  	 @param height
     *  	            Height of the area
     *  	 @param x
     *  	            Current x-coordinate
     *  	 @param y
     *  	            Current y-coordinate
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FA09E967-9419-BD93-1D11-E8156F215E43]
    // </editor-fold> 
    public double countFade(int width, int height, int x, int y) {

        height -= 1;
        width -= 1;
        if (height == 0 || width == 0) {
            return 0;
        }
        double mod = 0;
        if (style == 7) {
            mod = (double) amount / 50 - Math.random();
            if (mod > 1) {
                mod = 1;
            }
            if (mod < 0) {
                mod = 0;
            }
            return mod;
        }
        if (direction == VERTICAL); else if (direction == HORIZONTAL) {
            mod = Math.abs((double) width / 2 - x) / ((double) width / 2) + (double) amount / 50.0 - 1;
        } else if (direction == SQUARE) {
            mod = (Math.abs((double) width / 2 - x) / ((double) width / 2) + (double) amount / 50.0 - 1 + Math.abs((double) height / 2 - y) / ((double) height / 2) + (double) amount / 50.0 - 1) / 2;
        } else if (direction == LEFTRIGHT) {
            mod = (double) (width - x) / (double) width + (double) amount / 50 - 1;
        } else if (direction == UPDOWN) {
            mod = (double) (height - y) / (double) height + (double) amount / 50 - 1;
        } else if (direction == CIRCULAR) {
            double hdiff = (double) height / 2 - y;
            double wdiff = (double) width / 2 - x;
            mod = hdiff * hdiff / ((double) (height * height) / 4) + wdiff * wdiff / ((double) (width * width) / 4) + (double) amount / 50.0 - 1;
        } else if (direction == NODIRECTION) {
            mod = (double) amount / 100;
        }

        if (mod < 0) {
            mod = 0;
        } else if (mod > 1) {
            mod = 1;
        }

        return 0;

    }
} // End Fade