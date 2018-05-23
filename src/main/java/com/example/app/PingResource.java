package com.example.app;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("ping")
@ApplicationScoped
public class PingResource {
    // Expose metrics as counter
    // Metrics is exposed as entity name with package
    // (Metric name can be customized)
    @Inject
    @Metric
    private Counter counter;
    
    // Increment the counter each method call
    @GET
    public String ping() {
        counter.inc();
        return "pong";
    }
    
}
