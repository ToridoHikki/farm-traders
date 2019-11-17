package vn.silverbot99.farm_traders.app.network;

import retrofit2.Call;
import retrofit2.http.GET;
import vn.silverbot99.farm_traders.app.data.network.response.CategoriesResponse;

public class ApiInterfac {
    public interface ApiInterface {

        /*@GET("movie/popular")
        fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") PageNo: Int): Call<MovieListResponse>

        @GET("movie/{movie_id}")
        fun getMovieDetails(
            @Path("movie_id") movieId: Int, @Query("api_key") apiKey: String, @Query(
                "append_to_response"
            ) credits: String
        ): Call<Movie>*/

        @GET("getCategories")
        Call<CategoriesResponse> getCatalogies();
    }
}
