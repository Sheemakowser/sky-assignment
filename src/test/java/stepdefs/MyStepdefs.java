package stepdefs;

import helpers.TestProperties;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import helpers.BaseRequest;

import java.util.*;


public class MyStepdefs extends BaseRequest {
    private Properties properties = TestProperties.loadProperties();
    private String BASE_URL = properties.getProperty("BASE_URL");
    private String ARTICLES_RESOURCE_PATH = properties.getProperty("ARTICLES_RESOURCE_PATH");
    private String INVALID_RESOURCE_PATH = properties.getProperty("INVALID_RESOURCE_PATH");


    private static Response response;

    @Given("I have a valid endpoint")
    public void IHaveAValidEndpoint() {
        request.baseUri(BASE_URL);
    }

    @When("I attempt to get a list of articles")
    public void IAttemptToGetAListOfArticles() {
        response = request.get(ARTICLES_RESOURCE_PATH);
    }

    @When("I attempt to get an article with id {long}")
    public void IAttemptToGetAParticularArticle(Long id) {
        response = request.get(ARTICLES_RESOURCE_PATH + "/" + id);
    }

    @When("I attempt to get articles from an invalid path")
    public void IAttemptToGetArticlesFromAnInvalidPath() {
        response = request.get(INVALID_RESOURCE_PATH);
    }

    @When("I attempt to perform a POST action using valid path {string}")
    public void IAttemptToPerformAPOSTActionUsingValidPath(String path) {
        response = request.header("Content-Type", "application/json").when().post(path);
    }

    @When("I attempt to perform a PUT action using valid path {string}")
    public void IAttemptToPerformAPUTActionUsingValidPath(String path) {
        response = request.header("Content-Type", "application/json").when().put(path);
    }

    @When("I attempt to perform a DELETE action using valid path {string}")
    public void IAttemptToPerformADELETEActionUsingValidPath(String path) {
        response = request.header("Content-Type", "application/json").when().delete(path);
    }

    @Then("the received response status code is {int}")
    public void theResponseIsReceivedSuccessfully(Integer statusCode) {
        Assert.assertEquals(statusCode.longValue(), response.statusCode());
    }

    @Then("the response body has value {string}")
    public void theResponseBody(String responseBody) {
        Assert.assertEquals("\"" + responseBody + "\"", response.getBody().asString());
    }

    @And("the response format is JSON")
    public void theResponseFormatIsJSON() {
        String contentType = response.contentType();
        Assert.assertEquals("application/json", contentType);
    }

    @And("the response contains the list of articles")
    public void theResponseContainsTheListOfArticles() {
        String res = response.getBody().asString();
        if (!res.isEmpty()) {
            List<String> articles = JsonPath.from(res).get("id");
            articles.removeIf(Objects::isNull);
            Assert.assertTrue("List of articles in the response is 0", articles.size() > 0);
        } else
            Assert.fail("Response body is empty");
    }

    @And("the response contain an article {long} with {string}")
    public void theResponseContainsAParticularArticle(Long id, String title) {
        String res = response.getBody().asString();
        if (!res.isEmpty()) {
            String articleId = JsonPath.from(res).get("id");
            Assert.assertEquals(id.toString(), articleId);
            String articleTitle = JsonPath.from(res).get("title");
            Assert.assertEquals(title, articleTitle);
        } else
            Assert.fail("Response body is empty");
    }

}


