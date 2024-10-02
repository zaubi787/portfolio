package name.abuchen.portfolio.server.resources;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import name.abuchen.portfolio.json.JSecurity;
import name.abuchen.portfolio.model.Client;

@Path("/securities")
public class SecuritiesResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSecurity> listSecurities(@Context Client client)
    {
        var securities = client.getSecurities().stream().map(JSecurity::from);


        return securities.collect(Collectors.toList());
    }
}
