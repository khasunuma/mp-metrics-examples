package jp.coppermine.example.metrics.counter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("call")
@ApplicationScoped
public class CallCounterResource {
    
    @GET
    @Counted(monotonic = true, name = "called", absolute = true)
    public Response call() {
        return Response.ok().build();
    }
    
}
