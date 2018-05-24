package jp.coppermine.example.metrics.idolmaster;

import static java.util.stream.Collectors.joining;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static org.eclipse.microprofile.metrics.MetricType.HISTOGRAM;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.Metadata;
import org.eclipse.microprofile.metrics.MetricRegistry;

/**
 * Summarize the score in "THE IDOLM@STER CINDERELLA GIRLS STARLIGHT STAGE".
 * 
 * <p>This class exposes two {@link Histogram} metrics like as 
 * {@link CinderellaStageScoreResource}. This holds the score separated by 
 * musics. To define to expose metrics dynamically, using direct access to 
 * registry. ({@link MetricRegistry} class})</p>
 * <p>It is designed for "THE IDOLM@STER CINDERELLA GIRLS STARLIGHT STAGE",
 * but it might also adapt to "THE IDOLM@STER MILLION LIVE! THEATER DAYS".</p>
 * 
 * @see CinderellaStageScoreResource
 */
@Path("imas-cg/stage/all-result")
@ApplicationScoped
public class CinderellaStageMultipleScoreResource {
    
    @Inject
    private MetricRegistry registory;
    
    /**
     * Input a score on demand using XML or JSON format.
     * 
     * <p>This method exposes metrics - "${music}.combo" and "${music}.score" - 
     * instead of "play.combo" and "play.score" in 
     * {@link CinderellaStageScoreResource}. The suit of ${music} is depend on 
     * the input data, thus it directory access {@link MetricRegistry} and 
     * registers metrics on demand.</p>
     * 
     * @param scoreSheet a play score
     * @return Input data to confirm (XML or JSON format)
     * @see ScoreSheet
     */
    @POST
    @Path("input")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(ScoreSheet scoreSheet) {
        // create meta data from metric name and type
        // in this case, the name is "${music}.combo" and "${music}.score", 
        // the type is histogram
        Metadata comboHistogramMetadata = new Metadata(scoreSheet.getMusic() + ".combo", HISTOGRAM);
        Metadata scoreHistogramMetadata = new Metadata(scoreSheet.getMusic() + ".score", HISTOGRAM);
        
        // registers two metrics created the last procedure
        // and obtains as Histogram object to update values
        Histogram combo = registory.histogram(comboHistogramMetadata);
        Histogram score = registory.histogram(scoreHistogramMetadata);
        
        // update two histogram metrics
        combo.update(scoreSheet.getCombo());
        score.update(scoreSheet.getScore());
        
        return scoreSheet.toString();
    }
    
    /**
     * Import score records using XML or JSON format.
     * 
     * @param scoreSheets list of play scores
     * @return Input data to confirm (XML or JSON format)
     * @see ScoreSheet
     */
    @POST
    @Path("import")
    @Consumes({APPLICATION_XML, APPLICATION_JSON})
    public String input(List<ScoreSheet> scoreSheets) {
        scoreSheets.forEach(this::input);
        return scoreSheets.stream().map(e -> e.toString()).collect(joining("\n"));
    }
    
}
