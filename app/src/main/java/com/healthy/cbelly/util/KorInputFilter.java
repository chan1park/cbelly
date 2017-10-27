package com.healthy.cbelly.util;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by chan1park 17.05.02
 *
 * 한글 필터 (한글만 입력 가능)
 */
public class KorInputFilter implements InputFilter
{
    private String mRegularExpression;

    public KorInputFilter(String regularExpression)
    {
        this.mRegularExpression = regularExpression;
    }

    /**
    String * 정규 표현식
     * 참조 : http://blog.daum.net/question0921/419
     * 참조 : http://sexy.pe.kr/tc/113(유니코드 한글 범위표 한글코드 범위 {AC00-D7AF})
     */
    public static final String REGULAR_EXPRESSION_WITH_SPACE = "^[\\p{space}가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7\\uFE55]*$";
    public static final String REGULAR_EXPRESSION = "^[가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025\\u00B7\\uFE55]*$";

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
    {
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
        }

        if (keepOriginal) {
            return null;
        } else {
            if (source instanceof Spanned) {
                SpannableString spannableString = new SpannableString(
                        stringBuilder);
                TextUtils.copySpansFrom((Spanned) source, start, stringBuilder.length(), null, spannableString, 0);
                return spannableString;
            } else {
                return stringBuilder;
            }
        }
    }


}
