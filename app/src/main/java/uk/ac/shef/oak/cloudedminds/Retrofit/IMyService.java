package uk.ac.shef.oak.cloudedminds.Retrofit;

import io.reactivex.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyService {
    @POST("entry")
    @FormUrlEncoded
    Observable<String> enterData(@Field("event") String event,
                                 @Field("date") String date,
                                 @Field("mood") String mood,
                                 @Field("mood_rating") String mood_rating,
                                 @Field("catastrophise") String catastrophise,
                                 @Field("generalise") String generalise,
                                 @Field("ignoring") String ignoring,
                                 @Field("self_critical") String self_critical,
                                 @Field("mind_reading") String ming_reading,
                                 @Field("changed_mood") String changed_mood,
                                 @Field("changed_rating") String changed_rating);
}
