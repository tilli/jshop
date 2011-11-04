/*
 * 9.10. 1997
 */
package jshop.filters.colorspaces;

import java.awt.Color;

/**
 *  Defines methods needed to translate rgb values to other color spaces and
 *   back.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.FFA2514E-D265-5884-C5A9-857CCF026F05]
// </editor-fold> 
public interface ColorSpace {

    /**
     *  Converts an integer pixel to RGB
     * @param pixel
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9E1AA2D8-ECB1-F1DD-4F48-292838434944]
    // </editor-fold> 
    public int convertToRGB(int pixel);

    /**
     *  Converts an array of floats to RGB
     * @param channels 
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.21EF4E18-28C5-6BB5-B837-F87A10A3B715]
    // </editor-fold> 
    public int convertToRGB(float[] channels);

    /**
     *  Converts a short to RGB
     * @param pixel
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04D6ABDC-381A-4C74-76CA-16E9AF1A839F]
    // </editor-fold> 
    public int convertToRGB(short pixel);

    /**
     *  Converts RGB to float-array
     * @param rgb 
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1EF91BB7-3050-AAFF-1924-7C94EE53CA98]
    // </editor-fold> 
    public float[] convertRGBToFloat(int rgb);

    /**
     *  Converts a <code>Color</code> to float array
     * @param c
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8427D5C9-A2F9-C5C6-40CD-C4473497AE5F]
    // </editor-fold> 
    public float[] convertRGBToFloat(Color c);

    /**
     *  Converts RGB to short
     * @param rgb
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A6949B1E-5D47-055A-C5C7-3ECAD549B67D]
    // </editor-fold> 
    public short convertRGBToShort(int rgb);

    /**
     *  Converts a <code>Color</code> to short
     * @param c 
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.46DDA18E-CC74-C109-BACF-6406C7335229]
    // </editor-fold> 
    public short convertRGBToShort(Color c);

    /**
     *  Converts RGB to int
     * @param rgb
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB02AD89-2065-9D4F-7DB4-476C56B112A9]
    // </editor-fold> 
    public int convertRGBToInt(int rgb);

    /**
     *  Converts a <code>Color</code> to int
     * @param c
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.985EFC08-A414-0149-FD7C-C08EFA3EBD9C]
    // </editor-fold> 
    public int convertRGBToInt(Color c);
} // End ColorSpace