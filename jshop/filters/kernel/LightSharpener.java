package jshop.filters.kernel;

/**
 *  33 light sharpening.
 *   
 *   <pre>
 *       -1, -2, -1
 *       -2, 24, -2
 *       -1, -2, -1
 *       divided by 12
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.A4E3389F-E7AD-C5A7-1431-F2EAD3E8E4F5]
// </editor-fold> 
public class LightSharpener extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.084390EC-3AD7-4430-664A-2E907084BAA9]
    // </editor-fold> 
    private static final long serialVersionUID = -1325532451977455618L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C28784D7-9308-13BE-0E2C-7C8721093538]
    // </editor-fold> 
    /**
     * 
     */
    public LightSharpener() {

        super();
        int[] matrix = {-1, -2, -1, -2, 24, -2, -1, -2, -1};
        setData(matrix);
        setDimensions(3, 3);
        setOrigin(1, 1);
        setDivider(12);

    }
} // End LightSharpener