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

import android.content.Context;

import com.araneaapps.android.libs.logger.ALog;
import com.chute.sdk.v2.utils.RestConstants;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.BaseRestClient.RequestMethod;
import com.dg.libs.rest.parsers.HttpResponseParser;
import com.dg.libs.rest.requests.ParameterHttpRequestImpl;

public class AuthenticationToken<T> extends ParameterHttpRequestImpl<T> {
	@SuppressWarnings("unused")
	private static final String TAG = AuthenticationToken.class.getSimpleName();

	public AuthenticationToken(Context context, AuthConstants authConstants,
			String code, HttpResponseParser<T> parser, HttpCallback<T> callback) {
		super(context, RequestMethod.POST, parser, callback);
		ALog.d("code " + code + " client_id " + authConstants.clientId);
		
		addParam("code", code);
		addParam("scope", AuthConstants.PERMISSIONS_SCOPE);
		addParam("client_id", authConstants.clientId);
		addParam("client_secret", authConstants.clientSecret);
		addParam("grant_type", "authorization_code");
		addParam("redirect_uri",AuthConstants.CALLBACK_URL);
	}

	@Override
	protected String getUrl() {
		return RestConstants.URL_AUTHENTICATION_TOKEN;
	}
}
