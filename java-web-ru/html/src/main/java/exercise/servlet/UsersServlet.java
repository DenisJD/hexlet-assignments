package exercise.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }
        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");
        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper om = new ObjectMapper();
        Path path = Path.of("src/main/resources/users.json");
        String content = Files.readString(path);
        return om.readValue(content, new TypeReference<>() {
        });
        // END
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        List<Map<String, String>> listUsers = getUsers();
        pw.write("<table>");
        pw.write("""
                <tr>
                    <th>ID</th>
                    <th>Full name</th>
                  </tr>""");
        for (Map<String, String> user : listUsers) {
            pw.write("  </tr>\n" +
                    "    <td>" + user.get("id") + " </td>\n" +
                    "    <td>" + "<a href=\"/users/" + user.get("id") + "\">" +
                    user.get("firstName") + " " + user.get("lastName") + "</a>\n" +
                    "    </td>\n" +
                    "  </tr>\n");
        }
        pw.write("</table>");
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        // BEGIN
        PrintWriter pw = response.getWriter();
        List<Map<String, String>> listUsers = getUsers();
        boolean flag = false;
        for (Map<String, String> user : listUsers) {
            if (user.get("id").equals(id)) {
                pw.write("<table\n>" +
                        "    <th>ID</th>\n" +
                        "    <th>Firstname</th>\n" +
                        "    <th>Lastname</th>\n" +
                        "    <th>Email</th>\n" +
                        "  <tr>\n" +
                        "    <td>" + user.get("id") + " </td>" +
                        "    <td>" + user.get("firstName") + " </td>" +
                        "    <td>" + user.get("lastName") + " </td>" +
                        "    <td>" + user.get("email") + " </td>" +
                        "  </tr>\n" +
                        "</table>");
                flag = true;
            }
        }
        if (!flag) {
            response.sendError(SC_NOT_FOUND);
        }
        // END
    }
}
