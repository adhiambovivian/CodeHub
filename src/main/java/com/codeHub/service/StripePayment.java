/* Copyright (C)2021  Vivian */
package com.codeHub.service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import java.util.HashMap;
import java.util.Map;

public class StripePayment {
    public static void createCharge(String token)
            throws InvalidRequestException, AuthenticationException, APIConnectionException,
                    CardException, APIException {

        // Stripe.apiKey = "sk_test_Fk6wzvHUrxv4IaWLCo29Vxcn";
        RequestOptions requestOptions =
                (new RequestOptions.RequestOptionsBuilder()).setApiKey("").build();

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 25000); // convert dollars to cents
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "balkari@gmail.com"); // users email
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams, requestOptions);
        System.out.println("Stripe" + charge);
    }

    public static String createToken()
            throws InvalidRequestException, AuthenticationException, APIConnectionException,
                    CardException, APIException {
        {
            Stripe.apiKey = "";

            Map<String, Object> tokenParams = new HashMap<String, Object>();
            Map<String, Object> cardParams = new HashMap<String, Object>();
            cardParams.put("number", "4242424242424242");
            cardParams.put("exp_month", 9);
            cardParams.put("exp_year", 2018);
            cardParams.put("cvc", "314");
            cardParams.put("name", "Smith Balkari");
            cardParams.put("address_city", "Nairobi");
            cardParams.put("address_country", "Kenya");
            cardParams.put("address_line1", "9992 NRB");
            tokenParams.put("card", cardParams);

            Token token = Token.create(tokenParams);
            String transactionId = token.getId();
            return transactionId;
        }
    }
}
