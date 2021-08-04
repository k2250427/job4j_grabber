package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.utils.SqlRuDateTimeParser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(title, post.title)
                && Objects.equals(link, post.link)
                && Objects.equals(description, post.description)
                && created.equals(post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, description, created);
    }
}
