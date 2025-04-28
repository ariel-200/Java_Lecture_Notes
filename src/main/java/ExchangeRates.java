import kong.unirest.Unirest;

import java.util.Map;

import static input.InputUtils.doubleInput;

public class ExchangeRates {

    public static void main(String[] args) {

        String url = "https://1150-exchange-rates.azurewebsites.net/latest";

        Map<String, Object> params = Map.of("base", "USD", "symbols", "EUR");

        //  make request here
        RateResponse response = Unirest.get(url).queryString(params).asObject(RateResponse.class).getBody();
        String date = response.date;
        double rate = response.rates.EUR;
        double dollars = doubleInput("How many USD to convert to Euros? ");
        double euros = dollars * rate;
        System.out.println("On " + date + " the exchange rate from USD to Euros is " +  rate);
        System.out.println("$" + dollars + " converted to Euros is " + euros);

    }
}

class RateResponse {
    public String base;
    public String date;
    public Rates rates;
}

class Rates {
    public double EUR;

}