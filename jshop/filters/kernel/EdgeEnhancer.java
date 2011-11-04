package jshop.filters.kernel;

/**
 *  33 strong sharpening
 *   
 *   <pre>
 *       -1, -1, -1
 *       -1,  9, -1
 *       -1, -1, -1
 *       divided by 1
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.EA60D73F-3D47-8298-B190-898381ADFEEF]
// </editor-fold> 
public class EdgeEnhancer extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A31BDC74-54D3-1BE8-C56F-EAE3A156A20E]
    // </editor-fold> 
    private static final long serialVersionUID = 1736352851369714933L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.265081FD-4905-1921-7314-CEA9D03C02FB]
    // </editor-fold> 
    /**
     * 
     */
    public EdgeEnhancer() {

        super();
        int[] matrix = {-1, -1, -1, -1, 9, -1, -1, -1, -1};
        setData(matrix);
        setDimensions(3, 3);
        setOrigin(1, 1);

    }
} // End EdgeEnhancer