package com.travel.utility.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;

/**
 * Парсинг авиабилетов и номеров в отеле при составлении тура
 *
 * @author Artem Faenko
 */
public class Parsing {
    private static ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    private static String FILE_RESOURCE = classloader.getResource("phantomjs/bin/phantomjs.exe").getFile();

    private Document util(String url) {

        File file = new File(FILE_RESOURCE);
        System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
        WebDriver driver = new PhantomJSDriver();
        driver.get(url);

        // Ожидание 5 секунд
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
        }

        String htmlContent = driver.getPageSource();
        Document doc = Jsoup.parse(htmlContent);
        driver.close();

        return  doc;
    }

    public String parsingAvia(String url){
        System.out.println(url);
        String urlAvia = String.valueOf(url);
        Document doc = util(urlAvia);
        Element element = doc.getElementById("results_add_container");
        // lin3.getElementsByClass("creative").remove();

        String parse = String.valueOf(element);
        return parse;
    }

    public String parsingHotel(String url){
        System.out.println(url);
        String urlHotel = String.valueOf(url);

        Document doc = util(urlHotel);
        Element element = doc.getElementsByClass("hotels-grid-list").first();

        String parse = String.valueOf(element);
        return parse;
    }

    public String parsingHotelNumber(String url){
        System.out.println(url);
        String urlHotelNumber = String.valueOf(url);

        Document doc = util(urlHotelNumber);
        Element element = doc.getElementsByClass("hotels-one-info-collection").first();

        String parse = String.valueOf(element);
        System.out.println(parse);
        return parse;
    }

}
