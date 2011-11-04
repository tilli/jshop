package jshop.filters.kernel;

/**
 *  55 gaussian blur
 *   
 *   <pre>
 *       1, 2,  4, 2, 1
 *       2, 4,  8, 4, 2
 *       4, 8, 16, 8, 4
 *       2, 4,  8, 4, 2
 *       1, 2,  4, 2, 1
 *       divided by 100
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.4C5050E4-B993-7461-5106-7CE4733E229A]
// </editor-fold> 
public class Gauss5 extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3BA660D1-CE6D-DD08-EE30-EE96FBB83235]
    // </editor-fold> 
    private static final long serialVersionUID = 434521403184890109L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5976E048-9D68-46FF-8959-9B292C6F0FB4]
    // </editor-fold> 
    /**
     * 
     */
    public Gauss5() {

        super();
        int[] matrix = {1, 2, 4, 2, 1, 2, 4, 8, 4, 2, 4, 8, 16, 8, 4, 2, 4, 8,
            4, 2, 1, 2, 4, 2, 1};
        setData(matrix);
        setDimensions(5, 5);
        setOrigin(2, 2);
        setDivider(100);

    }
} // End Gauss5