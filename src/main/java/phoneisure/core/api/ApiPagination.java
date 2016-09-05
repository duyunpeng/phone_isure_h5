package phoneisure.core.api;

import java.util.List;

/**
 * Created by YJH on 2016/3/18.
 */
public class ApiPagination<T> {
    private List<T> data;
    private int count;
    private int page;
    private int pageSize;

    public ApiPagination() {
    }

    public List<T> getData() {
        return this.data;
    }

    public int getCount() {
        return this.count;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getPage() {
        return this.page;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
