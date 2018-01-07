-- Question 1: Presumes inserted table's name is cookiecnn
INSERT INTO cookiecnn(cookieid, firstvisit, lastvisit)
        SELECT l.cookieid,
          MIN(viewtimestamp) as first_visit,
          MAX(viewtimestamp) as last_visit
        FROM logs l
          INNER JOIN location loc ON l.locationid = loc.locationid AND loc.state = 'New York'
          INNER JOIN site s2 ON l.siteid = s2.siteid AND s2.sitename <> 'CNN' AND s2.sitevertical = 'News'
          WHERE l.viewtimestamp > '2017-08-01' AND l.viewtimestamp < '2017-09-01'
      GROUP BY l.cookieid;


--Question 2
SELECT userid FROM useraddress
WHERE state = 'New York'
      AND start_datetime <= '2017-07-01'
      AND end_datetime > '2017-04-01'