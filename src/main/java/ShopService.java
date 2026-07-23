import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId)
                    .orElseThrow(() ->
                            new NoSuchElementException(
                                    "Product with ID " + productId + " does not exist."
                            )
                    );

            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.PROCESSING);

        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders()
                .stream()
                .filter(order -> order.status() == status)
                .toList();
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Order existingOrder = orderRepo.getOrderById(orderId);

        if (existingOrder == null) {
            throw new NoSuchElementException(
                    "Order with ID " + orderId + " does not exist."
            );
        }

        Order updatedOrder = existingOrder.withStatus(newStatus);

        orderRepo.updateOrder(updatedOrder);

        return updatedOrder;
    }
}
