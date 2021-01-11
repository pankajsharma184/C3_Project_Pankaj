import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {


    Restaurant restaurant;
    RestaurantService service;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        service = new RestaurantService();
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertNotNull(service.findRestaurantByName("Amelie's cafe"));
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,()->{
            service.findRestaurantByName("A2B");
        });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void selecting_food_items_from_menu_should_display_order_correct_value() {
        ArrayList<String> orderItem = new ArrayList<>();
        restaurant.addToMenu("Masala Dosa",50);
        restaurant.addToMenu("Idly", 30);
        restaurant.addToMenu("Vada", 40);
        restaurant.addToMenu("Uttampam", 60);
        restaurant.addToMenu("Kesari Bhat", 20);
        restaurant.addToMenu("Filter Coffee", 10);

        orderItem.add("Kesari Bhat");
        orderItem.add("Masala Dosa");
        orderItem.add("Vada");
        assertEquals(110, restaurant.getOrderValue(orderItem));

    }
    @Test
    public void removing_food_items_from_selected_list_should_reduce_the_order_value_by_removed_item_price() {
        ArrayList<String> orderItem = new ArrayList<>();
        restaurant.addToMenu("Masala Dosa",50);
        restaurant.addToMenu("Idly", 30);
        restaurant.addToMenu("Vada", 40);
        restaurant.addToMenu("Uttampam", 60);
        restaurant.addToMenu("Kesari Bhat", 20);
        restaurant.addToMenu("Filter Coffee", 10);

        orderItem.add("Kesari Bhat");
        orderItem.add("Masala Dosa");
        orderItem.add("Vada");

        orderItem.remove("Kesari Bhat");
        assertEquals(90, restaurant.getOrderValue(orderItem));

    }




}