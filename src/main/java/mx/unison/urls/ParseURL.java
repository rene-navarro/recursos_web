package mx.unison.urls;


import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ParseURL {
    public static final String URL_STRING =
            "https://docs.oracle.com:80/javase/8/docs/api/java/net/URL.html#constructor.summary";

// "https://www.gutenberg.org:8080/ebooks/search/?author=Pedro"
    // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html
    // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html#constructor.summary
    // https://docs.oracle.com/javase/8/docs/api/java/net/URL.html

    public static void main(String[] args) {

        URI uri = URI.create(URL_STRING);

        URL aURL = null;
        try {
            aURL = uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
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
