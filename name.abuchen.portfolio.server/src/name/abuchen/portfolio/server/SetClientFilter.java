package name.abuchen.portfolio.server;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import name.abuchen.portfolio.model.Client;
import name.abuchen.portfolio.ui.editor.ClientInput;
import name.abuchen.portfolio.ui.editor.ClientInputFactory;

public class SetClientFilter implements Filter
{
    @Inject
    private ClientInputFactory factory;   

    @Override
    public void init(FilterConfig fConfig) throws ServletException // NOSONAR
    {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String filename = httpRequest.getHeader(ServerConstants.HEADER_FILE);

        if (filename == null)
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Optional<ClientInput> input = factory.lookupIfPresent(new File(filename));
        if (input.isPresent())
        {
            request.setAttribute(Client.class.getName(), input.get().getClient());
            chain.doFilter(request, response);
        }
        else
        {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void destroy() // NOSONAR
    {}

}
