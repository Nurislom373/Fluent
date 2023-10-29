package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 11:26 PM
 */
public class RowKeyboardButton extends KeyboardButton {

    private Integer row;

    public RowKeyboardButton() {
    }

    public RowKeyboardButton(Integer row) {
        this.row = row;
    }

    public RowKeyboardButton(String text, String url, String callbackData, Integer row) {
        super(text, url, callbackData);
        this.row = row;
    }

    public RowKeyboardButton withRow(Integer row) {
        this.row = row;
        return this;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "RowKeyboardButton{" +
                "row=" + row +
                '}';
    }
}
