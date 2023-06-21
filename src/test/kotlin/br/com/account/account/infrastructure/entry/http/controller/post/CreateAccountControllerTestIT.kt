package br.com.account.account.infrastructure.entry.http.controller.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
class CreateAccountControllerTestIT{

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object {
        private const val URI = "/accounts"

    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun `deve criticar os dados obrigatórios quando não forem enviados`() {

        val requestBody = """
            {
            
            }
        """

        val andExpect = mockMvc.post(URI) {
            headers {
                contentType = MediaType.APPLICATION_JSON

            }
            content = requestBody
        }.andExpect {
            status { isBadRequest() }
        }

        assertThat(andExpect.andReturn().response.contentAsByteArray.toString(Charsets.UTF_8))
            .contains("{application=Não deve ser nulo, email=Não deve ser nulo, username=Não deve ser nulo}\",\"path\":\"\"")
    }

    @Test
    fun `deve criar uma conta e um usuário master quando os dados forem enviados`() {
        val applicationId = UUID.randomUUID()
        val requestBody = """
            {
                "application": "$applicationId",
                "email": "43434343",
                "termAccept": "true",
                "username": "2323232"
            }
        """.trimIndent()
        mockMvc.post(URI){
            headers { contentType=MediaType.APPLICATION_JSON

            }
            content = requestBody
        }.andExpect { status { isCreated() } }
    }


}
