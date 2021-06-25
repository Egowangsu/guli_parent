package com.wyx.oss.service.Imp;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wyx.oss.service.OssService;
import com.wyx.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        OSS ossClient= null;
        try{
            //yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = ConstantPropertiesUtils.END_POINT;
            String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
            String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
            // 创建OSSClient实例。
             ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();

            //获取上传文件名
            String fileName=file.getOriginalFilename();

            //解决上传文件名相同的情况
            String uuid=UUID.randomUUID().toString().replaceAll("-","" );
            fileName = uuid + fileName;

            //把文件按照日期进行分类  例如2019/2/5/02.jpg  ,oss会创建文件夹2019/2/5
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd/");
            //与文件名进行拼接
            fileName = datePath + fileName;

            // 第一个参数为bucket的名称，第二个参数为上传的文件名，第三个参数为文件流
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //若上传成功将上传文件路径返回
            //需要把上传到阿里云oss的路劲自己手动拼接
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
