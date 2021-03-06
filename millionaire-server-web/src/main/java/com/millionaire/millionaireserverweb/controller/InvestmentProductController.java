package com.millionaire.millionaireserverweb.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.ProductQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ContractResponse;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Liu Kai
 * @Description: TODO 对投资产品的 查询 修改 新增
 * @date 2018/8/18 12:41
 */

@RestController
@RequestMapping("/a")
public class InvestmentProductController {
  private   Logger logger = LoggerFactory.getLogger(InvestmentProductController.class);
    private final InvestmentProductService investmentProductService;
    @Autowired
    public InvestmentProductController(InvestmentProductService investmentProductService) {
        this.investmentProductService = investmentProductService;
    }

    ContractResponse contractResponse = new ContractResponse();

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private RedisTemplate redisTemplate;



    /**
     * @param investmentProduct 投资产品类
     * @return 成功1 失败-1
     * @Description 新增投资产品
     *
     **/
    @PostMapping("/investment-product")
    public ResultBean insertInvestmentProduct(@RequestBody @Validated InvestmentProduct investmentProduct) {
        logger.info("新增投资产品:{]",investmentProduct);
        //产品名检验
        String name = investmentProduct.getName();
        InvestmentProduct product1 = investmentProductService.selectByProductName(name);
        //产品代号校验
        String code = investmentProduct.getProductCode();
        InvestmentProduct product2 = investmentProductService.selectByProductCode(code);
        //起投金额
        Integer startAmount=investmentProduct.getStartingAmount();
        if (product1 != null) {
            return new ResultBean(-1, "产品名重复", name);
        }
        if (product2 != null) {
            return new ResultBean(-1, "产品代号重复", code);
        }
        if(startAmount%10000 != 0){
            return new ResultBean(-1, "起投金额需为万的倍数",startAmount);
        }
        else {
            investmentProductService.insertProductSelective(investmentProduct);
            Long id = investmentProduct.getId();
            logger.info("新增投资产品id:{}", id);
            return new ResultBean(1, "success", id);
        }
    }

    /**
     * @param productId 产品id
     *        @RequestParam("isShelf") byte isShelf
     * @return 1=成功  -1=失败
     * @Description 编辑产品上下架状态
     *
     **/
    @PutMapping("/product-shelf/{productId}")
    public ResultBean updateProductIsShelf(@PathVariable("productId") Long productId,
                                         @RequestBody JSONObject jsonObject) {

        byte isShelf = jsonObject.getByte("isShelf");

//        if (isShelf == null) {
//            logger.info("上下架状态为空：{}",isShelf);
//            return new ResultBean(-1, "error isShelf is null", isShelf);
//        }
        // 对isShelf是否要添加此判断？若以后添加新状态，需要修改
        // 判断参数是否为0/1
        if (isShelf != 1 && isShelf != 0) {
            return new ResultBean(-1, "error isShelf should be 0 or 1", isShelf);
        }
        InvestmentProduct product = investmentProductService.selectByPrimaryKey(productId);
        if (product == null) {
            return new ResultBean(-1, "error no such productId", productId);
        } else {
            product.setIsShelf(isShelf);
            investmentProductService.updateProductByPrimaryKeySelective(product);
            logger.info("上下架产品，产品id:{} 上架情况:{}", productId, isShelf);
            return new ResultBean(1, "success");
        }
    }

    /**
     * @param productId 产品id
     * @return 成功1 失败-1
     * @Description 更新产品 角标 推荐 限购状态
     *  @RequestParam("type") Byte type,
     *  @RequestParam("isRecommend") Byte isRecommend,
     *   @RequestParam("isPurchaseLimit") Byte isPurchaseLimit
     *
     *
     **/
    @PutMapping("/product-info/{productId}")
    public ResultBean updateProductShelfCommendLimit(@PathVariable("productId") Long productId,
                                                     @RequestBody JSONObject jsonObject) {

        Byte type = jsonObject.getByte("type");
        Byte isRecommend = jsonObject.getByte("isRecommend");
        Byte isPurchaseLimit = jsonObject.getByte("isPurchaseLimit");


        //非空判断
        if(type == null || isRecommend == null || isPurchaseLimit == null){
            return new ResultBean(-1, "error type/recommend/limit can not be null");
        }
        //参数类型判断
        if (type != 10 && type != 20 && type != 30){
            return new ResultBean(-1, "error type",type);
        }
        if (isRecommend != 0 && isRecommend != 1){
            return new ResultBean(-1, "error isRecommend",isRecommend);
        }
        if (isPurchaseLimit != 0 && isPurchaseLimit != 1){
            return new ResultBean(-1, "error isPurchaseLimit",isPurchaseLimit);
        }
        //判断产品id存在
        InvestmentProduct product = investmentProductService.selectByPrimaryKey(productId);
        if(product == null){
            return new ResultBean(-1, "error no such productID", productId);
        }
        else {
            product.setIsRecommend(isRecommend);
            product.setIsPurchaseLimit(isPurchaseLimit);
            product.setType(type);
            logger.info("更新投资产品:{}",product);
            investmentProductService.updateProductByPrimaryKeySelective(product);
            logger.info("更新产品角标限购推荐状态 id:{}，type:{}，isLimit:{}，isRecommend:{}",
                    productId,type,isPurchaseLimit,isRecommend);
            return new ResultBean(1, "success");
        }
    }

    /**
     * @param pageSize     每页大小
     * @param pageNum      页码
     * @param productQuery 包装查询产品的相关参数
     * @return 根据条件查询的分页产品信息
     * @Description 分页查看所有产品信息
     **/
    @GetMapping("/list/investment-product")
    public ResultBean getListInvestmentProduct(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                               ProductQuery productQuery) {

            PageInfo<InvestmentProduct> pageInfo =
                    investmentProductService.selectProductByPage(productQuery, pageSize, pageNum);
            logger.info("查询产品参数：{}", productQuery);
            return new ResultBean(1, "success", pageInfo);

    }



    /**
     * @param productId 产品id
     * @return 成功1 失败-1
     * @Description 通过id查询产品
     **/
    @GetMapping("/product-info/{productId}")
    public ResultBean selectInvestmentProduct(@PathVariable("productId") Long productId) {
        InvestmentProduct product = investmentProductService.selectByPrimaryKey(productId);
        if (product == null) {
            return new ResultBean(-1, "error no such productID", productId);
        } else {
            logger.info("查询投资产品 id:{}", productId);
            return new ResultBean(1, "success", product);
        }
    }

    /**
     *@author qiaobao
     *@datetime  2018/9/15 19:47
     *@decribe 用户投资合同获取
     */

    @GetMapping("/investment-contract/{id}")
    public ResultBean getContractUser(@PathVariable("id") Long id) {
        contractResponse = investmentUserService.selectContractResponse(id);
        String companySeal = (String) redisTemplate.opsForValue().get("seal");
        contractResponse.setCompanySeal(companySeal);
        return new ResultBean(1, "success", contractResponse);
    }

}