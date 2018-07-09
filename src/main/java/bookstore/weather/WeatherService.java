package bookstore.weather;

import bookstore.users.daos.UserDAO;
import bookstore.users.services.UserContextHolder;
import bookstore.weather.model.WeatherResult;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

@Service
public class WeatherService {

    @Autowired
    private UserDAO userDAO;

    private Gson gson = new Gson();

    public String getWeather() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .build();

        OpenWeatherMapJ8 currentWeather = retrofit.create(OpenWeatherMapJ8.class);

        String email = UserContextHolder.getUserLoggedIn();

        String city = userDAO.getUserByEmail(email)
                .map(e -> e.getUserAddress().getCity())
                .orElse(null);
            //  .orElseGet(()-> null); //Suplier. Leniwy kod.

        CompletableFuture<WeatherResult> current = currentWeather.currentByCity(
                city,
                "ea900b66f547fd7b23625544873a4200",
                "metric",
                "pl"
        );

        return current
                .thenApplyAsync(forecast -> gson.toJson(forecast))
                .join();
    }


}
