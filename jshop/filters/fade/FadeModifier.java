package jshop.filters.fade;


/**
 *  Location-independent fade modifiers
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.CDFEE610-1084-360E-6CBD-5FC46B0C1ADE]
// </editor-fold> 
public interface FadeModifier {

    /**
     *  Performs an operation on the existing value and returns the result
     *  	 
     *  	 @param value
     *  	            original fade value
     *  	 @return modified fade value between 0 and 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.738B0D0B-2D85-0E5F-8965-E21F93185CD8]
    // </editor-fold> 
    public double modifyFade (double value);
} // End FadeModifier