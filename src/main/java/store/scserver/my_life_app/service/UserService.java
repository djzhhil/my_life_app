package store.scserver.my_life_app.service;

import store.scserver.my_life_app.entity.User;

public interface UserService {

    User getUser(Long id);

    void addExpAndCoin(Long userId, int exp, int coin);
}
