package ru.kosinov.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kosinov.blog.dto.Authority;
import ru.kosinov.blog.dto.User;
import ru.kosinov.blog.repository.UserRepository;
import ru.kosinov.blog.util.AuthoritiesConstants;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LazyFetchServiceImpl implements LazyFetchService {

    private final UserRepository userRepository;

    @Autowired
    public LazyFetchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(final String login, final String[] authorities){
        User user = new User();
        user.setLogin(login);
        Set<Authority> authoritySet =
                Arrays.stream(authorities).map(Authority::new).collect(Collectors.toSet());
        user.setAuthorities(authoritySet);
        userRepository.save(user);
        return user;
    }

    public boolean isAdmin(String login){
        return isUserInRole(login, AuthoritiesConstants.ADMIN);
    }

    @Transactional
    public boolean isUserInRole(String login, String roleName){
        Optional<User> user = userRepository.findOneByLogin(login);
        return user.map(user1 -> user1.getAuthorities().contains(new Authority(roleName))).orElse(false);

    }


}
