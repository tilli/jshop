package jshop.filters.kernel;

/**
 *  33 medium sharpening.
 *   
 *   <pre>
 *       -1, -2, -1
 *       -2, 16, -2
 *       -1, -2, -1
 *       divided by 4
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.5EB115FA-8B9F-276D-575B-3DC0F78EA070]
// </editor-fold> 
public class MediumSharpener extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.75BAF217-EA2C-CBF1-A911-4C70E23602D5]
    // </editor-fold> 
    private static final long serialVersionUID = -8477759014797554610L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8839C312-8E61-B347-30CC-882F79C3924C]
    // </editor-fold> 
    /**
     * 
     */
    public MediumSharpener() {

        super();
        int[] matrix = {-1, -2, -1, -2, 16, -2, -1, -2, -1};
        setData(matrix);
        setDimensions(3, 3);
        setOrigin(1, 1);
        setDivider(4);

    }
} // End MediumSharpener