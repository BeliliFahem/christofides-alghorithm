package pl.wat.tal.misc;

public class ProbabilityFieldFilter extends FieldFilter {

	@Override
	protected boolean test(String text) {
		try {
			double number = Double.parseDouble(text);
			if(number >= 0 && number <= 1)
				return true;
			else
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
