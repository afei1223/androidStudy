package com.example.androidstudy.HttpStudy;

public interface JCUploadCallback {
    /**
     * 文件状态更新通知
     *
     * @param percentage 文件消息对象，通过该对象可以获得当前文件传输的属性及状态
     */
    void onFileUpdate(float percentage);

    /**
     * 文件传输回调
     *
     * @param response 文件传输完成后来自服务器的json消息
     */
    void onFileResult(String response);

    /**
     * 文件传输失败回调
     *
     * @param id 进度信息的 id
     * @param e 错误信息
     * */
    void onFileError(long id, Exception e);
}
