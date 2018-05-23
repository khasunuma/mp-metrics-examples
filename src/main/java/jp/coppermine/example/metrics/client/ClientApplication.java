package jp.coppermine.example.metrics.client;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jp.coppermine.example.metrics.client.counter.SimpleCounterClient;
import jp.coppermine.example.metrics.client.hazelcast.HazelcastStatusClient;
import jp.coppermine.example.metrics.client.idolmaster.CinderellaStageScoreClient;

@ApplicationPath("client")
public class ClientApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(SimpleCounterClient.class, HazelcastStatusClient.class, CinderellaStageScoreClient.class).collect(toSet());
    }

}
