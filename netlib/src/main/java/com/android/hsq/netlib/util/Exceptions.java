package com.android.hsq.netlib.util;

import com.android.hsq.netlib.request.DataRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by hsq on 2016/6/29.
 */
public class Exceptions {

    /**
     * 当读取网路数据错误时抛出的异常
     */
   public static class HttpDataSourceException extends IOException {

        public static final int TYPE_OPEN = 1;
        public static final int TYPE_READ = 2;
        public static final int TYPE_CLOSE = 3;
        public static final int TYPE_CANCELED = 4;

        public final int type;

        /**
         * 当前连接的数据
         */
        public final DataRequest dataSpec;

        public HttpDataSourceException(DataRequest dataSpec, int type) {
            super();
            this.dataSpec = dataSpec;
            this.type = type;
        }

        public HttpDataSourceException(String message, DataRequest dataSpec, int type) {
            super(message);
            this.dataSpec = dataSpec;
            this.type = type;
        }

        public HttpDataSourceException(IOException cause, DataRequest dataSpec, int type) {
            super(cause);
            this.dataSpec = dataSpec;
            this.type = type;
        }

        public HttpDataSourceException(String message, IOException cause, DataRequest dataSpec, int type) {
            super(message, cause);
            this.dataSpec = dataSpec;
            this.type = type;
        }

    }

    /**
     * content type 无效时抛出的异常
     */
    public final static class InvalidContentTypeException extends HttpDataSourceException {

        public final String contentType;

        public InvalidContentTypeException(String contentType, DataRequest dataSpec) {
            super("无效 content type: " + contentType, dataSpec, TYPE_OPEN);
            this.contentType = contentType;
        }

    }

    /**
     * 当打开的连接响应结果码不在 2xx 的返回 抛出的异常.
     */
    public final static class InvalidResponseCodeException extends HttpDataSourceException {

        /**
         * 不在 2xx 范围的响应码.
         */
        public final int responseCode;

        /**
         * 响应头信息
         */
        public final Map<String, List<String>> headerFields;

        public InvalidResponseCodeException(int responseCode, Map<String, List<String>> headerFields,
                                            DataRequest dataSpec) {
            super("Response code: " + responseCode, dataSpec, TYPE_OPEN);
            this.responseCode = responseCode;
            this.headerFields = headerFields;
        }

    }
}
