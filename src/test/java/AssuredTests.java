import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AssuredTests {
    //Starts before the creation of the class
    @BeforeClass
    public void setUp() {
        baseURI = TestConfig.jsonplaceholderUrl;
        basePath = TestConfig.jsonplaceholderPath;

        //Sample for XML. An example for json can be executed in a similar way
        requestSpecification = TestConfig.castomRequestSpecificationXML;
    }

    //GET without query params
    @Test
    public void getWithoutParam() {
        given()
                .log().all() //log request
                .when()
                .get(TestConfig.jsonplaceholderGet) //end-point
                .then()
                .log().all() //log responce
                .statusCode(200); //check code responce

    }

    //GET with query params
    @Test
    public void getWithParams() {
        given()
                .queryParam("postId", 1, 2) //params
                .log().all() //log request
                .when()
                    .get(TestConfig.jsonplaceholderGet) //end-point
                .then()
                    .log().all() //log response
                    .statusCode(200); //check code response

    }

    //PUT
    @Test
    public void put() {
        /*
        {
        "id":1,
        "title":"foo",
        "body":"bar",
        "userId":1
        }
        */

        String putBodyJson = "{\n" +
                "\"id\":1,\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";

        given()
                .body(putBodyJson)
                    .log().uri() //log request
                .when()
                    .put(TestConfig.jsonplaceholderPut)
                .then()
                    .log().body() //log response
                    .statusCode(200); //check code response
    }

    //DELETE
    @Test
    public void delete() {
        given()
                .log().uri() //log request
                .when()
                    .delete(TestConfig.jsonplaceholderDelete)
                .then()
                    .log().body() //log response
                    .statusCode(200); //check code response
    }

    //POST with json. Example of use without specification
    @Test
    public void postWithJson() {
        /*
        {
        "title":"foo",
        "body":"bar",
        "userId":1
        }
        */

        String postBodyJson = "{\n" +
                "\"title\":\"foo\",\n" +
                "\"body\":\"bar\",\n" +
                "\"userId\":1\n" +
                "}";

        given()
                .body(postBodyJson)
                .log().uri() //log request
                .when()
                .post(TestConfig.jsonplaceholderPost)
                .then()
                .log().body() //log response
                .statusCode(200); //check code response
    }

    //Post with XML. Example of using the specification
    @Test
    public void postWithXml() {
        /*
        <Company>
          <Employee>
              <FirstName>Tanmay</FirstName>
              <LastName>Patil</LastName>
              <ContactNo>1234567890</ContactNo>
              <Email>tanmaypatil@xyz.com</Email>
              <Address>
                   <City>Bangalore</City>
                   <State>Karnataka</State>
                   <Zip>560212</Zip>
              </Address>
          </Employee>
        </Company>
         */

        String postBodyXml = "<Company>\n" +
                "  <Employee>\n" +
                "      <FirstName>Tanmay</FirstName>\n" +
                "      <LastName>Patil</LastName>\n" +
                "      <ContactNo>1234567890</ContactNo>\n" +
                "      <Email>tanmaypatil@xyz.com</Email>\n" +
                "      <Address>\n" +
                "           <City>Bangalore</City>\n" +
                "           <State>Karnataka</State>\n" +
                "           <Zip>560212</Zip>\n" +
                "      </Address>\n" +
                "  </Employee>\n" +
                "</Company>";

        given()
                .spec(TestConfig.castomRequestSpecificationXML)
                .body(postBodyXml)
                .log().all() //log request
                .when()
                .then()
                .spec(TestConfig.castomResponseSpecificationXMLForPost)
                .log().body() //log response
                .statusCode(200); //real code - 201. The service used emulates the request, so the response code is 200
    }

    //
//    @Test
//    public void postWith() {
//        String postIncorrectBodyXml = "<Company>\n" +
//                "  <Employee>\n" +
//                "      <FirstName>Tanmay</FirstName>\n" +
//                "      <LastName>Patil</LastName>\n" +
//                "      <ContactNo>1234567890</ContactNo>\n" +
//                "      <Email>tanmaypatil@xyz.com</Email>\n" +
//                "      <Address>\n" +
//                "           <City>Bangalore</City>\n" +
//                "           <State>Karnataka</State>\n" +
//                "           <Zip>560212</Zip>\n" +
//                "      </Address>\n" +
//                "  </Employee>\n" +
//                "</Company>";
//        JsonObject content = JsonObject.empty().put("success", "true");
//
//        given()
//                .body(postIncorrectBodyXml)
//                .log().uri() //log request
//                .when()
//                .post("https://enhn7stoyd7v.x.pipedream.net/")
//                .then()
//                .log().body() //log response
//                .statusCode(200)
//                .body("success", equalTo("true"));
//    }
}
