package se.oskare.player;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import se.oskare.SpaceBotsApi;


@Path("/player")
@Produces(MediaType.TEXT_HTML)
public class PlayerResource {

    @Inject
    @RestClient
    SpaceBotsApi apiService;

    @Inject
    Template player;

    @GET
    public TemplateInstance getPlayerInfo() {
        return player.data("player", apiService.getMe());
    }
}
