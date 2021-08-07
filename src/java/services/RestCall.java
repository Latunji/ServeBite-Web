/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import static jdk.nashorn.internal.objects.NativeRegExp.source;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import utilities.Utility;

/**
 *
 * @author softmac
 */
public class RestCall {
    final String secretKey = "EXMHGiAKT5Txa9vVUaBRntBjqnWSayuO/kag6TpamTO8muvHB0C4umWwBgC55UdnbkUz2BbVO2KwEKsdZy+SFw==";
    Utility util = new Utility();
  // String ip = "http://localhost:8090/Zoty/RestfulApi/";
    String ip = "http://localhost:8080/serveBiteApi/";
    
    public String executeRequest(String endPoint, JSONObject js) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip  + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String dataToSend = js.toString();
            System.out.println("dataToSend: " + dataToSend);
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes("utf-8");
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("dataReceived: " + myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public String executeGet(String endPoint) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("datareceived: " + myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String executePlainRequest(String endPoint, JSONObject js) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setDoOutput(true);
            String dataToSend = js.toString();
            System.out.println("dataToSend: " + dataToSend);
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes("utf-8");
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("dataReceived: " + myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public String executePlainGet(String endPoint) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setRequestProperty ("Header", "Header");
            connection.setDoOutput(true);
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            String res = myResponse.toString();
            System.out.println("datareceived: " + res);
            return res;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public String executeRequestJSONArray(String endPoint, JSONArray jss) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip  + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setRequestProperty ("Header", "Header");
            connection.setDoOutput(true);
            String dataToSend = jss.toString();
            System.out.println("dataToSend: " + dataToSend);
            String encAES = util.encryptToAES(dataToSend, secretKey);
            JSONObject requestObj = new JSONObject();
            requestObj.put("request", encAES);
            dataToSend = requestObj.toString();
            System.out.println("dataToSendEncrypted: " + dataToSend);
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes("utf-8");
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("dataReceived: " + myResponse.toString());
            //String res = util.decryptFromAES(myResponse.toString(), secretKey);
            String result = myResponse.toString();
            //System.out.println("dataReceivedDecrypted: " + res);
            return result;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public String executeRequestJSONArrayDecrypted(String endPoint, JSONArray jss) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip  + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setRequestProperty ("Header", "Header");
            connection.setDoOutput(true);
            String dataToSend = jss.toString();
            System.out.println("dataToSend: " + dataToSend);
            String encAES = util.encryptToAES(dataToSend, secretKey);
            JSONObject requestObj = new JSONObject();
            requestObj.put("request", encAES);
            dataToSend = requestObj.toString();
            System.out.println("dataToSendEncrypted: " + dataToSend);
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes("utf-8");
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("dataReceived: " + myResponse.toString());
            String res = util.decryptFromAES(myResponse.toString(), secretKey);
            System.out.println("dataReceivedDecrypted: " + res);
            return res;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public String executeRequestJSONObject(String endPoint, JSONObject js) throws JSONException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(ip  + endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setRequestProperty ("Header", "Header");
            connection.setDoOutput(true);
            String dataToSend = js.toString();
            System.out.println("dataToSend: " + dataToSend);
            String encAES = util.encryptToAES(dataToSend, secretKey);
            JSONObject requestObj = new JSONObject();
            requestObj.put("request", encAES);
            dataToSend = requestObj.toString();
            System.out.println("dataToSendEncrypted: " + dataToSend);
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes("utf-8");
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("dataReceived: " + myResponse.toString());
            //String res = util.decryptFromAES(myResponse.toString(), secretKey);
            String result = myResponse.toString();
            //System.out.println("dataReceivedDecrypted: " + res);
            return result;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
//    public void whenSendMultipartRequestUsingHttpClient_thenCorrect() 
//  throws IOException {
//        HttpURLConnection connection = null;
//            URL url = new URL(ip  + "bulkairtimeupload");
//            connection = (HttpURLConnection) url.openConnection();
////    CloseableHttpClient client = HttpClients.createDefault();
////    HttpPost httpPost = new HttpPost("http://www.example.com");
//
//    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//    builder.addTextBody("username", "John");
//    builder.addTextBody("password", "pass");
//    builder.addBinaryBody("file", new File("file.text)",
//      ContentType.APPLICATION_OCTET_STREAM, "file.ext");
//
//    HttpEntity multipart = builder.build();
//    httpPost.setEntity(multipart);
//
//    CloseableHttpResponse response = client.execute(httpPost);
//    client.close();
//}
    
public String multipost(String endPoint, HttpEntity reqEntity) throws JSONException {
//private static String multipost(String urlString, HttpEntity reqEntity) {
    try {
        URL url = new URL(ip  + endPoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        //conn.setRequestProperty("Connection", "Keep-Alive");
        conn.addRequestProperty("Content-length", reqEntity.getContentLength()+"");
        conn.addRequestProperty(reqEntity.getContentType().getName(), reqEntity.getContentType().getValue());

        OutputStream os = conn.getOutputStream();
        reqEntity.writeTo(conn.getOutputStream());
        os.close();
        conn.connect();

        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(conn.getInputStream());
        }

    } catch (Exception e) {
        
    }
    return null;        
}

private static String readStream(InputStream in) {
    BufferedReader reader = null;
    StringBuilder builder = new StringBuilder();
    try {
        reader = new BufferedReader(new InputStreamReader(in));
        String line = "";
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return builder.toString();
}

public String executeMultipartUpload(String targetURL, String methodType, MultipartFile file, String companyCode, String uploader) {
        HttpURLConnection connection = null;
        try {
            
            
            String charset = "UTF-8";
            File b = new File(file.toString());
            String path = b.getAbsolutePath();
            System.out.println("File path "+ path);
            File binaryFile = new File( file.getOriginalFilename() );
            String boundary = "------------------------" + Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
            String CRLF = "\r\n"; // Line separator required by multipart/form-data.
            
            
            
            URL url = new URL(targetURL);
            //Secure secure = new Secure();
            //String header = secure.GetSha512("StudyisfunForChildrenPaymentIsImportant");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(methodType);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty ("Authorization", "Authorization");
            connection.setRequestProperty ("Header", "Header");
            connection.setDoOutput(true);
            
            
            
            OutputStream output = connection.getOutputStream();
            PrintWriter writer  = new PrintWriter(new OutputStreamWriter(output, charset), true);
            
            
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
            writer.append("Content-Type: application/octet-stream").append(CRLF);// + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
            writer.append(CRLF).flush();

            // File data
            System.out.println("output1 "+ "hello1");
            Files.copy(binaryFile.toPath(), output);
//            FileUtils.copyFile((File) file, output);
//            File convFile = new File( file.getOriginalFilename() );
//            FileOutputStream fos = new FileOutputStream(convFile );
//            fos.write( file.getBytes() );
//            Files.copy(file, output);
//            file.transferTo(output);
            System.out.println("output "+ "hello");
            output.flush(); 
            
            writer.append(CRLF).append("--" + boundary + "--").flush();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
            System.out.println("restCall datatosend " + my_response);
            System.out.println("restCall "+ myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            
        }
    }


//    private static String multipost(String urlString, MultipartEntity reqEntity) {
//    try {
//        URL url = new URL(urlString);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setReadTimeout(10000);
//        conn.setConnectTimeout(15000);
//        conn.setRequestMethod("POST");
//        conn.setUseCaches(false);
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//
//        conn.setRequestProperty("Connection", "Keep-Alive");
//        conn.addRequestProperty("Content-length", reqEntity.getContentLength()+"");
//        conn.addRequestProperty(reqEntity.getContentType().getName(), reqEntity.getContentType().getValue());
//
//        OutputStream os = conn.getOutputStream();
//        reqEntity.writeTo(conn.getOutputStream());
//        os.close();
//        conn.connect();
//
//        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//            return readStream(conn.getInputStream());
//        }
//
//    } catch (Exception e) {
//        Log.e(TAG, "multipart post error " + e + "(" + urlString + ")");
//    }
//    return null;        
//}
//
//private static String readStream(InputStream in) {
//    BufferedReader reader = null;
//    StringBuilder builder = new StringBuilder();
//    try {
//        reader = new BufferedReader(new InputStreamReader(in));
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            builder.append(line);
//        }
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (reader != null) {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    return builder.toString();
//} 




}
