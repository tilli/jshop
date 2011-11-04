package jshop.filters.colorspaces;

import java.awt.Color;

/**
 *  Uses methods in <code>Color</code>-class to convert pixels between HSB and
 *   RGB
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B9D3825E-3CAF-C7CB-ECAF-999F1A7EF4C2]
// </editor-fold> 
public class HSBSpace extends AbstractColorSpace {

    /**
     *  Creates a new <code>HSBSpace</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B9BB89D4-F4DA-FFE7-7C18-094C80EA1A4E]
    // </editor-fold> 
    public HSBSpace() {
    }

    /**
     * Converts a HSB pixel to RGB pixel
     *
     * @param pixel
     *            HSB pixel in 0xaahhssbb format
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F49DF7E5-2EF0-A93D-2615-46C7ABC9A7FA]
    // </editor-fold> 
    public int convertToRGB(int pixel) {

        float[] f = new float[3];
        f[0] = (float) ((pixel >> 16) & 0xff) / 255;
        f[1] = (float) ((pixel >> 8) & 0xff) / 255;
        f[2] = (float) (pixel & 0xff) / 255;
        return (pixel & 0xff000000) | (Color.HSBtoRGB(f[0], f[1], f[2]) & 0xffffff);

    }

    /**
     * Converts a RGB pixel to HSB pixel
     *
     * @param rgb
     * @return HSB-pixel in 0xaahhssbb format
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.35993E63-CC77-9036-EAC6-8A2244B733C8]
    // </editor-fold> 
    public int convertRGBToInt(int rgb) {

        float[] f = new float[3];
        Color.RGBtoHSB((rgb >> 16) & 0xff, (rgb >> 8) & 0xff, rgb & 0xff, f);
        return (rgb & 0xff000000) | ((int) (f[0] * 255) << 16) | ((int) (f[1] * 255) << 8) | (int) (f[2] * 255);

    }

    /**
     * Converts a RGB pixel to HSB float array
     *
     * @param rgb
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4ADFA83F-1F6F-29BA-10FA-4E836A2A31B3]
    // </editor-fold> 
    public float[] convertRGBToFloat(int rgb) {

        return Color.RGBtoHSB((rgb >> 16) & 0xff, (rgb >> 8) & 0xff,
                rgb & 0xff, null);

    }

    /**
     * Converts a <code>Color</code> to HSB pixel
     *
     * @param c
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7434410C-9863-3E4F-26FA-7E2F2B32433B]
    // </editor-fold> 
    public float[] convertRGBToFloat(Color c) {

        return Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

    }

    /**
     * Converts a HSB float array to RGB pixel
     *
     * @param f 
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.18EB2798-9BE5-55C3-4C8A-1779A49CAE1C]
    // </editor-fold> 
    public int convertToRGB(float[] f) {

        return Color.HSBtoRGB(f[0], f[1], f[2]);

    }

    /**
     * Gets the string representation
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D2649A60-81D4-71E8-BEDF-07387D6AE84E]
    // </editor-fold> 
    public String toString() {

        return "HSB";

    }
} // End HSBSpace