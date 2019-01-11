package com.voam.loggingconsumer;

public class FoodOrder {
 
    private String restaurant;
    private String customerAddress;
    private String orderDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


 
    public FoodOrder(){
 
    }
 
    public FoodOrder(String restaurant, String customerAddress, String orderDescription, String name) {
        this.restaurant = restaurant;
        this.customerAddress = customerAddress;
        this.orderDescription = orderDescription;
        this.name = name;
    }
 
    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
 
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
 
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
 
    public String getRestaurant() {
        return restaurant;
    }
 
    public String getCustomerAddress() {
        return customerAddress;
    }
 
    public String getOrderDescription() {
        return orderDescription;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "restaurant='" + restaurant + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}