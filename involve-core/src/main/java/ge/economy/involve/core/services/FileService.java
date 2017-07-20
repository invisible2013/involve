package ge.economy.involve.core.services;

/**
 * Created by nl on 9/25/2016.
 */

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {


    //"D:\\upload"
    private String rootDir = "Users/nino/Desktop/upload";

    private final Logger logger = Logger.getLogger(FileService.class);

    public String getFileFullPath(String name) {
        return rootDir + "/" + name;
    }

    private void base64ToFile(String fileName, String base64File) throws FileNotFoundException, IOException {

        if (base64File == null || base64File.isEmpty()) {
            logger.error("unable to save documnet, base64File data is empty");
        }
        String fileType = base64File.split(",")[0].split(";")[0].split(":")[1].split("/")[1];
        String ext = base64File.substring(5, base64File.indexOf(";"));
        if (ext.contains("msword")) {
            fileType = ".doc";
        }

        String imageDataBytes = base64File.substring(base64File.indexOf(",") + 1);
        byte[] data = Base64.getDecoder().decode(imageDataBytes);
        try (OutputStream stream = new FileOutputStream(fileName + "." + fileType)) {
            stream.write(data);
        }

    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response, String name, String type)
            throws ServletException {

        String filePath = rootDir + "/" + name;

        try {
            switch (type) {
                case "draw":
                    drawFile(request, response, filePath);
                    break;
                case "download":
                    downloadFile(request, response, filePath, name);
                    break;
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            }
        }
    }

    private void drawFile(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        try {

            logger.info("processing image , path " + path);

            InputStream inputStream = new FileInputStream(new File(path));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (IOException ex) {
            logger.error("Unable to make responce for path " + path, ex);
            throw ex;
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String path, String name) throws IOException {

        try {
            logger.info("processing video , path " + path);
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Disposition", "attachment; filename=" + name);
            byte[] content = new byte[1024];
            OutputStream os;
            try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(path)))) {
                os = response.getOutputStream();
                while (is.read(content) != -1) {
                    os.write(content);
                }
            }
            os.close();
        } catch (IOException ex) {
            logger.error("Unable to make responce for path " + path, ex);
            throw ex;
        }
    }

    private void processFile(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        try {

            logger.info("processing image , path " + path);

            response.addHeader("Content-Type", "text/plain");

            InputStream inputStream = new FileInputStream(new File(path));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (IOException ex) {
            logger.error("Unable to make responce for path " + path, ex);
            throw ex;
        }
    }

    public void processVideo(HttpServletRequest request, HttpServletResponse response, String path, String name) throws IOException {

        try {

            logger.info("processing video , path " + path);

            response.setContentType("video/mpg");
            response.setHeader("Accept-Ranges", "bytes");
            response.setHeader("Content-Disposition", "attachment; filename=" + name);
            byte[] content = new byte[1024];
            OutputStream os;
            try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(new File(path)))) {
                os = response.getOutputStream();
                while (is.read(content) != -1) {
                    os.write(content);
                }
            }
            os.close();
        } catch (IOException ex) {
            logger.error("Unable to make responce for path " + path, ex);
            throw ex;
        }
    }

    public void processImage(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        try {

            path = rootDir + "/" + path;

            logger.info("processing file , path " + path);

            response.setContentType("image/x-png");

            File f = new File(path);
            BufferedImage bi = ImageIO.read(f);
            try (OutputStream out = response.getOutputStream()) {
                ImageIO.write(bi, "png", out);
            }
        } catch (IOException ex) {
            logger.error("Unable to make responce for path " + path, ex);
            throw ex;
        }
    }

    public String saveFile(MultipartFile file, String namePart) {

        String[] fileParts = file.getOriginalFilename().split("\\.");
        String fileExtension = fileParts.length > 1 ? fileParts[fileParts.length - 1] : "";
        String fileName = "" + namePart + "_" + UUID.randomUUID() + (fileExtension.length() > 0 ? ("." + fileExtension) : "");
        File f = new File(rootDir + "/" + fileName);
        try {
            file.transferTo(f);
        } catch (Exception ex) {
            logger.error("Unable to save file with name " + fileName, ex);
            return null;
        }
        return fileName;
    }

    public void writeFile(String identifier, String content) throws IOException, FileNotFoundException {
        if (content != null) {
            File directory = new File(rootDir);
            if (!directory.exists()) {
                directory.mkdir();
            }

            String fileName = rootDir + "/" + identifier;
            base64ToFile(fileName, content);
        }
    }

    public byte[] readFile(String identifier) throws IOException {
        return fileTOBytesArray(getFilePath(identifier), false);
    }

    private String getFilePath(String identifier) {
        File f = new File(rootDir);
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File _f : files) {
                if (FilenameUtils.removeExtension(_f.getName()).equals(identifier)) {
                    return _f.getPath();
                }
            }
        }
        return null;
    }

    public void deleteFile(String identifier) {
        File f = new File(rootDir + "/" + identifier);
        if(f.exists()){
            try {
                f.delete();
            }catch (Exception ex){

            }
        }
    }

    private byte[] fileTOBytesArray(String fileName, boolean base64) throws FileNotFoundException, IOException {
        if (fileName != null) {
            File f = new File(fileName);
            if (f.exists()) {
                byte[] fileAsBytes = IOUtils.toByteArray(new FileInputStream(new File(fileName)));
                if (base64) {
                    fileAsBytes = Base64.getEncoder().encode(fileAsBytes);
                }
                return fileAsBytes;
            }
        }
        return new byte[0];
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

}

