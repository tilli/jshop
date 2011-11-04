package jshop.filters.fade;

/**
 *  Fade modifier which takes the natural logarithm of the original value
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D76061AE-7E00-F90C-C945-6FE0EA50E90B]
// </editor-fold> 
public class Logarithm extends AbstractModifier {

    /**
     *  Creates a logarithmic fade modifier
     *  	 
     *  	 @param reversed
     *  	            reverses the fade
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.437AE753-9674-1671-1DBD-AA36ACCF07CA]
    // </editor-fold> 
    public Logarithm(boolean reversed) {

        super(reversed);

    }

    /**
     * Returns the natural logarithm of the original value
     *
     * @param value
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2B292FC0-5A95-3961-F50D-21AF57EB2236]
    // </editor-fold> 
    public double modify(double value) {

        return Math.log(value);

    }
} // End Logarithm