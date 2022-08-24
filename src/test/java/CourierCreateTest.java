import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CourierCreateTest {

    private Courier courier;
    private CourierClient courierClient;

    private int courierId;


    @Before
    public void setUp() {
        courier = CourierGenerator.getDefault();
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        courierClient.deleteCourier(courierId);
    }


    @Test
    @DisplayName("Courier can be created")
    public void courierCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        boolean isCreated = response.extract().path("ok");

        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        assertTrue("Courier is not created", isCreated);

        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");


    }

    @Test
    @DisplayName("Cannot create two identical couriers")
    public void doubleCourierNotCanBeCreatedTest() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");
        ValidatableResponse responseTwo = courierClient.create(courier);
        int statusCode = responseTwo.extract().statusCode();
        String messageError = responseTwo.extract().path("message");
        String messageExpected = "Этот логин уже используется";
        assertEquals("Status code is incorrect", SC_CONFLICT, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }

    @Test
    @DisplayName("Courier is not created without login")
    public void courierIsNotCreatedWithoutLoginTest() {
        ValidatableResponse response = courierClient.create(CourierGenerator.getWithoutLogin());
        int statusCode = response.extract().statusCode();
        String messageError = response.extract().path("message");
        String messageExpected = "Недостаточно данных для создания учетной записи";
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }

    @Test
    @DisplayName("Courier is not created without password")
    public void courierIsNotCreatedWithoutPasswordTest() {
        ValidatableResponse response = courierClient.create(CourierGenerator.getWithoutPassword());
        int statusCode = response.extract().statusCode();
        String messageError = response.extract().path("message");
        String messageExpected = "Недостаточно данных для создания учетной записи";
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }


}

