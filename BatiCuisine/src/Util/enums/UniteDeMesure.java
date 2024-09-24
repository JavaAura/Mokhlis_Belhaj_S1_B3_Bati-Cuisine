package Util.enums;

/**
 * Enumeration representing different units of measurement used in the BatiCuisine application.
 */
public enum UniteDeMesure {
	/** Represents the meter unit (m) */
	METRE("m"),
	/** Represents the square meter unit (m²) */
	METRE_CARRE("m²"),
	/** Represents the kilogram unit (kg) */
	KILOGRAMME("kg"),
	/** Represents the liter unit (L) */
	LITRE("L"),
	/** Represents a piece or unit count (pce) */
	PIECE("pce");

	/** The symbol associated with the unit of measurement */
	private final String symbol;

	/**
	 * Constructs a UniteDeMesure enum constant with the specified symbol.
	 *
	 * @param symbol The symbol representing the unit of measurement
	 */
	UniteDeMesure(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Returns the symbol associated with this unit of measurement.
	 *
	 * @return The symbol as a String
	 */
	public String getSymbol() {
		return symbol;
	}

}
