/*
 * 9.10. 1997
 */
package jshop.filters.colorspaces;

import java.awt.Color; 

/**
 *  Implementation of <code>ColorSpace</code> with empty methods. Subclasses
 *   should override the methods they need.
 *   
 *   @see ColorSpace
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.713F67D7-C754-CF3C-7508-9AA0AD98F1C9]
// </editor-fold> 
public class AbstractColorSpace implements ColorSpace {

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.00F5A8AD-EF92-587F-23AA-DD035378B1DD]
    // </editor-fold> 
    public int convertToRGB (int pixel) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D7DF1D84-3C51-3C4E-1AFE-9717D657161B]
    // </editor-fold> 
    public int convertToRGB (float[] channels) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4FFB89C9-71A0-E557-5013-63F2DF58C8C1]
    // </editor-fold> 
    public int convertToRGB (short pixel) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.205795F6-DE9A-0CB2-EB01-A8C6F8A9E866]
    // </editor-fold> 
    public float[] convertRGBToFloat (int rgb) {
		return new float[] { 0, 0, 0 };
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6818FC55-797C-5001-12B1-4998C65E4C2A]
    // </editor-fold> 
    public float[] convertRGBToFloat (Color c) {
		return new float[] { 0, 0, 0 };
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5C71EF48-96A5-6024-4D42-4C2B3A8EC2BB]
    // </editor-fold> 
    public short convertRGBToShort (int rgb) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.283B7004-9E7C-03C2-B659-CC1FFDDE90CF]
    // </editor-fold> 
    public short convertRGBToShort (Color c) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.946E5F51-661F-F298-67F2-FBBB12D0DAB8]
    // </editor-fold> 
    public int convertRGBToInt (int rgb) {
		return 0;
	}

	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.608A89CE-6877-07AA-F4AB-D4A36DC4B08A]
    // </editor-fold> 
    public int convertRGBToInt (Color c) {
		return 0;
	}
} // End AbstractColorSpace