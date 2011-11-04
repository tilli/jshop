package jshop.filters.kernel;


/**
 *  Kernel which is used to detect lines from an image
 *   
 *   <pre>
 *         Horizontal     Vertical      Top-left     Top-right        All
 *       -10, -10, -10   -10 20 -10    20 -10 -10   -10 -10  20   -10 -10 -10
 *        20,  20,  20   -10 20 -10   -10  20 -10   -10  20 -10   -10  80 -10
 *       -10, -10, -10   -10 20 -10   -10 -10  20    20 -10 -10   -10 -10 -10
 *       divided by 11-strength
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3E286965-C633-1120-11FE-4F5FF4CC19EA]
// </editor-fold> 
public class LineDetector extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.685DE186-34B4-6600-5A04-A02F338DDC82]
    // </editor-fold> 
    private static final long serialVersionUID = 1148868123527322764L;

    /**
     *  All edges
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BCF5EA96-D6A9-199E-6F41-E5478C183774]
    // </editor-fold> 
    public static final int ALL = 0;

    /**
     *  Horizontal lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AAABB33B-A370-5366-2680-3759F0686470]
    // </editor-fold> 
    public static final int HORIZONTAL = 1;

    /**
     *  Vertical lines
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E3B689EB-DA38-6784-EA04-A494B7DBB3F0]
    // </editor-fold> 
    public static final int VERTICAL = 2;

    /**
     *  Diagonal from top left to bottom right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F2B0C710-9643-51B4-E7C3-01EBA6E288E4]
    // </editor-fold> 
    public static final int TOPLEFT = 3;

    /**
     *  Diagonal from top right to bottom left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.879DEF80-0B06-34C0-BA56-C9A176193FD1]
    // </editor-fold> 
    public static final int TOPRIGHT = 4;

    /**
     *  Creates an all-detecting strength 1 <code>LineDetector</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D0AB9135-42F8-47EE-67DF-325A2855C411]
    // </editor-fold> 
    public LineDetector () {

		this(ALL, 1);

	}

    /**
     *  Creates a new <code>LineDetctor</code>.
     *  	 
     *  	 @param direction
     *  	            direction of lines
     *  	 @param strength
     *  	            detection strength. Values range from 10 (noisy) to -1000
     *  	            (almost nothing detected)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3D8AEE1E-42E2-8C9F-83E1-E28A19B8E72A]
    // </editor-fold> 
    public LineDetector (int direction, int strength) {

		if (strength > 10) {
			strength = 10;
		}
		if (strength < -1000) {
			strength = -1000;
		}

		if (direction == HORIZONTAL) {
			int[] matrix = { -10, -10, -10, 20, 20, 20, -10, -10, -10 };
			setData(matrix);
		} else if (direction == VERTICAL) {
			int[] matrix = { -10, 20, -10, -10, 20, -10, -10, 20, -10 };
			setData(matrix);
		} else if (direction == TOPLEFT) {
			int[] matrix = { 20, -10, -10, -10, 20, -10, -10, -10, 20 };
			setData(matrix);
		} else if (direction == TOPRIGHT) {
			int[] matrix = { -10, -10, 20, -10, 20, -10, 20, -10, -10 };
			setData(matrix);
		} else {
			int[] matrix = { -10, -10, -10, -10, 80, -10, -10, -10, -10 };
			setData(matrix);
		}
		setDimensions(3, 3);
		setOrigin(1, 1);
		setDivider(11 - strength);

	}
} // End LineDetector