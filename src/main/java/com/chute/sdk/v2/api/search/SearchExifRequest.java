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
package com.chute.sdk.v2.api.search;

import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;

import android.content.Context;
import android.text.TextUtils;

import com.chute.sdk.v2.api.parsers.ListResponseParser;
import com.chute.sdk.v2.model.AlbumModel;
import com.chute.sdk.v2.model.AssetModel;
import com.chute.sdk.v2.model.response.ListResponseModel;
import com.chute.sdk.v2.utils.RestConstants;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.BaseRestClient.RequestMethod;
import com.dg.libs.rest.requests.ParameterHttpRequestImpl;

public class SearchExifRequest extends
    ParameterHttpRequestImpl<ListResponseModel<AssetModel>> {

  public static final String TAG = SearchExifRequest.class.getSimpleName();
  private AlbumModel album;
  private HashMap<String, String> exif;

  public SearchExifRequest(Context context, AlbumModel album,
      HashMap<String, String> exif,
      HttpCallback<ListResponseModel<AssetModel>> callback) {
    super(context, RequestMethod.GET, new ListResponseParser<AssetModel>(
        AssetModel.class), callback);
    this.album = album;
    this.exif = exif;
    if (exif == null || exif.isEmpty()) {
      throw new IllegalArgumentException(
          "Need to provide exif information to execute search");
    }

  }

  @Override
  protected void doBeforeRunRequestInBackgroundThread() {
    super.doBeforeRunRequestInBackgroundThread();
    addParams(album, exif);

  }

  private void addParams(AlbumModel album, HashMap<String, String> exif) {
    if (album != null || !TextUtils.isEmpty(album.getId())) {
      addParam("album_id", album.getId());
    }
    JSONArray exifArray = new JSONArray();
    Iterator iterator = exif.keySet().iterator();
    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      exifArray.put(exif.get(key));
    }
    addParam("query[key]", exifArray.toString());
  }

  @Override
  protected String getUrl() {
    return RestConstants.URL_SEARCH_EXIF;
  }
}
