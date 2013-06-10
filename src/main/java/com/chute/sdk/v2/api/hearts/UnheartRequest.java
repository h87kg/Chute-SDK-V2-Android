package com.chute.sdk.v2.api.hearts;

import android.content.Context;
import android.text.TextUtils;

import com.chute.sdk.v2.api.parsers.ResponseParser;
import com.chute.sdk.v2.model.AlbumModel;
import com.chute.sdk.v2.model.AssetModel;
import com.chute.sdk.v2.model.HeartModel;
import com.chute.sdk.v2.model.requests.ResponseModel;
import com.chute.sdk.v2.utils.RestConstants;
import com.dg.libs.rest.callbacks.HttpCallback;
import com.dg.libs.rest.client.BaseRestClient.RequestMethod;
import com.dg.libs.rest.requests.ParameterHttpRequestImpl;

public class UnheartRequest extends
		ParameterHttpRequestImpl<ResponseModel<HeartModel>> {

	public static final String TAG = UnheartRequest.class.getSimpleName();
	private AlbumModel album;
	private AssetModel asset;

	public UnheartRequest(Context context, AlbumModel album, AssetModel asset,
			HttpCallback<ResponseModel<HeartModel>> callback) {
		super(context, RequestMethod.DELETE, new ResponseParser<HeartModel>(
				HeartModel.class), callback);
		if (asset == null || TextUtils.isEmpty(asset.getId())) {
			throw new IllegalArgumentException("Need to provide asset ID");
		}
		if (album == null || TextUtils.isEmpty(album.getId())) {
			throw new IllegalArgumentException("Need to provide album ID");
		}
		this.album = album;
		this.asset = asset;
	}

	@Override
	protected String getUrl() {
		return String.format(RestConstants.URL_HEARTS_REMOVE, album.getId(),
				asset.getId());
	}

}