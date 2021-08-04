package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private int id;
    private String title;
    private String link;
    private String description;
    private LocalDateTime created;

    public void loadFromLink(String link) throws Exception {
        this.link = link;
        Document doc = Jsoup.connect(link).get();
        title = doc.select("title").text();
        description = doc.select(".msgBody").get(1).text();
        SqlRuDateTimeParser dataParser = new SqlRuDateTimeParser();
        Elements footer = doc.select(".msgFooter");
        created = dataParser.parse(footer.get(0).text().substring(1,16));
    }

    public static void main(String[] args) throws Exception {
        Post post = new Post();
        post.loadFromLink("https://www.sql.ru/forum/1325330/"
                + "lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t");
    }
}
