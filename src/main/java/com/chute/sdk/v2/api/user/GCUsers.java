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
package com.chute.sdk.v2.api.user;

import android.content.Context;

import com.chute.sdk.v2.model.UserModel;
import com.chute.sdk.v2.model.requests.ResponseModel;
import com.dg.libs.rest.HttpRequest;
import com.dg.libs.rest.callbacks.HttpCallback;

/**
 * Provides all actions connected to user management operations within Chute.
 * 
 */
public class GCUsers {
	@SuppressWarnings("unused")
	private static final String TAG = GCUsers.class.getSimpleName();

	/**
	 * A private no-args default constructor.
	 */
	private GCUsers() {
		super();
	}

	/**
	 * Gets the user info for the provided id. Returns the full user info as a
	 * response.
	 * 
	 * @param context
	 *            - The application context
	 * @param user
	 *            - The {@link UserModel} containing the information to be
	 *            retrieved
	 * @param callback
	 *            - Instance of {@link HttpCallback} interface. If successful,
	 *            the callback returns {@link ResponseModel<UserModel>}
	 * @return - {@link UsersGetRequest}
	 */
	public static HttpRequest get(final Context context, final UserModel user,
			final HttpCallback<ResponseModel<UserModel>> callback) {
		return new UsersGetRequest(context, user, callback);
	}

	/**
	 * Gets the user model for the currently authenticated user by his
	 * Authorization token. Returns the full user info as a response.
	 * 
	 * @param context
	 *            - The application context
	 * @param callback
	 *            - Instance of {@link HttpCallback} interface. If successful,
	 *            the callback returns {@link ResponseModel<UserModel>}
	 * @return - {@link UsersCurrentRequest}
	 */
	public static HttpRequest me(final Context context,
			final HttpCallback<ResponseModel<UserModel>> callback) {
		return new UsersCurrentRequest(context, callback);
	}

}