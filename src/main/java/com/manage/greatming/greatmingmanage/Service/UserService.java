package com.manage.greatming.greatmingmanage.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manage.Exception.UserException;
import com.manage.greatming.greatmingmanage.DAO.Balance;
import com.manage.greatming.greatmingmanage.DAO.User;
import com.manage.greatming.greatmingmanage.DAO.UserMapper;
import com.manage.greatming.greatmingmanage.Utils.Md5Util;
import com.manage.greatming.greatmingmanage.Utils.TimeUtil;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    BalanceService balanceService;

    public List<User> findAll() {
        return userMapper.findAllUsers();
    }

    public User findById(long id) {
        return userMapper.findById(id);
    }

    public void updateUser(User user) {
        user.setPasswd(userMapper.findById(user.getId()).getPasswd());
        userMapper.updateUser(user);
    }

    public void addNewUser(User user) {
        String passwd = Md5Util.getMD5String("123456");
        user.setPasswd(passwd);
        userMapper.addNewUser(user);
    }

    public void delete(long id) {
        userMapper.deleteById(id);
    }

    @Transactional //wait test
    public void AKBCount(String username, int attendance, int kills) throws UserException{
        User user = userMapper.findByName(username);
        if (user != null) {
            long id = user.getId();
            Balance balance = balanceCount(id, attendance, kills);
            userMapper.updateAttendance(id, attendance);
            userMapper.updateKills(id, kills);
            balanceService.addNewBalance(id,balance);
        }else{
            throw new UserException(TimeUtil.GetDate()+" "+username+" is not exist.");
        }
        
}

    public Balance balanceCount(long id, int attendance, int kills) {
        User user = userMapper.findById(id);
        String username = user.getName();
        
        String company = user.getCompany();
        String ranks = user.getRanks();

        HashMap<String, Double> map = new HashMap<>();
        map.put("YB[III]", 1.0);
        map.put("YB[II]", 1.0);
        map.put("YB[I]", 1.0);
        map.put("BH", 2.0);
        map.put("XW", 2.0);
        map.put("QH", 3.0);
        map.put("DW[II]", 3.0);
        map.put("DW[I]", 3.0);
        map.put("CH", 3.0);
        map.put("ZH", 3.0);
        map.put("DZH", 3.0);
        map.put("TZ", 3.0);
        map.put("JYW", 3.0);

        String description = "";
        double companybalance = 0;
        double ranksbalance = 0;

        if (attendance >= 3) {
            companybalance += 1.0;
            if (attendance >= 6) {
                companybalance += 1.0;
                if (attendance >= 8) {
                    companybalance += 1.0;
                }
            }
            if (map.containsKey(ranks)) {
                ranksbalance = map.get(ranks);
            }else{
                description+="ranks isn't exist.";
            }
        }
        
        // System.out.println(username+"'s company is: "+company);
        if (company.equals("WJ") || company.equals("SJ")){
            if (kills > 30) {
                companybalance += 1.0;
            }
        }else if (company.equals("SQ")) {
            // System.out.println(username+" SQ kills is "+kills);
            if (kills > 50) {
                companybalance += 1.0;
            }
        }else if (company.equals("SJP")) {
            companybalance += 1.0;
        }else{
            System.out.println(username+" company is "+company+". it's isn't exist.");
        }

        Balance balance = new Balance();
        balance.setUserid(user.getId());
        balance.setCompany(companybalance);
        balance.setRanks(ranksbalance);
        balance.setSum(ranksbalance + companybalance);
        balance.setOther(0.0);
        balance.setDescription(description);
        // System.out.println(username+"companybalance:"+companybalance+"ranksbalance:"+ranksbalance);
        // balanceService.addNewBalance(user.getId(),balance);
        return balance;
        
    }
}
