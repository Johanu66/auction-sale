package models;

public record Product(int id, String name, String owner, float bid) {
    @Override
    public final String toString() {
        return String.format("{id=%d, name='%s', owner='%s', bid=%.2f}", id, name, owner, bid);
    }
}
