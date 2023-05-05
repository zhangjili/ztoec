package com.jili.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 反洗钱评级等级划分表
 * </p>
 *
 * @author zjl
 * @since 2023-03-08
 */
@TableName("rating_antimoney_level")
public class RatingAntimoneyLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 产品类型；0.反洗钱；1.风控中心
     */
    @TableField("PROJECT_TYPE")
    private String projectType;

    /**
     * 会员类型；0.商户；1.会员
     */
    @TableField("CUSTOMER_TYPE")
    private String customerType;

    /**
     * 商户号
     */
    @TableField("MERCHANT_NO")
    private String merchantNo;

    /**
     * 商户名称
     */
    @TableField("MERCHANT_NAME")
    private String merchantName;

    /**
     * 总分数
     */
    @TableField("TOTAL_SCORE")
    private Double totalScore;

    /**
     * 等级
     */
    @TableField("LEVEL")
    private String level;

    /**
     * 操作人
     */
    @TableField("OPERATOR_ID")
    private String operatorId;

    /**
     * 创建时间
     */
    @TableField(value = "CAREATE_TIME",fill = FieldFill.INSERT)
    private LocalDateTime careateTime;

    /**
     * 更新时间
     */
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("STATUS")
    private String status;

    /**
     * 审批状态
     */
    @TableField("APPROVE_STATE")
    private String approveState;

    /**
     * 审核表ID
     */
    @TableField("APPROVE_IDS")
    private String approveIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    public LocalDateTime getCareateTime() {
        return careateTime;
    }

    public void setCareateTime(LocalDateTime careateTime) {
        this.careateTime = careateTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getApproveState() {
        return approveState;
    }

    public void setApproveState(String approveState) {
        this.approveState = approveState;
    }
    public String getApproveIds() {
        return approveIds;
    }

    public void setApproveIds(String approveIds) {
        this.approveIds = approveIds;
    }

    @Override

    public String toString() {
        return "RatingAntimoneyLevel{" +
            "id=" + id +
            ", projectType=" + projectType +
            ", customerType=" + customerType +
            ", merchantNo=" + merchantNo +
            ", merchantName=" + merchantName +
            ", totalScore=" + totalScore +
            ", level=" + level +
            ", operatorId=" + operatorId +
            ", careateTime=" + careateTime +
            ", updateTime=" + updateTime +
            ", status=" + status +
            ", approveState=" + approveState +
            ", approveIds=" + approveIds +
        "}";
    }
}
