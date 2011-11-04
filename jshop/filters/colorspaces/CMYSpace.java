package jshop.filters.colorspaces;

/**
 *  Translates pixels beween CMY and RGB.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2261177C-AFC1-E259-C46B-928E03EAAD63]
// </editor-fold> 
public class CMYSpace extends AbstractColorSpace {

    /**
     *  Creates a new CMYSpace
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CC7DBFFA-A9A7-E89B-9F8F-63E3E1E50882]
    // </editor-fold> 
    public CMYSpace() {
    }

    /**
     * Converts the given RGB to CMY
     *
     * @param rgb
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85B37C1A-6F5E-B6C4-8DA0-05E5924EBBC3]
    // </editor-fold> 
    public int convertRGBToInt(int rgb) {

        return (rgb & 0xff000000) | (~rgb & 0xffffff);

    }

    /**
     * Converts the given CMY to RGB
     *
     * @param cmy
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0495C9A7-068E-A92A-9808-9237933BA9F2]
    // </editor-fold> 
    public int convertToRGB(int cmy) {

        return (cmy & 0xff000000) | (~cmy & 0xffffff);

    }

    /**
     * Gets the string representation
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6E12CBE6-6FD2-59BA-5727-8BC4A81E920B]
    // </editor-fold> 
    public String toString() {

        return "CMY";

    }
} // End CMYSpace