package kr.nexparan.louibitTrade.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User_Role {
    @Id //Primary Key
    private Long userId;

    @Id //Primary Key
    private Long roleId;
}
