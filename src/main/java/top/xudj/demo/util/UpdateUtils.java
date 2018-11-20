/**
 * Project Name:liz-user-mgmt
 * File Name:UpdateUtils.java
 * Package Name:com.gemii.lizcloud.core.usermgmt.utils
 * Date:2017年11月17日下午6:21:02
 * Copyright (c) 2017, chenxj All Rights Reserved.
 *
*/

package top.xudj.demo.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xudj
 */

public class UpdateUtils {
	/**
	 * 用来实体对象中的非null赋值
	 */

	/**
	 * 将空值的属性从src实体类中复制到target实体类中
	 * 
	 * @param src
	 *            : 要将属性中的非空值覆盖的对象(源实体类)
	 * @param target
	 *            :从数据库根据id查询出来的目标对象
	 */
	public static void copyNonNullProperties(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullProperties(src));
	}

	/**
	 * 将为空的properties给找出来,然后返回出来
	 * 
	 * @param src
	 * @return
	 */
	private static String[] getNullProperties(Object src) {
		BeanWrapper srcBean = new BeanWrapperImpl(src);
		PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
		Set<String> emptyName = new HashSet<>();
		for (PropertyDescriptor p : pds) {
			Object srcValue = srcBean.getPropertyValue(p.getName());
			if (srcValue == null) {
				emptyName.add(p.getName());
			}
		}
		String[] result = new String[emptyName.size()];
		return emptyName.toArray(result);
	}
}
