package net.benfro.lab.translationfrontend.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HtmlControllerTest(@Autowired val restTemplate: TestRestTemplate) {

  @Test
  fun `Assert blog page title, content and status code`() {
    val entity = restTemplate.getForEntity<String>("/")
    assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
    assertThat(entity.body).contains("<h1>Welcome to Translation Front End Application</h1>")
  }

}