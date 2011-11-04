package jshop.filters.fade;


/**
 *  Fade modifier which raises the original fade to some power before returning
 *   it
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.6D697B8E-96A1-6BB7-277F-9F8DC24BA4A1]
// </editor-fold> 
public class PowerModifier extends AbstractModifier {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0C2EBE72-E3E1-1BF5-89BB-8453F5614DD7]
    // </editor-fold> 
    private double power = 0;

    /**
     *  Creates a new <code>PowerModifier</code>
     *  	 
     *  	 @param reversed
     *  	            reverses the fade
     *  	 @param pow
     *  	            fade will be raised to power of this argument
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.00356417-C832-55D4-C910-427C44527F1E]
    // </editor-fold> 
    public PowerModifier (boolean reversed, double pow) {

		super(reversed);
		power = pow;

	}

	/**
	 * Performs the calculations
	 * 
	 * @param value
	 *            fade value between 0 and 1
	 * @return modified value
	 */
	@Override

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.335D8904-3E1D-77E4-6127-058A60A97C86]
    // </editor-fold> 
    public double modify (double value) {

		return Math.pow(value, power);

	}
} // End PowerModifier