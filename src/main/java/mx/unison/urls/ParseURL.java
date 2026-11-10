package mx.unison.urls;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseURL {
    public static final String URL_STRING = "https://docs.oracle.com:80/javase/8/docs/api/java/net/URL.html#constructor.summary";
// "https://www.gutenberg.org:8080/ebooks/search/?author=Pedro"
    // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
    // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html#constructor.summary

    public static void main(String[] args) {

        URL aURL = null;
        try {
            //aURL = new URL("//docs.oracle.com/javase/8/docs/api/java/net/URL.html#constructor.summary");
            aURL = new URL(URL_STRING);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ParseURL.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        System.out.println("protocol: " + aURL.getProtocol());
        System.out.println("authority: " + aURL.getAuthority());
        System.out.println("host: " + aURL.getHost());
        System.out.println("port: " + aURL.getPort());
        System.out.println("path: " + aURL.getPath());
        System.out.println("query: " + aURL.getQuery());
        System.out.println("filename: " + aURL.getFile());
        System.out.println("ref: " + aURL.getRef());
    }
}
