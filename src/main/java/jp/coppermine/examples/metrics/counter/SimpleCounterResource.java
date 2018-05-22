package jp.coppermine.examples.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("counter")
@ApplicationScoped
public class SimpleCounterResource {
    
    @Inject
    @Metric(name = "counter", absolute = true)
    private Counter counter;
    
    @GET
    @Path("inc")
    public void increment() {
        counter.inc();
    }
    
    @GET
    @Path("dec")
    public void decrement() {
        counter.dec();
    }
    
}
