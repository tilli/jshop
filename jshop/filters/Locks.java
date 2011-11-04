package jshop.filters;

import java.awt.image.ImageFilter;
import java.io.Serializable;

import jshop.filters.colorspaces.ColorSpace;

/**
 *  Abstract subclass of ImageFilter which contains the status of the color
 *   channel locks and current color space. <code>setColorSpace(</code><i>null</i>
 *   <code>)</code> will set RGB color space. <br>
 *   Filtering in any other than RGB-space will take longer because the bytes have
 *   to be converted from RGB to something and then back.<br>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.8B0F26F3-D68E-8009-A58C-FCBDED5340AE]
// </editor-fold> 
public abstract class Locks extends ImageFilter implements Serializable {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7A799202-B695-0A3D-78B7-84B0477BC0C8]
    // </editor-fold> 
    private static final long serialVersionUID = 4832595365627387261L;
    /**
     *  Channel 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9D69362A-B242-DFED-F51D-D558C0BDB2E4]
    // </editor-fold> 
    public static final int CH1 = 4;
    /**
     *  Channel 2
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C164207D-F855-8D4D-7859-02292C353904]
    // </editor-fold> 
    public static final int CH2 = 2;
    /**
     *  Channel 3
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3B7403CD-9B8E-4577-DA05-2F431F66BB53]
    // </editor-fold> 
    public static final int CH3 = 1;
    /**
     *  All channels.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7C8AE299-CC76-4F97-AEF6-E09747DA98BE]
    // </editor-fold> 
    public static final int ALL = 7;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3F9E4793-0A8B-68B8-965E-3AB870FCFC04]
    // </editor-fold> 
    private int locks = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FEC7DA57-BB56-E6CE-6844-3271E239C12F]
    // </editor-fold> 
    private ColorSpace space = null;

    /**
     *  Sets or removes channel locks. This does nothing if
     *  	 <code>usesLocks</code> returns false.
     * @param channels
     * @param b
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9B5E5B25-F89D-8FAF-5077-12298F425A75]
    // </editor-fold> 
    public void setLocks(int channels, boolean b) {

        if (!usesLocks()) {
            return;
        }
        if (b) {
            locks |= channels;
        } else {
            locks &= ~channels;
        }

    }

    /**
     *  Gets the status of a channel lock
     * @param channel 
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8603C14A-4E0C-49D5-9478-76BD91D2869B]
    // </editor-fold> 
    public boolean getLock(int channel) {

        if (!usesLocks()) {
            return false;
        }
        return (locks & channel) != 0;

    }

    /**
     *  Gets the status of all locks
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0A03C5D1-EAB7-21FB-7D73-1C24BA1024BC]
    // </editor-fold> 
    public int getLocks() {

        if (!usesLocks()) {
            return 0;
        }
        return locks;

    }

    /**
     *  Sets a new color space. This does nothing if <code>usesSpaces</code>
     *  	 returns false.
     * @param cp
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E64B2EE7-CFB9-9CBD-8FD7-119496961BBD]
    // </editor-fold> 
    public void setColorSpace(ColorSpace cp) {

        if (!usesSpaces()) {
            return;
        }
        space = cp;

    }

    /**
     *  Gets the current color space
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4A728592-EF0C-B320-21F1-178616A22CFB]
    // </editor-fold> 
    public ColorSpace getColorSpace() {

        if (!usesSpaces()) {
            return null;
        }
        return space;

    }

    /**
     * Gets the string representation
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0D3B9D37-C4C2-9623-B3DB-D54F61C773B1]
    // </editor-fold> 
    public String toString() {

        return getClass().getName() + "[[" + getProperties() + "]" + (usesLocks() ? lockString() : "") + (usesSpaces() ? spaceString() : "") + "]";

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.43072D61-D923-127C-849F-720A55C1A820]
    // </editor-fold> 
    private String lockString() {

        String s = "00" + Integer.toBinaryString(locks);
        s = s.replace('0', 'F');
        s = s.replace('1', 'T');
        return "[Locks: " + s.substring(s.length() - 3) + "]";

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0B502E67-370A-21C8-8DC1-8FE7AD696061]
    // </editor-fold> 
    private String spaceString() {

        if (space == null) {
            return "[ColorSpace: RGB]";
        }
        return "[ColorSpace: " + space.toString() + "]";

    }

    /**
     *  Returns a string representation of the filters properties.
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F3D9A177-35B2-0A17-32EF-A6995D4D44B8]
    // </editor-fold> 
    protected String getProperties() {

        return "";

    }

    /**
     *  Determines if the filter is affected by the channel locks. Default is
     *  	 true.
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8D1A4377-F401-4F5E-64B7-D4B50E645914]
    // </editor-fold> 
    protected boolean usesLocks() {

        return true;

    }

    /**
     *  Determines if the filter is affected by color space convertions. Default
     *  	 is true.
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C23A00D8-D3B2-9910-0357-195F0236EE3A]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return true;

    }
} // End Locks