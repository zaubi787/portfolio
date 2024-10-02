package name.abuchen.portfolio.server.resources;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import name.abuchen.portfolio.json.JAccount;
import name.abuchen.portfolio.model.Client;

@Path("/accounts")
public class AccountResource
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JAccount> listAccounts(@Context Client client)
    {
        var accounts = client.getAccounts().stream().map(JAccount::from);

        return accounts.collect(Collectors.toList());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public JAccount getAccount(@PathParam("id") String id, @Context Client client) 
    {
        var o = client.getAccounts().stream().filter(account -> account.getUUID().equals(id)).map(JAccount::from).findFirst();
                
        if(o.isEmpty()) 
        {
            throw new NotFoundException();
        }
        
        return o.get();
    }
}
