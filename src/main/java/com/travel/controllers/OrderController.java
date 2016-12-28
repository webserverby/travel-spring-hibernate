package com.travel.controllers;


import com.travel.model.Client;
import com.travel.dto.SearchClient;
import com.travel.service.ClientService;
import com.travel.service.CountryService;
import com.travel.model.DocumentClient;
import com.travel.service.DocumentService;
import com.travel.model.ServiceOrder;
import com.travel.service.ServiceOrderService;
import com.travel.dto.SearchTour;
import com.travel.service.TourService;
import com.travel.dto.SearchOrder;
import com.travel.model.Order;
import com.travel.service.OrderService;
import com.travel.utility.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Контролер страниц заказа
 *
 * @author Artem Faenko
 */
@Controller
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private ReturnUserName returnUserName = new ReturnUserName();

    private PdfStatement pdfStatement = new PdfStatement();
    private PdfContract pdfContract = new PdfContract();

    @Autowired
    OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TourService tourService;

    @Autowired
    CountryService countryService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    @Autowired
    DocumentService documentService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Это чтобы строки, содержащие дату, превращались в java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value ="/orders/order", method = RequestMethod.GET)
    public String outputOrder(SearchCriteria criteria, Model model) {
        criteria.setPageSize(7);
        criteria.setSearchString("");
        model.addAttribute("orders", orderService.listOrder(criteria));
        model.addAttribute("numbers", orderService.listOrderNumber());
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "orders/order";
    }

    @RequestMapping(value ="/orders/json-grafik-week", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public  List<Order> jsonGrafikWeek(){
        List<Order> orders = orderService.grafikWeekOrders();
        return orders;
    }

    @RequestMapping(value ="/orders/json-grafik-month", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public  List<Order> jsonGrafikMonth(){
        List<Order> orders = orderService.grafikMonthOrders();
        return orders;
    }

    @ModelAttribute("serviceList")
    public List<ServiceOrder> initializeService() {
        return serviceOrderService.findAll();
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String listOrder(SearchCriteria criteria, Model model) {
        model.addAttribute("orders", orderService.listOrder(criteria));
        return "orders/list";
    }

    @RequestMapping(value = "/orders/search-orders", method = RequestMethod.GET)
    public String searchOrders(SearchOrder searchOrder, Model model) {
        model.addAttribute("orders", orderService.searchOrders(searchOrder));
        return "orders/list";
    }

    @RequestMapping(value = "/orders/search", method = RequestMethod.GET)
    public void searchOrder(SearchCriteria searchCriteria) {
    }

    @RequestMapping(value = "/orders/open-order/{idOrder}", method = RequestMethod.GET)
    public String openOrder(@PathVariable("idOrder") int idOrder, Model model) {
        model.addAttribute("order", orderService.getOrderId(idOrder));
        return "orders/open";
    }

    @RequestMapping(value = "/orders/add-order-get", method = RequestMethod.GET)
    public String addOrder(Model model) {
        Order order = new Order();
        model.addAttribute("order", order);
        return "orders/add";
    }

    @RequestMapping(value = "/orders/add-order", method = RequestMethod.POST)
    @ResponseBody
    public Order saveOrder(Order order) throws IOException {
        orderService.saveOrder(order);
        Order orderNew = orderService.getOrderId(order.getIdOrder());

        BigDecimal sum = BigDecimal.valueOf(0);
        for(ServiceOrder serviceOrder : orderNew.getServiceOrders()){
            sum = sum.add(serviceOrder.getCostService());
        }
        BigDecimal costTour = orderNew.getTour().getCostTour();
        BigDecimal costOrder = costTour.add(sum);
        orderNew.setCostOrder(costOrder);

        LocalDate today = LocalDate.now();
        String numberOrder = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("ru")));
        String dateOrder = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd", new Locale("ru")));
        orderNew.setOrderNumber("TUR-" + order.getIdOrder() + "-" + numberOrder);
        orderNew.setOrderDate(java.sql.Date.valueOf(dateOrder));

        MoneyFormat mo = new MoneyFormat(String.valueOf(costOrder));
        String money_string = mo.num2str();
        orderNew.setCostOrderString(money_string);

        orderService.updateOrder(orderNew);

        Order orderDocument = orderService.getOrderId(orderNew.getIdOrder());
        pdfStatement.compilePdf(orderDocument, "Приложение_" + orderDocument.getIdOrder());
        compileDocument(orderDocument.getClient(), "Приложение_" + orderDocument.getIdOrder());

        pdfContract.compilePdf(orderDocument, "Договор_" + orderDocument.getIdOrder());
        compileDocument(orderDocument.getClient(), "Договор_" + orderDocument.getIdOrder());
        return orderDocument;
    }

    @RequestMapping(value = "/orders/edit-order/{idOrder}", method = RequestMethod.GET)
    public String editOrder(@PathVariable("idOrder") int idOrder, Model model) {
        model.addAttribute("order", orderService.getOrderId(idOrder));
        return "orders/edit";
    }

    @RequestMapping(value ="/orders/edit-order", method = RequestMethod.POST)
    @ResponseBody
    public Order updateOrder(Order order){
        orderService.updateOrder(order);
        Order orderNew = orderService.getOrderId(order.getIdOrder());
        return orderNew;
    }

    @RequestMapping(value="/orders/remove-order/{idOrder}", method = RequestMethod.DELETE)
    @ResponseBody
    public Order deleteOrder(@PathVariable int idOrder) {
        return orderService.deleteOrder(idOrder);
    }

    @RequestMapping(value ="/orders/search-client", method = RequestMethod.GET)
    public String outputClient(SearchCriteria criteria, Model model) {
        criteria.setPageSize(7);
        criteria.setSearchString("");
        model.addAttribute("clients", clientService.listClient(criteria));
        return "orders/clientmodal";
    }

    @RequestMapping(value = "/orders/clients", method = RequestMethod.GET)
    public String listClient(SearchCriteria criteria, Model model) {
        model.addAttribute("clients", clientService.listClient(criteria));
        return "orders/listclient";
    }

    @RequestMapping(value ="/orders/search-client-{nameRu}", method = RequestMethod.GET)
    public String findClient(@PathVariable("nameRu") String nameRu, Model model) {
        model.addAttribute("clients", clientService.findClientNameRu(nameRu));
        return "orders/listsearchclient";
    }

    @RequestMapping(value ="/orders/add-client-{idClient}", method = RequestMethod.GET)
    public String addClient(@PathVariable("idClient") int idClient, Model model) {
        model.addAttribute("client", clientService.getClientId(idClient));
        return "orders/addclient";
    }

    @RequestMapping(value ="/orders/search-tour", method = RequestMethod.GET)
    public String outputTour(SearchCriteria criteria, Model model) {
        criteria.setPageSize(7);
        criteria.setSearchString("");
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("tours", tourService.listTour(criteria));
        return "orders/tourmodal";
    }

    @RequestMapping(value = "/orders/tours", method = RequestMethod.GET)
    public String listTour(SearchCriteria criteria, Model model) {
        model.addAttribute("tours", tourService.listTour(criteria));
        return "orders/listtour";
    }

    @RequestMapping(value ="/orders/add-tour-{idTour}", method = RequestMethod.GET)
    public String addTour(@PathVariable("idTour") int idTour, Model model) {
        model.addAttribute("tour", tourService.getTourId(idTour));
        return "orders/addtour";
    }

    @RequestMapping(value = "/orders/search-tours", method = RequestMethod.GET)
    public String searchTours(SearchTour searchTour, Model model) {
        model.addAttribute("tours", tourService.searchTours(searchTour));
        return "orders/listtour";
    }

    @RequestMapping(value = "/orders/search-clients", method = RequestMethod.GET)
    public String searchClients(SearchClient searchClient, Model model) {
        model.addAttribute("clients", clientService.searchClients(searchClient));
        return "orders/listclient";
    }

    public void compileDocument(Client client, String nameDocument) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String FILE_RESOURCE = classloader.getResource("").getFile();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(FILE_RESOURCE + nameDocument + ".pdf");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int readed;
            while ((readed = fis.read(buf)) != -1) {
                baos.write(buf, 0, readed);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

        byte[] data = baos.toByteArray();

        DocumentClient document = new DocumentClient();
        document.setName(file.getName());
        document.setDescription("договор");
        document.setType("application/pdf");
        document.setContent(data);
        document.setClient(client);
        documentService.saveDocument(document);
        file.delete();
    }

}
