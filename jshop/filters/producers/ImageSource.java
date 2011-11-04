package jshop.filters.producers;

import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.Hashtable;

/**
 *  Implementation of both <code>ImageProducer</code> and
 *   <code>ImageConsumer</code>. This class provides the framework for classes
 *   which define the methods needed to produce and consume certain types of
 *   images. The <code>filterImage</code> method is used to create a new
 *   <code>ImageSource</code> object which has been filtered through the given
 *   <code>ImageFilter</code>.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.614238BF-6251-59DD-6901-CFC6E83B75CA]
// </editor-fold> 
public class ImageSource implements ImageProducer, ImageConsumer {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3ED2D8BA-4F6E-2CE5-218D-7262B70E653E]
    // </editor-fold> 
    private Object data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.49D9BA25-546C-00CE-55AC-D3DB8F4ADFA2]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1B6FFBA8-7045-911E-9B7E-D7C43115A6C7]
    // </editor-fold> 
    private int height;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7CFAB46C-0166-2BC3-54A7-1D35CC7784B5]
    // </editor-fold> 
    private ColorModel model;

    /**
     *  Creates a new <code>ImageSource</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.38E8FEFB-26B3-DD57-3D00-389B7C63DAFA]
    // </editor-fold> 
    public ImageSource() {
    }

    /**
     *  Creates a new <code>ImageSource</code> from an <code>ImageProducer</code>
     * @param ip
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5BB9B883-69C0-F58C-2FDA-7D1D9806F76E]
    // </editor-fold> 
    public ImageSource(ImageProducer ip) {
        ip.startProduction(this);
    }

    /**
     *  Creates a new <code>ImageSource</code> from the given data
     *  	 
     *  	 @param data 
     * @param w
     * @param model
     * @param h
     * @excepton IllegalArgumentException If data is not byte[] or int[]
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.85A4D791-6E51-4994-7D90-94512646EB19]
    // </editor-fold> 
    public ImageSource(Object data, int w, int h, ColorModel model) {
        if (!(data instanceof int[]) && !(data instanceof byte[])) {
            throw new IllegalArgumentException("Must be byte or integer array");
        }
        this.data = data;
        width = w;
        height = h;
        if (model == null) {
            this.model = ColorModel.getRGBdefault();
        } else {
            this.model = model;
        }
    }

    /**
     *  Returns a new <code>ImageSource</code> which has been filtered through
     *  	 the filter. This method calls the <code>createNewInstance</code> of the
     *  	 subclass to determine the class of the new <code>ImageSource</code>
     * @param filter
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EDE8D28F-575E-B914-01C8-AED0AA6B3F3B]
    // </editor-fold> 
    public final ImageSource filterImage(ImageFilter filter) {
        ImageSource is = new ImageSource();
        filter = filter.getFilterInstance(is);
        this.startProduction(filter);
        return is;
    }

    /**
     * Sets the dimensions. This calls the <code>resizeData</code> of the
     * subclass
     *
     * @param w
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8BD1F9D9-08BC-F05F-5BED-21648E16F65B]
    // </editor-fold> 
    public void setDimensions(int w, int h) {
        width = w;
        height = h;
    }

    /**
     *  Gets the color model
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7B785325-F096-AD4F-D369-09E2D9BFF678]
    // </editor-fold> 
    public ColorModel getColorModel() {
        return model;
    }

    /**
     * Hints have no effect
     *
     * @param hints
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C8BEE852-B97B-7EB7-E5D5-B3AAF59FC258]
    // </editor-fold> 
    public void setHints(int hints) {
    }

    /**
     * No properties recorded
     *
     * @param h
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.84B005E7-74B2-92CD-04D0-94C8C2B30CBE]
    // </editor-fold> 
    public void setProperties(Hashtable h) {
    }

    /**
     * Sets the color model
     *
     * @param c
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C17C4AE0-5E79-AD27-85C5-7F5ABEF48076]
    // </editor-fold> 
    public void setColorModel(ColorModel c) {
        model = c;
    }

    /**
     * Sets byte data to this producer
     *
     * @param sc
     * @param d
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9F29C59D-2001-6E79-3BD5-0FB419E75FA8]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel model, byte[] d, int off, int sc) {
        if (data == null) {
            data = new byte[width * height];
        }
        for (int dy = y, sy = 0; dy < y + h; dy++, sy++) {
            System.arraycopy(d, sy * sc, data, dy * width + x + off, sc);
        }
    }

    /**
     * Sets integer data to this producer
     *
     * @param sc
     * @param d
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F0054708-FE32-387F-1634-41E73A0BAC0B]
    // </editor-fold> 
    public synchronized void setPixels(int x, int y, int w, int h, ColorModel model, int[] d, int off, int sc) {
        if (data == null) {
            data = new int[width * height];
        }
        for (int dy = y, sy = 0; dy < y + h; dy++, sy++) {
            System.arraycopy(d, sy * sc, data, dy * width + x + off, sc);
        }
    }

    /**
     * Invoked after image has been delivered
     *
     * @exception ImageException
     *                if errors occurred
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E707F69D-B581-732A-8C4E-8A5A24FC6156]
    // </editor-fold> 
    public synchronized void imageComplete(int status) {
        if (status == IMAGEERROR) {
            throw new ImageException();
        }
    }

    /**
     * Starts the production
     *
     * @param c
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4C2E7DC7-1AFD-057D-CE5A-40FEA9B27F15]
    // </editor-fold> 
    public synchronized void addConsumer(ImageConsumer c) {
        startProduction(c);
    }

    /**
     * Does nothing
     *
     * @param c
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DF21B606-B9EF-B7E5-B22B-DD1D8F8EDB9C]
    // </editor-fold> 
    public void removeConsumer(ImageConsumer c) {
    }

    /**
     * Does nothing
     *
     * @param c
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CE0116A1-4A04-87A4-F515-BAD1567B9BB8]
    // </editor-fold> 
    public void requestTopDownLeftRightResend(ImageConsumer c) {
    }

    /**
     * Returns false.
     *
     * @param c
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B3CEDC7B-53AF-9203-5943-12402A1EA6E3]
    // </editor-fold> 
    public boolean isConsumer(ImageConsumer c) {
        return false;
    }

    /**
     * Starts the production. The pixels will be sent in a single pass.
     *
     * @param consumer
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.867FC9BD-BA21-22FB-D0D3-FA74FB805A0E]
    // </editor-fold> 
    public synchronized void startProduction(ImageConsumer consumer) {
        try {
            long l = System.currentTimeMillis();
            consumer.setColorModel(model);
            consumer.setHints(ImageConsumer.SINGLEPASS);
            consumer.setDimensions(width, height);
            if (data instanceof int[]) {
                consumer.setPixels(0, 0, width, height, model, (int[]) data, 0,
                        width);
            } else {
                consumer.setPixels(0, 0, width, height, model, (byte[]) data,
                        0, width);
            }
            consumer.imageComplete(STATICIMAGEDONE);
            System.out.println(System.currentTimeMillis() - l);
        } catch (Exception e) {
            e.printStackTrace();
        // consumer.imageComplete(IMAGEERROR);
        }
    }

    /**
     *  Gets the image data
     *  	 
     *  	 @return Image data in byte or integer array.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.75B9F8E2-9C7D-6BC9-99AB-533E204F3510]
    // </editor-fold> 
    public Object getData() {
        return data;
    }

    /**
     *  Gets the image width
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.557AE3BD-E642-EC6B-6137-E5C1095AC856]
    // </editor-fold> 
    public int getWidth() {
        return width;
    }

    /**
     *  Gets the image height
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B40A3A96-1A8F-CBCE-4DED-C51C1451C688]
    // </editor-fold> 
    public int getHeight() {
        return height;
    }

    /**
     *  Sets new data to this <code>ImageSource</code>.
     * @param data
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.97081F23-3E97-8756-B907-2348987768FE]
    // </editor-fold> 
    protected void setData(Object data) {
        this.data = data;
    }
}
