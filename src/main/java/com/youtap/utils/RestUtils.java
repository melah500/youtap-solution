package com.youtap.utils;

import com.youtap.constants.AppConstants;
import com.youtap.dto.Response;
import com.youtap.dto.ResponseMetaData;
import com.youtap.dto.ServiceResponse;
import com.youtap.enums.ResponseCode;
import com.youtap.exception.ResourceNotFoundException;
import com.youtap.exception.YouTapServiceException;

public class RestUtils {

    public static <T> ResponseMetaData getMetaData(ServiceResponse<T> serviceResponse) {
        ResponseMetaData responseMetaData = new ResponseMetaData();
        responseMetaData.setMessage(serviceResponse.getNarrative());
        responseMetaData.setCode(serviceResponse.getResponseCode().getCode());
        return responseMetaData;
    }

    public static Response<Void> getApiResponse(YouTapServiceException youTapServiceException) {
        ResponseMetaData responseMetaData = new ResponseMetaData();
        responseMetaData.setMessage(youTapServiceException.getMessage());
        responseMetaData.setCode(youTapServiceException.getResponseCode());
        return new Response<>(responseMetaData);
    }

    public static Response<Void> getApiResponse() {
        ResponseMetaData responseMetaData = new ResponseMetaData();
        responseMetaData.setMessage(AppConstants.GENERIC_FAILURE);
        responseMetaData.setCode(ResponseCode.INTERNAL_ERROR.getCode());
        return new Response<>(responseMetaData);
    }

    public static Response<Void> getNotFoundApiResponse(ResourceNotFoundException exception) {
        ResponseMetaData responseMetaData = new ResponseMetaData();
        responseMetaData.setMessage(AppConstants.USER_NOT_FOUND);
        responseMetaData.setCode(exception.getResponseCode());
        return new Response<>(responseMetaData);
    }

    public static Response<Void> getApiResponse(ResponseCode responseCode, String message) {
        ResponseMetaData responseMetaData = new ResponseMetaData();
        responseMetaData.setMessage(message);
        responseMetaData.setCode(responseCode.getCode());
        return new Response<>(responseMetaData);
    }
}
