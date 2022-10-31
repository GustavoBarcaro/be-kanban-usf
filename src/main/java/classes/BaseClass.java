package classes;

import core.Database;

public abstract class BaseClass {
    protected Database conn;

    protected BaseClass() {
        this.conn = new Database();
    }
}
