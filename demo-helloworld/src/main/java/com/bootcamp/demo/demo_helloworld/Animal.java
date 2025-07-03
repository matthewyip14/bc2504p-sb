public class Animal {
  // ! attribute has no ordering
  // ! JSON has no ordering
  private Integer age; // ! Avoid primitive for serialization
  private String name;

  // ! attribute name has to be same as the JSON field name
}