/*
 * 13.10. 1997
 */
package jshop.filters;

/**
 *  Performs histogram equalization to an image.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6882EE58-5A57-2CFC-8393-0B281A1F3ED4]
// </editor-fold> 
public class HistogramEqualizer extends HistogramCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.27B57579-0F83-3430-BB48-A315F1999F01]
    // </editor-fold> 
    private static final long serialVersionUID = -3152009705761322228L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F37D22D0-33D2-BEBF-6044-442F44ABA2A1]
    // </editor-fold> 
    private int style;
    /**
     *  Equalizes the channels separately
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C4544AF6-5CA5-58B6-F366-47CA704A54CC]
    // </editor-fold> 
    public static final int CHANNELS = 0;
    /**
     *  Equalizes the average of the three channels
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.55A05163-1002-6C78-BF47-51D561704308]
    // </editor-fold> 
    public static final int AVERAGE = 1;

    /**
     *  Creates a new HistogramEqualizer which equalizes channels separately
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C79460E0-6845-121F-117A-F60DD2BCE398]
    // </editor-fold> 
    public HistogramEqualizer() {

        this(0);

    }

    /**
     *  Creates a new HistogramEqualizer
     * @param style 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2A7DF7FB-1C4F-150C-DFBB-3F2C4FA4F7AD]
    // </editor-fold> 
    public HistogramEqualizer(int style) {

        this.style = style;

    }

    /**
     * Gets the equalized data
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D6998560-D948-95D5-2FC3-066A79EC9945]
    // </editor-fold> 
    protected int[][] getFilteredData(int[][] data, int w, int h) {

        if (style == 0) {
            return channelEqu(data, w * h);
        } else {
            return averageEqu(data, w * h);
        }

    }

    /**
     * Gray scale equalization
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3BD10634-6718-DDBA-684F-C3878A3B4488]
    // </editor-fold> 
    protected int[] getByteData(int[] data, int w, int h) {

        return data;

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7ABAC63C-5F23-B3FF-FA0B-F32EE103DB06]
    // </editor-fold> 
    private int[][] averageEqu(int[][] orig, int size) {

        double pr = 0;
        int diff;
        int[][] data = new int[3][256];
        for (int i = 0; i < 256; i++) {
            pr += ((double) orig[0][i] + (double) orig[1][i] + (double) orig[2][i]) / ((double) size * 3);
            diff = (int) ((pr - (double) i / 255) * 255);
            data[0][i] = data[1][i] = data[2][i] = i + diff;
        }
        return data;

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.71ABE7F6-04E9-B4F2-29CC-CF7C98362177]
    // </editor-fold> 
    private int[][] channelEqu(int[][] orig, int size) {

        double rpr = 0, gpr = 0, bpr = 0;
        int diff;
        int[][] data = new int[3][256];
        for (int i = 0; i < 256; i++) {
            rpr += (double) orig[0][i] / size;
            gpr += (double) orig[1][i] / size;
            bpr += (double) orig[2][i] / size;
            diff = (int) ((rpr - (double) i / 255) * 255);
            data[0][i] = i + diff;
            diff = (int) ((gpr - (double) i / 255) * 255);
            data[1][i] = i + diff;
            diff = (int) ((bpr - (double) i / 255) * 255);
            data[2][i] = i + diff;
        }
        return data;

    }
}