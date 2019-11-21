package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.*;
import com.springmall.mapper.*;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    Goods_attributeMapper goodsAttributeMapper;
    @Autowired
    Goods_specificationMapper goodsSpecificationMapper;
    @Autowired
    Goods_productMapper productMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GrouponService grouponService;
    @Autowired
    BrandService brandService;
    @Autowired
    IssueService issueService;
    @Autowired
    CommentService commentService;
    @Autowired
    CollectService collectService;

    /**
     * 更新商品信息
     * @param goods
     * @param specifications
     * @param products
     * @param attributes
     */
    @Override
    @Transactional
    public void updateGoodsInfo(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes) {
        // 删除指定id的goods相关信息，然后重新写入：即更新
        goodsMapper.updateByPrimaryKeySelective(goods);
        //1.删除
        Integer id = goods.getId();
        if (specifications.size() != 0) {
            Goods_specificationExample specificationExample = new Goods_specificationExample();
            specificationExample.createCriteria().andGoodsIdEqualTo(id);
            goodsSpecificationMapper.deleteByExample(specificationExample);
            goodsSpecificationMapper.insertSpecificationsSelective(specifications);
        }
        if (products.size() !=0) {
            Goods_productExample productExample = new Goods_productExample();
            productExample.createCriteria().andGoodsIdEqualTo(id);
            productMapper.deleteByExample(productExample);
            for (Goods_product product : products) {
                product.setGoodsId(goods.getId());
            }
            productMapper.insertProductsSelective(products);
        }
        if (attributes.size() != 0) {
            AttributeExample attributeExample = new AttributeExample();
            attributeExample.createCriteria().andGoodsIdEqualTo(id);
            attributeMapper.deleteByExample(attributeExample);
            attributeMapper.insertAttributesSelective(attributes);
        }

        /*goodsMapper.updateByPrimaryKeySelective(goods);
        *//*productMapper.updateProductsByPrimaryKeySelective(products);
        attributeMapper.updateAttributesByPrimaryKeySelective(attributes);
        goodsSpecificationMapper.updateSpecificationsByPrimaryKeySelective(specifications);*//*
        for (Goods_specification specification : specifications) {
            goodsSpecificationMapper.updateByPrimaryKey(specification);
        }
        for (Goods_product product : products) {
            productMapper.updateByPrimaryKey(product);
        }
        for (Goods_attribute attribute : attributes) {
            goodsAttributeMapper.updateByPrimaryKey(attribute);
        }*/
    }

    /**
     * 查询商品总数
     * @return
     */
    @Override
    public long queryTotalGoods() {
        GoodsExample example = new GoodsExample();
        example.createCriteria().andDeletedEqualTo(false);
        return goodsMapper.countByExample(example);
    }

    /**
     * 若categoryId不为0或者不为null获取当前类目下的商品信息和所有二级类目信息
     * 如果有关键词则根据关键词搜索
     * 若有brandId则根据brandId搜索
     * @param
     * @return
     */
    @Override
    public List<Goods> goodsListInCurrentCategory(PageRequest request) {
        PageHelper.startPage(request.getPage(), request.getSize());

        Integer categoryId = request.getCategoryId();
        String keyword = request.getKeyword();
        Integer brandId = request.getBrandId();
        GoodsExample example = new GoodsExample();

        //如果id不为0则根据id查询
        if (categoryId != null && categoryId != 0) {
            example.createCriteria().andCategoryIdEqualTo(categoryId).andDeletedEqualTo(false);
        }
        if (keyword != null){
            //否则根据关键词查询
            example.createCriteria().andNameLike("%" + keyword + "%").andDeletedEqualTo(false);
        }
        if (brandId != null) {
            example.createCriteria().andBrandIdEqualTo(brandId).andDeletedEqualTo(false);
        }

        List<Goods> goods = goodsMapper.selectByExample(example);

        return goods;

    }

    /**
     * 获取相同类目的商品
     * @param id
     * @return
     */
    @Override
    public List<Goods> selectGoodsInSameCategory(Integer id) {
        return goodsMapper.selectGoodsInSameCategory(id);
    }

    /**
     * 显示商品详情
     * @param id
     * @return
     */
    @Override
    public Map selectGoodsDetailById(Integer id) {
        //查询商品团购信息
        List grouponRules = grouponService.selectRulesByGoodsId(id);

        //查询商品相关信息
        GoodInfoData goodInfoData = queryGoodsDetailByGoodsId(id);
        List<Attribute> attributes = goodInfoData.getAttributes();
        Goods goods = goodInfoData.getGoods();
        List<Goods_product> products = goodInfoData.getProducts();

        //根据商品信息获取所属品牌id
        Integer brandId = goods.getBrandId();
        Brand brand = brandService.queryBrandById(brandId);

        //获取所有常见问题
        List issues = issueService.queryAllIssue();

        //查询商品的评论
        List comments = commentService.queryCommentsByGoodsId(id);
        //封装评论
        Map commentMap = new HashMap();
        commentMap.put("data", comments);
        commentMap.put("count", comments.size());

        //查看用户是否收藏该商品
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        Boolean isCollect = null;
        if (principal != null) {
            Integer userId = principal.getId();
            isCollect = collectService.queryGoodCollect(userId, id);
        }

        //封装规格
        List<Map<String,Object>> specList = new ArrayList<>(); //规格结果集

        List<Goods_specification> specifications = goodInfoData.getSpecifications();
        //specList的索引
        int currentIndex = 0;
        for (Goods_specification specification : specifications) {
            String specName = specification.getSpecification();
            //用map去重，key为规格名，value为索引值
            Map<String,Integer> nameMap = new HashMap<>();
            Integer index = nameMap.get(specName);
            if (index == null) {
                //说明没有与该规格名重名的
                //将该规格名和现在的index存入保存规格名的map中
                nameMap.put(specName, currentIndex);
                //保存结果的list索引值+1,用于保存新的规格名
                currentIndex++;
                Map<String,Object> sameNameSpec = new HashMap<>();
                List<Goods_specification> valueList = new ArrayList<>();
                valueList.add(specification);
                sameNameSpec.put("name", specName);
                sameNameSpec.put("valueList", valueList);
                specList.add(sameNameSpec);
            } else {
                //说明已经存在了该规格名
                //根据索引获取结果list的中保存的具体信息
                Map<String, Object> map = specList.get(index);
                //获取相同规格名保存的list
                List<Goods_specification> valueList = (List<Goods_specification>) map.get("valueList");
                //添加该规格信息
                valueList.add(specification);
                //将更新后的数据重新封装进结果list中
                map.put("valueList", valueList);
                specList.add(index, map);
            }
        }
        //封装所有数据
        Map result = new HashMap();
        result.put("attribute", attributes);
        result.put("brand", brand);
        result.put("comment", commentMap);
        result.put("groupon", grouponRules);
        result.put("info", goods);
        result.put("issue", issues);
        result.put("productList", products);
        result.put("shareImage", ""); //未知
        result.put("specificationList", specList);
        result.put("userHasCollect", isCollect);
        return result;
    }

    /**
     * 获取热销商品
     * @param amountLimimt
     * @return
     */
    @Override
    public List<Goods> getHotGoods(int amountLimimt) {
        return goodsMapper.selectHotGoods(amountLimimt);
    }

    /**
     *
     * @param goods 商品信息
     * @param specifications 商品规格
     * @param products 商品表
     * @param attributes 商品参数
     * @return
     */
    @Override
    @Transactional
    public int createGoods(Goods goods, List<Goods_specification> specifications, List<Goods_product> products, List<Goods_attribute> attributes) {
        goodsMapper.insertSelective(goods);
        Integer id = goods.getId();
        for (Goods_product product : products) {
            product.setGoodsId(id);
        }
        productMapper.insertProductsSelective(products);
        for (Goods_attribute attribute : attributes) {
            attribute.setGoodsId(id);
        }
        attributeMapper.insertAttributesSelective(attributes);
        for (Goods_specification specification : specifications) {
            specification.setGoodsId(id);
        }
        goodsSpecificationMapper.insertSpecificationsSelective(specifications);
//        goodsSpecificationMapper.insertSelective(specifications);
        return 1;
    }

    /**
     * 查询商品信息
     * @param id 商品id
     * @return
     */
    @Override
    public GoodInfoData queryGoodsDetailByGoodsId(int id) {
        GoodInfoData goodInfoData = new GoodInfoData();
        // 查询goods表
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        // 查询attribute表
        AttributeExample attributeExample = new AttributeExample();
        attributeExample.createCriteria().andGoodsIdEqualTo(id);
        List<Attribute> attributes = attributeMapper.selectByExample(attributeExample);
        // 查询specifications表
        Goods_specificationExample goodsSpecificationExample = new Goods_specificationExample();
        goodsSpecificationExample.createCriteria().andGoodsIdEqualTo(id);
        List<Goods_specification> specifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        // 查询products表
        Goods_productExample productExample = new Goods_productExample();
        productExample.createCriteria().andGoodsIdEqualTo(id);
        List<Goods_product> products = productMapper.selectByExample(productExample);
        // 封装数据
        Integer categoryId = goods.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Integer[] categoryIds = new Integer[]{category.getPid(),category.getId()};
        goodInfoData.setCategoryIds(categoryIds);
        goodInfoData.setGoods(goods);
        goodInfoData.setAttributes(attributes);
        goodInfoData.setSpecifications(specifications);
        goodInfoData.setProducts(products);
        return goodInfoData;
    }

    /**
     * 逻辑删除商品
     * @param goods
     * @return
     */
    @Override
    public int deleteGoods(Goods goods) {
        goods.setDeleted(true);
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 根据id获取对象
     * @param goodsId
     * @return
     */
    @Override
    public Goods selectGoodsById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    /**
     * 分页查询商品
     * @param page 当前页
     * @param limit 每页显示数量
     * @param goodsSn 商品编号
     * @param name 商品名
     * @param sortField 排序字段
     * @param order 排序规则
     * @return
     */
    @Override
    public List<Goods> queryGoodsByPage(String page, String limit, String goodsSn, String name, String sortField, String order) {
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        GoodsExample goodsExample = new GoodsExample();
        goodsExample.setOrderByClause(sortField + " " + order);
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        // goodsSn或name不为空是才查询
        if (!StringUtils.isEmpty(goodsSn)) {
            criteria.andGoodsSnEqualTo(goodsSn);
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        return goods;
    }
}
