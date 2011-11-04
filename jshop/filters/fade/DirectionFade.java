package jshop.filters.fade;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A4AC9029-72BC-FBE1-C609-8D3201DC0D44]
// </editor-fold> 
/**
 * 
 * @author icecom
 */
public class DirectionFade extends AbstractFade {

    /**
     *  Vertical fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.270946C2-216B-1E9A-9A4E-315312CA02A3]
    // </editor-fold> 
    public static final int VERTICAL = 0;
    /**
     *  Horizontal fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CDDB087C-96A4-67EA-AD24-DEB2DEFAF696]
    // </editor-fold> 
    public static final int HORIZONTAL = 1;
    /**
     *  Diagonal fade from top left to bottom right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0F6ED7AE-EA9B-EE4E-31C6-B691D08A5864]
    // </editor-fold> 
    public static final int TLDIAGONAL = 2;
    /**
     *  Diagonal fade from top right to bottom left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D13C90B2-2E16-7FE0-2B55-55E4F0C5B05E]
    // </editor-fold> 
    public static final int TRDIAGONAL = 3;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C949E118-3354-D259-FB1D-FA11105CB926]
    // </editor-fold> 
    private int direction = VERTICAL;

    /**
     *  Creates a horizontal fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2F0C2F05-991F-7B96-A1EB-EB702440CB9E]
    // </editor-fold> 
    public DirectionFade() {
    }

    /**
     *  Creates a new <code>DirectionFade</code>
     * @param direction 
     * @param amount
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ADAB5C8F-8BF4-DB75-8996-F7805C1C02B4]
    // </editor-fold> 
    public DirectionFade(int direction, double amount) {

        super(amount);
        this.direction = direction;

    }

    /**
     * Calculates the fade values
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F01003FC-75F7-63EB-F842-6EA49756BEC6]
    // </editor-fold> 
    protected double calculate(int x, int y, int w, int h, double amount) {

        double mod = 0;
        if (direction == VERTICAL) {
            mod = (double) (w - x) / (double) w + amount * 2 - 1;
        } else if (direction == HORIZONTAL) {
            mod = (double) (h - y) / (double) h + amount * 2 - 1;
        }
        return mod;

    }
} // End DirectionFade