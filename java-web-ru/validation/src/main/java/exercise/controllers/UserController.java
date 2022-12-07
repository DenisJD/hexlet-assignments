package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.javalin.core.validation.JavalinValidation;
import io.javalin.core.validation.ValidationError;
import io.javalin.core.validation.Validator;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;
import java.util.Map;

public final class UserController {

    public static Handler listUsers = ctx -> {

        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        ctx.attribute("users", users);
        ctx.render("users/index.html");
    };

    public static Handler showUser = ctx -> {
        long id = ctx.pathParamAsClass("id", Long.class).getOrDefault(null);

        User user = new QUser()
                .id.equalTo(id)
                .findOne();

        ctx.attribute("user", user);
        ctx.render("users/show.html");
    };

    public static Handler newUser = ctx -> {

        ctx.attribute("errors", Map.of());
        ctx.attribute("user", Map.of());
        ctx.render("users/new.html");
    };

    public static Handler createUser = ctx -> {
        // BEGIN
        String name = ctx.formParam("firstName");
        String surname = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        Validator<String> nameValidator = ctx.formParamAsClass("firstName", String.class)
                .check(n -> !n.isEmpty(), "Имя не должно быть пустым");
        Validator<String> surnameValidator = ctx.formParamAsClass("lastName", String.class)
                .check(s -> !s.isEmpty(), "Фамилия не должна быть пустой");
        Validator<String> emailValidator = ctx.formParamAsClass("email", String.class)
                .check(e -> EmailValidator.getInstance().isValid(e), "Невалидный Email");
        Validator<String> passwordValidator = ctx.formParamAsClass("password", String.class)
                .check(p -> !p.isEmpty(), "Пароль не должен быть пустым")
                .check(p -> {
                    if (p != null) {
                        return p.length() >= 4;
                    }
                    return false;
                }, "Пароль должен содержать не менее 4-х символов")
                .check(StringUtils::isNumeric, "Пароль должен состоять из цифр");

        Map<String, List<ValidationError<? extends Object>>> errors = JavalinValidation.collectErrors(
                nameValidator,
                surnameValidator,
                emailValidator,
                passwordValidator
        );

        if (!errors.isEmpty()) {
            User invalidUser = new User(name, surname, email, password);
            ctx.status(HttpCode.UNPROCESSABLE_ENTITY);
            ctx.attribute("errors", errors);
            ctx.attribute("user", invalidUser);
            ctx.render("users/new.html");
        } else {
            User newUser = new User(name, surname, email, password);
            newUser.save();
            ctx.sessionAttribute("flash", "Пользователь успешно создан");
            ctx.redirect("/users");
        }
        // END
    };
}
