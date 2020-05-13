1
https://raw.githubusercontent.com/wangxingman/my_project/master/netty_game_app/netty_game_server/src/main/java/com/game/entity/SdkUser.java
package com.game.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author @123
 * @since 2019-11-22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="sdk_user")
@org.hibernate.annotations.Table(appliesTo = "sdk_user",comment = "用户信息表")
public class SdkUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 用户的id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "su_id")
    private Integer sdkUserId;
    /**
     * 用户的密码
     */
    @Column(name = "su_password",nullable = false)
    @NotBlank
    private String sdkUserPassword;
    /**
     * 用户名
     */
    @Column(name = "su_username",nullable = false)
    @NotBlank
    private String sdkUserName;
    /**
     * 用户的手机号
     */
    @Column(name = "su_phone",nullable = false)
    @NotBlank
    private String sdkUserPhone;
    /**
     * '用户性别(1:男,2:女)',
     */
    @Column(name = "su_sex",nullable = false)
    @NotNull
    private Integer sdkUserSex;
    /**
     * 注册时间
     */
    @Column(name = "su_register_time",nullable = false)
    @NotNull
    private Date sdkUserRegisterTime;
    /**
     * 设备id
     */
    @Column(name = "su_openid",nullable = false)
    @NotBlank
    private String sdkUserOpenid;
    /**
     * 用户的ip
     */
    @Column(name = "su_ip",nullable = false)
    @NotBlank
    private String sdkUserIp;
    /**
     * 用户登录时间
     */
    @Column(name = "su_login_time",nullable = false)
    @NotNull
    private Date sdkUserLoginTime;
    /**
     * 用户头像
     */
    @Column(name = "su_image",nullable = false)
    @NotBlank
    private String sdkUserImage;
    /**
     * 用户的渠道id
     */
    @Column(name = "su_channel",nullable = false)
    @NotNull
    private Integer sdkUserChannelid;
    /**
     * 用户地址
     */
    @Column(name = "su_address",nullable = false)
    @NotBlank
    private String sdkUserAddress;
    /**
     * 备注
     */
    @Column(name = "su_remarks",nullable = false)
    @NotBlank
    private String sdkUserRemarks;
    /**
     * 银行名字
     */
    @Column(name = "su_bankName",nullable = false)
    @NotBlank
    private String sdkUserBankName;
    /**
     * 归属银行
     */
    @Column(name = "su_attribution_bank",nullable = false)
    @NotBlank
    private String sdkUserAttributionBank;
    /**
     * 银行账号
     */
    @Column(name = "su_bank_account",nullable = false)
    @NotBlank
    private String sdkUserBankAccount;
    /**
     * 支付宝名字
     */
    @Column(name = "su_ali_name",nullable = false)
    @NotBlank
    private String sdkUserAliName;
    /**
     * 支付宝账号
     */
    @Column(name = "su_ali_account",nullable = false)
    @NotBlank
    private String sdkUserAliAccount;
    /**
     * 个性签名
     */
    @Column(name = "su_sign",nullable = false)
    @NotBlank
    private String sdkUserSign;
    /**
     * 是否拉黑（0否，1是）
     */
    @Column(name = "su_is_black",nullable = false)
    @NotNull
    private Integer sdkUserIsBlack;
    /**
     * 是否冻结（0否，1是）
     */
    @Column(name = "su_is_frozen",nullable = false)
    @NotNull
    private Integer sdkUserIsFrozen;
    /**
     * 用户等级
     */
    @Column(name = "su_level",nullable = false)
    @NotNull
    private Integer sdkUserLevel;

    public SdkUser(String sdkUserPassword, String sdkUserName, String sdkUserPhone, String sdkUserOpenid) {
        this.sdkUserPassword = sdkUserPassword;
        this.sdkUserName = sdkUserName;
        this.sdkUserPhone = sdkUserPhone;
        this.sdkUserOpenid = sdkUserOpenid;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
