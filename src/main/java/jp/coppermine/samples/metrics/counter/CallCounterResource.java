package jp.coppermine.samples.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("call-counter")
@ApplicationScoped
public class CallCounterResource {
    
    @GET
    @Counted(monotonic = true, name = "called", absolute = true)
    public void call() {
        
    }
    
}