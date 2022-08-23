package com.fbe.be.dto;

@Data
@Entity
@Table(name = "client")
public class BaseEntity implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("f_name")
    private String name;

    @JsonProperty("m_email")
    private String email;

}