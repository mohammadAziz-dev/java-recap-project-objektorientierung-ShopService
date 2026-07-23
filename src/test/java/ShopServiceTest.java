import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.PROCESSING);
        assertEquals(expected.products(), actual.products());
        // assertNotNull(expected.id());
        assertNotNull(actual.id());
    }

    @Test
    //void addOrderTest_whenInvalidProductId_expectNull() {
    void addOrder_whenProductDoesNotExist_throwsException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        //Order actual = shopService.addOrder(productsIds);

        //THEN
        //assertNull(actual);
        // WHEN & THEN
        assertThrows(
                NoSuchElementException.class,
                () -> shopService.addOrder(productsIds)
        );
    }

    @Test
    void getOrdersByStatus_whenStatusMatches_returnsOrders() {
        // GIVEN
        ShopService shopService = new ShopService();

        shopService.addOrder(List.of("1"));
        shopService.addOrder(List.of("1"));

        // WHEN
        List<Order> actual =
                shopService.getOrdersByStatus(OrderStatus.PROCESSING);

        // THEN
        assertEquals(2, actual.size());
        assertTrue(
                actual.stream()
                        .allMatch(order ->
                                order.status() == OrderStatus.PROCESSING
                        )
        );
    }
}
