package cn.myjava.ssmDemo.entity;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.Serializable;

/**
 * 导入导出Bean
 */
public class ExcelBean implements Serializable {
    private static final long serialVersionUID = 8378617050358082182L;
    private String titleNameName; //列头（标题）名
    private String propertyName; //对应字段名
    private Integer cols; //合并单元格数

    public ExcelBean(String titleNameName, String propertyName, Integer cols){
        this.titleNameName= titleNameName;
        this.propertyName = propertyName;
        this.cols = cols;
    }
    public String getTitleNameName() {
        return titleNameName;
    }

    public void setTitleNameName(String titleNameName) {
        this.titleNameName = titleNameName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

}
