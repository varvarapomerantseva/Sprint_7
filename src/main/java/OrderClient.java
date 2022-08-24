import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient {

    private static final String ORDER_LIST = "/api/v1/orders";
    private static final String CREATE_ORDER = "/api/v1/orders";

    public ValidatableResponse getListOrder() {
        return given()
                .spec(getBaseSpec())
                .get(ORDER_LIST)
                .then();

    }

    public ValidatableResponse createOrder(Order order) {
        return given()
                .spec(getBaseSpec())
                .body(order)
                .when()
                .post(CREATE_ORDER)
                .then();
    }
}
