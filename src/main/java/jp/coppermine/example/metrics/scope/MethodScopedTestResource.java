package jp.coppermine.example.metrics.scope;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("scope/method")
@RequestScoped
public class MethodScopedTestResource {
    
    @GET
    @Counted(monotonic = true, name = "method-scoped", absolute = true)
    public String call() {
        return "call method-scoped";
    }
    
}
