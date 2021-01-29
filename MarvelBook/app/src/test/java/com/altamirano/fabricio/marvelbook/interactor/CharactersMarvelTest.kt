package com.altamirano.fabricio.marvelbook.interactor

import com.altamirano.dagger.util.Constants
import com.altamirano.dagger.util.Constants.getAsUrl
import com.altamirano.dagger.models.ResponseCharacters
import com.altamirano.dagger.models.Thumbnail
import com.altamirano.fabricio.marvelbook.service.HandlerMarvelService
import com.altamirano.dagger.api.IApiService
import com.google.gson.Gson
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class CharactersMarvelTest : TestCase() {

    lateinit var mocServer: MockWebServer
    lateinit var marvelService: IApiService

    @Before
    public override fun setUp() {

        MockitoAnnotations.initMocks(this)
     /*   mocServer = MockWebServer()
        mocServer.start()*/

        marvelService = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IApiService::class.java)

        super.setUp()
    }

    @Test
    fun testingHashId() {
        assertEquals("79d6935a9459e7f38158d0a110562b57", Constants.HASH_ID)
    }

    fun testPreloadFile() {
        val handler = HandlerMarvelService("json_character.json")
        assertNotNull(handler.content)
    }

    @Test
    fun testCreateUrl() {
        val thumbnail = Thumbnail("http://developer.marvel.com/image", "png")
        assertEquals("https://developer.marvel.com/image.png", thumbnail.getAsUrl())
    }


    fun testLoadCharacters() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(HandlerMarvelService("json_character.json").content)

        mocServer.enqueue(response)

        val mResponse = marvelService.listCharacters(Constants.HASH_ID, 0).execute()
        assertEquals(response.status, mResponse.code().toString())
    }

    fun testLoadCharactersBody() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(HandlerMarvelService("json_character.json").content)

        mocServer.enqueue(response)


        val mResponse = marvelService.listCharacters(Constants.HASH_ID, 0).execute()

        val mockResponse =response.getBody()?.readUtf8()
        val responseCharacterMock = Gson().fromJson(mockResponse, ResponseCharacters::class.java)

        assertEquals(responseCharacterMock, mResponse.body())
        assertEquals(responseCharacterMock.data, mResponse.body()?.data)
        assertEquals(responseCharacterMock.data?.results, mResponse.body()?.data?.results)
    }
}