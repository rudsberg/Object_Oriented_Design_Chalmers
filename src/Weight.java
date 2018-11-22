public class Weight {

    /**
     *
     * Weight is a class which handles all weight-related issues. All other classes delegate
     * their weight handling to this class.
     */
    private int weight;

    public Weight(int weight) {
        this.weight = weight;
    }

    /**
     * Adds the @amount to weight variable.
     * @param amount weight added to total weight.
     */
    public void addToWeight(int amount) {
        weight += amount;
    }

    /**
     *
     * Removes weight @amount weight from the total weight.
     * @param amount Weight to remove.
     */
    public void removeWeight(int amount) {
        weight -= amount;
    }

    /** Returns the whole object of Weight(this).
     *
     */
    public Weight getReferenceWeight() {
        return this;
    }

    /**Returns the integer variable weight.
     *
     */
    public int getWeight() {
        return weight;
    }
}
