package jshop.filters;

import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.ArrayList;

/**
 *  Similar to awt.image.FIS but this class keeps no track to the original
 *   ImageProducer. Image data is stored in an integer array
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.DFC1A9A5-8E73-58D7-CE5F-D4A5C201CADB]
// </editor-fold> 
public class FilteredImageSource implements ImageProducer {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8186F92D-B0CC-EA57-4659-EABB6BE2BD7A]
    // </editor-fold> 
    private int[] data;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0C60FBC3-6ED8-B45A-4144-F3E06EA54EB4]
    // </editor-fold> 
    private int width;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.548FC9F6-B3AB-AA47-C089-2E94FFA9CC7F]
    // </editor-fold> 
    private int height;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.25E924BF-CF22-33E4-74CA-A62B3EE57911]
    // </editor-fold> 
    private ArrayList<ImageConsumer> consumers = new ArrayList<ImageConsumer>();

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7CBA0E19-1BFD-A961-BA39-569B59AEE82F]
    // </editor-fold> 
    /**
     * 
     * @param ip
     * @param filter
     */
    public FilteredImageSource(ImageProducer ip, ImageDataCollector filter) {

        ip.startProduction(filter);

    // data = filter.getData();
    // height = filter.getSize().height;
    // width = filter.getSize().width;
    }

    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6B674038-43EF-7748-6D38-067BEE1384F5]
    // </editor-fold> 
    public void addConsumer(ImageConsumer c) {

        startProduction(c);

    }

    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.26DC877B-00BD-BDF0-AD41-0BB2B71BDC86]
    // </editor-fold> 
    public void removeConsumer(ImageConsumer c) {

        consumers.remove(c);

    }

    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.17EFAA66-9D8A-632C-2682-D8C7BF215474]
    // </editor-fold> 
    public void requestTopDownLeftRightResend(ImageConsumer c) {
    }

    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.44C0838B-920A-4257-C32E-C1C28589C469]
    // </editor-fold> 
    public boolean isConsumer(ImageConsumer c) {

        return consumers.contains(c);

    }

    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.FE42DF27-C5DC-B78C-7159-3300D9275B8C]
    // </editor-fold> 
    public void startProduction(ImageConsumer consumer) {

        if (consumers.contains(consumer)) {
            return;
        }
        try {
            consumers.add(consumer);
            ColorModel rgb = ColorModel.getRGBdefault();
            consumer.setHints(ImageConsumer.TOPDOWNLEFTRIGHT);
            consumer.setColorModel(rgb);
            consumer.setDimensions(width, height);
            consumer.setPixels(0, 0, width, height, rgb, data, 0, width);
            consumer.imageComplete(ImageConsumer.STATICIMAGEDONE);
        } catch (Exception e) {
            consumer.imageComplete(ImageConsumer.IMAGEERROR);
        }

    }
}