import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    public RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://qa-scooter.praktikum-services.ru/")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();


    }
}
