package example;

import bookstore.currency.RatesWrapper;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class XmlExample {

    @Test
    void currency() {


        try {
            URL url = new URL("http://api.nbp.pl/api/exchangerates/tables/A?format=xml");
            URLConnection urlConnection = url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String inputLine;
            String result = "";

            while ((inputLine = bufferedReader.readLine()) != null) {
                result = result + inputLine;
            }

            bufferedReader.close();


            XmlMapper mapper = new XmlMapper();

            RatesWrapper.
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
