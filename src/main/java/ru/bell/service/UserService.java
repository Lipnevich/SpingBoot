package ru.bell.service;

import ru.bell.api.User;
import ru.bell.api.UserPage;
import ru.bell.api.UserSearch;
import ru.bell.dao.AccountDAO;
import ru.bell.dao.EmailDAO;
import ru.bell.dao.PhoneDAO;
import ru.bell.dao.UserDAO;
import ru.bell.dao.UserEntity;
import ru.bell.task.BalancesIncreasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    static final Logger LOGGER = Logger.getLogger(BalancesIncreasing.class.getName());

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private EmailDAO emailDataDAO;

    @Autowired
    private PhoneDAO phoneDataDAO;

    public Stream<User> streamAll() {
        return StreamSupport.stream(userDAO.findAll().spliterator(), false).map(UserConverter::from);
    }

    @Transactional
    public User create(User user) {
        UserEntity entity = userDAO.save(UserConverter.from(user));
        LOGGER.info("New User was created " + entity);

        return user.setId(entity.getId());
    }

    public void update(User user) {
        userDAO.save(UserConverter.from(user));
    }

    public UserPage search(UserSearch search) {
        UserPage page = new UserPage();
        page.setTotal(0);
        page.setPage(search.getPage() != null ? search.getPage() : 0);
        page.setSize(search.getSize() != null ? search.getSize() : UserPage.DEFAULT_SIZE);

        List<UserEntity> users = new ArrayList<>();
        if (search.getName() != null) {
            page.setTotal(userDAO.countUsersWithNameLike(search.getName()));
            if (page.getTotal() > 0) {
                users = userDAO.findUsersWithNameLike(search.getName(), page.getPage(), page.getSize());
            }
        } else if (search.getBirthday() != null) {
            page.setTotal(userDAO.countUsersWithBirthdayFrom(search.getBirthday()));
            if (page.getTotal() > 0) {
                users = userDAO.findUsersWithBirthdayFrom(search.getBirthday(), page.getPage(), page.getSize());
            }
        } else if (search.getEmail() != null) {
            UserEntity entity = userDAO.findByEmail(search.getEmail());
            if (entity != null) {
                page.setTotal(1);
                users.add(entity);
            }
        } else if (search.getPhone() != null) {
            UserEntity entity = userDAO.findByPhone(search.getPhone());
            if (entity != null) {
                page.setTotal(1);
                users.add(entity);
            }
        }

        page.setUsers(StreamSupport.stream(users.spliterator(), false)
                .map(UserConverter::from).collect(Collectors.toList()));

        return page;
    }

}
