package jshop.filters.kernel;

/**
 *  55 averaging blur
 *   
 *   <pre>
 *       1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1
 *       divided by 25
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.DB08A401-09EB-32DB-13BC-2DFD875D1EA2]
// </editor-fold> 
public class Average5 extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4758336B-20C1-580E-FF9B-931E39C12A09]
    // </editor-fold> 
    private static final long serialVersionUID = -9030642671751101558L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1E428DEE-8DF0-885A-8A11-F67F87E3AC11]
    // </editor-fold> 
    /**
     * 
     */
    public Average5() {

        super();
        int[] matrix = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1};
        setData(matrix);
        setDimensions(5, 5);
        setOrigin(2, 2);
        setDivider(25);

    }
} // End Average5