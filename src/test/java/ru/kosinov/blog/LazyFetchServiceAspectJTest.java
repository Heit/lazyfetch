package ru.kosinov.blog;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.kosinov.blog.dto.Authority;
import ru.kosinov.blog.repository.AuthorityRepository;
import ru.kosinov.blog.service.LazyFetchService;
import ru.kosinov.blog.util.AuthoritiesConstants;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Application.class })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class LazyFetchServiceAspectJTest {

    @Autowired
    private LazyFetchService lazyFetchService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @BeforeAll
    public  void setUp() {
        Authority authAdmin = new Authority(AuthoritiesConstants.ADMIN);
        Authority authUser = new Authority(AuthoritiesConstants.USER);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authUser);
        lazyFetchService.createUser("admin", new String[]{AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN});
        lazyFetchService.createUser("user", new String[]{AuthoritiesConstants.USER});
    }

    @Test
    public void testUserInRole() {
        assertThat(lazyFetchService.isUserInRole("admin",AuthoritiesConstants.ADMIN)).isTrue();
    }

    @Test
    public void testUserIsAdmin() {
        assertThat(lazyFetchService.isAdmin("admin")).isTrue();
    }

}
