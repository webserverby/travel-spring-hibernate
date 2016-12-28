package com.travel.utility;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.travel.model.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *  Генерация PDF используя iText
 */
public class PdfContract {

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
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        // Формат даты в текстовый в вид "15 мая 2015"
        String dateString = String.valueOf(order.getOrderDate());
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dateZakaz = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("ru")));

        Paragraph title = new Paragraph("ДОГОВОР  №"+ order.getOrderNumber() +"\n" +
                "о подборе, бронировании и приобретении тура Турагентом в интересах Заказчика", boldFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph paragraph1 = new Paragraph(dateZakaz, normalFont);
        paragraph1.setAlignment(Element.ALIGN_RIGHT);
        paragraph1.setSpacingAfter(10);
        document.add(paragraph1);

        Paragraph paragraph2 = new Paragraph("ООО Турфирма «Бон Вояж», в лице директора Ивановой Анастасии Ивановны, действующего на основании Устава," +
                " в дальнейшем именуемое «Турагент», с одной  стороны и " + order.getClient().getNameRu() +", в дальнейшем именуемый(ая) «Заказчик», с другой  стороны," +
                " вместе именуемые Стороны, заключили настоящий Договор о нижеследующем:", normalFont);
        paragraph2.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph2.setSpacingAfter(5);
        document.add(paragraph2);

        Paragraph paragraph3 = new Paragraph("1. ПРЕДМЕТ ДОГОВОРА", boldFont);
        paragraph3.setAlignment(Element.ALIGN_CENTER);
        paragraph3.setSpacingAfter(2);
        document.add(paragraph3);

        Paragraph paragraph4 = new Paragraph("1.1.\tТурагент обязуется за вознаграждение, от своего имени, совершить по поручению и за счет Заказчика" +
                " юридические и иные действия, направленные на подбор, бронирование и оплату туристского продукта, потребительские свойства которого" +
                " указаны в Заявке на бронирование (Приложение №1 к договору). Везде, где по тексту договора указан Заказчик , имеются в виду также" +
                " третьи лица, в интересах которых  действует Заказчик, сопровождающие его (сопровождаемые им) лица, в том числе несовершеннолетние," +
                " или иной заказчик туристского продукта. \n" +
                "1.2.\tТуристский продукт, соответствующий характеристикам, указанным в Заявке на бронирование, формируется Туроператором." +
                " Туроператор является лицом (исполнителем), обеспечивающим оказание Заказчику услуг," +
                " входящих в туристский продукт, и несет перед Заказчиком ответственность за неоказание или ненадлежащее оказание  услуг, входящих в" +
                " туристский продукт, независимо от того, кем должны были оказываться или оказывались эти услуги.\n" +
                "1.3.\tВ комплекс туристских услуг, составляющих туристский продукт, могут входить: \n" +
                "-\tуслуги по размещению;\n" +
                "-\tуслуги по перевозке, трансфер;\n" +
                "-\tэкскурсионные услуги;\n" +
                "-\tмедицинское страхование, страхование расходов, возникших вследствие отмены поездки за границу или изменения сроков пребывания за границей;\n" +
                "-\tсодействие в оформлении въездной визы;\n" +
                "иные услуги, указанные в Заявке на бронирование.\n" +
                "1.4.\tТурагент предоставляет Заказчику достоверные сведения о составе и характеристиках услуг, входящих в туристский продукт. Услуги, входящие" +
                " в туристский продукт, непосредственно оказываются Заказчику третьими лицами – туроператором, перевозчиком, отелем или иным средством размещения," +
                " страховщиком и прочими лицами, предоставляющими услуги, входящие в туристский продукт.\n" +
                "1.5.\tТуристский продукт требует предварительного бронирования и подтверждения Турагентом наличия такого продукта у Туроператора. \n", normalFont);
        paragraph4.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph4.setSpacingAfter(5);
        document.add(paragraph4);

        Paragraph paragraph5 = new Paragraph("2. ПРАВА И ОБЯЗАННОСТИ СТОРОН", boldFont);
        paragraph5.setAlignment(Element.ALIGN_CENTER);
        paragraph5.setSpacingAfter(2);
        document.add(paragraph5);

        Paragraph paragraph6 = new Paragraph("2.1. \tТурагент обязуется:\n" +
                "2.1.1. \tСовершить юридические и иные действия, направленные на подбор, бронирование и оплату туристского продукта, соответствующего характеристикам," +
                " указанным в Заявке на бронирование. Передать указанный туристский продукт Заказчику. \n" +
                "2.1.2. \tПредоставить Заказчику  информацию:\n" +
                "-\tо потребительских свойствах туристского продукта;\n" +
                "-\tо правилах въезда в страну (место) временного пребывания и выезда из страны (места) временного пребывания, об основных документах, необходимых для" +
                " въезда в страну (место) временного пребывания и выезда из страны (места) временного пребывания, включая сведения о необходимости наличия визы для въезда" +
                " в страну и (или) выезда из страны временного пребывания;\n" +
                "-\tо таможенных, пограничных, медицинских, санитарно-эпидемиологических и иных правилах (в объеме, необходимом для совершения путешествия);\n" +
                "-\tоб обычаях местного населения, о религиозных обрядах, о святынях, памятниках природы, истории, культуры и других объектах туристского показа, находящихся" +
                " под особой охраной, состоянии окружающей природной среды (в объеме, необходимом для совершения путешествия;\n" +
                "-\tо национальных и религиозных особенностях страны (места) временного пребывания;\n" +
                "В целях исполнения указанной обязанности Турагент, помимо прочего, предоставляет Заказчику Памятку. Подписанием договора Заказчик подтверждает свое ознакомление" +
                " с указанной информацией и получение соответствующих материалов. \n" +
                "2.1.3. Представить Заказчику  отчет в порядке и сроки, которые установлены договором.\n" +
                "2.2.\tТурагент вправе:\n" +
                "2.2.1.\tОтказаться от исполнения договора в случае нарушения Заказчиком порядка оплаты, а также в случаях не предоставления или несвоевременного предоставления" +
                " Заказчиком сведений и документов, необходимых для исполнения договора или нарушения Заказчиком иных обязанностей установленных настоящим Договором. \n" +
                "2.2.2.\tПолучить от Туроператора бонусы, скидки и иные формы материального поощрения, предусмотренного для турагентов, юридических лиц, за реализацию туристского" +
                " продукта и оставить их в своем распоряжении.\n" +
                "2.3.\tЗаказчик обязуется:\n" +
                "2.3.1.\tВ установленный Турагентом  срок произвести оплату туристского продукта в соответствии с разделом 3 договора.\n" +
                "2.3.2.\tВ установленный Турагентом срок предоставить Турагенту комплект необходимых документов, в объеме, необходимом для исполнения договора (в том числе заграничный" +
                " паспорт, фотографии в требуемом количестве, анкетную и прочую информацию), сообщить сведения и представить иные документы, указанные в Заявке на бронирование и в настоящем Договоре.\n" +
                "2.3.3.\tПредоставить Турагенту точную информацию о своем адресе и телефоне, необходимую Турагент у для оперативной связи с Заказчиком.\n" +
                "2.3.4.\tСвоевременно (за день до вылета) уточнить у Турагента время и место вылета, сроки проведения поездки, расписание авиарейсов, место и время сбора группы, прочие существенные даты и сроки. \n" +
                "2.3.5.\tДо начала поездки получить документы, необходимые для осуществления поездки в офисе Турагента (в некоторых случаях, с учетом особенностей туристского продукта" +
                " - в аэропорту/на вокзале у представителя Турагента или Туроператора). Осмотреть указанные документы  и известить  Турагента  без промедления об обнаруженных в документах недостатках.\n" +
                "2.3.7.\tСвоевременно прибыть в аэропорт (на вокзал) к установленному Турагентом месту встречи. Неявка (опоздание) к отправлению по любым причинам приравнивается к отказу Заказчика" +
                " от исполнения договора. Изменение сроков поездки возможно только по предварительному письменному согласованию с Турагентом, при отсутствии которого Заказчику не будет предоставлено" +
                " размещение в отеле при самостоятельном прибытии Заказчика в отель раньше или позже согласованного срока.\n" +
                "2.4.\tЗаказчик вправе:\n" +
                "2.4.1.\tОтразить существенные для него условия размещения и/или перевозки в Заявке на бронирование. В противном случае Туроператор может осуществить замену оговоренных услуг" +
                " (в том числе замену средства размещения или компании-перевозчика) с сохранением класса услуг или с заменой на услуги более высокого класса без доплаты со стороны Заказчика. \n" +
                "2.4.2.\tПолучить при заключении договора информацию, в соответствии с п. 2.1.2. договора. \n" +
                "2.4.3.\tПотребовать возмещения убытков и компенсации морального вреда в случае неисполнения условий договора в порядке установленном законодательством Российской Федерации. \n" +
                "2.4.4.\tВ случае неисполнения Туроператором обязательств \n" +
                "по оказанию Заказчику услуг, входящих в состав туристского продукта, либо в случае наличия в туристском продукте существенных недостатков, включая существенные нарушения требований" +
                " к качеству и безопасности, Заказчик вправе обратиться с письменным требованием о выплате страхового" +
                " возмещения по договору страхования ответственности.", normalFont);
        paragraph6.setAlignment(Element.ALIGN_JUSTIFIED);
        paragraph6.setSpacingAfter(5);
        document.add(paragraph6);

        Paragraph paragraph7 = new Paragraph("3. РЕКВИЗИТЫ И ПОДПИСИ СТОРОН", boldFont);
        paragraph7.setAlignment(Element.ALIGN_CENTER);
        paragraph7.setSpacingAfter(2);
        document.add(paragraph7);
        createTableSignature(document, order);
    }

    private static void createTableSignature(Document document, Order order) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont(FONT_LOCATION, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font normalFont = new Font(baseFont, 9,Font.NORMAL);
        Font boldFont = new Font(baseFont, 9,Font.BOLD);

        PdfPTable tableHotel = new PdfPTable(2);
        tableHotel.setWidthPercentage (100);

        PdfPCell c1 = new PdfPCell(new Phrase("ТУРАГЕНТ:", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("ЗАКАЗЧИК:", boldFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        tableHotel.addCell(c1);

        c1 = new PdfPCell(new Phrase("ООО Турфирма «Бон Вояж»\n" +
                "Адрес: 212001, г. Минск, ул.Профсоюзная\n" +
                "Телефон: +375(29)340-00-00\n" +
                "УНП 790934098 Р/С 3012167045012\n" +
                "Р/С 3012167000111\n" +
                "в ОАО «БПС-СБЕРБАНК», г. Минск\n" +
                "БИК 153001369\n", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("С условиями договора ознакомлен и согласен. \n" +
                "Документы, являющиеся приложением к настоящему договору, полную информацию о туристском продукте, получил.\n" +
                "Подтверждаю наличие у меня права заключить настоящий Договор также в интересах  третьих лиц, указанных в Заявке на бронирование (Приложение №1) :\n" +
                "\n" + order.getClient().getNameRu() +
                "\n" +
                "Паспорт "+ order.getClient().getSeriesNumber() +"\n" +
                "Адрес " + order.getClient().getAddress() + "\n" +
                "Тел. "+ order.getClient().getPhoneMobile() +"\n", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        tableHotel.addCell(c1);

        c1 = new PdfPCell(new Phrase("____________ /Иванова Анастасия Ивановна/", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        tableHotel.addCell(c1);
        c1 = new PdfPCell(new Phrase("____________ /"+ order.getClient().getNameRu() +"/", normalFont));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        c1.setBorder(PdfPCell.NO_BORDER);
        c1.setPaddingTop (20);
        tableHotel.addCell(c1);

        document.add(tableHotel);

    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
