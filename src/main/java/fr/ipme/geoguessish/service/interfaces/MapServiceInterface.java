package fr.ipme.geoguessish.service.interfaces;

public interface MapServiceInterface<T, C, U, ID> extends
        CreateServiceInterface<T, C>,
        GetEntityServiceInterface<T, ID> {

    T update(U item, ID id);

}
