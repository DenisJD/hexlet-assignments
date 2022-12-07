package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> listOfUsers = new QUser()
                .orderBy()
                .id.asc()
                .findList();

        String jsonOfUsers = DB.json().toJson(listOfUsers);
        ctx.json(jsonOfUsers);
        // END
    }

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.equalTo(Long.parseLong(id))
                .findOne();

        String jsonOfUser = DB.json().toJson(user);
        ctx.json(jsonOfUser);
        // END
    }

    public void create(Context ctx) {

        // BEGIN
        User newUser = ctx.bodyValidator(User.class)
                .check(u -> !u.getFirstName().isEmpty(), "Name field is empty")
                .check(u -> !u.getLastName().isEmpty(), "Surname field is empty")
                .check(u -> EmailValidator.getInstance().isValid(u.getEmail()), "Email invalid")
                .check(u -> u.getPassword().length() >= 4, "Password length must be more than 4 symbols")
                .check(u -> StringUtils.isNumeric(u.getPassword()), "Password must be numeric")
                .get();

        newUser.save();
        // END
    }

    public void update(Context ctx, String id) {
        // BEGIN
        String jsonOfUser = ctx.body();
        User newUser = DB.json().toBean(User.class, jsonOfUser);
        newUser.setId(id);
        newUser.update();
        // END
    }

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser()
                .id.equalTo(Long.parseLong(id))
                .delete();
        // END
    }
}
