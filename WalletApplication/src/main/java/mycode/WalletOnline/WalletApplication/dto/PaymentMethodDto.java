package mycode.WalletOnline.WalletApplication.dto;

public class PaymentMethodDto {
    private Long userId;
    private String type;
    private String details;

    // Constructors
    public PaymentMethodDto() {
    }

    public PaymentMethodDto(Long userId, String type, String details) {
        this.userId = userId;
        this.type = type;
        this.details = details;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
