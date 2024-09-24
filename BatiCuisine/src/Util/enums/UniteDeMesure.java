package Util.enums;

public enum UniteDeMesure {
	 METRE("m"),
	    METRE_CARRE("m²"),
	    KILOGRAMME("kg"),
	    LITRE("L"),
	    PIECE("pce");

	    private final String symbol;

	    UniteDeMesure(String symbol) {
	        this.symbol = symbol;
	    }

	    public String getSymbol() {
	        return symbol;
	    }

}
