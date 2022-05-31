package it.myevents.myevents.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class DAO<T> {

    private static final String ALL = "SELECT * FROM ";
    private static final String RANDOM = " ORDER BY RANDOM() LIMIT 1";

    String table;
    Function<ResultSet, Optional<T>> getFromResult;
    Function<T, String> getInsertQuery;

    public DAO(String table, Function<ResultSet, Optional<T>> getFromResult, Function<T, String> getInsertQuery) {
        this.table = table;
        this.getFromResult = getFromResult;
        this.getInsertQuery = getInsertQuery;
    }

    public boolean insert(T t) {
        return insertPrint(t);
    }

    public boolean insertAll(Collection<T> coll) {
        boolean check = false;
        for (T t : coll)
            check = insertNoPrint(t) && check;
        return check;
    }

    public Optional<T> getRandom() {
        return singleSelect(ALL + table + RANDOM);
    }

    public List<T> getAll() {
        return multipleSelect(ALL + table);
    }

    private boolean insertPrint(T t) {
        return insert(t, true);
    }

    private boolean insertNoPrint(T t) {
        return insert(t, false);
    }

    protected boolean insert(T t, boolean print) {
        PreparedStatement statement = null;
        boolean inserted = false;
        String query = getInsertQuery.apply(t);
        try {
            statement = ConnectionFactory.getConnection().prepareStatement(query);
            inserted = statement.executeUpdate() == 0 ? false : true;
        } catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query: " + query);
            e.printStackTrace();
        } finally {
            if (print && inserted)
                System.out.println("Inserimento di " + t.toString() + " avvenuto con successo");
            if (!inserted)
                System.out.println("Non è stato possibile inserire " + t.toString());
        }
        return inserted;
    }

    protected Optional<T> singleSelect(String query) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<T> optional = Optional.empty();
        try {
            statement = ConnectionFactory.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optional = getFromResult.apply(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query:" + query);
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return optional;
    }

    protected List<T> multipleSelect(String query) {
        List<T> list = new LinkedList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Optional<T> optional = Optional.empty();
        try {
            statement = ConnectionFactory.getConnection().prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                optional = getFromResult.apply(resultSet);
                if (!optional.isEmpty())
                    list.add(optional.get());
            }
        } catch (SQLException e) {
            System.out.println("Errore nell'esecuzione della query:" + query);
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection();
        }
        return list;
    }

    public static String wrapString(String string) {
        return "'" + string + "'";
    }
}