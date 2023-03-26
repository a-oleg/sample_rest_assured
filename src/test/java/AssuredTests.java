import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AssuredTests {
    public static String jsonplaceholderUrl = "https://jsonplaceholder.typicode.com/";
    public static String jsonplaceholderPath = "";
    public static String jsonplaceholderGet = "comments/";
    public static String jsonplaceholderPut = "posts/1/";
    public static String jsonplaceholderDelete = "posts/1/";
    public static String jsonplaceholderPost = "posts/";

    //Starts before the creation of the class
    @BeforeClass
    public void setUp() {
        baseURI = jsonplaceholderUrl;
        basePath = jsonplaceholderPath;
    }

    //GET without query params
    @Test
    public void getWithoutParam() {
        given()
                .log().all() //log request
                .when()
                .get(jsonplaceholderGet) //end-point
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
                    .get(jsonplaceholderGet) //end-point
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
                    .put(jsonplaceholderPut)
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
                    .delete(jsonplaceholderDelete)
                .then()
                    .log().body() //log response
                    .statusCode(200); //check code response
    }

    //POST with json
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
                .post(jsonplaceholderPost)
                .then()
                .log().body() //log response
                .statusCode(201); //check code response
    }

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
                .body(postBodyXml)
                .log().uri() //log request
                .when()
                .post("https://enhn7stoyd7v.x.pipedream.net")
                .then()
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
