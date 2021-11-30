module testfx
{
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    opens Entities to org.hibernate.orm.core;
    exports Entities;
    exports users;
    opens users to org.hibernate.orm.core;
    exports server;
    opens server to org.hibernate.orm.core;
}