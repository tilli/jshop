package jshop.filters;

import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import jshop.filters.colorspaces.ColorSpace;

/**
 *  Collects image data to an array which is processed by the subclass. There are
 *   four possible ways to process the data:
 *   <ul>
 *   <li>If the original data is integer data, <code>filterData</code> method is
 *   used to filter it. In that case subclass should use
 *   <code>passToConsumer</code> to pass filtered data to consumer. Subclasses
 *   should not alter the original data during filtering or else the channel locks
 *   will not work properly. If a channel is locked, changing the size of the
 *   image might result in <code>ArrayIndexOutOfBoundsException</code>
 *   
 *   <li>If <code>isSpecialModel</code> returns true, <code>filterBytes</code> is
 *   invoked. This means basically gray scale images, where there is a possibility
 *   to perform convolution etc. operations even if the colors are indexed. This
 *   should call <code>passBytesToConsumer</code> to pass filtered data to the
 *   consumer. If it returns false <code>isFilteringColorModel</code> is checked.
 *   
 *   <li>If <code>isFilteringColorModel</code> returns false (default),
 *   <code>filterIndexed</code> is invoked. This method should also call
 *   <code>passBytesToConsumer</code> to pass filtered data to consumer.
 *   
 *   <li>If <code>isFilteringColorModel</code> returns true, the
 *   <code>filterColorModel</code> is invoked. This method returns a new
 *   <code>IndexColorModel</code> and the main data stays untouched.
 *   </ul>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.918FC3A5-76B6-C62F-2803-9EAE6030C35E]
// </editor-fold> 
public abstract class ImageDataCollector extends Locks {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.88A317B9-DC72-5D39-4188-E2ECBC9B13F1]
    // </editor-fold> 
    private static final long serialVersionUID = 5064996414444901744L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F1C04B3C-E700-3050-7E8A-CAB6159FCB6B]
    // </editor-fold> 
    private Object data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.675B5076-BA3C-EA54-A205-D74730DF622B]
    // </editor-fold> 
    private ColorModel model;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7F84E414-2EE0-4B43-FB9F-3001678418B5]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C3A8E6BF-6071-7C8E-BD25-508FBF2C5CE0]
    // </editor-fold> 
    private int height;

    /**
     * Sets the colormodel of the consumer. If image data is in byte-array, the
     * color model must be instance of <code>IndexColorModel</code>.
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.523CB6F0-4267-AC1E-4F5E-0932E550CDA3]
    // </editor-fold> 
    public synchronized void setColorModel(ColorModel model) {

        this.model = model;
        consumer.setColorModel(model);

    }

    /**
     * Passes the hints to the consumer. If SINGLEPASS is turned on, it is
     * removed
     *
     * @param hints
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D714B3DC-45B4-9F51-C960-C247EB93041E]
    // </editor-fold> 
    public synchronized void setHints(int hints) {

        if ((hints & SINGLEPASS) != 0) {
            consumer.setHints(hints &= ~SINGLEPASS);
        } else {
            consumer.setHints(hints);
        }

    }

    /**
     * Sets the dimensions of the image consumer and creates the int-array
     *
     * @param w
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4F99DBDE-F287-F0B9-7C67-D4259F13B73B]
    // </editor-fold> 
    public synchronized void setDimensions(int w, int h) {

        width = w;
        height = h;
        consumer.setDimensions(w, h);

    }

    /**
     * Collects the pixels to an array. If the color model is not RGB default
     * pixels will be converted. If the color space is not RGB, the pixels will
     * be converted to current color space. <code>passToConsumer</code> will
     * convert the pixels back to RGB after filtering.
     *
     * @param offset
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BA6341BD-25BA-75BD-88ED-1FE70D6F6704]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int offset, int scansize) {

        if (data == null) {
            data = new int[width * height];
        }
        int[] dat = (int[]) data;
        if (model != ColorModel.getRGBdefault()) {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = model.getRGB(pixels[i]);
            }
        }
        ColorSpace cs = getColorSpace();
        if (cs != null) {
            int soffset = 0;
            for (int cy = y; cy < h + y; cy++) {
                for (int cx = x; cx < w + x; cx++) {
                    dat[offset + width * cy + cx] = cs.convertRGBToInt(pixels[soffset++]);
                }
            }
        } else {
            for (int cy = y, sy = 0; sy < h; cy++, sy++) {
                System.arraycopy(pixels, sy * scansize, dat, offset + cy * width + x, scansize);
            }
        }

    }

    /**
     * Sets the pixels to a byte-array
     *
     * @param offset
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C2845F32-C44F-B955-F041-1E65348D43AE]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int offset, int scansize) {

        if (data == null) {
            data = new byte[width * height];
        }
        byte[] dat = (byte[]) data;
        for (int cy = y, sy = 0; sy < h; cy++, sy++) {
            System.arraycopy(pixels, sy * scansize, dat, offset + cy * width + x, scansize);
        }

    }

    /**
     *  Filters integer data. The color model is rgb default
     *  	 
     *  	 @param pix
     *  	            image data
     *  	 @param w
     *  	            image width
     *  	 @param h
     *  	            image height
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B126BCA9-E228-2783-0A67-D74312175EF0]
    // </editor-fold> 
    protected void filterData(int[] pix, int w, int h) {

        consumer.setPixels(0, 0, w, h, model, pix, 0, w);

    }

    /**
     *  Filters indexed byte data. Basically this restricts filtering to
     *  	 single-pixel operations.
     *  	 
     *  	 @param pix
     *  	            image data
     *  	 @param w
     *  	            image width
     *  	 @param h
     *  	            image height
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9553DB28-C8EB-9E14-14F6-D1024B93944B]
    // </editor-fold> 
    protected void filterIndexed(byte[] pix, int w, int h) {

        consumer.setPixels(0, 0, w, h, model, pix, 0, w);

    }

    /**
     *  Filtering alters only the contents of the color model
     *  	 
     *  	 @param model
     *  	            color model which will be filtered
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A8A0DBB2-1A07-6BE5-0400-76F8115B66A0]
    // </editor-fold> 
    protected IndexColorModel filterColorModel(IndexColorModel model) {

        return model;

    }

    /**
     *  Filters byte data. This data is not restiricted to single-pixel
     *  	 operations
     *  	 
     *  	 @param pix
     *  	            image data
     *  	 @param w
     *  	            image width
     *  	 @param h
     *  	            image height
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5CF78389-1114-04FB-6D37-D29CE0EC51F1]
    // </editor-fold> 
    protected void filterBytes(byte[] pix, int w, int h) {

        consumer.setPixels(0, 0, w, h, model, pix, 0, w);

    }

    /**
     *  Converts the pixels back to RGB space (if needed) and passes them to the
     *  	 consumer.
     * @param x 
     * @param y
     * @param w
     * @param h
     * @param pixels
     * @param offset
     * @param scansize
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A78CB206-825B-9AA0-6658-01FD504894FE]
    // </editor-fold> 
    protected void passToConsumer(int x, int y, int w, int h, int[] pixels, int offset, int scansize) {

        if (getLocks() != 0) {
            boolean ch1 = getLock(CH1);
            boolean ch2 = getLock(CH2);
            boolean ch3 = getLock(CH3);
            int[] dt = (int[]) data;
            for (int dy = y, sy = 0; dy < y + h; dy++, sy++) {
                for (int dx = x, sx = 0; dx < x + w; dx++, sx++) {
                    int sind = sy * w + sx;
                    int dind = dy * width + dx;
                    pixels[sind] = (pixels[sind] & 0xff000000) | (ch1 ? dt[dind] & 0xff0000
                            : pixels[sind] & 0xff0000) | (ch2 ? dt[dind] & 0xff00 : pixels[sind] & 0xff00) | (ch3 ? dt[dind] & 0xff : pixels[sind] & 0xff);
                }
            }
        }
        ColorSpace cs = getColorSpace();
        if (cs != null) {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = cs.convertToRGB(pixels[i]);
            }
        }
        consumer.setPixels(x, y, w, h, model, pixels, offset, scansize);

    }

    /**
     *  Byte filters should call this to pass the pixels to consumer
     * @param x 
     * @param off
     * @param y
     * @param w
     * @param h
     * @param pix
     * @param sc
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.281D35AD-135F-BD2C-D8E0-64D74CC7FE2A]
    // </editor-fold> 
    protected void passBytesToConsumer(int x, int y, int w, int h, byte[] pix, int off, int sc) {

        consumer.setPixels(x, y, w, h, model, pix, off, sc);

    }

    /**
     * Invokes the current filter method. This means <code>filterData</code>,
     * <code>filterBytes</code>, <code>filterIndexed</code> or
     * <code>filterColorModel</code> depending on the previous method calls.
     * Also notifies the consumer after image is filtered
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AD244F2B-FF38-52B9-E185-05BE986CB77B]
    // </editor-fold> 
    public synchronized void imageComplete(int status) {

        if (status == IMAGEERROR) {
            consumer.imageComplete(status);
            return;
        }
        if (data instanceof int[]) {
            filterData((int[]) data, width, height);
        } else if (isSpecialModel((IndexColorModel) model)) {
            filterBytes((byte[]) data, width, height);
        } else if (isFilteringColorModel()) {
            IndexColorModel mod = filterColorModel((IndexColorModel) model);
            consumer.setColorModel(mod);
            consumer.setPixels(0, 0, width, height, mod, (byte[]) data, 0,
                    width);
        } else {
            filterIndexed((byte[]) data, width, height);
        }
        consumer.imageComplete(status);

    }

    /**
     *  If the byte-filter only alters the contents of the
     *  	 <code>ColorModel</code> this should return true. In that case
     *  	 <code>filterColorModel</code> should be overridden. If not, subclass
     *  	 should override <code>filterIndexed</code>. By default this returns true.
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9DF5DA5A-46E4-EE93-5787-F17BB21A4526]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return true;

    }

    /**
     *  Checks the given color model. Default implementation tests if the color
     *  	 model is a direct gray scale from 0 to 255 (0 black, 255 white). If this
     *  	 method returns true, <code>filterBytes</code> is called instead of
     *  	 <code>filterIndexed</code> or <code>filterColorModel</code>
     * @param mod
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6177BEF7-AD38-1DBE-578B-463DB74EDF2B]
    // </editor-fold> 
    protected boolean isSpecialModel(IndexColorModel mod) {

        for (int i = 0; i < 255; i++) {
            if ((mod.getRGB(i) & 0xffffff) != ((i << 16) | (i << 8) | i)) {
                return false;
            }
        }
        return true;

    }

    /**
     *  Gets the color model
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.76B7B07B-1294-D4EF-CB81-F6BB690F1E0A]
    // </editor-fold> 
    public ColorModel getColorModel() {

        return model;

    }
} // End ImageDataCollector