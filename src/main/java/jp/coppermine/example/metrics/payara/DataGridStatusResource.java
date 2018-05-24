package jp.coppermine.example.metrics.payara;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

import com.hazelcast.core.HazelcastInstance;

@Path("payara")
@ApplicationScoped
public class DataGridStatusResource {
    
    @Inject
    private HazelcastInstance hazelcastInstance;
    
    @GET
    @Path("cluster/members")
    @Gauge(unit = "unit", name = "payara.data-grid.members", absolute = true)
    public int count() {
        return hazelcastInstance.getCluster().getMembers().size();
    }

}
