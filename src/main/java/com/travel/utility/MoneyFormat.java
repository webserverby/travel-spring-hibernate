package com.travel.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для перевода цены прописью
 * @author artem
 */
public class MoneyFormat {

    /**
     * Сумма денег
     */
    private BigDecimal amount;

    /**
     * Конструктор из Long
     */
    public MoneyFormat(long l) {
        String s = String.valueOf(l);
        if (!s.contains(".") )
            s += ".0";
        this.amount = new BigDecimal( s );
    }

    /**
     * Конструктор из Double
     */
    public MoneyFormat(double l) {
        String s = String.valueOf(l);
        if (!s.contains(".") )
            s += ".0";
        this.amount = new BigDecimal( s );
    }

    /**
     * Конструктор из String
     */
    public MoneyFormat(String s) {
        if (!s.contains(".") )
            s += ".0";
        this.amount = new BigDecimal( s );
    }

    /**
     * Вернуть сумму как строку
     */
    public String asString() {
        return amount.toString();
    }

    /**
     * Вернуть сумму прописью, с точностью до копеек
     */
    public String num2str() {
        return num2str(false);
    }

    /**
     * Выводим сумму прописью
     * @param stripkop boolean флаг - показывать копейки или нет
     * @return String Сумма прописью
     */
    public String num2str(boolean stripkop) {
        String[][] sex = {
                {"","один","два","три","четыре","пять","шесть","семь","восемь","девять"},
                {"","одна","две","три","четыре","пять","шесть","семь","восемь","девять"},
        };
        String[] str100= {"","сто","двести","триста","четыреста","пятьсот","шестьсот","семьсот", "восемьсот","девятьсот"};
        String[] str11 = {"","десять","одиннадцать","двенадцать","тринадцать","четырнадцать", "пятнадцать","шестнадцать","семнадцать","восемнадцать","девятнадцать","двадцать"};
        String[] str10 = {"","десять","двадцать","тридцать","сорок","пятьдесят","шестьдесят", "семьдесят","восемьдесят","девяносто"};
        String[][] forms = {
                {"копейка", "копейки", "копеек", "1"},
                {"рубль", "рубля", "рублей", "0"},
                {"тысяча", "тысячи", "тысяч", "1"},
                {"миллион", "миллиона", "миллионов", "0"},
                {"миллиард","миллиарда","миллиардов","0"},
                {"триллион","триллиона","триллионов","0"},
                // можно добавлять дальше секстиллионы и т.д.
        };
        // получаем отдельно рубли и копейки
        long rub = amount.longValue();
        String[] moi = amount.toString().split("\\.");
        long kop = Long.valueOf(moi[1]);
        if (!moi[1].substring( 0,1).equals("0") ){// начинается не с нуля
            if (kop<10 )
                kop *=10;
        }
        String kops = String.valueOf(kop);
        if (kops.length()==1 )
            kops = "0"+kops;
        long rub_tmp = rub;
        // Разбиватель суммы на сегменты по 3 цифры с конца
        ArrayList segments = new ArrayList();
        while(rub_tmp>999) {
            long seg = rub_tmp/1000;
            segments.add( rub_tmp-(seg*1000) );
            rub_tmp=seg;
        }
        segments.add( rub_tmp );
        Collections.reverse(segments);
        // Анализируем сегменты
        String o = "";
        if (rub== 0) {// если Ноль
            o = "ноль "+morph( 0, forms[1][ 0],forms[1][1],forms[1][2]);
            if (stripkop)
                return o;
            else
                return o +" "+kop+" "+morph(kop,forms[ 0][ 0],forms[ 0][1],forms[ 0][2]);
        }
        // Больше нуля
        int lev = segments.size();
        for (int i= 0; i<segments.size(); i++ ) {// перебираем сегменты
            int sexi = (int)Integer.valueOf( forms[lev][3].toString() );// определяем род
            int ri = (int)Integer.valueOf( segments.get(i).toString() );// текущий сегмент
            if (ri== 0 && lev>1) {// если сегмент ==0 И не последний уровень(там Units)
                lev--;
                continue;
            }
            String rs = String.valueOf(ri); // число в строку
            // нормализация
            if (rs.length()==1) rs = "00"+rs;// два нулика в префикс?
            if (rs.length()==2) rs = "0"+rs; // или лучше один?
            // получаем циферки для анализа
            int r1 = (int)Integer.valueOf( rs.substring( 0,1) ); //первая цифра
            int r2 = (int)Integer.valueOf( rs.substring(1,2) ); //вторая
            int r3 = (int)Integer.valueOf( rs.substring(2,3) ); //третья
            int r22= (int)Integer.valueOf( rs.substring(1,3) ); //вторая и третья
            // Супер-нано-анализатор циферок
            if (ri>99) o += str100[r1]+" "; // Сотни
            if (r22>20) {// >20
                o += str10[r2]+" ";
                o += sex[ sexi ][r3]+" ";
            }
            else { // <=20
                if (r22>9) o += str11[r22-9]+" "; // 10-20
                else o += sex[ sexi ][r3]+" "; // 0-9
            }
            // Единицы измерения (рубли...)
            o += morph(ri, forms[lev][ 0],forms[lev][1],forms[lev][2])+" ";
            lev--;
        }
        // Копейки в цифровом виде
        if (stripkop) {
            o = o.replaceAll(" {2,}", " ");
        }
        else {
            o = o+""+kops+" "+morph(kop,forms[ 0][ 0],forms[ 0][1],forms[ 0][2]);
            o = o.replaceAll(" {2,}", " ");
        }
        return o;
    }

    /**
     * Склоняем словоформу
     * @param n Long количество объектов
     * @param f1 String вариант словоформы для одного объекта
     * @param f2 String вариант словоформы для двух объектов
     * @param f5 String вариант словоформы для пяти объектов
     * @return String правильный вариант словоформы для указанного количества объектов
     */
    public static String morph(long n, String f1, String f2, String f5) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return f5;
        if (n1 > 1 && n1 < 5) return f2;
        if (n1 == 1) return f1;
        return f5;
    }

}
