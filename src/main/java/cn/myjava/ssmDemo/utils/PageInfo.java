package cn.myjava.ssmDemo.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 李雪阳
 * @version 1.0
 * @date 2020/7/23  20:19
 */
@Setter
@Getter
public class PageInfo<T> {
    /**
     * 页面数据
     */
    private List<T> datas;
    /**
     * 当前页码
     */
    private Integer pageNo;
    /**
     * 总记录数
     */
    private Integer totalRows;
    /**
     * 总页数
     */
    private Integer totalPages;
    /**
     * 每页数据条数
     */
    private Integer pageSize;
    /**
     * 查询页的开始下标索引
     */
    private Integer index;

    public PageInfo() {
        this.pageNo = 1;
        this.pageSize = 3;
    }

    public Integer getTotalPages() {
        return totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getIndex() {
        return (pageNo - 1) * pageSize;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
