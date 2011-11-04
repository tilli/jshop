package jshop.filters;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;

/**
 *  Implementation of the EasyFilter, which invokes the arithmetic image filters
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B3E2E931-4185-8A66-5EBD-6101CCA43D9C]
// </editor-fold> 
public final class Arithmetic implements EasyFilter {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FDB9C809-0A81-ADDA-F08B-72B182C5E71E]
    // </editor-fold> 
    private ImageProducer image;

    /**
     *  Creates a new Arithmetic EasyFilter
     * @param image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9FF02311-DBDE-37F9-944A-22CCC6255C29]
    // </editor-fold> 
    public Arithmetic(Image image) {

        this.image = image.getSource();

    }

    /**
     *  Runs the image through ColorChanger
     *  	 
     *  	 @param rop 
     * @param barg
     * @param bop
     * @param rarg 
     * @param gop
     * @param b
     * @param garg
     * @see ColorChanger#ColorChanger(int, int, int, double, double, double,
     *  	      boolean)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.21A100C7-96D4-FFA1-264C-9A60995C88A5]
    // </editor-fold> 
    public void channelArithmetic(int rop, int gop, int bop, double rarg, double garg, double barg, boolean b) {

        image = new FilteredImageSource(image, new ColorChanger(rop, gop, bop,
                rarg, garg, barg, b));

    }

    /**
     *  Runs the image through a PercentageChanger
     *  	 
     *  	 @param a1
     * @param a2
     * @param a3
     * @see PercentageChanger#PercentageChanger(int, int, int)
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7BAE46B9-1100-6F39-98C3-2B37F3DB7E96]
    // </editor-fold> 
    public void adjust(int a1, int a2, int a3) {

        image = new FilteredImageSource(image,
                new PercentageChanger(a1, a2, a3));

    }

    /**
     *  Runs the image through a ColorValueSetter
     *  	 
     *  	 @param data
     * @see ColorValueSetter#ColorValueSetter(short[])
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.739D77D0-9D2D-797E-06E9-6FD72703BD1C]
    // </editor-fold> 
    public void setPixels(short[] data) {
        // image = new FilteredImageSource(image, new ColorValueSetter(data));
    }

    /**
     *  Runs the image through a ColorValueSetter
     *  	 
     *  	 @param rdata 
     * @param bdata
     * @param gdata
     * @see ColorValueSetter#ColorValueSetter(short[], short[], short[])
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A774CA17-7648-E059-23DE-432531429FC5]
    // </editor-fold> 
    public void setPixels(short[] rdata, short[] gdata, short[] bdata) {
        // image = new FilteredImageSource(image, new ColorValueSetter(rdata,
        // gdata, bdata));
    }

    /**
     * Gets the filtered image
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EB7DCE75-66A0-69F4-0919-7976E1D63417]
    // </editor-fold> 
    public Image getFilteredImage() {

        return Toolkit.getDefaultToolkit().createImage(image);

    }
}