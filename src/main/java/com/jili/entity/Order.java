package com.jili.entity;


import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
public class Order {
 
    /**
     * 订单原价金额
     */
    private int price;
 
    /**
     *下单人
     */
    private User user;
 
    /**
     *积分
     */
    private int score;
 
    /**
     * 下单日期
     */
    private Date bookingDate;

    private Integer amout;

    public Integer getAmout() {
        return amout;
    }

    public void setAmout(Integer amout) {
        this.amout = amout;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", user=" + user +
                ", score=" + score +
                ", bookingDate=" + bookingDate +
                ", amout=" + amout +
                '}';
    }
}