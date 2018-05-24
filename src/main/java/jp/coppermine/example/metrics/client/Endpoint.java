package jp.coppermine.example.metrics.client;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

public final class Endpoint {
    
    /**
     * The base URI of the server.
     */
    public static final URI SERVER = UriBuilder.fromUri("http://localhost:8080").build();
    
    /**
     * Context root of this application.
     */
    public static final URI CONTEXT_ROOT = UriBuilder.fromUri(SERVER).path("metrics-as-toys").build();
    
    /**
     * REST endpoint for this application.
     */
    public static final URI APPLICATION_PATH = UriBuilder.fromUri(CONTEXT_ROOT).path("rest").build();
    
    /**
     * REST endpoint for obtaining whole metrics.
     */
    public static final URI METRICS = UriBuilder.fromUri(SERVER).path("metrics").build();
    
    /**
     * REST endpoint for obtaining base metrics.
     */
    public static final URI BASE_METRICS = UriBuilder.fromUri(METRICS).path("base").build();
    
    /**
     * REST endpoint for obtaining vendor metrics.
     */
    public static final URI VENDOR_METRICS = UriBuilder.fromUri(METRICS).path("vendor").build();
    
    /**
     * REST endpoint for obtaining application metrics.
     */
    public static final URI APPLICATION_METRICS = UriBuilder.fromUri(METRICS).path("application").build();
    
}
