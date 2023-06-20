package br.com.account.account.infrastructure.entry.http.controller.post

import br.com.account.account.infrastructure.dto.entry.AccountNewEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.UUID
import java.util.function.Predicate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateAccountControllerTest{

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

        assertThat(andExpect.andReturn().response.contentAsString)
            .contains("\"message\":\"{application=must not be empty, email=size must be between 5 and 100, username=size must be between 5 and 100}\",\"path\":\"\"")
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
