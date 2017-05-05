package com.hawk.framework.dic.validation.validator;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Synonym;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;
import com.hawk.framework.dic.exception.InvalidWordRuntimeException;
import com.hawk.framework.dic.exception.WordNotFoundRuntimeException;
import com.hawk.framework.dic.service.DictionaryService;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.utility.tools.BooleanTools;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class ValidWordValidator implements ConstraintValidator<ValidWord, Object> {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void valid(ValidWord annotation, Object value, String code) throws EmptyParameterRuntimeException {

		/**
		 * 优先取注解里的code
		 */
		String wordCode = annotation.value();

		/**
		 * 注解找不到，才取传入的code
		 */
		if (StringTools.isNullOrEmpty(wordCode)) {
			wordCode = code;
		}
		Word word = dictionaryService.queryWord(wordCode);

		if (word == null)
			throw new WordNotFoundRuntimeException(wordCode);

		String displayName = annotation.name();
		if (StringTools.isNullOrEmpty(displayName)) {
			Synonym synonym = dictionaryService.querySynonym(wordCode);
			if (synonym != null) {
				displayName = synonym.getSynonymDisplayName();
			} else {
				displayName = word.getName();
			}
		}

		if (value == null) {
			if (!annotation.nullable()) {
				throw new EmptyParameterRuntimeException(displayName);
			} else {
				return;
			}
		}

		if (word.getDataType() == EnumDataType.String) {
			checkValue(word, (String) value, displayName);
		} else if (word.getDataType() == EnumDataType.Long) {
			checkValue(word, (Long) value, displayName);
		} else if (word.getDataType() == EnumDataType.Integer) {
			checkValue(word, (Integer) value, displayName);
		} else if (word.getDataType() == EnumDataType.Numeric) {
			checkValue(word, (BigDecimal) value, displayName);
		} else if (word.getDataType() == EnumDataType.Date) {
			checkValue(word, (Date) value, displayName);
		}

	}

	private String buildMessage(String displayName, String patternComment, String localComment) {
		return StringTools.isNotNullOrEmpty(patternComment) ? displayName + "必须符合:" + patternComment : displayName + "必须符合:" + localComment;
	}

	private void checkValue(Word word, Date value, String displayName) {
		String strMax = word.getMaxValue();
		String patternComment = word.getPatternComment();
		String localComment = "";
		if (StringTools.isNotNullOrEmpty(strMax)){
			Date maxDate = DateTools.parse(strMax, DateTools.DATETIME_PATTERN);
			if (value.after(maxDate)){
				localComment = "不能大于"+strMax;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
		
		String strMin = word.getMinValue();
		patternComment = word.getPatternComment();
		localComment = "";
		if (StringTools.isNotNullOrEmpty(strMin)){
			Date minDate = DateTools.parse(strMin, DateTools.DATETIME_PATTERN);
			if (value.before(minDate)){
				localComment = "不能小于"+strMin;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
	}
	
	public static void main(String[] args ){
		BigDecimal b = new BigDecimal("100.0000");
		System.out.println("scale = " + b.scale());
		System.out.println("precision = " + b.precision());
	}

	private void checkValue(Word word, BigDecimal value, String displayName) {
		Integer precision = word.getNumericPrecision(); //有效数字
		Integer scale = word.getNumericScale(); //小数位数 
		String patternComment = word.getPatternComment();
		String localComment = "";
		if (scale != null){
			if (value.scale() > scale){
				localComment = "小数位数不能大于"+scale;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
		
		if (precision != null){
			if (value.precision() > precision){
				localComment = "有效 位数不能大于"+precision;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
		String strMax = word.getMaxValue();
		if (StringTools.isNotNullOrEmpty(strMax)){
			if (value.compareTo(new BigDecimal(strMax))>0){
				localComment = "不能大于"+strMax;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
		String strMin = word.getMinValue();
		if (StringTools.isNotNullOrEmpty(strMin)){
			if (value.compareTo(new BigDecimal(strMin))<0){
				localComment = "不能小于"+strMin;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}
		
	}

	private void checkValue(Word word, Integer value, String displayName) {
		String strMax = word.getMaxValue();
		int max = StringTools.isNullOrEmpty(strMax) ? Integer.MAX_VALUE : Integer.parseInt(strMax);
		String strMin = word.getMinValue();
		int min = StringTools.isNullOrEmpty(strMin) ? Integer.MIN_VALUE : Integer.parseInt(strMin);
		String patternComment = word.getPatternComment();
		String localComment = "";
		if (value > max) {
			localComment = "不能大于" + max;
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		if (value < min) {
			localComment = "不能小于" + min;
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		if (BooleanTools.parse(word.getIsEnum())) {
			if (word.getEnumValues().get(value.toString()) == null) {
				StringTools.concatWithSymbol(",", word.getEnumValues().keySet());
				localComment = "值只能是" + "其中之一";
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}

	}

	private void checkValue(Word word, Long value, String displayName) {
		String strMax = word.getMaxValue();
		long max = StringTools.isNullOrEmpty(strMax) ? Long.MAX_VALUE : Long.parseLong(strMax);
		String strMin = word.getMinValue();
		long min = StringTools.isNullOrEmpty(strMin) ? Long.MIN_VALUE : Long.parseLong(strMin);
		String patternComment = word.getPatternComment();
		String localComment = "";
		if (value > max) {
			localComment = "不能大于" + max;
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		if (value < min) {
			localComment = "不能小于" + min;
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		// if (BooleanTools.parse(word.getIsEnum())){
		// if (word.getEnumValues().get(value.toString()) == null){
		// StringTools.concatWithSymbol(",", word.getEnumValues().keySet());
		// localComment = "值只能是" +"其中之一";
		// throw new
		// InvalidWordRuntimeException(buildMessage(displayName,patternComment,localComment));
		// }
		// }

	}

	private void checkValue(Word word, String value, String displayName) {
		Integer maxLength = word.getCharMaxLength();
		Integer minLength = word.getCharMinLength();
		String regex = word.getRegex();

		String patternComment = word.getPatternComment();

		boolean fixedLength = (maxLength != null && maxLength.equals(minLength));
		String localComment = "";
		if (fixedLength) {
			localComment = "定长" + maxLength;
		}

		if (maxLength != null && value.length() > maxLength) {

			if (!fixedLength) {
				localComment = "长度不大于" + maxLength;
			}
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		if (minLength != null && value.length() < minLength) {
			if (!fixedLength) {
				localComment = "长度不小于" + minLength;
			}
			throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
		}

		if (StringTools.isNotNullOrEmpty(regex)) {
			if (!value.matches(regex)) {
				localComment = "匹配正则表达式" + regex;
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}

		if (BooleanTools.parse(word.getIsEnum())) {
			if (word.getEnumValues().get(value) == null) {
				StringTools.concatWithSymbol(",", word.getEnumValues().keySet());
				localComment = "值只能是" + "其中之一";
				throw new InvalidWordRuntimeException(buildMessage(displayName, patternComment, localComment));
			}
		}

	}

}
