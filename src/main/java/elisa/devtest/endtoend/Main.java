package elisa.devtest.endtoend;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        return createHttpServerWith(new ResourceConfig().packages("elisa.devtest.endtoend").register(JacksonFeature.class));
    }

    private static HttpServer createHttpServerWith(ResourceConfig rc) {
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("src/main/webapp");
        staticHttpHandler.setFileCacheEnabled(false);
        staticHttpHandler.start();
        httpServer.getServerConfiguration().addHttpHandler(staticHttpHandler);
        return httpServer;
    }

    public static void main(String[] args) throws IOException {
        System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
        final HttpServer server = startServer();
        new DbBootstrap().bootstratp();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        server.start();
        System.in.read();
        server.stop();
    }

}

