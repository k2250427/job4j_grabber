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
        String dateStr = footer.get(0).text();
        int pos = dateStr.indexOf('[');
        created = dataParser.parse(dateStr.substring(0, pos - 1));
    }

    public static void main(String[] args) throws Exception {
        Post post = new Post();
        post.loadFromLink("https://www.sql.ru/forum/1325330/"
                + "lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t");
        System.out.println(post);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
