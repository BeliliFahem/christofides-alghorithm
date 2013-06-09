package pl.wat.tal.misc;

public class IntegerFieldFilter extends FieldFilter {

	@Override
	protected boolean test(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
