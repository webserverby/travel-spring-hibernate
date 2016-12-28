package com.travel.dao.impl;


import com.travel.dao.ClientDao;
import com.travel.dao.AbstractDao;
import com.travel.model.Client;
import com.travel.dto.SearchClient;
import com.travel.utility.SearchCriteria;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao {

    @SuppressWarnings("unchecked")
    public List<Client> listClient(SearchCriteria criteria){
        Criteria crit = createEntityCriteria();
                crit.addOrder(Order.asc("nameRu"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .setMaxResults(criteria.getPageSize())
                .setFirstResult(criteria.getPage() * criteria.getPageSize());
        return (List<Client>)crit.list();
    }

    @SuppressWarnings("unchecked")
    public List<Client> listClientNumber(){
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("nameRu"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<Client> clients = (List<Client>) criteria.list();
        return clients;
    }

    @SuppressWarnings("unchecked")
    public List<Client> searchClients(SearchClient searchClient){

        Criteria criteria = createEntityCriteria();
        if(searchClient.getBeginDateSearch()!=null && searchClient.getEndDateSearch()!=null){
            criteria.add(Restrictions.between("regDate", searchClient.getBeginDateSearch(), searchClient.getEndDateSearch()));
        }
        if(searchClient.getNameSearch()!=""){
            criteria.add(Restrictions.like("nameRu", searchClient.getNameSearch() + "%").ignoreCase());
        }
        if(searchClient.getPhoneSearch()!=""){
            criteria.add(Restrictions.like("phoneMobile", searchClient.getPhoneSearch() + "%"));
        }
        if(searchClient.getSortDate()!=null){
            criteria.addOrder(Order.desc("regDate"));
        }
        if(searchClient.getSortABC()!=null){
            criteria.addOrder(Order.asc("nameRu"));
        }

        List<Client> clients = (List<Client>) criteria.list();
        return clients;
    }

    @SuppressWarnings("unchecked")
    public List<Client> grafikWeekClients(){
        LocalDate today = LocalDate.now();
        LocalDate week = today.minusWeeks(1);
        Date todayNew = java.sql.Date.valueOf(today);
        Date weekNew = java.sql.Date.valueOf(week);

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.between("regDate", weekNew, todayNew));
        criteria.addOrder(Order.asc("regDate"));
        List<Client> clients = (List<Client>) criteria.list();
        return clients;
    }

    public void saveClient(Client client){
        save(client);
    }

    public Client deleteClient(Integer idClient) {
        Client client =  getByKey(idClient);
        delete(client);
        return client;
    }

    public Client getClientId(Integer idClient){
        Client client =  getByKey(idClient);
        return client;
    }

    public void updateClient(Client client){
        update(client);
    }

    @SuppressWarnings("unchecked")
    public List<Client> findClientNameRu(String nameRu){
        return  createEntityCriteria()
                .add(Restrictions.like("nameRu", nameRu + "%").ignoreCase())
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

    }

}
