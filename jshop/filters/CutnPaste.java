/*
 * 3.10 -> versio 0.2.0
 * Fade-efektit
 * 20.11. -> 0.3.0
 * Muunnos IDC:n alaluokaksi
 */
package jshop.filters;

import java.awt.Rectangle;

/**
 *  Class which handles cut- and paste-effects
 *   
 *   @see Fade
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B472580B-2C70-34F3-3E87-3A848BE6AB8F]
// </editor-fold> 
public final class CutnPaste extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4F7D0E69-65F9-2F86-60BA-93EDB88D4DB0]
    // </editor-fold> 
    private static final long serialVersionUID = -5145082910214119854L;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8E67C8B5-FE4C-8622-3FA2-433534998083]
    // </editor-fold> 
    private int style = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.BB627DB3-5EC6-A950-6AC6-2F38737C3C92]
    // </editor-fold> 
    private int col = 0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3848886C-C5BA-F62A-E38B-231E90A373CC]
    // </editor-fold> 
    private boolean paste;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.974B604B-3C20-DABA-C479-654E920B0F75]
    // </editor-fold> 
    private Fade fade;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.05B4837D-7654-9997-60C8-5C864C5DDE72]
    // </editor-fold> 
    private Rectangle rectangle;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.008CC923-4D23-A29B-9709-C156EBB2BFE9]
    // </editor-fold> 
    private int[] clipData;
    /**
     *  Normal paste
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CFF90209-B5F5-EECA-88C2-C8779552CE89]
    // </editor-fold> 
    public static final short OVERWRITING = 0;
    /**
     *  Adds the channels together
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C621F908-35F9-1E17-6633-F071CEDB5319]
    // </editor-fold> 
    public static final short ADDITIVE = 1;
    /**
     *  Adds channels together and overflows from 255 to 0
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.953D6480-2FC9-6EF4-CC35-47B1E8A2E18C]
    // </editor-fold> 
    public static final short OVERFLOWING = 2;
    /**
     *  Subtracts the paste-clip from the image
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C13DCFD3-8D5E-D458-0C2C-465F441CA0D0]
    // </editor-fold> 
    public static final short SUBTRACTIVE = 3;
    /**
     *  Subtracts and underflows from 0 to 255
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C08B2A81-44CE-E49C-577E-273CD8479BB6]
    // </editor-fold> 
    public static final short UNDERFLOWING = 4;

    /**
     *  Creates a new CutnPaste which fills the given area with the given color
     *  	 
     *  	 @param bgcolor
     *  	            Area color
     *  	 @param rect
     *  	            Area bounds
     *  	 @param data
     *  	            Area data. This data is only checked for transparencies.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.87FC6725-B60F-D000-181E-2265F0411475]
    // </editor-fold> 
    public CutnPaste(int bgcolor, Rectangle rect, int[] data) {

        col = bgcolor;
        paste = false;
        rectangle = rect;
        clipData = data;

    }

    /**
     *  Creates a new CutnPaste whics is used for pasting
     *  	 
     *  	 @param style
     *  	            Paste style
     *  	 @param data
     *  	            Clip-Image data
     *  	 @param rect
     *  	            Clip area
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9DCD7199-BCDC-F1EA-DCAE-62FC329C1429]
    // </editor-fold> 
    public CutnPaste(int style, int[] data, Rectangle rect) {

        this.style = style;
        rectangle = rect;
        paste = true;
        clipData = data;

    }

    /**
     * Gets this filters properties
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AE7739AF-1A72-CDD3-C872-5462679B4145]
    // </editor-fold> 
    protected String getProperties() {

        String s = "";
        String s2 = ", Area = (" + rectangle.x + ", " + rectangle.y + ")->(" + (rectangle.x + rectangle.width - 1) + ", " + (rectangle.y + rectangle.height - 1) + ")";
        if (paste) {
            switch (style) {
                case ADDITIVE:
                    return "Additive paste" + s2 + s;
                case OVERFLOWING:
                    return "Overflowing paste" + s2 + s;
                case SUBTRACTIVE:
                    return "Subtractive paste" + s2 + s;
                case UNDERFLOWING:
                    return "Underflowing paste" + s2 + s;
                default:
                    return "Overwriting paste" + s2 + s;
            }
        } else {
            return "Cutting" + s2 + ", Background = #" + Integer.toHexString(col) + s;
        }

    }

    /**
     * Filters the data
     *
     * @param data
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8F94D99A-169E-9C12-0689-299E2173E34E]
    // </editor-fold> 
    protected void filterData(int[] data, int width, int height) {

        if (paste) {
            pasteImage(data, width, height);
        } else {
            cutImage(data, width, height);
        }

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.04C2A7D0-7082-1C2D-5C35-A8F9990AC662]
    // </editor-fold> 
    private void cutImage(int[] data, int width, int height) {

        int cw = rectangle.width;
        int ch = rectangle.height;
        int px = rectangle.x;
        int py = rectangle.y;

        if ((getLocks() & 63) == 0 && fade == null) {
            for (int y = py, cy = 0; y < py + ch; y++, cy++) {
                for (int x = px, cx = 0; x < px + cw; x++, cx++) {
                    if ((clipData[cy * cw + cx] & 0xff000000) != 0) {
                        data[x + y * width] = col;
                    }
                }
            }
        } else if (fade == null) {
            int ind;
            for (int y = py, cy = 0; y < py + ch; y++, cy++) {
                for (int x = px, cx = 0; x < px + cw; x++, cx++) {
                    ind = y * width + x;
                    if ((clipData[cy * cw + cx] & 0xff000000) != 0) {
                        data[ind] = (data[ind] & 0xff000000) | (getLock(CH1) ? data[ind] & 0xff0000 : 0) | (getLock(CH2) ? data[ind] & 0xff00 : 0) | (getLock(CH3) ? data[ind] & 0xff : 0);
                    }
                }
            }
        } else {
            int ind, rd, gr, bl;
            double mod = 0;
            for (int y = py, cy = 0; y < py + ch; y++, cy++) {
                for (int x = px, cx = 0; x < px + cw; x++, cx++) {
                    ind = y * width + x;
                    if ((clipData[cy * cw + cx] & 0xff000000) != 0) {
                        mod = fade.countFade(cw, ch, cx, cy);
                        rd = getLock(CH1) ? data[ind] & 0xff0000
                                : (int) ((((data[ind] >> 16) & 0xff) * mod + ((col >> 16) & 0xff) * (1 - mod))) << 16;
                        gr = getLock(CH2) ? data[ind] & 0xff00
                                : (int) ((((data[ind] >> 8) & 0xff) * mod + ((col >> 8) & 0xff) * (1 - mod))) << 8;
                        bl = getLock(CH3) ? data[ind] & 0xff
                                : (int) ((data[ind] & 0xff) * mod + (col & 0xff) * (1 - mod));
                        data[ind] = (data[ind] & 0xff000000) | rd | gr | bl;
                    }
                }
            }
        }
        passToConsumer(0, 0, width, height, data, 0, width);

    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.278EF42B-84B6-C950-3A7D-31350A481D75]
    // </editor-fold> 
    private void pasteImage(int[] data, int width, int height) {

        int cw = rectangle.width;
        int ch = rectangle.height;
        int px = rectangle.x;
        int py = rectangle.y;

        double mod = 0; // Fade counter
        int ind, pind, rd, gr, bl;

        if (style == OVERWRITING) {
            for (int y = py, cy = 0; cy < ch; y++, cy++) {
                for (int x = px, cx = 0; cx < cw; x++, cx++) {
                    if (y < height && x < width && y > 0 && x > 0) {
                        ind = y * width + x;
                        pind = cx + cy * cw;
                        if ((clipData[pind] & 0xff000000) != 0) {
                            if (fade != null) {
                                mod = fade.countFade(cw, ch, cx, cy);
                            }
                            rd = getLock(CH1) ? data[ind] & 0xff0000
                                    : (int) ((((data[ind] >> 16) & 0xff) * mod + ((clipData[pind] >> 16) & 0xff) * (1 - mod))) << 16;
                            gr = getLock(CH2) ? data[ind] & 0xff00
                                    : (int) ((((data[ind] >> 8) & 0xff) * mod + ((clipData[pind] >> 8) & 0xff) * (1 - mod))) << 8;
                            bl = getLock(CH3) ? data[ind] & 0xff
                                    : (int) ((data[ind] & 0xff) * mod + (clipData[pind] & 0xff) * (1 - mod));
                            data[ind] = (data[ind] & 0xff000000) | rd | gr | bl;
                        }
                    }
                }
            }
        } else if (style == OVERFLOWING) {
            for (int y = py, cy = 0; cy < ch; y++, cy++) {
                for (int x = px, cx = 0; cx < cw; x++, cx++) {
                    if (y < height && x < width && y > 0 && x > 0) {
                        ind = y * width + x;
                        pind = cx + cy * cw;
                        if ((clipData[pind] & 0xff000000) != 0) {
                            if (fade != null) {
                                mod = fade.countFade(cw, ch, cx, cy);
                            }
                            rd = getLock(CH1) ? data[ind] & 0xff0000
                                    : (int) ((((data[ind] >> 16) & 0xff) + ((clipData[pind] >> 16) & 0xff) * (1 - mod))) << 16;
                            gr = getLock(CH2) ? data[ind] & 0xff00
                                    : (int) ((((data[ind] >> 8) & 0xff) + ((clipData[pind] >> 8) & 0xff) * (1 - mod))) << 8;
                            bl = getLock(CH3) ? data[ind] & 0xff
                                    : (int) ((data[ind] & 0xff) + (clipData[pind] & 0xff) * (1 - mod));
                            data[ind] = (data[ind] & 0xff000000) | rd | gr | bl;
                        }
                    }
                }
            }
        } else if (style == ADDITIVE) {
            for (int y = py, cy = 0; cy < ch; y++, cy++) {
                for (int x = px, cx = 0; cx < cw; x++, cx++) {
                    if (y < height && x < width && y > 0 && x > 0) {
                        ind = y * width + x;
                        pind = cx + cy * cw;
                        if ((clipData[pind] & 0xff000000) != 0) {
                            if (fade != null) {
                                mod = fade.countFade(cw, ch, cx, cy);
                            }
                            rd = getLock(CH1) ? data[ind] & 0xff0000
                                    : (int) ((((data[ind] >> 16) & 0xff) + ((clipData[pind] >> 16) & 0xff) * (1 - mod)));
                            gr = getLock(CH2) ? data[ind] & 0xff00
                                    : (int) ((((data[ind] >> 8) & 0xff) + ((clipData[pind] >> 8) & 0xff) * (1 - mod)));
                            bl = getLock(CH3) ? data[ind] & 0xff
                                    : (int) ((data[ind] & 0xff) + (clipData[pind] & 0xff) * (1 - mod));
                            if (rd > 255) {
                                rd = 255;
                            }
                            if (gr > 255) {
                                gr = 255;
                            }
                            if (bl > 255) {
                                bl = 255;
                            }
                            data[ind] = (data[ind] & 0xff000000) | (rd << 16) | (gr << 8) | bl;
                        }
                    }
                }
            }
        } else if (style == SUBTRACTIVE) {
            for (int y = py, cy = 0; cy < ch; y++, cy++) {
                for (int x = px, cx = 0; cx < cw; x++, cx++) {
                    if (y < height && x < width && y > 0 && x > 0) {
                        ind = y * width + x;
                        pind = cx + cy * cw;
                        if ((clipData[pind] & 0xff000000) != 0) {
                            if (fade != null) {
                                mod = fade.countFade(cw, ch, cx, cy);
                            }
                            rd = getLock(CH1) ? data[ind] & 0xff0000
                                    : (int) ((((data[ind] >> 16) & 0xff) - ((clipData[pind] >> 16) & 0xff) * (1 - mod)));
                            gr = getLock(CH2) ? data[ind] & 0xff00
                                    : (int) ((((data[ind] >> 8) & 0xff) - ((clipData[pind] >> 8) & 0xff) * (1 - mod)));
                            bl = getLock(CH3) ? data[ind] & 0xff
                                    : (int) ((data[ind] & 0xff) - (clipData[pind] & 0xff) * (1 - mod));
                            if (rd < 0) {
                                rd = 0;
                            }
                            if (gr < 0) {
                                gr = 0;
                            }
                            if (bl < 0) {
                                bl = 0;
                            }
                            data[ind] = (data[ind] & 0xff000000) | (rd << 16) | (gr << 8) | bl;
                        }
                    }
                }
            }
        } else if (style == UNDERFLOWING) {
            for (int y = py, cy = 0; cy < ch; y++, cy++) {
                for (int x = px, cx = 0; cx < cw; x++, cx++) {
                    if (y < height && x < width && y > 0 && x > 0) {
                        ind = y * width + x;
                        pind = cx + cy * cw;
                        if ((clipData[pind] & 0xff000000) != 0) {
                            if (fade != null) {
                                mod = fade.countFade(cw, ch, cx, cy);
                            }
                            rd = getLock(CH1) ? data[ind] & 0xff0000
                                    : (int) ((((data[ind] >> 16) & 0xff) - ((clipData[pind] >> 16) & 0xff) * (1 - mod))) << 16;
                            gr = getLock(CH2) ? data[ind] & 0xff00
                                    : (int) ((((data[ind] >> 8) & 0xff) - ((clipData[pind] >> 8) & 0xff) * (1 - mod))) << 8;
                            bl = getLock(CH3) ? data[ind] & 0xff
                                    : (int) ((data[ind] & 0xff) - (clipData[pind] & 0xff) * (1 - mod));
                            data[ind] = (data[ind] & 0xff000000) | (rd & 0xff0000) | (gr & 0xff00) | (bl & 0xff);
                        }
                    }
                }
            }
        }
        passToConsumer(0, 0, width, height, data, 0, width);

    }

    /**
     *  Sets a new paste-style
     * @param style
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B4AED004-617E-579F-573D-D7E3B597AC4D]
    // </editor-fold> 
    public void setPasteStyle(int style) {

        this.style = style;

    }

    /**
     *  Sets a new background color
     * @param col 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.695FE81C-E800-2F90-A164-F277596636CE]
    // </editor-fold> 
    public void setBackground(int col) {

        this.col = col;

    }

    /**
     *  Sets the fade
     * @param f
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.00A9A4D3-8023-4F65-3542-8B001B4A1CB6]
    // </editor-fold> 
    public void setFade(Fade f) {

        fade = f;

    }
} // End CutnPaste