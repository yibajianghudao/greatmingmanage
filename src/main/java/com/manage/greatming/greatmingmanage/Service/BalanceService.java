package com.manage.greatming.greatmingmanage.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.greatming.greatmingmanage.DAO.Balance;
import com.manage.greatming.greatmingmanage.DAO.BalanceMapper;
import com.manage.greatming.greatmingmanage.DAO.UserMapper;

@Service
public class BalanceService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BalanceMapper balanceMapper;

    public List<Balance> findAll() {
        return balanceMapper.findAllBalance();
    }

    public Balance findByUserId(long userid) {
        return balanceMapper.findByUserId(userid);
    }
    @Transactional
    public void addNewBalance(long id, Balance balance) {
        // System.out.println(balance.getSum());
        double beforeBalance = userMapper.findBalance(id);
        balanceMapper.addNewBalance(balance);
        userMapper.updateBalance(id, balance.getSum());
        double afterBalance = userMapper.findBalance(id);
        if ((afterBalance-beforeBalance) == balance.getSum()) {
            System.out.println();
        }
    }

    public void deleteByBalanceId(long balanceid) {
        balanceMapper.deleteByBalanceId(balanceid);
    }
    
}
