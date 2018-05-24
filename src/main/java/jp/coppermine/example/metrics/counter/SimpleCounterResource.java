package jp.coppermine.example.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
    public Response increment() {
        counter.inc();
        return Response.ok().build();
    }
    
    @GET
    @Path("dec")
    public Response decrement() {
        counter.dec();
        return Response.ok().build();
    }
    
}
