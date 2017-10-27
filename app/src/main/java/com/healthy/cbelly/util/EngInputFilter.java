package com.healthy.cbelly.util;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by chan1park 17.05.02
 *
 * 영문 필터 (영문, 숫자만 입력 가능)
 */
public class EngInputFilter implements InputFilter {

    private String mRegularExpression;

    public EngInputFilter(String regularExpression) {
        this.mRegularExpression = regularExpression;
    }

    /**
     * String * 정규 표현식
     * 참조 : http://blog.daum.net/question0921/419
     * 참조 : http://sexy.pe.kr/tc/113(유니코드 한글 영문 코드 범위 {AC00-D7AF})
     */
    public static final String REGULAR_EXPRESSION_WITH_SPACE = "^[\\p{space}a-zA-Z0-9]*$";
    public static final String REGULAR_EXPRESSION = "^[a-zA-Z0-9]*$";

    /**
     * 허용할 특수기호 리스트
     * '.', ',', '@', '(', ')', '-', '_', '/'
     */
    public static final char REGULAR_POINT = '.';
    public static final char REGULAR_COMMA = ',';
    public static final char REGULAR_AT = '@';
    public static final char REGULAR_LEFT_BRACKET = '(';
    public static final char REGULAR_RIGHT_BRACKET = ')';
    public static final char REGULAR_HYPHEN = '-';
    public static final char REGULAR_UNDER_BAR = '_';
    public static final char REGULAR_SLASH = '/';

    /**
     *
     * @param source
     * @param start
     * @param end
     * @param dest
     * @param dstart
     * @param dend
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Pattern pattern = Pattern.compile(mRegularExpression);

        boolean keepOriginal = true;
        StringBuilder stringBuilder = new StringBuilder(end - start);

        for (int i = start; i < end; i++) {
            char c = source.charAt(i);

            if (pattern.matcher(Character.toString(c)).matches()) {
                stringBuilder.append(c);
            } else {
                keepOriginal = false;
            }

            if(matchRegular(c)) {
                stringBuilder.append(c);
            }
        }

        if (keepOriginal) {
            return null;
        } else {
            if (source instanceof Spanned) {
                SpannableString spannableString = new SpannableString(stringBuilder);
                TextUtils.copySpansFrom((Spanned) source, start, stringBuilder.length(), null,
                    spannableString, 0);
                return spannableString;
            } else {
                return stringBuilder;
            }
        }
    }

    /**
     * 일치하는 특수기호인지 검사
     */
    public boolean matchRegular(char c) {
        return (REGULAR_POINT == c ||
            REGULAR_COMMA == c ||
            REGULAR_AT == c ||
            REGULAR_LEFT_BRACKET == c ||
            REGULAR_RIGHT_BRACKET == c ||
            REGULAR_HYPHEN == c ||
            REGULAR_UNDER_BAR == c ||
            REGULAR_SLASH == c);
    }
}
