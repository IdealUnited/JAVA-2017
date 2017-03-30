
package com.idealunited.inf.dto;

import java.util.List;
import java.util.Set;

import com.idealunited.inf.model.Model;




public interface DtoUtil {
	
    Model convert2Model(Dto dto);
    
    Dto convert2Dto(Model model);
    
    List convert2Models(List dtos);
    
    List convert2Models(Set dtos);
    
    List convert2Dtos(List models);
    
    List convert2Dtos(Set models);
    
    Class getModelClass();
    
    Class getDtoClass();
    
//    Dto wrapDtoObject(
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception;
}
