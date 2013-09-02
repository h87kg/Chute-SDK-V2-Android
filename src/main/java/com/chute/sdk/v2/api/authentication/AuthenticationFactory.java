// Copyright (c) 2011, Chute Corporation. All rights reserved.
// 
//  Redistribution and use in source and binary forms, with or without modification, 
//  are permitted provided that the following conditions are met:
// 
//     * Redistributions of source code must retain the above copyright notice, this 
//       list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above copyright notice,
//       this list of conditions and the following disclaimer in the documentation
//       and/or other materials provided with the distribution.
//     * Neither the name of the  Chute Corporation nor the names
//       of its contributors may be used to endorse or promote products derived from
//       this software without specific prior written permission.
// 
//  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
//  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
//  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
//  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
//  BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
//  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
//  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
//  OF THE POSSIBILITY OF SUCH DAMAGE.
// 
package com.chute.sdk.v2.api.authentication;

import android.app.Activity;
import android.content.Intent;

import com.chute.sdk.v2.model.enums.AccountType;
import com.chute.sdk.v2.utils.RestConstants;

public class AuthenticationFactory {

  public static final String EXTRA_ACCOUNT_TYPE = "account_type";

  public static final int AUTHENTICATION_REQUEST_CODE = 123;

  @SuppressWarnings("unused")
  private static final String TAG = AuthenticationFactory.class
      .getSimpleName();
  private AuthConstants authConstants;

  private static AuthenticationFactory instance;

  public static AuthenticationFactory getInstance() {
    if (instance == null) {
      instance = new AuthenticationFactory();
    }
    return instance;
  }

  private AuthenticationFactory() {
  }

  public void setAuthConstants(AuthConstants authConstants) {
    this.authConstants = authConstants;
  }

  public String getAuthenticationURL(AccountType accountType) {
    if (authConstants == null) {
      throw new IllegalArgumentException(
          "If you are using the Authentication activity, you need to pass in the Authentication Constants to start it");
    }
    StringBuilder stringBuilder;
    stringBuilder = new StringBuilder(
        String.format(RestConstants.BASE_AUTH_URL, accountType.getLoginMethod()));
    stringBuilder.append("?");
    stringBuilder.append("scope=" + AuthConstants.PERMISSIONS_SCOPE);
    stringBuilder.append("&");
    stringBuilder.append("response_type=code");
    stringBuilder.append("&");
    stringBuilder.append("client_id=" + authConstants.clientId);
    stringBuilder.append("&");
    stringBuilder.append("redirect_uri=" + AuthConstants.CALLBACK_URL);
    return stringBuilder.toString();
  }

  /**
   * <p>
   * Use {@link #AUTHENTICATION_REQUEST_CODE} in onActivityResult to check the
   * request code.
   * <p>
   * Use {@link Activity#RESULT_OK} as a result code if the authentication was
   * successful.
   * 
   * <pre>
   * <b> For errors use the following constants as a result code: </b>
   * Use {@link GCAuthenticationActivity#CODE_HTTP_EXCEPTION}} - For connection problems.
   * Use {@link GCAuthenticationActivity#CODE_HTTP_ERROR}} - For server issues. See logcat for detailed error.
   * Use {@link GCAuthenticationActivity#CODE_PARSER_EXCEPTION}} - For result parsing errors. See logcat for details.
   * </pre>
   * 
   * @param activity
   * @param accountType
   * @param scope
   * @param redirectUri
   * @param clientId
   * @param clientSecret
   */
  public void startAuthenticationActivity(Activity activity, AccountType accountType) {
    Intent intent = new Intent(activity, AuthenticationActivity.class);
    intent.putExtra(EXTRA_ACCOUNT_TYPE, accountType.ordinal());
    activity.startActivityForResult(intent, AUTHENTICATION_REQUEST_CODE);
  }

  public AuthConstants getAuthConstants() {
    return authConstants;
  }

  public boolean isRedirectUri(String url) {
    return url.startsWith(AuthConstants.CALLBACK_URL);
  }

}
