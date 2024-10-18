package fr.ipme.geoguessish.service.interfaces;

public interface UpdateServiceInterface<T, U, ID> {

    T update(U item, ID id);

}
