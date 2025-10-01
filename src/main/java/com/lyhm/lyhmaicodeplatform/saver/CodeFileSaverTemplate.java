package com.lyhm.lyhmaicodeplatform.saver;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.lyhm.lyhmaicodeplatform.constant.AppConstant;
import com.lyhm.lyhmaicodeplatform.exception.BusinessException;
import com.lyhm.lyhmaicodeplatform.exception.ErrorCode;
import com.lyhm.lyhmaicodeplatform.model.enums.CodeGenTypeEnum;

import java.io.File;
import java.nio.charset.StandardCharsets;

public abstract class CodeFileSaverTemplate<T> {

    // 应用生成根目录（用于浏览）
    private static final String PREVIEW_ROOT_DIR = AppConstant.CODE_OUTPUT_ROOT_DIR;



    public final File saveCode(T result,Long appId) {
        //1.验证输入
        validateInput(result);
        //2.构建唯一目录
        String baseDirPath = buildUniqueDir(appId);
        //3.保存文件（具体实现由子类提供)
        saveFiles(result,baseDirPath);
        //4.返回目录文件对象
        return new File(baseDirPath);
    }
    protected void validateInput(T result){
        if (result == null) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"代码结果对象不能为空");
        }
    }

    /**
     * 构建唯一目录路径
     * @return 目录路径
     */
    protected final String buildUniqueDir(Long appId) {
        if(appId == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"应用ID吧你为空");
        }
        String codeType = getCodeType().getValue();
        String uniqueDirName = StrUtil.format("{}_{}",codeType, appId);
        String dirPath = FILE_SAVE_ROOT_DIR + File.separator + uniqueDirName;
        FileUtil.mkdir(dirPath);
        return dirPath;
    }

    /**
     * 写入单个文件的工具方法
     * @param dirPath
     * @param filename
     * @param content
     */
    protected final  void writeToFile(String dirPath,String filename,String content) {
        if (StrUtil.isNotBlank(content)) {
            String filePath = dirPath + File.separator + filename;
            FileUtil.writeString(content,filePath, StandardCharsets.UTF_8);
        }
    }

    /**
     * 获取代码类型（由子类实现）
     * @return
     */
    protected abstract CodeGenTypeEnum getCodeType();

    /**
     * 保存文件的具体实现（由子类实现）
     * @param result
     * @param baseDirPath
     */
    protected abstract  void saveFiles(T result,String baseDirPath);

    // 文件保存根目录
    protected static final String FILE_SAVE_ROOT_DIR = AppConstant.CODE_OUTPUT_ROOT_DIR;

}
