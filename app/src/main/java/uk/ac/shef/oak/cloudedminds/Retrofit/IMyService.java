package uk.ac.shef.oak.cloudedminds.Retrofit;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import uk.ac.shef.oak.cloudedminds.Entry;

/**
 * This interface implements the GET and POST requests which are sent to the server in order to
 * retrieve user entries and login details, alongside adding entries into the database.
 * Based on tutorial by EMDT Dev on Youtube: https://www.youtube.com/watch?v=4Xq2FUJvE-c
 *
 * @FormUrlEncoded sets the mime type of the request to application/x-www-form-urlencoded
 */
public interface IMyService {

    @POST("/")
    @FormUrlEncoded
    Observable<String> enterData(@Field("user") String user,
                                 @Field("event") String event,
                                 @Field("date") String date,
                                 @Field("mood") String mood,
                                 @Field("mood_rating") Integer mood_rating,
                                 @Field("catastrophise") String catastrophise,
                                 @Field("generalise") String generalise,
                                 @Field("ignoring") String ignoring,
                                 @Field("self_critical") String self_critical,
                                 @Field("mind_reading") String ming_reading,
                                 @Field("changed_mood") String changed_mood,
                                 @Field("changed_rating") Integer changed_rating);


    @POST("/patients")
    @FormUrlEncoded
    Observable<String> signupUser(@Field("name") String name,
                                  @Field("username") String username,
                                  @Field("password") String password);


    @POST("/login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("username") String username,
                                  @Field("password") String password);

    @GET("/entries")
    Call<List<Entry>> getEntries();



}
