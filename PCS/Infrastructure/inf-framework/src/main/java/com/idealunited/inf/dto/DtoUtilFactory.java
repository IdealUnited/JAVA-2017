
package com.idealunited.inf.dto;


public interface DtoUtilFactory {
        /**
         * 增加一个DtoUtil对象.
         * @param util
         */
        DtoUtilFactory addDtoUtil(DtoUtil util);
        
        /**
         * 返回一个Model或者Dto对应的DtoUtil对象.
         * @param clazz
         * @return
         */
        DtoUtil getDtoUtil(Class clazz);
}
