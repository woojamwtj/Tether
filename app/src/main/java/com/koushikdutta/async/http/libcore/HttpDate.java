package com.koushikdutta.async.http.libcore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class HttpDate {
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMATS = new String[]{"EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
    private static final ThreadLocal<DateFormat> STANDARD_DATE_FORMAT = new C01451();

    /* renamed from: com.koushikdutta.async.http.libcore.HttpDate$1 */
    static class C01451 extends ThreadLocal<DateFormat> {
        C01451() {
        }

        /* Access modifiers changed, original: protected */
        public DateFormat initialValue() {
            DateFormat rfc1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
            rfc1123.setTimeZone(TimeZone.getTimeZone("UTC"));
            return rfc1123;
        }
    }

    public static Date parse(String value) {
        try {
            return ((DateFormat) STANDARD_DATE_FORMAT.get()).parse(value);
        } catch (ParseException e) {
            String[] strArr = BROWSER_COMPATIBLE_DATE_FORMATS;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                try {
                    return new SimpleDateFormat(strArr[i], Locale.US).parse(value);
                } catch (ParseException e2) {
                    i++;
                }
            }
            return null;
        }
    }

    public static String format(Date value) {
        return ((DateFormat) STANDARD_DATE_FORMAT.get()).format(value);
    }
}
