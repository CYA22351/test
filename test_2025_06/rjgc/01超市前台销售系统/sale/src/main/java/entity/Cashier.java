package entity;


import lombok.Data;

@Data
public class Cashier {
    private int cashierId;
    private String cashierName;
    private String phone;
    private String password;
    private int roleId;

    // 构造方法、Getter和Setter省略...

}    