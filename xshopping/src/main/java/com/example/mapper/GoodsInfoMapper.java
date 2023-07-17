package com.example.mapper;

import com.example.entity.GoodsInfo;
import com.example.entity.TypeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * 商品相关的mapper
 */
@Repository
public interface GoodsInfoMapper extends Mapper<GoodsInfo> {
    /*根据商品名称模糊查询，或者根据id查询一个商品*/
    List<GoodsInfo> findByName(@Param("name") String name,@Param("id") Long id);

    /*查询推荐商品*/
    @Select("select * from goods_info where recommend = '是'")
    List<GoodsInfo> findRecommendGoods();

    /*根据类型查询商品列表*/
    @Select("select * from goods_info where typeId = #{typeId}")
    List<GoodsInfo> findByType(@Param("typeId")Integer typeId);

    /*根据评分查询商品排名*/
    @Select("select * from goods_info order by mark desc")
    List<GoodsInfo> findByMark();
}