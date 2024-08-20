package com.br.javabasic.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String getStringFromDate(Date date) {
        return sdf.format(date);
    }

}
