package com.example.umc_week9_standard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.umc_week9_standard.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //웹 브라우저 창열기
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.odcloud.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 어떤 주소를 입력했다
        val apiService = retrofit.create(ApiService::class.java)

        //입력한 주소 중 하나로 연결시도
        apiService.getCheckMail(10 ).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                val responseData = response.body()

                // 데이터가 안 넘어올 수 있어서 체크
                if (responseData != null) {
//                    val list: List<MovieDto> = responseData!!.boxofficeResult!!.dailyBoxOfficeList
                    Log.d("Retrofit", "Response\nCode: ${responseData.num} resultValue: ${responseData.name} ")
                }
                // 응답이 왔지만 실패인 경우
                else {
                    // http 프로토콜 오류 code (전세계 규격 코드)
                    Log.w("Retrofit", "Response Not Successful ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Retrofit", "Error!", t)
            }

        })
    }
}


