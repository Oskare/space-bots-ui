package se.oskare;

import java.util.List;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import se.oskare.cargo.DirectSellRequest;
import se.oskare.cargo.DirectSellResponse;
import se.oskare.fleet.Fleet;
import se.oskare.mining.MineResponse;
import se.oskare.player.Player;
import se.oskare.system.System;
import se.oskare.system.TravelRequest;
import se.oskare.system.TravelResponse;

@RegisterRestClient(baseUri = "https://space-bots.longwelwind.net/v1")
@ClientHeaderParam(name = "Authorization", value = "Bearer spbo_ApueScAGlxmQjyGoWTNUlo2bOmmFUaz7")
public interface SpaceBotsApi {

    @GET
    @Path("/fleets/my")
    List<Fleet> getMyFleets();

    @GET
    @Path("/fleets/{id}")
    Fleet getFleet(@PathParam("id") String id);

    @GET
    @Path("/systems/{id}")
    System getSystem(@PathParam("id") String id);

    @POST
    @Path("/fleets/{fleetId}/direct-sell")
    DirectSellResponse directSell(@PathParam("fleetId") String fleetId, DirectSellRequest request);

    @POST
    @Path("/fleets/{fleetId}/travel")
    TravelResponse travel(@PathParam("fleetId") String fleetId, TravelRequest destination);

    @POST
    @Path("/fleets/{fleetId}/mine")
    MineResponse mine(@PathParam("fleetId") String fleetId);

    @POST
    @Path("/fleets/{fleetId}/buy-ships")
    Object buyShips(@PathParam("fleetId") String fleetId, Object shipsToBuy);

    @GET
    @Path("/ship-types")
    List<Object> getShipTypes();

    @GET
    @Path("/resources")
    List<Object> getResources();

    @GET
    @Path("/users/me")
    Player getMe();
}
