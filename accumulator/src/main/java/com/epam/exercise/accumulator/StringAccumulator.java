package com.epam.exercise.accumulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class StringAccumulator {

	public static final String DEF_DELIMITER_1 = ",";
	public static final String DEF_DELIMITER_2 = "\n";
	public static final String DELIMITER_BEGIN = "//";
	public static final String DELIMITER_END = "\n";
	public static final String DELIMITER_OF_DELIM = "|";

	private Set<String> delimiters = new HashSet<String>(Arrays.asList(new String[] { DEF_DELIMITER_1, DEF_DELIMITER_2 }));

	/**
	 * Expose method for String accumulator
	 * @param numbers
	 * @return
	 * @throws Exception
	 */
	public int add(String numbers) throws Exception {
		String numbersWithoutDelimiter = parseDelimiter(numbers);
		List<Integer> nums = parseNumbers(numbersWithoutDelimiter);
		String negNums = containNegatives(nums);
		if (negNums.length() > 0) {
			throw new Exception("negatives not allowed " + negNums);
		}
		return add(nums);
	}

	private int add(List<Integer> nums) {
		int total = 0;
		for (int n : nums) {
			if (n > 1000) {
				continue;
			}
			total += n;
		}
		return total;
	}

	private String containNegatives(List<Integer> nums) {
		StringBuffer negatives = new StringBuffer();
		for (int n : nums) {
			if (n < 0) {
				negatives.append(String.valueOf(n)).append(",");
			}
		}
		return negatives.toString();
	}

	private List<Integer> convertToInt(String[] nums) {
		List<Integer> retList = new ArrayList<Integer>();
		if (nums != null && nums.length > 0) {
			for (String str : nums) {
				if (str.trim().equals("")) {
					retList.add(0);
				} else {
					retList.add(Integer.valueOf(str.trim()));
				}
			}
		}
		return retList;
	}

	private List<Integer> parseNumbers(String numbers) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("[");
		for (String d : delimiters) {
			strBuffer.append("[").append(Pattern.quote(d)).append("]");
		}
		strBuffer.append("]");
		String[] nums = numbers.split(strBuffer.toString());
		return convertToInt(nums);
	}

	private String parseDelimiter(String numbers) {
		if (numbers.startsWith(DELIMITER_BEGIN)) {
			int endIdx = numbers.indexOf(DELIMITER_END);
			String tempStr = numbers.substring(2, endIdx);
			String[] tempD = tempStr.split(DELIMITER_OF_DELIM);
			delimiters.addAll(Arrays.asList(tempD));
			return numbers.substring(endIdx);
		} else {
			return numbers;
		}
	}
}
