package ru.itis.inform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Kamil Karimov on 16.11.2016.
 */
public class SiteParser {
    final String userAgent = "Mozilla/5.0 (X11; U; Linux i586; en-US; rv:1.7.3) Gecko/20040924 Epiphany/1.4.4 (Ubuntu)";

    public String showWeather() {
        String result = "";
        try {
            Document doc = Jsoup.connect("https://www.gismeteo.ru/city/daily/4364/")
                    .userAgent(userAgent)
                    .timeout(10 * 1000)
                    .get();
            Elements newsHeadlines = doc.select("#content");
            doc = Jsoup.parse(newsHeadlines.toString());
            Node node = doc.childNode(0);
            int mas[] = {1, 0, 5, 3, 1, 1, 3, 7, 1, 0};
            for (int i = 0; i < mas.length; i++) {
                node = node.childNode(mas[i]);
            }
            result = node.attr("text").toString();
            result = result.replaceAll("\n", "");
            result = result.replaceAll(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result + " °C";
    }
}
