package cn.promptness.blog.support.vo;

import lombok.Data;

/**
 * @author : Lynn
 * @date : 2019-06-02 03:41
 */
@Data
public class UploadVO {

    private Integer success;
    private String message;
    private String url;

    public static UploadVO success(String url) {
        UploadVO uploadVO = new UploadVO();
        uploadVO.setSuccess(1);
        uploadVO.setMessage("上传成功！");
        uploadVO.setUrl(url);
        return uploadVO;
    }

    public static UploadVO fail() {
        UploadVO uploadVO = new UploadVO();
        uploadVO.setSuccess(0);
        uploadVO.setMessage("上传失败！");
        return uploadVO;
    }
}
