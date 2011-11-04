package jshop.filters.kernel;


/**
 *  Kernel which is used to detect edges from an image. Complete edge gradient is
 *   obtained by taking horizontal and vertical gradients and adding them together
 *   using formula sqrt(GxGx+GyGy)
 *   
 *   <pre>
 *         Horizontal     Vertical      Top-left     Top-right  
 *       -10, -20, -10   -10  0  10     0 -10 -14    14  10   0 
 *         0,   0,   0   -20  0  20    10   0 -10    10   0 -10 
 *        10,  20,  10   -10  0  10    14  10   0     0 -10 -14 
 *       divided by 11-strength
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6DC65FDA-66F3-50A5-D6E8-E39B79AFE068]
// </editor-fold> 
public class EdgeDetector extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38CBC97F-048D-233C-8E5B-60C67388236B]
    // </editor-fold> 
    private static final long serialVersionUID = 6138844768912460302L;

    /**
     *  Horizontal lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38DCB583-9551-EC06-E2A7-CFE3851025CB]
    // </editor-fold> 
    public static final int HORIZONTAL = 1;

    /**
     *  Vertical lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1C6DF2BF-FC35-449A-E4C4-1F048BE5962B]
    // </editor-fold> 
    public static final int VERTICAL = 2;

    /**
     *  Diagonal from top left to bottom right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B90B644F-1758-D615-3BBD-8E93B09BA5F0]
    // </editor-fold> 
    public static final int TOPLEFT = 3;

    /**
     *  Diagonal from top right to bottom left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EA8C7FCC-478E-009D-2ED9-96AF00B48485]
    // </editor-fold> 
    public static final int TOPRIGHT = 4;

    /**
     *  Creates an horizontal strength 1 <code>EdgeDetector</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.150A8752-2D9D-FC6A-149C-EB2D525E3A05]
    // </editor-fold> 
    public EdgeDetector () {

		this(HORIZONTAL, 1, false);

	}

    /**
     *  Creates a new <code>EdgeDetctor</code>.
     *  	 
     *  	 @param direction
     *  	            direction of edges
     *  	 @param strength
     *  	            detection strength. Values range from 10 (noisy) to -1000
     *  	            (nothing detected)
     *  	 @param inverted
     *  	            multiplies the kernel by -1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4A792B4C-A68F-3BF4-28C9-B5BE77C1CB8B]
    // </editor-fold> 
    public EdgeDetector (int direction, int strength, boolean inverted) {

		if (strength > 10) {
			strength = 10;
		}
		if (strength < -1000) {
			strength = -1000;
		}
		int vl = 10 * (inverted ? -1 : 1);
		int vl2 = 14 * (inverted ? -1 : 1);

		if (direction == HORIZONTAL) {
			int[] matrix = { -vl, -2 * vl, -vl, 0, 0, 0, vl, 2 * vl, vl };
			setData(matrix);
		} else if (direction == VERTICAL) {
			int[] matrix = { -vl, 0, vl, -2 * vl, 0, 2 * vl, -vl, 0, vl };
			setData(matrix);
		} else if (direction == TOPLEFT) {
			int[] matrix = { 0, -vl, -vl2, vl, 0, -vl, vl2, vl, 0 };
			setData(matrix);
		} else {
			int[] matrix = { vl2, vl, 0, vl, 0, -vl, 0, -vl, -vl2 };
			setData(matrix);
		}
		setDimensions(3, 3);
		setOrigin(1, 1);
		setDivider(11 - strength);

	}
} // End EdgeDetector