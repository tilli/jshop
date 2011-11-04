package jshop.filters.kernel;

/**
 *  33 gaussian blur.
 *   
 *   <pre>
 *       1, 2, 1
 *       2, 4, 2
 *       1, 2, 1
 *       divided by 16
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4D26796B-0D25-615E-BA03-B073DC69E0F2]
// </editor-fold> 
public class Gauss3 extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E90933E3-56DB-92EB-5D8C-F08CDC231DF0]
    // </editor-fold> 
    private static final long serialVersionUID = -5797805811355122245L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AF244DF1-22AC-819E-85C6-5EA3810F0D91]
    // </editor-fold> 
    /**
     * 
     */
    public Gauss3() {

        super();
        int[] matrix = {1, 2, 1, 2, 4, 2, 1, 2, 1};
        setData(matrix);
        setDimensions(3, 3);
        setOrigin(1, 1);
        setDivider(16);

    }
} // End Gauss3