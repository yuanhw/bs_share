package cn.wyh.dao;

import cn.wyh.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    int updateSingByUserId(@Param("userId") int userId);

    List<Address> selectByUserId(@Param("userId") int userId);

    Address getDefaultAddressByUserId(@Param("userId") int userId);
}