package jshop.filters;

/**
 *  Class which is used to resize and resample the original image
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.E01C35BD-EDEE-4635-0166-59D7EC40DB3A]
// </editor-fold> 
public class ImageResizer extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8998941C-2C62-2C23-5C2B-583635867604]
    // </editor-fold> 
    private static final long serialVersionUID = -3420929423346998341L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AB42E576-822B-E9AB-9759-28A513586472]
    // </editor-fold> 
    private int dw;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.137D2531-8BD4-3EBB-CAC7-A188B24EC60F]
    // </editor-fold> 
    private int dh;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7CC6BEA9-B283-69AC-E2B5-63435AEF6FFD]
    // </editor-fold> 
    private int style;
    /**
     *  Resamples the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0B17B123-81CD-BB5F-B2ED-8B90A9088D46]
    // </editor-fold> 
    public static final int RESAMPLE = 0;
    /**
     *  resizes the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C2185B80-A88A-CAC0-43BA-D5B0D1372BEA]
    // </editor-fold> 
    public static final int RESIZE = 1;

    /**
     * No locks
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BF7DF1A6-3CB0-91BC-9992-F93CA52FF3A9]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }

    /**
     * Always RGB
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6E245016-4A67-33F9-8C79-AE5080838CE7]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }

    /**
     *  Creates a new ImageResizer
     *  	 
     *  	 @param width
     *  	            New width
     *  	 @param height
     *  	            New height
     *  	 @param style
     *  	            Resize / resample
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7499044C-58A8-E3FB-A418-B49D2449C23D]
    // </editor-fold> 
    public ImageResizer(int width, int height, int style) {

        dw = width;
        dh = height;
        this.style = style;

    }

    /**
     * Filters the data
     *
     * @param data
     * @param width 
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B10930FB-9BAA-6A85-B843-D1CD77ED0D53]
    // </editor-fold> 
    protected void filterData(int[] data, int width, int height) {

        if (style == RESIZE) {
            resize(data, width, height);
        } else {
            resample(data, width, height);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.21CC16BE-5A7F-7C59-FAA9-28C2859E3E0A]
    // </editor-fold> 
    private void resize(int[] data, int width, int height) {

        System.out.println(width + " " + height + " " + dw + " " + dh);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.601333AA-F8F0-AF64-148F-33DA3C1F298F]
    // </editor-fold> 
    private void resample(int[] data, int width, int height) {

        System.out.println(width + " " + height + " " + dw + " " + dh);

    }
} // End ImageResizer