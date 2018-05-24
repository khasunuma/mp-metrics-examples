package jp.coppermine.example.metrics.client;

import static java.util.stream.Collectors.toSet;

import java.util.Set;
import java.util.stream.Stream;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import jp.coppermine.example.metrics.client.counter.SimpleCounterClient;
import jp.coppermine.example.metrics.client.gauge.SimpleGaugeClient;
import jp.coppermine.example.metrics.client.histogram.SimpleHistogramClient;
import jp.coppermine.example.metrics.client.idolmaster.CinderellaStageScoreClient;
import jp.coppermine.example.metrics.client.meter.SimpleMeterClient;
import jp.coppermine.example.metrics.client.payara.DataGridStatusClient;
import jp.coppermine.example.metrics.client.timer.SimpleTimerClient;

@ApplicationPath("client")
public class ClientApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(SimpleCounterClient.class, 
                SimpleGaugeClient.class, 
                SimpleMeterClient.class, 
                SimpleHistogramClient.class, 
                SimpleTimerClient.class, 
                DataGridStatusClient.class, 
                CinderellaStageScoreClient.class).collect(toSet());
    }

}
