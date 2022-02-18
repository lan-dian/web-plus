package com.landao.web.plus.model.response;



import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 分页包装类
 */
public class PageDTO<T> {

    private Long n;

    private List<T> data;

    public PageDTO() {

    }

    public PageDTO(Long n, List<T> data) {
        this.n = n;
        this.data = data;
    }

    public static <T> PageDTO<T> build(Long n, List<T> data){
        return new PageDTO<>(n,data);
    }

    public static <T> PageDTO <T> build(IPage<T> iPage){
        return new PageDTO<>(iPage.getTotal(),iPage.getRecords());
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageDTO{" +
                "n=" + n +
                ", data=" + data +
                '}';
    }
}
