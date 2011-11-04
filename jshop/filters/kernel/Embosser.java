package jshop.filters.kernel;

/**
 *  Kernel which is used in emboss operations
 *   
 *   <pre>
 *        North       West     Northwest  etc.
 *       0, -1, 0    0, 0, 0   -1, 0, 0
 *       0,  0, 0   -1, 0, 1    0, 0, 0
 *       0,  1, 0    0, 0, 0    0, 0, 1
 *       multiplied by strength
 *   </pre>
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D6F94509-0176-529A-23C0-F7E65F2F656C]
// </editor-fold> 
public class Embosser extends Kernel {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.18D297EB-40A0-6781-1EA9-8006C083E06F]
    // </editor-fold> 
    private static final long serialVersionUID = 7574208908254216609L;
    /**
     *  North light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5C4D20F9-0CC4-E9AD-3EF7-FED27B6E14F1]
    // </editor-fold> 
    public static final int N = 0;
    /**
     *  Northeast light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CACD8BC8-1EB1-BCD1-CC6D-EA4709FA1F7F]
    // </editor-fold> 
    public static final int NE = 1;
    /**
     *  East light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3E26B405-0B40-9A78-3F74-B3DC0A895DF0]
    // </editor-fold> 
    public static final int E = 2;
    /**
     *  Southeast light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D06A3443-D4A9-F95E-3BAF-45248F9F5825]
    // </editor-fold> 
    public static final int SE = 3;
    /**
     *  South light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DAFB42DD-3B69-FCA3-4FDD-942493F0406D]
    // </editor-fold> 
    public static final int S = 4;
    /**
     *  Southwest light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F0D7DA52-FD95-0A43-0566-86610BF920D6]
    // </editor-fold> 
    public static final int SW = 5;
    /**
     *  West light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.A46CD641-BB62-96A2-CE4E-420DA63F2A34]
    // </editor-fold> 
    public static final int W = 6;
    /**
     *  Northwest light source
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5575F5E5-071B-F08D-32E8-C203DF247E9F]
    // </editor-fold> 
    public static final int NW = 7;

    /**
     *  Creates a default <code>Embosser</code>
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B1C5C04A-02B7-DA69-F348-2DEA2284F813]
    // </editor-fold> 
    public Embosser() {

        this(128, 128, 128, NW, 1);

    }

    /**
     *  Creates an <code>Embosser</code> with gray scale background
     * @param bg 
     * @param str
     * @param dir
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.277E42E9-7E74-8A3A-64B2-363F7D4C1B92]
    // </editor-fold> 
    public Embosser(int bg, int dir, int str) {

        this(bg, bg, bg, dir, str);

    }

    /**
     *  Creates a new <code>Embosser</code>
     *  	 
     *  	 @param ch1
     *  	            channel 1 background
     *  	 @param ch2
     *  	            channel 2
     *  	 @param ch3
     *  	            channel 3
     *  	 @param dir
     *  	            direction
     *  	 @param str
     *  	            strength
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E754F6C4-BDA7-27FC-28FC-11FA08325978]
    // </editor-fold> 
    public Embosser(int ch1, int ch2, int ch3, int dir, int str) {

        if (dir == SE) {
            int[] matrix = {str, 0, 0, 0, 0, 0, 0, 0, -str};
            setData(matrix);
        } else if (dir == NW) {
            int[] matrix = {-str, 0, 0, 0, 0, 0, 0, 0, str};
            setData(matrix);
        } else if (dir == SW) {
            int[] matrix = {0, 0, str, 0, 0, 0, -str, 0, 0};
            setData(matrix);
        } else if (dir == NE) {
            int[] matrix = {0, 0, -str, 0, 0, 0, str, 0, 0};
            setData(matrix);
        } else if (dir == N) {
            int[] matrix = {0, -str, 0, 0, 0, 0, 0, str, 0};
            setData(matrix);
        } else if (dir == W) {
            int[] matrix = {0, 0, 0, -str, 0, str, 0, 0, 0};
            setData(matrix);
        } else if (dir == S) {
            int[] matrix = {0, str, 0, 0, 0, 0, 0, -str, 0};
            setData(matrix);
        } else {
            int[] matrix = {0, 0, 0, str, 0, -str, 0, 0, 0};
            setData(matrix);
        }
        setDimensions(3, 3);
        setOrigin(1, 1);
        setOffsets(ch1, ch2, ch3);

    }
} // End Embosser