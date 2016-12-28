package com.travel.dao;


import com.travel.model.Client;
import com.travel.dto.SearchClient;
import com.travel.utility.SearchCriteria;

import java.util.List;

public interface ClientDao {

    List<Client> listClient(SearchCriteria criteria);

    List<Client> listClientNumber();

    List<Client> searchClients(SearchClient searchClient);

    List<Client> grafikWeekClients();

    void saveClient(Client client);

    Client deleteClient(Integer idClient);

    Client getClientId(Integer idClient);

    void updateClient(Client client);

    List<Client> findClientNameRu(String nameRu);


}
