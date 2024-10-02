package name.abuchen.portfolio.server.resources;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import name.abuchen.portfolio.json.JTaxonomy;
import name.abuchen.portfolio.model.Client;

@Path("/taxonomies")
public class TaxonomiesResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JTaxonomy> listTaxonomies(@Context Client client)
    {
        var taxonomies = client.getTaxonomies().stream().map(JTaxonomy::from).collect(Collectors.toList());
        
        return taxonomies;
    }
}
