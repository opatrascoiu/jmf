/**
 * @author Octavian Patrascoiu
 *
 */
package uk.ac.kent.cs.kmf.graphics.chart;
/**
 * The goal of this class is to re-scalle the values given at the applet
 * to tailor the kiviat needs
 */
public class KiviatCom {

	private int[] valueOk;
	private double minKiviat,
		maxKiviat,
		transformedminKiviat,
		transformedmaxKiviat;

	/**
	 * Method to re-scalle the values  
	 * These values are contained in the following datasets : <br>
	 * <ul>
	 * <li> minmaxkiviat : Values for the plotarea (calculated before with the applet width and height)<br>
	 * <li>MinMaxValues : High and Low Values given to the applet<br>
	 * <li>dataset0 : Original values given to the applet <br>
	 * @param : k   KiviatChart Object<br><p>
	 * <b>WARNING !!
	 * This method modify the <i>dataset0</i> dataset 
	 * and create <i>valueOk</i></b> dataset 
	 */

	public void transformValues(KiviatChart k) {

		Dataset minmaxkiviatDataset;
		Dataset OriginalMinMaxValuesDataset;
		Dataset OriginalValuesDataset;
		double[] OriginalMinMaxValues;
		double[] OriginalValues;

		double[] valueOk;
		Datum d;

		minmaxkiviatDataset = k.getDataset("minmaxkiviat");
		d = minmaxkiviatDataset.getDataElementAt(0);
		minKiviat = d.getY();
		d = minmaxkiviatDataset.getDataElementAt(1);
		maxKiviat = d.getY();

		OriginalMinMaxValuesDataset = k.getDataset("MinMaxValues");
		OriginalMinMaxValues = OriginalMinMaxValuesDataset.getYValues();

		OriginalValuesDataset = k.getDataset("dataset0");
		OriginalValues = OriginalValuesDataset.getYValues();

		double[] TransformedValue =
			new double[java.lang.reflect.Array.getLength(OriginalValues)];
		int j = 0;
		valueOk = new double[java.lang.reflect.Array.getLength(OriginalValues)];

		for (int i = 0;
			i < java.lang.reflect.Array.getLength(OriginalValues);
			i++) {
			if ((OriginalValues[i] >= OriginalMinMaxValues[j])
				&& (OriginalValues[i] <= OriginalMinMaxValues[j + 1])) {
				TransformedValue[i] =
					minKiviat
						+ ((OriginalValues[i] - OriginalMinMaxValues[j])
							* (maxKiviat - minKiviat))
							/ (OriginalMinMaxValues[j
								+ 1]
								- OriginalMinMaxValues[j]);
				valueOk[i] = 1;
			}

			if ((OriginalValues[i] > OriginalMinMaxValues[j + 1])) {
				TransformedValue[i] = maxKiviat * 1.15;
				if (OriginalValues[i] == 10000000000.00) {
					valueOk[i] = -1;
				} else {
					valueOk[i] = 0;
				}
			}

			if ((OriginalValues[i] < OriginalMinMaxValues[j])) {
				TransformedValue[i] = minKiviat / 1.5;
				if (OriginalValues[i] == 10000000000.00) {
					valueOk[i] = -1;
				} else {
					valueOk[i] = 0;
				}
			}

			if ((OriginalValues[i] == OriginalMinMaxValues[j])
				&& (OriginalValues[i] == OriginalMinMaxValues[j + 1])) {
				TransformedValue[i] = minKiviat + ((maxKiviat - minKiviat) / 2);
				valueOk[i] = 1;
			}
			j = j + 2;
		}
		k.addDataset("valueOk", valueOk);
		OriginalValuesDataset.replaceYData(TransformedValue);
	}

	/**
	* Return current KiviatCom
	*/
	public KiviatCom getkiviatCom() {
		return this;

	}

}
