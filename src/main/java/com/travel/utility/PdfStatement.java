package com.travel.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.travel.model.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Генерация PDF используя iText
 */
public class PdfStatement {

        private static ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        private static String FILE_RESOURCE = classloader.getResource("").getFile();
        private static String FONT_LOCATION = "tahoma.ttf";

        public void compilePdf(Order order, String nameDocument) {
            try {
                Document document = new Document(PageSize.A4, 25, 25, 25, 25);
                PdfWriter.getInstance(document, new FileOutputStream(FILE_RESOURCE + nameDocument + ".pdf"));
                document.open();
                addMetaData(document);
                addTitlePage(document, order);
                document.close();
                /*
                File file = new File(strBuffer4.toString() + "DDDD.pdf");
                file.delete();
                */
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    /**
     * Добавить метаданные для просмотра в Свойствах
     */
    private static void addMetaData(Document document) {
        document.addTitle("Contract");
        document.addSubject("Contract");
        document.addKeywords("Contract, Order");
        document.addAuthor("Faenko Artem");
        document.addCreator("Faenko Artem");
    }

    private static void addTitlePage(Document document, Order order)
            throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);
        Font boldBig = new Font(baseFont, 11,Font.BOLD);

        // Формат даты в текстовый в вид "15 мая 2015"
        String dateString = String.valueOf(order.getOrderDate());
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateZakaz = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru")));

        MoneyFormat mo = new MoneyFormat(String.valueOf(order.getCostOrder()));
        String money_as_string = mo.num2str();
/*
        Image img = Image.getInstance("e:/sample.png");
        document.add(img);
*/
        Paragraph title = new Paragraph("Приложение  №1 к договору " + order.getOrderNumber() + "\n" +
              "от " +  dateZakaz, boldFont);
        title.setAlignment(Element.ALIGN_RIGHT);
        addEmptyLine(title, 1);
        document.add(title);

        Paragraph paragraph1 = new Paragraph("ЗАЯВКА НА  БРОНИРОВАНИЕ  ТУРИСТСКОГО ПРОДУКТА", boldBig);
        paragraph1.setSpacingAfter(10);
        /* paragraph1.setSpacingBefore(25);
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setIndentationLeft(50);
        paragraph1.setIndentationRight(50); */
        document.add(paragraph1);
        createTableTitle(document, order);

        String imageUrl = "http://maps.google.com/maps/api/staticmap?center=" + order.getTour().getCity().getCoordinateX() + "," + order.getTour().getCity().getCoordinateY() + "&markers=color:red|label:*|" + order.getTour().getCity().getCoordinateX() + "," + order.getTour().getCity().getCoordinateY() + "&zoom=12&size=900x300&language=ru";
        Image stamp = Image.getInstance(new URL(imageUrl));
        stamp.setAlignment(Element.ALIGN_CENTER);
        stamp.scaleToFit(545f, 545f);
        document.add(stamp);

        Paragraph paragraph2 = new Paragraph("ИНФОРМАЦИЯ О ТУРИСТЕ:", boldFont);
        paragraph2.setSpacingAfter(5);
        paragraph2.setSpacingBefore(5);
        document.add(paragraph2);
        createTableZakaz(document, order);

        Paragraph paragraph3 = new Paragraph("ИНФОРМАЦИЯ ОБ АВИАПЕРЕЛЕТЕ:", boldFont);
        paragraph3.setSpacingAfter(5);
        paragraph3.setSpacingBefore(5);
        document.add(paragraph3);
        createTableAvia(document, order);

        Paragraph paragraph4 = new Paragraph("ИНФОРМАЦИЯ О РАЗМЕЩЕНИИ В ОТЕЛЯХ:", boldFont);
        paragraph4.setSpacingAfter(5);
        paragraph4.setSpacingBefore(5);
        document.add(paragraph4);
        createTableHotel(document, order);

        Paragraph paragraph5 = new Paragraph("ДОПОЛНИТЕЛЬНЫЕ УСЛУГИ:", boldFont);
        paragraph5.setSpacingAfter(5);
        paragraph5.setSpacingBefore(5);
        document.add(paragraph5);
        createTableUslugi(document, order);

        Paragraph paragraph6 = new Paragraph("СТОИМОСТЬ ТУРА В РУБЛЯХ: " + order.getCostOrder() + " (" + order.getCostOrderString() + ")", boldFont);
        paragraph6.setSpacingAfter(5);
        paragraph6.setSpacingBefore(5);
        addEmptyLine(paragraph6, 1);
        document.add(paragraph6);

        createTableSignature(document, order);

    }

    private static void createTableTitle(Document document, Order order) throws DocumentException, IOException {
        //подключаем файл шрифта, который поддерживает кириллицу
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        float[] columnWidths = {1,4};
        PdfPTable tableTitle = new PdfPTable(columnWidths);
        tableTitle.setWidthPercentage (100);
        // tableZakaz.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell c1 = new PdfPCell(new Phrase("Заказчик", boldFont));
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getClient().getNameRu(), boldFont));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase("Адрес/телефон", boldFont));
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getClient().getAddress() + " / " + order.getClient().getPhoneMobile(), boldFont));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase("Название тура", boldFont));
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getTour().getNameTour(), boldFont));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase("Туроператор", boldFont));
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableTitle.addCell(c1);
        c1 = new PdfPCell(new Phrase("ООО «Бон Вояж»", boldFont));
        c1.setPadding (3);
        tableTitle.addCell(c1);

        document.add(tableTitle);

    }

    private static void createTableZakaz(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        String dateString = String.valueOf(order.getClient().getBirthDate());
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String birthDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));

        float[] columnWidths = {1,8,2,3,3,2,2,2};
        PdfPTable tableZakaz = new PdfPTable(columnWidths);
        tableZakaz.setWidthPercentage (100);

        PdfPCell c1 = new PdfPCell(new Phrase("№", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ф.И.О.", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Пол", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Паспорт", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Дата  рождения", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Виза", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Мед. страх", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("Гид", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableZakaz.addCell(c1);

        tableZakaz.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase("2", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getClient().getNameRu() + "\n" +
                order.getClient().getNameEn() + "\n", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getClient().getPersonSex(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getClient().getSeriesNumber(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase(birthDate, normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("да", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("да", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);
        c1 = new PdfPCell(new Phrase("нет", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableZakaz.addCell(c1);

        document.add(tableZakaz);

    }


    private static void createTableAvia(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        String dateStringBegin = String.valueOf(order.getTour().getBeginDate());
        LocalDate dateBegin = LocalDate.parse(dateStringBegin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String beginDate = dateBegin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));
        String dateStringEnd = String.valueOf(order.getTour().getEndDate());
        LocalDate dateEnd = LocalDate.parse(dateStringEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dateEnd.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));

        float[] columnWidths = {1,2,1,1,1,1};
        PdfPTable tableAvia = new PdfPTable(columnWidths);
        tableAvia.setWidthPercentage (100);

        PdfPCell c1 = new PdfPCell(new Phrase("Дата", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("Аэропорт", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("Маршрут", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("№ рейса", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("Вылет", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("Прилет", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        c1.setPadding (3);
        tableAvia.addCell(c1);

        tableAvia.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase(beginDate, normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getTour().getTransport().getAirportStart(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getTour().getTransport().getCityDeparture() + " - "
                + order.getTour().getTransport().getCityArrival(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase("ORB 667", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase(beginDate, normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);
        c1 = new PdfPCell(new Phrase(endDate, normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setPadding (3);
        tableAvia.addCell(c1);

        document.add(tableAvia);

    }

    private static void createTableHotel(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        String dateStringBegin = String.valueOf(order.getTour().getBeginDate());
        LocalDate dateBegin = LocalDate.parse(dateStringBegin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String beginDate = dateBegin.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));
        String dateStringEnd = String.valueOf(order.getTour().getEndDate());
        LocalDate dateEnd = LocalDate.parse(dateStringEnd, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String endDate = dateEnd.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru")));

        float[] columnWidths = {1,1,2,1,1,1,1};
        PdfPTable tableHotel = new PdfPTable(columnWidths);
        tableHotel.setWidthPercentage (100);

        PdfPCell c1 = new PdfPCell(new Phrase("Страна", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Город", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Отель", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Номер", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Питание", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Даты заезда - выезда", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Кол.ночей", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(new BaseColor(229,229,229));
        tableHotel.addCell(c1);

        tableHotel.setHeaderRows(1);

        c1 = new PdfPCell(new Phrase(order.getTour().getCity().getCountry().getCountryName(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getTour().getCity().getCityName(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase(order.getTour().getHotel().getHotelName(), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Standart", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Полное", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase(beginDate + " - " + endDate, normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase(String.valueOf(order.getTour().getNumberDay()), normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHotel.addCell(c1);

        document.add(tableHotel);

    }

    private static void createTableUslugi(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        PdfPTable tableUslugi = new PdfPTable(1);
        tableUslugi.setWidthPercentage (100);
        // tableZakaz.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell c1 = new PdfPCell(new Phrase(" ", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableUslugi.addCell(c1);
        c1 = new PdfPCell(new Phrase(" ", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        tableUslugi.addCell(c1);

        document.add(tableUslugi);

    }

    private static void createTableSignature(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        PdfPTable tableHotel = new PdfPTable(2);
        tableHotel.setWidthPercentage (100);

        PdfPCell c1 = new PdfPCell(new Phrase("Турагент", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingLeft (20);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("Заказчик", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingLeft (20);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("____________ /Иванова Анастасия Ивановна/", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        c1.setPaddingLeft (20);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("____________ /"+ order.getClient().getNameRu() +"/", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        c1.setPaddingLeft (20);
        tableHotel.addCell(c1);

        document.add(tableHotel);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
