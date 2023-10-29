package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/29/2023 11:18 PM
 */
public class KeyboardButton {

    private String text;
    private String url;
    private String callbackData;

    public KeyboardButton() {
    }

    public KeyboardButton(String text, String url, String callbackData) {
        this.text = text;
        this.url = url;
        this.callbackData = callbackData;
    }

    public KeyboardButton withText(String text) {
        this.text = text;
        return this;
    }

    public KeyboardButton withUrl(String url) {
        this.url = url;
        return this;
    }

    public KeyboardButton withCallbackData(String callbackData) {
        this.callbackData = callbackData;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    @Override
    public String toString() {
        return "KeyboardButton{" +
                "text='" + text + '\'' +
                ", url='" + url + '\'' +
                ", callbackData='" + callbackData + '\'' +
                '}';
    }
}
