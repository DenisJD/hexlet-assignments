package exercise.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        List<String> companies = getCompanies();
        String queryString = request.getQueryString();
        String searchParam = request.getParameter("search");
        if (queryString == null || searchParam == null) {
            companies
                    .forEach(out::println);
        } else {
            companies = companies.stream()
                    .filter(x -> x.contains(searchParam))
                    .collect(Collectors.toList());
            if (companies.size() > 0) {
                companies
                        .forEach(out::println);
            } else {
                out.println("Companies not found");
            }
        }
        // END
    }
}
