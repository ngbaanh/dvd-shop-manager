package util;
/**
 * @author ngbaanh
 *
 */
public class Util {	
	/**
	 * escape HTML characters to prevent XSS attack
	 * @param in
	 * @return encoded string
	 */
	public static String escapeHTML(String in) {
		if (in == null) {
			throw new NullPointerException();
		}
		return in.replaceAll("\\<", "&lt;")
				.replaceAll("\\>", "&gt;")
				.replaceAll("\\\"", "&quot;")
				.replaceAll("\\&", "&amp;")
				.replaceAll("'", "&#x27;")
				.replaceAll("\\/", "&#x2F;");
	}
	
	/**
	 * keep necessary part of a string
	 * @param in
	 * @return beautified string
	 */
	public static String beautify(String in) {
		if (in == null) {
			throw new NullPointerException();
		}
		return in.trim().replaceAll("\\s+", " ");
	}


}
