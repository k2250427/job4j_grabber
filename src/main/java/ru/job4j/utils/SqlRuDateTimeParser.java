package ru.job4j.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SqlRuDateTimeParser implements DateTimeParser {

    @Override
    public LocalDateTime parse(String parse) {
        String buffer = parse.replace(",","");
        if (buffer.contains("сегодня")) {
            buffer = buffer.replace("сегодня",
                    LocalDate.now().format(DateTimeFormatter.ofPattern("d MM yyyy")));
        } else if (buffer.contains("вчера")) {
            buffer = buffer.replace("вчера",
                    LocalDate.now().minusDays(1L).format(DateTimeFormatter.ofPattern("d MM yyyy")));
        } else if (buffer.contains("янв")) {
            buffer = buffer.replace("янв", "01");
        } else if (buffer.contains("фев")) {
            buffer = buffer.replace("фев", "02");
        } else if (buffer.contains("мар")) {
            buffer = buffer.replace("мар", "03");
        } else if (buffer.contains("апр")) {
            buffer = buffer.replace("апр", "04");
        } else if (buffer.contains("май")) {
            buffer = buffer.replace("май", "05");
        } else if (buffer.contains("июн")) {
            buffer = buffer.replace("июн", "06");
        } else if (buffer.contains("июл")) {
            buffer = buffer.replace("июл", "07");
        } else if (buffer.contains("авг")) {
            buffer = buffer.replace("авг", "08");
        } else if (buffer.contains("сен")) {
            buffer = buffer.replace("сен", "09");
        } else if (buffer.contains("окт")) {
            buffer = buffer.replace("окт", "10");
        } else if (buffer.contains("ноя")) {
            buffer = buffer.replace("ноя", "11");
        } else if (buffer.contains("дек")) {
            buffer = buffer.replace("дек", "12");
        }
        return LocalDateTime.parse(buffer,
                DateTimeFormatter.ofPattern("d MM yyyy HH:mm"));
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser dtp = new SqlRuDateTimeParser();
        LocalDateTime dt = dtp.parse("вчера, 10:51");
    }
}