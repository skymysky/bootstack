/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bootstack.core.config.handler;

import com.bootstack.common.validation.ValidationUtils;
import com.bootstack.model.common.CommonResponseModel;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p> BootStackExceptionHandler </p>
 * <p> Description : BootStackExceptionHandler </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 15:16 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@ControllerAdvice
@ResponseBody
public class BootStackExceptionHandler {

    /**
     * POST validation error
     *
     * @param exception param validation exception
     * @return error result
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
        return CommonResponseModel.validateError(ValidationUtils.extractValidate(exception.getBindingResult()));
    }

    /**
     * GET validation error
     *
     * @param exception param validation exception
     * @return error result
     */
    @ExceptionHandler(value = BindException.class)
    public Object methodArgumentNotValidHandler(BindException exception) {
        return CommonResponseModel.validateError(ValidationUtils.extractValidate(exception.getBindingResult()));
    }

}