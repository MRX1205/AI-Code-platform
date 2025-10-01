package com.lyhm.lyhmaicodeplatform.saver;

import cn.hutool.core.util.StrUtil;
import com.lyhm.lyhmaicodeplatform.ai.model.HtmlCodeResult;
import com.lyhm.lyhmaicodeplatform.exception.BusinessException;
import com.lyhm.lyhmaicodeplatform.exception.ErrorCode;
import com.lyhm.lyhmaicodeplatform.model.enums.CodeGenTypeEnum;

/**
 * HTML代码文件保存器
 */
public class HtmlCodeFileSaverTemplate extends CodeFileSaverTemplate<HtmlCodeResult> {
    @Override
    protected CodeGenTypeEnum getCodeType() {
        return  CodeGenTypeEnum.HTML;
    }

    @Override
    protected void saveFiles(HtmlCodeResult result,String baseDirPath) {
        //保存HTML文件
        writeToFile(baseDirPath,"index.html",result.getHtmlCode());
    }
    @Override
    protected void validateInput(HtmlCodeResult result) {
        super.validateInput(result);
        //HTML代码不能为空
        if (StrUtil.isBlank(result.getHtmlCode())) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"HTML代码不能为空");

        }
    }
}
