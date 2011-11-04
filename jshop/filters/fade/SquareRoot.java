package jshop.filters.fade;

/**
 *  Fade modifier which takes the square root of the original value
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.1D9A80E1-5691-73A6-9D91-101F7B325024]
// </editor-fold> 
public class SquareRoot extends AbstractModifier {

    /**
     *  Creates a new square root fade modifier
     *  	 
     *  	 @param reversed
     *  	            reverses the fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BE99B056-8CD8-A0DB-18F4-605E05432A9A]
    // </editor-fold> 
    public SquareRoot(boolean reversed) {

        super(reversed);

    }

    /**
     * Returns the square root of the original value
     *
     * @param value 
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4245F919-ED3D-447F-E6AF-11757B159C9E]
    // </editor-fold> 
    public double modify(double value) {

        return Math.sqrt(value);

    }
} // End SquareRoot