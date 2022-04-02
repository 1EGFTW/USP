package bg.tuvarna.sit.usp_cars.data.repositories;

import java.util.List;

public interface DAORepository<T> {
    void save(T obj);
    void update(T obj);
    void delete(T obj);
    T getById(Integer id);
    List<T> getAll();
}
