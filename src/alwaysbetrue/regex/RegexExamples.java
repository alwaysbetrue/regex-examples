package alwaysbetrue.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular Expressions
 * 
 * This class shows several examples of regular expression patterns and results
 * to illustrate the concepts.
 * 
 * @author Dave Schick
 *
 */

public class RegexExamples {

	private static String regex = "";
	private static String inputString = "";

	public static void main(String[] args) {
		/*
		 * Simple pattern match
		 */
		regex = "cubs";
		inputString = "cubscubscubs";
		showMeTheResults(regex, inputString);
		// I found the text "cubs" starting at index 0 and ending at index 4.
		// I found the text "cubs" starting at index 4 and ending at index 8.
		// I found the text "cubs" starting at index 8 and ending at index 12.

		/*
		 * Using the . metacharacter to match on any String
		 */
		regex = "cub.";
		inputString = "cubs";
		showMeTheResults(regex, inputString);
		// I found the text "cubs" starting at index 0 and ending at index 4.

		/*
		 * Escaping the . so java doesn't treat it as a . metacharacter
		 */
		regex = "cub\\.";
		inputString = "cub.s";
		showMeTheResults(regex, inputString);
		// I found the text "cub." starting at index 0 and ending at index 4.

		/*
		 * Character class example. Matches on any of the single letters in the
		 * class.
		 */
		regex = "Cubs [HMB]at";
		inputString = "Cubs Hat";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs Hat" starting at index 0 and ending at index
		// 8.
		inputString = "Cubs Bat";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs Bat" starting at index 0 and ending at index
		// 8.
		inputString = "Cubs Mat";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs Bat" starting at index 0 and ending at index
		// 8.

		/*
		 * Negation. Match all characters EXCEPT those in the class
		 */

		regex = "Cubs [^HMB]at";
		inputString = "Cubs Hat";
		showMeTheResults(regex, inputString);
		// No match found.

		inputString = "Cubs Bat";
		showMeTheResults(regex, inputString);
		// No match found.

		inputString = "Cubs Rat";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs Rat" starting at index 0 and ending at index
		// 8.

		/*
		 * Ranges. Match all characters in the range specified.
		 */

		regex = "[abc]";
		inputString = "d";
		showMeTheResults(regex, inputString);
		// No match found.
		inputString = "b";
		showMeTheResults(regex, inputString);
		// I found the text "b" starting at index 0 and ending at index 1.

		regex = "Cubs[1-5]";
		inputString = "Cubs6";
		showMeTheResults(regex, inputString);
		// No match found.

		inputString = "Cubs4";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs4" starting at index 0 and ending at index 5.

		regex = "Cubs[^1-5]";
		inputString = "Cubs6";
		showMeTheResults(regex, inputString);
		// I found the text "Cubs6" starting at index 0 and ending at index 5.

		inputString = "Cubs4";
		showMeTheResults(regex, inputString);
		// No match found.

		/*
		 * UNION single character class examples
		 */
		regex = "[0-4[6-8]]";
		inputString = "Cubs3";
		showMeTheResults(regex, inputString);
		// I found the text "3" starting at index 4 and ending at index 5.
		inputString = "Cubs7";
		showMeTheResults(regex, inputString);
		// I found the text "7" starting at index 4 and ending at index 5.
		inputString = "Cubs5";
		showMeTheResults(regex, inputString);
		// No match found.
		inputString = "Cubs9";
		showMeTheResults(regex, inputString);
		// No match found.

		/*
		 * INTERSECTION single character classes
		 */
		regex = "[0-9&&[345]]";
		inputString = "4";
		showMeTheResults(regex, inputString);
		// I found the text "4" starting at index 0 and ending at index 1.
		inputString = "7";
		showMeTheResults(regex, inputString);
		// No match found.

		regex = "[2-8&&[4-6]]";
		inputString = "5";
		showMeTheResults(regex, inputString);
		// I found the text "5" starting at index 0 and ending at index 1.

		inputString = "7";
		showMeTheResults(regex, inputString);
		// No match found.

		/*
		 * SUBTRACTION examples. Creates single character class that matches
		 * everything EXCEPT 3,4,5
		 */
		regex = "[0-9&&[^345]]";
		inputString = "331";
		showMeTheResults(regex, inputString);
		// I found the text "1" starting at index 2 and ending at index 3.

		inputString = "334555";
		showMeTheResults(regex, inputString);
		// No match found.

		/*
		 * Pre-Defined Character Class Examples
		 */
		// Any character .
		regex = ".";
		inputString = "#";
		showMeTheResults(regex, inputString);
		// I found the text "#" starting at index 0 and ending at index 1.

		// Digits only
		regex = "\\d";
		inputString = "9";
		showMeTheResults(regex, inputString);
		// I found the text "9" starting at index 0 and ending at index 1.

		inputString = "g";
		showMeTheResults(regex, inputString);
		// No match found.

		// NON-Digits only
		regex = "\\D";
		inputString = "9";
		showMeTheResults(regex, inputString);
		// No match found.

		inputString = "g";
		showMeTheResults(regex, inputString);
		// I found the text "g" starting at index 0 and ending at index 1.

		// Whitespace characters
		regex = "\\s";
		inputString = "a b";
		showMeTheResults(regex, inputString);
		// I found the text " " starting at index 1 and ending at index 2.

		inputString = "abc";
		showMeTheResults(regex, inputString);
		// No match found.

		// NON-whitespace characters
		regex = "\\S";
		inputString = " ab";
		showMeTheResults(regex, inputString);
		// Returns 2 groups:
		// I found the text "a" starting at index 1 and ending at index 2.
		// I found the text "b" starting at index 2 and ending at index 3.

		inputString = " ";
		showMeTheResults(regex, inputString);
		// No match found.

		/*
		 * Word Character examples
		 */

		// Word characters
		regex = "\\w";
		inputString = "a9";
		showMeTheResults(regex, inputString);
		// Returns 2 groups:
		// I found the text "a" starting at index 0 and ending at index 1.
		// I found the text "9" starting at index 1 and ending at index 2.

		inputString = "&%";
		showMeTheResults(regex, inputString);
		// No match found.

		// NON-word characters
		regex = "\\W";
		inputString = "a9";
		showMeTheResults(regex, inputString);
		// No match found.
		// No match found.

		inputString = "&%";
		showMeTheResults(regex, inputString);
		// Returns 2 groups:
		// I found the text "&" starting at index 0 and ending at index 1.
		// I found the text "%" starting at index 1 and ending at index 2.

		/*
		 * QUANTIFIERS
		 */
		// Greedy -- zero occurrences
		regex = "a?";
		inputString = "";
		showMeTheResults(regex, inputString);
		// I found the text "" starting at index 0 and ending at index 0.
		// Greedy allows zero occurrences of the letter 'a'

		// Reluctant - zero occurrences
		regex = "a*";
		inputString = "";
		showMeTheResults(regex, inputString);
		// I found the text "" starting at index 0 and ending at index 0.
		// Reluctant allows zero occurrences of the letter 'a'

		// Possessive - zero occurrences
		regex = "a+";
		inputString = "";
		showMeTheResults(regex, inputString);
		// No match found
		// Possessive does NOT allow zero occurrences of the letter 'a'

		// Greedy -- Mix of found and not found
		regex = "a?";
		inputString = "ababaaaab";
		showMeTheResults(regex, inputString);
		// I found the text "a" starting at index 0 and ending at index 1.
		// I found the text "" starting at index 1 and ending at index 1.
		// I found the text "a" starting at index 2 and ending at index 3.
		// I found the text "" starting at index 3 and ending at index 3.
		// I found the text "a" starting at index 4 and ending at index 5.
		// I found the text "a" starting at index 5 and ending at index 6.
		// I found the text "a" starting at index 6 and ending at index 7.
		// I found the text "a" starting at index 7 and ending at index 8.
		// I found the text "" starting at index 8 and ending at index 8.
		// I found the text "" starting at index 9 and ending at index 9.
		// Greedy only cares about finding 'a' - 'b' is considered a zero match

		// Reluctant -- Mix of found and not found
		regex = "a*";
		inputString = "ababaaaab";
		showMeTheResults(regex, inputString);
		// I found the text "a" starting at index 0 and ending at index 1.
		// I found the text "" starting at index 1 and ending at index 1.
		// I found the text "a" starting at index 2 and ending at index 3.
		// I found the text "" starting at index 3 and ending at index 3.
		// I found the text "aaaa" starting at index 4 and ending at index 8.
		// I found the text "" starting at index 8 and ending at index 8.
		// I found the text "" starting at index 9 and ending at index 9.
		// Reluctant calls 'b' zero length match and multiple occurrences of 'a'
		// a single match

		// Possessive -- Mix of found and not found
		regex = "a+";
		inputString = "ababaaaab";
		showMeTheResults(regex, inputString);
		// I found the text "a" starting at index 0 and ending at index 1.
		// I found the text "a" starting at index 2 and ending at index 3.
		// I found the text "aaaa" starting at index 4 and ending at index 8.
		// Possessive doesn't return the zero length matches on 'b'

		/*
		 * Match a pattern 'n' number of times
		 */
		regex = "a{3}";
		inputString = "aa";
		showMeTheResults(regex, inputString);
		// No match found.

		regex = "a{3}";
		inputString = "aaa";
		showMeTheResults(regex, inputString);
		// I found the text "aaa" starting at index 0 and ending at index 3.

		regex = "a{3}";
		inputString = "aaaa";
		showMeTheResults(regex, inputString);
		// I found the text "aaa" starting at index 0 and ending at index 3.

		regex = "a{3,}";
		inputString = "aaaaaaaaa";
		showMeTheResults(regex, inputString);
		// I found the text "aaaaaaaaa" starting at index 0 and ending at index
		// 9.

		regex = "a{3,6}";
		inputString = "aaaaaaaaa";
		showMeTheResults(regex, inputString);
		// I found the text "aaaaaa" starting at index 0 and ending at index 6.
		// I found the text "aaa" starting at index 6 and ending at index 9.
		// First match was forced to stop at 6 occurrences.
		// Second match found the next 3 occurrences, which is the minimum # for
		// a match.

		regex = "a{3,6}";
		inputString = "aaaaaaaa";
		showMeTheResults(regex, inputString);
		// I found the text "aaaaaa" starting at index 0 and ending at index 6.
		// Only one match this time, because only "aa" was left over, which is
		// under the minimum limit of 3 for a match.

		/*
		 * CAPTURING GROUPS
		 */
		regex = "cubs{3}";
		inputString = "cubscubscubscubscubscubs";
		showMeTheResults(regex, inputString);
		// No match found.
		// This is looking for the letter 's' 3 times in a row.

		regex = "(cubs){3}";
		inputString = "cubscubscubscubscubscubs";
		showMeTheResults(regex, inputString);
		// I found the text "cubscubscubs" starting at index 0 and ending at
		// index 12.
		// I found the text "cubscubscubs" starting at index 12 and ending at
		// index 24.
		// This is looking for group 'cubs' 3 times in a row. Found it twice.

		regex = "[cub]{3}";
		inputString = "cubbucucb";
		showMeTheResults(regex, inputString);
		// I found the text "cub" starting at index 0 and ending at index 3.
		// I found the text "buc" starting at index 3 and ending at index 6.
		// I found the text "ucb" starting at index 6 and ending at index 9.
		// Matches on any combination of letters 'c,u,b' that occur 3 in a row.

		// Greedy quantifier
		regex = ".*cub";
		inputString = "xcubxxxxxxcub";
		showMeTheResults(regex, inputString);
		// I found the text "xcubxxxxxxcub" starting at index 0 and ending at
		// index 13.
		// .* says "find anything zero or more times" which is satisfied first
		// before it even gets to 'cub'

		// Reluctant Quantifier
		regex = ".?cub";
		inputString = "xcubxxxxxxcub";
		showMeTheResults(regex, inputString);
		// I found the text "xcub" starting at index 0 and ending at index 4.
		// I found the text "xcub" starting at index 9 and ending at index 13.
		// Find anything 0 or more times (one character) but start at beginning
		// of the string - then look for 'cub'. So finds 'x'

		// Possessive quantifier
		regex = ".*+cub";
		inputString = "xcubxxxxxxcub";
		showMeTheResults(regex, inputString);

		// No match found.
		// Entire string's consumed by '.*+' - nothing left over to satisfy the
		// 'cub'

		/*
		 * CAPTURING GROUPS
		 */
		regex = "((A)(B(C)))";
		inputString = "Go Cubs Go!  Go Cubs Go!";
		showMeTheResultsWithGroupCount(regex, inputString);
		// Number of Capturing Groups in this Regex: 4
		// No matches found.

		// Referencing a capturing group in the regex
		regex = "(\\d\\d)\\1";
		inputString = "1212";
		showMeTheResultsWithGroupCount(regex, inputString);
		// Find any 2 digits that repeat.
		// The first captured group is the 2 repeating digits and is referenced
		// in the regex
		// I found the text "1212" starting at index 0 and ending at index 4.

		// This one won't find any results
		regex = "(\\d\\d)\\1";
		inputString = "1234";
		showMeTheResultsWithGroupCount(regex, inputString);
		// Find any 2 digits that repeat.
		// The first captured group is the 2 repeating digits and is referenced
		// in the regex
		// Since no 2 numbers repeat, nothing's returned
		// No results found

		/*
		 * BOUNDARY MATCHERS
		 */
		regex = "^Cubs$";
		inputString = "Cubs ";
		showMeTheResults(regex, inputString);
		// Requires Cubs to be at the beginning of the line with nothing after
		// it.
		// No results found

		regex = "^Cubs\\s$";
		inputString = "Cubs ";
		showMeTheResults(regex, inputString);
		// Requires Cubs to be at the beginning of the line and whitespace is
		// allowed before the end of the line.
		// I found the text "Cubs " starting at index 0 and ending at index 5.

		regex = "^Cubs\\s$";
		inputString = "Cubs Rock";
		showMeTheResults(regex, inputString);
		// Requires Cubs to be at the beginning of the line and whitespace is
		// allowed before the end of the line.
		// No match found
		// More than just whitespace between Cubs and end of the line.

		regex = "\\sCubs$";
		inputString = "          Cubs";
		showMeTheResults(regex, inputString);
		// Requires Cubs to be at the end of the line, with any amount of
		// whitespace at the beginning.
		// I found the text " Cubs" starting at index 9 and ending at index 14.

		regex = "^Cubs\\w*";
		inputString = "Cubswinandwinandwin";
		showMeTheResults(regex, inputString);
		// Requires Cubs to be at the beginning of the line followed by any
		// number of word characters.
		// I found the text "Cubswinandwinandwin" starting at index 0 and ending
		// at index 19.

		// Word boundaries
		regex = "\\bCub\\b";
		inputString = "Anthony Rizzo wins Cub of the Year";
		showMeTheResults(regex, inputString);
		// Matches on the exact word boundary
		// I found the text "Cub" starting at index 19 and ending at index 22.

		// Word boundaries
		regex = "\\bCub\\b";
		inputString = "Will the Cubs win it all this year?";
		showMeTheResults(regex, inputString);
		// Matches on the exact word boundary - so there's no match for just
		// "Cub"
		// No match found.

		/*
		 * Comprehensive Example
		 */
		regex = "\\S*-x1[zyq]-(\\d*)-\\d*-88888-bufferzone";
		inputString = "examplecompany-division-type-domain-x1q-bb|awesomeapp|examplecompany-division-type-domain-x1z-444632-9-88888-bufferzone|";
		showMeTheResultsAndOnlyDisplayOneGroup(regex, inputString, 1);
		// I found the text "444632" starting at index 0 and ending at index 93.
		// Looks for match starting with any NON-whitespace characters, followed
		// by "-x1" and any single character 'z,y,q', followed by a dash,
		// followed by our target group (any collection of digits), followed by
		// a dash and any number of digits, followed by a dash and '88888',
		// followed by '-bufferzone'.
		// The method we're calling only returns the first group - so that's all
		// that's returned in the output (444632).

	}

	/**
	 * Given a regex string and an input string to match against, writes the
	 * results to the console, along with the starting and ending index
	 * positions.
	 * 
	 * @param regex
	 * @param inputString
	 */
	public static void showMeTheResults(String regex, String inputString) {
		String result = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputString);
		boolean found = false;
		while (matcher.find()) {
			result = String.format(
					"I found the text" + " \"%s\" starting at "
							+ "index %d and ending at index %d.%n",
					matcher.group(), matcher.start(), matcher.end());
			System.out.println(result);
			found = true;
		}
		if (!found) {
			result = "No match found.";
			System.out.println(result);
		}
	};

	/**
	 * Given a regex string and an input string to match against, writes the
	 * results to the console, along with the starting and ending index
	 * positions. Only displays the group number from input.
	 * 
	 * @param regex
	 * @param inputString
	 * @param groupNumber
	 */
	public static void showMeTheResultsAndOnlyDisplayOneGroup(String regex,
			String inputString, int groupNumber) {
		String result = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputString);
		boolean found = false;
		while (matcher.find()) {
			result = String.format(
					"I found the text" + " \"%s\" starting at "
							+ "index %d and ending at index %d.%n",
					matcher.group(groupNumber), matcher.start(), matcher.end());
			System.out.println(result);
			found = true;
		}
		if (!found) {
			result = "No match found.";
			System.out.println(result);
		}
	};

	/**
	 * Given a regex string and an input string to match against, writes the
	 * results to the console, along with the starting and ending index
	 * positions. Also prints out the group count.
	 * 
	 * @param regex
	 * @param inputString
	 */
	public static void showMeTheResultsWithGroupCount(String regex,
			String inputString) {
		String result = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputString);
		System.out.println("Number of Capturing Groups in this Regex: "
				+ matcher.groupCount());
		boolean found = false;
		while (matcher.find()) {
			result = String.format(
					"I found the text" + " \"%s\" starting at "
							+ "index %d and ending at index %d.%n",
					matcher.group(), matcher.start(), matcher.end());
			System.out.println(result);
			found = true;
		}
		if (!found) {
			result = "No match found.";
			System.out.println(result);
		}
	};

}