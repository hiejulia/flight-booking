package com.project.flightbooking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SolrDocument(solrCoreName = "mycore")
public class Tweet implements Serializable {

    private static final long serialVersionUID = -4186949579773122733L;
    @Id
    @Indexed(name = "id", type = "long")
    @Field("id")
    private long id;
    @Field("idStr")
    private String idStr;
    @Indexed(name = "text", type = "string")
    @Field("text")
    private String text;
    @Field("creatAt")
    private Date createdAt;
    @Field("fromUser")
    private String fromUser;
    private String profileImageUrl;
    private Long toUserId;
    private Long inReplyToStatusId;
    private Long inReplyToUserId;
    private String inReplyToScreenName;
    private long fromUserId;
    private String languageCode;
    private String source;
    private Integer retweetCount;
    private boolean retweeted;
    private Tweet retweetedStatus;
    private boolean favorited;
    private Integer favoriteCount;
    private TwitterProfile user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tweet tweet = (Tweet) o;

        if (fromUserId != tweet.fromUserId) {
            return false;
        }
        if (id != tweet.id) {
            return false;
        }
        if (idStr != null ? !idStr.equals(tweet.idStr) : tweet.idStr != null) {
            return false;
        }
        if (retweeted != tweet.retweeted) {
            return false;
        }
        if (createdAt != null ? !createdAt.equals(tweet.createdAt) : tweet.createdAt != null) {
            return false;
        }
        if (fromUser != null ? !fromUser.equals(tweet.fromUser) : tweet.fromUser != null) {
            return false;
        }
        if (inReplyToScreenName != null ? !inReplyToScreenName.equals(tweet.inReplyToScreenName) : tweet.inReplyToScreenName != null) {
            return false;
        }
        if (inReplyToStatusId != null ? !inReplyToStatusId.equals(tweet.inReplyToStatusId) : tweet.inReplyToStatusId != null) {
            return false;
        }
        if (inReplyToUserId != null ? !inReplyToUserId.equals(tweet.inReplyToUserId) : tweet.inReplyToUserId != null) {
            return false;
        }
        if (languageCode != null ? !languageCode.equals(tweet.languageCode) : tweet.languageCode != null) {
            return false;
        }
        if (profileImageUrl != null ? !profileImageUrl.equals(tweet.profileImageUrl) : tweet.profileImageUrl != null) {
            return false;
        }
        if (retweetCount != null ? !retweetCount.equals(tweet.retweetCount) : tweet.retweetCount != null) {
            return false;
        }
        if (retweetedStatus != null ? !retweetedStatus.equals(tweet.retweetedStatus) : tweet.retweetedStatus != null) {
            return false;
        }
        if (source != null ? !source.equals(tweet.source) : tweet.source != null) {
            return false;
        }
        if (text != null ? !text.equals(tweet.text) : tweet.text != null) {
            return false;
        }
        if (toUserId != null ? !toUserId.equals(tweet.toUserId) : tweet.toUserId != null) {
            return false;
        }
        if (user != null ? !user.equals(tweet.user) : tweet.user != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;//(int) (id ^ (id >>> 32));
        result = 31 * result + (idStr != null ? idStr.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (profileImageUrl != null ? profileImageUrl.hashCode() : 0);
        result = 31 * result + (toUserId != null ? toUserId.hashCode() : 0);
        result = 31 * result + (inReplyToStatusId != null ? inReplyToStatusId.hashCode() : 0);
        result = 31 * result + (inReplyToUserId != null ? inReplyToUserId.hashCode() : 0);
        result = 31 * result + (inReplyToScreenName != null ? inReplyToScreenName.hashCode() : 0);
        result = 31 * result + (int) (fromUserId ^ (fromUserId >>> 32));
        result = 31 * result + (languageCode != null ? languageCode.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (retweetCount != null ? retweetCount.hashCode() : 0);
        result = 31 * result + (retweeted ? 1 : 0);
        result = 31 * result + (retweetedStatus != null ? retweetedStatus.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}