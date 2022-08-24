import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(Parameterized.class)
public class OrderCreateTest {

    private OrderClient orderClient;

    private final Order order;

    public OrderCreateTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {OrderGenerator.orderWithBlack()},
                {OrderGenerator.orderWithGrey()},
                {OrderGenerator.orderWithoutColor()},
                {OrderGenerator.orderWithBothColor()}
        };
    }

    @Before
    public void setUp() {
        orderClient = new OrderClient();

    }

    @Test
    public void orderCanBeCreatedTest() {
        ValidatableResponse response = orderClient.createOrder(order);
        int statusCode = response.extract().statusCode();
        int track = response.extract().path("track");
        assertEquals("Status code is incorrect", SC_CREATED, statusCode);
        assertNotEquals("Order not created", 0, track);

    }
}

