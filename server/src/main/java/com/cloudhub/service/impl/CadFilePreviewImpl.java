package com.cloudhub.service.impl;

import com.cloudhub.config.ConfigConstants;
import com.cloudhub.model.FileAttribute;
import com.cloudhub.model.ReturnResponse;
import com.cloudhub.service.FileHandlerService;
import com.cloudhub.service.FilePreview;
import com.cloudhub.utils.DownloadUtils;
import com.cloudhub.utils.KkFileUtils;
import com.cloudhub.web.filter.BaseUrlFilter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

/**
 * @author chenjh
 * @since 2019/11/21 14:28
 */
@Service
public class CadFilePreviewImpl implements FilePreview {

    private static final String OFFICE_PREVIEW_TYPE_IMAGE = "image";
    private static final String OFFICE_PREVIEW_TYPE_ALL_IMAGES = "allImages";
    private static final String FILE_DIR = ConfigConstants.getFileDir();

    private final FileHandlerService fileHandlerService;
    private final OtherFilePreviewImpl otherFilePreview;

    public CadFilePreviewImpl(FileHandlerService fileHandlerService, OtherFilePreviewImpl otherFilePreview) {
        this.fileHandlerService = fileHandlerService;
        this.otherFilePreview = otherFilePreview;
    }

    @Override
    public String filePreviewHandle(String url, Model model, FileAttribute fileAttribute) {
        // 预览Type，参数传了就取参数的，没传取系统默认
        String officePreviewType = fileAttribute.getOfficePreviewType() == null ? ConfigConstants.getOfficePreviewType() : fileAttribute.getOfficePreviewType();
        String baseUrl = BaseUrlFilter.getBaseUrl();
        boolean forceUpdatedCache=fileAttribute.forceUpdatedCache();
        String fileName = fileAttribute.getName();
        String suffix = fileAttribute.getSuffix();
        String cadPreviewType = ConfigConstants.getCadPreviewType();
        String pdfName = fileName.substring(0, fileName.lastIndexOf(".")) + suffix +"." + cadPreviewType ; //生成文件添加类型后缀 防止同名文件
        String outFilePath = FILE_DIR + pdfName;
        // 判断之前是否已转换过，如果转换过，直接返回，否则执行转换
        if (forceUpdatedCache || !fileHandlerService.listConvertedFiles().containsKey(pdfName) || !ConfigConstants.isCacheEnabled()) {
            String filePath;
            ReturnResponse<String> response = DownloadUtils.downLoad(fileAttribute, null);
            if (response.isFailure()) {
                return otherFilePreview.notSupportedFile(model, fileAttribute, response.getMsg());
            }
            filePath = response.getContent();
            String imageUrls = null;
            if (StringUtils.hasText(outFilePath)) {
                try {
                    imageUrls =  fileHandlerService.cadToPdf(filePath, outFilePath,cadPreviewType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (imageUrls == null ) {
                    return otherFilePreview.notSupportedFile(model, fileAttribute, "office转图片异常，请联系管理员");
                }
                //是否保留CAD源文件
                if( ConfigConstants.getDeleteSourceFile()) {
                    KkFileUtils.deleteFileByPath(filePath);
                }
                if (ConfigConstants.isCacheEnabled()) {
                    // 加入缓存
                    fileHandlerService.addConvertedFile(pdfName, fileHandlerService.getRelativePath(outFilePath));
                }
            }
        }
        if("tif".equalsIgnoreCase(cadPreviewType)){
            model.addAttribute("currentUrl", pdfName);
            return TIFF_FILE_PREVIEW_PAGE;
        }else if("svg".equalsIgnoreCase(cadPreviewType)){
            model.addAttribute("currentUrl", pdfName);
            return SVG_FILE_PREVIEW_PAGE;
        }
        if (baseUrl != null && (OFFICE_PREVIEW_TYPE_IMAGE.equals(officePreviewType) || OFFICE_PREVIEW_TYPE_ALL_IMAGES.equals(officePreviewType))) {
            return OfficeFilePreviewImpl.getPreviewType(model, fileAttribute, officePreviewType, baseUrl, pdfName, outFilePath, fileHandlerService, OFFICE_PREVIEW_TYPE_IMAGE,otherFilePreview);
        }
        model.addAttribute("pdfUrl", pdfName);
        return PDF_FILE_PREVIEW_PAGE;
    }
}
