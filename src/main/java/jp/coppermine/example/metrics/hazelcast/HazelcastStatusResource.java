package jp.coppermine.example.metrics.hazelcast;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Gauge;

import com.hazelcast.core.HazelcastInstance;

@Path("hazelcast")
@ApplicationScoped
public class HazelcastStatusResource {
    
    @Inject
    private HazelcastInstance hazelcastInstance;
    
    @GET
    @Path("members")
    @Gauge(unit = "unit", name = "hazelcast-cluster-members", absolute = true)
    public int count() {
        return hazelcastInstance.getCluster().getMembers().size();
    }

}
