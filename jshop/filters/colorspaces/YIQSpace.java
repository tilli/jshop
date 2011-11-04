package jshop.filters.colorspaces;

/**
 *  Translates pixels between YIQ and RGB Not working correctly.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6B142E05-3FBA-37EC-4794-E182FA5EC6AE]
// </editor-fold> 
public class YIQSpace extends AbstractColorSpace {

    /**
     *  Creates a new YIQSpace
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85603CE7-A81D-65B5-B356-3CAB2F60EAC6]
    // </editor-fold> 
    public YIQSpace() {
    }

    /**
     * Converts the given RGB to YIQ
     *
     * @param rgb 
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.70DF9C80-F4B1-D1F9-AA06-36C2DD8CD77A]
    // </editor-fold> 
    public int convertRGBToInt(int rgb) {

        int y = (int) (((rgb >> 16) & 0xff) * .299 + ((rgb >> 8) & 0xff) * .587 + (rgb & 0xff) * .114);
        int i = (int) (((rgb >> 16) & 0xff) * .596 + ((rgb >> 8) & 0xff) * -.275 + (rgb & 0xff) * -.321);
        int q = (int) (((rgb >> 16) & 0xff) * .212 + ((rgb >> 8) & 0xff) * -.523 + (rgb & 0xff) * .311);
        return (rgb & 0xff000000) | (y << 16) | (i << 8) | q;

    }

    /**
     * Converts the given YIQ to RGB
     *
     * @param yiq
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2CAFE637-DBBD-8A43-3A2C-3E7492115C85]
    // </editor-fold> 
    public int convertToRGB(int yiq) {

        int r = (int) (((yiq >> 16) & 0xff) + ((yiq >> 8) & 0xff) * .956 + (yiq & 0xff) * .621);
        int g = (int) (((yiq >> 16) & 0xff) + ((yiq >> 8) & 0xff) * -.272 + (yiq & 0xff) * -.647);
        int b = (int) (((yiq >> 16) & 0xff) + ((yiq >> 8) & 0xff) * -1.106 + (yiq & 0xff) * -1.703);
        return (yiq & 0xff000000) | (r << 16) | (g << 8) | b;

    }

    /**
     * Gets the string representation
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.658A1B4B-0353-892C-71CB-5D179BC43831]
    // </editor-fold> 
    public String toString() {

        return "YIQ";

    }
} // End YIQSpace