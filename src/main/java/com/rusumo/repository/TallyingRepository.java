package com.rusumo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rusumo.models.Mdl_tallying;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TallyingRepository extends JpaRepository<Mdl_tallying, Long> {

    @Query(value = "select t.id as id, t.account_id,   t.date_time d1, t.description, t.item_name, t.quantity, t.weight, t.arriv_tallying, \n"
            + "             a.id as arriv_id, a.client_arriv, a.entry_arrival,\n"
            + "             e.id as entry_id,   e.country, e.date_time, e.ddcom, e.descripiion, e.plate_no, e.stat_del, e.stat_paid, e.status, e.`year` ,\n"
            + "             c.id client_id,   c.address, c.date_time d2, c.name, c.surname, c.telephone, c.tin\n"
            + "             from tallying t \n"
            + "             join arrival a on a.id =t.arriv_tallying \n"
            + "             join entry e on  a.entry_arrival = e.id\n"
            + "             join client c on e.client_entry  = c.id \n"
            + "             where e.id= ? ", nativeQuery = true)
    public List<Mdl_tallying> findArrivalByEntry(String entryId);
}
