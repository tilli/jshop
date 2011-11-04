package jshop.filters;


/**
 *  Sorting algorithms.
 *   
 *   @author Timo Ohtonen
 */
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.023421CD-25B3-20FC-2417-D1B056BB8BF4]
// </editor-fold> 
public class Sort {

    /**
     *  Quicksort
     *  	 
     *  	 @param source
     *  	            Data to be sorted
     *  	 @param left
     *  	            Left pivot, 0 should be used
     *  	 @param right
     *  	            Right pivot, source.length-1 should be used
     */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E60D4AE9-B9ED-A171-D51A-CE0F227C8CFB]
    // </editor-fold> 
    public void quickSort (int[] source, int left, int right) {

		if (right > left) {
			int pivot2;
			int pivot = source[right];
			int left2 = left - 1;
			int right2 = right;
			do {
				do {
					left2++;
				} while (source[left2] < pivot);
				do {
					if (right2 > 0) {
						right2--;
					}
				} while (source[right2] > pivot && right2 > 0);
				pivot2 = source[left2];
				source[left2] = source[right2];
				source[right2] = pivot2;
			} while (right2 > left2);
			source[right2] = source[left2];
			source[left2] = source[right];
			source[right] = pivot2;
			quickSort(source, left, left2 - 1);
			quickSort(source, left2 + 1, right);
		}

	}
} // End Sort