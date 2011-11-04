package jshop.filters.kernel;

/**
 *  33 averaging blur. I'll soon replace all blurring kernels with a two filter
 *   classes, one for gaussian and one for averaging. These classes should be able
 *   to perform efficient blurring from 22 kernels up to whatever user wants
 *   (10001000 sounds interesting)
 *   
 *   <pre>
 *       1, 1, 1
 *       1, 1, 1
 *       1, 1, 1
 *       divided by 9
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2502B835-F6B1-9B63-20EE-AF57E9A233C5]
// </editor-fold> 
public class Average3 extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1029232A-D31B-29F7-4D63-BDBEC9FAF358]
    // </editor-fold> 
    private static final long serialVersionUID = -5382550075424310845L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3FCAAEE6-71E4-D748-AF82-CEFE4F3A80DC]
    // </editor-fold> 
    /**
     * 
     */
    public Average3() {

        super();
        int[] matrix = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        setData(matrix);
        setDimensions(3, 3);
        setOrigin(1, 1);
        setDivider(9);

    }
} // End Average3