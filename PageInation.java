
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 通用分页器
 *
 * @author liyebing created on  15/9/10.
 * @version $Id$
 */
public class PageInation<T> implements Serializable {

    private static final long serialVersionUID = 4773269869603036013L;

    //默认每页大小
    private static final int DEFAULT_SIZE = 20;
    //存放当前页中的数据
    private List<T> data;
    //每页大小
    private int pageSize;
    //记录总数
    private int totalCount;
    //当前页的数据
    private int currentPage;
    //总页数
    private int totalPage;


    public PageInation() {
        this(0, DEFAULT_SIZE, 0, Collections.EMPTY_LIST);
    }

    public static <T>  PageInation<T> create(){
       return new PageInation<T>();
    }

    public static <T>  PageInation<T> create(int currentPage, int pageSize, int totalCount){
       return new PageInation<T>(currentPage,pageSize,totalCount);
    }

    public static <T>  PageInation<T> create(int currentPage, int pageSize, int totalCount,List<T> data){
        return new PageInation<T>(currentPage,pageSize,totalCount,data);
    }

    public PageInation(int currentPage, int pageSize, int totalCount){
        this.currentPage = currentPage;
        this.totalCount=totalCount;
        this.pageSize=pageSize;
        totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
    }

    public PageInation(int currentPage, int pageSize, int totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.data = data;
        this.totalCount=totalCount;
        this.pageSize=pageSize;
        //totalPage = (totalCount + size - 1) / size;
        totalPage = totalCount % pageSize == 0 ? (totalCount / pageSize) : (totalCount / pageSize + 1);
    }

    public PageInation(int currentPage, int totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.data = data;
        this.totalCount=totalCount;
        this.pageSize=DEFAULT_SIZE;
        //totalPage = (totalCount + size - 1) / size;
        totalPage = totalCount % DEFAULT_SIZE == 0 ? (totalCount / DEFAULT_SIZE) : (totalCount / DEFAULT_SIZE + 1);
    }



    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }



    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getOffset() {
        return (currentPage-1)*pageSize;
    }


}
