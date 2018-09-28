package com.osamaomar.akhbarak.API;

import com.osamaomar.akhbarak.model.CommentsModel;
import com.osamaomar.akhbarak.model.GetPostsModel;
import com.osamaomar.akhbarak.model.LoginModel;
import com.osamaomar.akhbarak.model.MailValidationModel;
import com.osamaomar.akhbarak.model.RegisterModel;
import com.osamaomar.akhbarak.model.TagsModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("GetPosts/{pagination_id}")
    @Headers("Accept: Application/json")
    Call<GetPostsModel> getPostsData(
            @Path("pagination_id") String pagination_id);

    @GET("PostComments/{post_id}")
    @Headers("Accept: Application/json")
    Call<CommentsModel> getCommentsData(
            @Path("post_id") String post_id);


    @GET("Tags/{rowsnum}")
    @Headers("Accept: Application/json")
    Call<TagsModel> getTagsData(
            @Path(value = "rowsnum", encoded = true) int rowsnum);


    @Multipart
    @POST("Rejster")
    Call<RegisterModel> UserRegister(
            @Part("Name") RequestBody Name,
            @Part("FullName") RequestBody FullName,
            @Part("Phone") RequestBody Phone,
            @Part("Email") RequestBody Email,
            @Part("Address") RequestBody Address,
            @Part("Token") RequestBody Token,
            @Part("CityId") RequestBody CityId,
            @Part("Password") RequestBody Password,
            @Part MultipartBody.Part Photo
    );

    @Multipart
    @POST("ValidateEmail")
    Call<MailValidationModel> CheckMailValidation(
            @Part("Email") RequestBody Email
    );

    @Multipart
    @POST("Login")
    Call<LoginModel> UserLogin(
            @Part("Email") RequestBody uname,
            @Part("Password") RequestBody password
    );


    @Multipart
    @POST("AddPost")
    Call<ResponseBody> ADDPost(
            @Part List<MultipartBody.Part> files,
            @Part  ("Photo") MultipartBody.Part videoImage,
            @Part  MultipartBody.Part video,
            @Part("UserId") RequestBody UserId,
            @Part("Title") RequestBody Title,
            @Part("Latitude") RequestBody Latitude,
            @Part("Longitude") RequestBody Longitude,
            @Part("Place_name") RequestBody Place_name,
            @Part("TagId") RequestBody TagId
    );

    @Multipart
    @POST("AddPost")
    Call<ResponseBody> ADDPost2(
            @Part List<MultipartBody.Part> files,
            @Part  ("Photo") RequestBody videoImage,
            @Part ("Video") RequestBody video,
            @Part("UserId") RequestBody UserId,
            @Part("Title") RequestBody Title,
            @Part("Latitude") RequestBody Latitude,
            @Part("Longitude") RequestBody Longitude,
            @Part("Place_name") RequestBody Place_name,
            @Part("TagId") RequestBody TagId
    );



    @GET("LikeComment/{UserId}/{CommentId}")
    @Headers("Accept: Application/json")
    Call<ResponseBody> makeLike(
            @Path("UserId") String UserId,
            @Path("CommentId") String CommentId);

    @Multipart
    @POST("AddComment")
    Call<ResponseBody> ADDComment(
            @Part ("UserId") RequestBody UserId,
            @Part ("PostId") RequestBody Title,
            @Part ("Comment") RequestBody Latitude,
            @Part ("Photo") MultipartBody.Part Photo
            );


    ////////in case of without part
    @Multipart
    @POST("AddComment")
    Call<ResponseBody> ADDComment2(
            @Part ("UserId") RequestBody UserId,
            @Part ("PostId") RequestBody Title,
            @Part ("Comment") RequestBody Latitude,
            @Part ("Photo") RequestBody Photo
    );

}
