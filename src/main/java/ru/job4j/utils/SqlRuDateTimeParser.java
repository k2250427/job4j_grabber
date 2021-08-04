package ru.job4j.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SqlRuDateTimeParser implements DateTimeParser {

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
            switch (buffer[1]) {
                case "янв":
                    buffer[1] = "01";
                    break;
                case "фев":
                    buffer[1] = "02";
                    break;
                case "мар":
                    buffer[1] = "03";
                    break;
                case "апр":
                    buffer[1] = "04";
                    break;
                case "май":
                    buffer[1] = "05";
                    break;
                case "июн":
                    buffer[1] = "06";
                    break;
                case "июл":
                    buffer[1] = "07";
                    break;
                case "авг":
                    buffer[1] = "08";
                    break;
                case "сен":
                    buffer[1] = "09";
                    break;
                case "окт":
                    buffer[1] = "10";
                    break;
                case "ноя":
                    buffer[1] = "11";
                    break;
                case "дек":
                    buffer[1] = "12";
                    break;
                default:
                    buffer[1] = "01";
            }
            int year = Integer.parseInt(buffer[2]);
            if (year < 2000) {
                buffer[2] = String.valueOf(year + 2000);
            }
            dateParts = Arrays.copyOf(buffer, 4);
        }
        String sb = "";
        sb = sb.concat(dateParts[0]);
        sb = sb.concat(" ");
        sb = sb.concat(dateParts[1]);
        sb = sb.concat(" ");
        sb = sb.concat(dateParts[2]);
        sb = sb.concat(" ");
        sb = sb.concat(dateParts[3]);
        return LocalDateTime.parse(sb,
                DateTimeFormatter.ofPattern("d MM yyyy HH:mm"));
    }

    public static void main(String[] args) {
        SqlRuDateTimeParser dtp = new SqlRuDateTimeParser();
        LocalDateTime dt = dtp.parse("13 дек 21, 10:51");
    }
}