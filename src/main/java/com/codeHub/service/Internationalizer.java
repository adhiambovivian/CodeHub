package com.codeHub.service;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Internationalizer {
    public static void internationalierCmds(){
        //printLocalDetails();
//        translateMessages();
        dateLocalization();
    }
    public static void printLocalDetails(){
        Locale [] locales=Locale.getAvailableLocales();
        System.out.println("Def locale: "+Locale.getDefault());

        for(Locale locale:locales) {
            try {
                System.out.println("<Name>\t" + locale.getDisplayName() + "\t<Country>\t " + locale.getDisplayCountry() + "\t<ISO country>\t" + locale.getISO3Country() +
                        "\t<Language>\t" + locale.getDisplayLanguage() + "\t<ISO language>\t" + locale.getISO3Language() + "\t<Variant>\t" + locale.getDisplayVariant()+"" +
                        "\t<language>\t"+locale.getLanguage()+"\tcountry>\t"+locale.getCountry());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        Locale enLocale = new Locale("en", "US");
        Locale frLocale = new Locale("fr", "FR");
        Locale esLocale = new Locale("es", "ES");
        System.out.println("English language name (default): " +
                enLocale.getDisplayLanguage());

        System.out.println("English language name in French: " +
                enLocale.getDisplayLanguage(frLocale));
        System.out.println("English language name in spanish: " +
                enLocale.getDisplayLanguage(esLocale));
    }

    public static void translateMessages(){
        ResourceBundle bundle = ResourceBundle.getBundle("messages",new Locale("luo","KE"));
        System.out.println("Message: "+bundle.getString("greeting1"));

    }

    public static void dateLocalization(){
        Locale[] locales=Locale.getAvailableLocales();
        for(Locale locale:locales) {
            try {
                DateFormat format=DateFormat.getDateInstance(DateFormat.DEFAULT,locale);
                Date currentDate=new Date();
                String date = format.format(currentDate);
                System.out.println("country>\t"+locale.getCountry()+"\t"+date+" "+locale);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }


}
