package ru.kosinov.blog.service;

import ru.kosinov.blog.dto.User;

public interface LazyFetchService {

    boolean isAdmin(String login);

    boolean isUserInRole(String login, String roleName);

    User createUser(final String login, final String[] authorities);

}
