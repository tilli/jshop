package jshop.filters.kernel;

/**
 *  77 averaging blur
 *   
 *   <pre>
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       1, 1, 1, 1, 1, 1, 1
 *       divided by 49
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.3EBAFFD7-8984-F959-7099-13E7F8158543]
// </editor-fold> 
public class Average7 extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6673AEDA-9A32-5E68-8D9B-2B614FDB2783]
    // </editor-fold> 
    private static final long serialVersionUID = 3664646582919518250L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.786DAB0A-73E1-A120-CD30-833BE83466E8]
    // </editor-fold> 
    /**
     * 
     */
    public Average7() {

        super();
        int[] matrix = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
            1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        setData(matrix);
        setDimensions(7, 7);
        setOrigin(3, 3);
        setDivider(49);

    }
} // End Average7