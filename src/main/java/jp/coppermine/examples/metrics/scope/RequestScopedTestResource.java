package jp.coppermine.examples.metrics.scope;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("scope/req")
@RequestScoped
public class RequestScopedTestResource {

    @Counted(monotonic = true, name = "req-scoped", absolute = true)
    public RequestScopedTestResource() {
        
    }
    
    @GET
    public String call() {
        return "call app-scoped";
    }

}
