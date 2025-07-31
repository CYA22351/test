package service.impl;

import dao.SaleDAO;
import dao.impl.SaleDAOImpl;
import entity.Sale;
import entity.SaleItem;
import entity.Product;
import entity.Member;
import entity.StockLog;
import service.SaleService;
import service.ProductService;
import service.MemberService;
import service.StockLogService;
import service.impl.ProductServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.StockLogServiceImpl;
import util.StringUtil;
import util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class SaleServiceImpl implements SaleService {

    private SaleDAO saleDAO = new SaleDAOImpl();
    private ProductService productService = new ProductServiceImpl();
    private MemberService memberService = new MemberServiceImpl();
    private StockLogService stockLogService = new StockLogServiceImpl();

    @Override
    public Sale createSale(int cashierId) {
        if (cashierId <= 0) {
            return null;
        }

        try {
            String saleNo = StringUtil.generateSaleNo();
            Sale sale = new Sale(saleNo, cashierId);
            sale.setPayType("cash"); // 默认现金支付，防止插入失败
            saleDAO.save(sale);
            return sale;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sale findBySaleNo(String saleNo) {
        if (StringUtil.isEmpty(saleNo)) {
            return null;
        }
        return saleDAO.findBySaleNo(saleNo);
    }

    @Override
    public Sale findById(int id) {
        return saleDAO.findById(id);
    }

    @Override
    public List<Sale> findAll() {
        return saleDAO.findAll();
    }

    @Override
    public List<Sale> findByCashier(int cashierId) {
        return saleDAO.findByCashier(cashierId);
    }

    @Override
    public List<Sale> findByMember(int memberId) {
        return saleDAO.findByMember(memberId);
    }

    @Override
    public List<Sale> findByStatus(String status) {
        return saleDAO.findByStatus(status);
    }

    @Override
    public List<Sale> findByDateRange(Date startDate, Date endDate) {
        return saleDAO.findByDateRange(startDate, endDate);
    }

    @Override
    public boolean addProductToSale(int saleId, String barcodeOrName, int quantity) {
        if (saleId <= 0 || StringUtil.isEmpty(barcodeOrName) || quantity <= 0) {
            return false;
        }
        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }
        // 先按条码查
        Product product = productService.findByBarcode(barcodeOrName);
        // 如果条码查不到，尝试用名称模糊查找唯一商品
        if (product == null) {
            java.util.List<Product> products = productService.searchByName(barcodeOrName);
            if (products != null && products.size() == 1) {
                product = products.get(0);
            } else {
                return false;
            }
        }
        if (product.getStatus() != 1) {
            return false;
        }
        // 检查库存（累加后总数）
        List<SaleItem> items = saleDAO.findItemsBySaleId(saleId);
        int oldQty = 0;
        for (SaleItem item : items) {
            if (item.getProductId() == product.getId()) {
                oldQty = item.getQuantity();
                break;
            }
        }
        int newQty = oldQty + quantity;
        if (!productService.checkStock(product.getId(), newQty)) {
            return false;
        }
        try {
            if (oldQty > 0) {
                // 已存在，直接更新数量
                ((dao.impl.SaleDAOImpl) saleDAO).updateSaleItemQuantity(saleId, product.getId(), newQty,
                        product.getPrice().multiply(java.math.BigDecimal.valueOf(newQty)));
            } else {
                // 不存在，插入新明细
                SaleItem item = new SaleItem(saleId, product.getId(), quantity, product.getPrice());
                saleDAO.saveSaleItem(item);
            }
            // 重新计算总额
            java.math.BigDecimal total = calculateTotal(saleId);
            sale.setTotalAmount(total);
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeProductFromSale(int saleId, int productId) {
        if (saleId <= 0 || productId <= 0) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        try {
            // 删除销售明细
            List<SaleItem> items = saleDAO.findItemsBySaleId(saleId);
            for (SaleItem item : items) {
                if (item.getProductId() == productId) {
                    // 这里应该删除特定的明细项，暂时简化处理
                    break;
                }
            }

            // 重新计算总额
            BigDecimal total = calculateTotal(saleId);
            sale.setTotalAmount(total);
            saleDAO.update(sale);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProductQuantity(int saleId, int productId, int quantity) {
        if (saleId <= 0 || productId <= 0 || quantity < 0) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        Product product = productService.findById(productId);
        if (product == null) {
            return false;
        }
        if (quantity > 0 && !productService.checkStock(productId, quantity)) {
            return false;
        }

        try {
            if (quantity == 0) {
                // 只删除该商品明细
                ((dao.impl.SaleDAOImpl) saleDAO).deleteSaleItemByProductId(saleId, productId);
            } else {
                // 直接更新该商品明细
                ((dao.impl.SaleDAOImpl) saleDAO).updateSaleItemQuantity(saleId, productId, quantity,
                        product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity)));
            }
            // 重新计算总额
            java.math.BigDecimal total = calculateTotal(saleId);
            sale.setTotalAmount(total);
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean setMember(int saleId, String cardNo) {
        if (saleId <= 0 || StringUtil.isEmpty(cardNo)) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        Member member = memberService.findByCardNo(cardNo);
        if (member == null || member.getStatus() != 1) {
            return false;
        }

        try {
            sale.setMemberId(member.getId());
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BigDecimal calculateTotal(int saleId) {
        if (saleId <= 0) {
            return BigDecimal.ZERO;
        }

        List<SaleItem> items = saleDAO.findItemsBySaleId(saleId);
        BigDecimal total = BigDecimal.ZERO;

        for (SaleItem item : items) {
            total = total.add(item.getAmount());
        }

        return total;
    }

    @Override
    public boolean checkout(int saleId, String payType) {
        if (saleId <= 0 || StringUtil.isEmpty(payType)) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        try {
            // 重新计算总额（防止前端未刷新）
            java.math.BigDecimal total = calculateTotal(saleId);
            sale.setTotalAmount(total);

            // 计算折扣
            BigDecimal discountAmount = BigDecimal.ZERO;
            if (sale.getMemberId() != null) {
                discountAmount = memberService.calculateDiscount(sale.getMemberId(), total);
            }

            // 计算实收金额
            BigDecimal actualAmount = total.subtract(discountAmount);

            // 更新销售单
            sale.setDiscountAmount(discountAmount);
            sale.setActualAmount(actualAmount);
            sale.setPayType(payType);
            // 强制设为COMPLETED
            sale.setStatus("COMPLETED");
            sale.setCompleteTime(DateUtil.getCurrentDateTime());
            saleDAO.update(sale);

            // 确保所有明细都已写入sale_item表（冗余安全）
            List<SaleItem> items = sale.getItems();
            if (items == null || items.isEmpty()) {
                items = saleDAO.findItemsBySaleId(saleId);
            }
            for (SaleItem item : items) {
                if (item.getId() == 0) {
                    saleDAO.saveSaleItem(item);
                }
            }

            // 减少库存并写入stock_log
            for (SaleItem item : items) {
                // 获取扣减前库存
                Product product = productService.findById(item.getProductId());
                int beforeStock = product.getStock();
                int afterStock = beforeStock - item.getQuantity();
                // 扣减库存
                productService.reduceStock(item.getProductId(), item.getQuantity());
                // 写入stock_log
                StockLog log = new StockLog();
                log.setProductId(item.getProductId());
                log.setChangeType("SALE");
                log.setBeforeStock(beforeStock);
                log.setAfterStock(afterStock);
                log.setChangeQuantity(-item.getQuantity());
                log.setOperatorId(sale.getCashierId());
                log.setRemark("销售出库，销售单号:" + sale.getSaleNo());
                stockLogService.addLog(log);
            }

            // 处理会员消费
            if (sale.getMemberId() != null) {
                memberService.processConsumption(sale.getMemberId(), actualAmount);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean holdSale(int saleId) {
        if (saleId <= 0) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        try {
            sale.setStatus("HOLD");
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cancelSale(int saleId) {
        if (saleId <= 0) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"PENDING".equals(sale.getStatus())) {
            return false;
        }

        try {
            sale.setStatus("CANCELLED");
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean resumeSale(int saleId) {
        if (saleId <= 0) {
            return false;
        }

        Sale sale = saleDAO.findById(saleId);
        if (sale == null || !"HOLD".equals(sale.getStatus())) {
            return false;
        }

        try {
            sale.setStatus("PENDING");
            saleDAO.update(sale);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String generateReceipt(int saleId) {
        Sale sale = saleDAO.findById(saleId);
        if (sale == null) {
            return "";
        }

        StringBuilder receipt = new StringBuilder();
        receipt.append("=== 销售小票 ===\n");
        receipt.append("销售单号: ").append(sale.getSaleNo()).append("\n");
        receipt.append("收银员: ").append(sale.getCashierName()).append("\n");
        receipt.append("时间: ").append(DateUtil.formatDateTime(sale.getCreateTime())).append("\n");
        receipt.append("会员: ").append(sale.getMemberName() != null ? sale.getMemberName() : "非会员").append("\n");
        receipt.append("--------------------------------\n");

        List<SaleItem> items = saleDAO.findItemsBySaleId(saleId);
        for (SaleItem item : items) {
            receipt.append(String.format("%-20s %d x %.2f = %.2f\n",
                    item.getProductName(), item.getQuantity(), item.getPrice(), item.getAmount()));
        }

        receipt.append("--------------------------------\n");
        receipt.append(String.format("应收总额: %.2f\n", sale.getTotalAmount()));
        receipt.append(String.format("优惠金额: %.2f\n", sale.getDiscountAmount()));
        receipt.append(String.format("实收金额: %.2f\n", sale.getActualAmount()));
        receipt.append("支付方式: ").append(sale.getPayType()).append("\n");
        receipt.append("=== 谢谢惠顾 ===\n");

        return receipt.toString();
    }

    @Override
    public BigDecimal getTotalSales(Date startDate, Date endDate) {
        return saleDAO.getTotalSales(startDate, endDate);
    }

    @Override
    public int getSaleCount(Date startDate, Date endDate) {
        return saleDAO.getSaleCount(startDate, endDate);
    }

    @Override
    public List<Sale> getHoldSales(int cashierId) {
        List<Sale> allSales = saleDAO.findByCashier(cashierId);
        allSales.removeIf(sale -> !"HOLD".equals(sale.getStatus()));
        return allSales;
    }

    @Override
    public boolean addSale(Sale sale) {
        return false;
    }

    @Override
    public boolean updateSale(Sale sale) {
        return false;
    }

    @Override
    public boolean deleteSale(int id) {
        return false;
    }

    @Override
    public List<Sale> findByDateRange(String startDate, String endDate) {
        if (StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)) {
            return findAll();
        }
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date start = sdf.parse(startDate);
            java.util.Date end = sdf.parse(endDate);
            // 包含当天结束时间
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.setTime(end);
            cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
            cal.add(java.util.Calendar.SECOND, -1);
            end = cal.getTime();
            return saleDAO.findByDateRange(start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Sale> findByStatus(int status) {
        // status: 1=COMPLETED, 0=PENDING, 2=CANCELLED, 3=HOLD
        String statusStr;
        switch (status) {
            case 1:
                statusStr = "COMPLETED";
                break;
            case 0:
                statusStr = "PENDING";
                break;
            case 2:
                statusStr = "CANCELLED";
                break;
            case 3:
                statusStr = "HOLD";
                break;
            default:
                statusStr = null;
        }
        if (statusStr == null)
            return findAll();
        return saleDAO.findByStatus(statusStr);
    }

    @Override
    public List<SaleItem> getSaleItems(int saleId) {
        return new ArrayList<>();
    }

    @Override
    public BigDecimal getTodaySales() {
        // 统计当天已结算订单金额
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new java.util.Date());
        try {
            java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf2.parse(today + " 00:00:00");
            Date endDate = sdf2.parse(today + " 23:59:59");
            return saleDAO.getTotalSales(startDate, endDate);
        } catch (Exception e) {
            return java.math.BigDecimal.ZERO;
        }
    }

    @Override
    public int getTodayOrderCount() {
        // 统计当天已结算订单数量
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new java.util.Date());
        try {
            java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf2.parse(today + " 00:00:00");
            Date endDate = sdf2.parse(today + " 23:59:59");
            return saleDAO.getSaleCount(startDate, endDate);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public BigDecimal getMonthSales() {
        return null;
    }

    @Override
    public int getMonthOrderCount() {
        return 0;
    }

    @Override
    public int getProductSalesCount(int productId) {
        return 0;
    }

    @Override
    public BigDecimal getMemberConsumption(int memberId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getCashierSalesStats(String startDate, String endDate) {
        return new ArrayList<>();
    }

    @Override
    public Map<String, Object> getDailyStats(String date) {
        return new HashMap<>();
    }

    @Override
    public Map<String, Object> getMonthlyStats(String year, String month) {
        return new HashMap<>();
    }
}