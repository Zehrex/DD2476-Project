1
https://raw.githubusercontent.com/ryewen/quickbuy/master/src/main/java/com/loststars/quickbuy/service/impl/UserServiceImpl.java
package com.loststars.quickbuy.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loststars.quickbuy.dao.UserDOMapper;
import com.loststars.quickbuy.dao.UserPasswordDOMapper;
import com.loststars.quickbuy.dataobject.UserDO;
import com.loststars.quickbuy.dataobject.UserPasswordDO;
import com.loststars.quickbuy.error.BusinessException;
import com.loststars.quickbuy.error.EmBusinessError;
import com.loststars.quickbuy.service.UserService;
import com.loststars.quickbuy.service.model.UserModel;
import com.loststars.quickbuy.validator.ValidationResult;
import com.loststars.quickbuy.validator.ValidatorImpl;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDOMapper userDOMapper;
    
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    
    @Autowired
    private ValidatorImpl validatorImpl;
    
    @Override
    public UserModel getUserById(Integer id) {
        UserDO userDO = userDOMapper.selectByPrimaryKey(id);
        if (userDO == null) return null;
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        return convertFromDataObject(userDO, userPasswordDO);
    }

    private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
        if (userDO == null) return null;
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDO, userModel);
        if (userPasswordDO != null) {
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
        }
        return userModel;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(UserModel userModel) throws BusinessException {
        if (userModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        ValidationResult validationResult = validatorImpl.validate(userModel);
        if (validationResult.isHasErrors()) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
        try {
            UserDO userDO = convertFromModel(userModel);
            userDOMapper.insertSelective(userDO);
            userModel.setId(userDO.getId());
            UserPasswordDO userPasswordDO = convertPasswordFromModel(userModel);
            userPasswordDOMapper.insertSelective(userPasswordDO);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已注册");
        }
    }
    
    private UserDO convertFromModel(UserModel userModel) {
        if (userModel == null) return null;
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userModel, userDO);
        return userDO;
    }
    
    private UserPasswordDO convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) return null;
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        userPasswordDO.setUserId(userModel.getId());
        return userPasswordDO;
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(encrptPassword)) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        UserDO userDO = userDOMapper.selectByTelphone(telphone);
        if (userDO == null) throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userDO.getId());
        if (! userPasswordDO.getEncrptPassword().equals(encrptPassword)) throw new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
        return convertFromDataObject(userDO, userPasswordDO);
    }
}
