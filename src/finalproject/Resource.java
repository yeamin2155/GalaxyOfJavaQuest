package finalproject;

/**
 * Represents resources in the Galaxy of JavaQuest game.
 */
public class Resource extends SpaceEntity {
    private ResourceType type;
    private int quantity;
    private double value;

    public Resource(String name, ResourceType type, int quantity, double value) {
        super(name);
        this.type = type;
        this.quantity = quantity;
        this.value = value;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getDescription() {
        return "Resource: " + getName() + 
               " (Type: " + type + 
               ", Quantity: " + quantity + 
               ", Value: " + value + ")";
    }

    public void modifyQuantity(int change) {
        this.quantity += change;
        if (this.quantity < 0) this.quantity = 0;
    }
}
