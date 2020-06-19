package mpp.services.interfaces;

import mpp.domain.entities.User;

public interface Services {
    Object login(String username, String pass/*, Observer observer*/) throws Exception;
}
