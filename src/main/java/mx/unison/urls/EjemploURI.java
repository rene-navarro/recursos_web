package mx.unison.urls;

import java.net.URI;

public class EjemploURI {

    public static void main(String[] args) {

        // este URI no existe, es solo un ejemplo para mostrar los componentes de un URI
        URI uri = URI.create(
                "https://ejemplo.com:8080/productos?id=25#detalles"
        );

        // Imprime los componentes del URI
        System.out.println("Cadena: " + uri.toString());
        System.out.println("Esquema: " + uri.getScheme());
        System.out.println("Host: " + uri.getHost());
        System.out.println("Puerto: " + uri.getPort());
        System.out.println("Ruta: " + uri.getPath());
        System.out.println("Consulta: " + uri.getQuery());
        System.out.println("Fragmento: " + uri.getFragment());


        System.out.println(" " );

        // este URI si existe, es solo un ejemplo para mostrar los componentes de un URI
        uri = URI.create("https://docs.oracle.com/javase/8/docs/api/java/net/URI.html");

        // Imprime los componentes del URI
        System.out.println("Cadena: " + uri.toString());
        System.out.println("Esquema: " + uri.getScheme());
        System.out.println("Host: " + uri.getHost());
        System.out.println("Puerto: " + uri.getPort());
        System.out.println("Ruta: " + uri.getPath());
        System.out.println("Consulta: " + uri.getQuery());
        System.out.println("Fragmento: " + uri.getFragment());

    }
}