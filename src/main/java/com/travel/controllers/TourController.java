package com.travel.controllers;

import com.travel.model.City;
import com.travel.service.CityService;
import com.travel.service.CountryService;
import com.travel.model.DocumentTour;
import com.travel.service.DocumentTourService;
import com.travel.model.Hotel;
import com.travel.service.HotelService;
import com.travel.dto.Response;
import com.travel.dto.SearchTour;
import com.travel.model.Tour;
import com.travel.service.TourService;
import com.travel.model.Transport;
import com.travel.service.TransportService;
import com.travel.utility.*;
import com.travel.utility.parsing.Parsing;
import com.travel.utility.parsing.ParsingIMG;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * Контролер страниц тура
 *
 * @author Artem Faenko
 */
@Controller
public class TourController {

    private static final Logger logger = LoggerFactory.getLogger(TourController.class);
    private ReturnUserName returnUserName = new ReturnUserName();
    private Parsing parsing = new Parsing();

    @Autowired
    TourService tourService;

    @Autowired
    CountryService countryService;

    @Autowired
    CityService cityService;

    @Autowired
    HotelService hotelService;

    @Autowired
    TransportService transportService;

    @Autowired
    DocumentTourService documentTourService;

    @Autowired
    FileValidator fileValidator;

    @Autowired
    MultiFileValidator multiFileValidator;


    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @InitBinder("multiFileBucket")
    protected void initBinderMultiFileBucket(WebDataBinder binder) {
        binder.setValidator(multiFileValidator);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Это чтобы строки, содержащие дату, превращались в java.util.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @RequestMapping(value ="/tours/tour", method = RequestMethod.GET)
    public String outputTour(SearchCriteria criteria, Model model) {
        criteria.setPageSize(7);
        criteria.setSearchString("");
        model.addAttribute("tours", tourService.listTour(criteria));
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("numbers", tourService.listTourNumber());
        model.addAttribute("loggedinuser", returnUserName.getPrincipal());
        return "tours/tour";
    }

    @RequestMapping(value = "/tours", method = RequestMethod.GET)
    public String listTour(SearchCriteria criteria, Model model) {
        model.addAttribute("tours", tourService.listTour(criteria));
        return "tours/list";
    }

    @RequestMapping(value = "/tours/search-tours", method = RequestMethod.GET)
    public String searchTours(SearchTour searchTour, Model model) {
        model.addAttribute("tours", tourService.searchTours(searchTour));
        return "tours/list";
    }

    @RequestMapping(value = "/tours/search", method = RequestMethod.GET)
    public void searchTour(SearchCriteria searchCriteria) {
    }

    @RequestMapping(value ="/tours/list/{nameTour}", method = RequestMethod.GET)
    public String findTour(@PathVariable("nameTour") String nameTour, Model model) {
        model.addAttribute("tours", tourService.findTourNameTour(nameTour));
        return "tours/listsearch";
    }

    @RequestMapping(value = "/tours/open-tour/{idTour}", method = RequestMethod.GET)
    public String openTour(@PathVariable("idTour") int idTour, Model model) {
        model.addAttribute("tour", tourService.getTourId(idTour));
        return "tours/open";
    }

    @RequestMapping(value = "/tours/add-tour-get", method = RequestMethod.GET)
    public String addTour(Model model){
        model.addAttribute("countryList", countryService.findAll());
        return "tours/add";
    }

    @RequestMapping(value = "/tours/add-tour", method = RequestMethod.POST)
    @ResponseBody
    public Tour saveTour(Tour tour) {
        int days = Days.daysBetween(new DateTime(tour.getBeginDate()), new DateTime(tour.getEndDate())).getDays();
        tour.setNumberDay(days);
        tourService.saveTour(tour);
        Tour tourNew = tourService.getTourId(tour.getIdTour());
        return tourNew;
    }

    @RequestMapping(value = "/tours/add-avia", method = RequestMethod.POST)
    @ResponseBody
    public Transport saveTransport(Transport transport) {
        transportService.save(transport);
        Transport transportNew = transportService.findById(transport.getIdTransport());
        return transportNew;
    }

    @RequestMapping(value = "/tours/add-hotel", method = RequestMethod.POST)
    @ResponseBody
    public Hotel saveHotel(Hotel hotel) {
        hotelService.save(hotel);
        Hotel hotelNew = hotelService.findById(hotel.getIdHotel());
        return hotelNew;
    }

    @RequestMapping(value="/tours/remove-tour/{idTour}", method = RequestMethod.DELETE)
    @ResponseBody
    public void removeTour(@PathVariable int idTour) {
         tourService.deleteTour(idTour);
    }

    @RequestMapping(value = "/tours/search-tour-{idTour}", method = RequestMethod.GET)
    @ResponseBody
    public Tour findTour(@PathVariable("idTour") int idTour) {
        Tour tour = tourService.getTourId(idTour);
        return tour;
    }

    @RequestMapping(value = "/tours/search-country-{idCountry}", method = RequestMethod.GET)
    @ResponseBody
    public List<City> addCity(@PathVariable("idCountry") int idCountry, Model model) {
        List<City> citys = cityService.findAllCountryId(idCountry);
        model.addAttribute("citys", citys);
        return citys;
    }

    @RequestMapping(value = "/tours/open-avia-{idCity}-{beginDate}-{endDate}-{personNumber}", method = RequestMethod.GET)
    public String openAvia(@PathVariable int idCity, @PathVariable String beginDate,
                            @PathVariable String endDate, @PathVariable String personNumber, Model model) {
        City city = cityService.findById(idCity);
        String codeIATA = city.getCodeIata();
        model.addAttribute("parse", parsing.parsingAvia("https://search.aviasales.ru/MSQ" + beginDate + codeIATA + endDate + personNumber));
        return "tours/parseAvia";
    }

    @RequestMapping(value = "/tours/open-hotel-{idCity}-{beginDate}-{endDate}-{personNumber}", method = RequestMethod.GET)
    public String openHotel(@PathVariable int idCity, @PathVariable String beginDate,
                           @PathVariable String endDate, @PathVariable String personNumber, Model model) {
        City city = cityService.findById(idCity);
        String locationId = city.getLocationId();
        String[] begin = beginDate.split("[\\.]");
        String beginDateNew = begin[0]+ "-" +begin[1]+ "-" +begin[2];
        String[] end = endDate.split("[\\.]");
        String endDateNew = end[0]+ "-" +end[1]+ "-" +end[2];

        model.addAttribute("parse", parsing.parsingHotel("https://search.hotellook.com/?locationId=" + locationId +
                "&checkIn=" +  beginDateNew + "&checkOut=" + endDateNew + "&adults=" + personNumber + "&language=ru-RU&currency=BYN"));
        return "tours/parseHotel";
    }

    @RequestMapping(value = "/tours/open-hotelNumber", method = RequestMethod.GET)
    @ResponseBody
    public Response openHotelNumber(@RequestParam String text, @RequestParam String textOne, @RequestParam String textTwo,
                                    @RequestParam String textThree, @RequestParam String textFour, @RequestParam String textFive) throws IOException {
        Response result = new Response();
        String textHotel = parsing.parsingHotelNumber(text);
        result.setText(textHotel);

        ParsingIMG parsingIMG = new ParsingIMG();
        parsingIMG.parsingImage(textOne, "one");
        parsingIMG.parsingImage(textTwo, "two");
        parsingIMG.parsingImage(textThree, "three");
        parsingIMG.parsingImage(textFour, "four");
        parsingIMG.parsingImage(textFive, "five");
        return result;
    }


    @RequestMapping(value = "/tours/search-transport-{idTransport}", method = RequestMethod.GET)
    @ResponseBody
    public Transport findTransport(@PathVariable("idTransport") int idTransport) {
        Transport transport = transportService.findById(idTransport);
        return transport;
    }

    @RequestMapping(value = { "/tours/add-document" }, method = RequestMethod.GET)
    public String addDocuments(Model model) {
        MultiFileBucket filesModel = new MultiFileBucket();
        model.addAttribute("multiFileBucket", filesModel);
        return "tours/document";
    }

    @RequestMapping(value = { "/tours/add-document-{idTour}" }, method = RequestMethod.POST)
    @ResponseBody
    public void uploadDocument(MultiFileBucket multiFileBucket, @PathVariable int idTour) throws IOException {
        Tour tour = tourService.getTourId(idTour);
        saveDocument(multiFileBucket, tour);
    }

    private void saveDocument(MultiFileBucket multiFileBucket,  Tour tour) throws IOException {

        for(FileBucket bucket : multiFileBucket.getFiles()){
            DocumentTour document = new DocumentTour();
            document.setNameDoc(bucket.getFile().getOriginalFilename());
            document.setTypeDoc(bucket.getFile().getContentType());
            document.setContentTour(bucket.getFile().getBytes());
            document.setTour(tour);
            documentTourService.saveDocument(document);
        }

    }

    @RequestMapping(value = { "/tours/eye-document-{idDocTour}" }, method = RequestMethod.GET)
    public void eyeDocument(@PathVariable int idDocTour, HttpServletResponse response) throws IOException {
        DocumentTour document = documentTourService.findById(idDocTour);
        response.setContentType(document.getTypeDoc());
        response.setContentLength(document.getContentTour().length);
        response.setHeader("Content-Disposition","inline; filename=\"" + document.getNameDoc() +"\"");
        FileCopyUtils.copy(document.getContentTour(), response.getOutputStream());
    }

    @RequestMapping(value = { "/tours/delete-document-{idDocTour}" }, method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDocument(@PathVariable int idDocTour) {
        documentTourService.deleteById(idDocTour);
    }


    /* -------------- */

    @RequestMapping(value = "/tours/search-city-{idCity}", method = RequestMethod.GET)
    @ResponseBody
    public List<Hotel> addHotel_Staroeee(@PathVariable("idCity") int idCity) {
        List<Hotel> hotels = hotelService.findAllCityId(idCity);
        return hotels;
    }

    @RequestMapping(value = "/tours/search-hotel-service-{idHotel}", method = RequestMethod.GET)
    @ResponseBody
    public Hotel findHotel(@PathVariable("idHotel") int idHotel) {
        Hotel hotel = hotelService.findById(idHotel);
        return hotel;
    }

    @RequestMapping(value = "/tours/search-transports-{idCity}", method = RequestMethod.GET)
    @ResponseBody
    public List<Transport> addTransport(@PathVariable("idCity") int idCity) {
        List<Transport> transports = transportService.findAllCityId(idCity);
        return transports;
    }

    @RequestMapping(value = "/tours/edit-tour/{idTour}", method = RequestMethod.GET)
    public String editTour(@PathVariable("idTour") int idTour, Model model) {
        model.addAttribute("tour", tourService.getTourId(idTour));
        return "tours/edit";
    }

    @RequestMapping(value ="/tours/edit-tour", method = RequestMethod.POST)
    @ResponseBody
    public Tour updateTour(Tour tour){
        int days = Days.daysBetween(new DateTime(tour.getBeginDate()), new DateTime(tour.getEndDate())).getDays();
        tour.setNumberDay(days);
        tourService.updateTour(tour);
        return tour;
    }

}
