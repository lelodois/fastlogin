package br.com.lelo.fastlogin.message;

public class TokenMessage {

    private String hash;
    private String source;

    public TokenMessage() {
    }

    public TokenMessage(String hash, String source) {
        this.hash = hash;
        this.source = source;
    }

    public String getHash() {
        return hash;
    }

    public String getSource() {
        return source;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
