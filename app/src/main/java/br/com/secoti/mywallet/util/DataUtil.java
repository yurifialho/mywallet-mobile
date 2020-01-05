package br.com.secoti.mywallet.util;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DataUtil {

    private DataUtil(){}
    public static final String BR_DATE_FORMAT = "dd/MM/yyyy";

    public static String dateToString(Date data) {
        try {
            return new SimpleDateFormat(BR_DATE_FORMAT).format(data);
        } catch (Exception e) {
            Log.println(Log.ERROR,"ERRO DATE CONVERT", "ERROR IN CONVERSION");
        }
        return "";
    }

    public static Date stringToDate(String data) {
        try {
            return new SimpleDateFormat(BR_DATE_FORMAT).parse(data);
        } catch (Exception e) {
            Log.println(Log.ERROR,"ERRO DATE CONVERT", "ERROR IN CONVERSION");
        }
        return null;
    }
}
