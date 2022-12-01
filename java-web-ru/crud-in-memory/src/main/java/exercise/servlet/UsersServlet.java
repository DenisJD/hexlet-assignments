package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

import static exercise.Data.getUsers;
import static exercise.Data.getNextId;

public class UsersServlet extends HttpServlet {

    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    private Map<String, String> getUserById(String id) {
        Map<String, String> user = users
            .stream()
            .filter(u -> u.get("id").equals(id))
            .findAny()
            .orElse(null);

        return user;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list" -> showUsers(request, response);
            case "new" -> newUser(request, response);
            case "edit" -> editUser(request, response);
            case "show" -> showUser(request, response);
            case "delete" -> deleteUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new" -> createUser(request, response);
            case "edit" -> updateUser(request, response);
            case "delete" -> destroyUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }


    private void showUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        // BEGIN
        Map<String, String> emptyUser = new HashMap<>();
        request.setAttribute("emptyUser", emptyUser);
        request.setAttribute("error", "");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        // BEGIN
        Map<String, String> newUser = new HashMap<>();

        String id = getNextId();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        newUser.put("id", id);
        newUser.put("firstName", firstName);
        newUser.put("lastName", lastName);
        newUser.put("email", email);

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            request.setAttribute("newUser", newUser);
            if (firstName.isEmpty()) {
                request.setAttribute("errorName", "Поле \"Имя\" не может быть пустым.");
            }
            if (lastName.isEmpty()) {
                request.setAttribute("errorSurname", "Поле \"Фамилия\" не может быть пустым.");
            }
            if (email.isEmpty()) {
                request.setAttribute("errorEmail", "Поле \"Почта\" не может быть пустым.");
            }
            response.setStatus(422);
            requestDispatcher.forward(request, response);
            return;
        }
        users.add(newUser);
        response.sendRedirect("/users");
        // END
    }

    private void editUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void updateUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        user.replace("firstName", firstName);
        user.replace("lastName", lastName);
        user.replace("email", email);

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
            request.setAttribute("user", user);
            if (firstName.isEmpty()) {
                request.setAttribute("errorName", "Поле \"Имя\" не может быть пустым.");
            }
            if (lastName.isEmpty()) {
                request.setAttribute("errorSurname", "Поле \"Фамилия\" не может быть пустым.");
            }
            if (email.isEmpty()) {
                request.setAttribute("errorEmail", "Поле \"Почта\" не может быть пустым.");
            }
            response.setStatus(422);
            requestDispatcher.forward(request, response);
            return;
        }

        users.remove(getUserById(id));
        users.add(user);
        response.sendRedirect("/users/show?id=" + id);
        // END
    }

    private void deleteUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);

    }

    private void destroyUser(HttpServletRequest request,
                         HttpServletResponse response)
                 throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }
}
