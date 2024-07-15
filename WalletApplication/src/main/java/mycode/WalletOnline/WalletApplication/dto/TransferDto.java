package mycode.WalletOnline.WalletApplication.dto;

public class TransferDto {
    private Long senderUserId;
    private Long receiverUserId;
    private Double amount;

    // Constructors
    public TransferDto() {
    }

    public TransferDto(Long senderUserId, Long receiverUserId, Double amount) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(Long senderUserId) {
        this.senderUserId = senderUserId;
    }

    public Long getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(Long receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

