package service.impl;

import dao.StockLogDAO;
import dao.impl.StockLogDAOImpl;
import entity.StockLog;
import service.StockLogService;

public class StockLogServiceImpl implements StockLogService {
    private StockLogDAO stockLogDAO = new StockLogDAOImpl();

    @Override
    public void addLog(StockLog log) {
        stockLogDAO.addLog(log);
    }
}