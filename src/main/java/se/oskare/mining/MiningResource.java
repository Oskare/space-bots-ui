package se.oskare.mining;

import io.quarkus.qute.Template;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SessionState;
import se.oskare.SpaceBotsApi;

@Path("/mining")
@Produces(MediaType.TEXT_HTML)
public class MiningResource {

    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    SessionState state;

    @Inject
    Template mine;

    @POST
    public Response mine() {
        var result = apiService.mine(state.currentFleet);

        JsonObject events = new JsonObject();
        JsonObject actionEvent = new JsonObject();
        actionEvent.put("duration", result.duration);
        events.put("action-started", actionEvent);

        return Response
                .ok(mine.data("duration", result.duration))
                .header("HX-Trigger", events)
                .build();
    }
}
