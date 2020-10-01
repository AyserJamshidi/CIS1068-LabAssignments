public class StringFunctions {

	/**
	 * Returns true if {@code s} contains any quote characters.
	 *
	 * @param s the string to check quotes in.
	 * @return true if this string contains characters <b>"</b>, <b>“</b> or <b>”</b>, false otherwise.
	 */
	public static boolean containsQuotes(String s) {
		return s.contains("\"") || s.contains("“") || s.contains("”");
	}

	public static boolean isQuote(char c) {
		return c == ('\"') || c == ('“') || c == ('”');
	}
}
