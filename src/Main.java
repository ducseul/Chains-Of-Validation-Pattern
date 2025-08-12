import validation.Validation;
import validation.ValidationException;
import validation.rules.NotNullRule;
import validation.rules.PhoneNumberRule;
import validation.rules.CustomRule;

public class Main {
    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.setName("Drag0n");
        entity.setPhoneNumber("0364564605");

        // Perform validation
        try {
            new Validation<Entity>(entity)
                    .chains(
                            NotNullRule.of(Entity::getName, "Entity name is null"),
                            PhoneNumberRule.of(Entity::getPhoneNumber),
                            CustomRule.testIf(
                                    e -> e.getName().length() < 5,
                                    "Name is too short"
                            )
                    )
                    .validate();
        } catch (ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
            return;
        }
        System.out.println("Successfully validated!");
    }
}