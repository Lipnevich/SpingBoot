package org.example.service;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.api.User;
import org.example.api.UserPage;
import org.example.api.UserSearch;
import org.example.dao.AccountDAO;
import org.example.dao.EmailDAO;
import org.example.dao.PhoneDAO;
import org.example.dao.UserDAO;
import org.example.dao.UserEntity;
import org.example.task.BalanceIncreasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    static final Logger LOGGER = Logger.getLogger(BalanceIncreasing.class.getName());

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

    public User get(Long id) {
        return UserConverter.from(userDAO.findById(id).get());
    }
}
