34
https://raw.githubusercontent.com/1127140426/tensquare/master/tensquare_spit/src/main/java/com/tensquare/spit/pojo/Spit.java
package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李聪
 * @date 2020/2/17 14:34
 */
public class Spit implements Serializable {
    @Id
    private String _id;
    private String content;
    private Date publishtime;
    private String userid;
    String nickname;
    private Integer visits;
    private Integer thumbup;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentid;


    public Spit() {
    }

    public Spit(String _id, String content, Date publishtime, String userid, String nickname, Integer visits, Integer thumbup, Integer share, Integer comment, String state, String parentid) {
        this._id = _id;
        this.content = content;
        this.publishtime = publishtime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.share = share;
        this.comment = comment;
        this.state = state;
        this.parentid = parentid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}