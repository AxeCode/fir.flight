package io.github.ryanhoo.firFlight.data.source.remote.api;

import java.util.List;

import io.github.ryanhoo.firFlight.data.model.AppInstallInfo;
import io.github.ryanhoo.firFlight.data.model.Courses;
import io.github.ryanhoo.firFlight.data.model.Message;
import io.github.ryanhoo.firFlight.data.model.Token;
import io.github.ryanhoo.firFlight.data.model.User;
import io.github.ryanhoo.firFlight.network.MultiPageResponse;
import retrofit2.http.*;
import rx.Observable;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 5/31/16
 * Time: 11:10 PM
 * Desc: RESTFulApiService
 */
public interface RESTFulApiService {

    // Token

    @FormUrlEncoded
    @POST("/login")
    Observable<Token> accessToken(@Field("email") String email, @Field("password") String password);

    @GET("/user/api_token")
    Observable<Token> apiToken();

    @POST("/user/api_token")
    Observable<Token> refreshApiToken();

    // User

    @GET("/user")
    Observable<User> user();

    // Apps

    @GET("/courses?type=sys&mock=true&mock_data=majors/58f0331bc6bdf222b1744e10.json&mock_delay=3000")
    Observable<List<Courses>> courses();

    @GET("/courses?type=sys&mock=true&mock_data=math/{classId}.json&mock_delay=3000")
    Observable<Courses> classes(@Path("classId") String classId);

    @GET("/apps/latest/{appId}?requireApiToken=true")
    Observable<AppInstallInfo> appInstallInfo(@Path("appId") String appId);

    // Messages(no longer available)

    @Deprecated
    @GET("/notifications?type=sys&mock=true&mock_data=messages.json&mock_delay=3000")
    Observable<MultiPageResponse<Message>> systemMessages();

    @Deprecated
    @GET("/notifications")
    Observable<MultiPageResponse<Message>> messages(@Query("type") String type, @Query("page") int page);

    @Deprecated
    @POST("/notifications/{notificationId}")
    Observable<Message> markMessageAsRead(
            @Path("notificationId") String notificationId,
            @Query("is_read") boolean isRead
    );

    @Deprecated
    @DELETE("/notifications/{notificationId}")
    Observable<Message> deleteMessage(@Path("notificationId") String notificationId);
}
