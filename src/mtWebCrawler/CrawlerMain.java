package mtWebCrawler;

import java.util.ArrayList;

public class CrawlerMain {

    public static void main(String[] args) {
        // Create a list to store crawler instances
        ArrayList<Crawler> crawlers = new ArrayList<>();
        // Add crawler instances with different URLs and IDs
        crawlers.add(new Crawler("https://www.bbc.com", 1));
        crawlers.add(new Crawler("https://edition.cnn.com", 2));
        crawlers.add(new Crawler("https://www.theguardian.com", 3));

        // Start each crawler thread and wait for them to finish
        for (Crawler crawler : crawlers) {
            try {
                crawler.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
