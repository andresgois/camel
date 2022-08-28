package br.com.camel.model;


@Getter
@Setter
@NoArgsContructor
@Entity
public class Person {
    
    @Id
    @generatedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private Integer age;
}
