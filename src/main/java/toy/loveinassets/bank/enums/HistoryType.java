package toy.loveinassets.bank.enums;

public enum HistoryType {
    DEPOSIT("입금"), WITHDRAWAL("출금");

    private String historyName;

    HistoryType(String historyName) {
        this.historyName = historyName;
    }
}
