package com.travel.controllers;

import com.travel.model.Client;
import com.travel.dto.SearchClient;
import com.travel.model.Order;
import com.travel.service.ClientService;
import com.travel.model.DocumentClient;
import com.travel.service.DocumentService;
import com.travel.utility.FileBucket;
import com.travel.utility.FileValidator;
import com.travel.utility.ReturnUserName;
import com.travel.utility.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
/**
 * Контролер страниц клиента
 *
 * @author Artem Faenko
 */
@Controller
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ReturnUserName returnUserName = new ReturnUserName();

    @Autowired
    ClientService clientService;

    @Autowired
    DocumentService documentService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder
    public void initBinder2(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value ="/clients/client", method = RequestMethod.GET)
    public String outputClient(SearchCriteria criteria, Model model) {
        criteria.setPageSize(7);
        criteria.setSearchString("");
        model.addAttribute("clients", clientService.listClient(criteria));
        model.addAttribute("numbers", clientService.listClientNumber());
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "clients/client";
    }

    @RequestMapping(value ="/clients/json-grafik-week", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public  List<Client> jsonGrafikWeek(){
        List<Client> clients = clientService.grafikWeekClients();
        return clients;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String listClient(SearchCriteria criteria, Model model) {
        model.addAttribute("clients", clientService.listClient(criteria));
        return "clients/list";
    }

    @RequestMapping(value = "/clients/search-clients", method = RequestMethod.GET)
    public String searchClients(SearchClient searchClient, Model model) {
        model.addAttribute("clients", clientService.searchClients(searchClient));
        return "clients/list";
    }

    @RequestMapping(value = "/clients/search", method = RequestMethod.GET)
    public void searchClient(SearchCriteria searchCriteria) {
    }

    @RequestMapping(value ="/clients/list/{nameRu}", method = RequestMethod.GET)
    public String findClient(@PathVariable("nameRu") String nameRu, Model model) {
        model.addAttribute("clients", clientService.findClientNameRu(nameRu));
        return "clients/listsearch";
    }

    @RequestMapping(value = "/clients/open-client/{idClient}", method = RequestMethod.GET)
    public String openClient(@PathVariable("idClient") int idClient, Model model) {
        model.addAttribute("client", clientService.getClientId(idClient));
        return "clients/open";
    }

    @RequestMapping(value = "/clients/add-client-get", method = RequestMethod.GET)
    public String addClient() {
        return "clients/add";
    }

    @RequestMapping(value = "/clients/add-client", method = RequestMethod.POST)
    @ResponseBody
    public Client saveClient(Client client) {
        clientService.saveClient(client);
        return client;
    }

    @RequestMapping(value = "/clients/edit-client/{idClient}", method = RequestMethod.GET)
    public String editClient(@PathVariable("idClient") int idClient, Model model) {
        model.addAttribute("client", clientService.getClientId(idClient));
        return "clients/edit";
    }

    @RequestMapping(value ="/clients/edit-client", method = RequestMethod.POST)
    @ResponseBody
    public Client updateClient(Client client){
        clientService.updateClient(client);
        return client;
    }

    @RequestMapping(value="/clients/remove-client/{idClient}", method = RequestMethod.DELETE)
    @ResponseBody
    public Client deleteClient(@PathVariable int idClient) {
        return clientService.deleteClient(idClient);
    }

    @RequestMapping(value = { "/clients/add-basic/{idClient}" }, method = RequestMethod.GET)
    public String addBasic(@PathVariable("idClient") int idClient, Model model) {
        Client client = clientService.getClientId(idClient);
        model.addAttribute("client", client);
        return "clients/openbasic";
    }

    @RequestMapping(value = { "/clients/add-orderOpen/{idClient}" }, method = RequestMethod.GET)
    public String addOrders(@PathVariable("idClient") int idClient, Model model) {
        Client client = clientService.getClientId(idClient);
        Set<Order> orders = client.getOrders();
        model.addAttribute("orders", orders);
        return "clients/openorder";
    }

    @RequestMapping(value = { "/clients/add-tourOpen/{idClient}" }, method = RequestMethod.GET)
    public String addTours(@PathVariable("idClient") int idClient, Model model) {
        Client client = clientService.getClientId(idClient);
        Set<Order> orders = client.getOrders();
        model.addAttribute("orders", orders);
        return "clients/opentour";
    }

    @RequestMapping(value = { "/clients/add-document/{idClient}" }, method = RequestMethod.GET)
    public String addDocuments(@PathVariable("idClient") int idClient, Model model) {
        Client client = clientService.getClientId(idClient);
        model.addAttribute("client", client);

        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);

        List<DocumentClient> documents = documentService.findAllByUserId(idClient);
        model.addAttribute("documents", documents);

        return "clients/document";
    }

    @RequestMapping(value = { "/clients/add-document-{idClient}" }, method = RequestMethod.POST)
    @ResponseBody
    public void uploadDocument(FileBucket fileBucket, @PathVariable int idClient) throws IOException {
        Client client = clientService.getClientId(idClient);
        saveDocument(fileBucket, client);
    }

    private void saveDocument(FileBucket fileBucket,  Client client) throws IOException {
        DocumentClient document = new DocumentClient();
        MultipartFile multipartFile = fileBucket.getFile();

        document.setName(multipartFile.getOriginalFilename());
        document.setDescription(fileBucket.getDescription());
        document.setType(multipartFile.getContentType());
        document.setContent(multipartFile.getBytes());
        document.setClient(client);
        documentService.saveDocument(document);
    }

    @RequestMapping(value = { "/clients/eye-document-{docId}" }, method = RequestMethod.GET)
    public void eyeDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
        DocumentClient document = documentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","inline; filename=\"" + document.getName() +"\"");
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
    }

    @RequestMapping(value = { "/clients/download-document-{docId}" }, method = RequestMethod.GET)
    public void downloadDocument(@PathVariable int docId, HttpServletResponse response) throws IOException {
        DocumentClient document = documentService.findById(docId);
        response.setContentType(document.getType());
        response.setContentLength(document.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
        FileCopyUtils.copy(document.getContent(), response.getOutputStream());
    }

    @RequestMapping(value = { "/delete-document-{docId}" }, method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDocument(@PathVariable int docId) {
        documentService.deleteById(docId);
    }




}
