package com.eritlab.jexmon.domain.repository
import com.eritlab.jexmon.domain.item.*
import com.eritlab.jexmon.domain.model.userDetail.UserDetailModel
import com.eritlab.jexmon.domain.model.userForgetPassword.UserForgetPasswordResponseModel
import com.eritlab.jexmon.domain.model.userLogin.UserLoginModel
import com.eritlab.jexmon.domain.model.userOTP.UserOTPResponseModel
import com.eritlab.jexmon.domain.model.userRegister.UserRegisterResponseModel
import com.eritlab.jexmon.domain.model.userResetPassword.UserResetPasswordResponseModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserAPI {
    @POST("user/login")
    suspend fun login(@Body login: UserLoginResponseItem):UserLoginModel
    @POST("user/register")
    suspend fun register(@Body register: UserRegisterRequestItem):UserRegisterResponseModel
    @POST("user/otp")
    suspend fun otp(@Body otp:UserOTPRequestItem):UserOTPResponseModel
    @POST("user/forgetPassword")
    suspend fun forgetPassword(@Body forgetPassword:UserForgetPasswordRequestItem):UserForgetPasswordResponseModel
    @POST("user/resetPassword")
    suspend fun resetPassword(@Body resetPassword:UserResetPasswordRequestItem):UserResetPasswordResponseModel
    @GET("user/{userId}")
    suspend fun getUser(@Path("userId") userId:Int):UserDetailModel
}