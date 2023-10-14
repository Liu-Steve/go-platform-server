package com.goplatform.server.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对应用户表，用于记录用户信息
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "GO_USER")
public class UserEntity {
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL", unique = true)
    private String email;

    /**
     * 用户状态
     * 0：空闲
     * 1：房间创建者
     * 2：房间进入者
     */
    @Column(name = "STATUS")
    private int status;

    @Column(name = "CREATED_DATE")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdDate;

    @Column(name = "UPDATED_DATE")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedDate;

    /**
     * 用户的角色列表
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<RoleEntity> roles = new ArrayList<>();
}
