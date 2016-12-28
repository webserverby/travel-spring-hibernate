package com.travel.service.impl;


import com.travel.dao.ClientDao;
import com.travel.model.Client;
import com.travel.dto.SearchClient;
import com.travel.service.ClientService;
import com.travel.utility.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    public List<Client> listClient(SearchCriteria criteria) {
        return clientDao.listClient(criteria);
    }

    @Transactional
    public List<Client> listClientNumber(){
        return clientDao.listClientNumber();
    }

    @Transactional
    public List<Client> searchClients(SearchClient searchClient) {
        return clientDao.searchClients(searchClient);
    }

    @Transactional
    public List<Client> grafikWeekClients() {
        return clientDao.grafikWeekClients();
    }

    @Transactional
    public void saveClient(Client client){
        clientDao.saveClient(client);
    }

    @Transactional
    public Client deleteClient(Integer idClient) {
        Client client = clientDao.getClientId(idClient);
        clientDao.deleteClient(idClient);
        return client;
    }

    @Transactional
    public Client getClientId(Integer idClient) {
        return clientDao.getClientId(idClient);
    }

    @Transactional
    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    @Transactional
    public List<Client> findClientNameRu(String nameRu){
        return clientDao.findClientNameRu(nameRu);
    }

}
