package jshop.filters.fade;

/**
 *  Abstract implementation of the <code>Fader</code>. This adds possibilities to
 *   use a modifier after the actual fade calculation. Fade amount is a value
 *   between 0 and 1. If amount is 0, calculated fade value will always be 0. If
 *   amount is 1 calculated value will always be 1. When amount is 0.5
 *   <code>calculate should return
 *       values between 0 and 1.
 *       Example:
 *       <pre>
 *       //This performs a horizontal fade from right to left.
 *       public double calculate(int x, int y, int w, int h, double amount) {
 *         return (double)(w-x) / (double)w + amount2 - 1;
 *       }
 *       </pre>
 *   
 *   @see FadeModifier
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.2AD99A65-69DC-DA01-715D-3EEEE7EF8950]
// </editor-fold> 
public abstract class AbstractFade implements Fader {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B57D148F-E8B3-2F52-2D9D-D5F40903D25A]
    // </editor-fold> 
    private FadeModifier modifier;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.88A85C9C-F5AD-949A-AA95-5A5B96C424C8]
    // </editor-fold> 
    private double amount = 0;

    /**
     *  Creates a new <code>AbstractFade</code> with amount of 0
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F2EB3596-6EBA-CB96-7547-91E5FEF0795B]
    // </editor-fold> 
    public AbstractFade() {
    }

    /**
     *  Creates a new <code>AbstractFade</code>
     *  	 
     *  	 @param amount
     *  	            fade amount between 0 and 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.CB59842D-61F4-467A-7A21-5D424A5DEF9F]
    // </editor-fold> 
    public AbstractFade(double amount) {

        if (amount < 0) {
            amount = 0;
        } else if (amount > 1) {
            amount = 1;
        }
        this.amount = amount;

    }

    /**
     *  Sets the fade amount
     *  	 
     *  	 @param amount
     *  	            fade amount between 0 and 1
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D86FCE66-A868-6CC0-C944-351CF505756C]
    // </editor-fold> 
    public void setAmount(double amount) {

        if (amount < 0) {
            amount = 0;
        } else if (amount > 1) {
            amount = 1;
        }
        this.amount = amount;

    }

    /**
     *  Gets the fade amount
     * @return 
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C4D583EA-A536-2321-170C-EB697650CA46]
    // </editor-fold> 
    public double getAmount() {

        return amount;

    }

    /**
     *  Sets the modifier which will be used after the calculation.
     * @param f
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.40C30A10-CD0E-B336-0AB8-19C76EEA9F51]
    // </editor-fold> 
    public void setModifier(FadeModifier f) {

        modifier = f;

    }

    /**
     *  Gets the modifier which is invoked after <code>calculate</code>
     * @return
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F7B5BD42-7C1A-2FEB-F847-8AC3763FE71A]
    // </editor-fold> 
    public FadeModifier getModifier() {

        return modifier;

    }

    /**
     * Invokes <code>calculate</code>. If calculate returns a value that is
     * larger than 1 it will be set to 1. If calculate returns a value that is
     * less than 0 it will be set to 0. If modifier is set this also invokes
     * <code>FadeModifier.modifyFade</code>.
     */
    @Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.2A33631F-DEC7-5245-1F2D-86CDB6E322EB]
    // </editor-fold> 
    public double calculateFade(int x, int y, int w, int h) {

        if (amount == 1) {
            return 1;
        } else if (amount == 0) {
            return 0;
        }
        double val = calculate(x, y, w, h, amount);
        if (val > 1) {
            val = 1;
        } else if (val < 0) {
            val = 0;
        }
        if (modifier != null) {
            val = modifier.modifyFade(val);
        }
        return val;

    }

    /**
     *  Performs the fade calculation
     *  	 
     *  	 @param x
     *  	            x coordinate of current location
     *  	 @param y
     *  	            y coordinate
     *  	 @param w
     *  	            width of the fade area
     *  	 @param h
     *  	            height of the fade area
     *  	 @param amount
     *  	            fade amount. This will be between 0 and 1
     *  	 @return calculated fade amount.
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.83BEE908-2F87-6C23-9223-FD4C3D0690E5]
    // </editor-fold> 
    protected abstract double calculate(int x, int y, int w, int h, double amount);
} // End AbstractFade