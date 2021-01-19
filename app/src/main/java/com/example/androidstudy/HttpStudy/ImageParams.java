package com.example.androidstudy.HttpStudy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImageParams implements ParamString{

    public String getAppKey() {
        return appKey;
    }

    public String getCallId() {
        return callId;
    }

    public String getFileName() {
        return fileName;
    }

    public long getTakeTime() {
        return takeTime;
    }

    public int getFileSize() {
        return fileSize;
    }

    public String getAgentId() {
        return agentId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getFileHash() {
        return fileHash;
    }

    //必填
    private String appKey;
    private String callId;
    private String fileName;
    private long takeTime;
    private int fileSize;

    //非必填
    private String agentId;
    private String resourceId;
    //s3上传类型使用
    private String bucketName;
    private String fileHash;

    public ImageParams(Builder builder) {
        this.appKey = builder.appKey;
        this.callId = builder.callId;
        this.fileName = builder.fileName;
        this.takeTime = builder.takeTime;
        this.fileSize = builder.fileSize;

        this.agentId = builder.agentId;
        this.resourceId = builder.resourceId;
        this.bucketName = builder.bucketName;
        this.fileHash = builder.fileHash;
    }

//    public Builder builder() {
//        return new Builder();
//    }

    public static class Builder{
        private String agentId;
        private String resourceId;
        //s3上传类型使用
        private String bucketName;
        private String fileHash;

        //必填
        private String appKey;
        private String callId;
        private String fileName;
        private long takeTime;
        private int fileSize;

        public Builder(String appKey, String callId, String fileName, long takeTime, int fileSize) {
            this.appKey = appKey;
            this.callId = callId;
            this.fileName = fileName;
            this.takeTime = takeTime;
            this.fileSize = fileSize;
        }

        public Builder setAgentId(String agentId) {
            this.agentId = agentId;
            return this;
        }

        public Builder setResourceId(String resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder setBucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        public Builder setFileHash(String fileHash) {
            this.fileHash = fileHash;
            return this;
        }

        public ImageParams build(){
            return new ImageParams(this);
        }
    }

    @Override
    public HashMap toMap(){
        HashMap map = new HashMap();
        map.put("appKey", appKey);
        map.put("agentId",agentId);
        map.put("bucketName",bucketName);
        map.put("fileHash",fileHash);
        map.put("fileName",fileName);
        map.put("takeTime",takeTime);
        map.put("resourceId",resourceId);
        map.put("callId",callId);
        map.put("fileSize",fileSize);
        return map;
    }

    /**
     * 这里用反射的方法实现获取变量名
     * 或者可以用自己添加的方式String[] a = ["name"]
     * */
    @Override
    public List<String> getNames() {
        Field[] fields = this.getClass().getDeclaredFields();
        List<String> names = new ArrayList<>();
        for(Field field : fields){
            names.add(field.getName());
        }
        return names;
    }

}


