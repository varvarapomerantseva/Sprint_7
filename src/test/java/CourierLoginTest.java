import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.*;

public class CourierLoginTest {
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
    @DisplayName("Courier can login")
    public void courierCanBeLoginTest() {
        ValidatableResponse response = courierClient.create(courier);
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        courierId = loginResponse.extract().path("id");
        int statusCode = loginResponse.extract().statusCode();
        assertEquals("Status code is incorrect", SC_OK, statusCode);
        assertNotEquals("Courier not login", 0, courierId);


    }

    @Test
    public void courierIsNotLoginWithoutLoginTest() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(CourierGenerator.getWithoutLogin()));
        int statusCode = loginResponse.extract().statusCode();
        String messageError = loginResponse.extract().path("message");
        String messageExpected = "Недостаточно данных для входа";
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }

    @Test
    public void courierIsNotLoginWithoutPasswordTest() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(CourierGenerator.getWithoutPassword()));
        int statusCode = loginResponse.extract().statusCode();
        String messageError = loginResponse.extract().path("message");
        String messageExpected = "Недостаточно данных для входа";
        assertEquals("Status code is incorrect", SC_BAD_REQUEST, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }

    @Test
    @DisplayName("Courier is not login nonexistent user")
    public void courierIsNotLoginNonexistentUserTest() {
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(CourierGenerator.getNonexistentUser()));
        int statusCode = loginResponse.extract().statusCode();
        String messageError = loginResponse.extract().path("message");
        String messageExpected = "Учетная запись не найдена";
        assertEquals("Status code is incorrect", SC_NOT_FOUND, statusCode);
        assertEquals("Message is not true", messageExpected, messageError);

    }
}

