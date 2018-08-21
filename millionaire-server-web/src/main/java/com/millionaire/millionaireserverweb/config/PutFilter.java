package com.millionaire.millionaireserverweb.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.HttpPutFormContentFilter;

/**
 * @author Liu Kai
 * @Description: TODO 获取put请求body传入的参数
 * @date 2018/8/19 22:43
 */
@Component
public class PutFilter extends HttpPutFormContentFilter {
}
