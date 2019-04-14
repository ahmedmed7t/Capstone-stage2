package omdvet.com.WebServices;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import omdvet.com.WebServices.Models.ClientOrder;
import omdvet.com.WebServices.Requests.EmpRequest;
import omdvet.com.WebServices.Requests.LoginRequest;
import omdvet.com.WebServices.Requests.RegistRequest;
import omdvet.com.WebServices.Requests.addClientRequest;
import omdvet.com.WebServices.Requests.addOrderRequest;
import omdvet.com.WebServices.Requests.addProductRequest;
import omdvet.com.WebServices.Requests.clientInfoRequest;
import omdvet.com.WebServices.Requests.clientsRequest;
import omdvet.com.WebServices.Requests.getProductsRequest;
import omdvet.com.WebServices.Requests.payClientRequest;
import omdvet.com.WebServices.Requests.reportRequest;
import omdvet.com.WebServices.Requests.updateProductRequest;
import omdvet.com.WebServices.Responses.EmpResponse;
import omdvet.com.WebServices.Responses.GetAllBillesResponse;
import omdvet.com.WebServices.Responses.LoginResponse;
import omdvet.com.WebServices.Responses.RegistResponse;
import omdvet.com.WebServices.Responses.addClientResponse;
import omdvet.com.WebServices.Responses.addOrderResponse;
import omdvet.com.WebServices.Responses.addProductResponse;
import omdvet.com.WebServices.Responses.clientInfoResponse;
import omdvet.com.WebServices.Responses.clientsResponse;
import omdvet.com.WebServices.Responses.getProductClints;
import omdvet.com.WebServices.Requests.getProductsClints;
import omdvet.com.WebServices.Responses.getProductsResponse;
import omdvet.com.WebServices.Responses.payClientResponse;
import omdvet.com.WebServices.Responses.reportResponse;
import omdvet.com.WebServices.Responses.updateProductResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitService {
    @POST("register")
    Call<RegistResponse> REGIST_CALL(@Body RegistRequest request);

    @POST("login")
    Call<LoginResponse> LOGIN_CALL(@Body LoginRequest request);


    @POST("addClient")
    Call<addClientResponse> ADD_CLIENT_CALL(@Body addClientRequest request);

    @Multipart
    @POST("addProduct")
    Call<addProductResponse> addProduct(
            @Part("name") RequestBody name,
            @Part("price") RequestBody price,
            @Part("quantity") RequestBody quantity,
            @Part MultipartBody.Part photo);


    @POST("getProducts")
    Call<getProductsResponse> GET_PRODUCT_CALL(@Body getProductsRequest request);

    @POST("updateProduct")
    Call<updateProductResponse> UPDATE_PRODUCT_CALL(@Body updateProductRequest request);

    @POST("clients")
    Call<clientsResponse> CLIENTS_CALL(@Body clientsRequest request);


    @POST("report")
    Call<reportResponse> REPORT_CALL(@Body reportRequest request);

    @POST("clientInfo")
    Call<clientInfoResponse> CLIENT_INFO_CALL(@Body clientInfoRequest request);

    @POST("payment_client")
    Call<payClientResponse> PAY_CLIENT_CALL(@Body payClientRequest request);

    @POST("emps")
    Call<EmpResponse> EMP_CALL(@Body EmpRequest request);

    @POST("addOrder")
    Call<addOrderResponse> ADD_ORDER_CALL(@Body ClientOrder request);

    @POST("billes/client")
    Call<getProductClints> GetPClint (@Body getProductsClints request);

    @POST("billes")
    Call<GetAllBillesResponse> GetAllBilles ();
}
