package com.example.shop.mapper;

import com.example.shop.model.dao.Product;
import com.example.shop.model.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductDto map(Product product);

    Product map(ProductDto productDto);
}
