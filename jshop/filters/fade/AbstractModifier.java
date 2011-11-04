package jshop.filters.fade;

/**
 *  Abstract implementation of the <code>FadeModifier</code> which add the
 *   possibility of fade reversal. Reversed fade is the original subtracted from 1
 *   
 *   @see FadeModifier
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.BD42119C-88ED-1B78-90BE-8E10FD92E45E]
// </editor-fold> 
public abstract class AbstractModifier implements FadeModifier {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8A021BB9-28FE-F843-65D6-B48DC5C4DBA3]
    // </editor-fold> 
    private boolean reversed = false;

    /**
     *  Creates an unreversed <code>AbstractModifier</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8BF5B252-D38C-BE7D-3EAD-1F640A51EDFA]
    // </editor-fold> 
    public AbstractModifier() {
    }

    /**
     *  Creates an <code>AbstractModifier</code>.
     *  	 
     *  	 @param reversed
     *  	            if set, the fade value is reversed
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.58AE19AA-A2E0-7F85-E6F7-08538390349E]
    // </editor-fold> 
    public AbstractModifier(boolean reversed) {

        this.reversed = reversed;

    }

    /**
     *  Sets the reversed flag
     * @param b 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.630670FC-27DF-AE17-0B9A-3D92EBB02AB9]
    // </editor-fold> 
    public void setReversed(boolean b) {

        reversed = b;

    }

    /**
     *  Gets the reversed flag
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EC481361-BDF4-0D5F-C2D0-24ED5DA338D3]
    // </editor-fold> 
    public boolean getReversed() {

        return reversed;

    }

    /**
     * Invokes <code>modify</code>. If reversed-flag is on, the value is
     * subtracted from 1 before it is returned.
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7622FD95-F2B8-323B-1CC1-0D6784FF654A]
    // </editor-fold> 
    public double modifyFade(double value) {

        if (reversed) {
            return 1 - modify(value);
        } else {
            return modify(value);
        }

    }

    /**
     *  Performs the actual modifications
     * @param val
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7D8FFB3A-EAE1-C96C-5527-FD30976BA90A]
    // </editor-fold> 
    public abstract double modify(double val);
}