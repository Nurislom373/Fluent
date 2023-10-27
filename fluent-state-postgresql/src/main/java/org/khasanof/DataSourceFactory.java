package org.khasanof;

import javax.sql.DataSource;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 10/27/2023 10:26 PM
 */
public interface DataSourceFactory {

    DataSource createDataSource();

}
