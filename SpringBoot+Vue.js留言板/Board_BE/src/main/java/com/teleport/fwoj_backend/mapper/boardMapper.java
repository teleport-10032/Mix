package com.teleport.fwoj_backend.mapper;
import com.teleport.fwoj_backend.pojo.board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface boardMapper {
    //获取所有title content
    List<board> getBoardList();
    //提交title content
    int boardSubmit(String title,String content);
}
