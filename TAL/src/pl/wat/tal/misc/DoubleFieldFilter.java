package pl.wat.tal.misc;

public class DoubleFieldFilter extends FieldFilter {

	@Override
	protected boolean test(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
