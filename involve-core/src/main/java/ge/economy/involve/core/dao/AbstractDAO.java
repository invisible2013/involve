package ge.economy.involve.core.dao;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by nino on 5/5/16.
 */
@Repository
public class AbstractDAO {

    @Autowired
    protected DSLContext dslContext;
}
