package mycode.WalletOnline.WalletApplication.dto;

public class PaymentDto {
    private Long userId;
    private String merchantId;
    private Double amount;
    private String paymentMethodId;

    // Constructors
    public PaymentDto() {
    }

    public PaymentDto(Long userId, String merchantId, Double amount, String paymentMethodId) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.paymentMethodId = paymentMethodId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}

