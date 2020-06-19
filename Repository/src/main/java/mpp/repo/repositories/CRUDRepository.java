package mpp.repo.repositories;


public interface CRUDRepository<T extends  Object> {
    void save(T o);

    T findOne(int id) throws Exception;
    T findByUsername(String username) throws Exception;
    T findPlayer(String username, String password) throws Exception;

    void delete(int id);

    void update(int id, T newUser);

    Iterable<T> getAll();
}
