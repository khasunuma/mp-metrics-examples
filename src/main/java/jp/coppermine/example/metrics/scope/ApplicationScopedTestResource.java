package jp.coppermine.example.metrics.scope;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("scope/app")
@ApplicationScoped
public class ApplicationScopedTestResource {

    @Counted(monotonic = true, name = "app-scoped", absolute = true)
    public ApplicationScopedTestResource() {
        
    }
    
    @GET
    public String call() {
        return "call app-scoped";
    }
}
