package com.jili.jdk8.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MemberRuleSetting implements Serializable {
    private static final long serialVersionUID = 9024523697080413221L;

    private Integer id;
    private String batchNo;
    private String memberNo;
    private String ruleNo;
    private Integer memberType;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer authStatus;
    private String authBy;
    private Integer flag;
    private String note;
    private Integer del;
}
