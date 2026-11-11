package mx.unison.urls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class EjemploURL {

    public static void main(String[] args) {

        // Primero crear un URI y luego convertirlo a URL
        //URI uri = URI.create("https://docs.oracle.com/javase/8/docs/api/java/net/URL.html");
        URI uri = URI.create("https://www.unison.mx/wp-content/themes/awaken/img/logo_unison_sin_tit.png");

        URL url = null;

        try {
            url = uri.toURL();
        } catch (MalformedURLException e) {
            System.out.println("Error al convertir URI a URL: " + e.getMessage());
            System.exit(1);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),
                        StandardCharsets.UTF_8))) {

            String linea;

            while ( (linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al abrir la conexión a la URL: " + e.getMessage());
            System.exit(1);
        }
    }
}