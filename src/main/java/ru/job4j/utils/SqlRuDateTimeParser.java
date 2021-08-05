package ru.job4j.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    @SuppressWarnings("checkstyle:ConstantName")
    private static final Map<String, String> MONTHTONUMBER = new HashMap<>();

    public SqlRuDateTimeParser() {
        MONTHTONUMBER.put("янв", "01");
        MONTHTONUMBER.put("фев", "02");
        MONTHTONUMBER.put("мар", "03");
        MONTHTONUMBER.put("апр", "04");
        MONTHTONUMBER.put("май", "05");
        MONTHTONUMBER.put("июн", "06");
        MONTHTONUMBER.put("июл", "07");
        MONTHTONUMBER.put("авг", "08");
        MONTHTONUMBER.put("сен", "09");
        MONTHTONUMBER.put("окт", "10");
        MONTHTONUMBER.put("ноя", "11");
        MONTHTONUMBER.put("дек", "12");
    }

    @Override
    public LocalDateTime parse(String parse) {
        String[] buffer = parse.replace(",", "").split(" ");
        String[] dateParts = new String[4];
        if (buffer.length == 2) {
            LocalDate date;
            if (buffer[0].equals("сегодня")) {
                date = LocalDate.now();
            } else if (buffer[0].equals("вчера")) {
                date = LocalDate.now().minusDays(1L);
            } else {
                throw new IllegalArgumentException();
            }
            dateParts[0] = String.valueOf(date.getDayOfMonth());
            dateParts[1] = String.format("%02d", date.getMonthValue());
            dateParts[2] = String.valueOf(date.getYear());
            dateParts[3] = buffer[1];
        } else {
            buffer[1] = MONTHTONUMBER.get(buffer[1]);
            int year = Integer.parseInt(buffer[2]);
            if (year < 2000) {
                buffer[2] = String.valueOf(year + 2000);
            }
            dateParts = Arrays.copyOf(buffer, 4);
        }
        String sb = String.join(" ", dateParts[0], dateParts[1], dateParts[2], dateParts[3]);
        return LocalDateTime.parse(sb,
                DateTimeFormatter.ofPattern("d MM yyyy HH:mm"));
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser dtp = new SqlRuDateTimeParser();
        LocalDateTime dt = dtp.parse("13 дек 21, 10:51");
    }
}