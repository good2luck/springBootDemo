package top.xudj.demo.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: demo
 * @description: ids
 * @author: xudj
 * @create: 2018-08-16 14:30
 **/
@Data
public class IdParam implements Serializable {

    private List<String> ids;

}
