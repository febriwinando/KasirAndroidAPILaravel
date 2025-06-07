package tech.id.kasir.api;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.Path;
import tech.id.kasir.response_api.RestoranResponse;
import tech.id.kasir.response_api.login;

public interface api_transakasi_data {

    @FormUrlEncoded
    @POST("login/admin")
    Call<login> loginadmin(
            @Field("username") String username,
            @Field("password") String password
    );


    @GET("restoran/{id}")
    Call<RestoranResponse> getRestoran(@Path("id") int restoranId);

}
