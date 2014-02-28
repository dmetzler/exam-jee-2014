package org.isen.jee.billing.api;

/**
 * Enumère les différents type de cartes possible. Pour récupérer le maximum du
 * numéro CCV, on pourra utiliser le code suivant :
 * 
 * <code>
 * 	CCType.VIS.getMaxCCVNumber();
 * </code>
 * 
 * @author dmetzler
 * 
 */
public enum CCType {
	VISA(1000), AMEX(10000);

	private int maxCCVNumber;

	private CCType(int maxCCVNumber) {
		this.maxCCVNumber = maxCCVNumber;
	}

	public int getMaxCCVNumber() {
		return maxCCVNumber;
	}

}
