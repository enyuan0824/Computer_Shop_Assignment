package com.example.new_shop;

public class ViewOrder {
    private long id;
    private String name;
    private String product_name;
    private String payment_type;
    private String delivery_option;
    private String total;
    private String process;

    public long getId(){return id;}
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPaymentType() {
        return payment_type;
    }

    public void setPaymentType(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getDeliveryOption() {
        return delivery_option;
    }

    public void setDeliveryOption(String delivery_option) {
        this.delivery_option = delivery_option;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getProcess(){
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }
}
