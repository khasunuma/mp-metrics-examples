package jp.coppermine.example.metrics.scope;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.annotation.Metric;

@Path("scope/field")
@RequestScoped
public class FieldScopedTestResource {
    
    @Inject
    @Metric(name = "field-scoped", absolute = true)
    private Counter counter;
    
    @GET
    public String call() {
        counter.inc();
        return "call method-scoped"; 
    }
    
}
