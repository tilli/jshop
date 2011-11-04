package jshop.filters;

/**
 *  Rotates an image. The image is anti-aliased with area-averaging methods to
 *   create a smooth-looking result.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.D862C6F1-2126-4B10-3C0B-F6001A10C037]
// </editor-fold> 
public final class ImageRotator extends ImageDataCollector {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DF11E424-9FEE-DA4C-5648-C4AFF978308D]
    // </editor-fold> 
    private static final long serialVersionUID = 2674036222157514610L;
    /**
     *  Rotate right
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.586B6F05-53B0-7709-12C2-4423580233A4]
    // </editor-fold> 
    public static final int RIGHT = 1;
    /**
     *  Rotate left
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5FF84A7E-A608-C612-C431-B62ECD163522]
    // </editor-fold> 
    public static final int LEFT = -1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.550C6459-2DF1-F644-D2F4-F84441C5045F]
    // </editor-fold> 
    private int direction;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.40DD79F4-26FE-8ECD-A7A5-EE656341202D]
    // </editor-fold> 
    private int angle;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E52A721C-E6DC-F223-0696-2E05EFB7EAC3]
    // </editor-fold> 
    private int bgcolor;

    /**
     *  Creates a new <code>ImageRotator</code>.
     *  	 
     *  	 @param direction
     *  	            Direction of rotation
     *  	 @param angle
     *  	            Angle of rotation in degrees
     *  	 @param bgcolor
     *  	            Background color
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1154CA88-1494-0B75-83DE-0E8F353185A5]
    // </editor-fold> 
    public ImageRotator(int direction, int angle, int bgcolor) {

        this.direction = direction;
        this.angle = angle % 360;
        this.bgcolor = bgcolor;

    }

    /**
     * Gets the string representation of the properties of this filter
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.76C750EF-817E-1FCF-B5BF-F700D5FE9F3C]
    // </editor-fold> 
    protected String getProperties() {

        return "Direction = " + (direction == RIGHT ? "Right" : "Left") + ", Angle = " + angle + ", Background = #" + Integer.toHexString(bgcolor);

    }

    /**
     * Rotates the pixel data and passes it to the image consumer
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.4CF3D29E-8D64-001A-D361-DEF5469F146F]
    // </editor-fold> 
    protected void filterData(int[] pix, int width, int height) {

        // Easy ones
        if (angle == 90 || angle == 270) {
            consumer.setDimensions(height, width);
            int[] row = new int[height];
            if ((angle == 90 && direction == RIGHT) || (angle == 270 && direction == LEFT)) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        row[height - 1 - y] = pix[y * width + x];
                    }
                    passToConsumer(0, x, height, 1, row, 0, height);
                }
            } else {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        row[y] = pix[(y + 1) * width - x - 1];
                    }
                    passToConsumer(0, x, height, 1, row, 0, height);
                }
            }
        } else if (angle == 180) {
            int[] row = new int[width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    row[width - 1 - x] = pix[(height - y - 1) * width + x];
                }
                passToConsumer(0, y, width, 1, row, 0, width);
            }
        } // And not so easy
        else {
            double ang;
            int destWidth, destHeight;
            ang = ((double) angle) * Math.PI / 180;
            destWidth = (int) Math.round(Math.abs(Math.cos(ang) * width) + Math.abs(Math.sin(ang) * height));
            destHeight = (int) Math.round(Math.abs(Math.sin(ang) * width) + Math.abs(Math.cos(ang) * height));
            consumer.setDimensions(destWidth, destHeight);

            double currentx, currenty; // Coordinates in new image, relative to
            // center
            double sourcex, sourcey; // Source point
            double dhhalf = destHeight / 2;
            double dwhalf = destWidth / 2;
            double halfw = width / 2;
            double halfh = height / 2;
            double cos = Math.cos(direction * ang);
            double sin = Math.sin(direction * ang);
            double sinneg = -Math.sin(direction * ang);
            double diffx, diffy; // Difference between source point and the next
            // pixel to the topleft
            int tlx, trx, blx, brx, tly, tyr, bly, bry; // Four pixels around
            // the source point
            int tlval, trval, blval, brval; // Values in these pixels
            int a = 255, r, g, b;

            int[] row = new int[destWidth];
            for (int y = 0; y < destHeight; y++) {
                for (int x = 0; x < destWidth; x++) {
                    currentx = x - dwhalf; // Translate to center
                    currenty = y - dhhalf;
                    sourcex = cos * currentx + sin * currenty + halfw; // Count
                    // the
                    // source
                    // point
                    sourcey = sinneg * currentx + cos * currenty + halfh;
                    tlx = blx = (int) Math.floor(sourcex); // Pixels around the
                    // source point
                    tly = tyr = (int) Math.floor(sourcey);
                    brx = trx = tlx + 1;
                    bry = bly = tly + 1;
                    diffx = sourcex - tlx;
                    diffy = sourcey - tly;

                    // Values of the four pixels around the source point.
                    // If a pixel is out of the source image area, bgcolor is
                    // used
                    tlval = (tlx < 0 || tlx >= width || tly < 0 || tly >= height) ? bgcolor
                            : pix[tlx + tly * width];
                    trval = (trx < 0 || trx >= width || tyr < 0 || tyr >= height) ? bgcolor
                            : pix[trx + tyr * width];
                    blval = (blx < 0 || blx >= width || bly < 0 || bly >= height) ? bgcolor
                            : pix[blx + bly * width];
                    brval = (brx < 0 || brx >= width || bry < 0 || bry >= height) ? bgcolor
                            : pix[brx + bry * width];

                    // Counts the value of the current pixel from the four
                    // source pixels.
                    r = (int) ((((tlval >> 16) & 0xff) * (1 - diffx) + ((tlval >> 16) & 0xff) * (1 - diffy) + ((blval >> 16) & 0xff) * (diffy - diffx) + ((trval >> 16) & 0xff) * (diffx - diffy) + ((brval >> 16) & 0xff) * (diffy) + ((brval >> 16) & 0xff) * (diffx)) / 2);
                    g = (int) ((((tlval >> 8) & 0xff) * (1 - diffx) + ((tlval >> 8) & 0xff) * (1 - diffy) + ((blval >> 8) & 0xff) * (diffy - diffx) + ((trval >> 8) & 0xff) * (diffx - diffy) + ((brval >> 8) & 0xff) * (diffy) + ((brval >> 8) & 0xff) * (diffx)) / 2);
                    b = (int) (((tlval & 0xff) * (1 - diffx) + (tlval & 0xff) * (1 - diffy) + (blval & 0xff) * (diffy - diffx) + (trval & 0xff) * (diffx - diffy) + (brval & 0xff) * (diffy) + (brval & 0xff) * (diffx)) / 2);

                    // Prevents possible over and underflow
                    r = r < 0 ? 0 : r > 255 ? 255 : r;
                    g = g < 0 ? 0 : g > 255 ? 255 : g;
                    b = b < 0 ? 0 : b > 255 ? 255 : b;

                    row[x] = (a << 24) | (r << 16) | (g << 8) | b;
                }
                passToConsumer(0, y, destWidth, 1, row, 0, destWidth);
            }
        }

    }

    /**
     * 8-bit filtering. This can only rotate 90, 180 or 270 degrees.
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AB76922D-22A6-00D2-888B-CD3C3FF232F9]
    // </editor-fold> 
    protected void filterIndexed(byte[] pix, int width, int height) {

        if (angle == 90 || angle == 270) {
            consumer.setDimensions(height, width);
            byte[] row = new byte[height];
            if ((angle == 90 && direction == RIGHT) || (angle == 270 && direction == LEFT)) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        row[height - 1 - y] = pix[y * width + x];
                    }
                    passBytesToConsumer(0, x, height, 1, row, 0, height);
                }
            } else {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        row[y] = pix[(y + 1) * width - x - 1];
                    }
                    passBytesToConsumer(0, x, height, 1, row, 0, height);
                }
            }
        } else if (angle == 180) {
            byte[] row = new byte[width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    row[width - 1 - x] = pix[(height - y - 1) * width + x];
                }
                passBytesToConsumer(0, y, width, 1, row, 0, width);
            }
        }

    }

    /**
     * 8-bit grayscale filtering.
     *
     * @param width
     * @param height
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E0A231F7-6611-57BE-039E-1F0C73FF102F]
    // </editor-fold> 
    protected void filterBytes(byte[] pix, int width, int height) {

        if (angle == 90 || angle == 180 || angle == 270) {
            filterIndexed(pix, width, height);
        } else { // Copied from above
            double ang;
            int destWidth, destHeight;
            ang = ((double) angle) * Math.PI / 180;
            destWidth = (int) Math.round(Math.abs(Math.cos(ang) * width) + Math.abs(Math.sin(ang) * height));
            destHeight = (int) Math.round(Math.abs(Math.sin(ang) * width) + Math.abs(Math.cos(ang) * height));
            consumer.setDimensions(destWidth, destHeight);

            double currentx, currenty;
            double sourcex, sourcey;
            double dhhalf = destHeight / 2;
            double dwhalf = destWidth / 2;
            double halfw = width / 2;
            double halfh = height / 2;
            double cos = Math.cos(direction * ang);
            double sin = Math.sin(direction * ang);
            double sinneg = -Math.sin(direction * ang);
            double diffx, diffy;
            int tlx, trx, blx, brx, tly, tyr, bly, bry;
            int tlval, trval, blval, brval;
            int pixval;

            byte[] row = new byte[destWidth];
            for (int y = 0; y < destHeight; y++) {
                for (int x = 0; x < destWidth; x++) {
                    currentx = x - dwhalf;
                    currenty = y - dhhalf;
                    sourcex = cos * currentx + sin * currenty + halfw;
                    sourcey = sinneg * currentx + cos * currenty + halfh;
                    tlx = blx = (int) Math.floor(sourcex);
                    tly = tyr = (int) Math.floor(sourcey);
                    brx = trx = tlx + 1;
                    bry = bly = tly + 1;
                    diffx = sourcex - tlx;
                    diffy = sourcey - tly;

                    tlval = (tlx < 0 || tlx >= width || tly < 0 || tly >= height) ? bgcolor
                            : pix[tlx + tly * width] & 0xff;
                    trval = (trx < 0 || trx >= width || tyr < 0 || tyr >= height) ? bgcolor
                            : pix[trx + tyr * width] & 0xff;
                    blval = (blx < 0 || blx >= width || bly < 0 || bly >= height) ? bgcolor
                            : pix[blx + bly * width] & 0xff;
                    brval = (brx < 0 || brx >= width || bry < 0 || bry >= height) ? bgcolor
                            : pix[brx + bry * width] & 0xff;

                    pixval = (int) (((tlval & 0xff) * (1 - diffx) + (tlval & 0xff) * (1 - diffy) + (blval & 0xff) * (diffy - diffx) + (trval & 0xff) * (diffx - diffy) + (brval & 0xff) * (diffy) + (brval & 0xff) * (diffx)) / 2);

                    pixval = pixval < 0 ? 0 : pixval > 255 ? 255 : pixval;

                    row[x] = (byte) (pixval & 0xff);
                }
                passBytesToConsumer(0, y, destWidth, 1, row, 0, destWidth);
            }
        }

    }

    /**
     * No locks
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.1BBE322F-F72D-AB2D-E046-CC69C0C02F23]
    // </editor-fold> 
    protected boolean usesLocks() {

        return false;

    }

    /**
     * Always RGB
     *
     * @return
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.67C1BEDA-6712-1784-2686-3BC4C0E9469F]
    // </editor-fold> 
    protected boolean usesSpaces() {

        return false;

    }

    /**
     * Not filtering color model
     *
     * @return 
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.EE5B42CC-EECF-20CA-1F16-E8E3D88B92EF]
    // </editor-fold> 
    protected boolean isFilteringColorModel() {

        return false;

    }
} // End ImageRotator