package mycode.WalletOnline.WalletApplication.dto;

public class TransactionDto {
    private Long userId;
    private Double amount;
    private String type;

    // Constructors
    public TransactionDto() {
    }

    public TransactionDto(Long userId, Double amount, String type) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
