import com.example.network.ApiService
import com.example.network.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get user by id should return user`() = runTest {
        // Подготовка mock ответа
        val mockResponse = MockResponse()
            .setBody("""{"id": 1, "name": "John Doe", "email": "john@example.com"}""")
            .setResponseCode(200)

        mockWebServer.enqueue(mockResponse)

        // Вызов API
        val user = apiService.getUser(1)

        // Проверки
        assertEquals(1, user.id)
        assertEquals("John Doe", user.name)

        // Проверка запроса
        val request = mockWebServer.takeRequest()
        assertEquals("/users/1", request.path)
        assertEquals("GET", request.method)
    }

    @Test
    fun `create user should return created user`() = runTest {
        val testUser = User(name = "Test", email = "test@example.com")
        val mockResponse = MockResponse()
            .setBody("""{"id": 1, "name": "Test", "email": "test@example.com"}""")
            .setResponseCode(201)

        mockWebServer.enqueue(mockResponse)

        val response = apiService.createUser(testUser)

        assertTrue(response.isSuccessful)
        assertEquals(1, response.body()?.id)
        assertEquals("Test", response.body()?.name)

        val request = mockWebServer.takeRequest()
        assertEquals("/users", request.path)
        assertEquals("POST", request.method)
    }
}