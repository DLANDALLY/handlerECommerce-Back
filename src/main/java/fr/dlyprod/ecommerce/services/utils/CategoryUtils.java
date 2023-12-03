package fr.dlyprod.ecommerce.services.utils;

import fr.dlyprod.ecommerce.entities.Categorie;
import fr.dlyprod.ecommerce.forms.CategoryForm;
import org.modelmapper.ModelMapper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Categorie convertToCategory(CategoryForm categoryForm) {
        return modelMapper.map(categoryForm, Categorie.class);
    }

    public static boolean validateUrl(String url) {
        String URL_REGEX = "^https?://(?:www\\.)?[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+(?:/[^\\s]*)?$";
        Pattern pattern = Pattern.compile(URL_REGEX);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public static void checkPhoto(String urlString) throws IOException {
        URL url = new URL(urlString);
        BufferedImage image = ImageIO.read(url);

        BufferedImage filteredImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

        //ImageIO.write(filteredImage, "png", new File("chemin/vers/image_filtree.png"));
        ImageIO.write(filteredImage, "png", new File("src/main/resources/images/image_filtree.png"));

    }
}
