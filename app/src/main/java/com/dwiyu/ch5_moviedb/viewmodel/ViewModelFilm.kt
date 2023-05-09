package com.dwiyu.ch5_moviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiyu.ch5_moviedb.model.NowPlayingMovieResponse
import com.dwiyu.ch5_moviedb.model.NowplayingMovie
import com.dwiyu.ch5_moviedb.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {
    var liveDataMovie: MutableLiveData<List<NowPlayingMovieResponse>?> = MutableLiveData()

    fun callApi() {
        ApiClient.instance.getMovie().enqueue(object : Callback<NowplayingMovie> {
            override fun onResponse(
                call: Call<NowplayingMovie>,
                response: Response<NowplayingMovie>
            ) {
                if (response.isSuccessful) {
                    liveDataMovie.postValue(response.body()?.results)
                } else {
                    liveDataMovie.postValue(null)
                }
            }

            override fun onFailure(call: Call<NowplayingMovie>, t: Throwable) {
                liveDataMovie.postValue(null)
            }

        })
    }
}
