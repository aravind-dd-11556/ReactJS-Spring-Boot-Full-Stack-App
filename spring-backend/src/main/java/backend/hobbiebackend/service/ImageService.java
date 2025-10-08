package backend.hobbiebackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");

    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        if (file == null) return null;

        InputStream in = null;
        try {
            in = file.getInputStream();
            String stamp = FORMATTER.format(new Date());
            @SuppressWarnings("rawtypes")
            Map response = cloudinary.uploader().upload(in, ObjectUtils.asMap("public_id", "img_" + stamp));
            Object url = response.get("secure_url");
            return url != null ? url.toString() : null;
        } catch (Exception ex) {
            logger.debug("upload failed: {}", ex.getMessage());
            return null;
        } finally {
            try {
                if (in != null) in.close();
            } catch (Exception e) {
                logger.trace("failed to close stream", e);
            }
        }
    }
}