package com.codeHub.service;

import java.util.Locale;

public class Internationalizer {
    public static void internationalierCmds(){
        printLocalDetails();
    }
    public static void printLocalDetails(){
        Locale [] locales=Locale.getAvailableLocales();
        System.out.println("Def locale: "+Locale.getDefault());

        for(Locale locale:locales) {
            try {
                System.out.println("Name: " + locale.getDisplayName() + "Country: " + locale.getDisplayCountry() + " ISO country: " + locale.getISO3Country() +
                        " Language: " + locale.getDisplayLanguage() + "ISO language: " + locale.getISO3Language() + " Variant: " + locale.getDisplayVariant());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
