package mx.unison.urls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EjemploURL {

    public static void main(String[] args) throws Exception {

        URI uri = URI.create("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
        URL url = uri.toURL();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        StandardCharsets.UTF_8))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        }
    }
}