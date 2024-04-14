package mtWebCrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Crawler implements Runnable {

    private static final int MAX_DEPTH = 3;
    private Thread thread;
    private String firstLink;
    private ArrayList<String> visitedLinks = new ArrayList<>();
    private int id;

    public Crawler(String link, int num) {
        System.out.print("Crawler Created");
        firstLink = link;
        id = num;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        crawl(1, firstLink);
    }

    private void crawl(int level, String url) {
        if (level <= MAX_DEPTH) {
            Document doc = request(url);
            if (doc != null) {
                for (Element link : doc.select("a[href]")) {
                    String nextLink = link.absUrl("href");
                    if (!visitedLinks.contains(nextLink)) {
                        crawl(level + 1, nextLink);
                    }
                }
            }
        }
    }

    private Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                // Print the URL and title of the webpage
                System.out.println("\n**Crawler ID: " + id + " Received Webpage at " + url);

                String title = doc.title();
                System.out.println(title);
                // Add the URL to the list of visited links
                visitedLinks.add(url);
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public Thread getThread() {
        return thread;
    }
}
