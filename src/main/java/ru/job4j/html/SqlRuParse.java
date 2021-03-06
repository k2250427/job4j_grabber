package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.Parse;
import ru.job4j.Post;
import ru.job4j.utils.DateTimeParser;
import ru.job4j.utils.SqlRuDateTimeParser;
import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public List<Post> list(String link) {
        List<Post> list = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(link).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                list.add(detail(href.attr("href")));
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Post detail(String link) {
        Post post = new Post();
        try {
            post.setLink(link);
            Document doc = Jsoup.connect(link).get();
            post.setTitle(doc.select("title").text());
            post.setDescription(doc.select(".msgBody").get(1).text());
            SqlRuDateTimeParser dataParser = new SqlRuDateTimeParser();
            Elements footer = doc.select(".msgFooter");
            String dateStr = footer.get(0).text();
            int pos = dateStr.indexOf('[');
            post.setCreated(dataParser.parse(dateStr.substring(0, pos - 1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static void main(String[] args) {
        DateTimeParser dtParser = new SqlRuDateTimeParser();
        SqlRuParse sqlParse = new SqlRuParse(dtParser);
        Post post;
        List<Post> list;
        post = sqlParse.detail("https://www.sql.ru/forum/1325330/"
                + "lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t");
        System.out.println(post);
        System.out.println("=".repeat(30));
        list = sqlParse.list("https://www.sql.ru/forum/job-offers/1");
        list.forEach(System.out::println);
    }
}
