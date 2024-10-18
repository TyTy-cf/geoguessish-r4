package fr.ipme.geoguessish.service.interfaces;

public interface MapServiceInterface<T, C, U, ID> extends
        CreateServiceInterface<T, C>,
        UpdateServiceInterface<T, U, ID>,
        GetEntityServiceInterface<T, ID> {
}
