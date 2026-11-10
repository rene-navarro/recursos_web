package mx.unison.urls;

import java.net.URI;

public class EjemploURI {

    public static void main(String[] args) {

        URI uri = URI.create(
                "https://ejemplo.com:8080/productos?id=25#detalles"
        );

        System.out.println("Cadena: " + uri.toString());
        System.out.println("Esquema: " + uri.getScheme());
        System.out.println("Host: " + uri.getHost());
        System.out.println("Puerto: " + uri.getPort());
        System.out.println("Ruta: " + uri.getPath());
        System.out.println("Consulta: " + uri.getQuery());
        System.out.println("Fragmento: " + uri.getFragment());


        System.out.println(" " );


        uri = URI.create("https://docs.oracle.com/javase/8/docs/api/java/net/URI.html");
        System.out.println("Cadena: " + uri.toString());
        System.out.println("Esquema: " + uri.getScheme());
        System.out.println("Host: " + uri.getHost());
        System.out.println("Puerto: " + uri.getPort());
        System.out.println("Ruta: " + uri.getPath());
        System.out.println("Consulta: " + uri.getQuery());
        System.out.println("Fragmento: " + uri.getFragment());

    }
}