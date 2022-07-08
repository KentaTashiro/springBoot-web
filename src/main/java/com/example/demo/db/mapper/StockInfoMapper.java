package com.example.demo.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.db.entity.StockInfo;

@Mapper
public interface StockInfoMapper {
	StockInfo search(String id);
}

// @Mapper            ：Mybatisを使用するときのお決まり
// StockInfoMapper.xml：src/main/resources配下に「StockInfoMapper.java」のパッケージ（com.example.demo.mapper）と同様の階層で配置する。
