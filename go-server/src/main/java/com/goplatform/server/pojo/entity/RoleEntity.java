package com.goplatform.server.pojo.entity;

import com.goplatform.server.pojo.converter.StringListConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色表，用于鉴权
 */
@Data
@Entity
@Table(name = "GO_ROLE")
public class RoleEntity {
    @Id
    @Column(name = "NAME")
    private String name;

    @Convert(converter = StringListConverter.class)
    @Column(name = "AUTHORITIES")
    private List<String> authorities = new ArrayList<>();
}
