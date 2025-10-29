package backend.hobbiebackend.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.of("UTC"));

    private final Cloudinary cloudinary;

    public ImageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        if (file == null) return null;

        try (InputStream in = file.getInputStream()) {
            String stamp = FORMATTER.format(Instant.now());
            @SuppressWarnings("unchecked")
            Map<String, Object> response = cloudinary.uploader().upload(in, ObjectUtils.asMap("public_id", "img_" + stamp));
            Object url = response.get("secure_url");
            return url != null ? url.toString() : null;
        } catch (Exception ex) {
            logger.warn("upload failed: {}", ex.getMessage());
            return null;
        }
    }
}