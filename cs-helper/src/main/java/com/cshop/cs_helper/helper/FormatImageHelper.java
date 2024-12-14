package com.cshop.cs_helper.helper;

import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import com.cshop.cs_helper.bean.ImageDataBean;
import com.cshop.cs_helper.bean.ServiceResponse;
import com.cshop.cs_helper.constants.CommonConstants;
import com.cshop.cs_helper.constants.MessageConstants;

public class FormatImageHelper {

	private static final Logger LOG = LogManager.getLogger();

	public static String formatImageUrl(String[] imageResizeStringArr, String requestID) {

		long startTime = System.currentTimeMillis();
		String imgurl = null;

		try {
//			imageResizeStringArr[2]={"minWidth":0,"minHeight":0,"maxWidth":0,"maxHeight":0,"optimalWidth":0,"optimalHeight":0}
//			imageResizeStringArr[7]= imagePath

			ServiceResponse<ImageDataBean> serviceResponse = getImageDim(imageResizeStringArr[7], requestID);
			if (serviceResponse != null) {
				if (serviceResponse.getData().size() > 0) {
					ImageDataBean imageDataBean = serviceResponse.getData().get(0);

					Integer imgWidth = (imageDataBean.getImageWidth() != null && imageDataBean.getImageWidth() > 0)
							? imageDataBean.getImageWidth()
							: 0;
					Integer imgHeight = (imageDataBean.getImageHeight() != null && imageDataBean.getImageHeight() > 0)
							? imageDataBean.getImageHeight()
							: 0;
					@SuppressWarnings("unused")
					String imgFormat = StringUtils.isNoneEmpty(imageDataBean.getImageFormt())
							? imageDataBean.getImageFormt()
							: CommonConstants.BLANK;

					JSONObject json = new JSONObject(imageResizeStringArr[2]);
					Integer minWidth = (Integer) json.getInt(CommonConstants.MIN_IMAGE_WIDTH);
					Integer minHeight = (Integer) json.getInt(CommonConstants.MIN_IMAGE_HEIGHT);
					Integer maxWidth = (Integer) json.getInt(CommonConstants.MAX_IMAGE_WIDHT);
					Integer maxHeight = (Integer) json.getInt(CommonConstants.MAX_IMAGE_HEIGHT);
					Integer optimalWidth = 0;
					Integer optimalHeight = 0;
					if ((imgWidth < minWidth || imgHeight < minHeight)
							|| (imgWidth > maxWidth || imgHeight > maxHeight)) {
						optimalWidth = (Integer) json.getInt(CommonConstants.OPTIMAL_IMAGE_WIDTH);
						optimalHeight = (Integer) json.getInt(CommonConstants.OPTIMAL_IMAGE_HEIGHT);

						imgurl = MessageFormat.format(imageResizeStringArr[3] + imageResizeStringArr[4]
								+ imageResizeStringArr[5] + imageResizeStringArr[6], optimalWidth.toString(),
								optimalHeight.toString());

					} else {
						imgurl = imageResizeStringArr[7];
					}

				} else {
					imgurl = imageResizeStringArr[7];
					LOG.error(requestID + MessageConstants.UNABLE_TO_FETCH_IMAGE_DATA);
				}
			} else {
				imgurl = imageResizeStringArr[7];
				LOG.error(requestID + MessageConstants.CALLING_GET_IMAGEDIM_RESPONSE_EMPTY);

			}
		} catch (Exception e) {
			imgurl = null;
			LogException.logException(e, requestID);
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return imgurl;

	}

	private static ServiceResponse<ImageDataBean> getImageDim(String imagePath, String requestID) {
		long startTime = System.currentTimeMillis();
		ImageDataBean imageDataBean = null;
		ServiceResponse<ImageDataBean> baseResponse = new ServiceResponse<ImageDataBean>();
		URL url = null;
		HttpURLConnection connection = null;
		BufferedImage image = null;
		ImageInputStream imageStream = null;
		Iterator<ImageReader> imageReaders = null;
		ImageReader reader = null;

		try {

			url = new URL(imagePath);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
			connection.setRequestMethod(CommonConstants.HTTP_METHOD_GET);
			image = ImageIO.read(connection.getInputStream());

			imageStream = ImageIO.createImageInputStream(url.openStream());

			imageReaders = ImageIO.getImageReaders(imageStream);

			imageReaders.hasNext();
			reader = (ImageReader) imageReaders.next();

			imageDataBean = new ImageDataBean();
			imageDataBean.setImageWidth(image.getWidth());
			imageDataBean.setImageHeight(image.getHeight());
			imageDataBean.setImageFormt(reader.getFormatName());
			baseResponse = new ServiceResponse<ImageDataBean>();
			baseResponse.setData(Arrays.asList(imageDataBean));

		} catch (Exception e) {
			LOG.error(requestID + MessageConstants.ERROR_WHILE_GETTING_IMAGE_DATA);
			e.printStackTrace();
			LogException.logException(e, requestID);
		} finally {
			url = null;
			imageDataBean = null;
			connection = null;
			image = null;
			imageStream = null;
			imageReaders = null;
			reader = null;
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + CommonConstants.EXEC_TIME + (endTime - startTime));
		return baseResponse;
	}
}
