package com.app.dialy_cafe.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.util.Date;

@Entity
@Setter
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "phone", nullable = false, unique = true, length = 10)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "address", length = 512)
    private String address;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "version")
    @Version
    private int version;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

    public Users(Long id, String name, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', phone='" + phone + "', email='" + email +
                "', address='" + address + "' createdAt=" + createdDate + "}";
    }
}
