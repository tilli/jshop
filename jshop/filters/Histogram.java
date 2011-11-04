package jshop.filters;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;

// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.191DFB16-01E0-D57C-57A1-DEA204AA1954]
// </editor-fold> 
/**
 * 
 * @author icecom
 */
public class Histogram implements EasyFilter {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.10CC8810-EBE5-7B8F-A03F-984B6C903F68]
    // </editor-fold> 
    private ImageProducer image;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.15AE334B-D516-5A00-0446-A2A7C42FEC37]
    // </editor-fold> 
    /**
     *
     * @param image
     */
    public Histogram(Image image) {

        this.image = image.getSource();

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DA4398A2-FB33-0922-A70F-B9E14F2045F0]
    // </editor-fold> 
    /**
     *
     */
    public void equalize() {

        HistogramEqualizer he = new HistogramEqualizer();
        image = new FilteredImageSource(image, he);

    }

    /**
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.89FA8052-07B5-DD9F-A956-2C17673B4929]
    // </editor-fold> 
    public Image getFilteredImage() {

        return Toolkit.getDefaultToolkit().createImage(image);

    }
}