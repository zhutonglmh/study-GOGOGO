package com.zt.study.conf;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhutong
 * create by zhutong on 2018/7/20
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Object writeDataSource; //写数据源

    private Object readDataSource; //读数据源

    @Override
    public void afterPropertiesSet() {
        if (null == this.writeDataSource) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }
        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
        if(readDataSource != null) {
            targetDataSources.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if(dynamicDataSourceGlobal == null
                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            logger.debug("写");
            return DynamicDataSourceGlobal.WRITE.name();
        }
        logger.debug("读");
        return DynamicDataSourceGlobal.READ.name();
    }

    /**
     * Getter method for property <tt>writeDataSource</tt>.
     *
     * @return property value of writeDataSource
     */
    public Object getWriteDataSource() {
        return writeDataSource;
    }

    /**
     * Setter method for property <tt>writeDataSource </tt>.
     *
     * @param writeDataSource value to be assigned to property writeDataSource
     */
    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    /**
     * Getter method for property <tt>readDataSource</tt>.
     *
     * @return property value of readDataSource
     */
    public Object getReadDataSource() {
        return readDataSource;
    }

    /**
     * Setter method for property <tt>readDataSource </tt>.
     *
     * @param readDataSource value to be assigned to property readDataSource
     */
    public void setReadDataSource(Object readDataSource) {
        this.readDataSource = readDataSource;
    }
}
