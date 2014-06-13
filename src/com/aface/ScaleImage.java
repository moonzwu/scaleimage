package com.aface;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScaleImage {

    public static void main(String[] args) {
        File log4jPropertyFile= new File(System.getProperty("user.dir"), "log4j.properties");
        PropertyConfigurator.configure(log4jPropertyFile.getAbsolutePath());
        Logger logger = Logger.getLogger(ScaleImage.class);

        Date startTime = new Date();
        String srcFilePath = args[0];
        File srcFile = new File(srcFilePath);
        if (srcFile.exists()) {
            String extension = FilenameUtils.getExtension(srcFilePath);
            int dispW = Integer.parseInt(args[1]);
            int dispH = Integer.parseInt(args[2]);

            try {
                Thumbnails.of(srcFile)
                        .size(dispW, dispH)
                        .outputFormat(extension)
                        .toFiles(Rename.PREFIX_DOT_THUMBNAIL);
            } catch (IOException e) {
                logger.error(e.getStackTrace());
            }

            Date endTime = new Date();
            logger.debug(String.format("Convert picture : %s | Cost: %dms",
                    srcFilePath,
                    (endTime.getTime() - startTime.getTime())));
        } else {
            logger.info("File " + srcFilePath + " doesn't exist.");
        }
    }
}
