package pl.wat.tal.misc;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public abstract class FieldFilter extends DocumentFilter {
	private Toolkit toolkit;
	private Document doc;
	private StringBuilder sb;
	
	public FieldFilter() {
		toolkit = Toolkit.getDefaultToolkit();
	}
	
	protected abstract boolean test(String text);

	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {
		doc = fb.getDocument();
		sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);
		
		if(test(sb.toString())){
			super.insertString(fb, offset, string, attr);
		}
		else{
			toolkit.beep();  // dzwiek ze wstawiony element nie jest liczba
		}
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {
		doc = fb.getDocument();
		sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);
		
		if(test(sb.toString())){
			super.replace(fb, offset, length, text, attrs);
		}
		else{
			toolkit.beep();
		}
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		doc = fb.getDocument();
		sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);
		
		if(test(sb.toString()) || sb.toString().isEmpty()){
			super.remove(fb, offset, length);
		}
		else{
			toolkit.beep();
		}
	}
}
