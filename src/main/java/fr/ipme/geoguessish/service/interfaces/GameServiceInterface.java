package fr.ipme.geoguessish.service.interfaces;

import java.security.Principal;

public interface GameServiceInterface<T, C, ID> extends
        GetEntityServiceInterface<T, ID> {

    T create(C item, Principal principal);

}
