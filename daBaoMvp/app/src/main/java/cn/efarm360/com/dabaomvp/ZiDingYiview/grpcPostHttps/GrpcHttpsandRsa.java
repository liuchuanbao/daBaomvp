package cn.efarm360.com.dabaomvp.ZiDingYiview.grpcPostHttps;



import android.util.Log;

import javax.net.ssl.SSLSocketFactory;

import cn.efarm360.com.dabaomvp.utils.SSLUtil;

/**
 * Created by pubinfo on 2017/3/14.
 */

public class GrpcHttpsandRsa {
//    private ManagedChannel mChannel;
//公钥
private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXq6N83mz+K5Jrvp6va7n5BvD466Sxy1kweUbe0O/Nh0wmfTXX68Sz4dUTCIjIiruIan6y5NJZtgwSetamRG5Oc2X2n2oaIVir2m7ciqGw0FGwipp1iH4TX7l3N4gQmgsNl9j76fvhq049zf8e4+s3anXsAvgY6nvFOFRRINHY+wIDAQAB";
    //CA
    private static final String CA = "-----BEGIN CERTIFICATE-----\n" +
            "MIICtzCCAiCgAwIBAgIJANRXig95YtlWMA0GCSqGSIb3DQEBCwUAMHIxCzAJBgNV\n" +
            "BAYTAkNOMREwDwYDVQQIDAhaaGVqaWFuZzERMA8GA1UEBwwISGFuZ3pob3UxGDAW\n" +
            "BgNVBAoMD1poZWppYW5nVGVsZWNvbTEQMA4GA1UECwwHUHViaW5mbzERMA8GA1UE\n" +
            "AwwITXlSb290Q0EwIBcNMTYxMjA3MTA0OTMxWhgPMjExNjEyMDcxMDQ5MzFaMHIx\n" +
            "CzAJBgNVBAYTAkNOMREwDwYDVQQIDAhaaGVqaWFuZzERMA8GA1UEBwwISGFuZ3po\n" +
            "b3UxGDAWBgNVBAoMD1poZWppYW5nVGVsZWNvbTEQMA4GA1UECwwHUHViaW5mbzER\n" +
            "MA8GA1UEAwwITXlSb290Q0EwgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBALS5\n" +
            "exrG9TCDq7Wwo9gO2NloFYKPq+KNDfIqgrc1sG9wSOOBcPGZHTuhJi3okunJNUMp\n" +
            "gfAyuD4V5o5HaL1IGmHT/GU97oiSqGjOXqsa4l0zty22UoUEap+a0TasRbl+y5Vc\n" +
            "1FljtvMaUlTPZJEMWRnupEWDnqXWyXxkKmGv2PpfAgMBAAGjUzBRMB0GA1UdDgQW\n" +
            "BBTFHhhmO/PZttHlESUpNyXM8wUEizAfBgNVHSMEGDAWgBTFHhhmO/PZttHlESUp\n" +
            "NyXM8wUEizAPBgNVHRMBAf8EBTADAQH/MA0GCSqGSIb3DQEBCwUAA4GBAAZZwu8K\n" +
            "mw08v7QvlpavcUIQ+m7bkzt7RF/qtCQZFZDJ4OFCtmssLgtFWmjE2GOyGHPrssSz\n" +
            "IqQsPVByA4ukskVfUSVPrSmG3jrVZvbnzVmyLfXUNWNU5q4ciWhQ8w94DNW6c0wS\n" +
            "pyYf9yAvDCZ0F5uglEPwm/0Xe0+DDCPqIx+4\n" +
            "-----END CERTIFICATE-----";

//}
/**
 * grpc支持http的
 */
    //        mChannel = ManagedChannelBuilder.forAddress("114.215.249.192", 8088).usePlaintext(true).build();

    //使用SSL
//    SSLSocketFactory sslFactory = null;
//    try {
//        sslFactory = SSLUtil.getSSLSocketFactory(new Buffer()
//                .writeUtf8(CA)
//                .inputStream());
//    } catch (Exception e) {
//        e.printStackTrace();
//        return;
//    }
    /**
     * grpc支持https的
     */
//    mChannel = OkHttpChannelBuilder.forAddress(HOST, PORT)
////                .overrideAuthority("scanpig.efarm360.com")  //@ExperimentalApi, 仅用于测试机主机名验证!
//            .sslSocketFactory(sslFactory)
//             .build();

    /**
     * 解密
     */
//    private void testfile(byte[] b1) {
////    public void testfile() {
//        try {
//            long startTime = System.currentTimeMillis();   //获取开始时间
//
//            //获取资源文件里的数据  这个是可以运行的
////            byte[] b1 = getBytes();
                // TODo 通过这个进行解密
//            byte[] decodedData = RSAUtils.decryptByPublicKey(b1, publicKey);
//            //TOdo 公钥解密失败
//            Log.e(TAG, "testfile: 解密后长度" + decodedData.length);
//            //imei ch
//            if(decodedData.length<40960){
//                buildDialog("1003");
//                return;
//            }
}
