import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestConfig {
    public static String jsonplaceholderUrl = "https://jsonplaceholder.typicode.com/";
    public static String jsonplaceholderPath = "";
    public static String jsonplaceholderGet = "comments/";
    public static String jsonplaceholderPut = "posts/1/";
    public static String jsonplaceholderDelete = "posts/1/";
    public static String jsonplaceholderPost = "posts/";

    //RequestSpecification. You can add an annotation @BeforeClass if you need to call the request specification before all tests
    public static RequestSpecification castomRequestSpecificationXML = new RequestSpecBuilder()
            .addHeader("Content-Type", "application/xml")
            .addCookie("testCookieXML")
            .setBaseUri("https://enhn7stoyd7v.x.pipedream.net")
            .build();

    //ResponseSpecification for get
    public static ResponseSpecification castomResponseSpecificationXMLForGet = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    //ResponseSpecification for post
    public static ResponseSpecification castomResponseSpecificationXMLForPost = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .build();
}
