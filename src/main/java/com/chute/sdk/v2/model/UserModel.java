// Copyright (c) 2011, Chute Corporation. All rights reserved.
//
// Redistribution and use in source and binary forms, with or without modification,
// are permitted provided that the following conditions are met:
//
// * Redistributions of source code must retain the above copyright notice, this
// list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright notice,
// this list of conditions and the following disclaimer in the documentation
// and/or other materials provided with the distribution.
// * Neither the name of the Chute Corporation nor the names
// of its contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
// LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
// OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGE.
//
package com.chute.sdk.v2.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The {@link UserModel} class contains user features such as: links, name,
 * username and avatar URL.
 * 
 */
public class UserModel implements Parcelable {

  /**
   * Unique identifier.
   */
  @JsonProperty("id")
  private String id;

  /**
   * User links.
   */
  @JsonProperty("links")
  private LinkModel links;

  /**
   * Name of the user.
   */
  @JsonProperty("name")
  private String name;

  /**
   * Username of the user.
   */
  @JsonProperty("username")
  private String username;

  /**
   * User avatar URL.
   */
  @JsonProperty("avatar")
  private String avatar;

  /**
   * Time and date of creation.
   */
  @JsonProperty("created_at")
  private String created_at;

  /**
   * Time and date of update.
   */
  @JsonProperty("updated_at")
  private String updated_at;

  /**
   * User profile.
   */
  @JsonProperty("profile")
  private ProfileModel profile;

  /**
   * User e-mail.
   */
  @JsonProperty("email")
  private String email;

  /**
   * OAuth token.
   */
  @JsonProperty("oauth_token")
  private String oauthToken;

  /**
   * User status. It can be pending or verified.
   */
  private String status;

  /**
   * Default non-args constructor.
   */
  public UserModel() {
  }

  /**
   * Getters and setters.
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LinkModel getLinks() {
    return links;
  }

  public void setLinks(LinkModel links) {
    this.links = links;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  public ProfileModel getProfile() {
    return profile;
  }

  public void setProfile(ProfileModel profile) {
    this.profile = profile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getOauthToken() {
    return oauthToken;
  }

  public void setOauthToken(String oauthToken) {
    this.oauthToken = oauthToken;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public UserModel(Parcel in) {
    this();
    id = in.readString();
    links = in.readParcelable(LinkModel.class.getClassLoader());
    name = in.readString();
    username = in.readString();
    avatar = in.readString();
    created_at = in.readString();
    updated_at = in.readString();
    profile = in.readParcelable(ProfileModel.class.getClassLoader());
    email = in.readString();
    oauthToken = in.readString();
    status = in.readString();
  }

  /*
   * (non-Javadoc)
   * @see android.os.Parcelable#describeContents()
   */
  @Override
  public int describeContents() {
    return 0;
  }

  /*
   * (non-Javadoc)
   * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
   */
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(id);
    dest.writeParcelable(links, flags);
    dest.writeString(name);
    dest.writeString(username);
    dest.writeString(avatar);
    dest.writeString(created_at);
    dest.writeString(updated_at);
    dest.writeParcelable(profile, flags);
    dest.writeString(email);
    dest.writeString(oauthToken);
    dest.writeString(status);
  }

  public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {

    @Override
    public UserModel createFromParcel(Parcel in) {
      return new UserModel(in);
    }

    @Override
    public UserModel[] newArray(int size) {
      return new UserModel[size];
    }

  };

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("UserModel [id=");
    builder.append(id);
    builder.append(", links=");
    builder.append(links);
    builder.append(", name=");
    builder.append(name);
    builder.append(", username=");
    builder.append(username);
    builder.append(", avatar=");
    builder.append(avatar);
    builder.append(", created_at=");
    builder.append(created_at);
    builder.append(", updated_at=");
    builder.append(updated_at);
    builder.append(", profile=");
    builder.append(profile);
    builder.append(", email=");
    builder.append(email);
    builder.append(", oauthToken=");
    builder.append(oauthToken);
    builder.append(", status=");
    builder.append(status);
    builder.append("]");
    return builder.toString();
  }

}