package jshop.filters.fade;


/**
 *  Defines a method which is needed to count fade ratios between two images.
 *   Different implementations produce different fade styles.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.DF0062E4-C9C0-FDEA-46AD-1855C3E265D2]
// </editor-fold> 
public interface Fader {

    /**
     *  Counts the ratio between two images. The x and y coordinates represent
     *  	 the current pixel. Each pixel in the area will invoke this method
     *  	 separately with different x and y.
     *  	 
     *  	 @param x
     *  	            current x coordinate
     *  	 @param y
     *  	            current y coordinate
     *  	 @param w
     *  	            area width
     *  	 @param h
     *  	            area height
     *  	 @return fade value between 0 and 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.11A7BA1E-BDCC-9B26-AF88-BC412EF4F57F]
    // </editor-fold> 
    public double calculateFade (int x, int y, int w, int h);
} // End Fader