package jshop.filters;

import java.awt.Image;

/**
 *  Defines the interface for filters that return the filtered image through
 *   <code>getFilteredImage</code>-method EasyFilter implementations defined in
 *   this package are used with images which have the
 *   jshop.filters.producers.ImageSource as their ImageProducer.
 *   
 *   @see jshop.filters.producers.ImageSource
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.10BCF93C-35B7-590D-2A58-B7767CF7059A]
// </editor-fold> 
public interface EasyFilter {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E1D241FD-F2A4-821C-0FDE-CBC674F12915]
    // </editor-fold> 
    /**
     * 
     * @return
     */
    public Image getFilteredImage();
}