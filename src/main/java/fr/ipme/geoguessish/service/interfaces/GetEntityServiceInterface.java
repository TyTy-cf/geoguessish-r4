package fr.ipme.geoguessish.service.interfaces;

import java.util.List;

public interface GetEntityServiceInterface<T, ID> {

    T findOneById(ID id);

    List<T> findAll();

}
