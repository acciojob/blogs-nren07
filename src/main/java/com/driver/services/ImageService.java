package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image=new Image();
        image.setDescription(description);
        image.setDimentions(dimensions);

        Blog blog=blogRepository2.findById(blogId).get();
        image.setBlog(blog);

        blog.getImageList().add(image);

        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(id).get();
        String dimentions=image.getDimentions();
        String arr[]=dimentions.split("X");
        int imageHeight=Integer.parseInt(arr[0]);
        int imageWidth=Integer.parseInt(arr[1]);

        String arr2[]=screenDimensions.split("X");
        int screenHeight=Integer.parseInt(arr2[0]);
        int screenWidth=Integer.parseInt(arr2[1]);

        int ans=(int)(Math.floor(new Double(screenHeight)/new Double(imageHeight))*Math.floor(new Double(screenWidth)/new Double(imageWidth)));

        return ans;
    }
}
