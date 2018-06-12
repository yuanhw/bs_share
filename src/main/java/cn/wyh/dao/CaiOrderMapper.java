package cn.wyh.dao;

import cn.wyh.dto.CaiOneDto;
import cn.wyh.dto.CaiOrderWeb;
import cn.wyh.dto.CaiSearchDto;
import cn.wyh.entity.CaiOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CaiOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaiOrder record);

    int insertSelective(CaiOrder record);

    CaiOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaiOrder record);

    int updateByPrimaryKey(CaiOrder record);

    int getNumByPlantId(@Param("plantId") int plantId);

    List<CaiOneDto> selectCaiOrder(@Param("userId") int userId, @Param("status") int status);

    List<CaiOrderWeb> selectCaiOrderWeb(CaiSearchDto dto);

    int getCaiOrderTotalForWeb(CaiSearchDto dto);
}