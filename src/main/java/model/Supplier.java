package model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Supplier{
    private Integer id;
    private String name;
    private String company;
    private String email;
    private String phoneNo;
}
