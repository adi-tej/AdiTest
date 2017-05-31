package com.andigital.andservice.model.domain;

import java.util.Date;

/**
 * Created by rakeshchoudhary on 5/24/17.
 */
public class Invoice {

    private Long id;
    
    private String type;
    
    private String status;
    
    private Date invoiceDate;
    
    private String approvalStatus;
    
    private Date approvalDate;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the invoice date.
     *
     * @return the invoice date
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Sets the invoice date.
     *
     * @param invoiceDate the new invoice date
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * Gets the approval status.
     *
     * @return the approval status
     */
    public String getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * Sets the approval status.
     *
     * @param approvalStatus the new approval status
     */
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    /**
     * Gets the approval date.
     *
     * @return the approval date
     */
    public Date getApprovalDate() {
        return approvalDate;
    }

    /**
     * Sets the approval date.
     *
     * @param approvalDate the new approval date
     */
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", invoiceDate=").append(invoiceDate);
        sb.append(", approvalStatus='").append(approvalStatus).append('\'');
        sb.append(", approvalDate=").append(approvalDate);
        sb.append('}');
        return sb.toString();
    }
}
