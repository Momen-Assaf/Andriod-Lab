package edu.birzeit.courseproject.network;

import edu.birzeit.courseproject.models.PizzaTypeResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/types")
    Call<PizzaTypeResponse> getPizzaTypes();
}
